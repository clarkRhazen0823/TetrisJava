package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetrominoes.*;

public class GameArea extends JPanel
{
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color[][] background;
    
    private TetrisBlock[] nextBlocks = new TetrisBlock[3];
    private JPanel nextPanelRef1;
    private JPanel nextPanelRef2;
    private JPanel nextPanelRef3;

    private TetrisBlock block;
    private TetrisBlock[] tetrominoes;
    private int[] bag = new int[7];
    private int bagPointer = 7;

    
    public GameArea(JPanel placeholder, JPanel next1, JPanel next2, JPanel next3, int columns) //CONSTRUCTOR
    {
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        this.nextPanelRef1 = next1;
        this.nextPanelRef2 = next2;
        this.nextPanelRef3 = next3;
        
        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize; 
        
        background = new Color[gridRows][gridColumns];
        
        tetrominoes = new TetrisBlock[]{new IShape(), new JShape(), new LShape(), new OShape(), new SShape(), new TShape(), new ZShape()};
        
        // Hook up custom traditional drawing canvas to Panel 1
        setupPreviewCanvas(this.nextPanelRef1, 0);
        // Hook up custom traditional drawing canvas to Panel 2
        setupPreviewCanvas(this.nextPanelRef2, 1);
        // Hook up custom traditional drawing canvas to Panel 3
        setupPreviewCanvas(this.nextPanelRef3, 2);
    }
    
        private void setupPreviewCanvas(JPanel panel, final int previewIndex)
    {
        if (panel == null) return;
        
        panel.add(new javax.swing.JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Read from the specific index slot in our preview array
                if (nextBlocks[previewIndex] == null) return;

                int[][] shape = nextBlocks[previewIndex].getShape();
                int h = nextBlocks[previewIndex].getHeight();
                int w = nextBlocks[previewIndex].getWidth();
                Color c = nextBlocks[previewIndex].getColor();

                // Centering math inside 100x100 panel bounds
                int cellSize = 20; 
                int startX = (100 - (w * cellSize)) / 2;
                int startY = (100 - (h * cellSize)) / 2;

                for (int row = 0; row < h; row++) {
                    for (int col = 0; col < w; col++) {
                        if (shape[row][col] == 1) {
                            int x = startX + (col * cellSize);
                            int y = startY + (row * cellSize);

                            g.setColor(c);
                            g.fillRect(x, y, cellSize, cellSize);
                            g.setColor(Color.BLACK);
                            g.drawRect(x, y, cellSize, cellSize);
                        }
                    }
                }
            }
        });
        panel.getComponent(0).setBounds(0, 0, 100, 100);
    }

    public void spawnBlock()
    {
        // 1. Initial setup check: If the queue is entirely empty (first run), populate all 3 look-ahead slots
        if (nextBlocks[0] == null)
        {
            for (int i = 0; i < 3; i++)
            {
                if (bagPointer >= bag.length) refillTraditionalBag();
                nextBlocks[i] = tetrominoes[bag[bagPointer]];
                bagPointer++;
            }
        }

        // 2. The immediate upcoming block (slot 0) becomes your active falling piece
        block = nextBlocks[0];
        block.spawn(gridColumns);

        // 3. Shift the entire remaining preview queue forward manually
        nextBlocks[0] = nextBlocks[1];
        nextBlocks[1] = nextBlocks[2];

        // 4. Draw a fresh block index from your 7-bag to fill the empty back slot (slot 2)
        if (bagPointer >= bag.length)
        {
            refillTraditionalBag();
        }
        nextBlocks[2] = tetrominoes[bag[bagPointer]];
        bagPointer++;

        // 5. Force all 3 sidebar layouts to clear and draw their updated shapes
        if (nextPanelRef1 != null) nextPanelRef1.repaint();
        if (nextPanelRef2 != null) nextPanelRef2.repaint();
        if (nextPanelRef3 != null) nextPanelRef3.repaint();
    }



    
    private void refillTraditionalBag()
    {
        for (int i = 0; i < bag.length; i++)
        {
            bag[i] = i;
        }

        for (int i = bag.length - 1; i > 0; i--)
        {
            int j = (int)(Math.random() * (i + 1));

            int temp = bag[i];
            bag[i] = bag[j];
            bag[j] = temp;
        }

        bagPointer = 0;
    }

    
    public boolean checkOutOfBounds()
    {
        if (block.getY() < 0) 
        {
            block = null;
            return true;
        }
        
        return false;
    }
    
    public boolean moveBlockDown()
    {
        if (checkBottom() == false) 
        {
            
            return false;
        }
        
        block.moveDown();
        repaint();
        
        return true;
    }
    
    public void moveBlockRight()
    {
        if (block == null) return;
        if ( !checkRight() ) return;
        
        block.moveRight();
        repaint();

    }

    public void moveBlockLeft()
    {
        if (block == null) return;
        if ( !checkLeft() ) return;
        block.moveLeft();
        repaint();

    }
    
    public boolean hardDrop()
    {
        if (block == null) return false;
        
        while(checkBottom())
        {
            block.moveDown();
        }
        
        moveBlockToBackground();
        repaint();
        
        return true;
    }    
    
    public void rotateBlock()
    {
        if (block == null) return;

        int oldX = block.getX();
        int oldY = block.getY();

        block.rotate();

        if(block.getLeftEdge() < 0)
            block.setX(0);

        if(block.getRightEdge() >= gridColumns)
            block.setX(gridColumns - block.getWidth());

        if(block.getBottomEdge() >= gridRows)
            block.setY(gridRows - block.getHeight());

        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        boolean collision = false;

        for(int row = 0; row < h; row++)
        {
            for(int col = 0; col < w; col++)
            {
                if(shape[row][col] != 0)
                {
                    int x = col + block.getX();
                    int y = row + block.getY();

                    if(y >= 0 && background[y][x] != null)
                    {
                        collision = true;
                    }
                }
            }
        }

        if(collision)
        {
            block.setX(oldX);
            block.setY(oldY);

            block.rotate();
            block.rotate();
            block.rotate();
        }

        repaint();
    }
    
    
    private boolean checkBottom()
    {
        if ( block.getBottomEdge() == gridRows)
        {
            return false;
        }
        
        int[][]shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for (int col = 0; col < w; col++) 
        {
            for (int row = h - 1; row >= 0; row--) 
            {
                if (shape[row][col] != 0) 
                {
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    if(y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        
        return true;
    }
    
    private boolean checkLeft()
    {
        if(block.getLeftEdge() == 0) return false;
        
        int[][]shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for (int row = 0; row < h; row++) 
        {
            for (int col = 0; col < w; col++) 
            {
                if (shape[row][col] != 0) 
                {
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();
                    if(y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        
        return true;
    }
    
    private boolean checkRight()
    {
        if(block.getRightEdge() == gridColumns) return false;
        
        int[][]shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for (int row = 0; row < h; row++) 
        {
            for (int col = w - 1; col >= 0; col--) 
            {
                if (shape[row][col] != 0) 
                {
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    if(y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        
        return true;
    }
    
    public int clearLines()
    {
        boolean lineFilled;
        int linesCleared = 0;
        
        for (int r = gridRows - 1; r >= 0; r--)
        {
            lineFilled = true;
            
            for(int c = 0; c < gridColumns; c++)
            {
                if (background[r][c] == null)
                {
                    lineFilled = false;
                    break;
                }
            }
            
            if(lineFilled)
            {
                linesCleared++;
                removeLine(r);
                shiftDown(r);
                removeLine(0);
                
                r++;
                
                repaint();
            }
        }
        return linesCleared;
    }
    
    private void removeLine(int r)
    {
        for ( int i = 0; i < gridColumns; i++) 
        {
            background[r][i] = null;
        }
    }
    
    private void shiftDown(int r)
    {
        for (int row = r; row > 0; row--) 
        {
            for (int col = 0; col < gridColumns; col++) 
            {
                background[row][col] = background[row - 1][col];
            }
        }
    }
    
    public void moveBlockToBackground()
    {
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        
        int xPos = block.getX();
        int yPos = block.getY();
        
        Color color = block.getColor();
        
        for (int r = 0 ; r < h; r++) 
        {
            for (int c = 0; c < w; c++) 
            {
                if (shape[r][c] == 1)
                {
                    background[r + yPos][c + xPos] = color;
                }
            }
        }
    }
    
    private void drawBlock(Graphics g)
    {
        int h = block.getHeight();
        int w = block.getWidth();
        Color c = block.getColor();
        int [][] shape = block.getShape();
        
        for (int row =0; row < h; row++)
        {
            for (int col = 0; col < w; col++)
            {
                if (shape[row][col] == 1)
                {
                    int x = (block.getX() + col) * gridCellSize;
                    int y = (block.getY() + row) * gridCellSize;
                    
                    drawGridSquare(g, c, x, y);
                }
            }
        }
    }
  
    private void drawBackground(Graphics g)
    {
        Color color;
        for (int r = 0; r < gridRows; r++) 
        {
            for (int c = 0; c < gridColumns; c++) 
            {
                color = background[r][c];
                
                if (color != null) 
                {
                    int x = c * gridCellSize;
                    int y = r * gridCellSize;
                    
                    drawGridSquare(g, color, x, y);
                }
            }
            
        }
    }
    
    private void drawGridSquare(Graphics g, Color color, int x, int y)
    {
        g.setColor(color);
        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        drawBackground(g);
        drawBlock(g);
    }
}

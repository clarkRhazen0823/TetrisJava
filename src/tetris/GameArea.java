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
    private TetrisBlock nextBlock;
    private TetrisBlock block;
    private TetrisBlock[] tetrominoes;
    private int[] bag = new int[7];
    private int bagPointer = 7;
    private JPanel nextPanelRef;

    
    public GameArea(JPanel placeholder, JPanel nextPanel,int columns) //CONSTRUCTOR
    {
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        this.nextPanelRef = nextPanel;
        
        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize; 
        
        background = new Color[gridRows][gridColumns];
        
        tetrominoes = new TetrisBlock[]{new IShape(), new JShape(), new LShape(), new OShape(), new SShape(), new TShape(), new ZShape()};
        
        this.nextPanelRef.add(new javax.swing.JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (nextBlock == null) return;

                int[][] shape = nextBlock.getShape();
                int h = nextBlock.getHeight();
                int w = nextBlock.getWidth();
                Color c = nextBlock.getColor();

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
        this.nextPanelRef.getComponent(0).setBounds(0, 0, 100, 100);
    }
    
        public void spawnBlock()
    {
        if (nextBlock == null)
        {
            if (bagPointer >= bag.length) refillTraditionalBag();
            nextBlock = tetrominoes[bag[bagPointer]];
            bagPointer++;
        }

        block = nextBlock;
        block.spawn(gridColumns);

        if (bagPointer >= bag.length)
        {
            refillTraditionalBag();
        }
        int nextPieceIndex = bag[bagPointer];
        bagPointer++;

        nextBlock = tetrominoes[nextPieceIndex];

        if (nextPanelRef != null)
        {
            nextPanelRef.repaint();
        }
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

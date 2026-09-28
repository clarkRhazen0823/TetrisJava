package tetrominoes;

import java.awt.Color;
import tetris.TetrisBlock;



public class LShape extends TetrisBlock
{
    public LShape()
    {
        super(new int[][]{ {1,0},{1,0},{1,1} }, Color.BLUE);
    }
}

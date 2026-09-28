package tetrominoes;

import java.awt.Color;
import tetris.TetrisBlock;

public class JShape extends TetrisBlock
{
    public JShape()
    {
        super(new int[][]{{0,1},{0,1},{1,1}} , Color.ORANGE);
    }
}

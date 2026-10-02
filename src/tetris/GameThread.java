package tetris;

public class GameThread extends Thread
{
    private GameArea ga;
    private GameForm gf;
    private int lines;
    private int level = 1;
    private int linesPerLvl = 10;
    
    private int tickRate = 1000;
    private int speedPerLvl = 100;
    
    public GameThread(GameArea ga, GameForm gf)
    {
        this.ga = ga;
        this.gf = gf;
    }
    
    @Override
    public void run()
    {        
        // Spawn the first block before entering the loop to establish the game state
        ga.spawnBlock();
        
        while(true)
        {
            // Keep shifting the piece downwards until it meets an obstacle
            while ( ga.moveBlockDown() )
            {
                try 
                {
                    Thread.sleep(tickRate);
                } 
                catch (InterruptedException ex) 
                {
                    // Classic standard output fallback handling
                    ex.printStackTrace();
                }
            }
            
            // The block is locked; place it permanently into the matrix grid
            ga.moveBlockToBackground();
            
            // Evaluate the Game Over criteria immediately upon landing
            if (ga.checkOutOfBounds())
            {
                System.out.println("<<========== GAME OVER ==========>>");
                break;
            }
            
            // Clear filled segments and update metrics counters
            lines += ga.clearLines();
            gf.updateScore(lines);
            
            int lvl = lines / linesPerLvl + 1;
            if (lvl > level)
            {
                level = lvl;
                gf.updateLvl(level);
                if (tickRate > 100) tickRate -= speedPerLvl;
            }
            
            // Spawn the next piece from our customized randomizer pool
            ga.spawnBlock();
        }
    }
}

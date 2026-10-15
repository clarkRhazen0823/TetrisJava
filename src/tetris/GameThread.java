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
        ga.spawnBlock();
        
        while(true)
        {
            try 
            {
                Thread.sleep(tickRate);
            } 
            catch (InterruptedException ex) 
            {
                // Interrupted by drop/lock action
            }
            
            // Move block down 1 step; if it cannot move down, lock and spawn
            if (!ga.moveBlockDown())
            {
                ga.moveBlockToBackground();
                
                if (ga.checkOutOfBounds())
                {
                    System.out.println("<<========== GAME OVER ==========>>");
                    break;
                }
                
                lines += ga.clearLines();
                gf.updateScore(lines);
                
                int lvl = lines / linesPerLvl + 1;
                if (lvl > level)
                {
                    level = lvl;
                    gf.updateLvl(level);
                    if (tickRate > 100) tickRate -= speedPerLvl;
                }
                
                ga.spawnBlock();
            }
        }
    }
}
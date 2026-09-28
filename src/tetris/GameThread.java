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
        while(true)
        {
            ga.spawnBlock();
            while ( ga.moveBlockDown() )
            {
                try 
                {

                    Thread.sleep(tickRate);
                } 
                catch (InterruptedException ex) 
                {
                    System.getLogger(GameThread.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
            
            if (ga.checkOutOfBounds())
            {
                System.out.println("<<========== GAME OVER ==========>>");
                break;
            }
            
            ga.moveBlockToBackground();
            lines += ga.clearLines();
            gf.updateScore(lines);
            
            int lvl = lines / linesPerLvl + 1;
            if (lvl > level)
            {
                level = lvl;
                gf.updateLvl(level);
                tickRate -= speedPerLvl;
            }
        }

    }
}

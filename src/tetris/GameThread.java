package tetris;

public class GameThread extends Thread
{
    private GameArea ga;
    
    public GameThread(GameArea ga)
    {
        this.ga = ga;
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

                    Thread.sleep(500);
                } 
                catch (InterruptedException ex) 
                {
                    System.getLogger(GameThread.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        }

    }
}

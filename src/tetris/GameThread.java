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
            try 
            {
                System.out.println("Playing TETRIS");
            
                ga.moveBlockDown();
                Thread.sleep(1000);
            } 
            catch (InterruptedException ex) 
            {
                System.getLogger(GameThread.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }

    }
}

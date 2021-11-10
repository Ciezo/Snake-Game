import javax.swing.SwingWorker;

public class Worker extends SwingWorker<Void, String> {

    public static String fetchers = EventMaster.deliverCmnd();
    
    @Override
    protected Void doInBackground() throws Exception {
        // TODO: SwingWorker instance in here on a standalone class, "Worker.java"
            // Log to console
                System.out.println("Currently working in background: WORKER.JAVA EXTENDS SWINGWORKER");

            // TODO: Try and play the main window or home page background music
                SnakeGame.playBGonStart();
            
            while (Snake.game_isRunning) {   
                // Find out the state or current action command upon runtime of the app        
                    System.out.println("\t ON BACKGROUND, ACTION IS SET TO: " + fetchers);

                switch (fetchers) {
                    case "grassPick": 
                        System.out.println("\t ON BACKGROUND, ACTION IS SET TO: " + fetchers);    
                        cancel(true); 

                        break;

                    default:
                        System.out.println("");
                        break;
                }
            }

        return null;
    }
}
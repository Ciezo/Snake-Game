import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.Random;
import java.io.File;
import javax.sound.sampled.*;


public class Snake extends JPanel implements ActionListener {

    private static final int WIDTH = 960; 
    private static final int HEIGHT = 720; 
    private static final int SIZE_CHUNK = 25; 
    private static final int GAME_UNITS = (WIDTH*HEIGHT)/SIZE_CHUNK;
    final static  int x[] = new int[GAME_UNITS];
    final static int y[] = new int[GAME_UNITS];
    static final int DELAY = 75;

    boolean gridFlag; 
    boolean removeGridFlag; 

    static int heartCount = 3; 
    static int snakeBodyCount = 2; 
    static int apples;
    static int apple_X;
    static int apple_Y;

    char directionTo = 'R';
    boolean game_isRunning = true;          // Try and set already to true, so, we can meet the condition to render the Snake character
    Timer timer; 
    static Random random;  

    Image snakeBodyChunk; 
    Image snakeHeadChunk;
    Image apple_render; 

    File audio;
    AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;
    Clip clip;

    public Snake() {
        // Setting up and initialzing the panel
            this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            this.setLayout(null);
            // this.setBounds(0, 120, WIDTH, HEIGHT);
            // this.setLocation(0, 120);
            this.setOpaque(false); 
            this.setFocusable(true); 
            this.addKeyListener(new GLOBAL_MOVEMENT());
        
        // TODO: Add function to initialize and prep all panel resources
            init_globalPanel();
    }



    private void init_globalPanel() {
        // Call the dedicated function to load assets 
            fetchResources();

        // Call the method dedicated in setting up game mechanics
            prep_GameMechanic();    

    }



    private void prep_GameMechanic() { 
        // Prep and init the apple coords
            appleCoordSys();
        
        // Instantiate the timer upon runtime 
            timer = new Timer(DELAY, this);  
            timer.start();

    }


    // Upon start and instance of Timer, we immediately start to paint the components, in other words, we immediately start rendering our graphical objects 
    @Override
    public void paintComponent(Graphics g) {
        // Reference and call from the abstract class and its method
            super.paintComponent(g);
    
        // Call our method of rendering graphical objects
            mainRenderingSys(g);
    }

    private void mainRenderingSys(Graphics g) {
        // If user is in-game, or as, game_isRunning == true
        // Then..Start rendering objects
            if (game_isRunning) {
                // Add some grid lines
                    if (gridFlag == true) {
                        // Draw and render grid lines
                        for (int j = 0; j < HEIGHT/SIZE_CHUNK; j++) {
                            g.drawLine(j*SIZE_CHUNK, 0, j*SIZE_CHUNK, HEIGHT);
                            g.drawLine(0, j*SIZE_CHUNK, WIDTH, j*SIZE_CHUNK);
                        }
                    }

                    /** NOTE THIS IS CAUSING SOME BUGS, WE NEED A BETTER METHOD TO REMOVE THE GRIDLINES */
                    // if (removeGridFlag == true) {
                    //     // Try and remove grid lines
                    //     for (int i = 0; i < HEIGHT/SIZE_CHUNK; i++) {
                    //         g.clearRect(i*SIZE_CHUNK, 0, i*SIZE_CHUNK, HEIGHT);
                    //         g.clearRect(0, i*SIZE_CHUNK, WIDTH, i*SIZE_CHUNK);
                    //     }
                    // }

                // Render the apple graphic
                    g.drawImage(apple_render, apple_X, apple_Y, this);

                /** TEST CASE FOR APPLE RENDERING SAMPLE OBJECT */
                // g.setColor(Color.red);
                // g.fillOval(apple_X, apple_Y, SIZE_CHUNK, SIZE_CHUNK);

                for (int i = 0; i < snakeBodyCount; i++) {
                    if (i == 0) {
                        // Try and render the snake head
                            g.drawImage(snakeHeadChunk, x[i], y[i], this); 
                        
                        /** TEST CASE for head 
                         * Uncomment if necessary for test casing */  
                            // g.setColor(Color.CYAN);                     
                            // g.fillRect(x[i], y[i], SIZE_CHUNK, SIZE_CHUNK);
                    }
                    
                    else {
                        // Try and render the snake body 
                            g.drawImage(snakeBodyChunk, x[i], y[i], this);

                        /** TEST CASE for body 
                         * Uncomment if necessary for test casing */  
                            // g.setColor(Color.blue);
                            // g.fillRect(x[i], y[i], SIZE_CHUNK, SIZE_CHUNK);
                    }
                }

                // Sync all graphical state
                    Toolkit.getDefaultToolkit().sync();

            }

        // When game is over 
            else {
                System.out.println("Gameover!");
            }

    }



    private static void appleCoordSys() {
         // Random instance
            random = new Random(); 

        // Apple X-coord
            apple_X = random.nextInt((int)(WIDTH/SIZE_CHUNK))*SIZE_CHUNK;

        // Apple Y-coord
            apple_Y = random.nextInt((int)(HEIGHT/SIZE_CHUNK))*SIZE_CHUNK;
    }



    private static void appleCountANDcheck() {
        // This helps to check whether the x and y coords of snake character is colliding with the apple coords of x and y 
        if ((x[0] == apple_X) && (y[0] == apple_Y)) {
            snakeBodyCount++;
            apples++; 
            System.out.println("Apples eaten: " + apples);

            // Try and set the score updates to the dashboard
            /** TODO: TRY AND FETCH THE SCORE LABEL FROM GAMEPLAY DASHBOARD TO UPDATE ACCORDING TO THE APPLES EATEN */
            updateToDashboard();

            // Call the dedicated method to generate new coords for apple
            appleCoordSys();
        } 
    }



    public static void updateToDashboard() {
        // Log to console
        System.out.println("Updating dashboard!");
        System.out.println("\t \t **Score value from updateToDashboard: " + apples + "**");
        System.out.println("\t \t Hearts count: " + heartCount);
        
        // Update score value on dashboard
        SnakeGame.setScoreOnDashBoardLabel("x" + String.valueOf(apples));

        // Update the heart count accordingly when the snake gets hurt
        if (heartCount == 2) {
            SnakeGame.heartPanel.remove(SnakeGame.heart1);
        }

        else if (heartCount == 1) {
            SnakeGame.heartPanel.remove(SnakeGame.heart2);
        }

        else if (heartCount == 0) {
            SnakeGame.heartPanel.remove(SnakeGame.heart3);
        }

    }



    private void movementTo() {
        for (int i = snakeBodyCount; i > 0; i-- ) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }   
        
        switch (directionTo) {
            // UP 
            case 'U':
                y[0] = y[0] - SIZE_CHUNK;
                
                break;

            // DOWN 
            case 'D':
                y[0] = y[0] + SIZE_CHUNK;
                
                break;

            // LEFT 
            case 'L':
                x[0] = x[0] - SIZE_CHUNK;
                
                break;

            // RIGHT    
            case 'R':
                x[0] = x[0] + SIZE_CHUNK;
                break;
        }
    }



    private void collisionDetectionSys() {
        // Helps and checks to see if the snake has hit itself or the wall 

        /** NOTE:
         *  This for-loop helps to validate whether the snake has bitten 
         *  itself 
         */ for (int i = snakeBodyCount; i > 0; i--) {
                if ((x[0] == x[i] && y[0] == y[i])) {
                    heartCount--; 
                    System.out.println("Snake has bitten itself and it's hurt!");
                    System.out.println("Remaining hearts left: " + heartCount);
                    playSound("sfxpack/wav/hurt.wav");
                    
                    if (heartCount == 0) {
                        System.out.println("You have ran out of hearts because you bit yourself! GAME OVER");
                        game_isRunning = false;
                        playSound("sfxpack/wav/gameOver.wav");
                    }
                }
            }
        
        // A condition to see if the current x-cord is less than zero, meaning it can be out of bounds!
        if (x[0] < 0) {
            heartCount--; 
            if (heartCount == 0) {
                System.out.println("You have ran out of hearts as you kept trying to injure yourself!");
                game_isRunning = false;
                playSound("sfxpack/wav/gameOver.wav");
            }
        }

        // Out of bounds check, on width of screen  
        if (x[0] > WIDTH) {
            heartCount--; 
            System.out.println("\t SNAKE OUT OF BOUNDS ON X-CORD OF X_arr");


            System.out.println("STOP GOING OUTSIDE BOUNDARIES! YOU ARE GOING TO HURT YOURSELF!");
            System.out.println("Remaining hearts left: " + heartCount);
            playSound("sfxpack/wav/hurt.wav");

            if (heartCount == 0) {
                System.out.println("You have ran out of hearts as you tried going out of horizontal bounds");
                System.out.println("YOU WENT TOO FAR OUTSIDE GAME BOUNDARIES! GAME OVER!");
                game_isRunning = false;
                playSound("sfxpack/wav/gameOver.wav");
            }
        }

        // Out of bounds check again, on y-cord
        if (y[0] < 0) {
            heartCount--; 
            System.out.println("\t SNAKE BITE OUT OF BOUNDS ON Y-CORD OF Y_arr");


            System.out.println("STOP GOING OUTSIDE BOUNDARIES! YOU ARE GOING TO HURT YOURSELF!");
            System.out.println("Remaining hearts left: " + heartCount);
            playSound("sfxpack/wav/hurt.wav");

            if (heartCount == 0) {
                System.out.println("You have ran out of hearts as you kept trying to injure yourself!");
                game_isRunning = false;
                playSound("sfxpack/wav/gameOver.wav");
            }
        }

        // Out of bounds check, on height of screen 
        if (y[0] > HEIGHT) {
            heartCount--; 
            System.out.println("STOP GOING OUTSIDE BOUNDARIES! YOU ARE GOING TO HURT YOURSELF!");
            System.out.println("Remaining hearts left: " + heartCount);
            playSound("sfxpack/wav/hurt.wav");

            if (heartCount == 0) {
                System.out.println("You have ran out of hearts as you tried going out of horizontal bounds");
                System.out.println("YOU WENT TOO FAR OUTSIDE GAME BOUNDARIES! GAME OVER!");
                game_isRunning = false;
                playSound("sfxpack/wav/gameOver.wav");
            }
        }

        // This goes back to the for-loop, whereas, the boolean flag for game running is set to false. Stop the game!
        if (!game_isRunning) {
            // Stop sending and firing action events
            timer.stop();
        }
    }


    private void fetchResources() {
        /** NOTE: Resources defined as textures or images, sound pack, fonts, and other assets to be loaded as well  */

        // Load and fetch up graphical assets 
            /** SNAKE BODY */  
            ImageIcon snk_BodyChunk__LOAD = new ImageIcon("assets/snake/body.png");
            snakeBodyChunk = snk_BodyChunk__LOAD.getImage(); 

            /** SNAKE HEAD */
            ImageIcon snk_HeadChunk__LOAD = new ImageIcon("assets/snake/head.png");
            snakeHeadChunk = snk_HeadChunk__LOAD.getImage();
            
            /** APPLE RENDERING */
            ImageIcon apple__LOAD = new ImageIcon("assets/items/apples/50x50/apple2.png");
            apple_render = apple__LOAD.getImage(); 
    }
    


    public void actionPerformed(ActionEvent e) {
        // Keep firing these methods and events as long as game is running 
        /** NOTE: game_isRunning == true , must be met */

        if (game_isRunning) {
            movementTo();                   // Snake movement
            appleCountANDcheck();           // Apple counts and apple checking
            collisionDetectionSys();        // Object collision checker
            updateToDashboard();            // Update values for dashboard
        }

        // Repaint the frame
        repaint();
    }


    public void playSound(String path) {
        try {
            
            audio = new File(path);
            stream = AudioSystem.getAudioInputStream(audio);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();

            // System.out.println("Trying to play SFX");
        }

        catch (Exception e) {
            // Errors on filestreaming will only occur IF FILE NOT EXITS
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }



    // This will handle all keyboard events for movement
    /** NOTE:
     *  WASD keys
     *  Arrow keys (up down left right)
     */
    private class GLOBAL_MOVEMENT extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()){
                /** KEYSTROKE 'W' AND ARROW UP */
                    case KeyEvent.VK_W:
                        if (directionTo != 'D') {
                            directionTo = 'U'; 
            
                            playSound("sfxpack/wav/U_m.wav");
                        }
                    break;
                    
                    case KeyEvent.VK_UP:
                        if(directionTo != 'D') {
                            directionTo = 'U';

                            playSound("sfxpack/wav/U_m.wav");
                        }
                    break;
                        
                        
                /** KEYSTROKE 'S' AND ARROW DOWN */
                    case KeyEvent.VK_S:
                        if (directionTo != 'U') {
                            directionTo = 'D';
        
                            playSound("sfxpack/wav/D_m.wav");
                        }
                    break;
                    
                    case KeyEvent.VK_DOWN:
                        if(directionTo != 'U') {
                            directionTo = 'D';
    
                            playSound("sfxpack/wav/D_m.wav");
                        }    
                    break;


                /** KEYSTROKE 'A' AND ARROW LEFT */
                    case KeyEvent.VK_A:
                        if (directionTo != 'R') {
                            directionTo = 'L';
        
                            playSound("sfxpack/wav/L_m.wav");
                        }
                    break;

                    case KeyEvent.VK_LEFT:
                        if (directionTo != 'R') {
                            directionTo = 'L';

                            playSound("sfxpack/wav/L_m.wav");
                        }
                    break;
                    
                        
                /** KEYSTROKE 'D' AND ARROW RIGHT */
                    case KeyEvent.VK_D: 
                        if(directionTo != 'L') {
                            directionTo = 'R';

                            playSound("sfxpack/wav/R_m.wav");
                        }
                    break;

                    case KeyEvent.VK_RIGHT:
                        if (directionTo != 'L') {
                            directionTo = 'R';

                            playSound("sfxpack/wav/R_m.wav");
                        }
                    break;
                

                /** KEYSTROKE 'G' */    
                    case KeyEvent.VK_G:
                        System.out.println("Adding some gridlines");
                        System.out.println("Keystroke G: isPressed!");
                        
                        // Set boolean for activating grid to true
                        gridFlag = true; 
                    break; 
                

                /** KEYSTORE 'H' */
                    case KeyEvent.VK_H:
                        System.out.println("Removing gridlines");

                        // Set boolean flag for grid lines to false
                        removeGridFlag = true; 
                    break;
            }
        }
    }
    
    
    
    public int getWidth() {
        return WIDTH;
    }
    
    public int getHeight() {
        return HEIGHT; 
    }



    /** NOTE:
     *  RUN AND TEST THROUGH MAIN, uncomment if needed tests to run this instance ALONE */ 
    // public static void main(String[] args) {
    //         // Create an instance of a frame
    //         JFrame frame = new JFrame("Snake"); 

    //         // Set some properties
    //         frame.setResizable(false);
    //         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
    //         // Add the Snake panel 
    //         frame.add(new Snake());
            
    //         // Pack, center, and set visible as true for the frame 
    //         frame.pack();
    //         frame.setLocationRelativeTo(null);
    //         frame.setVisible(true);
    // }
}   
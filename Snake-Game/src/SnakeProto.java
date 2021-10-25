import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.io.File;
import java.awt.Font;
import java.io.IOException;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import javax.sound.sampled.*;


public class SnakeProto extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 960;
    static final int SCREEN_HEIGHT = 720;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 5;                              // set how long the body size 
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;


    File audio;
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        Clip clip;
    

    // For rendering or painting the image to be used as background 
    Image img; 
    
    // Apply and implement custom background 
    /** NOTE: A BETTER IMPLEMENTATION HAS BEEN APPLIED,
     *        We now use the paintComponent method to render 
     *        the image to be used as a background 
     * 
     * @note The fix can be found within the fetch branch of the official repository
     */
    // JLabel gameBG; 



    /** Try and implement the same features and techniques we did from the previous classes */ 
    
    SnakeProto() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
        
        // Initialize the Image to be rendered        
        img = Toolkit.getDefaultToolkit().createImage("assets/backgrounds/grass_biome-BG_lv1.png");



        // @deprecated 
        // Initialize gameplay background
            // "assets/backgrounds/grass_biome-BG_lv1.png"
                /** LABEL TO BACKGROUND */                
                    // gameBG = new JLabel(new ImageIcon("assets/backgrounds/grass_biome-BG_lv1.png"));
                    // this.add(gameBG); 
    }

    public void startGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){

        // Render the img object
        g.drawImage(img, 0, 0, null); 
        
        if(running) {


            for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
                // g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                // g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }
            // Paints the apple over the screen 
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for(int i = 0;i < bodyParts;i++) {
                if(i == 0) {
                    // Set the color of head
                    g.setColor(Color.CYAN);                     
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else {
                    // Set the color of the body
                    // g.setColor(new Color(45,180,0));
                    g.setColor(Color.blue);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " +applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " +applesEaten))/2, g.getFont().getSize());
        }

        else {
            gameOver(g);
            System.out.println("The snake died! To restart, please press n"); 
            this.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                    System.out.println(e.getModifiersEx()); 
                    System.out.println(e.getKeyCode());
                    System.out.println(e.getKeyChar());
                    int key = e.getKeyCode(); 

                    if (key == KeyEvent.VK_N) {
                        System.out.println("Restarting game");
                        new GameFrame(); 
                        
                    }
                    
                    else {
                        System.out.println("");
                    }

                }

                @Override
                public void keyReleased(KeyEvent e) {
                }

            });   
        }
    }

    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }

    public void move(){
        for(int i = bodyParts;i>0;i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple(){
        if((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions(){
        for(int i = bodyParts;i>0;i--) {
            if((x[0] == x[i] && y[0] == y[i])) {
                running = false;
            }
        }
        if(x[0] < 0) {
            running = false;
        }
        if(x[0] > SCREEN_WIDTH) {
            running = false;
        }
        if(y[0] < 0) {
            running = false;
        }
        if(y[0] > SCREEN_HEIGHT) {
            running = false;
        }

        if(!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " +applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " +applesEaten))/2, g.getFont().getSize());

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(running) {
            move();
            checkApple();
            checkCollisions();
        }
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

            System.out.println("Trying to play SFX");
        }

        catch (Exception e) {
            // Errors on filestreaming will only occur IF FILE NOT EXITS
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';

                        playSound("sfxpack/wav/L_m.wav");
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';

                        playSound("sfxpack/wav/R_m.wav");
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';

                        playSound("sfxpack/wav/U_m.wav");
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';

                        playSound("sfxpack/wav/D_m.wav");
                    }
                    break;
                case KeyEvent.VK_W:
                    if (direction != 'D') {
                        direction = 'U'; 

                        playSound("sfxpack/wav/U_m.wav");
                    }
                    break; 
                case KeyEvent.VK_A:
                    if(direction != 'R') {
                        direction = 'L';

                        playSound("sfxpack/wav/L_m.wav");
                    }
                    break;
                case KeyEvent.VK_S:
                    if(direction != 'U') {
                        direction = 'D';

                        playSound("sfxpack/wav/D_m.wav");
                    }
                    break;
                case KeyEvent.VK_D: 
                    if(direction != 'L') {
                        direction = 'R';

                        playSound("sfxpack/wav/R_m.wav");
                    }
                    break;

            }
        }
    } 

    public static class GameFrame extends JFrame {

        // JFrame
        // JFrame game_frame; 

        // Fonts 
        public static Font timerF; 
        public static Font scoreF; 

        // Panels
        JPanel gameplay;
        JPanel dashboardPanel;
        JPanel navbar; 
        JPanel resPanel; 
        JPanel setPanel; 
        JPanel menuPanel; 
        JPanel quiPanel; 
        JPanel heartPanel;
        JPanel timerPanel;
        JPanel scorePanel; 

        // JLabels
        JLabel dashboard; 
        JLabel heart1;
        JLabel heart2;
        JLabel heart3;
        JLabel timer; 
        JLabel scoreLabel; 

        // JButtons for navbar
        JButton restart; 
        JButton settings; 
        JButton menu; 
        JButton quit; 


        GameFrame() {
            // Fonts, try setting up 
            runFonts();



            // Set up the frame 
            this.add(new SnakeProto());
            this.setSize(960, 720);
            this.setTitle("Now Playing! Snake Game Classic!");
            // this.setContentPane(new JLabel(new ImageIcon ("assets/backgrounds/grass_biome-BG_lv1.png")));

            // Set and initialize the dashboard texture with ImageIcon
            dashboard = new JLabel(new ImageIcon("assets/dashboard/dashboard2.png")); 

            // Set and initialzie the navbar icons: restart, settings, menu, quit with ImageIcon
            /** ImageIcons to set for the navbar buttohns*/
            ImageIcon restartIcon = new ImageIcon("assets/icons/restart.png"); 
            ImageIcon settingsIcon = new ImageIcon("assets/icons/settings.png");
            ImageIcon menuIcon = new ImageIcon("assets/icons/menu.png");
            ImageIcon quitIcon = new ImageIcon("assets/icons/quit.png");

             /** Restart navbar button */
            restart = new JButton(restartIcon);

            /** Settings navbar button */
            settings = new JButton(settingsIcon);

            /** Menu navbar button */
            menu = new JButton(menuIcon); 

            /** Quit navbar button */
            quit = new JButton(quitIcon);

            // Remove the default look of JButton for navbar
            restart.setOpaque(false);         // make invisible
            settings.setOpaque(false);        // make invisible
            menu.setOpaque(false);
            quit.setOpaque(false);         // make invisible 

            restart.setContentAreaFilled(false); 
            settings.setContentAreaFilled(false);
            menu.setContentAreaFilled(false);
            quit.setContentAreaFilled(false);

            restart.setBorderPainted(false); 
            settings.setBorderPainted(false);
            menu.setBorderPainted(false);
            quit.setBorderPainted(false);

            restart.setFocusPainted(false); 
            settings.setFocusPainted(false);
            menu.setFocusPainted(false);
            quit.setFocusPainted(false);

             // Set and initialize heart icons to set with ImageIcon 
            ImageIcon h1 = new ImageIcon("assets/icons/hearts.png");
            ImageIcon h2 = new ImageIcon("assets/icons/hearts.png");
            ImageIcon h3 = new ImageIcon("assets/icons/hearts.png");

            // Set and initialize hearts label set with ImageIcon 
            heart1 = new JLabel(h1);
            heart2 = new JLabel(h2);
            heart3 = new JLabel(h3); 


            // Set and initialize the timer label 
            timer = new JLabel("0:00");
            timer.setFont(timerF); 
            timer.setForeground(new Color(196, 8, 78));

            // Set and initialize the score label
            scoreLabel = new JLabel("x0"); 
            scoreLabel.setFont(scoreF);
            scoreLabel.setForeground(Color.BLACK);

            // Set and initialize the dashboard panel acting as container 
            /** Panel for dashboard */
            dashboardPanel = new JPanel(); 
            dashboardPanel.setOpaque(false); 
            dashboardPanel.setBounds(25, 1, 910, 250); 


            // Add components to dashboard 
            dashboardPanel.add(dashboard);

            // Add components to frame 
            // This seems to break everything......
            // IDK WHY 
            // IT DOES NOT BEHAVE TO THE WAY IT WAS SUPPOSED TO BE
            // this.add(dashboardPanel); 


            // Pack, center, and set visible as true for the frame 
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }



    }


    public static void runFonts() {
        Font timerF; 
        Font scoreF; 

        try {    
            // Fetch font through File reading from dir
                /** For timer */
                timerF = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/LCDM2N__.TTF")).deriveFont(40f);

                /** For score label */
                scoreF = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Bungee-Regular.ttf")).deriveFont(35f);

            // Create graphics environment to register and render our custom font 
                GraphicsEnvironment gg = GraphicsEnvironment.getLocalGraphicsEnvironment();
            
            // Try, try register font to gg
                /** Register Timer font */
                gg.registerFont(timerF);
                gg.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/LCDM2N__.TTF")));

                /** Register Score Label font */
                gg.registerFont(scoreF); 
                gg.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Bungee-Regular.ttf")));
        }     
        
        catch (IOException | FontFormatException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


    public static void main() {
        new GameFrame();
    }
}
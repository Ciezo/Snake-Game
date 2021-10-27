import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.io.File;
import java.awt.Font;
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
        // frame = new GameFrame(); 
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
        // g.drawImage(img, 0, 0, null); 
        
        if(running) {


            // for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
            //     g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
            //     g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            // }
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
        public void keyReleased(KeyEvent e) {
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

        GameFrame() {
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("Closing game");
                    System.exit(0);
                }
            });

            // Set up the frame 
            this.add(new SnakeProto());
            this.setSize(960, 720);
            this.setTitle("Now Playing! Snake Game Classic!");

            // Pack, center, and set visible as true for the frame 
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }
    }



    public static void main() {
        new GameFrame();
    }
}
// IMPORT SECTION 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SnakeGame implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
    
    // Values 
    public int width = 960; 
    public int height = 720;  

    // Create instance of a JFrame  
    JFrame main_frame; 
    
    // Create instances of JPanel 
    JPanel home_panel;                     // For the main menu or home page of the game 
    JPanel gameplay;                        // For the gameplay screen 

    // Create instance for JLabel 
    /** For Main Window */
    JLabel main_windowBG;
    JLabel titleCard;  
    JLabel play;
    JLabel about;
    JLabel exit; 

    public SnakeGame() {

        // Call the dedicated function initializer to set and prepare our main menu frame 
        init_mainW();   

        // Call the window icon setter 
        setWindowIcon(); 

        // Call the listeners 
        fetchAllListeners();

        // Pack the frame and set to be visible on run 
        main_frame.pack(); 
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        main_frame.setVisible(true); 
    }


    public void init_mainW () { 
        main_frame = new JFrame(); 
        home_panel = new JPanel(); 
        
        main_frame.setTitle ("mmmmSnekkk"); 

        main_windowBG = new JLabel(new ImageIcon("assets/backgrounds/main_window-BG.png")); 
        titleCard = new JLabel(new ImageIcon("assets/backgrounds/title_card.png")); 
        play = new JLabel(new ImageIcon("assets/icons/play.png"));
        about = new JLabel(new ImageIcon("assets/icons/about.png"));
        exit = new JLabel(new ImageIcon("assets/icons/exit.png"));

        home_panel.add(main_windowBG); 
        home_panel.add(titleCard); 
        home_panel.add(play); 
        home_panel.add(about);
        home_panel.add(exit); 
        main_frame.add(home_panel); 
    }

    public void setWindowIcon () { 
        JLabel icon = new JLabel("Window icon"); 
        ImageIcon frameIcon = new ImageIcon("assets/items/apples/100x100/apple2.png");   

        icon.setIcon(frameIcon); 
        main_frame.setIconImage(frameIcon.getImage()); 
    }

    public void fetchAllListeners () { 
        // MouseListener
        // PLAY BUTTON
        play.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("CLICKED: Play button label");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("HOVERED OVER: Play button label");
                // play = new JLabel(new ImageIcon("assets/icons/onHover/play_onHover.png")); 
                ImageIcon playHoverIcon = new ImageIcon("assets/icons/onHover/play_onHover.png"); 
                play.setIcon(playHoverIcon); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("EXITED OVER: Play button label"); 
                ImageIcon playExitIcon = new ImageIcon("assets/icons/play.png"); 
                play.setIcon(playExitIcon); 
            }
        });

        // ABOUT BUTTON
        about.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("CLICKED: About button label");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("HOVERED OVER: About button label");
                ImageIcon aboutHoverIcon = new ImageIcon("assets/icons/onHover/about_onHover.png"); 
                about.setIcon(aboutHoverIcon); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("EXITED OVER: About button label"); 
                ImageIcon aboutExitIcon = new ImageIcon("assets/icons/about.png"); 
                about.setIcon(aboutExitIcon); 
            }
        });

        // EXIT BUTTON
        exit.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("CLICKED: Exit button label");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("HOVERED OVER: Exit button label");
                ImageIcon exitHoverIcon = new ImageIcon("assets/icons/onHover/exit_onHover.png"); 
                exit.setIcon(exitHoverIcon); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("EXITED OVER: Exit button label"); 
                ImageIcon exitHoverIcon = new ImageIcon("assets/icons/exit.png"); 
                exit.setIcon(exitHoverIcon); 

            }

        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}

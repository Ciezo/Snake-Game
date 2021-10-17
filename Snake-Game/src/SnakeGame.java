// IMPORT SECTION 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SnakeGame {
    
    // Values 
    public int width = 960; 
    public int height = 720;  

    // Listeners 
    EventMaster handler = new EventMaster(SnakeGame.this);  
 
    // Create instance of a JFrame  
    JFrame main_frame; 
    
    // Create instances of JPanel 
    JPanel home_panel;                     // For the main menu or home page of the game 
    JPanel gameplay;                        // For the gameplay screen 

    // Create instance for JLabel and JButtons 
    /** For Main Window */
        JLabel main_windowBG;
        JLabel titleCard;  
        JLabel play;
        JLabel about;
        JLabel exit; 

        JButton playB; 
        JButton aboutB;
        JButton exitB;
    
    /** For Gameplay Window */ 
        JLabel gameplayBG; 
        JPanel dashboard_container; 
        JLabel dashboard; 

 
    public SnakeGame() {

        // Call the dedicated function initializer to set and prepare our main menu frame 
        init_mainW();   

        // Call the window icon setter 
        setWindowIcon(); 

        // Call Action commanders 
        actionCommanders();

        // Call the listeners 
        fetchAllListeners();

        // Pack the frame and set to be visible on run 
        main_frame.pack(); 
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        main_frame.setVisible(true); 
    }


    public void init_mainW() { 
        main_frame = new JFrame(); 
        home_panel = new JPanel(); 
        
        main_frame.setTitle ("mmmmSnekkk"); 

        main_windowBG = new JLabel(new ImageIcon("assets/backgrounds/main_window-BG.png")); 
        titleCard = new JLabel(new ImageIcon("assets/backgrounds/title_card.png")); 
        
        // Acting buttons using custom images found in the icons dir 
        /** These instances are using JLabel */ 
            // play = new JLabel(new ImageIcon("assets/icons/play.png"));
            // about = new JLabel(new ImageIcon("assets/icons/about.png"));
            // exit = new JLabel(new ImageIcon("assets/icons/exit.png"));

        /** Meanwhile, these instances are going to use JButton which is much preferred. So, let's test it! */
            // Set the ImageIcon
            ImageIcon play_ico = new ImageIcon("assets/icons/play.png");
            ImageIcon about_ico = new ImageIcon("assets/icons/about.png");
            ImageIcon exit_ico = new ImageIcon("assets/icons/exit.png"); 
            
            // Set the buttons to embed the ImageIcon 
            playB = new JButton(play_ico); 
            aboutB = new JButton(about_ico); 
            exitB = new JButton(exit_ico); 

            // Modify the default look of JButton; set all of it be invisible, remove borders, and set opaque 
            playB.setOpaque(false);         // make invisible
            aboutB.setOpaque(false);        // make invisible
            exitB.setOpaque(false);         // make invisible 

            playB.setContentAreaFilled(false); 
            aboutB.setContentAreaFilled(false);
            exitB.setContentAreaFilled(false);

            playB.setBorderPainted(false); 
            aboutB.setBorderPainted(false);
            exitB.setBorderPainted(false);

            playB.setFocusPainted(false); 
            aboutB.setFocusPainted(false);
            exitB.setFocusPainted(false);


        // Add the background image found in "assets/backgrounds/main_window-BG.png" to the home_panel 
        home_panel.add(main_windowBG); 
        // Add the title card image found in "assets/backgrounds/title_card.png" to the home_panel 
        home_panel.add(titleCard); 

        // Add the Play, About, Exit buttons to the home_panel  
        home_panel.add(playB);
        home_panel.add(aboutB);
        home_panel.add(exitB); 

        // Adding the acting JLabels as "buttons" to the home_panel 
        // home_panel.add(play); 
        // home_panel.add(about);
        // home_panel.add(exit); 

        // Finally, add the modified components to our frame 
        main_frame.add(home_panel); 
    }

    public void setWindowIcon() { 
        JLabel icon = new JLabel("Window icon"); 
        ImageIcon frameIcon = new ImageIcon("assets/items/apples/100x100/apple2.png");   

        icon.setIcon(frameIcon); 
        main_frame.setIconImage(frameIcon.getImage()); 
    }

    public void init_and_prepGameplayW() {
        // Remove the old components from the main_frame 
        main_frame.remove(home_panel);
        
        // Set a new size for the new instance to hold the gameplay panel 
        main_frame.setSize(width, height);
        
        
        // Repaint the main_frame 
        main_frame.repaint();
    }
    
    public void setGamePlay() {
        // Create a new instance for the gameplay JPanel
        gameplay = new JPanel(); 
        
        // Create a new instance for the dashboard container panel
        dashboard_container = new JPanel(); 
        
        // Fetch the custom image to act as background for the gameplay panel  
        gameplayBG = new JLabel(new ImageIcon("assets/backgrounds/grass_biome-BG_lv1.png")); 
        
        // Fetch and set image to the dashboard to be which added to the dashboard_container panel 
        dashboard = new JLabel(new ImageIcon("assets/dashboard/dashboard.png")); 

        // Add dashboard to dashboard_container panel 
        dashboard_container.add(dashboard); 

        // Add components to the gameplay panel  
        gameplay.add(gameplayBG);                   // add the custom and modified background graphic
        gameplay.add(dashboard_container);          // now, add the customized dashboard and everything about it 

        // Add the gameplay panel to the main frame 
        main_frame.add(gameplay); 
        
        
        // Repaint the main frame 
        main_frame.repaint();
    }

    public void handleAbout() {
        // Message 
        String message = "Eat some apples to which will be randomly generated across the screen! Avoid getting hit on the walls and do not bite yourself ";

        // Beep 
        Toolkit.getDefaultToolkit().beep();

        // Pop-up some message dialog for information 
        JOptionPane.showMessageDialog(null, message, "About and Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    public void actionCommanders() {
        // Set action command to these buttons 
        playB.setActionCommand("Play");
        aboutB.setActionCommand("About");
        exitB.setActionCommand("Exit"); 

    }

    public void fetchAllListeners () { 
        // Add mouse listener to these buttons 
        playB.addMouseListener(handler); 
        aboutB.addMouseListener(handler);
        exitB.addMouseListener(handler);

        // Add action listener to these buttons 
        playB.addActionListener(handler); 
        aboutB.addActionListener(handler); 
        exitB.addActionListener(handler); 
    }

}

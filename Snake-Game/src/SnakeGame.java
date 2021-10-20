// IMPORT SECTION 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class SnakeGame {
    
    // Values 
    public int width = 960; 
    public int height = 720;
    
    // Image rendering, try 
    Image img; 

    // Listeners 
    EventMaster handler = new EventMaster(SnakeGame.this);  
 
    // Create instance of a JFrame  
    JFrame main_frame; 
    
    // Create instances of JPanel 
        JPanel home_panel;                     // For the main menu or home page of the game 
        JPanel gameplay;                       // For the gameplay screen 

        JPanel playbuttonpanel;                // For play button which acts as a container 
        JPanel aboutbuttonpanel;               // For about button which is a container  
        JPanel exitbuttonpanel;                // For exit button which is a container as well 

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

        /** Pop-up window for choosing levels */
            // A JFrame
                JFrame levelChooser_container; 
            // Levels picker 
           JButton grass_lvlPick = new JButton("Grass Biome"); 
           JButton desert_lvlPick = new JButton("Desert Biome");
           JButton winter_lvlPick = new JButton("Winter Biome");
    
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
        // Image fetch, rendering 
        img = Toolkit.getDefaultToolkit().createImage("assets/backgrounds/main_window-BG.png");

        // Try, position rendered image at back
        // draw(); 

        main_frame = new JFrame(); 
        home_panel = new JPanel(); 
        
        playbuttonpanel = new JPanel(); 
        aboutbuttonpanel = new JPanel();
        exitbuttonpanel = new JPanel();  

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
        // home_panel.add(playB);
        // home_panel.add(aboutB);
        // home_panel.add(exitB); 

        // Add the buttons to each on its own panel container 
        playbuttonpanel.add(playB); 
        aboutbuttonpanel.add(aboutB); 
        exitbuttonpanel.add(exitB); 

        // Add the Panel containers for buttons to the home_panel 
        home_panel.add(playbuttonpanel);
        home_panel.add(aboutbuttonpanel);
        home_panel.add(exitbuttonpanel);


        // Adding the acting JLabels as "buttons" to the home_panel 
        // home_panel.add(play); 
        // home_panel.add(about);
        // home_panel.add(exit); 

        // Set bounding coordinates 
        playbuttonpanel.setBounds(0, 0, 200, 200);
        aboutbuttonpanel.setBounds(50, 50, 200, 200);
        exitbuttonpanel.setBounds(100, 100, 200, 200);
        

        // Finally, add the modified components to our frame 
        main_frame.add(home_panel); 
    }

    public void draw(Graphics g) {
        g.drawImage(img, 0, 0, null);  
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
    
    public void prepLevelPicker() {
        // Create a new instance for the acting container of buttons as level picker
        levelChooser_container = new JFrame("Please choose a level"); 

        // Set button sizes 
        grass_lvlPick.setPreferredSize(new Dimension(200, 100));
        desert_lvlPick.setPreferredSize(new Dimension(200, 100));
        winter_lvlPick.setPreferredSize(new Dimension(200, 100)); 

        // Add initialized components in the frame acting as container 
        levelChooser_container.add(grass_lvlPick, BorderLayout.LINE_START); 
        levelChooser_container.add(desert_lvlPick, BorderLayout.CENTER);
        levelChooser_container.add(winter_lvlPick, BorderLayout.LINE_END);

        // Pack the pop-up window and set visible as true  
        levelChooser_container.pack();
        levelChooser_container.setVisible(true);

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
        // String message = "Eat some apples to which will be randomly generated across the screen! Avoid getting hit on the walls and do not bite yourself ";

        // Beep 
        Toolkit.getDefaultToolkit().beep();

        // Pop-up some message dialog for information 
        /** A NEW IMPLEMENTATION WILL BE UPDATED AND APPLIED FOR THIS FUNCTION */
        // JOptionPane.showMessageDialog(null, message, "About and Instructions", JOptionPane.INFORMATION_MESSAGE);

        // Call and prepare the dedication function to set up the About window 
        init_and_prepAboutW();
    }

    public void init_and_prepAboutW() {
        // Create a new instance of a frame 
        JFrame frame_about = new JFrame("About game and official instructions"); 

        // Create panels
        JPanel panel = new JPanel();

        // Log to console 
        System.out.println("Preparing window About");

        // Label for instructions 
        JLabel msg = new JLabel();
        JLabel prompt = new JLabel(); 

        // Try link to github 
        JLabel link = new JLabel(new ImageIcon("assets/icons/github.png")); 

        // Try wrapping
            /** About content panel */ 
            msg.setText("<html><body><h1>INSTRUCTIONS</h1><p>Eat some apples to which will be randomly generated across the screen! <br> Avoid getting hit on the walls and do not bite yourself <br> </p><h2>Developers</h2> </body></html>");

            prompt.setText("<html><body><h3>Artwork and Graphics By:</h3><p>Cloyd Secuya <br> </p></body></html>");



        // Create a string array of developers 
        String[] devs= { " Cloyd Secuya", 
                         " Darrel Bilbao",                
                         " Matthew Martinez", 
                         " Howell Rias"
                       };

        // Create a list for the developers 
        JList list = new JList<>(devs);
        list.setPreferredSize(new Dimension(400, 75));

        // Link to github feature 
        link.setPreferredSize(new Dimension(100, 100));
        link.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    final URI link = new URI("https://github.com/Ciezo/Snake-Game");
                    Desktop.getDesktop().browse(link); 
                } 
                
                catch (URISyntaxException | IOException e1) {
                    e1.printStackTrace();
                }  
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("HOVERED OVER: Github link"); 

                // Change icon when hover
                ImageIcon gitIcon_hover = new ImageIcon("assets/icons/onHover/github_onHover.png");
                link.setIcon(gitIcon_hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("EXITED OVER: Github link"); 

                // Default back to icon when cursor exit over
                ImageIcon gitIcon_exit = new ImageIcon("assets/icons/github.png");
                link.setIcon(gitIcon_exit);
            }

        });

        // Add these initialized components in the panel 
        panel.add(msg, BorderLayout.LINE_START); 
        panel.add(list, BorderLayout.PAGE_END);   
        panel.add(prompt, BorderLayout.SOUTH); 
         
        

        // Fetch the instance of frame object and set all given initialized components to it 
        frame_about.getContentPane();
        frame_about.setPreferredSize(new Dimension(500, 500)); 
        frame_about.add(panel, BorderLayout.CENTER);  
        frame_about.add(link, BorderLayout.SOUTH); 
        
        // Center the frame pop-up 
        frame_about.setLocationRelativeTo(null); 

        // Pack the frame and set visible as true 
        frame_about.pack();
        frame_about.setVisible(true);
    }

    public void hanleExit() {
        // Beep 
        Toolkit.getDefaultToolkit().beep();
        
        // Pop-up dialog
        int arg = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the application?", 
        "Exit?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Pass the decision as int
        decision_ext(arg);
        
    }

    public void decision_ext(int arg) {
        
        // Print the passed parameter from JOption
        //** TEST CASE */
        System.out.println(arg);
        
        switch (arg) {
            
            case 0:
                System.out.println("Closing application!");
                System.exit(0);

                break; 


            case 1:
                System.out.println("Returning to application");
                break; 
            

            default:
                System.out.println("Decision entered");
                break; 
        }
    }

    public void actionCommanders() {
        // Set action command to these buttons 
        playB.setActionCommand("Play");
        aboutB.setActionCommand("About");
        exitB.setActionCommand("Exit"); 
        
        // Set action command to level pickers 
        grass_lvlPick.setActionCommand("grassPick");
        desert_lvlPick.setActionCommand("desertPick");
        winter_lvlPick.setActionCommand("winterPick");
    }

    public void fetchAllListeners () { 

        // Add mouse motion listener to the frame 
        main_frame.addMouseMotionListener(handler); 

        // Add mouse listener to these buttons on the Play, About, Exit 
        playB.addMouseListener(handler); 
        aboutB.addMouseListener(handler);
        exitB.addMouseListener(handler);

        // Add action listener to these buttons on the Play, About, Exit
        playB.addActionListener(handler); 
        aboutB.addActionListener(handler); 
        exitB.addActionListener(handler);

        // Add action listener to the level picker as buttons 
        grass_lvlPick.addActionListener(handler);
        desert_lvlPick.addActionListener(handler);
        winter_lvlPick.addActionListener(handler);

        // Add mouse listener to the level picking buttons 
        grass_lvlPick.addMouseListener(handler);
        desert_lvlPick.addMouseListener(handler);
        winter_lvlPick.addMouseListener(handler); 
    }

}

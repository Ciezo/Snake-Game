// IMPORT SECTION 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.io.File;
import java.awt.Font;
import java.net.URI;
import javax.swing.event.MouseInputAdapter;
import java.awt.GraphicsEnvironment;

public class SnakeGame {
    
    // Classic Snake Instance
        // 
        

    // Values 
        public int width = 960; 
        public int height = 720;

    // Sets and values
        String getCommander;     

    // Fonts   
        Font timerF; 
        Font scoreF;   

    // Try, play audio on background
        File audio;
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        Clip clip;
    
    // Image rendering, try 
        Image img; 

    // Listeners 
        EventMaster handler = new EventMaster(SnakeGame.this);  
 
    // Create instance of a JFrame  
        JFrame main_frame; 
        JFrame game_frame;
    


    // Create instances of JPanel 
        /** Main home window */
            JPanel home_panel;                     // For the main menu or home page of the game 
            JPanel playbuttonpanel;                // For play button which acts as a container 
            JPanel aboutbuttonpanel;               // For about button which is a container  
            JPanel exitbuttonpanel;                // For exit button which is a container as well 
            JPanel titlecardpanel;                 // For title card which acts as a panel container too 

        
        /** Gameplay window */
            JPanel gameplay;                       // For the gameplay screen 
            JPanel dashboard_container;            // Dashboard panel act as container 
            JPanel navbar;                         // For navbar on Dashboard
            JPanel resPanel; 
            JPanel setPanel; 
            JPanel menuPanel; 
            JPanel quiPanel; 
            static JPanel heartPanel;
            JPanel timerPanel;
            JPanel scorePanel; 




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
                    JButton launch_classicSnake = new JButton("Launch Classic Snake by BroCode");
    


        /** For Gameplay Window */ 
            JLabel gameplayBG;  
            JLabel dashboard; 
            static JLabel heart1;
            static JLabel heart2;
            static JLabel heart3;
            JLabel timer; 
            static JLabel scoreLabel; 

            JButton restart;
            JButton settings; 
            JButton menu;
            JButton quit; 
        
    
    public SnakeGame() {
        // Fonts, try setting up 
            runFonts(); 

        // Call the dedicated function initializer to set and prepare our main menu home page on the main_frame 
            init_mainW();   
            
        // Call the window icon setter 
            setWindowIcon(main_frame); 

        // Try, and call the function to play audio 
            playBGonStart();     
            
        // Call Action commanders 
            actionCommanders();

        // Call the listeners 
            fetchAllListeners();

        // Pack and center the frame and set to be visible on run 
            main_frame.pack(); 
            main_frame.setLocationRelativeTo(null);
            main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
            main_frame.setVisible(true); 
    }



    public void init_mainW() { 
        // Initializing fresh instances of our main_frame and the dedicated home_panel 
            main_frame = new JFrame(); 

            home_panel = new JPanel(); 
            home_panel.setLayout(null);
        
        // Set and initialize the panels acting as container 
            playbuttonpanel = new JPanel(); 
            aboutbuttonpanel = new JPanel();
            exitbuttonpanel = new JPanel();  
            titlecardpanel = new JPanel();
        
        // Add customizations to the main_frame 
            main_frame.setTitle ("mmmmSnekkk"); 
        
            // Create instances for the background and title card with their respective textures from 'assets' 
                main_windowBG = new JLabel(new ImageIcon("assets/backgrounds/main_window-BG.png")); 
                titleCard = new JLabel(new ImageIcon("assets/backgrounds/title_card.png")); 

            // Do not allow the main_frame to be resizeable 
                main_frame.setResizable(false);
            

                // Try, position rendered image at back
                    /** @NOTE: THIS WORKS! BUT WE HAVE TO BE 
                     *        AWARE OF THE FACT THAT WE NEED TO PLACE
                     *        THE TEXTURE ASSETS PROPERLY 
                     * 
                     *        @NOTE_NOTICE: IF WE TRULY IMPLEMENT 
                     *                      THIS, COMMENT OUT THE
                     *                      ADDING OF JLABEL COMPONENTS 
                     *                      ONTO THE FRAME WHICH 
                     *                      CAN BE SEEN BELOW
                     */
        
                        main_frame.setContentPane(main_windowBG);  


                    /** @NOTE: 
                     *      AFTER SETTING THE CONTENTPANE WITH A CUSTOM 
                     *      BACKGROUND, CREATE FRESH INSTANCES OF THE 
                     *      PANEL ACTING AS CONTAINERS THEN APPLY 
                     *      SETBOUNDS WITH PROPER COORDINATES POSITIONS
                     */
            
        
        


        // Acting buttons using custom images found in the icons dir 
            /** These instances are using JLabel */ 
                // play = new JLabel(new ImageIcon("assets/icons/play.png"));
                // about = new JLabel(new ImageIcon("assets/icons/about.png"));
                // exit = new JLabel(new ImageIcon("assets/icons/exit.png"));

        /** Meanwhile, these instances are going to use JButton which is much preferred. So, let's test it! */
            // Set the ImageIcon
                ImageIcon play_ico = new ImageIcon("assets/icons/play2.png");
                ImageIcon about_ico = new ImageIcon("assets/icons/about2.png");
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
            // home_panel.add(main_windowBG); 
        
        // Add the title card image found in "assets/backgrounds/title_card.png" to the home_panel 
            // home_panel.add(titleCard); 

        // Add the Play, About, Exit buttons to the home_panel  
            // home_panel.add(playB);
            // home_panel.add(aboutB);
            // home_panel.add(exitB); 
            
        // Adding the acting JLabels as "buttons" to the home_panel 
            // home_panel.add(play); 
            // home_panel.add(about);
            // home_panel.add(exit); 

        // Add the title card to its own panel container 
            titlecardpanel.add(titleCard);     

        // Add the buttons to each on its own panel container 
            playbuttonpanel.add(playB); 
            aboutbuttonpanel.add(aboutB); 
            exitbuttonpanel.add(exitB); 
        
        // Set location and allow transparency
            playbuttonpanel.setBounds(25, 485, 170, 200);
            playbuttonpanel.setOpaque(false);
            aboutbuttonpanel.setBounds(650, 390, 200, 200);
            aboutbuttonpanel.setOpaque(false);
            exitbuttonpanel.setBounds(750, 500, 170, 150);
            exitbuttonpanel.setOpaque(false);
            titlecardpanel.setBounds(20, 200, 600, 300);
            titlecardpanel.setOpaque(false);
        
        // Add the Panel containers for buttons to the home_panel 
            home_panel.add(playbuttonpanel);
            home_panel.add(aboutbuttonpanel);
            home_panel.add(exitbuttonpanel);
            home_panel.add(titlecardpanel); 


        
        // Finally, add the modified components to our frame 
            main_frame.add(home_panel); 
            main_frame.add(playbuttonpanel); 
            main_frame.add(aboutbuttonpanel);
            main_frame.add(exitbuttonpanel); 
            main_frame.add(titlecardpanel);
    }



    public void runFonts() {
        
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




    public void setWindowIcon(JFrame thisFrame) { 
        JLabel icon = new JLabel("Window icon"); 
        ImageIcon frameIcon = new ImageIcon("assets/items/apples/100x100/apple2.png");   

        icon.setIcon(frameIcon); 
        thisFrame.setIconImage(frameIcon.getImage()); 
    }



    public void playBGonStart() {
        try {
            // File yourFile;
            // AudioInputStream stream;
            // AudioFormat format;
            // DataLine.Info info;
            // Clip clip;

            audio = new File("sfxpack/wav/bg-forest.wav");
            stream = AudioSystem.getAudioInputStream(audio);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();

                // Try to stop the home window bg music when in gameplay
                    /** TODO: TRY TO APPLY THREADWORKER HERE */
                    EventMaster em = new EventMaster(this);  
                    getCommander = em.deliverCmnd(); 

                    if (getCommander == "grassPick") {
                        // Log the level option to console 
                        System.out.println(getCommander);
                        stream.close();
                        clip.stop();
                        clip.close();
                    }

            System.out.println("Trying to play music audio file");
        }

        catch (Exception e) {
            // Errors on filestreaming will only occur IF FILE NOT EXITS
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }



    // The dedicated function handler to play sfx files found from 'sfxpack'
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



    public void prepLevelPicker() {
        // Create a new instance for the acting container of buttons as level picker
            levelChooser_container = new JFrame("Please choose a level"); 
        
        // Set button sizes 
            grass_lvlPick.setPreferredSize(new Dimension(200, 100));
            desert_lvlPick.setPreferredSize(new Dimension(200, 100));
            winter_lvlPick.setPreferredSize(new Dimension(200, 100)); 
            launch_classicSnake.setPreferredSize(new Dimension(200, 50)); 
            
        // Add initialized components in the frame acting as container 
            levelChooser_container.add(grass_lvlPick, BorderLayout.LINE_START); 
            levelChooser_container.add(desert_lvlPick, BorderLayout.CENTER);
            levelChooser_container.add(winter_lvlPick, BorderLayout.LINE_END);
            levelChooser_container.add(launch_classicSnake, BorderLayout.PAGE_END);
            
        
        // Avoid pop-up window resizing
            levelChooser_container.setResizable(false);

        // Pack and center the the pop-up window and set visible as true  
            levelChooser_container.pack();
            levelChooser_container.setLocationRelativeTo(null);
            levelChooser_container.setVisible(true);
    }
    


    public void init_and_prepGameplayW() {
        // Try and stop the music
            clip.stop();
            clip.close();

        // Create a fresh instance for gameplay frame 
            game_frame = new JFrame(); 

        // Remove the old components from the main_frame 
            main_frame.getContentPane().removeAll();
            main_frame.remove(main_windowBG);
            main_frame.remove(home_panel);
            main_frame.remove(playbuttonpanel);
            main_frame.remove(aboutbuttonpanel); 
            main_frame.remove(exitbuttonpanel); 
            main_frame.remove(titlecardpanel);
        
        // Set a new size for the new instance to hold the gameplay panel 
            main_frame.setSize(width, height);
        
        
        // Repaint the main_frame 
            main_frame.repaint();

        // Dispose main_frame to create game_frame
            /** TEST OKAY! */
            main_frame.dispose();             
    }   
    


    public void paintComponent(Graphics g) {
        game_frame.paintComponents(g);
        draw(g);
    }



    public void draw(Graphics g) {
        // Render the img object
            // Try render and paint image, rather than setting content pane 
                img = Toolkit.getDefaultToolkit().createImage("assets/backgrounds/grass_biome-BG_lv1.png");
                g.drawImage(img, 0, 0, null); 
    }


    
    public void setGamePlay() {
        // Initialize the game_frame 
            game_frame = new JFrame(); 
        

        // Set window icon 
            setWindowIcon(game_frame);
            

        // Set and play gameplay music background
            playSound("sfxpack/wav/snakejazz.wav");


        // Initialize the game frame 
            game_frame.setTitle("Now Playing! Snake Game - Grass Biome");
            game_frame.setSize(width, height);
            game_frame.setLocationRelativeTo(null); 
            game_frame.setResizable(false); 
            game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            game_frame.setContentPane(new JLabel(new ImageIcon ("assets/backgrounds/grass_biome-BG_lv1.png")));

        // Add mouse motion listener
            game_frame.addMouseMotionListener(handler);

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

            /** IMMEDIATELY APPLY THE ACTION COMMANDERS FOR THE NAVBAR ICONS UPON INSTANTIATION */
                // navbar_actionCommanders();

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
                dashboard_container = new JPanel(); 
                dashboard_container.setOpaque(false); 
                dashboard_container.setBounds(25, 1, 910, 250); 

            /** Panel for navbar */
            // This is a the MASTER CONTAINER for the navbar buttons
                navbar = new JPanel(); 
                navbar.setLayout(null);
                navbar.setOpaque(false); 
                navbar.setBounds(705, 13, 500, 500);

            /** Panel for restart icon */
                resPanel = new JPanel(); 
                resPanel.setOpaque(false); 
                resPanel.setBounds(0, 0, 50, 50);

            /** Panel for settings icon */
                setPanel = new JPanel(); 
                setPanel.setOpaque(false); 
                setPanel.setBounds(50, 0, 50, 50);

            /** Panel for menu icon */
                menuPanel = new JPanel(); 
                menuPanel.setOpaque(false); 
                menuPanel.setBounds(100, 0, 50, 50);
        
            /** Panel for quit icon */
                quiPanel = new JPanel(); 
                quiPanel.setOpaque(false); 
                quiPanel.setBounds(150, 0, 50, 50);

            /** Panel for hearts acting as container  */
                heartPanel = new JPanel(); 
                heartPanel.setLayout(null);
                heartPanel.setOpaque(false);
                heartPanel.setBounds(495, 12, 200, 200);

            /** Panel for timer for timer label */
                timerPanel = new JPanel();
                timerPanel.setLayout(null);
                timerPanel.setOpaque(false);
                timerPanel.setBounds(80, 0, 100, 100);

            /** Panel for the score label */
                scorePanel = new JPanel(); 
                scorePanel.setLayout(null);
                scorePanel.setOpaque(false);
                scorePanel.setBounds(416, 13, 95, 95);

            /** Set coords for the hearts inside the panel */  
                heart1.setBounds(0, 0, 70, 70);
                heart2.setBounds(55, 0, 70, 70);
                heart3.setBounds(105, 0, 70, 70);

            /** Set coords for timer label inside the timerPanel */
                // Set it to the most upperleft of the timerPanel too 
                    timer.setBounds(0, 0, 100, 100);

            /** Now, set coords for the score label inside the score panel */
                // Set it to the most upperleft of the score panel 
                    scoreLabel.setBounds(0, 0, 100, 100);


        // Add components to panel containers 
            dashboard_container.add(dashboard);     
            resPanel.add(restart); 
            setPanel.add(settings); 
            menuPanel.add(menu); 
            quiPanel.add(quit);
            heartPanel.add(heart1);
            heartPanel.add(heart2);
            heartPanel.add(heart3);
            timerPanel.add(timer); 
            scorePanel.add(scoreLabel);
        
        // Then, try to add the containers to the main navbar container panel
            navbar.add(resPanel);
            navbar.add(setPanel);
            navbar.add(menuPanel);
            navbar.add(quiPanel);

        // Then, try to add the navbar to the dashboard panel 
            dashboard_container.add(navbar); 

        // Try to add heart panel to dashboard panel 
            dashboard_container.add(heartPanel);

        // Try to add timer panel to dashboard panel 
            dashboard_container.add(timerPanel);

        // This time and finally,...try to add score panel onto the dashboard panel 
            dashboard.add(scorePanel);



        // Create instance of Classic Snake
            // TODO: RUN INSTANCE OF SNAKE HERE
            /** NOTE: Try to new on it runUpdates__AND__Game() */
                runUpdates__AND__Game();
                

                // Finally, add the panels to frame instance
                game_frame.add(navbar); 
                game_frame.add(heartPanel);
                game_frame.add(timerPanel);
                game_frame.add(scorePanel);
                game_frame.add(dashboard_container);
            
        
            
        // Pack the frame and set visible as true
            game_frame.pack();
            game_frame.setVisible(true);

    }



    // Try, update the informative contents on dashboard such as score and heart count
        /** NOTE: 
         *  This should be called after init_gameplay and setGameplay 
         *  Moreover, the screen, game_frame, must contain the snake movement and such */ 
    public void runUpdates__AND__Game() { 
        Snake snake = new Snake(); 
        
        snake.setLayout(null);
        snake.setOpaque(false);
        snake.setBounds(0, 0, snake.getWidth(), snake.getHeight());
        // snake.setLocation(0, 120);
        

        // Then, add this instance of Snake to the game_frame
        game_frame.add(snake); 
    }



    public void handleAbout() {
        // Message 
            // String message = "Eat some apples to which will be randomly generated across the screen! Avoid getting hit on the walls and do not bite yourself ";

        // Beep 
        Toolkit.getDefaultToolkit().beep();
        
        // Call and prepare the dedication function to set up the About window 
            init_and_prepAboutW();

        // Pop-up some message dialog for information 
            /** A NEW IMPLEMENTATION WILL BE UPDATED AND APPLIED FOR THIS FUNCTION */
                // JOptionPane.showMessageDialog(null, message, "About and Instructions", JOptionPane.INFORMATION_MESSAGE);
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
        
        // Try and link to BroCode YouTube channel 
            JLabel brocode = new JLabel(new ImageIcon("assets/icons/brocode.png"));

        // Link to referral link to which is a reference to the concept of our Snake game code 
            JLabel referLink = new JLabel(new ImageIcon("assets/icons/referLink.png"));


        // Try wrapping
            /** About content panel */ 
            msg.setText("<html><body><h1>INSTRUCTIONS</h1><p>Eat some apples to which will be randomly generated across the screen! <br> Avoid getting hit on the walls and do not bite yourself <br> </p><h2>Developers</h2> </body></html>");

            prompt.setText("<html><body><h3>Artwork and Graphics By:</h3><p>Cloyd Secuya <br> </p> <br> </body></html>");
            
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
            link.addMouseListener(new MouseInputAdapter() {
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

        // BroCode link
            brocode.setPreferredSize(new Dimension(70, 50));
            brocode.addMouseListener(new MouseInputAdapter() {
                @Override 
                public void mouseEntered(MouseEvent e) {
                    ImageIcon brocode_onHover = new ImageIcon("assets/icons/onHover/brocode_onHover.png");
                    brocode.setIcon(brocode_onHover);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    ImageIcon brocode_defaultIcon = new ImageIcon("assets/icons/brocode.png");
                    brocode.setIcon(brocode_defaultIcon);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    try {
                        final URI link = new URI("https://www.youtube.com/c/BroCodez");
                        Desktop.getDesktop().browse(link); 
                    } 
                    
                    catch (URISyntaxException | IOException e1) {
                        e1.printStackTrace();
                    }  
                }
            });

            // Referral Link 
                referLink.setPreferredSize(new Dimension(50, 50));
                referLink.addMouseListener(new MouseInputAdapter() {
                    @Override 
                    public void mouseEntered(MouseEvent e) {
                        ImageIcon referLink_onHover = new ImageIcon("assets/icons/onHover/referLink_onHover.png");
                        referLink.setIcon(referLink_onHover);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        ImageIcon referLink_default = new ImageIcon("assets/icons/referLink.png");
                        referLink.setIcon(referLink_default);
                    }

                    @Override 
                    public void mouseReleased(MouseEvent e) {
                        try {
                            final URI link = new URI("https://zetcode.com/javagames/snake/");
                            Desktop.getDesktop().browse(link); 
                        } 
                        
                        catch (URISyntaxException | IOException e1) {
                            e1.printStackTrace();
                        }  
                    }
                });

        // Add these initialized components in the panel 
            panel.add(msg, BorderLayout.LINE_START); 
            panel.add(list, BorderLayout.PAGE_END);  
            panel.add(prompt, BorderLayout.SOUTH); 
            panel.add(brocode, BorderLayout.EAST);
            panel.add(referLink, BorderLayout.EAST);


        // Fetch the instance of frame object and set all given initialized components to it 
            frame_about.getContentPane();
            frame_about.setPreferredSize(new Dimension(500, 500)); 
            frame_about.add(panel, BorderLayout.CENTER);  
            frame_about.add(link, BorderLayout.SOUTH); 
        
        // Avoid resizing
            frame_about.setResizable(false);
        
        // Pack and center the frame and set visible as true 
            frame_about.pack();
            frame_about.setLocationRelativeTo(null); 
            frame_about.setVisible(true);
    }



    public void hanleExit() {
        
        playSound("sfxpack/wav/click.wav");

        // Pop-up dialog
        int arg = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the application?", 
        "Exit?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        // Beep 
            Toolkit.getDefaultToolkit().beep();
        
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
            launch_classicSnake.setActionCommand("classicSnake");
            
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
        launch_classicSnake.addActionListener(handler);
        
        // Add mouse listener to the level picking buttons 
        grass_lvlPick.addMouseListener(handler);
        desert_lvlPick.addMouseListener(handler);
        winter_lvlPick.addMouseListener(handler); 
        launch_classicSnake.addMouseListener(handler);        
    }
    
    
    
    public void navbar_actionCommanders() {
        // Set action command to the navbar icons
        restart.setActionCommand("restart");
        settings.setActionCommand("settings");
        menu.setActionCommand("menu");
        quit.setActionCommand("quit");
    }



    public void fetchMouseListener_forNavbar() {
        // Add mouse listener to the navbar icons 
            restart.addMouseListener(handler);
            settings.addMouseListener(handler);
            menu.addMouseListener(handler);
            quit.addMouseListener(handler);
        
        // Add mouse motion listener to the navbar icons 
            restart.addMouseMotionListener(handler);
            settings.addMouseMotionListener(handler);
            menu.addMouseMotionListener(handler);
            quit.addMouseMotionListener(handler);
    
        // Add action listener to the navbar icons 
            restart.addActionListener(handler);
            settings.addActionListener(handler);
            menu.addActionListener(handler);
            quit.addActionListener(handler);
    }



    public static JLabel getScoreOnDashBoardLabel() {
        return scoreLabel; 
    }



    public static void setScoreOnDashBoardLabel(String scoreJLabel) {
        scoreLabel.setText(scoreJLabel); 
    }
}
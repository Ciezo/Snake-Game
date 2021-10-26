// IMPORT SECTION
import javax.swing.*;

import java.awt.event.*;




public class EventMaster extends JFrame implements ActionListener, MouseListener, MouseMotionListener, KeyListener{

    SnakeGame gui; 
    SnakeProto classic; 
    
    public String commander; 

    public EventMaster(SnakeGame gui) {
        this.gui = gui;

        // Just new lines for proper spacing and line break in console logging 
        System.out.println("\n" + "\n" + "\n" + "\n" + "\n" + "\n" +"\n" + "\n" + "\n" + "\n");  
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
           
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Coordinates: " + "X: " + e.getX() + "    " + "Y: " + e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
        // PLAY Button 
        if (e.getSource() == gui.playB) {
            System.out.println("CLICKED: Play button");

            System.out.println("Preparing and initializing Gameplay");
            // gui.init_and_prepGameplayW();
            // gui.prepLevelPicker(); 
            // gui.setGamePlay();

           
            // Attempt to play sound effect 
                    // Call the dedication function handler
                        System.out.println("Attempting to play click sound");
                        gui.playSound("sfxpack/wav/click.wav");
        }

        // Level Picking
            /** TRY TO DIPOSE THE LEVEL PICKER CONTAINER WHICH IS A JFRAME */    
            if (e.getSource() == gui.grass_lvlPick) {
                gui.levelChooser_container.dispose(); 
            }

            else if (e.getSource() == gui.launch_classicSnake) {
                gui.levelChooser_container.dispose();
                gui.main_frame.dispose();
            }

        // ABOUT Button 
            if (e.getSource() == gui.aboutB) {
                System.out.println("CLICKED: About button");


                // Attempt to play sound effect 
                    // Call the dedication function handler
                    System.out.println("Attempting to play click sound");
                    gui.playSound("sfxpack/wav/click.wav");
            }    

        // EXIT Button 
        if (e.getSource() == gui.exitB) {
            System.out.println("CLICKED: Exit button");


            // Attempt to play sound effect 
                // Call the dedication function handler
                System.out.println("Attempting to play click sound");
                gui.playSound("sfxpack/wav/click.wav");
        }    
    } 

    @Override
    public void mouseEntered(MouseEvent e) {
       
        /** HOME PANEL */
        // In these implementations, we try to change and apply response to the buttons: Play, About, Exit 
        // That is to say, these buttons should change texture or icon when mouse cursor hovered over 
            // PLAY button 
            if (e.getSource() == gui.playB) { 
                System.out.println("HOVERED OVER: Play button label");

                ImageIcon playHoverIcon = new ImageIcon("assets/icons/onHover/play2_onHover.png");  
                gui.playB.setIcon(playHoverIcon);   

                // Attempt to play sound effect 
                    // Call the dedication function handler
                    System.out.println("Attempting to play hover sound");
                    gui.playSound("sfxpack/wav/hover.wav");
            }
            
            // ABOUT Button 
            else if (e.getSource() == gui.aboutB) {
                System.out.println("HOVERED OVER: About button label"); 

                ImageIcon aboutHoverIcon = new ImageIcon("assets/icons/onHover/about2_onHover.png");  
                gui.aboutB.setIcon(aboutHoverIcon);  

                // Attempt to play sound effect 
                    // Call the dedication function handler
                    System.out.println("Attempting to play hover sound");
                    gui.playSound("sfxpack/wav/hover.wav");
            }

            // EXIT Button 
            else if (e.getSource() == gui.exitB) {
                System.out.println("HOVERED OVER: Exit button label");

                ImageIcon exitHoverIcon = new ImageIcon("assets/icons/onHover/exit_onHover.png");  
                gui.exitB.setIcon(exitHoverIcon); 

                // Attempt to play sound effect 
                    // Call the dedication function handler
                    System.out.println("Attempting to play hover sound");
                    gui.playSound("sfxpack/wav/hover.wav");
            }
    }

    @Override
    public void mouseExited(MouseEvent e) {
      
        /** HOME PANEL */
        // Meanwhile, after the cursor exits over to the buttons components: Play, About, Exit. 
        // They should revert back to the original icon look and feel         
            // PLAY Button
            if (e.getSource() == gui.playB) { 
                System.out.println("EXITED OVER: Play button label");

                ImageIcon playHoverIcon_exit = new ImageIcon("assets/icons/play2.png");  
                gui.playB.setIcon(playHoverIcon_exit);  
            }
            
            // ABOUT Button 
            else if (e.getSource() == gui.aboutB) {
                System.out.println("EXITED OVER: About button label");

                ImageIcon aboutHoverIcon_exit = new ImageIcon("assets/icons/about2.png");  
                gui.aboutB.setIcon(aboutHoverIcon_exit);   
            }

            // EXIT Button 
            else if (e.getSource() == gui.exitB) {
                System.out.println("EXITED OVER: Exit button label");

                ImageIcon exitHoverIcon_exit = new ImageIcon("assets/icons/exit.png");   
                gui.exitB.setIcon(exitHoverIcon_exit);
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        commander = e.getActionCommand(); 
        switch (commander) {

            /** HOME PANEL */
                case "Play":
                    System.out.println("Action performed: Play button");
                    System.out.println("Picking a level");
                    gui.prepLevelPicker(); 
                    
                break;  
                
                /** TEST CASES FOR LEVEL PICKING */
                        case "grassPick":
                            System.out.println("Entering Grass Biome"); 
                            System.out.println("Setting Gameplay Frame");
                            System.out.println("Fetching images and assets files");
                            gui.init_and_prepGameplayW(); 
                            gui.setGamePlay(); 
                            deliverCmnd();
                            gui.game_frame.repaint();
                            
                        break; 

                        case "desertPick":
                            System.out.println("Desert IN DEVELOPMENT");
                        break; 

                        case "winterPick":
                            System.out.println("Winter IN DEVELOPMENT");
                        break; 

                        case "classicSnake":
                            System.out.println("Launching Classic Snake Game"); 
                            classic = new SnakeProto(); 
                            gui.clip.stop(); 
                            gui.playSound("sfxpack/wav/snakejazz.wav");
                            SnakeProto.main();
                        break; 

                case "About":
                    System.out.println("Attempting to show and pop About and Instructions message");
                    gui.handleAbout();

                    break; 
                case "Exit":
                    System.out.println("Attempting to close application!");
                    System.out.println("Waiting user for response!");
                    gui.hanleExit();

                break; 
        }
    }

    public String deliverCmnd() {
        // Log to console the given action command 
            System.out.println(commander);

        return commander; 
    }

}
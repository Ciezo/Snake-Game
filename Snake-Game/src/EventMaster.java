// IMPORT SECTION
import javax.swing.*;
import java.awt.event.*;


public class EventMaster extends JFrame implements ActionListener, MouseListener, MouseMotionListener, KeyListener{

    SnakeGame gui; 
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
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        e.getSource(); 
        e.getID(); 

        // PLAY Button 
        if (e.getSource() == gui.playB) {
            System.out.println("CLICKED: Play button");

            System.out.println("Preparing and initializing Gameplay");
            gui.init_and_prepGameplayW(); 
            gui.setGamePlay();
        }
        
    } 

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getSource(); 
        e.getID(); 
        System.out.println(e.getSource());
        System.out.println(e.getID()); 

        /** HOME PANEL */
        // In these implementations, we try to change and apply response to the buttons: Play, About, Exit 
        // That is to say, these buttons should change texture or icon when mouse cursor hovered over 
            // PLAY button 
            if (e.getSource() == gui.playB) { 
                System.out.println("HOVERED OVER: Play button label");

                ImageIcon playHoverIcon = new ImageIcon("assets/icons/onHover/play_onHover.png");  
                gui.playB.setIcon(playHoverIcon);   
            }
            
            // ABOUT Button 
            else if (e.getSource() == gui.aboutB) {
                System.out.println("HOVERED OVER: About button label"); 

                ImageIcon aboutHoverIcon = new ImageIcon("assets/icons/onHover/about_onHover.png");  
                gui.aboutB.setIcon(aboutHoverIcon);  
            }

            // EXIT Button 
            else if (e.getSource() == gui.exitB) {
                System.out.println("HOVERED OVER: Exit button label");

                ImageIcon exitHoverIcon = new ImageIcon("assets/icons/onHover/exit_onHover.png");  
                gui.exitB.setIcon(exitHoverIcon); 
            }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.getSource(); 
        e.getID(); 
        System.out.println(e.getSource());
        System.out.println(e.getID()); 

        /** HOME PANEL */
        // Meanwhile, after the cursor exits over to the buttons components: Play, About, Exit. 
        // They should revert back to the original icon look and feel         
            // PLAY Button
            if (e.getSource() == gui.playB) { 
                System.out.println("EXITED OVER: Play button label");

                ImageIcon playHoverIcon_exit = new ImageIcon("assets/icons/play.png");  
                gui.playB.setIcon(playHoverIcon_exit);  
            }
            
            // ABOUT Button 
            else if (e.getSource() == gui.aboutB) {
                System.out.println("EXITED OVER: About button label");

                ImageIcon aboutHoverIcon_exit = new ImageIcon("assets/icons/about.png");  
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
        e.getSource(); 
        e.getID(); 
        System.out.println(e.getSource());
        System.out.println(e.getID());
        
        commander = e.getActionCommand(); 
        switch (commander) {

            /** HOME PANEL */
                case "Play":
                    System.out.println("Action performed: Play button");
                    
                    System.out.println("Setting Gameplay Frame");
                    System.out.println("Fetching images and assets files"); 
                    gui.setGamePlay(); 
                    break;  

                case "About":
                
                    break; 
                case "Exit":
            
                break; 
        }
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author aon_c
 * 
 * This class is View class.
 * Use for handle UI.
 */
public class DisplayCore extends JFrame{
    private int displayWidth;
    private int displayHeight;
    private String title;

    public DisplayCore(int displayWidth, int displayHeight, String title) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.title = title;
    }
    
    public void init(){
        setSize(displayWidth, displayHeight);
        setTitle(title);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void exit(){
        dispose();
    }
    
}

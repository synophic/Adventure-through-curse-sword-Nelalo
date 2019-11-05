/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author aon_c
 *
 * This class is View class. Use for handle UI.
 */
public class DisplayCore extends JFrame {

    private final int displayWidth;
    private final int displayHeight;
    private final String title;
    private JPanel p1;

    public DisplayCore(int displayWidth, int displayHeight, String title) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.title = title;
    }

    public void init() {
        setSize(displayWidth, displayHeight);
        setTitle(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        p1 = new JPanel();
        add(p1);
    }

    public void exit() {
        dispose();
    }

    public void addSprite(Sprites spr) {
        p1.add(new JLabel(spr.getImg(), spr.getPosition()[0]));
    }
}

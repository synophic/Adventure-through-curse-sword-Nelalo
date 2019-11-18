/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
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
    private final Map<String, Sprites> spr = new HashMap<>();

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
        p1.setLayout(null);
        add(p1);
    }

    public void exit() {
        dispose();
    }

    public void addSprite(String name, DataCore data) {
        spr.put(name, data.getCharr(name));
        refreshCharr(name, data);
    }

    //Rewrite character
    public void refreshCharr(String name, DataCore data) {
        spr.put(name, data.getCharr(name));
        p1.add(spr.get(name));
        spr.get(name).setBounds(spr.get(name).getPosition().width,
                spr.get(name).getPosition().height,
                spr.get(name).getPreferredSize().width,
                spr.get(name).getPreferredSize().height);
    }

}

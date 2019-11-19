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

    //Rewrite character
    public void refreshCharr(String name, DataCore data) {

        p1.add(data.getCharr(name));
        data.getCharr(name).setBounds(data.getCharr(name).getPosition().width,
                data.getCharr(name).getPosition().height,
                data.getCharr(name).getPreferredSize().width,
                data.getCharr(name).getPreferredSize().height);

    }
    
    public void refreshBg(String name, DataCore data) {

        p1.add(data.getBg(name));
        data.getBg(name).setBounds(data.getBg(name).getPosition().width,
                data.getBg(name).getPosition().height,
                data.getBg(name).getPreferredSize().width,
                data.getBg(name).getPreferredSize().height);

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author aon_c
 *
 * This is View class. Use for handle UI.
 */
public class DisplayCore extends JFrame implements Runnable {

    private DataCore data;
    private final int displayWidth;
    private final int displayHeight;
    private final String title;
    private JPanel p1;

    public DisplayCore(int displayWidth, int displayHeight, String title) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.title = title;
    }

    public void init(DataCore data) {
        this.data = data;
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

    public void pause() {
        JLabel pa = new JLabel("PAUSED");
        pa.setBounds(200, 200, 200, 200);
        p1.add(pa);
    }

    public void resume() {
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

    @Override
    public void run() {
        while (true) {
            try {
                UIHandle();
                Thread.sleep(17); // refresh every 17ms --> ~60fps
            } catch (InterruptedException ex) {
                Logger.getLogger(DisplayCore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void UIHandle() {
        //Runs inside of the Swing UI thread. Fix flicking image
        invokeLater(new Runnable() {
            public void run() {
                repaint();
                refreshBg("layer3", data);

                refreshCharr("knigth", data);

                refreshBg("layer2", data);
                refreshBg("layer1", data);
            }
        });
    }
}

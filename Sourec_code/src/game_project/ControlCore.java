/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author aon_c
 *
 * This class is Controller class. Use for handle command.
 */
public class ControlCore implements Runnable {

    private DataCore data;
    private DisplayCore display;
    private ServiceCore service;
    private KeyListener kl;
    private WindowAdapter w1;

    public void setData(DataCore data) {
        this.data = data;
    }

    public void setDisplay(DisplayCore display) {
        this.display = display;
    }

    public void setService(ServiceCore service) {
        this.service = service;
    }

    public void init() {
        data.init();
        service.init();
        display.init();

        //service.saveChar(data.getAllChar());
        data.replaceAllChar(service.loadChar());
        kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                data.set_Pressed(ke.getKeyCode());
                System.out.println("Pressed: " + ke.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                data.set_Released(ke.getKeyCode());
                System.out.println("Released: " + ke.getKeyCode());
            }
        };

        w1 = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(display,
                        "Save?", "EXIT",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    service.saveChar(data.getAllChar());
                    System.exit(0);
                }
            }
        };

        display.addKeyListener(kl);
        display.addWindowListener(w1);

        data.getCharr("knigth").setSpeed(20);
    }

    public void addSprite(Charactor charr) {
        data.addCharr("knigth_idle_1", charr);
    }

    public boolean getkeydock(int ch) {
        return data.is_pressed(ch);
    }

    //Game loop here
    @Override
    public void run() {
        while (true) {
            try {
                /*int dy = ((data.is_pressed(KeyEvent.VK_S) || data.is_pressed(KeyEvent.VK_DOWN)) ? 1 : 0)
                        - ((data.is_pressed(KeyEvent.VK_W) || data.is_pressed(KeyEvent.VK_UP)) ? 1 : 0);*/
                int dx = ((data.is_pressed(KeyEvent.VK_D) || data.is_pressed(KeyEvent.VK_RIGHT)) ? 1 : 0)
                        - ((data.is_pressed(KeyEvent.VK_A) || data.is_pressed(KeyEvent.VK_LEFT)) ? 1 : 0);
                Dimension dm = new Dimension(dx, 0);
                data.moveCharrPos("knigth", dm);

                //Runs inside of the Swing UI thread. Fix flicking image
                invokeLater(new Runnable() {
                    public void run() {
                        display.refreshBg("layer3", data);

                        display.refreshCharr("knigth", data);

                        display.refreshBg("layer2", data);
                        display.refreshBg("layer1", data);
                    }
                });

                //display.repaint();
                //System.out.println("Dx:" + dx + " Dy:" + dy);
                //System.out.println("" + data.getCharr("knigth").getPosition().toString());
                Thread.sleep(16); // refresh every 17ms --> ~60fps
            } catch (InterruptedException ex) {
                //System.out.println(ex.toString());
            }
        }

    }
}

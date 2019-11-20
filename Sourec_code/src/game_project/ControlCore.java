/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author aon_c
 *
 * This is Controller class. 
 * Use for handle command.
 */
public class ControlCore implements Runnable {

    private DataCore data;
    private DisplayCore display;
    private ServiceCore service;
    private KeyListener kl;
    private WindowAdapter w1;
    private Thread gameThread;

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
        //Setup other core
        data.init();
        service.init();
        display.init();
        
        gameThread = new Thread(this);

        //service.saveChar(data.getAllChar());   //sace char data
        data.replaceAllChar(service.loadChar()); //load char data
        
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

        //Ask for save on exit
        w1 = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                pauseGame();
                if (JOptionPane.showConfirmDialog(display,
                        "Save?", "EXIT",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    service.saveChar(data.getAllChar());
                    System.exit(0);
                }
            }
        };

        //Add listener
        display.addKeyListener(kl);
        display.addWindowListener(w1);

        //Set charr property
        data.getCharr("knigth").setSpeed(20);
        
    }

    public void addSprite(Charactor charr) {
        data.addCharr("knigth_idle_1", charr);
    }
    
    public void startGame(){
        gameThread.start();
    }
    
    public void pauseGame(){
        gameThread.suspend();
    }
    
    public void resumeGame(){
        gameThread.resume();
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
                Thread.sleep(17); // refresh every 17ms --> ~60fps
                
                //Debug
                //System.out.println(LocalTime.now());
                //System.out.println("Dx:" + dx + " Dy:" + dy);
                //System.out.println("" + data.getCharr("knigth").getPosition().toString());
                
            } catch (InterruptedException ex) {
                //System.out.println(ex.toString());
            }
        }

    }
}

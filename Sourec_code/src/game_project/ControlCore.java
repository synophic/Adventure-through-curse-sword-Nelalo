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
import javax.swing.JOptionPane;

/**
 * @author aon_c
 *
 * This is Controller class. Use for handle command.
 */
public class ControlCore implements Runnable {

    private DataCore data;
    private DisplayCore display;
    private ServiceCore service;
    private KeyListener kl;
    private WindowAdapter w1;
    private Thread gameThread, displayThread;
    private boolean is_paused, is_freezeCam;

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
        display.init(data);

        gameThread = new Thread(this);
        displayThread = new Thread(display);

        loadGame(data.getLevel());

        kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                data.set_Pressed(ke.getKeyCode());
                //System.out.println("Pressed: " + KeyEvent.getKeyText(ke.getKeyCode()));
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                data.set_Released(ke.getKeyCode());
                //System.out.println("Released: " + KeyEvent.getKeyText(ke.getKeyCode()));

                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!is_paused) {
                        pauseGame();
                    } else {
                        resumeGame();
                    }
                    is_paused = !is_paused;
                } else if (ke.getKeyCode() == KeyEvent.VK_P) {
                    is_freezeCam = !is_freezeCam;
                }
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
                    //service.saveChar(data.getAllChar());
                    saveGame(data.getLevel());
                    System.exit(0);
                }
            }
        };

        //Add listener
        display.addKeyListener(kl);
        display.addWindowListener(w1);

    }

    public void addSprite(Charactor charr) {
        data.addCharr("knigth_idle_1", charr);
    }

    public void loadGame(int level) {
        //data.replaceAllChar(service.loadChar()); //load char data
        data.replaceAllChar(service.loadChar());
        data.replaceAllBg(service.loadBg(level));
    }

    public void saveGame(int level) {
        //service.saveChar(data.getAllChar());   //save char data

        service.saveChar(data.getAllChar());
        service.saveBg(data.getAllBg(), level);
    }

    public void startGame() {
        gameThread.start();
        displayThread.start();
    }

    public void pauseGame() {
        //displayThread.suspend();
        gameThread.suspend();
    }

    public void resumeGame() {
        //displayThread.resume();
        gameThread.resume();
    }

    //Game loop here
    @Override
    public void run() {
        while (true) {
            try {
                moveHandle();
                bghandle();

                Thread.sleep(17);
                //Debug
                //System.out.println(data.getBg("layer3").getPosition().width);
                //System.out.println(LocalTime.now());
                //System.out.println("Dx:" + dx + " Dy:" + dy);
                //System.out.println("" + data.getCharr("knigth").getPosition().toString());
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }

    }

    private void moveHandle() {
        /*int dy = ((data.is_pressed(KeyEvent.VK_S) || data.is_pressed(KeyEvent.VK_DOWN)) ? 1 : 0)
                        - ((data.is_pressed(KeyEvent.VK_W) || data.is_pressed(KeyEvent.VK_UP)) ? 1 : 0);*/
        int dx = ((data.is_pressed(KeyEvent.VK_D) || data.is_pressed(KeyEvent.VK_RIGHT)) ? 1 : 0)
                - ((data.is_pressed(KeyEvent.VK_A) || data.is_pressed(KeyEvent.VK_LEFT)) ? 1 : 0);
        Dimension dm = new Dimension(dx, 0);
        data.moveCharrPos("knigth", dm);
    }

    private void bghandle() {
        if (data.getCharr("knigth").getPosition().width < 5 && data.getBg("layer4").getPosition().width < 0 && !is_freezeCam) {
            data.moveCharrPos("knigth", new Dimension(1, 0));
            for (int i = 1; i <= 4; i++) {
                data.moveBgPos("layer" + i, new Dimension(1, 0));
            }
        } else if (data.getCharr("knigth").getPosition().width > (display.getWidth() / 2 - data.getCharr("knigth").getPreferredSize().width) && data.getBg("layer4").getPosition().width > -(data.getBg("layer4").getPreferredSize().width - display.getDisplayWidth()) && !is_freezeCam) {
            data.moveCharrPos("knigth", new Dimension(-1, 0));
            for (int i = 1; i <= 4; i++) {
                data.moveBgPos("layer" + i, new Dimension(-1, 0));
            }
        } else if (data.getCharr("knigth").getPosition().width > display.getWidth() - 60 && data.getBg("layer4").getPosition().width == -(data.getBg("layer4").getPreferredSize().width - display.getDisplayWidth())) {
            nextlavel();
        } else if (data.getCharr("knigth").getPosition().width < - 20) {
            data.moveCharrPos("knigth", new Dimension(1, 0));
        } else if (data.getCharr("knigth").getPosition().width > display.getWidth() - 60) {
            data.moveCharrPos("knigth", new Dimension(-1, 0));
        }
    }

    private void nextlavel() {
        data.setLevel(data.getLevel() + 1);
        System.out.println("Next level is :" + data.getLevel());
        //Wait for display thread reach sleep. This make sure not interupt for loop
        while (!displayThread.getState().equals(Thread.State.TIMED_WAITING)) {
            displayThread.suspend();
            loadGame(data.getLevel());
        }
        //data.getBg("layer1").setPosition(new Dimension(0, 0));
        //data.getBg("layer2").setPosition(new Dimension(0, 0));
        //data.getBg("layer3").setPosition(new Dimension(0, 0));
        data.getCharr("knigth").setPosition(new Dimension(-20, 420));
        displayThread.resume();
    }

}

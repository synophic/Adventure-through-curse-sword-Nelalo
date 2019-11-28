/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import java.awt.*;
import TileMap.Background;
import java.awt.event.KeyEvent;
import javax.sound.sampled.Clip;

/**
 *
 * @author synophic
 */
public class MenuState extends GameState {

    private Background bg;

    private int currentChoice = 0;
    private String[] options = {
        "Start",
        "Help",
        "Credit",
        "Quit"
    };

    private Font font;

    public MenuState(GameStateManager gsm) {

        this.gsm = gsm;

        try {
            bg = new Background("/background/title.png", 1);
            bg.setVector(0, 0);

            font = new Font("Arial", Font.PLAIN, 20);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        bg.update();
    }

    @Override
    public void draw(Graphics2D g) {
        AudioPlayer.getMusic("menu").loop(Clip.LOOP_CONTINUOUSLY);
        //draw bg
        bg.draw(g);

        //draw menu options
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.DARK_GRAY);
            }
            g.drawString(options[i], 176, 170 + i * 25);
        }

    }

    private void select() {
        AudioPlayer.stopAllMusic();
        if (currentChoice == 0) {
            gsm.setState(GameStateManager.CH1_EP1STATE);
        }
        if (currentChoice == 1) {
            gsm.setState(GameStateManager.HOWTO1);
        }
        if (currentChoice == 2) {
            gsm.setState(GameStateManager.CREDIT);
        }
        if (currentChoice == 3) {
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k) {

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import TileMap.AnimateBackground;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author aon_c
 */
public class LevelSelect extends GameState {

    private AnimateBackground abg;
    private String fileName;
    private int rightChoice;
    private int leftChoice;
    private int centerChoice;
    private int frameAmount;
    private int delay;
    private Font font;

    private int currentChoice = 0;
    private String[] options = {
        "BACK",
        "Select",
        "NEXT"
    };

    public LevelSelect(GameStateManager gsm, String lvname, String fileName, int leftChoice, int centerChoice, int rightChoice, int frameAmount, int delay) {
        this.gsm = gsm;
        this.fileName = fileName;
        this.rightChoice = rightChoice;
        this.leftChoice = leftChoice;
        this.centerChoice = centerChoice;
        this.frameAmount = frameAmount;
        this.delay = delay;
        options[1] = lvname;

        font = new Font("Arial", Font.PLAIN, 15);
        abg = new AnimateBackground(fileName, 1, frameAmount, delay);
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
        abg.update();
    }

    private void select() {
        if (currentChoice == 0) {
            gsm.setState(leftChoice);
        }
        if (currentChoice == 1) {
            gsm.setState(centerChoice);
        }
        if (currentChoice == 2) {
            gsm.setState(rightChoice);
        }
    }

    @Override
    public void draw(Graphics2D g) {

        //draw animation background
        abg.draw(g);

        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.DARK_GRAY);
            }
            if (i == 1) {
                g.drawString(options[i], 160, 285);
            } else {
                g.drawString(options[i], 15 + i * 168, 257);
            }

        }

    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_LEFT) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_RIGHT) {
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

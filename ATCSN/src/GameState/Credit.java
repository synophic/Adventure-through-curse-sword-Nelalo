/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import TileMap.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author aon_c
 */
public class Credit extends GameState {

    private Background bg;

    private int currentChoice = 0;
    private String madeby = "Made By";
    private String[] name = {
        "นายพร้อมภพ เขียวสด 6107012",
        "นายยิ่งธรรม ปรีชาชาญ 61070178",
        "นายศุภสัณห์ ศิลาโรจน์ 61070227",
        "นายสรวิศ แย้มคํา 61070234",
        "นายอานนท์ อุ่นทน 61070268"
    };
    private String[] options = {
        "Back"
    };

    private Font font, font2;

    public Credit(GameStateManager gsm) {

        this.gsm = gsm;

        try {
            bg = new Background("/background/credit2.png", 1);
            bg.setVector(0, 0);

            font = new Font("Arial", Font.BOLD, 16);
            font2 = new Font("Tahoma", Font.BOLD, 12);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // AudioPlayer.load();
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

        //draw bg
        bg.draw(g);
        g.setFont(font);
        g.setColor(Color.BLUE);
        g.drawString(madeby, 168, 135);
        g.setFont(font2);
        for (int i = 0; i < name.length; i++) {
            g.setColor(Color.BLACK);
            g.drawString(name[i], 106, 178 + i * 15);
        }
        //draw menu options
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.DARK_GRAY);
            }
            g.drawString(options[i], 15 + i * 335, 257);
        }

    }

    private void select() {
        if (currentChoice == 0) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if (currentChoice == 1) {

        }
        if (currentChoice == 2) {

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

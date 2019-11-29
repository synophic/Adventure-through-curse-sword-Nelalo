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
import java.util.Random;

/**
 *
 * @author aon_c
 */
public class Credit extends GameState {

    private Background bg;

    private int currentChoice = 0;
    private String[] name = {
        "นายพร้อมภพ เขียวสด 61070129",
        "นายยิ่งธรรม ปรีชาชาญ 61070178",
        "นายศุภสัณห์ ศิลาโรจน์ 61070227",
        "นายสรวิศ แย้มคํา 61070234",
        "นายอานนท์ อุ่นทน 61070268"
    };

    private String[] name_eng = {
        "Prompop Keawsod 61070129",
        "Yingtham Preechachan 61070178",
        "Suphasan Silarot 61070227",
        "Soravit Yamkum 61070234",
        "Arnon Unthon 61070268"
    };
    private String[] role = {
        "Level Design",
        "Character & art design",
        "Programmer",
        "Tester",
        "Game Debugger & Optimizer"
    };
    private String[] options = {
        "Back"
    };

    private Font font, font2;

    public Credit(GameStateManager gsm) {

        this.gsm = gsm;

        try {
            bg = new Background("/background/newCredit.png", 1);
            bg.setVector(0, 0);

            font = new Font("Tahoma", Font.BOLD, 14);
            font2 = new Font("Tahoma", Font.BOLD, 12);//Arial Tahoma

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
        g.setFont(font2);
        g.setColor(Color.white);
        for (int i = 0; i < name_eng.length; i++) {
            g.drawString(name_eng[i], 116, 100 + i * 37);
        }
        g.setFont(font);
        for (int i = 0; i < role.length; i++) {
            g.setColor(new Color(new Random().nextFloat(),new Random().nextFloat(),new Random().nextFloat()));
            g.drawString(role[i], 106, 85 + i * 37);
        }

        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString(options[0], 182, 288);

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

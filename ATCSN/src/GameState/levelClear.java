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
 * @author synophic
 */
public class levelClear extends GameState{
    
    private Background bg;
    private Font dataFont;
    private Font menuFont;
    private int currentChoice = 0;
    private String[] options = {
        "MAIN MENU",
        "LEVEL SELECT"
    };
    private String[] info = {
        "SCORE",
        "DMG deal",
        "DMG recive",
        "Enemy Kill"
    };
    levelClear(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bg = new Background("/background/levelClear.png",1);
            bg.setVector(0, 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        dataFont = new Font("Tahoma",Font.PLAIN,17);
        menuFont = new Font("Arial",Font.PLAIN,14);
        AudioPlayer.playMusic("menu");
    }

    @Override
    public void update() {
        bg.update();
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        //draw information
        g.setFont(dataFont);
        g.setColor(Color.WHITE);
        for (int i = 0; i < info.length; i++) {
            g.drawString(info[i], 125, 100 + i * 30);
        }
        g.drawString(gsm.getScore() + "", 225, 100);
        g.drawString((int)gsm.getDmgDeal() + "", 225, 130);
        g.drawString((int)gsm.getDmgTaken() + "", 225, 160);
        g.drawString(gsm.getEnemyKill() + "", 225, 190);
        
        //draw menu
        for (int i = 0; i < options.length; i++) {
            g.setFont(menuFont);
            if(i == currentChoice) {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.DARK_GRAY);
            }
            g.drawString(options[i], 165 - i * 14, 245 + i * 36);
        }
    }
    
    private void select() {
        if(currentChoice == 0) {
            gsm.setInit(GameStateManager.CH1_EP1STATE);
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if(currentChoice == 1) {
            //System.exit(0);
            gsm.setState(GameStateManager.SELECT_CH1EP1);
        }
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER) {
            select();
        }
        if(k == KeyEvent.VK_UP) {
            currentChoice--;
            if(currentChoice == -1) {
                currentChoice = options.length -1;
            }
        }
        if(k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if(currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k) {
        
    }
    
}

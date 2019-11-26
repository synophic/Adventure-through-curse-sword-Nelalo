/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import TileMap.Background;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author synophic
 */
public class GameOverState extends GameState{
    
    private Background bg;

    public GameOverState(GameStateManager gsm) {
        this.gsm = gsm;
        
        try {
            bg = new Background("/background/gameOver.png",1);
            bg.setVector(0, 0);
        } catch(Exception e) {
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
        //draw bg
        bg.draw(g);
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }

    @Override
    public void keyReleased(int k) {
        
    }
    
}

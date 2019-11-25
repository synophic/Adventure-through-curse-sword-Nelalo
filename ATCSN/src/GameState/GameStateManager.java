/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author synophic
 */
public class GameStateManager {
    
    private ArrayList<GameState> gameStates;
    private int currentState;
    
    //Game State List
    public static final int MENUSTATE = 0;
    public static final int CH1_EP1STATE = 1;

    public GameStateManager() {
        
        gameStates = new ArrayList<GameState>();
        
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new Chap1_Ep1(this));
        
    }
    
    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }
    
    public void update() {
        gameStates.get(currentState).update();
    }
    
    public void draw(Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }
    
    public void keyPressed(int k) {
        gameStates.get(currentState).keyPressed(k);
    }
    
    public void keyReleased(int k) {
        gameStates.get(currentState).keyReleased(k);
    }
    
}

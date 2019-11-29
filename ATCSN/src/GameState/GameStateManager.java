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

    //over all score
    private int enemyKill;
    private double dmgDeal;
    private double dmgTaken;
    private int score;

    //Game State List
    public static final int MENUSTATE = 0;
    public static final int HOWTO1 = 1;
    public static final int HOWTO2 = 2;
    public static final int HOWTO3 = 3;
    public static final int CH1_EP1STATE = 4;
    public static final int CH1_EP2STATE = 5;
    public static final int SELECT_CH1EP1 = 6;
    public static final int SELECT_CH1EP2 = 7;
    public static final int LEVELCLEARSTATE = 8;
    public static final int GAMEOVERSTATE = 9;
    public static final int CREDIT = 10;

    public GameStateManager() {
        gameStates = new ArrayList<GameState>();

        //currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new Tutorial(this, "/background/howTo1.png", MENUSTATE, HOWTO2, 12, 150));
        gameStates.add(new Tutorial(this, "/background/howTo2.png", HOWTO1, HOWTO3, 10, 150));
        gameStates.add(new Tutorial(this, "/background/howTo3.png", HOWTO2, MENUSTATE, 12, 150));
        gameStates.add(new Chap1_Ep1(this));
        gameStates.add(new Chap1_Ep2(this));
        gameStates.add(new LevelSelect(this, "Chapter1 Ep1", "/background/thumb_ch1ep1.png", MENUSTATE, CH1_EP1STATE, SELECT_CH1EP2, 7, 500));
        gameStates.add(new LevelSelect(this, "Chapter1 Ep2", "/background/thumb_ch1ep2.png", SELECT_CH1EP1, CH1_EP2STATE, MENUSTATE, 7, 500));
        gameStates.add(new levelClear(this));
        gameStates.add(new GameOverState(this));
        gameStates.add(new Credit(this));
    }

    public void setEnemyKill(int enemyKill) {
        this.enemyKill = enemyKill;
    }

    public void setDmgDeal(double dmgDeal) {
        this.dmgDeal = dmgDeal;
    }

    public void setDmgTaken(double dmgTaken) {
        this.dmgTaken = dmgTaken;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getEnemyKill() {
        return enemyKill;
    }

    public double getDmgDeal() {
        return dmgDeal;
    }

    public double getDmgTaken() {
        return dmgTaken;
    }

    public int getScore() {
        return score;
    }

    public void setInit(int state) {
        gameStates.get(state).init();
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

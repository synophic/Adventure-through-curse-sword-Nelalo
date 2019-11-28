/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Entity.*;
import Entity.Enemies.*;
import TileMap.*;
import atcsn.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.*;

import java.util.ArrayList;
import javax.sound.sampled.Clip;

/**
 *
 * @author synophic
 */
public class Chap1_Ep1 extends GameState {

    private TileMap tileMap;
    private Background bg;
    private Player player;
    private ArrayList<Enemy> enemies;
    private HUD hud;
    private long Timer;
    private boolean lose;
    private boolean win;
    private int score;
    private int enemyKill;

    public Chap1_Ep1(GameStateManager gsm) {
        this.gsm = gsm;
        tileMap = new TileMap(32);
        tileMap.loadTiles("/Sprite/forestTile.png");
        tileMap.loadMap("/source/lv1_1.map");
        tileMap.setPosition(0, 0);

        bg = new Background("/background/nightForest.png", 0.1);
        player = new Player(tileMap);
        player.setPosition(100, 200);

        populateEnemies();

        hud = new HUD(player);
        lose = false;
        win = false;
        score = 0;
        enemyKill = 0;
    }

    @Override
    public void init() {
        AudioPlayer.stopAllMusic();
        AudioPlayer.playMusic("ep1");
    }

    private void populateEnemies() {
        enemies = new ArrayList<Enemy>();

        Bat bat;
        Point[] points = new Point[]{
            new Point(1120, 72),
            new Point(1344, 104),
            new Point(1440, 136),
            new Point(1600, 104),
            new Point(1760, 200),
            new Point(1856, 200),
            new Point(1952, 200)
        };
        for (int i = 0; i < points.length; i++) {
            bat = new Bat(tileMap);
            bat.setPosition(points[i].x, points[i].y);
            enemies.add(bat);
        }
        WolfBoss wolf = new WolfBoss(tileMap);
        wolf.setPosition(2912, 80);
        enemies.add(wolf);
    }

    @Override
    public void update() {

        if (win) {
            long elapsed = (System.nanoTime() - Timer) / 1000000;
            if (elapsed > 600) {
                gsm.setState(GameStateManager.LEVELCLEARSTATE);
                gsm.setDmgDeal(player.getDmgDeal());
                gsm.setDmgTaken(player.getDmgTaken());
                gsm.setScore(score);
                gsm.setEnemyKill(enemyKill);
            }
        } else if (lose) {
            long elapsed = (System.nanoTime() - Timer) / 1000000;
            if (elapsed > 400) {
                gsm.setState(GameStateManager.GAMEOVERSTATE);
            }
        }

        if (player.isDead() && !lose) {
            lose = true;
            Timer = System.nanoTime();
        }

        //attack enemies
        player.checkAttack(enemies);

        //update player
        player.update();
        tileMap.setPosition(GamePanel.WIDTH / 2 - player.getX(), GamePanel.HEIGHT / 2 - player.getY());

        //set background
        bg.setPosition(tileMap.getX(), tileMap.getY());

        //update every enemies
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update(player);
            if (e.isDead()) {
                if (e.getType() == Enemy.BOSS && !win) {
                    win = true;
                    Timer = System.nanoTime();
                }
                enemies.remove(i);
                i--;
                enemyKill++;
                if (e.getType() == Enemy.BOSS) {
                    score += 2000;
                } else {
                    score += 300;
                }
            }
        }

    }

    @Override
    public void draw(Graphics2D g) {

        //draw background
        bg.draw(g);

        //draw map tile
        tileMap.draw(g);

        //draw player
        player.draw(g);

        //draw enemies
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
            //draw hud
        }

        hud.draw(g, enemies);

    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_LEFT) {
            player.setLeft(true);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player.setRight(true);
        }
        if (k == KeyEvent.VK_UP) {
            if (!player.isAttacking()) {
                player.setJumping(true);
            }
        }
        if (k == KeyEvent.VK_SPACE) {
            if (!player.isAttacking() && !(player.isJumping() || player.isFalling())) {
                player.setAttacking();
                player.setComboing1();
            } else if (player.isAttacking() && !player.isNextCombo() && !(player.isJumping() || player.isFalling())) {
                player.setNextCombo();
            }
        }
    }

    @Override
    public void keyReleased(int k) {
        if (k == KeyEvent.VK_LEFT) {
            player.setLeft(false);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player.setRight(false);
        }
        if (k == KeyEvent.VK_UP) {
            player.setJumping(true);
        }
    }

}

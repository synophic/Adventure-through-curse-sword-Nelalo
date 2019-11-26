/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import TileMap.TileMap;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author synophic
 */
public class Player extends MapObject {
    
    //player stuff
    private double health;
    private double maxHealth;
    private double ATK;
    private double DEF;
    private boolean dead;
    private boolean flinching;
    private long flinchTimer;
    
    //combo property
    private boolean nextCombo;
    private boolean attacking;
    private boolean hited;
    
    //combo1
    private boolean comboing1;
    private double combos1_Scale;
    private int combos1_width;
    private int combos1_height;
    
    //combo2
    private boolean comboing2;
    private double combos2_Scale;
    private int combos2_width;
    private int combos2_height;
    
    //combo3
    private boolean comboing3;
    private double combos3_Scale;
    private int combos3_width;
    private int combos3_height;
    
    //animations
    private ArrayList<BufferedImage[]> sprites;
    private static final int ACTIONS = 7;
    private final int[] numFrames = {
        3, 4, 1, 1, 4, 4, 4
    };
    
    //animation actions
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int COMBO_1 = 4;
    private static final int COMBO_2 = 5;
    private static final int COMBO_3 = 6;
    
    public Player(TileMap tm) {
        
        super(tm);
        
        width = 112;
        height = 112;
        cwidth = 18;
        cheight = 50;
        
        moveSpeed = 0.4;
        maxSpeed = 2;
        stopSpeed = 0.4;
        fallSpeed = 0.2;
        maxFallSpeed = 5.0;
        jumpStart = -6;
        stopJumpSpeed = 0.3;
        
        facingRight = true;
        attacking = false;
        hited = false;
        
        health = maxHealth = 150;
        ATK = 50;
        DEF = 40;
        
        combos1_Scale = 0.4;
        combos1_width = 39;
        combos1_height = 34;
        
        combos2_Scale = 0.6;
        combos2_width = 39;
        combos2_height = 56;
        
        combos3_Scale = 0.7;
        combos3_width = 40;
        combos3_height = 50;
        
        //load sprites
        try {
            
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprite/player.png"));
            sprites = new ArrayList<BufferedImage[]>();
            for(int i = 0; i < ACTIONS; i++) {
                
                BufferedImage[] bi = new BufferedImage[numFrames[i]];
                for (int j = 0; j < numFrames[i]; j++) {
                    bi[j] = spritesheet.getSubimage(
                            j * width, 
                            i * height, 
                            width, 
                            height);
                }
                
                sprites.add(bi);
                
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        animation = new Animation();
        currentAction = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(400);
        
    }

    public double getHealth() {
        return health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getATK() {
        return ATK;
    }

    public double getDEF() {
        return DEF;
    }

    public boolean isNextCombo() {
        return nextCombo;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setComboing1() {
        this.comboing1 = true;
    }

    public void setNextCombo() {
        this.nextCombo = true;
    }

    public void setAttacking() {
        this.attacking = true;
    }

    public boolean isDead() {
        return dead;
    }
    
    
    
    public void checkAttack(ArrayList<Enemy> enemies) {
        
        //loop check enemies
        for (int i = 0; i < enemies.size(); i++) {
                    
            Enemy e = enemies.get(i);
        
            if(animation.getFrame() == 1) {
            //check combo 1
            if(comboing1 && !e.isHited()) {
                if(facingRight) {
                    if(e.getX() > x &&
                        e.getX() < x + combos1_width &&
                        e.getY() > y - combos1_height / 2 &&
                        e.getY() < y + combos1_height / 2) 
                    {
                        e.hit(ATK * combos1_Scale);
                    }
                    
                }
                
                else {
                    if(e.getX() < x &&
                            e.getX() > x - combos1_width &&
                            e.getY() > y - combos1_height / 2 &&
                            e.getY() < y + combos1_height / 2) 
                    {
                        e.hit(ATK * combos1_Scale);
                    }
                    
                }
            }
            
            //check combo 2
            else if(comboing2 && !e.isHited()) {
                if(facingRight) {
                    if(e.getX() > x &&
                        e.getX() < x + combos2_width &&
                        e.getY() > y - combos2_height / 2 &&
                        e.getY() < y + combos2_height / 2) 
                    {
                        e.hit(ATK * combos2_Scale);
                    }
                    
                }
                
                else {
                    if(e.getX() < x &&
                            e.getX() > x - combos2_width &&
                            e.getY() > y - combos2_height / 2 &&
                            e.getY() < y + combos2_height / 2) 
                    {
                        e.hit(ATK * combos2_Scale);
                    }
                    
                }
            }
            
            //check combo 3
            else if(comboing3 && !e.isHited()) {
                if(facingRight) {
                    if(e.getX() > x &&
                        e.getX() < x + combos3_width &&
                        e.getY() > y - combos3_height / 2 &&
                        e.getY() < y + combos3_height / 2) 
                    {
                        e.hit(ATK * combos3_Scale);
                    }
                    
                }
                
                else {
                    if(e.getX() < x &&
                            e.getX() > x - combos3_width &&
                            e.getY() > y - combos3_height / 2 &&
                            e.getY() < y + combos3_height / 2) 
                    {
                        e.hit(ATK * combos3_Scale);
                    }
                    
                }
            }
            
            }
            
            if(currentAction == COMBO_1 || currentAction == COMBO_2 ||currentAction == COMBO_3) {
                if(animation.hasPlayedOnce()){
                    e.setHited();
                }
            }
            }

        
        
    }
    
    public void hit(double damage) {
        if(flinching) return;
        damage *= 1 - (DEF * 0.35 / 100);
        health -= damage;
        if(health < 0) health = 0;
        if(health == 0) dead = true;
        flinching = true;
        flinchTimer = System.nanoTime();
    }
    
    private void getNextPosition() {
        
        //movement
        if(left) {
            dx -= moveSpeed;
            if(dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        }
        else if(right) {
            dx += moveSpeed;
            if(dx > maxSpeed) {
                dx = maxSpeed;
            }
        }
        else {
            if(dx > 0) {
                dx -= stopSpeed;
                if(dx < 0) {
                    dx = 0;
                }
            }
            else if(dx < 0) {
                dx += stopSpeed;
                if(dx > 0) {
                    dx = 0;
                }
            }
        }
        
        //cannot moving while attack
        if((currentAction == COMBO_1 || currentAction == COMBO_2 || currentAction == COMBO_3) &&
                !(jumping || falling)) {
            dx = 0;
            if(currentAction == COMBO_2) {
                if(facingRight) {
                    dx += 0.5;
                    if(dx > 1) dx = 0;
                }
                if(!facingRight) {
                    dx -= 0.5;
                    if(dx < -1) dx = 0;
                }
            }
        }
        
        //jumping
        if(jumping && !falling) {
            dy = jumpStart;
            falling = true;
        }
        
        //falling
        if(y + cwidth / 2 > tileMap.getHeight()) {
            dy = 0;
            dead = true;
            falling = false;
        }
        if(falling) {
            dy += fallSpeed;
            
            if(dy > 0) jumping = false;
            if(dy < 0 && !jumping) dy += stopJumpSpeed;
            if(dy > maxFallSpeed) dy = maxFallSpeed;
        }
        
    }
    
    public void update() {
        
        //update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);
        
        System.out.println(dead + "");
        
        //stop attack
        if(currentAction == COMBO_1) {
            if(animation.hasPlayedOnce() && nextCombo){
                comboing1 = false;
                comboing2 = true;
                nextCombo = false;
            }
            else if(animation.hasPlayedOnce() && !nextCombo) {
                comboing1 = false;
                attacking = false;
                nextCombo = false;
            }
        }
        else if(currentAction == COMBO_2) {
            if(animation.hasPlayedOnce() && nextCombo){
                comboing2 = false;
                comboing3 = true;
                nextCombo = false;
            }
            else if(animation.hasPlayedOnce() && !nextCombo) {
                comboing2 = false;
                attacking = false;
                nextCombo = false;
            }
        }
        else if(currentAction == COMBO_3) {
            if(animation.hasPlayedOnce()) {
                comboing3 = false;
                attacking = false;
                nextCombo = false;
            }
        }
        
        //check done flinching
        if(flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if(elapsed > 1000) flinching = false;
        }
        
        //set animation
        if(comboing1) {
            if(currentAction != COMBO_1) {
                currentAction = COMBO_1;
                animation.setFrames(sprites.get(COMBO_1));
                animation.setDelay(70);
            }
        }
        else if(comboing2) {
            if(currentAction != COMBO_2) {
                currentAction = COMBO_2;
                animation.setFrames(sprites.get(COMBO_2));
                animation.setDelay(70);
            }
        }
        else if(comboing3) {
            if(currentAction != COMBO_3) {
                currentAction = COMBO_3;
                animation.setFrames(sprites.get(COMBO_3));
                animation.setDelay(120);
            }
        }
        else if(dy > 0) {
            if(currentAction != FALLING) {
                currentAction = FALLING;
                animation.setFrames(sprites.get(FALLING));
                animation.setDelay(100);
            }
        }
        else if(dy < 0) {
            if(currentAction != JUMPING) {
                currentAction = JUMPING;
                animation.setFrames(sprites.get(JUMPING));
                animation.setDelay(100);
            }
        }
        else if(left || right) {
            if(currentAction != WALKING) {
                currentAction = WALKING;
                animation.setFrames(sprites.get(WALKING));
                animation.setDelay(100);
            }
        }
        else {
            if(currentAction != IDLE) {
                currentAction = IDLE;
                animation.setFrames(sprites.get(IDLE));
                animation.setDelay(400);
            }
        }
        
        animation.update();
        
        //set direction
        if(currentAction != COMBO_1 && currentAction != COMBO_2 && currentAction != COMBO_3) {
            if(right) facingRight = true;
            if(left) facingRight = false;
        }
        
    }
    
    public void draw(Graphics2D g) {
        
        setMapPosition();
        
        //draw player
        if(flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if(elapsed / 100 % 2 == 0) {
                return;
            }
        }
        
        super.draw(g);
        
    }
    
}

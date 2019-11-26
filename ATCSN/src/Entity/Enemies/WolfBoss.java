/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Enemies;

import Entity.*;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author synophic
 */
public class WolfBoss extends Enemy{
    
    private boolean waiting;
    private boolean startAction;
    
    //bite
    private boolean biting;
    private double biteScale;
    private int biteWidth;
    private int biteHeight;
    
    //scratch
    private boolean scratching;
    private double scratchScale;
    private int scratchWidth;
    private int scratchHeight;
    
    //animations
    private ArrayList<BufferedImage[]> sprites;
    private static final int ACTIONS = 4;
    private final int[] numFrames = {
        4, 4, 6, 5
    };
    
    //animation actions
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int BITING = 2;
    private static final int SCRATCHING = 3;
    
    private Random r;
    
    //timer
    private long waitTimer;
    
    public WolfBoss(TileMap tm) {
        super(tm);
        
        moveSpeed = 0.5;
        maxSpeed = 3;
        stopSpeed = 0.4;
        fallSpeed = 0.2;
        maxFallSpeed = 5.0;
        jumpStart = -6;
        stopJumpSpeed = 0.3;
        
        width = height = 112;
        cwidth = 60;
        cheight = 24;
        
        facingRight = false;
        waiting = true;
        
        health = maxHealth = 250;
        ATK = 35;
        DEF = 40;
        
        biteScale = 0.5;
        scratchScale = 0.75;
        biteWidth = scratchWidth = 54;
        biteHeight = scratchHeight = 80;
        
        enemyType = Enemy.BOSS;
        
        r = new Random();
        
        //load sprites
        try {
            
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprite/WolfBoss.png"));
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
        animation.setDelay(200);
        
    }
    
    public void checkAttack(Player p) {
        
            
            //check bite
            if(biting) {
                if(!facingRight) {
                    if(p.getX() > x &&
                        p.getX() < x + biteWidth &&
                        p.getY() > y - biteHeight / 2 &&
                        p.getY() < y + biteHeight / 2) 
                    {
                        attack(ATK * biteScale, p);
                    }
                    
                }
                
                else {
                    if(p.getX() < x &&
                        p.getX() > x - biteWidth &&
                        p.getY() > y - biteHeight / 2 &&
                        p.getY() < y + biteHeight / 2) 
                    {
                        attack(ATK * biteScale, p);;
                    }
                    
                }
            }
            
            //check scratch
            else if(scratching) {
                if(!facingRight) {
                    if(p.getX() > x &&
                        p.getX() < x + scratchWidth &&
                        p.getY() > y - scratchHeight / 2 &&
                        p.getY() < y + scratchHeight / 2) 
                    {
                        attack(ATK * scratchScale, p);
                    }
                    
                }
                
                else {
                    if(p.getX() < x &&
                        p.getX() > x - scratchWidth &&
                        p.getY() > y - scratchHeight / 2 &&
                        p.getY() < y + scratchHeight / 2) 
                    {
                        attack(ATK * scratchScale, p);
                    }
                    
                }
            }
            
        
        
    }
    
    protected void getNextPosition(Player p) {
        
        if(waiting) {
            if(x - 200 < p.getX()) {
                waiting = false;
                waitTimer = System.nanoTime();
            }
        }
        
        else {
            long elapsed = (System.nanoTime() - waitTimer) / 1000000;
            if(elapsed > 3000) {
                int i = r.nextInt(3);
                if(i == 1) {
                    biting = true;
                    left = false;
                    right = false;
                    
                    if(x < p.getX()) {
                        facingRight = false;
                    }
                    else if(x > p.getX()) {
                        facingRight = true;
                    }
                }
                else if(i == 2) {
                    scratching = true;
                    left = false;
                    right = false;
                    
                    if(x < p.getX()) {
                        facingRight = false;
                    }
                    else if(x > p.getX()) {
                        facingRight = true;
                    }
                }
            }
            
            else if(collision == true && facingRight){
                left = false;
                right = true;
                facingRight = false;
            }
            else if(collision == true && !facingRight){
                left = true;
                right = false;
                facingRight = true;
            }
            
            else if(x < p.getX() - 100 && (!scratching || !biting)) {
                left = false;
                right = true;
                facingRight = false;
            }
            else if(x > p.getX() + 100 && (!scratching || !biting)) {
                left = true;
                right = false;
                facingRight = true;
            }
        }
        
        //cannot moving while attack
        if((currentAction == BITING || currentAction == SCRATCHING) &&
                !(jumping || falling)) {
            dx = 0;
        }
        
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
        
        //jumping
        if(jumping && !falling) {
            dy = jumpStart;
            falling = true;
        }
        
        //falling
        if(falling) {
            dy += fallSpeed;
            
            if(dy > 0) jumping = false;
            if(dy < 0 && !jumping) dy += stopJumpSpeed;
            if(dy > maxFallSpeed) dy = maxFallSpeed;
        }
        
    }
    
    public void update(Player p) {
        
        //update position
        getNextPosition(p);
        checkTileMapCollision();
        setPosition(xtemp, ytemp);
        
        checkAttack(p);
        if((currentAction == BITING || currentAction == SCRATCHING) && ! startAction) {
            waitTimer = System.nanoTime();
            startAction = true;
        }
        else if(currentAction == BITING  && animation.hasPlayedOnce() && startAction) {
            startAction = false;
            biting = false;
        }
        else if(currentAction == SCRATCHING  && animation.hasPlayedOnce() && startAction) {
            startAction = false;
            scratching = false;
        }
        
        if(health == 0) dead = true;
        
        if(left || right) {
            if(currentAction != WALKING) {
                currentAction = WALKING;
                animation.setFrames(sprites.get(WALKING));
                animation.setDelay(100);
            }
        }
        else if(biting) {
            if(currentAction != BITING) {
                currentAction = BITING;
                animation.setFrames(sprites.get(BITING));
                animation.setDelay(100);
            }
        }
        else if(scratching) {
            if(currentAction != SCRATCHING) {
                currentAction = SCRATCHING;
                animation.setFrames(sprites.get(SCRATCHING));
                animation.setDelay(100);
            }
        }
        else {
            if(currentAction != IDLE) {
                currentAction = IDLE;
                animation.setFrames(sprites.get(IDLE));
                animation.setDelay(200);
            }
        }
        
        animation.update();
        
    }
    
    public void draw(Graphics2D g) {
        
        setMapPosition();
        
        if(notOnScreen()) return;
        
        super.draw(g);
        
    }
    
}

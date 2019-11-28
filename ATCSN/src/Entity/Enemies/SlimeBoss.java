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
public class SlimeBoss extends Enemy{
    
    //bite
    private boolean biting;
    private double biteScale;
    private int biteWidth;
    private int biteHeight;
    
    //spike
    private boolean spiking;
    private double spikeScale;
    private int spikeWidth;
    private int spikeHeight;
    
    //animations
    private ArrayList<BufferedImage[]> sprites;
    private static final int ACTIONS = 6;
    private final int[] numFrames = {
        4, 2, 2, 7, 5, 6
    };
    
    //animation actions
    private static final int IDLE = 0;
    private static final int JUMPING = 1;
    private static final int FALLING = 2;
    private static final int BITING = 3;
    private static final int SPIKING = 4;
    private static final int DYING = 5;
    
    private Random r;
    private boolean waiting;
    private boolean onGround;
    private boolean dying;
    private long waitTimer;
    
    public SlimeBoss(TileMap tm) {
        super(tm);
        
        moveSpeed = 0.4;
        maxSpeed = 2.5;
        fallSpeed = 0.2;
        maxFallSpeed = 8;
        jumpStart = -6;
        stopJumpSpeed = 0.1;
        
        width = height = 112;
        cwidth = 40;
        cheight = 30;
        
        health = maxHealth = 350;
        ATK = 45;
        DEF = 100;
        
        biteScale = 0.4;
        biteWidth = 50;
        biteHeight = 50;
        
        spikeScale = 0.6;
        spikeWidth = 46;
        spikeHeight = 48;
        
        enemyType = BOSS;
        
        r = new Random();
        
        //load sprites
        try {
            
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprite/slimeBoss.png"));
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
            if(facingRight && animation.getFrame() == 4) {
                if(p.getX() > x &&
                    p.getX() < x + biteWidth &&
                    p.getY() > y - biteHeight / 2 &&
                    p.getY() < y + cheight / 2) 
                {
                    attack(ATK * biteScale, p);
                }
                    
            }
                
            else if(!facingRight && animation.getFrame() == 4){
                if(p.getX() < x &&
                    p.getX() > x - biteWidth &&
                    p.getY() > y - biteHeight / 2 &&
                    p.getY() < y + cheight / 2) 
                {
                    attack(ATK * biteScale, p);;
                }
                    
            }
        }
            
        //check scratch
        else if(spiking) {
            if(facingRight && animation.getFrame() == 3) {
                if(p.getX() > x - spikeWidth &&
                    p.getX() < x + spikeWidth &&
                    p.getY() > y - spikeHeight / 2 &&
                    p.getY() < y + cheight / 2) 
                {
                    attack(ATK * spikeScale, p);
                }
                    
            }
                
            else if(!facingRight && animation.getFrame() == 3){
                if(p.getX() < x &&
                    p.getX() > x - spikeWidth &&
                    p.getY() > y - spikeHeight / 2 &&
                    p.getY() < y + cheight / 2) 
               {
                    attack(ATK * spikeScale, p);
                }
                    
            }
        }
        
    }
    
    protected void getNextPosition(Player p) {
        
        if(waiting) {
            long elapsed = (System.nanoTime() - waitTimer) / 1000000;
            left = false;
            right = false;
            if(x - 300 < p.getX() && elapsed > 1500) {
                waiting = false;
                waitTimer = System.nanoTime();
                
            }
        }
        else if(!waiting && !dying){
            if(onGround) {
                waitTimer = System.nanoTime();
                if(x > p.getX() - 50 && x < p.getX() + 50) {
                    int i = r.nextInt(3);
                    if(i == 0) {
                        biting = true;
                        left = false;
                        right = false;
                        if(x > p.getX()) facingRight = false;
                        else if(x < p.getX()) facingRight = true;
                    }
                    else if(i == 1) {
                        spiking = true;
                        left = false;
                        right = false;
                        if(x > p.getX()) facingRight = false;
                        else if(x < p.getX()) facingRight = true;
                    }
                    else {
                        jumping = true;
                        if(x > p.getX()) {
                            left = true;
                            right = false;
                            facingRight = false;
                        }
                        else if(x < p.getX()) {
                            left = false;
                            right = true;
                            facingRight = true;
                        }
                    }
                }
                else {
                    jumping = true;
                    if(x > p.getX()) {
                        left = true;
                        right = false;
                        facingRight = false;
                    }
                    else if(x < p.getX()) {
                        left = false;
                        right = true;
                        facingRight = true;
                    }
                }
                
                onGround = false;
            }
            if(!jumping && !falling && !biting && !spiking) {
                onGround = true;
                waiting = true;
            }
        }
        
        //cannot moving while attack
        if((currentAction == BITING || currentAction == SPIKING || onGround)) {
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
        
        if(health == 0) dying = true;
        
        if(currentAction == DYING  && animation.getFrame() == 3) {
            dead = true;
        }
        else if(currentAction == BITING  && animation.hasPlayedOnce()) {
            biting = false;
        }
        else if(currentAction == SPIKING  && animation.hasPlayedOnce()) {
            spiking = false;
        }
        
        if(biting) {
            if(currentAction != BITING) {
                currentAction = BITING;
                animation.setFrames(sprites.get(BITING));
                animation.setDelay(100);
            }
        }
        else if(spiking) {
            if(currentAction != SPIKING) {
                currentAction = SPIKING;
                animation.setFrames(sprites.get(SPIKING));
                animation.setDelay(100);
            }
        }
        else if(dying) {
            if(currentAction != DYING) {
                currentAction = DYING;
                animation.setFrames(sprites.get(DYING));
                animation.setDelay(200);
            }
        }
        else if(dy > 0) {
            if(currentAction != FALLING) {
                currentAction = FALLING;
                animation.setFrames(sprites.get(FALLING));
                animation.setDelay(500);
            }
        }
        else if(dy < 0) {
            if(currentAction != JUMPING) {
                currentAction = JUMPING;
                animation.setFrames(sprites.get(JUMPING));
                animation.setDelay(500);
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
        
        if(notOnScreen()) return;
        
        setMapPosition();
        
        super.draw(g);
        
    }
    
}

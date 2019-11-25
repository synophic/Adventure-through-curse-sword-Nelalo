/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Enemies;

import Entity.Animation;
import Entity.Enemy;
import Entity.Player;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author synophic
 */
public class Bat extends Enemy{
    
    //bat stuff
    private boolean flying;
    private boolean waiting;
    private boolean dying;
    
    private double flySpeed;
    private double maxFlySpeed;
    
    //animations
    private ArrayList<BufferedImage[]> sprites;
    private static final int ACTIONS = 3;
    private final int[] numFrames = {
        4, 4, 4
    };
    
    //animation actions
    private static final int IDLE = 0;
    private static final int FLYING = 1;
    private static final int DYING = 2;
    
    public Bat(TileMap tm) {
        
        super(tm);
        
        width = height = 56;
        cwidth =  15;
        cheight = 15;
        
        moveSpeed = 0.5;
        maxSpeed = 1;
        stopSpeed = 0.2;
        fallSpeed = 0;
        maxFallSpeed = 0;
        flySpeed = 0.3;
        maxFlySpeed = 0.8;
        
        facingRight = false;
        waiting = true;
        hited = false;
        
        health = maxHealth = 15;
        ATK = 20;
        DEF = 15;
        
        //load sprites
        try {
            
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprite/bat.png"));
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
    
    protected void getNextPosition(Player player) {
        
        //movement
        if(left) {
            dx -= moveSpeed;
            if(dx < -maxSpeed) dx = -maxSpeed;
        }
        else if(right) {
            dx += moveSpeed;
            if(dx > maxSpeed) dx = maxSpeed;
        }
        
        if(down) {
            dy += flySpeed;
            if(dy > maxFlySpeed) dy = maxFlySpeed;
        }
        else if(up) {
            dy -= flySpeed;
            if(dy < -maxFlySpeed) dy = -maxFlySpeed;
        }
        
        //cannot move while dead & waiting
        if(currentAction == IDLE || currentAction == DYING) {
            dx = dy = 0;
        }
        
        if(waiting) {
            if(x - 100 < player.getX() && x + 100 >= player.getX()) {
                waiting = false;
                flying = true;
            }
        }
        
        if(flying) {
            if(x < player.getX() - 50) {
                left = false;
                right = true;
                facingRight = true;
            }
            else if(x > player.getX() + 50) {
                left = true;
                right = false;
                facingRight = false;
            }
            if(y < player.getY()) {
                down = true;
                up = false;
            }
            else if(y > player.getY()) {
                down = false;
                up = true;
            }
        }
        
    }
    
    public void update(Player player) {
        
        //update position
        getNextPosition(player);
        checkTileMapCollision();
        setPosition(xtemp, ytemp);
        
        //System.out.println(health +"");
        
        if(intersects(player)) {
            attack(ATK, player);
        }
        
        if(health == 0) dying = true;
        
        if(currentAction == DYING) {
            if(animation.hasPlayedOnce()) dead = true;
        }
        
        if(dying) {
            if(currentAction != DYING) {
                currentAction = DYING;
                animation.setFrames(sprites.get(DYING));
                animation.setDelay(70);
            }
        }
        else if(left || right || up || down) {
            if(currentAction != FLYING) {
                currentAction = FLYING;
                animation.setFrames(sprites.get(FLYING));
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
        
        //update animation
        animation.update();
        
    }
    
    public void draw(Graphics2D g) {
        
        setMapPosition();
        
        if(notOnScreen()) return;
        
        super.draw(g);
        
    }
    
}

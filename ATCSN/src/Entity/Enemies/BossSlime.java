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
import javax.imageio.ImageIO;

/**
 *
 * @author synophic
 */
public class BossSlime extends Enemy{
    
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
    
    public BossSlime(TileMap tm) {
        super(tm);
        
        moveSpeed = 0.2;
        maxSpeed = 1;
        fallSpeed = 0.3;
        maxFallSpeed = 8;
        jumpStart = -7;
        stopJumpSpeed = 0.25;
        
        width = height = 112;
        cwidth = 40;
        cheight = 30;
        
        health = maxHealth = 350;
        ATK = 45;
        DEF = 70;
        
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
        left = true;
        
    }
    
    protected void getNextPosition(Player p) {
        
    }
    
    public void update(Player p) {
        
        //update position
        getNextPosition(p);
        checkTileMapCollision();
        setPosition(xtemp, ytemp);
        
    }
    
    public void draw(Graphics2D g) {
        
        if(notOnScreen()) return;
        
        setMapPosition();
        
        super.draw(g);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import atcsn.GamePanel;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author synophic
 */
public class HUD {
    
    private Font font;
    private Player player;
    
    //mapObject HP BAR
    private BufferedImage image;
    private BufferedImage[] object;
    public static final int PLAYER_BAR = 0;
    public static final int HP_BAR = 1;
    public static final int ENEMY_BAR = 2;
    public static final int ENEMY_HP = 3;
    public static final int BOSS_BAR = 4;
    
    //story interface
    private boolean skip;
    private String[] story;
    private int allStory;
    private int currentStory;
    
    public HUD(Player p) {
        player = p;
        skip = false;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Sprite/interface.png"));
            object = new BufferedImage[5];
            object[PLAYER_BAR] = image.getSubimage(0, 0, 128, 48);
            object[HP_BAR] = image.getSubimage(0, 48, 71, 3);
            object[ENEMY_BAR] = image.getSubimage(80, 48, 16, 3);
            object[ENEMY_HP] = image.getSubimage(81, 56, 14, 1);
            object[BOSS_BAR] = image.getSubimage(0, 64, 128, 32);
            font = new Font("Tahoma", Font.PLAIN, 14);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void drawStory(Graphics2D g, String s) {
        try {
            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            allStory = Integer.parseInt(br.readLine());
            if(!skip) {
                story = new String[allStory];
                
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g, ArrayList<Enemy> enemies) {
        
        //draw player bar & hp
        g.drawImage(object[PLAYER_BAR], 
                0, 
                0, 
                null);
        g.drawImage(object[HP_BAR], 
                44, 
                16, 
                (int)(71 * player.getHealth() / player.getMaxHealth()), 
                3, 
                null);
        
        for (int i = 0; i < enemies.size(); i++) {
            
            //draw enemy hp
            if(!enemies.get(i).notOnScreen()) {
                
                if(enemies.get(i).getType() == Enemy.NORMAL) {
                    
                    g.drawImage(object[ENEMY_BAR], 
                            (int)(enemies.get(i).getX() + enemies.get(i).xmap - 14), 
                            (int)((enemies.get(i).getCwidth() / 2) + enemies.get(i).getY() + enemies.get(i).ymap + 10),
                            28,
                            2,
                            null);
                    g.drawImage(object[ENEMY_HP], 
                            (int)(enemies.get(i).getX() + enemies.get(i).xmap - 13), 
                            (int)((enemies.get(i).getCwidth() / 2) + enemies.get(i).getY() + enemies.get(i).ymap + 11),
                            (int)(28 * enemies.get(i).getHealth() / enemies.get(i).getMaxHealth()),
                                2,
                            null);
                    
                }
                else if(enemies.get(i).getType() == Enemy.BOSS) {
                    
                    g.drawImage(object[BOSS_BAR], 
                            GamePanel.WIDTH / 2 - 64, 
                            0, 
                            null);
                    g.drawImage(object[HP_BAR], 
                            GamePanel.WIDTH / 2 - 55, 
                            14, 
                           (int)(112 * enemies.get(i).getHealth() / enemies.get(i).getMaxHealth()), 
                            4, 
                            null);
                    
                }
                
            }
            
        }
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author synophic
 */
public class HUD {
    
    private Player player;
    private ArrayList<BufferedImage[]> image;
    
    public HUD(Player p) {
        player = p;
        try {
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
}

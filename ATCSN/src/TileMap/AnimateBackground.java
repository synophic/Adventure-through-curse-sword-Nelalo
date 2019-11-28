/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TileMap;

import Entity.Animation;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import atcsn.GamePanel;

/**
 *
 * @author synophic
 */
public class AnimateBackground {
    protected Animation animation;
    private BufferedImage[] animate;
    
    private double x;
    private double y;
    private double dx;
    private double dy;
    
    private double moveScale;
    
    public AnimateBackground(String s, double ms, int frameAmount, int delay) {
        try{
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(s));
            int width = image.getWidth() / frameAmount;
            int height = image.getHeight();
            moveScale = ms;
            animate = new BufferedImage[frameAmount];
            for (int i = 0; i < frameAmount; i++) {
                animate[i] = image.getSubimage(
                        i * width, 
                        0, 
                        width, 
                        height);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        animation = new Animation();
        animation.setFrames(animate);
        animation.setDelay(delay);
    }
    
    public void setPosition(double x, double y) {
        this.x = (x * moveScale) % GamePanel.WIDTH;
        this.y = (y * moveScale) % GamePanel.HEIGHT;
    }
    
    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    
    public void update() {
        x += dx;
        y += dy;
        animation.update();
    }
    
    public void draw(Graphics2D g) {
        g.drawImage(animation.getImage(), (int)x, (int)y, null);
        if(x < 0) {
            g.drawImage(animation.getImage(), (int)x + GamePanel.WIDTH, (int)y, null);
        }
        if(x > 0) {
            g.drawImage(animation.getImage(), (int)x - GamePanel.WIDTH, (int)y, null);
        }
    }
    
}

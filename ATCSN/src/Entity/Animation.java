/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.image.BufferedImage;

/**
 *
 * @author synophic
 */
public class Animation {
    
    //frame
    private BufferedImage[] frames;
    private int currentFrame;
    
    //sprite delay
    private long startTime;
    private long delay;
    
    //loopable
    private boolean playedOnce;
    
    public Animation() {
        playedOnce = false;
    }
    
    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
        playedOnce = false;
    }

    public void setFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }
    
    public void update() {
        
        if(delay == -1) return;
        
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if(elapsed > delay) {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length) {
            currentFrame = 0;
            playedOnce = true;
        }
                
    }

    public BufferedImage getImage() {
        return frames[currentFrame];
    }

    public int getFrame() {
        return currentFrame;
    }

    public boolean hasPlayedOnce() {
        return playedOnce;
    }
    
    
}

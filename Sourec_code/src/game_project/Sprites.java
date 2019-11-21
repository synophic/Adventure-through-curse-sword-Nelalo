/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * @author aon_c
 *
 * Interface for movable sprite. Ex. Character, Puzzle, other physic thing.
 */
public class Sprites extends JLabel {

    private boolean dying, jumping, falling;
    //private ImageIcon img;
    private int speed, jump_heigth, jump_speed;
    private Dimension position, base_pos; //[X, Y]

    public Sprites(ImageIcon img, Dimension position) {
        super(img);
        //this.img = img;
        this.position = position;
        base_pos = position;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public void setImageIcon(ImageIcon img) {

    }

    public Dimension getPosition() {
        return position;
    }

    public void setPosition(Dimension position) {
        this.position = position;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public int getJump_heigth() {
        return jump_heigth;
    }

    public void setJump_heigth(int jump_heigth) {
        this.jump_heigth = jump_heigth;
    }

    public Dimension getBase_pos() {
        return base_pos;
    }

    public void setBase_pos(Dimension base_pos) {
        this.base_pos = base_pos;
    }

    public int getJump_speed() {
        return jump_speed;
    }

    public void setJump_speed(int jump_speed) {
        this.jump_speed = jump_speed;
    }

}

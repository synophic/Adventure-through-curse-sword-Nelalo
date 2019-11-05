/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * @author aon_c
 *
 * Interface for movable sprite. Ex. Character, Puzzle, other physic thing.
 */
public class Sprites {

    private boolean visible, dying;
    private ImageIcon img;
    private int position[] = {0, 0}; //[X, Y]
    private JLabel j1;

    public Sprites(ImageIcon img, int[] position) {
        this.img = img;
        this.position = position;
        j1 = new JLabel(img, position[0]);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

}

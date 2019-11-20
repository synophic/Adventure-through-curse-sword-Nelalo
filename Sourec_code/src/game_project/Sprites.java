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

    private boolean dying;
    //private ImageIcon img;
    private Dimension position; //[X, Y]

    public Sprites(ImageIcon img, Dimension position) {
        super(img);
        //this.img = img;
        this.position = position;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public Dimension getPosition() {
        return position;
    }

    public void setPosition(Dimension position) {
        this.position = position;
    }

}

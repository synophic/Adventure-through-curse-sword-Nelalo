/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import java.awt.Dimension;
import javax.swing.ImageIcon;

/**
 *
 * @author aon_c
 */
public class Charactor extends Sprites {

    private int health, atk;

    public Charactor(ImageIcon img, Dimension position) {
        super(img, position);
    }

    public int getHealth() {
    return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

   
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.ImageIcon;

/**
 * @author aon_c
 *
 * This class is Model class. Use for handle UI.
 */
public class DataCore {

    private final Map<String, Charactor> charr = new HashMap<>();
    private final Map<Integer, Boolean> keydock = new HashMap<>();

    public void init() {
        this.addCharr("knigth", new Charactor(new ImageIcon("src/resource/Sprite/Char/Knight/knight.png"), new Dimension(0, 0)));
    }

    public void addCharr(String name, Charactor charr) {
        this.charr.put(name, charr);
    }

    public Charactor getCharr(String name) {
        return charr.get(name);
    }

    public void setCharrPos(String name, Dimension dm) {
        Charactor charr_temp = charr.get(name);
        charr_temp.setPosition(dm);
        charr.put(name, charr_temp);
    }

    public void moveCharrPos(String name, Dimension dm) {
        Charactor charr_temp = charr.get(name);
        Dimension dm_temp = new Dimension(charr_temp.getPosition().width + (dm.width * charr_temp.getSpeed()),
                            charr_temp.getPosition().height + (dm.height * charr_temp.getSpeed()));
        charr_temp.setPosition(dm_temp);
        charr.put(name, charr_temp);
    }

    public boolean is_pressed(int key) {
        return keydock.get(key) == null ? false : keydock.get(key);
    }

    public void set_Pressed(int key) {
        keydock.put(key, true);
    }

    public void set_Released(int key) {
        keydock.put(key, false);
    }

}

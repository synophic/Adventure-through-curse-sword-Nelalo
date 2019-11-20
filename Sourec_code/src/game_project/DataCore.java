/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 * @author aon_c
 *
 * This is Model class. 
 * Use for handle data.
 */
public class DataCore {

    private Map<String, Charactor> charr = new HashMap<>();
    private Map<String, Charactor> bg = new HashMap<>();
    private Map<Integer, Boolean> keydock = new HashMap<>();

    public void init() {
        this.addCharr("knigth", new Charactor(new ImageIcon("src/resource/Sprite/Char/Knight/knight.png"), new Dimension(0, 0)));
        this.addBg("layer1", new Charactor(new ImageIcon("src/resource/BG/level1/layer1.png"), new Dimension(0, 0)));
        this.addBg("layer2", new Charactor(new ImageIcon("src/resource/BG/level1/layer2.png"), new Dimension(0, 0)));
        this.addBg("layer3", new Charactor(new ImageIcon("src/resource/BG/level1/layer3.png"), new Dimension(0, 0)));
    }

    public void addCharr(String name, Charactor charr) {
        this.charr.put(name, charr);
    }

    public Charactor getCharr(String name) {
        return charr.get(name);
    }

    public void addBg(String name, Charactor spr) {
        this.bg.put(name, spr);
    }

    public Charactor getBg(String name) {
        return bg.get(name);
    }

    public void setCharrPos(String name, Dimension dm) {
        Charactor charr_temp = charr.get(name);
        charr_temp.setPosition(dm);
        charr.put(name, charr_temp);
    }

    public void moveCharrPos(String name, Dimension dm) {
        // Charactor charr_temp = charr.get(name);
        Dimension dm_temp = new Dimension(charr.get(name).getPosition().width + (dm.width * charr.get(name).getSpeed()),
                charr.get(name).getPosition().height + (dm.height * charr.get(name).getSpeed()));
        //charr_temp.setPosition(dm_temp);
        //charr.put(name, charr_temp);
        charr.get(name).setPosition(dm_temp);
    }

    public void moveBgPos(String name, Dimension dm) {
        Dimension dm_temp = new Dimension(bg.get(name).getPosition().width + dm.width,
                bg.get(name).getPosition().height + dm.height);
        bg.get(name).setPosition(dm_temp);
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

    public Map<String, Charactor> getAllChar() {
        return charr;
    }

    public void replaceAllChar(Map<String, Charactor> charr) {
        this.charr = charr;
    }
}

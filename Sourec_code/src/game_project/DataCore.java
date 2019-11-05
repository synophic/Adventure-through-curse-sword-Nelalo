/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 * @author aon_c
 *
 * This class is Model class. Use for handle UI.
 */
public class DataCore {

    private Map<String, Character> charr = new HashMap<>();

    public void init() {
        this.addCharr("knigth_idle_1", new Character(new ImageIcon("src/resource/Sprite/Char/Knight/knight.png"), new int[]{0, 0}));
    }

    public void addCharr(String name, Character charr) {
        this.charr.put(name, charr);
    }

    public Character getCharr(String name) {
        return charr.get(name);
    }
}

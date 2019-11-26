/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 *
 * @author synophic
 */
public class AudioPlayer {
    
    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
    public static Map<String, Music> musicMap = new HashMap<String, Music>();
    
    public static void load() {
        
        try {
            soundMap.put("dead", new Sound("/Sound/SFX/dead.wav"));
            soundMap.put("hit", new Sound("/Sound/SFX/hit.wav"));
            soundMap.put("jump", new Sound("/Sound/SFX/jump.wav"));
            soundMap.put("slash1", new Sound("/Sound/SFX/slash1.wav"));
            soundMap.put("slash2", new Sound("/Sound/SFX/slash2.wav"));
            soundMap.put("slash3", new Sound("/Sound/SFX/slash3.wav"));
            
            musicMap.put("menu", new Music("/Sound/BGM/menu.ogg"));
            musicMap.put("ep1", new Music("/Sound/BGM/ep1.wav"));
            musicMap.put("gameOver", new Music("/Sound/BGM/gameOver.ogg"));
        } catch (SlickException e) {
            e.printStackTrace();
        }
        
    }
    
    public static Music getMusic(String key) {
        return musicMap.get(key);
    }
    
    public static Sound getSound(String key) {
        return soundMap.get(key);
    }
    
}

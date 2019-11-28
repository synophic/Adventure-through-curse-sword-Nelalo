/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

/**
 *
 * @author synophic
 */
public class AudioPlayer {
    
    private static final String[] SFX = {
        "dead",
        "hit",
        "jump",
        "slash1",
        "slash2",
        "slash3"
    };
    
    private static final String[] SFXFile = {
        "Sound/dead.wav",
        "Sound/hit.wav",
        "Sound/jump.wav",
        "Sound/slash1.wav",
        "Sound/slash2.wav",
        "Sound/slash3.wav"
    };
    
    private static final String[] BGM = {
        "menu",
        "ep1",
        "gameOver",
    };
    
    private static final String[] BGMFile = {
        "Sound/menu.ogg",
        "Sound/ep1.wav",
        "Sound/gameOver.ogg",
    };
    
    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
    public static Map<String, Music> musicMap = new HashMap<String, Music>();
    
    public static void load() {
        for (int i = 0; i < SFXFile.length; i++) {
            try {
                soundMap.put(SFX[i], new Sound(SFXFile[i]));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < BGMFile.length; i++) {
            try {
                musicMap.put(BGM[i], new Music(BGMFile[i]));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static Music getMusic(String key) {
        return musicMap.get(key);
    }
    
    public static Sound getSound(String key) {
        return soundMap.get(key);
    }
    
}

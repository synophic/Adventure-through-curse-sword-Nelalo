/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import java.io.File;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//import org.newdawn.slick.Music;
//import org.newdawn.slick.SlickException;
//import org.newdawn.slick.Sound;

/**
 *
 * @author synophic
 */
public class AudioPlayer {

    public static Map<String, Clip> soundMap = new HashMap<String, Clip>();
    public static Map<String, Clip> musicMap = new HashMap<String, Clip>();

    public static void load() {
        soundMap.put("dead", loadSound("src/Sound/SFX/dead.wav", 1f));
        soundMap.put("hit", loadSound("src/Sound/SFX/hit.wav", 1f));
        soundMap.put("jump", loadSound("src/Sound/SFX/jump.wav", 1f));
        soundMap.put("slash1", loadSound("src/Sound/SFX/slash1.wav", 1f));
        soundMap.put("slash2", loadSound("src/Sound/SFX/slash2.wav", 1f));
        soundMap.put("slash3", loadSound("src/Sound/SFX/slash3.wav", 1f));
        musicMap.put("menu", loadSound("src/Sound/BGM/menu.wav", 0f));
        musicMap.put("ep1", loadSound("src/Sound/BGM/ep1.wav", -9f));
        musicMap.put("gameOver", loadSound("src/Sound/BGM/gameOver.wav", 0f));

        //getMusic("ep1").start();
        //getMusic("ep1").stop();
    }

    public static Clip getSound(String key) {
        return soundMap.get(key);
    }

    public static Clip getMusic(String key) {
        return musicMap.get(key);
    }

    public static Clip loadSound(String url, float gain) {
        try {
            File f = new File(url);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(gain);
            return clip;
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

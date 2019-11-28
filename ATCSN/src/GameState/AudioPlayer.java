/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.sound.sampled.*;
//import org.newdawn.slick.Music;
//import org.newdawn.slick.Sound;

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
        "gameOver",};

    private static final String[] BGMFile = {
        "Sound/menu.wav",
        "Sound/ep1.wav",
        "Sound/gameOver.wav",};

    public static Map<String, Clip> soundMap = new HashMap<String, Clip>();
    public static Map<String, Clip> musicMap = new HashMap<String, Clip>();

    public static void load() {
        for (int i = 0; i < SFXFile.length; i++) {
            try {
                soundMap.put(SFX[i], loadSound(SFXFile[i]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < BGMFile.length; i++) {
            try {
                musicMap.put(BGM[i], new Music(BGMFile[i]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Clip getMusic(String key) {
        return musicMap.get(key);
    }

    public static Clip getSound(String key) {
        return soundMap.get(key);
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

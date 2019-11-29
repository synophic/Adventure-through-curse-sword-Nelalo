/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;
//import org.newdawn.slick.Music;
//import org.newdawn.slick.Sound;

/**
 *
 * @author synophic
 */
public class AudioPlayer {

    //getMusic("ep1").start();
    //getMusic("ep1").stop();
    //getMusic("ep1").loop(Clip.LOOP_CONTINUOUSLY);
    private static final String[] SFX = {
        "dead",
        "hit",
        "jump",
        "slash1",
        "slash2",
        "slash3"
    };

    private static final Object[][] SFXFile = {
        {"src/Sound/SFX/dead.wav", -4f},
        {"src/Sound/SFX/hit.wav", -4f},
        {"src/Sound/SFX/jump.wav", -15f},
        {"src/Sound/SFX/slash1.wav", -4f},
        {"src/Sound/SFX/slash2.wav", -4f},
        {"src/Sound/SFX/slash3.wav", -4f}
    };

    private static final String[] BGM = {
        "menu",
        "ep1",
        "gameOver"
    };

    private static final Object[][] BGMFile = {
        {"src/Sound/BGM/menu.wav", -6f},
        {"src/Sound/BGM/ep1.wav", -6f},
        {"src/Sound/BGM/gameOver.wav", -6f}
    };

    public static Map<String, Clip> soundMap = new HashMap<String, Clip>();
    public static Map<String, Clip> musicMap = new HashMap<String, Clip>();

    // min[-80f <--> 6f]max  | [-30f <--> 0f] recomanded
    public static void load() {
        for (int i = 0; i < SFXFile.length; i++) {
            try {
                soundMap.put(SFX[i], loadSound((String) SFXFile[i][0], (Float) SFXFile[i][1]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < BGMFile.length; i++) {
            try {
                musicMap.put(BGM[i], loadSound((String) BGMFile[i][0], (Float) BGMFile[i][1]));
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

    public static void playMusic(String key) {
        musicMap.get(key).setFramePosition(0);
        musicMap.get(key).loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stopMusic(String key) {
        musicMap.get(key).stop();
    }

    public static void playSound(String key) {
        soundMap.get(key).setFramePosition(0);
        soundMap.get(key).start();
    }

    public static void stopSound(String key) {
        soundMap.get(key).stop();
    }

    public static void stopAllMusic() {
        for (String key : musicMap.keySet()) {
            stopMusic(key);
        }
    }

    public static void stopAllSound() {
        for (String key : musicMap.keySet()) {
            stopSound(key);
        }
    }

}

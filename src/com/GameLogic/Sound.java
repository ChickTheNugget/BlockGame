package com.GameLogic;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * The SoundManager, responsible for loading and playing sounds.
 */
public class Sound {
    Clip clip;
    URL[] soundURL = new URL[3];

    /**
     * Constructor for the Sound class.
     */
    public Sound() {
        soundURL[0] = getClass().getResource("/com/assets/box_push.wav");
        soundURL[1] = getClass().getResource("/com/assets/music.wav");
        soundURL[2] = getClass().getResource("/com/assets/winsound.wav");
    }

    /**
     * Sets the clip to the sound index in order for it to be played later.
     * 
     * @param sound the index of the sound to be played in the soundURL array
     */
    public void setFile(int sound) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[sound]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            System.out.println("Error while fetching soundclip: " + e);
            e.printStackTrace();
            System.exit(e.hashCode());
        }
    }

    /**
     * Starts playing a sound.
     */
    public void play() {
        clip.start();
    }

    /**
     * Sets the clip to looping.
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops playing a sound.
     */
    public void stop() {
        clip.stop();
    }
}

package com.GameLogic;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/com/assets/blip.wav"); 
        soundURL[1] = getClass().getResource("/com/assets/music.wav"); 
    }
    public void setFile(int sound) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[sound]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            System.out.println("cant get soundurl");
        }
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
}

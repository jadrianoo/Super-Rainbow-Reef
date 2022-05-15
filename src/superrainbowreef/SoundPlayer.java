package superrainbowreef;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;


public class SoundPlayer {

    public Clip clip;
    URL soundURL[] = new URL[10];

    public SoundPlayer(){
        soundURL[0] = SRR.class.getClassLoader().getResource("Music.wav");
        soundURL[1] = SRR.class.getClassLoader().getResource("Sound_block.wav");
    }
    public void setFile(int x){
        try{
            AudioInputStream sounds = AudioSystem.getAudioInputStream(soundURL[x]);
            clip = AudioSystem.getClip();
            clip.open(sounds);
        }catch(Exception e){

        }
    }
    public void playClip(){
        clip.start();
    }
    public void loopClip(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopClip(){
        clip.start();
    }
}

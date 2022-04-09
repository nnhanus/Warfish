package OST;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    static AudioInputStream audio;

    Clip clip;

    public Music () throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        audio = AudioSystem.getAudioInputStream(new File("Program in C — The Memory Unsafety Anthem.mp3").getAbsoluteFile());
        clip = AudioSystem.getClip();

        clip.open(audio);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play(){
        clip.start();
    }
}

package OST;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    static AudioInputStream audio;

    Clip clip;

    public Music () throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File f = new File("/home/brice/IdeaProjects/Warfish/src/OST/Program_in_C.au");
        System.out.println(f.getAbsoluteFile());
        AudioFileFormat.Type[] supportedList = AudioSystem.getAudioFileTypes();
        clip = AudioSystem.getClip();

        audio = AudioSystem.getAudioInputStream(f.getAbsoluteFile());


        clip.open(audio);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play(){
        clip.start();
    }
}

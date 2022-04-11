import Control.Controller;
import Modele.GrilleMod;
import Modele.Jardinier;
import View.View;
import View.ThreadAffichage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Main
 * Lancement du jeu
 */
public class Warfish {


    public static void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Program in C.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    public static void main(String [] args){
        GrilleMod gr = new GrilleMod(); //Etat du jeu
        View view = new View(); //Affichage du jeu
        Controller controller = new Controller(view); //Contrôle
        (new ThreadAffichage(view)).start(); //Mise à jour de l'affichage
        playSound();
    }
}

import Control.Controller;
import Modele.GrilleMod;
import Modele.Jardinier;
import View.View;
import View.ThreadAffichage;

/**
 * Main
 * Lancement du jeu
 */
public class Warfish {
    public static void main(String [] args){
        GrilleMod gr = new GrilleMod(); //Etat du jeu
        View view = new View(); //Affichage du jeu
        Controller controller = new Controller(view); //Contrôle
        (new ThreadAffichage(view)).start(); //Mise à jour de l'affichage
    }
}

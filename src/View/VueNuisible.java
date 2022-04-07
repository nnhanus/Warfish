package View;

import Modele.GrilleMod;
import Modele.Nuisible;

import java.awt.*;
import java.util.ArrayList;

public class VueNuisible {
    public static ArrayList<Nuisible> nuisibles = new ArrayList<>();
    public static int TAILLE_NUISIBLE = 50;

    public VueNuisible(){
        nuisibles.addAll(GrilleMod.getNuisibles());
    }

    /**
     * drawNuisibles
     * Affiche les nuisibles
     * @param g
     */
    public static void drawNuisibles(Graphics g){
        for (Nuisible n : nuisibles) {
            g.drawImage(Movable.nuis, n.getX() - TAILLE_NUISIBLE/2, n.getY()-TAILLE_NUISIBLE/2, TAILLE_NUISIBLE, TAILLE_NUISIBLE, null);
        }
    }

    /**
     * updateNuisibles
     * Met à jour l'affichage des nuisibles
     */
    public static void updateNuisibles(){
        nuisibles.clear();
        nuisibles.addAll(GrilleMod.getNuisibles());
    }
}

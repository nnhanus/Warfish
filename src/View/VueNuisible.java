package View;

import Modele.GrilleMod;
import Modele.Nuisible;

import java.awt.*;
import java.util.ArrayList;

public class VueNuisible {
    public static ArrayList<Nuisible> nuisibles = new ArrayList<>(); //nuisibles sur le terrain
    public static int TAILLE_NUISIBLE = 50; //taille

    public VueNuisible(){
        nuisibles.addAll(GrilleMod.getNuisibles()); //ajout des nuisibles à la liste
    }

    /**
     * drawNuisibles
     * Affiche les nuisibles
     * @param g
     */
    public static void drawNuisibles(Graphics g){
        for (Nuisible n : nuisibles) { //parcours
            //image
            g.drawImage(Movable.nuis, n.getX() - TAILLE_NUISIBLE/2, n.getY()-TAILLE_NUISIBLE/2, TAILLE_NUISIBLE, TAILLE_NUISIBLE, null);
        }
    }

    /**
     * updateNuisibles
     * Met à jour l'affichage des nuisibles
     */
    public static void updateNuisibles(){
        nuisibles.clear(); //enlever tous les nuisibles
        nuisibles.addAll(GrilleMod.getNuisibles()); //ajouter tous les nuisibles
    }
}

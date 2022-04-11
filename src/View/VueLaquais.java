package View;

import Modele.GrilleMod;
import Modele.Jardinier;
import Modele.Laquais;

import java.awt.*;
import java.util.ArrayList;

public class VueLaquais {
    public static int TAILLE_LAQUAIS = 40;

    public VueLaquais(){
    }

    /**
     * drawJardinier
     * Affiche le jardinier ainsi que son rayon d'action
     * @param g
     */
    public static void drawLaquais(Graphics g){
        for (Laquais laquais : GrilleMod.getLaquais()) { //parcours de la liste
            //image
            g.drawImage(Movable.laquais, laquais.getX() - TAILLE_LAQUAIS/2, laquais.getY() - TAILLE_LAQUAIS/2, TAILLE_LAQUAIS, TAILLE_LAQUAIS, null);
            //rayon d'action
        }
    }

    public static void updateLaquais(){

    }
}

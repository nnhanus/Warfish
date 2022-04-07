package View;


import Modele.GrilleMod;
import Modele.Jardinier;

import java.awt.*;
import java.util.ArrayList;


public class JardinierView {
    public static int TAILLE_JARDINIER = 80;

    public static ArrayList<Jardinier> listjardinier = new ArrayList<>(); //liste des jardiniers en jeu


    public JardinierView(){
        for(Jardinier u:GrilleMod.getJardiniers()){ //parcours des jardiniers du terrain et ajout à la liste
            listjardinier.add(u);
        }
    }

    /**
     * drawJardinier
     * Affiche le jardinier ainsi que son rayon d'action
     * @param g
     */
    public static void drawJardinier(Graphics g){
        for (Jardinier jardinier : listjardinier) { //parcours de la liste
            //image
            g.drawImage(Movable.meduse, jardinier.getX() - JardinierView.TAILLE_JARDINIER/2, jardinier.getY() - JardinierView.TAILLE_JARDINIER/2, JardinierView.TAILLE_JARDINIER, JardinierView.TAILLE_JARDINIER, null);
            //rayon d'action
            g.setColor(Color.PINK);
            int sq = (int) Math.sqrt(19000);
            g.drawOval(jardinier.getX() - sq, jardinier.getY() - sq, sq*2, sq*2);
        }
    }
}

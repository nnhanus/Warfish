package View;

import Modele.*;

import java.awt.*;
import java.util.ArrayList;

public class VueFleur {
    public static ArrayList<Fleur> fleurs = new ArrayList<Fleur>(); //fleurs sur le terrain

    public static int TAILLE_FLEUR = 40; //taille des fleurs
  
    public VueFleur(){
        fleurs.addAll(GrilleMod.getFleurs()); //ajout des fleurs
    }

    /**
     * drawFleur
     * Affiche les fleurs selon leur état et type
     */
    public static void drawFleur(Graphics g){
        for (Fleur f : fleurs) { //on itère sur le tableau des fleurs présentes sur le terrain
            if (!(f.getIsPicked())) { //si la fleur est toujours sur le terrain
                if (f.lifespan >= 9) { //si la fleur est fermée
                    switch (f.getType()) { //on affiche la fleur en fonction de son type
                        case GrilleMod.indiceFleurR:
                            g.drawImage(Movable.RFer, f.getX() - TAILLE_FLEUR / 2, f.getY() - TAILLE_FLEUR / 2, TAILLE_FLEUR, TAILLE_FLEUR, null);
                            break;
                        case GrilleMod.indiceFleurJ:
                            g.drawImage(Movable.JFer, f.getX() - TAILLE_FLEUR / 2, f.getY() - TAILLE_FLEUR / 2, TAILLE_FLEUR, TAILLE_FLEUR, null);
                            break;
                        case GrilleMod.indiceFleurV:
                            g.drawImage(Movable.VFer, f.getX() - TAILLE_FLEUR / 2, f.getY() - TAILLE_FLEUR / 2, TAILLE_FLEUR, TAILLE_FLEUR, null);
                            break;
                    }
                } else if (!(f.getIsDead())) { //si la fleur est fleurie
                    switch (f.getType()) { //on affiche la fleur en fonction de son type
                        case GrilleMod.indiceFleurR:
                            g.drawImage(Movable.ROuv, f.getX() - TAILLE_FLEUR / 2, f.getY() - TAILLE_FLEUR / 2, TAILLE_FLEUR, TAILLE_FLEUR, null);
                            break;
                        case GrilleMod.indiceFleurJ:
                            g.drawImage(Movable.JOuv, f.getX() - TAILLE_FLEUR / 2, f.getY() - TAILLE_FLEUR / 2, TAILLE_FLEUR, TAILLE_FLEUR, null);
                            break;
                        case GrilleMod.indiceFleurV:
                            g.drawImage(Movable.VOuv, f.getX() - TAILLE_FLEUR / 2, f.getY() - TAILLE_FLEUR / 2, TAILLE_FLEUR, TAILLE_FLEUR, null);
                            break;
                    }
                } else { //si la fleur est morte
                    switch (f.getType()) { //on affiche la fleur en fonction de son type
                        case GrilleMod.indiceFleurR:
                            g.drawImage(Movable.RMor, f.getX() - TAILLE_FLEUR / 2, f.getY() - TAILLE_FLEUR / 2, TAILLE_FLEUR, TAILLE_FLEUR, null);
                            break;
                        case GrilleMod.indiceFleurJ:
                            g.drawImage(Movable.JMor, f.getX() - TAILLE_FLEUR / 2, f.getY() - TAILLE_FLEUR / 2, TAILLE_FLEUR, TAILLE_FLEUR, null);
                            break;
                        case GrilleMod.indiceFleurV:
                            g.drawImage(Movable.VMor, f.getX() - TAILLE_FLEUR / 2, f.getY() - TAILLE_FLEUR / 2, TAILLE_FLEUR, TAILLE_FLEUR, null);
                            break;
                    }

                }
                //rayon d'action des fleurs
                int sq = (int) Math.sqrt(GrilleMod.RANGE_PLACEABLE);
                switch (f.getType()){ //sélection des couleurs en fonction des fleurs
                    case GrilleMod.indiceFleurR:
                        g.setColor(Color.RED);
                        break;
                    case GrilleMod.indiceFleurJ:
                        g.setColor(Color.YELLOW);
                        break;
                    case GrilleMod.indiceFleurV:
                        g.setColor(Color.GREEN);
                        break;
                }
                g.drawOval(f.getX() - sq, f.getY() - sq, sq*2, sq*2);
            }
        }
    }

    public static void updateFleur(){
        fleurs.clear(); //enlever toutes les fleurs
        fleurs.addAll(GrilleMod.getFleurs()); //ajouter toutes les fleurs
    }
}

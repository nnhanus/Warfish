package View;

import Modele.*;

import java.awt.*;
import java.util.ArrayList;

public class VueFleur {
    public static ArrayList<Fleur> fleurs = new ArrayList<Fleur>();

    public static int TAILLE_FLEUR = 40;
  
    public VueFleur(){
        fleurs.addAll(GrilleMod.getFleurs());
    }

    /**
     * Fonction d'affichage
     */
    public static void drawFleur(Graphics g){
        for (Fleur f : fleurs) { //on itère sur le tableau des fleurs présentes sur le terrain
            if (!(f.getIsPicked())) { //si la fleur est toujours sur le terrain
                if (f.lifespan >= 450) { //si la fleur est fermée
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
                } else if (!(f.getIsDead())) {
                    switch (f.getType()) {
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
                } else {
                    switch (f.getType()) {
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
                //g.setColor(Color.BLACK);
                int sq = (int) Math.sqrt(GrilleMod.RANGE_PLACEABLE);
                switch (f.getType()){
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
        fleurs.clear();
        fleurs.addAll(GrilleMod.getFleurs());
    }
}

package View;

import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueFleur {
    public static ArrayList<Fleur> fleurs = new ArrayList<Fleur>();

    public static int TAILLE_FLEUR = 40;
  
    public VueFleur(){
        fleurs.addAll(GrilleMod.getFleurs());
    }

    public static void drawFleur(Graphics g){
        for (Fleur f : fleurs) {
            if (!(f.getIsPicked())) {
                if (f.lifespan >= 450) {
                    g.drawImage(Movable.B1, f.getX() - TAILLE_FLEUR/2, f.getY() - TAILLE_FLEUR/2, TAILLE_FLEUR, TAILLE_FLEUR, null);
                } else if (!(f.getIsDead())) {
                    g.drawImage(Movable.f1, f.getX() - TAILLE_FLEUR/2, f.getY()- TAILLE_FLEUR/2, TAILLE_FLEUR, TAILLE_FLEUR, null);
                } else {
                    g.drawImage(Movable.fan1, f.getX()- TAILLE_FLEUR/2, f.getY() - TAILLE_FLEUR/2, TAILLE_FLEUR, TAILLE_FLEUR, null);
                }
                int sq = (int) Math.sqrt(GrilleMod.RANGE_PLACEABLE);
                g.drawOval(f.getX() - sq, f.getY() - sq, sq*2, sq*2);
            }
        }
    }

    public static void updateFleur(){
        fleurs.clear();
        fleurs.addAll(GrilleMod.getFleurs());
    }
}

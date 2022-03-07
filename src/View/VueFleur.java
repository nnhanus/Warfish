package View;

import Modele.Fleur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueFleur {
    private ArrayList<Fleur> fleurs = new ArrayList<Fleur>();
    private Grille gr;

    public VueFleur(Grille grille){
        gr = grille;
        fleurs = g.getFleurs;
    }

    public void afficheFleur(Graphics g){
        g.setColor(Color.PINK);
        for (Fleur f : fleurs) {
            g.drawOval(f.x, f.y, 30, 30);
        }
    }

}

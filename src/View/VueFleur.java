package View;

import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueFleur {
    private ArrayList<Fleur> fleurs = new ArrayList<Fleur>();
    private GrilleMod gr;

    public VueFleur(GrilleMod grille){
        gr = grille;
        fleurs = gr.getFleurs();
    }

    public void afficheFleur(Graphics g){
        g.setColor(Color.PINK);
        for (Fleur f : fleurs) {
            g.drawOval(f.getX(), f.getY(), 30, 30);
        }
    }

}
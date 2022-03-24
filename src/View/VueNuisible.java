package View;

import Modele.GrilleMod;
import Modele.Nuisible;

import java.awt.*;
import java.util.ArrayList;

public class VueNuisible {
    public static ArrayList<Nuisible> nuisibles = new ArrayList<>();

    public VueNuisible(){
        nuisibles.addAll(GrilleMod.getNuisibles());
    }

    public static void drawNuisibles(Graphics g){
        for (Nuisible n : nuisibles) {
            g.drawImage(Movable.nuis, n.getX(), n.getY(), 50, 50, null);
        }
    }

    public static void updateNuisibles(){
        nuisibles.clear();
        nuisibles.addAll(GrilleMod.getNuisibles());
    }
}

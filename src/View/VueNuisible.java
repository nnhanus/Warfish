package View;

import Modele.GrilleMod;
import Modele.Nuisible;

import java.util.ArrayList;

public class VueNuisible {
    public ArrayList<Nuisible> nuisibles = new ArrayList<>();

    public VueNuisible(){
        nuisibles.addAll(GrilleMod.getNuisibles());
    }
}

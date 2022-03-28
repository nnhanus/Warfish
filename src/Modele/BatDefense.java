package Modele;

import View.BuildingView;

import java.util.ArrayList;

/**
 * Batiment de défense
 * Effraie automatiquement les nuisibles
 */
public class BatDefense extends Building {
    public static final int DEFENSE_RANGE = 19000;
    public BatDefense(int x, int y) {
        super(x, y, DEFENSE_RANGE);
        effrayer();
        BuildingView.updateBuildings(this);
    }

    public void effrayer(){
        ArrayList<Nuisible> list = new ArrayList<>();
        list.addAll(GrilleMod.getNuisibles());
        for(Nuisible n : list){
            int posX = n.getX() - this.x;
            int posY = n.getY() - this.y;

            if(posX*posX + posY*posY <= this.range){
                n.setenFuite();
            }
        }
    }
} /*Effrayer les lapins dans les cases voisines (bâtiment de défense).
    Cette action est automatique et instantanée.
    comme pour production? genre si le lapin il avance et il aarive près du bâtiment, ils ont peur?*/

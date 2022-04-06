package Modele;

import View.BuildingView;

import java.util.ArrayList;

/**
 * Batiment de défense
 * Effraie automatiquement les nuisibles
 */
public class BatDefense extends Building {
    public static final int DEFENSE_RANGE = 19000; //la portée du bâtiment

    public BatDefense(int x, int y) {
        super(x, y, DEFENSE_RANGE); //création du bâtiment
        effrayer();
        BuildingView.updateBuildings(this); //mise à jour de la vue
    }

    /**
     * Effraie les nuisibles dans son rayon d'action
     */
    public void effrayer() {
        //Récupération des nuisibles et de leurs positions
        ArrayList<Nuisible> list = new ArrayList<>();
        list.addAll(GrilleMod.getNuisibles());
        for (Nuisible n : list) {
            int posX = n.getX() - this.x;
            int posY = n.getY() - this.y;

            if (posX * posX + posY * posY <= this.range) { //le nuisible est dans le rayon d'action
                n.setenFuite(); //il est effrayé
            }
        }
    }
}

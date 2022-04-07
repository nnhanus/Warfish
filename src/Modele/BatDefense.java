package Modele;

import View.BuildingView;

import java.util.ArrayList;

/**
 * Batiment de défense
 * Effraie automatiquement les nuisibles
 */
public class BatDefense extends Building {
    public static final int DEFENSE_RANGE = 19000; //rayon d'action

    public BatDefense(int x, int y) {
        super(x, y, DEFENSE_RANGE); //création du bâtiment
        effrayer(); //effet dès qu'il est placé
        BuildingView.updateBuildings(this); //mise à jour de la vue
    }

    /**
     * effrayer
     * Set le statut enfuite des nuisibles proche à true
     */
    public void effrayer(){
        //récupération des nuisibles
        ArrayList<Nuisible> list = new ArrayList<>();
        list.addAll(GrilleMod.getNuisibles());
        for(Nuisible n : list){
            //récupération des positions
            int posX = n.getX() - this.x;
            int posY = n.getY() - this.y;

            if(posX*posX + posY*posY <= this.range){ //le nuisible est dans le rayon d'action
                n.setenFuite(); //il est effrayé
            }
        }
    }
}

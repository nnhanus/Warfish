package Modele;

import View.BuildingView;

/**
 * Bâtiment de Production
 * Augmente la production des fleurs voisines.
 */
public class BatProduction extends Building {

    public static final int PRODUCTION_RANGE = 19000; //rayon d'action

    public BatProduction(int x, int y) {
        super(x, y, PRODUCTION_RANGE); //création du batiment
        boostAllNear();
        BuildingView.updateBuildings(this); //mise a jour de l'affichage
    }

    /**
     * boostAllNear
     * Set le statut boosted des fleurs proches à true
     */
    public void boostAllNear(){
        for(Fleur f : GrilleMod.getFleurs()){ //parcours de toutes les fleurs du terrain
            //récupération position
            int posX = f.getX() - this.x;
            int posY = f.getY() - this.y;
            if(posX*posX + posY*posY <= this.range && !f.getIsDead()) { //la fleur est en vie et dans le rayon d'action
                f.boost(); //la fleur est boostée
            }
        }
    }
}

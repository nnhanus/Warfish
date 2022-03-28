package Modele;

import View.BuildingView;

/**
 * Bâtiment de Production
 * Augmente la production des fleurs voisines
 * Cette action est automatique et instantanée.
 */
public class BatProduction extends Building {
    public static final int PRODUCTION_RANGE = 19000;
    public BatProduction(int x, int y) {
        super(x, y, PRODUCTION_RANGE);
        boostAllNear();
        BuildingView.updateBuildings(this);
    }

    /**
     * boostAllNear
     * Set le statut boosted des fleurs proches à true
     */
    public void boostAllNear(){
        for(Fleur f : GrilleMod.getFleurs()){
            int posX = f.getX() - this.x;
            int posY = f.getY() - this.y;
            if(posX*posX + posY*posY <= this.range && !f.getIsDead()) {
                f.boost();
            }
        }
    }
}

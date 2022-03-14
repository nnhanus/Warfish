package Modele;

/**
 * Bâtiment de Production
 * Augmente la production des fleurs voisines
 */
class BatProduction extends Building {
    public BatProduction(int x, int y) {
        super(x, y, 7);
        boostAllNear();
    }

    //TODO
    /*Augmenter la production des fleurs dans les cases voisines (bâtiment de production).
    Cette action est automatique et instantanée.
    Je pense que ça devrait être gérer dans les cases ou les fleurs peut-être?*/

    /**
     * boostAllNear
     * Set le statut boosted des fleurs proches à true
     */
    public void boostAllNear(){
        for(Fleur f : GrilleMod.getFleurs()){
            int posX = f.getX() - this.x;
            int posY = f.getY() - this.y;
            if(posX*posX + posY*posY <= this.range*this.range) {
                f.boost();
            }
        }
    }
}

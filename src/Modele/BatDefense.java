package Modele;

/**
 * Batiment de défense
 * Effraie automatiquement les nuisibles
 */
public class BatDefense extends Building {
    public BatDefense(int x, int y) {
        super(x, y, 19000);
        effrayer();
    }

    public void effrayer(){
        for(Nuisible n : GrilleMod.getNuisibles()){
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

package Modele;

/**
 * Building
 * Classe-mère pour les différents bâtiments
 */
public class Building {
    protected final int x, y; //position
    protected final int range; //définie le rayon dans lequel les "capacités" du bâtiment sont efficaces

    public Building(int x, int y, int range) {
        //Initialise la position
        this.x = x;
        this.y = y;
        //Définie le rayon d'action
        this.range = range;

    }

    /**
     * Renvoie l'abscisse du bâtiment
     */
    public int getX(){
        return this.x;
    }

    /**
    * Renvoie l'ordonnée du bâtiment
    */
    public int getY(){
        return this.y;
    }

    /**
     * Renvoie le rayon d'action du bâtiment
     */
    public int getRange(){
        return this.range;
    }
}

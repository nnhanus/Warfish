package Modele;

import java.sql.Array;
import java.util.ArrayList;

public class Building {
    public int x, y;

    public Building(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class BatPrincipal extends Building{
    private static int tirelire = 200;
    public static final int PRIX_JARD = 50;
    private ArrayList<Ressource> ressources = new ArrayList<>();

    public BatPrincipal(int x, int y){
        super(x,y);
    }

    /**
     * @return la tirelire
     */
    public int getTirelire(){
        return tirelire;
    }

    /**
     * Modifie tirelire
     * @param x la nouvelle balance
     */
    public void setTirelire(int x){
        tirelire = x;
    }

    /**
     * Créer un nouveau jardinier sur la case du bâtiment.
     * Prend du temps.
     */
    public void creeJard(){
        tirelire -= PRIX_JARD;
        //cree un jardinnier
        //et attendre un temps
    }

    /**
     * Achete une graine contre de l'argent.
     * Instantanée.
     */
    public void acheteGrain(){
    }

    //public void
       /* Collecter les fleurs d’un jardinier lorsqu’il arrive sur le bâtiment (bâtiment principal). Cette action est automatique et instantanée.
        Vendre les bouquets réalisés par le jardinier (bâtiment principal). Cette action est automatique et instantanée.
        */
}

class BatProduction extends Building{
    public BatProduction(int x, int y){
        super(x,y);
    }

    //TODO
    /*Augmenter la production des fleurs dans les cases voisines (bâtiment de production). Cette action est automatique et instantanée.*/
}

class BatDefense extends Building{
    public BatDefense(int x, int y){
        super(x,y);
    }
    //TODO
} /*Effrayer les lapins dans les cases voisines (bâtiment de défense). Cette action est automatique et instantanée.*/


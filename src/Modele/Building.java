package Modele;

import java.util.ArrayList;

public class Building {
    public int x, y;

    public Building(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/**
 * Bâtiment Principal
 * Gère les ressources générales, et notamment l'argent/score
 */
class BatPrincipal extends Building{
    private static int tirelire = 200;
    public static final int PRIX_JARD = 50;
    private int[] ressources = new int[1]; //pour l'instant

    public BatPrincipal(int x, int y){
        super(x,y);
    }

    /**
     * @return la tirelire
     */
    public static int getTirelire(){
        return tirelire;
    }

    /**
     * Modifie tirelire
     * @param x la nouvelle balance
     */
    public static void setTirelire(int x){
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
    public void acheteGrain(Jardinier p, Fleur f){
        if (f.prix <= tirelire) {
            p.getInventaire()[0]++;
            tirelire -= f.prix;
        }
        //on peut ajouter un message "pas assez d'argent"
    }

    /**
     * Vide l'inventaire d'un paysan et le transfert dans l'inventaire du bâtiment
     * Automatique, instantané
     * @param p un paysan
     */
    public void collecteFleur(Jardinier p){
        //copie toutes les ressources du paysan dans le bâtiment
        for (int i = 0; i < p.getInventaire().length; i++){
            ressources[i] = p.getInventaire()[i];
        }
        //vide l'inventaire du paysan
        p.videInventaire();
    }

    /**
     * Vend les bouquets composés par un jardinier
     * Automatique, instanté
     */
    public void vendBouquet(){
        /*
       Il faut que j'ai la fonction du pecno pour faire les bouquets
        */
        //TODO
    }
}

/**
 * Bâtiment de Production
 * Augmente la production des fleurs voisines
 */
class BatProduction extends Building{
    public BatProduction(int x, int y){
        super(x,y);
    }

    //TODO
    /*Augmenter la production des fleurs dans les cases voisines (bâtiment de production).
    Cette action est automatique et instantanée.
    Je pense que ça devrait être gérer dans les cases ou les fleurs peut-être?*/
}

/**
 * Batiment de défense
 * Effraie automatiquement les nuisibles
 */
class BatDefense extends Building{
    public BatDefense(int x, int y){
        super(x,y);
    }
    //TODO
} /*Effrayer les lapins dans les cases voisines (bâtiment de défense).
    Cette action est automatique et instantanée.
    comme pour production? genre si le lapin il avance et il aarive près du bâtiment, ils ont peur?*/


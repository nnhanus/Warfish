package Modele;
abstract class Ressource extends Thread{ //à terme plusieurs types de ressources
    protected int prix;
    public int id;

    public abstract boolean isPickable();
}





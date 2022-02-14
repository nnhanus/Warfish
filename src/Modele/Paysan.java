package Modele;

import java.util.Arrays;

public class Paysan extends Unite{
    public int[] inventaire = new int[1]; //pour l'instant

    public Paysan(int x, int y){
        super(x, y);
        vitesse = 100;
        Arrays.fill(inventaire, 0);
    }

    /**
     * planteFleur
     * Le paysan plante une fleur sur une case proche
     */
    public void planteFleur() {
        this.inventaire[0]--;
    }

    /**
     * recolterRessources
     * recolte une certaine quantité d'une ressource
     * @param r la ressource récolter
     * @param amount la quantité à mettre dans l'inventaire du paysan
     */
    public void recolterRessource(Ressource r, int amount) {
        this.inventaire[r.id] += amount;
    }

    /**
     * acheterGraine
     * Achete une(des) graines
     * @param amount la quantité acheter
     */
    public void acheterGraine(int amount) {
        BatPrincipal.setTirelire(BatPrincipal.getTirelire()-amount*Fleur.prix);
    }

    /**
     * vendre
     * Vend une certaine quantité d'une ressource
     * @param r la ressource à vendre
     * @param amount la quantité à vendre
     */
    public void vendre(Ressource r, int amount) { //augmenter
        BatPrincipal.setTirelire(BatPrincipal.getTirelire()+amount*Fleur.prix);
        this.inventaire[r.id] -= amount;
    }

    /*public void effrayer(){

    }*/
}

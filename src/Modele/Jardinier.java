package Modele;

import java.util.Arrays;

public class Jardinier extends Unite{
    private int[] inventaire = new int[1]; //pour l'instant

    public Jardinier(int x, int y){
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
    public void acheterGraine(int amount, Fleur f) {
        BatPrincipal.setTirelire(BatPrincipal.getTirelire()-amount*f.getPrix());
    }

    /**
     * vendre
     * Vend une certaine quantité d'une ressource
     * @param f la ressource à vendre
     * @param amount la quantité à vendre
     */
    public void vendre(int amount, Fleur f) { //augmenter
        BatPrincipal.setTirelire(BatPrincipal.getTirelire()+amount*f.getPrix());
        this.inventaire[f.id] -= amount;
    }

    /**
     * getInventaire
     * renvoie l'inventaire du jardinier
     * @return l'inventaire du jardinier
     */
    public int[] getInventaire(){
        return this.inventaire;
    }

    /**
     * videInventaire
     * vide l'inventaire
     */
    public void videInventaire(){
        Arrays.fill(inventaire, 0);
    }

    /*public void effrayer(){

    }*/

    /**
     * getRelevantCase
     * @return la case sur laquelle le lapin se trouve
     */
    public Case getRelevantCase(){
        return GrilleMod.plateau.get(this.x%GrilleMod.TAILLE_CASE).get(this.y%GrilleMod.TAILLE_CASE);
    }
}

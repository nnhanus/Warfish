package Modele;

import java.util.Arrays;

public class Jardinier extends Unite{
    private int[] inventaire = new int[5];
    //fleur graine bouquet bois pierre, in this order I guess

    public Jardinier(int x, int y){
        super(x, y);
        vitesse = 100;
        Arrays.fill(inventaire, 0);
    }

    /**
     * planteFleur
     * Le paysan plante une fleur sur la grille
     */
    public void planteFleur() {
        this.inventaire[0]--;
        GrilleMod.addFleur(new Fleur(this.x, this.y));
    }

    /**
     * recolterRessources
     * recolte une certaine quantité d'une ressource
     * @param r la ressource récolter
     */
    public void recolterRessource(Ressource r) {
            if(r.getClass() == Fleur.class){
                GrilleMod.removeFleur((Fleur) r);
            }
            this.inventaire[r.getid()] += r.getAmount();
    }

    public void desherber(Fleur r) {
        GrilleMod.removeFleur(r);
    }

    /**
     * plusProcheRessource
     * Renvoie la ressource la plus proche du Jardiner
     * @return une ressource
     */
    public Ressource plusProcheRessource(){
        Ressource nearest = null;
        for(Ressource r : GrilleMod.getRessources()){
            if(nearest == null){
                nearest = r;
            }else{
                if(getSQDistFrom(r.getX(), r.getY()) < getSQDistFrom(nearest.getX(), nearest.getY())){
                    nearest = r;
                }
            }
        }
        return nearest;
    }

    /**
     * acheterGraine
     * Achete une(des) graines
     * @param amount la quantité achetée
     */
    public void acheterGraine(int amount) {
        this.inventaire[1] += amount;
    }

    /**
     * vendre
     * Vend une certaine quantité d'une ressource
     * @param id l'indice de la ressource à vendre
     * @param amount la quantité à vendre
     */
    public void vendre(int amount, int id) {
        this.inventaire[id] -= amount;
    }

    /**
     * confectionneBouquet
     * Confectionne un bouquet à partir de 3 fleurs et le range dans l'inventaire
     */
    public void confectionneBouquet(){
        this.inventaire[0] -= 3;
        this.inventaire[2] += 1;
    }

    /**
     * vendBouquet
     * Vend tous les bouquets du jardinier
     */
    public void vendBouquet(){
        GrilleMod.getBatPrincipal().vendRessource(this.inventaire[2], 2);
        this.inventaire[2] = 0;
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

    /**
     * construitBatDefense
     * Enleve les ressources associées au coût d'un bâtiment de défense de l'inventaire
     */
    public void construitBatDefense(){
        /*this.inventaire[3] -= 5;
        this.inventaire[4] -= 15;*/
        BatPrincipal.setTirelire(BatPrincipal.getTirelire() - BatPrincipal.PRIX_DEFENSE);
        GrilleMod.addBatiment(new BatDefense(this.x, this.y));
    }

    /**
     * construitBatProduction
     * Enleve les ressources associées au coût d'un bâtiment de production de l'inventaire
     */
    public void construitBatProduction(){
        /*this.inventaire[3] -= 15;
        this.inventaire[4] -= 5;*/
        BatPrincipal.setTirelire(BatPrincipal.getTirelire() - BatPrincipal.PRIX_PRODUCTION);
        GrilleMod.addBatiment(new BatProduction(this.x, this.y));
    }

    /**
     * effrayer
     * Effraie tous les nuisibles proches
     */
    public void effrayer(){
        for(Nuisible n : GrilleMod.getNuisibles()){
            int posX = n.getX() - this.x;
            int posY = n.getY() - this.y;

            if(posX*posX + posY*posY <= 1000000){
                n.setenFuite();
            }
        }
    }
}

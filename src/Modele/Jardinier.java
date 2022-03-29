package Modele;

import View.BuildingView;
import View.Grille;
import View.JardinierView;
import View.Movable;

import java.util.ArrayList;
import java.util.Arrays;

public class Jardinier extends Unite{
    private int[] inventaire = new int[7];
    //cf GrilleMod pour les indices

    public Jardinier(int x, int y){
        super(x, y);
        vitesse = 100;
        Arrays.fill(inventaire, 0);
    }

    /**
     * planteFleur
     * Le paysan plante une fleur sur la grille
     */
    public void planteFleur(int id) {
        this.inventaire[id]--;
        switch (id) {
            case 3:
                GrilleMod.addFleur(new Fleur(this.x, this.y, 0));
                break;
            case 4:
                GrilleMod.addFleur(new Fleur(this.x, this.y, 1));
                break;
            case 5:
                GrilleMod.addFleur(new Fleur(this.x, this.y, 2));
        }
    }

    /**
     * recolterRessources
     * recolte une certaine quantité d'une ressource
     * @param f la ressource récolter
     */
    public void recolterRessource(Fleur f) {
        GrilleMod.removeFleur(f);
        this.inventaire[f.getType()] += f.getAmount();
    }

    public void desherber(Fleur r) {
        GrilleMod.removeFleur(r);
    }

    /**
     * plusProcheFleur
     * Renvoie la fleur la plus proche du Jardiner
     * @return une fleur
     */
    public Fleur plusProcheFleur(){
        Fleur nearest = null;
        for(Fleur f : GrilleMod.getFleurs()){
            if(nearest == null){
                nearest = f;
            }else{
                if(getSQDistFrom(f.getX(), f.getY()) < getSQDistFrom(nearest.getX(), nearest.getY())){
                    nearest = f;
                }
            }
        }
        return nearest;
    }

    /**
     * acheterGraine
     * Achete une(des) graines
     */
    public void acheterGraine(int id) {
        this.inventaire[id]++;
        System.out.println("bought");
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
        this.inventaire[GrilleMod.indiceFleurR] -= 3;
        this.inventaire[GrilleMod.indiceBouquet] += 1;
    }

    /**
     * vendBouquet
     * Vend tous les bouquets du jardinier
     */
    public void vendBouquet(){
        GrilleMod.getBatPrincipal().vendRessource();
        this.inventaire[GrilleMod.indiceBouquet] = 0;
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
        BatDefense b = new BatDefense(this.x, this.y);
        GrilleMod.addBatiment(b);
        System.out.println("BatDefense Added");
        BuildingView.updateBuildings(b);
    }

    /**
     * construitBatProduction
     * Enleve les ressources associées au coût d'un bâtiment de production de l'inventaire
     */
    public void construitBatProduction(){
        /*this.inventaire[3] -= 15;
        this.inventaire[4] -= 5;*/
        BatPrincipal.setTirelire(BatPrincipal.getTirelire() - BatPrincipal.PRIX_PRODUCTION);
        BatProduction b = new BatProduction(this.x, this.y);
        GrilleMod.addBatiment(b);
        System.out.println("BatProduction Added");

    }

    /**
     * effrayer
     * Effraie tous les nuisibles proches
     */
    public void effrayer(){
        ArrayList<Nuisible> nlist = new ArrayList<>();
        nlist.addAll(GrilleMod.getNuisibles());
        for(Nuisible n : nlist){
            int posX = n.getX() - this.x;
            int posY = n.getY() - this.y;

            if(posX*posX + posY*posY <= 19000){
                n.setenFuite();
            }
        }
    }
}

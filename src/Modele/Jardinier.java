package Modele;

import View.BuildingView;

import java.util.ArrayList;
import java.util.Arrays;

public class Jardinier extends Unite{
    private int[] inventaire = new int[] {0,0,0,0,0,0};
    //cf GrilleMod pour les indices

    public Jardinier(int x, int y){
        super(x, y);
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
        synchronized (GrilleMod.key) {
            GrilleMod.removeFleur(f);
        }
        this.inventaire[f.getType()] += f.getAmount();
    }

    public void desherber(Fleur r) {
        synchronized (GrilleMod.key){GrilleMod.removeFleur(r);}
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
    }

    public void useFlower(int id){
        this.inventaire[id]--;
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
        BatPrincipal.setTirelire(BatPrincipal.getTirelire() - BatPrincipal.PRIX_DEFENSE);
        BatDefense b = new BatDefense(this.x, this.y);
        GrilleMod.addBatiment(b);
        BuildingView.updateBuildings(b);
    }

    /**
     * construitBatProduction
     * Enleve les ressources associées au coût d'un bâtiment de production de l'inventaire
     */
    public void construitBatProduction(){
        BatPrincipal.setTirelire(BatPrincipal.getTirelire() - BatPrincipal.PRIX_PRODUCTION);
        BatProduction b = new BatProduction(this.x, this.y);
        GrilleMod.addBatiment(b);
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

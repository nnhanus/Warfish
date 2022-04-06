package Modele;

import View.BuildingView;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.*;

/**
 * Classe implémentant les jardiniers (=méduses)
 */
public class Jardinier extends Thread{
    private int[] inventaire = new int[7]; //inventaire, cf GrilleMod pour les indices
    protected int x, y; //les coordonnées de l'unité
    protected int vitesse; //la vitesse de l'unité, définir une valeur globale en fonction de l'unité en question
    protected boolean immobile = true;
    protected int targX; //Les coordonnées (x) ciblées par l'unité
    protected int targY; //Les coordonnées (y) ciblées par l'unité
    protected double dir; //La direction à suivre pour atteindre la cible


    /**
     * Constructeur
     * @param x l'abscisse du jardinier
     * @param y l'ordonnée du jardinier
     */
    public Jardinier(int x, int y){
        //coordonnées du jardinier
        this.x = x;
        this.y = y;
        //coordonnées visées par le jardinier
        this.targX = x;
        this.targY = y;
        vitesse = 100; //vitesse
        Arrays.fill(inventaire, 0); //remplissage de l'inventaire
        this.start(); //lancement du thread
    }

    /**
     * getX
     * @return l'abscisse x de l'unité
     */
    public int getX() {
        return this.x;
    }

    /**
     * getY
     * @return l'ordonnée y de l'unité
     */
    public int getY() {
        return this.y;
    }

    /**
     * getVitesse
     * @return la vitesse de l'unité
     */
    public int getVitesse() {
        return this.vitesse;
    }

    /**
     * getTargX
     * @return la cible (x) de l'unité
     */
    public int getTargX() {
        return this.targX;
    }

    /**
     * getTargY
     * @return la cible (y) de l'unité
     */
    public int getTargY() {
        return this.targY;
    }


    /**
     * planteFleur
     * Le paysan plante une fleur sur la grille
     */
    public void planteFleur(int id) {
        this.inventaire[id]--; //perd la graine qu'il plante
        switch (id) { //récupération du type de la fleur
            case GrilleMod.indiceGraineR: //la graine est rouge
                GrilleMod.addFleur(new Fleur(this.x, this.y, GrilleMod.indiceFleurR)); //plante une fleur rouge
                break;
            case GrilleMod.indiceGraineJ: //la graine est jaune
                GrilleMod.addFleur(new Fleur(this.x, this.y, GrilleMod.indiceFleurJ)); //plante une fleur jaune
                break;
            case GrilleMod.indiceGraineV: //la graine est verte
                GrilleMod.addFleur(new Fleur(this.x, this.y, GrilleMod.indiceFleurV)); //plante une fleur verte
        }
    }

    /**
     * recolterRessources
     * recolte une certaine quantité d'une ressource
     * @param f la ressource récolter
     */
    public void recolterRessource(Fleur f) {
        GrilleMod.removeFleur(f); //fleur enlevée du terrain
        this.inventaire[f.getType()] += f.getAmount(); //ajout à l'inventaire du jardinier
    }

    /**
     * Désherbe une fleur
     * @param r la fleur à désherber
     */
    public void desherber(Fleur r) {
        GrilleMod.removeFleur(r); //fleur enlevée du terrain
    }

    /**
     * plusProcheFleur
     * Renvoie la fleur la plus proche du Jardiner
     * @return une fleur
     */
    public Fleur plusProcheFleur(){
        Fleur nearest = null;
        for(Fleur f : GrilleMod.getFleurs()){ //parcours des fleurs du terrain
            if(nearest == null){ //on récupère la première fleur dans le tableau
                nearest = f;
            }else{
                //comparaison de la nearest actuelle avec la fleur parcourue
                //si la fleur parcourue est plus proche, elle devient la nearest
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
        this.inventaire[id]++; //ajout d'une graine à l'invantaire
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
        BatPrincipal.setTirelire(BatPrincipal.getTirelire() - BatPrincipal.PRIX_DEFENSE); //perd le prix d'un bâtiment
        BatDefense b = new BatDefense(this.x, this.y); //création d'un nouveau bâtiment aux coordonnées du jardinier
        GrilleMod.addBatiment(b); //ajout du bâtiment au terrain
    }

    /**
     * construitBatProduction
     * Enleve les ressources associées au coût d'un bâtiment de production de l'inventaire
     */
    public void construitBatProduction(){
        BatPrincipal.setTirelire(BatPrincipal.getTirelire() - BatPrincipal.PRIX_PRODUCTION); //perd le prix d'un bâtiment
        BatProduction b = new BatProduction(this.x, this.y); //création d'un nouveau bâtiment aux coordonnées du jardinier
        GrilleMod.addBatiment(b); //ajout du bâtiment au terrain
    }

    /**
     * effrayer
     * Effraie tous les nuisibles proches
     */
    public void effrayer(){
        //récupération des nuisibles du terrain
        ArrayList<Nuisible> nlist = new ArrayList<>();
        nlist.addAll(GrilleMod.getNuisibles());
        for(Nuisible n : nlist){
            //Récupération de la position
            int posX = n.getX() - this.x;
            int posY = n.getY() - this.y;

            if(posX*posX + posY*posY <= 19000){ //le nuisible est dans le rayon du jardinier
                n.setenFuite(); //il est effrayé
            }
        }
    }


    /**
     * getSQDistFrom
     * Retourne le carré de la distance entre l'unite et un point
     * @param x
     * @param y
     * @return un entier
     */
    public int getSQDistFrom(int x, int y) {
        return (this.x - x) * (this.x - x) + (this.y - y) * (this.y - y);
    }

    /**
     * setDir
     * Défini automatiquement la direction à suivre en radian
     */
    public void setDir() {
        int posX = targX - x;
        int posY = targY - y;
        this.dir = atan2(posX, posY) - PI/2.0;
    }

    /**
     * avance
     * fait avancer l'unité vers sa cible
     */
    public void avance() {
        this.x  += cos(dir)*1.6;
        this.y -= sin(dir)*1.6;
        this.setDir();
    }

    /**
     * setMoving
     * Désigne une cible pour l'unité
     * @param x l'abscisse de la cible
     * @param y l'ordonnée de la cible
     */
    public void setMoving(int x, int y) {
        this.targX = x;
        this.targY = y;
        this.setDir();
    }

    @Override
    public void run() {
        int posX;
        int posY;
        while (true) {
            posX = targX - x;
            posY = targY - y;
            if(posX*posX + posY*posY > 125) {
                this.avance();
            }
            try {
                sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

package Modele;

import View.BuildingView;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.*;
import static java.lang.Math.sin;

public class Jardinier extends Thread{
    protected int x, y; //les coordonnées de l'unité
    protected boolean immobile = true;
    protected int targX; //Les coordonnées (x) ciblées par l'unité
    protected int targY; //Les coordonnées (y) ciblées par l'unité
    protected double dir; //La direction à suivre pour atteindre la cible
    private int[] inventaire = new int[] {5,5,5,0,0,0};
    //cf GrilleMod pour les indices

    public Jardinier(int x, int y){
        //coordonnées
        this.x = x;
        this.y = y;
        //coordonnées visées
        this.targX = x;
        this.targY = y;
        this.start();
    }

    /*getters*/

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
        this.dir = atan2(posX, posY) - PI/2.0; //donne l'angle entre la droite tracée par 2 points,et le degré 0 dans le plan
    }

    /**
     * avance
     * fait avancer l'unité vers sa cible
     */
    public void avance() {
        //avance en fonction de la direction
        this.x += cos(dir)*1.6;
        this.y -= sin(dir)*1.6;
        this.setDir(); //mise à jour de la direction
    }

    /**
     * setMoving
     * Désigne une cible pour l'unité
     * @param x l'abscisse de la cible
     * @param y l'ordonnée de la cible
     */
    public void setMoving(int x, int y) {
        //Définition de la cible
        this.targX = x;
        this.targY = y;
        this.setDir(); //mise à jour de la direction
    }


    /**
     * planteFleur
     * Le paysan plante une fleur sur la grille
     */
    public void planteFleur(int id) {
        this.inventaire[id]--; //perd une graine
        switch (id) { //récupération du type de fleur à planter
            case GrilleMod.indiceGraineR :
                GrilleMod.addFleur(new Fleur(this.x, this.y, GrilleMod.indiceFleurR));
                break;
            case GrilleMod.indiceGraineJ :
                GrilleMod.addFleur(new Fleur(this.x, this.y, GrilleMod.indiceFleurJ));
                break;
            case GrilleMod.indiceGraineV :
                GrilleMod.addFleur(new Fleur(this.x, this.y, GrilleMod.indiceFleurV));
        }
    }

    /**
     * recolterRessources
     * recolte une certaine quantité d'une ressource
     * @param f la ressource récolter
     */
    public void recolterRessource(Fleur f) {
        synchronized (GrilleMod.key) {
            GrilleMod.removeFleur(f); //retirer la fleur
        }
        this.inventaire[f.getType()] += f.getAmount(); //mise à jour de l'inventaire
    }

    /**
     * désherber une fleur
     * @param r la fleur à enlever
     */
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
        for(Fleur f : GrilleMod.getFleurs()){ //parcours des fleurs
            if(nearest == null){ //récupération de la première fleur
                nearest = f;
            }else{
                if(getSQDistFrom(f.getX(), f.getY()) < getSQDistFrom(nearest.getX(), nearest.getY())){ //comparaision des distances
                    nearest = f; //la fleur courante est plus proche que la nearest, elle devient la nearest
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

    /**
     * Utiliser une fleur pour confectionner un bouquet
     * @param id le type de fleur à utiliser
     */
    public void useFlower(int id){
        this.inventaire[id]--;
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
     * construitBatDefense
     * Enleve les ressources associées au coût d'un bâtiment de défense de l'inventaire
     */
    public void construitBatDefense(){
        BatPrincipal.setTirelire(BatPrincipal.getTirelire() - BatPrincipal.PRIX_DEFENSE); //déduction du prix
        BatDefense b = new BatDefense(this.x, this.y); //construction bâtiment
        GrilleMod.addBatiment(b); //ajout au terrain
    }

    /**
     * construitBatProduction
     * Enleve les ressources associées au coût d'un bâtiment de production de l'inventaire
     */
    public void construitBatProduction(){
        BatPrincipal.setTirelire(BatPrincipal.getTirelire() - BatPrincipal.PRIX_PRODUCTION); //déduction du prix
        BatProduction b = new BatProduction(this.x, this.y); //construction bâtiment
        GrilleMod.addBatiment(b); //ajout au terrain
    }

    /**
     * effrayer
     * Effraie tous les nuisibles proches
     */
    public void effrayer(){
        //récupération des nuisibles
        ArrayList<Nuisible> nlist = new ArrayList<>();
        nlist.addAll(GrilleMod.getNuisibles());
        for(Nuisible n : nlist){ //parcours
            //récupération positions
            int posX = n.getX() - this.x;
            int posY = n.getY() - this.y;

            if(posX*posX + posY*posY <= 19000){ //se trouve dans le rayon d'action
                n.setenFuite(); //effrayé
            }
        }
    }

    @Override
    public void run() {
        int posX;
        int posY;
        while (true) {
            posX = targX - x;
            posY = targY - y;
            if(posX*posX + posY*posY > 125) { //environ 15 pixels
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

package Modele;

import java.util.Arrays;

import static java.lang.Math.*;
import static java.lang.Math.PI;

public class Laquais extends Thread {
    private int[] inventaire = new int[]{0,0,0}; //inventaire de fleur
    private int x; //coordonnée en X
    private int y; //coordonée en y
    private Fleur target = null; //cible du laquais
    private double dir; //direction vers la cible
    private final Jardinier proprio; //le propriétaire du laquais
    private boolean retour = false; //le phase du laquais, true si il revient au propriétaire, false si il cherche une fleur

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Fleur getTarget(){return target;}

    public Laquais(Jardinier j){
        proprio = j;
        x = GrilleMod.getBatX();
        y = GrilleMod.getBatY();
        start();
    }
    /**
     * avanceNuisible
     * Fait avancer le laquais dans la direction dir
     */
    public void avanceLaquais(){
            //avance en fonction de la direction
            this.x += cos(dir) * 1.6;
            this.y -= sin(dir) * 1.6;
            //l'écart entre le laquais et sa cible
            int posX;
            int posY;
            if (retour) { //disjonction de cas retour / cueillette
                //mise à jour direction
                posX = proprio.getX() - this.x;
                posY = proprio.getY() - this.y;
                this.dir = atan2(posX, posY) - PI / 2.0; //angle entre le laquais et sa cible
            } else if (target != null){
                //mise à jour direction
                posX = target.getX() - this.x;
                posY = target.getY() - this.y;
                this.dir = atan2(posX, posY) - PI / 2.0; //angle entre le laquais et sa cible
            }
    }

    /**
     * mangeFleur
     * fait disparaître la fleur cible
     */
    public void ramasseFleur(){

        System.out.println("Type: " + target.getType() + ", Amount : " + target.getAmount());
        this.inventaire[target.getType()] += target.getAmount(); //mise à jour de l'inventaire
        synchronized (GrilleMod.key) {
            GrilleMod.removeFleur(target); //retirer la fleur
        }
        retour = true;
        this.removeTarget();
        setDir();
    }

    public void removeTarget(){
        this.target = null;
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
     * acquireTarget
     * donne la fleur la plus proche comme cible au laquais
     */
    public void acquireTarget(){
        for(Fleur f : GrilleMod.getFleurs()){ //parcours des fleurs
            if(f != null && f.isPickable()) { //on s'intéresse aux fleurs fleuries
                if (this.target == null) {
                    this.target = f;
                } else {
                    //les coordonnées relatives à la fleur f
                    int posX = f.getX() - this.x;
                    int posY = f.getY() - this.y;

                    //les coordonnées relatives à la cible
                    int targX = this.target.getX() - this.x;
                    int targY = this.target.getY() - this.y;
                    if (posX * posX + posY * posY < targX * targX + targY * targY) { //comparaison des distances
                        this.target = f;
                    }
                }
            }
        }
        //pas de fleur respectant les conditions
        //définition d'une direction par défaut (centre de la grille)
        if(target != null) {
            int posX = target.getX() - x;
            int posY = target.getY() - y;
            this.dir = atan2(posX, posY) - PI/2.0;
        }
    }
     /** plusProcheFleur
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
     * setDir
     * Défini automatiquement la direction à suivre en radian
     */
    public void setDir() {
        //la position relative du laquais et de sa cible
        int posX;
        int posY;
        if(retour){
            posX = proprio.getX() - x;
            posY =  proprio.getY() - y;
        }else if(target != null){
            posX = target.getX() - x;
            posY = target.getY() - y;
        }else{
            posX = GrilleMod.getBatX() - x;
            posY = GrilleMod.getBatY() - y;
        }
        this.dir = atan2(posX, posY) - PI/2.0; //donne l'angle entre la droite tracée par 2 points,et le degré 0 dans le plan
    }

    /**
     * nearTarget
     * Indique si le laquais est suffisamment proche de sa cible
     * @return true si le laquais est assez proche de sa cible, false sinon
     */
    public boolean nearTarget(){
        //distance à la fleur
        int posX = target.getX() - this.x;
        int posY = target.getY() - this.y;
        return posX*posX + posY*posY <= 16;
    }

    /**
     * nearProprio
     * Indique si le laquais est suffisamment proche de son propriétaire
     * @return true si le laquais est assez proche de son propriétaire, false sinon
     */
    public boolean nearProprio(){
        //distance au propriétaire
        int posX = proprio.getX() - this.x;
        int posY = proprio.getY() - this.y;
        return posX*posX + posY*posY <= 50;
    }

    public void videInventaire(){
        proprio.recupererCadeau(this.inventaire); //ajout de son inventaire à celui du propriétaire
        Arrays.fill(inventaire,  0); //remise à vide
    }

    @Override
    public void run(){
        while(true){
            if(retour){
                if(nearProprio()){ //à portée de son propriétaire
                    videInventaire(); //donne son inventaire au propriétaire
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    retour = false; //passe en mode cueillette
                    synchronized (GrilleMod.key){acquireTarget();}
                    setDir(); //nouvelle direction
                }else{
                    avanceLaquais(); //avance vers le proprio
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                if(target != null && !target.isDead && !target.isPicked) {
                    if (nearTarget()) { //si le lapin est proche de sa cible il la mange
                        synchronized (GrilleMod.key){ramasseFleur();}
                        this.removeTarget(); //cible devient vide
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else if(/*target != null &&*/ target.isPickable()){ //sinon si la cible est ready
                        this.avanceLaquais();
                        try {
                            sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }else{ //si il n'a pas de cible
                    acquireTarget(); //assignation d'une cible
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}

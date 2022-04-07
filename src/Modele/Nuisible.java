package Modele;

import View.VueNuisible;
import static java.lang.Math.*;

/**
 * Nuisible
 * Classe gérant les actions, affectations, et le comportement des nuisibles
 */
public class Nuisible extends Thread{
    private int x, y; //position
    private double dir; //la direction du lapin en degrés
    private boolean enfuite = false;
    private Fleur target = null; //cible


    public Nuisible(int x, int y) {
        //position
        this.x = x;
        this.y = y;
        this.acquireTarget(); //cible
        VueNuisible.updateNuisibles(); //mise à jour affichage
        this.start(); //lancemand du thread
    }

    /*
    Getters
     */
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public double getDir(){
        return this.dir;
    }

    public boolean getStatus(){
        return this.enfuite;
    }

    public Fleur getTarget(){
        return this.target;
    }

    /**
     * avanceNuisible
     * Fait avancer le lapin dans la direction dir
     */
    public void avanceNuisible(){
        //avance en fonction de la direction
        if(target != null) {
            this.x += cos(dir) * 1.6;
            this.y -= sin(dir) * 1.6;
            //mise à jour direction
            int posX = target.getX() - this.x;
            int posY = target.getY() - this.y;
            this.dir = atan2(posX, posY) - PI / 2.0; //angle entre le lapin et sa cible
        }
    }

    /**
     * mangeFleur
     * fait disparaître la fleur cible
     */
    public void mangeFleur(){
        GrilleMod.removeFleur(this.target);
    }

    /**
     * setenFuite
     * Fait passer le lapin en état de fuite
     */
    public void setenFuite(){
        this.enfuite = true; //effrayé
        GrilleMod.removeNuisible(this); //enlever de la grille
        VueNuisible.updateNuisibles(); //mise à jour affichage
    }

    /**
     * isValidPosition
     * Détermine si la position du lapin est valide pour l'apparition (il ne va pas fuire immédiatement)
     * @return true si la position est valide, false sinon
     */
    public static boolean isNotValidPosition(int x, int y){
        for(Building b : GrilleMod.getBuildings()){ //parcours des bâtiments
            if(b.getClass() == BatDefense.class){ //on s'intéresse aux bâtiments de défense
                //distance au bâtiment
                int posX = b.getX() - x;
                int posY = b.getY() - y;
                if(posX*posX + posY*posY <= b.getRange()){ //dans le rayon d'action
                    return true; //position non-valide
                }
            }
        }
        return false; //position valide
    }

    /**
     * acquireTarget
     * donne la fleur la plus proche comme cible au lapin
     */
    public /*synchronized*/ void acquireTarget(){
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
        }else{
            int posX = GrilleMod.LARGEUR_GRILLE/2 - x;
            int posY = GrilleMod.HAUTEUR_GRILLE/2 - y;
            this.dir = atan2(posX, posY) - PI/2.0;
        }
    }

    /**
     * nearTarget
     * Indique si le nuisible est suffisamment proche de sa cible
     * @return true si le nuisible est assez proche de sa cible, false sinon
     */
    public boolean nearTarget(){
        //distance à la fleur
        int posX = target.getX() - this.x;
        int posY = target.getY() - this.y;
        return posX*posX + posY*posY <= 16;
    }

    /**
     * run
     * thread décrivant le comportement du nuisible :
     * Se dirige en ligne droite vers la fleur la plus proche (définie à l'appirition du lapin, ou quand sa cible est enlevé du terrain)
     */
    @Override
    public void run(){
        while(!enfuite){
            if(target != null) {
                if (nearTarget()) { //si le lapin est proche de sa cible il la mange
                    if(target.isPickable()) {
                        synchronized (GrilleMod.key) {
                            mangeFleur();
                        }
                    }
                    this.target = null; //cible devient vide
                    try {
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if(/*target != null &&*/ target.isPickable()){ //sinon si la cible est ready
                    this.avanceNuisible();
                        if(isNotValidPosition(this.x, this.y)){ ; //renverra true si à proximité d'un bâtiment de défense
                            setenFuite(); //fuit
                        }
                    try {
                        sleep(15);
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

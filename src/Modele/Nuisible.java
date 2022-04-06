package Modele;

import View.Grille;
import View.VueNuisible;

import static java.lang.Math.*;

/**
 * Nuisible
 * Classe gérant les actions, affectations, et le comportement des nuisibles
 */
public class Nuisible extends Thread{
    private int x, y;
    private double dir; //la direction du lapin en degrés (?)
    private boolean enfuite = false;
    private Fleur target = null;


    public Nuisible(int x, int y) {
        this.x = x;
        this.y = y;
        this.acquireTarget();
        VueNuisible.updateNuisibles();
        this.start(); //??
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
     * Fait avancer le lapin de distance dans la direction dir
     */
    public void avanceNuisible(){
        this.x += cos(dir)*1.6;
        this.y -= sin(dir)*1.6;
        int posX = target.getX() - this.x;
        int posY = target.getY() - this.y;
        this.dir = atan2(posX, posY) - PI/2.0;
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
        this.enfuite = true;
        GrilleMod.removeNuisible(this);
        VueNuisible.updateNuisibles();
    }

    /**
     * isValidPosition
     * Détermine si la position du lapin est valide pour l'apparition (il ne va pas fuire immédiatement)
     * @return true si la position est valide, false sinon
     */
    public static boolean isNotValidPosition(int x, int y){
        for(Building b : GrilleMod.getBuildings()){
            if(b.getClass() == BatDefense.class){
                int posX = b.getX() - x;
                int posY = b.getY() - y;
                if(posX*posX + posY*posY <= b.getRange()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * acquireTarget
     * donne la fleur la plus proche comme cible au lapin
     */
    public /*synchronized*/ void acquireTarget(){
        for(Fleur f : GrilleMod.getFleurs()){
            if(f != null && f.isPickable()) {
                if (this.target == null) {
                    this.target = f;
                } else {
                    //les coordonnées relatives à la fleur f
                    int posX = f.getX() - this.x;
                    int posY = f.getY() - this.y;

                    //les coordonnées relatives à la cible
                    int targX = this.target.getX() - this.x;
                    int targY = this.target.getY() - this.y;
                    if (posX * posX + posY * posY < targX * targX + targY * targY) {
                        this.target = f;
                    }
                }
            }
        }
        if(target != null) {
            int posX = target.getX() - x;
            int posY = target.getY() - y;
            this.dir = atan2(posX, posY) - PI/2.0;
        }else{ //en vrai ce cas est pas mal osef, juste pour le flex
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
                    mangeFleur();
                    this.target = null;
                    try {
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if(/*target != null &&*/ target.isPickable()){ //sinon si la cible est ready
                    this.avanceNuisible();
                        if(isNotValidPosition(this.x, this.y)){ ; //renverra true si à proximité d'un bâtiment de défense
                            setenFuite();
                        }
                    try {
                        sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }/*else{ //on refresh la cible
                    acquireTarget();//upon désherbing, no such element exception due to an element being removed from getFleurs()
                }*/
            }else{ //si il n'a pas de cible
                acquireTarget();
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

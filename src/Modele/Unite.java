package Modele;

import View.Grille;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.*;
import static java.lang.Math.abs;

public class Unite extends Thread{
    protected int x, y; //les coordonnées de l'unité
    protected int vitesse; //la vitesse de l'unité, définir une valeur globale en fonction de l'unité en question
    protected boolean immobile = true;
    protected int targX; //Les coordonnées (x) ciblées par l'unité
    protected int targY; //Les coordonnées (y) ciblées par l'unité
    protected double dir; //La direction à suivre pour atteindre la cible

    public Unite(int x, int y){
        this.x = x;
        this.y = y;
        this.targX = x;
        this.targY = y;
    }
    /*getters*/
    /**
     *getX
     * @return l'abscisse x de l'unité
     */
    public int getX(){
        return this.x;
    }

    /**
     *getY
     * @return l'ordonnée y de l'unité
     */
    public int getY(){
        return this.y;
    }

    /**
     *getVitesse
     * @return la vitesse de l'unité
     */
    public int getVitesse(){
        return this.vitesse;
    }

    /**
     * getTargX
     * @return la cible (x) de l'unité
     */
    public int getTargX(){
        return this.targX;
    }

    /**
     *getTargY
     * @return la cible (y) de l'unité
     */
    public int getTargY(){
        return this.targY;
    }

    /**
     * getSQDistFrom
     * Retourne le carré de la distance entre l'unite et un point
     * @param x
     * @param y
     * @return un entier
     */
    public int getSQDistFrom(int x, int y){
        return (this.x - x)*(this.x - x) + (this.y - y)*(this.y-y);
    }

    /**
     * setDir
     * Défini automatiquement la direction à suivre en radian
     */
    public void setDir(){
        //TODO corriger les équations
        if(y == targY) { //pour éviter un division par 0
            if(targX - x > 0){
                dir = 0;
            }else{
                dir = PI;
            }
        }else{
            this.dir = atan(abs(targX - x)/ (double) abs(targY - y));
        }
    }

    /**
     * avance
     * fait avancer l'unité vers sa cible
     */
    public void avance(){
        this.x += cos(dir);
        this.y += sin(dir);
    }

    /**
     * setMoving
     * Met l'Unité en mouvement
     * @param x l'abscisse de la cible
     * @param y l'ordonnée de la cible
     */
    public void setMoving(int x, int y) {
        if(!immobile){
            currentThread().interrupt();
            this.immobile = true;
        }
        if (x != this.x || y != this.y) { // on s'embête pas à le déplacer si il est déjà là
            this.targX = x;
            this.targY = y;
            this.setDir();
            this.immobile = false;
            this.start();
        }
    }

    @Override
    public void run(){
        int posX = targX - x;
        int posY = targY - y;
        while(posX*posX /*posY*posY*/> 4){
            /*
            avance();*/
            if(posX > 0){
                this.x++;
            }else{
                this.x--;
            }
            posX = targX - x;

            try {
                sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //TODO, à enlever quand on aura corriger les équations
        while(posY*posY > 4){
            if(posY > 0){
                this.y++;
            }else{
                this.y--;
            }
            posY = targY - y;
            try {
                sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


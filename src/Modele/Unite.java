package Modele;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Unite extends Thread{
    public int x, y; //les coordonnées de l'unité
    public int vitesse; //la vitesse de l'unité, définir une valeur globale en fonction de l'unité en question
    public int targX; //Les coordonnées (x) ciblées par l'unité
    public int targY; //Les coordonnées (y) ciblées par l'unité

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
     *getTargX
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
     * getRelevantCase
     * @return la case sur laquelle l'unité se trouve
     */
    public Case getRelevantCase(){
        return GrilleMod.plateau.get(this.x%GrilleMod.TAILLE_CASE).get(this.y%GrilleMod.TAILLE_CASE);
    }

    /**
     * avance
     * fait avancer l'unité dans une direction (peut être appelé de manière séquentielle pour faire suivre un parcours)
     * @param dir la direction à suivre
     */
    public void avance(double dir){
        this.x += cos(dir);
        this.y += sin(dir);
    }

    public void run(){
        int diffX = this.x - this.targX;
        int diffY = this.y - this.targY;
        while(diffX*diffX + diffY*diffY < 9){
            float tandir = (this.targY-this.y)/ (float) (this.targX - this.x);
            double dir = Math.toDegrees(Math.atan(tandir));
            //récupérer la direction de l'objectif
            //avancer dans la direction
            this.avance(dir);
            diffX = this.x - this.targX;
            diffY = this.y - this.targY;
            //moduler le temps de dormance en fonction de vitesse
            try {
                sleep(5000/vitesse);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentThread().interrupt();
    }

}


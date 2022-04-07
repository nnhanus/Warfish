package Modele;

import static java.lang.Math.*;

public class Unite extends Thread {
    protected int x, y; //les coordonnées de l'unité
    protected boolean immobile = true;
    protected int targX; //Les coordonnées (x) ciblées par l'unité
    protected int targY; //Les coordonnées (y) ciblées par l'unité
    protected double dir; //La direction à suivre pour atteindre la cible

    public Unite(int x, int y) {
        this.x = x;
        this.y = y;
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
        this.dir = atan2(posX, posY) - PI/2.0; //donne l'angle entre la droite tracée par 2 points, et le degré 0 dans le plan
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


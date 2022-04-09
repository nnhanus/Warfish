package Modele;

import java.util.Arrays;

import static java.lang.Math.*;
import static java.lang.Math.PI;

public class Laquais extends Thread {
    private int[] inventaire = new int[]{0,0,0}; //inventaire de fleur
    private static final int VITESSE = 30;
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
        GrilleMod.removeFleur(target); //retirer la fleur
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
        removeTarget();
        for(Fleur f : GrilleMod.getFleurs()){ //parcours des fleurs
            if(f != null && f.isPickable() && !f.getIsDead()) { //on s'intéresse aux fleurs fleuries
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
        /*if(target != null) {
            int posX = target.getX() - x;
            int posY = target.getY() - y;
            this.dir = atan2(posX, posY) - PI/2.0;
        }*/

        setDir();
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
            posX = proprio.getX() - x;
            posY = proprio.getY() - y;
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
        boolean aramasse = false;
        while(true){
            if(retour || target == null/* || target.getIsDead()*/){
                synchronized (GrilleMod.key) {
                    if (target == null /*|| target.getIsDead()*/) {
                        acquireTarget();
                    }
                }
                if(nearProprio()){
                    videInventaire();
                    retour = false;
                }else{
                    avanceLaquais();
                }
                try {
                    sleep(VITESSE);
                } catch (InterruptedException e) {}
            }

            if(!retour){
                if(target != null && !target.getIsDead()) {
                    synchronized (GrilleMod.key){
                        //acquireTarget();
                        if (target != null && nearTarget() && target.isPickable()) {
                            ramasseFleur();
                            aramasse = true;
                        }
                    }

                    if(aramasse){
                        aramasse = false;
                        try {
                            sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(target != null && !nearTarget() && !target.getIsDead()){
                        avanceLaquais();
                        try {
                            sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    synchronized (GrilleMod.key) {
                        acquireTarget(); //assignation d'une cible
                        if(target != null && target.getIsDead()){
                            removeTarget();
                        }
                    }
                    try {
                        sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

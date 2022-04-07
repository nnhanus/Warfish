package Modele;

import View.VueFleur;

public class Fleur extends Thread {
    //les différntes attributs des fleurs
    public int lifespan = 15; //leur temps de vie
    boolean isPicked = false; //si la fleur a été ramassée ou non
    boolean isDead = false; //si la fleur est morte ou non
    private boolean boosted = false; //si la fleur est boostée par un bâtiment
    private int x; //position
    private int y; //position
    private int type; //type (rouge, jaune, vert)

    /**
     * Constructeur avec type aléatoire
     * Utilisé à l'initialisation de la partie
     * @param x position
     * @param y position
     */
    public Fleur(int x, int y) {
        //position
        this.x = x;
        this.y = y;
        //type aléatoire
        this.type = (int) (Math.random() * 3);
        this.boosted = mustBeBoosted();
        //update de la vue
        VueFleur.updateFleur();
        //lancement du thread
        this.start();
    }

    /**
     * Constructeur avec type donné
     * Utilisé pendant la partie lorsqu'une graine est plantée
     * @param x position
     * @param y position
     * @param t type
     */
    public Fleur(int x, int y, int t){
        //position
        this.x = x;
        this.y = y;
        //type donnée
        this.type = t;
        this.boosted = mustBeBoosted();
        VueFleur.updateFleur();
        //lancement du thread
        this.start();
    }

    /**
     * Renvoie l'abscisse de la fleur
     */
    public int getX(){
        return this.x;
    }

    /**
     * Renvoie l'ordonnée de la fleur
     */
    public int getY(){
        return this.y;
    }

    /**
     * Renvoie le type
     */
    public int getType(){
        return type;
    }

    /**
     * Teste si la fleur est en état d'être cueillie
     *
     * @return vrai si la fleur peut être cueillie, faux sinon
     */
    public boolean isPickable() {
        return lifespan < 9 && lifespan > 0;
    }

    /**
     * Renvoie le prix de la fleur en fonction de son état
     *
     * @return le prix
     */
    public int getAmount() {
        int amount;
        if (lifespan <= 9 && lifespan > 3) { //si la fleur est cueillie prématurement
            amount = 1;
        } else if (lifespan <= 3) { //fleur ceuillie au meilleur moment, on en récolte plus
            amount = 3;
        } else { //sinon elle est pas récoltable ou elle est pourrie du coup 0
            amount = 0;
        }
        if (boosted) { //si elle est boostée, elle produit plus
            amount += 2;
        }
        return amount;
    }

    /**
     * boost
     * set le statut boosted de la fleur à true
     */
    public void boost() {
        this.boosted = true;
        System.out.println("Has been boosted");
    }

    /**
     * mustBeBoosted
     * Détermine si un bâtiment de production est à proximité de la fleur
     *
     * @return true si un bâtiment de production est à proximité, false sinon
     */
    public boolean mustBeBoosted() {
        for (Building b : GrilleMod.getBuildings()) { //on itère sur tous les bâtiments sur le terrain
            if (b.getClass() == BatProduction.class) { //si le bâtiment est de type production
                int posX = b.getX() - this.x;  //la distance d'abscisse entre le bat et la fleur
                int posY = b.getY() - this.y; //la distance d'ordonnée entre le bat et la fleur
                if (posX * posX + posY * posY <= b.getRange() * b.getRange()) { //on teste si la distance est inférieure au rayon du bat
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Set isPicked a true
     * Equivaut à la fleur qui se fait ramasser
     */
    public void isPicked() {
        isPicked = true;
        //System.out.println("je suis ramassée");
    }

    /**
     * @return isPicked
     */
    public boolean getIsPicked() {
        return isPicked;
    }

    /**
     * Set isDead à true
     */
    public void dies() {
        isDead = true;
    }

    /**
     * @return isDead
     */
    public boolean getIsDead() {
        return isDead;
    }

    @Override
    public void run() {
        while (!isPicked && lifespan > 0) { //si la fleur est en vie et sur le terrain
            lifespan -= 1; //son lifespan diminue
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        dies(); //une fois le lifespan à 0, la fleur meurt
    }
}


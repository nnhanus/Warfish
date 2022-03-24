package Modele;
public class Fleur extends Ressource { //à terme plusieurs types de fleurs, avec chacun différentes spécificités

    public int lifespan = 449;
    boolean isPicked = false;
    boolean isDead = false;
    private boolean boosted = false;
    private int x;
    private int y;
    //private int type;

    public Fleur(int x, int y) {
        super(x, y);
        this.type = 1 /*+ (int) (Math.random() * 3)*/;

        this.boosted = mustBeBoosted();
        this.start();
    }

    public int getType(){
        return type;
    }

    /**
     * Teste si la fleur est en état d'être cueillie
     *
     * @return vrai si la fleur peut être cueillie, faux sinon
     */
    public boolean isPickable() {
        if (lifespan >= 450 || lifespan <= 0) {
            return false;
        }
        return true;
    }

    /**
     * Renvoie le prix de la fleur en fonction de son état
     *
     * @return le prix
     */
    public int getAmount() {
        //Stade 3: fleur peut être cueillie mais rapporte moins
        int amount;
        if (lifespan <= 450 && lifespan > 150) {
            amount = 1;
        } else if (lifespan <= 150) { // Stade 4: fleur ceuillie au meilleur moment, prix le plus élévé
            amount = 3;
        } else { //sinon elle est pas récoltable ou elle est pourrie du coup 0
            amount = 0;
        }
        if (boosted) {
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
        for (Building b : GrilleMod.getBuildings()) {
            if (b.getClass() == BatProduction.class) {
                int posX = b.getX() - this.x;
                int posY = b.getY() - this.y;
                if (posX * posX + posY * posY <= b.getRange() * b.getRange()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void isPicked() {
        isPicked = true;
        //System.out.println("je suis ramassée");
    }

    public boolean getIsPicked() {
        return isPicked;
    }

    public void dies() {
        isDead = true;
    }

    public boolean getIsDead() {
        return isDead;
    }

    @Override
    public void run() {
        while (/*!isDead &&*/ !isPicked && lifespan > 0) {
            lifespan -= 50;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
        }
        dies();
    }
}


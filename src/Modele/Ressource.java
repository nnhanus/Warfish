package Modele;
abstract class Ressource extends Thread{ //à terme plusieurs types de ressources
    protected int prix;
    public int id;

    public abstract boolean isPickable();
}


class Fleur extends Ressource{ //à terme plusieurs types de fleurs, avec chacun différentes spécificités

    public int lifespan = 1000;
    boolean estLa = true;

    public Fleur() {
        this.prix = 100;
        this.id = 0;
    }

    /**
     * Teste si la fleur est en état d'être cueillie
     * @return vrai si la fleur peut être cueillie, faux sinon
     */
    public boolean isPickable(){
        if (lifespan >= 450 || lifespan <= 0){
            return false;
        }
        return true;
    }

    /**
     * Renvoie le prix de la fleur en fonction de son état
     * @return le prix
     */
    public int getPrix(){
        //Stade 3: fleur peut être cueillie mais rapporte moins
        if (lifespan <= 450 && lifespan > 150){
            return 60;
        } else if (lifespan <= 150){ // Stade 4: fleur ceuillie au meilleur moment, prix le plus élévé
            return 100;
        } else { //sinon elle est pas récoltable ou elle est pourrie du coup 0
            return 0;
        }
    }

    @Override
    public void run(){
        while(lifespan > 0 && estLa){
            lifespan -= 50;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e){
                estLa = false;
            }
        }
    }

}


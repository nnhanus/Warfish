package Modele;
abstract class Ressource { //à terme plusieurs types de ressources
    public int prix;
    public int id;
}


class Fleur extends Ressource{ //à terme plusieurs types de fleurs, avec chacun différentes spécificités

    public int lifespan = 1000;

    public Fleur() {
        this.prix = 100;
        this.id = 0;
    }
}


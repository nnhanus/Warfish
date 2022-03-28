package Modele;

/**
 * Un bâtiment-ressource (mais plutôt ressource) généré automatiquement sur lequel le jardinier peut récolter des cailloux
 */
public class Rocher extends Ressource{

    public Rocher(int x, int y){
        super(x, y);
    }

    public boolean isPickable(){
        return true;
    }

    /**
     * getid
     * @return l'id du cailloux (ici 4)
     */
    public int getType(){
        return 4;
    }

    public int getAmount(){
        return 2; //j'ai la flemme de faire un truc aléatoire, il est trop tard
    }
}

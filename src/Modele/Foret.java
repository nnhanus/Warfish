package Modele;

/**
 * Un bâtiment-ressource (mais plutôt ressource) généré automatiquement sur lequel le jardinier peut récolter du bois
 */
public class Foret extends Ressource{

    public Foret(int x, int y){
        super(x, y);
    }

    /**
     * je sais pas quoi faire de toi è-é
     * @return osef
     */
    public boolean isPickable(){
        return true;
    }

    /**
     * getid
     * @return l'id du bois (ici 3)
     */
    public int getid(){
        return 3;
    }

    public static int getidStatic(){
        return 3;
    }

    public int getAmount(){
        return 2; //j'ai la flemme de faire un truc aléatoire, il est trop tard
    }
}

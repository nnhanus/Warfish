package Modele;
import java.util.ArrayList;

public class Grille { //potentiellement mettre toutes les générations aléatoires, et déplacement automatique ou autre dans cette classe ???
    public static final int LARGEUR_GRILLE = 16; //la largeur en nombre de case de la grille
    public static final int HAUTEUR_GRILLE = 15; //la hauteur en nombre de case de la grille
    public static final int TAILLE_CASE = 3; //la taille des cases

    public static ArrayList<ArrayList<Case>> plateau = new ArrayList<> ();
    public ArrayList<Fleur> fleurs = new ArrayList<>();

    /**
     * Constructeur de Grille
     * J'ai rien à mettre dedans il me semble ?
     */
    public Grille() {

    }

    /**
     * getCase
     * Renvoie la case de coordonnées (i, j)
     * @param i une ligne
     * @param j une colonne
     * @return la case à la ligne i et la colonne j
     */
    public static Case getCase(int i, int j) {
        return plateau.get(i).get(j);
    }

    /**
     * putFleurAtRand
     * genere une fleur aléatoirement sur la grille
     */
    public void putFleurAtRand(){
        int randx = (int) (Math.random()*HAUTEUR_GRILLE);
        int randy = (int) (Math.random()*LARGEUR_GRILLE);
        Case c = getCase(randx, randy);
        if(!c.contientFleur()){
            Fleur f = new Fleur(randx, randy);
            putRessource(c, f); //ou bien un .add() tout simplement ?
            fleurs.add(f);
        }

    }

    /**
     * putRessource
     * Place une ressource sur une case de la grille
     * @param i l'abscisse d'une case
     * @param j l'ordonnée d'une case
     * @param r une ressource
     */
    public void putRessource(int i, int j, Ressource r){
        getCase(i, j).add(r);
    }

    /**
     * putRessource
     * Place une ressource sur une case de la grille
     * @param c une case
     * @param r une ressource
     */
    public void putRessource(Case c, Ressource r){c.add(r);}
    
    public ArrayList<Fleur> getFleurs(){
        return fleurs;
    }
}


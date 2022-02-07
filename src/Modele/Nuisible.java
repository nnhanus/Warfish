package Modele;

import static java.lang.Math.cos; //on pourrait aussi importer '*'
import static java.lang.Math.sin;

public class Nuisible {
    int x, y;
    double dir; //la direction du lapin en degrés (?)

    public Nuisible(int x, int y, double dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    /**
     * avanceNuisible
     * Fait avancer le lapin de distance dans la direction dir
     * @param distance la distance parcourue par le lapin
     * Je propose qu'on utilise l'axe y habituel, pas celui des info-graphistes (il va quand même falloir le prendre en compte au moment de l'affichage)
     */
    public void avanceNuisible(int distance){
        this.x += distance*cos(dir);
        this.y += distance*sin(dir);
    }

    /**
     * tourneNuisible
     * Fait se tourner le lapin de dir degrés
     * @param dir la valeur en degré de combien le lapin se tourne
     */
    public void tourneNuisible(double dir){
        this.dir += dir;
    }

    /**
     * getRelevantCase
     * @return la case sur laquelle le lapin se trouve
     */
    public Case getRelevantCase(){
        return new Case(this.x%Grille.TAILLE_CASE, this.y%Grille.TAILLE_CASE);
    }

    /**
     * mangeFleur
     * fait manger une fleur au lapin
     * Précondition : On doit avoir vérifier que la case sur laquelle se trouve le lapin contient bien une fleur
     */
    public void mangeFleur(){
        Case c = this.getRelevantCase();
        c.removeFleur();
    }
    //logiquement, on aurait un thread (run()) dans cette classe, qu'on lancerait à la création de chaque lapin, et qui contiendrait son comportement
}

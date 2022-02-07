package Modele;
import java.util.ArrayList;

public class Case {
    int x, y;
    ArrayList<Ressource> content = new ArrayList<>();
    //plusieurs alternatives :
    // 1) plusieurs arraylist, selon les ressources
    // 2) Si 1 fleur par case alors utiliser un booléen pour vérifier si une fleur est présente ? Placer la fleur tout le temps au même endroit pour aller plus vite
    // 3) faire une nouvelle classe itérable dédiée

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * vide le contenu de la liste
     */
    public void empty() {
        this.content.clear();
    }

    /**
     * add
     * Ajoute une ressource dans le contenu de la case
     * @param r une ressource
     */
    public void add(Ressource r){
        this.content.add(r);
    }

    /**
     * remove
     * Enlève une ressource dans le contenu de la case
     * @param r une ressource
     * Puisque la ressource est donnée par ses coordonnées, un appel statique à une méthode clear(r) de Ressource devrait suffir à enlever la ressource du bon type à la bonne case ?
     * Ça a l'air de nécessiter des bidouillages assez dégueulasses
     */
    public void remove(Ressource r){
        this.content.remove(r);
    }

    /**
     * removeFleur
     * Enlève la fleur de content
     */
    public void removeFleur(){
        //TODO
    }

    /**
     * contientFleur
     * Vérifie la présence d'une fleur sur la case
     * @return un booléen
     */
    public boolean contientFleur(){
        for(Ressource r : content){
            if(r.getClass()==Fleur.class){
                return true;
            }
        }
        return false;
    }

    /**
     * getFleur
     * renvoie la fleur présente sur la case
     * @return une Fleur
     */
    public Fleur getFleur(){
        //l'idée c'est de renvoyer une fleur si il y en a une, mais vas y c'est chiant
        //on considère qu'il n'y a qu'une fleur par case
        for(Ressource r : content){
            if(r.getClass()==Fleur.class){
                return (Fleur) r;
            }
        }
        return null;
    }
}


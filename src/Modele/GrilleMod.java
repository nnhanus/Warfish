package Modele;
import View.View;
import View.VueNuisible;

import java.util.ArrayList;

/**
 * Classe s'occupant du terrain de jeu
 */
public class GrilleMod extends Thread{
    public static final int LARGEUR_GRILLE = View.TERRAIN_WIDTH; //la largeur de la grille
    public static final int HAUTEUR_GRILLE = View.HEIGHT_WIN; //la hauteur de la grille

    private static ArrayList<Fleur> fleurs = new ArrayList<>(); //les fleurs présentes sur la grille
    private static ArrayList<Building> buildings = new ArrayList<>(); //les batiments présents sur la grille
    private static ArrayList<Nuisible> nuisibles = new ArrayList<>(); //les nuisibles présentes sur la grille
    private static ArrayList<Jardinier> jardiniers = new ArrayList<>(); //les jardiniers présents sur la grille

    public static final int RANGE_PLACEABLE = 3000; //rayon dans lequel on peut planter une fleur

    private static final int nbFleur = 8; //nombre de fleurs sur le terrain au lancement

    //Indices des différentes ressources
    public static final int indiceFleurR = 0;
    public static final int indiceFleurJ = 1;
    public static final int indiceFleurV = 2;
    public static final int indiceGraineR = 3;
    public static final int indiceGraineJ = 4;
    public static final int indiceGraineV = 5;
    public static final int indiceBouquet = 6;

    //Initialisation du bâtiment principal
    private static final BatPrincipal BAT_PRINCIPAL = new BatPrincipal((int) (Math.random() * LARGEUR_GRILLE), (int) (Math.random() * HAUTEUR_GRILLE));
   //Unité sélectionnée
    private static Jardinier selectedJard = null;

    /**
     * Constructeur de Grille
     */
    public GrilleMod() {
        initGrille();
    }

    /**
     * Renvoie la liste des fleurs sur le terrain
     */
    public static ArrayList<Fleur> getFleurs(){
        return GrilleMod.fleurs;
    }

    /**
     * Renvoie la liste des nuisibles sur le terrain
     */
    public static ArrayList<Building> getBuildings(){return GrilleMod.buildings;}

    /**
     * Renvoie la liste des nuisibles sur le terrain
     */
    public static ArrayList<Nuisible> getNuisibles(){
        return GrilleMod.nuisibles;
    }

    /**
     * Renvoie la liste des jardniers sur le terrain
     */
    public static ArrayList<Jardinier> getJardiniers(){
        return GrilleMod.jardiniers;
    }

    /**
     * getSelectedUnite
     * Renvoie l'unite selectionnée
     * @return la selectedUnite
     */
    public static Jardinier getSelectedJard(){
        return selectedJard;
    }

    /**
     * setSelectedUnite
     * Défini selectedUnite
     * @param u une unité
     */
    public static void setSelectedJard(Jardinier u){
        selectedJard = u;
    }

    /**
     * Renvoie le bâtiment principal
     */
    public static BatPrincipal getBatPrincipal(){
        return BAT_PRINCIPAL;
    }

    /**
     * Renvoie l'abscisse du bâtiment principal
     */
    public static int getBatX(){
        return BAT_PRINCIPAL.getX();
    }

    /**
     * Renvoie l'ordonnée du bâtiment principal
     */
    public static int getBatY(){
        return BAT_PRINCIPAL.getY();
    }

    /**
     * addFleur
     * Ajoute une fleur à la liste des fleurs
     * @param f une Fleur
     */
    public static void addFleur(Fleur f){
        fleurs.add(f);
    }

    /**
     * removeFleur
     * Enlève une fleur de la liste des fleurs, et met à jour la cible des nuisibles concernés
     * C'est typiquement le cas où on aurait besoin d'une section critique je pense, mais je sais pas si c'est possible/comment ça marche ici
     */
    public static void removeFleur(Fleur f){
        f.interrupt(); //interruption du thread de la fleur
        f.isPicked(); //la fleur est ramassée
        fleurs.remove(f); //la fleur est enlevée du terrain
        for(Nuisible n : nuisibles){ //Parcours des nuisibles du terrain
            if(n.getTarget() == f){ //la fleur est la cible du nuisible
                n.acquireTarget(); //le nuisible reçoit une nouvelle cible
            }
        }
    }

    /**
     * ajouteBatiment
     * Ajoute un bâtiment à la liste des bâtiments buildings
     * @param b un bâtiment
     */
    public static void addBatiment(Building b){
        buildings.add(b);
    }

    /**
     * addUnite
     * Ajoute une unité à la grille
     * @param j une unité
     */
    public static void addUnite(Jardinier j){
        jardiniers.add(j);
    }

    /**
     * addNuisible
     * Tente d'ajouter un nuisible sur le bord de l'écran (10 fois), puis n'importe où (10 fois)
     */
    public void addNuisible(){
        int randx = -1;
        int randy = -1;
        int iter = 0; //nombre d'itérations, condition de la boucle

        do {
            int side = (int) (Math.random() * 3); //le côté du terrain sur lequel le nuisible sera généré
            iter++;
            switch (side) {
                case 0 -> { //côté gauche
                    randx = 0;
                    randy = (int) (Math.random() * GrilleMod.HAUTEUR_GRILLE); //ordonnée aléatoire
                }
                case 1 -> { //côté droit
                    randx = GrilleMod.LARGEUR_GRILLE;
                    randy = (int) (Math.random() * GrilleMod.HAUTEUR_GRILLE); //ordonnée aléatoire
                }
                case 2 -> { //haut
                    randx = (int) (Math.random() * GrilleMod.LARGEUR_GRILLE); //abscisse aléatoire
                    randy = 0;
                }
                case 3 -> { //bas
                    randx = (int) (Math.random() * GrilleMod.LARGEUR_GRILLE); //abscisse aléatoire
                    randy = GrilleMod.HAUTEUR_GRILLE;
                }
            }
        } while(Nuisible.isNotValidPosition(randx, randy) && iter <= 10);
        //Tant que la position n'est pas valide et que le nombre d'itérations est inférieur à 10

        if(iter > 10){ //placement aléatoire sur le terrain
            do{
                iter++;
                //coordonnées aléatoires
                randx = (int) (Math.random() * GrilleMod.LARGEUR_GRILLE);
                randy = (int) (Math.random() * GrilleMod.HAUTEUR_GRILLE);
            } while(Nuisible.isNotValidPosition(randx, randy) && iter <= 20);
            //Tant que la position n'est pas valide et que le nombre d'itérations est inférieur à 20
        }

        if (iter < 20) { //une fois qu'on a atteint 20 intérations
            nuisibles.add(new Nuisible(randx, randy)); //on crée le nuisible
            VueNuisible.updateNuisibles(); //mise à jour de la vue
        }

    }

    /**
     * Supprime un nuisible du terrain
     * @param n le nuisible à supprimer
     */
    public static void removeNuisible(Nuisible n){
        nuisibles.remove(n);
        //VueNuisible.updateNuisibles();
    }

    /**
     * isNotValidPosition
     * Détermine si la position passée en paramètre est (trop) proche d'un élément du terrain
     * @param x l'abscisse d'intérêt
     * @param y l'ordonnée d'intérêt
     * @return false si la position est valide, true sinon
     */
    public static boolean isNotValidPosition(int x, int y){
        for(Building b : buildings){ //Parcours des bâtiments
            int posX = b.x - x;
            int posY = b.y - y;
            if(posX*posX + posY*posY < RANGE_PLACEABLE){ //un bâtiment est trop proche
                return true; //la position n'est pas valide
            }
        }

        for(Fleur f : fleurs){ //Parcours des fleurs
            int posX = f.getX() - x;
            int posY = f.getY() - y;
            if(posX*posX + posY*posY < RANGE_PLACEABLE){ //une fleur est trop proche
                return true; //la position n'est pas valide
            }
        }
        return false; //la position est valide
    }

    /**
     * getSQDist
     * Renvoie le carré de la distance entre 2 points
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return un entier
     */
    public static int getSQDist(int x1, int y1, int x2, int y2){
        return (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2-y1);
    }

    /**
     * initFleur
     * Initialise aléatoirement des Fleurs sur le terrain
     * Utilisé à la création de la grille
     */
    public void initFleur(){
        for(int i = 0; i < nbFleur; i++) { //on place le nombre indiqué de fleurs sur la grille
            //coordonnées aléatoires
            int randx = (int) (Math.random() * HAUTEUR_GRILLE);
            int randy = (int) (Math.random() * LARGEUR_GRILLE);

            while (isNotValidPosition(randx, randy)){ //tant que la position n'est pas valide
                //on regénère les coordonnées
                randx = (int) (Math.random() * HAUTEUR_GRILLE);
                randy = (int) (Math.random() * LARGEUR_GRILLE);
            }
            addFleur(new Fleur(randx, randy)); //une fois que les coordonnées sont bonnes, on ajoute la fleur
        }
    }

    /**
     * initGrille
     * Initialise la grille en y plaçant des éléments
     */
    public void initGrille(){
        addBatiment(BAT_PRINCIPAL); //ajout du bâtiment principal
        initFleur(); //ajout des fleurs
        //génération d'un jardinier aléatoire
        Jardinier J = new Jardinier(LARGEUR_GRILLE/2, HAUTEUR_GRILLE/2);
        addUnite(J);
        selectedJard = J; //on sélectionne le jardinier créé
        start(); //lancement du thread
    }

    /**
     * run
     * Thread générant un nuisible toutes les 12 secondes
     */
    @Override
    public void run(){
        while(true){
            addNuisible();
            try {
                sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


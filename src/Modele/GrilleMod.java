package Modele;
import View.Grille;
import View.Movable;
import View.View;
import View.VueNuisible;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GrilleMod extends Thread{ //potentiellement mettre toutes les générations aléatoires, et déplacement automatique ou autre dans cette classe ???
    public static final int LARGEUR_GRILLE = View.TERRAIN_WIDTH; //la largeur en nombre de case de la grille
    public static final int HAUTEUR_GRILLE = View.HEIGHT_WIN; //la hauteur en nombre de case de la grille

    private static ArrayList<Fleur> fleurs = new ArrayList<>(); //passer en static asap ?
    private static ArrayList<Ressource> ressources = new ArrayList<>(); //plusieurs tableaux de ressources pour aller un poil plus vite I guess, c'est aussi pour pas avoir d'emmerde avec les types
    private static ArrayList<Building> buildings = new ArrayList<>();
    private static ArrayList<Nuisible> nuisibles = new ArrayList<>();
    private static ArrayList<Unite> unites = new ArrayList<>();

    public static final int ID_FLEUR = 1;

    public static final int RANGE_PLACEABLE = 3000;

    private static final int nbForet = 4;
    private static final int nbRocher = 4;
    private static final int nbFleur = 8;


    public static final int indiceFleurR = 1;
    public static final int indiceFleurV = 2;
    public static final int indiceGraineR = 3;
    public static final int indiceGraineJ = 4;
    public static final int indiceGraineV = 5;
    public static final int indiceBouquet = 6;

    private static final BatPrincipal BAT_PRINCIPAL = new BatPrincipal((int) (Math.random() * LARGEUR_GRILLE), (int) (Math.random() * HAUTEUR_GRILLE));
    private static Unite selectedUnite = null;

    /**
     * Constructeur de Grille
     * J'ai rien à mettre dedans il me semble ?
     */
    public GrilleMod() {
        initGrille();
    }

    /**
     * getFleurs
     * @return GrilleMod.fleurs
     */
    public static ArrayList<Fleur> getFleurs(){
        return GrilleMod.fleurs;
    }

    /**
     * getRessources
     * @return GrilleMod.ressources
     */
    public static ArrayList<Ressource> getRessources(){return GrilleMod.ressources;}

    /**
     * getBuildings
     * @return GrilleMod.buildings
     */
    public static ArrayList<Building> getBuildings(){return GrilleMod.buildings;}

    /**
     * getNuisibles
     */
    public static ArrayList<Nuisible> getNuisibles(){
        return GrilleMod.nuisibles;
    }

    public static ArrayList<Unite> getUnites(){
        return GrilleMod.unites;
    }

    /**
     * getSelectedUnite
     * Renvoie l'unite selectionnée
     * @return la selectedUnite
     */
    public static Unite getSelectedUnite(){
        return selectedUnite;
    }

    /**
     * setSelectedUnite
     * Défini selectedUnite
     * @param u une unité
     */
    public static void setSelectedUnite(Unite u){
        selectedUnite = u;
    }

    public static BatPrincipal getBatPrincipal(){
        return BAT_PRINCIPAL;
    }

    public static int getBatX(){
        return BAT_PRINCIPAL.getX();
    }

    public static int getBatY(){
        return BAT_PRINCIPAL.getY();
    }

    /**
     * addFleur
     * Ajoute une fleur à la liste des fleurs et à la liste des ressources
     * @param f une Fleur
     */
    public static void addFleur(Fleur f){
        fleurs.add(f);
        ressources.add(f);
    }

    /**
     * removeFleur
     * Enlève une fleur de la liste des fleurs et des ressources, et met à jour la cible des nuisibles concernés
     * C'est typiquement le cas où on aurait besoin d'une section critique je pense, mais je sais pas si c'est possible/comment ça marche ici
     */
    public static void removeFleur(Fleur f){
        f.interrupt();
        f.isPicked();
        ressources.remove(f);
        fleurs.remove(f);
        for(Nuisible n : nuisibles){
            if(n.getTarget() == f){
                n.acquireTarget(); //ou mise à null, c'est équivalent
            }
        }
    }

    public static void desherbeFleur(Fleur f){
        f.isPicked();
        //if (f.getIsDead()) {
            ressources.remove(f);
            fleurs.remove(f);
            for (Nuisible n : nuisibles) {
                if (n.getTarget() == f) {
                    n.acquireTarget(); //ou mise à null, c'est équivalent
                }
            //}
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
     * addRessource
     * Place une ressource sur la grille
     * @param r une ressource
     */
    public static void addRessource(Ressource r){
        ressources.add(r);
    }

    /**
     * addUnite
     * Ajoute une unité à la grille
     * @param u une unité
     */
    public static void addUnite(Unite u){
        unites.add(u);
    }

    /**
     * addNuisible
     * Tente d'ajouter un nuisible sur le bord de l'écran (10 fois), puis n'importe où (10 fois)
     */
    public void addNuisible(){

        int randx = -1;
        int randy = -1;
        int iter = 0;

        do {
            int side = (int) (Math.random() * 3); //le côté du terrain sur lequel le nuisible sera généré
            iter++;
            switch (side) {
                case 0 -> {
                    randx = 0;
                    randy = (int) (Math.random() * GrilleMod.HAUTEUR_GRILLE);
                }
                case 1 -> {
                    randx = GrilleMod.LARGEUR_GRILLE;
                    randy = (int) (Math.random() * GrilleMod.HAUTEUR_GRILLE);
                }
                case 2 -> {
                    randx = (int) (Math.random() * GrilleMod.LARGEUR_GRILLE);
                    randy = 0;
                }
                case 3 -> {
                    randx = (int) (Math.random() * GrilleMod.LARGEUR_GRILLE);
                    randy = GrilleMod.HAUTEUR_GRILLE;
                }
            }
        }while(Nuisible.isNotValidPosition(randx, randy) && iter <= 10);

        if(iter > 10){
            do{
                iter++;
                randx = (int) (Math.random() * GrilleMod.LARGEUR_GRILLE);
                randy = (int) (Math.random() * GrilleMod.HAUTEUR_GRILLE);
            }while(Nuisible.isNotValidPosition(randx, randy) && iter <= 20);
        }

        if(iter < 20) {
            nuisibles.add(new Nuisible(randx, randy));
            VueNuisible.updateNuisibles();
        }

    }

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
        for(Building b : buildings){
            int posX = b.x - x;
            int posY = b.y - y;
            if(posX*posX + posY*posY < RANGE_PLACEABLE){
                return true;
            }
        }

        for(Ressource r : ressources){
            int posX = r.x - x;
            int posY = r.y - y;
            if(posX*posX + posY*posY < RANGE_PLACEABLE){
                return true;
            }
        }
        return false;
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
     * Initialise aléatoirement des Fleur sur le terrain
     */
    public void initFleur(){
        for(int i = 0; i < nbFleur; i++) {
            int randx = (int) (Math.random() * HAUTEUR_GRILLE);
            int randy = (int) (Math.random() * LARGEUR_GRILLE);

            while (isNotValidPosition(randx, randy)){
                randx = (int) (Math.random() * HAUTEUR_GRILLE);
                randy = (int) (Math.random() * LARGEUR_GRILLE);
            }
            addFleur(new Fleur(randx, randy));
        }
    }

    /**
     * initRocher
     * Initialise aléatoirement des Rocher sur le terrain
     */
    public void initRocher(){
        for(int i = 0; i < nbRocher; i++) {
            int randx = (int) (Math.random() * HAUTEUR_GRILLE);
            int randy = (int) (Math.random() * LARGEUR_GRILLE);
            while (isNotValidPosition(randx, randy)){
                randx = (int) (Math.random() * HAUTEUR_GRILLE);
                randy = (int) (Math.random() * LARGEUR_GRILLE);
            }
            addRessource(new Rocher(randx, randy));
        }
    }

    /**
     * initForet
     * Initialise aléatoirement des Foret sur le terrain
     */
    public void initForet(){
        for(int i = 0; i < nbForet; i++) {
            int randx = (int) (Math.random() * HAUTEUR_GRILLE);
            int randy = (int) (Math.random() * LARGEUR_GRILLE);
            while (isNotValidPosition(randx, randy)){
                randx = (int) (Math.random() * HAUTEUR_GRILLE);
                randy = (int) (Math.random() * LARGEUR_GRILLE);
            }
            addRessource(new Foret(randx, randy));
        }
    }

    /**
     * initGrille
     * Initialise la grille en y plaçant des éléments
     */
    public void initGrille(){
        addBatiment(BAT_PRINCIPAL);
        System.out.println(BAT_PRINCIPAL.getX() + " " + BAT_PRINCIPAL.getY());
        initFleur();
        //initRocher();
        //initForet();

        Jardinier J = new Jardinier(LARGEUR_GRILLE/2, HAUTEUR_GRILLE/2);
        addUnite(J);
        selectedUnite = J;
        start();
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


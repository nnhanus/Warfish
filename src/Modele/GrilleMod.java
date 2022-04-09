package Modele;

import View.View;
import View.VueNuisible;
import View.VueCommandes;
import View.VueFleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GrilleMod {
    public static final int LARGEUR_GRILLE = View.TERRAIN_WIDTH; //la largeur du terrain
    public static final int HAUTEUR_GRILLE = View.HEIGHT_WIN; //la hauteur du terrain

    //liste des éléments importants du jeu
    private static ArrayList<Fleur> fleurs = new ArrayList<>(); //la liste des fleurs sur le terrain
    private static ArrayList<Building> buildings = new ArrayList<>();//la liste des bâtiments sur le terrain
    private static ArrayList<Nuisible> nuisibles = new ArrayList<>();//la liste des nuisibles sur le terrain
    private static ArrayList<Jardinier> jardiniers = new ArrayList<>();//la liste des jardiniers sur le terrain
    private static ArrayList<Commande> commandes = new ArrayList<>();//la liste des commandes
    private static int[] bouquets = new int[]{0,0,0,0,0,0,0,0,0,0}; //inventaire des bouquets
    private static ArrayList<Laquais> laquais = new ArrayList<>();

    public static final Object key = new Object();

    //délais d'apparition des nuisibles et fleurs
    private static final int BUNNY_SPAWN_DELAY = 4;
    private static final int FLOWER_SPAWN_DELAY = 2;

    public static final int RANGE_PLACEABLE = 3000; //rayon de placement

    private static final int nbFleur = 8; //nombre de fleurs au lancement

    //indices des différentes ressources
    public static final int indiceFleurR = 0;
    public static final int indiceFleurJ = 1;
    public static final int indiceFleurV = 2;
    public static final int indiceGraineR = 3;
    public static final int indiceGraineJ = 4;
    public static final int indiceGraineV = 5;

    //initialisation du bâtiment aléatoire
    private static final BatPrincipal BAT_PRINCIPAL = new BatPrincipal((int) (Math.random() * LARGEUR_GRILLE), (int) (Math.random() * HAUTEUR_GRILLE));
    private static Jardinier selectedUnite = null; //jardinier sélectionné

    /**
     * Constructeur de Grille
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

    public static ArrayList<Jardinier> getJardiniers(){
        return GrilleMod.jardiniers;
    }

    /**
     * getSelectedUnite
     * Renvoie l'unite selectionnée
     * @return la selectedUnite
     */
    public static Jardinier getSelectedUnite(){
        return selectedUnite;
    }

    /**
     * setSelectedUnite
     * Défini selectedUnite
     * @param u une unité
     */
    public static void setSelectedUnite(Jardinier u){
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
        f.interrupt(); //thread de fleur interrompue
        f.isPicked(); //fleur est ramassée
        fleurs.remove(f); //fleur enlevée du terrain
        for(Nuisible n : nuisibles){ //pour les nuisibles ayant cette fleur comme cible
            if(n.getTarget() == f){
                n.removeTarget();
                //n.acquireTarget(); //nouvelle cible assignée
            }
        }
        for(Laquais l : laquais){
            if(l.getTarget() == f){
                l.removeTarget();
                //l.acquireTarget();
            }
        }
    }

    /**
     * @return GrilleMod.commandes
     */
    public static ArrayList<Commande> getCommandes(){return commandes;}

    /**
     * Ajout d'une commande au terrain
     */
    public static void addCommande(){
        if(commandes.size() < Commande.MAX_COMMANDE) { //s'il reste de la place pour une commande
            commandes.add(new Commande());
        }
    }

    /**
     * Enlever une commande du terrain
     * @param c la commande à retirer
     */
    public static void removeCommande(Commande c){
        commandes.remove(c);
    }

    /**
     * @return GrilleMod.bouquets
     */
    public static int[] getBouquets(){return bouquets;}

    /**
     * Ajout d'un bouquet de l'inventaire
     * @param id l'indice du bouquet à ajouter
     */
    public static void addBouquet(int id){
        bouquets[id]++;
    }

    /**
     * Enlever un bouquet de l'inventaire
     * @param id l'indice du bouquet à enlever
     */
    public static void removeBouquet(int id){
        bouquets[id]--;
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
     * @param u une unité
     */
    public static void addJardinier(Jardinier u){
        jardiniers.add(u);
    }

    /**
     * addLaquais
     * Ajoute un laquais à la grille
     * @param l un laquais
     */
    public static void addLaquais(Laquais l){laquais.add(l);}

    /**
     * getLaquais
     * getter
     * @return la liste de laquais
     */
    public static ArrayList<Laquais> getLaquais(){
        return laquais;
    }
    /**
     * addNuisible
     * Tente d'ajouter un nuisible sur le bord de l'écran (10 fois), puis n'importe où (10 fois)
     */
    public static void addNuisible(){
        int randx = -1;
        int randy = -1;
        int iter = 0;

        do {
            int side = (int) (Math.random() * 3); //le côté du terrain sur lequel le nuisible sera généré
            iter++;
            switch (side) {
                case 0 -> { //côté gauche
                    randx = 0; //abscisse fixée
                    randy = (int) (Math.random() * GrilleMod.HAUTEUR_GRILLE); //ordonnée aléatoire
                }
                case 1 -> { //côté droit
                    randx = GrilleMod.LARGEUR_GRILLE; //abscisse fixée
                    randy = (int) (Math.random() * GrilleMod.HAUTEUR_GRILLE); //ordonnée aléatoire
                }
                case 2 -> { //haut
                    randx = (int) (Math.random() * GrilleMod.LARGEUR_GRILLE); //abscisse aléatoire
                    randy = 0; //ordonnée fixe
                }
                case 3 -> { //bas
                    randx = (int) (Math.random() * GrilleMod.LARGEUR_GRILLE); //abscisse aléatoire
                    randy = GrilleMod.HAUTEUR_GRILLE; //ordonnée fixe
                }
            }
        }while(Nuisible.isNotValidPosition(randx, randy) && iter <= 10); //tant qu'un certain nombre d'itérations n'est pas atteint

        if(iter > 10){
            do{ //placement aléatoire dans le terrain
                iter++;
                randx = (int) (Math.random() * GrilleMod.LARGEUR_GRILLE);
                randy = (int) (Math.random() * GrilleMod.HAUTEUR_GRILLE);
            }while(Nuisible.isNotValidPosition(randx, randy) && iter <= 20); //tant qu'un certain nombre d'itérations n'est pas atteint
        }

        if(iter < 20) {
            nuisibles.add(new Nuisible(randx, randy)); //ajout du nuisible au terrain
            VueNuisible.updateNuisibles(); //mise à jour de la vue
        }

    }

    /**
     * Enlever un nuisible de la vue
     * @param n le nuisible à enlever
     */
    public static void removeNuisible(Nuisible n){
        nuisibles.remove(n);
    }

    /**
     * isNotValidPosition
     * Détermine si la position passée en paramètre est (trop) proche d'un élément du terrain
     * @param x l'abscisse d'intérêt
     * @param y l'ordonnée d'intérêt
     * @return false si la position est valide, true sinon
     */
    public static boolean isNotValidPosition(int x, int y){
        for(Building b : buildings){ //parcours des bâtiments
            //récupération des coordonnées
            int posX = b.x - x;
            int posY = b.y - y;
            if(posX*posX + posY*posY < RANGE_PLACEABLE){ //un bâtiment est trop proche
                return true; //position non-valide
            }
        }

        for(Fleur f : fleurs){ //parcours des fleurs
            //récupération des coordonnées
            int posX = f.getX() - x;
            int posY = f.getY() - y;
            if(posX*posX + posY*posY < RANGE_PLACEABLE){ //une fleur est trop proche
                return true; //position non-valide
            }
        }
        return false; //position valide
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
        for(int i = 0; i < nbFleur; i++) { //placer le nombre de fleurs indiqué au début de partie
            //coordonnées aléatoires
            int randx = (int) (Math.random() * HAUTEUR_GRILLE);
            int randy = (int) (Math.random() * LARGEUR_GRILLE);

            //si la position n'est pas valide, re-générer des coordonnées
            while (isNotValidPosition(randx, randy)){
                randx = (int) (Math.random() * HAUTEUR_GRILLE);
                randy = (int) (Math.random() * LARGEUR_GRILLE);
            }
            addFleur(new Fleur(randx, randy)); //ajout au terrain
        }
    }

    /**
     * addRandomFlower
     * Ajoute une fleur au hasard sur la grille
     */
    public static void addRandomFlower(){
        //coordonnées aléatoires
        int randx = (int) (Math.random() * HAUTEUR_GRILLE);
        int randy = (int) (Math.random() * LARGEUR_GRILLE);
        //si la position n'est pas valide, re-générer des coordonnées
        while (isNotValidPosition(randx, randy)){
            randx = (int) (Math.random() * HAUTEUR_GRILLE);
            randy = (int) (Math.random() * LARGEUR_GRILLE);
        }
        addFleur(new Fleur(randx, randy)); //ajout au terrain
        VueFleur.updateFleur(); //mise a jour de la vue

    }

    /**
     * initGrille
     * Initialise la grille en y plaçant des éléments
     */
    public void initGrille(){
        addBatiment(BAT_PRINCIPAL); //ajout du batiment principal
        initFleur(); //initialisation des fleurs

        //initialisation des commandees
        new Commande(0);
        new VueCommandes();

        //initialisation du jardinier
        Jardinier J = new Jardinier(LARGEUR_GRILLE/2, HAUTEUR_GRILLE/2);
        addJardinier(J);
        selectedUnite = J;
    }

    //ajout de nuisibles
    static ActionListener genNuisible = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            addNuisible();
        }
    };

    //ajout de fleurs
    static ActionListener genFlower = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            addRandomFlower();
        }
    };

    //lancement des timers de générations aléatoires
    static{
        new Timer(1000*BUNNY_SPAWN_DELAY, genNuisible).start();
        new Timer(1000*FLOWER_SPAWN_DELAY, genFlower).start();
    }

}


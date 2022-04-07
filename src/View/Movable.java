package View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Classe utilisée pour l'affichage des éléments Moveable, c'est-à-dire des éléments qui changent d'états
 * Jardiniers, fleurs, nuisibles, bâtiments
 */
public class Movable{
    //les vues des différents éléments
    public static JardinierView vueJard;
    public static VueFleur vueFleur;
    public static VueNuisible vueNuisible;
    public static BuildingView vueBuilding;
    //les constantes pour les images à afficher
    protected static BufferedImage meduse = null; // méduse
    protected static BufferedImage ROuv = null; //fleur rouge ouverte
    protected static BufferedImage RFer = null; //fleur rouge fermée
    protected static BufferedImage RMor = null; // fleur rouge morte
    protected static BufferedImage JOuv = null; //fleur jaune ouverte
    protected static BufferedImage JFer = null; //fleur jaune fermée
    protected static BufferedImage JMor = null; //fleur jaune morte
    protected static BufferedImage VOuv = null; //fleur verte ouverte
    protected static BufferedImage VFer = null; //fleur verte fermée
    protected static BufferedImage VMor = null; //fleur verte morte
    protected static BufferedImage nuis = null; //nuisibles
    protected static BufferedImage batProd = null;
    protected static BufferedImage batDef = null;
    protected static BufferedImage batPrinc = null;


    /**
     * Fonction pour charger les images
     */
    private void chargeImage(){
        //Chargement de l'image de méduse
        try {
            meduse = ImageIO.read(new File("src/Image/meduseJardiniere.png"));
        } catch (IOException ex) {
            System.out.println("Fichier méduse manquant");
        }
        //Chargeemnt des fleurs rouges
        try {
            ROuv = ImageIO.read(new File("src/Image/Rouge_ouverte.png"));
        } catch (IOException ex) {
            System.out.println("Fichier rouge ouverte manquant");
        }
        try {
            RFer = ImageIO.read(new File("src/Image/Rougefermee.png"));
        } catch (IOException ex) {
            System.out.println("Fichier rouge fermée manquant");
        }
        try {
            RMor = ImageIO.read(new File("src/Image/FleurRougeMorte.png"));
        } catch (IOException ex) {
            System.out.println("Fichier rouge morte manquant");
        }
        //Chargement des fleurs jaunes
        try {
            JOuv = ImageIO.read(new File("src/Image/JauneOuverte.png"));
        } catch (IOException ex) {
            System.out.println("Fichier jaune ouverte manquant");
        }
        try {
            JFer = ImageIO.read(new File("src/Image/JauneFermee.png"));
        } catch (IOException ex) {
            System.out.println("Fichier jaune fermée manquant");
        }
        try {
            JMor = ImageIO.read(new File("src/Image/JauneMorte.png"));
        } catch (IOException ex) {
            System.out.println("Fichier jaune morte manquant");
        }
        //Chargement des fleurs vertes
        try {
            VOuv = ImageIO.read(new File("src/Image/VerteOuverte.png"));
        } catch (IOException ex) {
            System.out.println("Fichier vert ouverte manquant");
        }
        try {
            VFer = ImageIO.read(new File("src/Image/VerteFermee.png"));
        } catch (IOException ex) {
            System.out.println("Fichier vert fermée manquant");
        }
        try {
            VMor = ImageIO.read(new File("src/Image/VerteMorte.png"));
        } catch (IOException ex) {
            System.out.println("Fichier vert morte manquant");
        }
        //Chargement des Nuisibles
        try {
            nuis = ImageIO.read(new File("src/Image/cursebriceCute.png")); //image de brice
        } catch (IOException ex) {
            System.out.println("Fichier nuisible manquant");
        }
        try {
            batDef = ImageIO.read(new File("src/Image/maison1.png")); //image de brice
        } catch (IOException ex) {
            System.out.println("Fichier bat def manquant");
        }
        try {
            batProd = ImageIO.read(new File("src/Image/maison3.png")); //image de brice
        } catch (IOException ex) {
            System.out.println("Fichier bat prod manquant");
        }
        try {
            batPrinc = ImageIO.read(new File("src/Image/maison2.png")); //image de brice
        } catch (IOException ex) {
            System.out.println("Fichier bat princ manquant");
        }
    }

    /**
     * Constructeur
     * @param jardvue vue jardinier pour afficher les méduses
     * @param vuefleur vue fleurs pour afficher les fleurs
     * @param vueNuisible vue nuisibles pour afficher les nuisibles
     * @param vueBuilding vue bâtiments pour afficher les batiments
     */
    public Movable(JardinierView jardvue, VueFleur vuefleur,VueNuisible vueNuisible, BuildingView vueBuilding) {
        //liaison des variables
        Movable.vueFleur = vuefleur;
        Movable.vueJard = jardvue;
        Movable.vueNuisible = vueNuisible;
        Movable.vueBuilding = vueBuilding;
        //appelle de la fonction pour charger les images
        chargeImage();
    }

    /**
     * Fonction d'affichage des éléments movable
     * @param g
     */
    public void paint(Graphics g) {
        //appel des différentes fonctions de dessin
        vueBuilding.drawBuildings(g);
        VueFleur.drawFleur(g);
        VueNuisible.drawNuisibles(g);
        JardinierView.drawJardinier(g);
    }
}

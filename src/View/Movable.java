package View;

import Modele.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Movable{

    public static JardinierView vueJard;
    public static VueFleur vueFleur;
    public static VueNuisible vueNuisible;
    public static BuildingView vueBuilding;
    protected static BufferedImage meduse = null;
    protected static BufferedImage f1 = null;
    protected static BufferedImage B1 = null;
    protected static BufferedImage fan1 = null;
    protected static BufferedImage nuis = null;

    public Movable(JardinierView jardvue, VueFleur vuefleur,VueNuisible vueNuisible, BuildingView vueBuilding) {
        Movable.vueFleur = vuefleur;
        Movable.vueJard = jardvue;
        Movable.vueNuisible = vueNuisible;
        Movable.vueBuilding = vueBuilding;
        try {
            meduse = ImageIO.read(new File("src/View/Image/jelly.png")); //image de méduse
        } catch (IOException ex) {
            System.out.println("Fichier manquant"); //absence de l'image
        }
        try {
            f1 = ImageIO.read(new File("src/Image/Rouge_ouverte.png")); //image de méduse
        } catch (IOException ex) {
            System.out.println("Fichier manquant"); //absence de l'image
        }
        try {
            B1 = ImageIO.read(new File("src/Image/Rougefermee.png")); //image de méduse
        } catch (IOException ex) {
            System.out.println("Fichier manquant"); //absence de l'image
        }
        try {
            fan1 = ImageIO.read(new File("src/View/Image/fleur_rouge_fan.png")); //image de méduse
        } catch (IOException ex) {
            System.out.println("Fichier manquant"); //absence de l'image
        }
        try {
            nuis = ImageIO.read(new File("src/View/Image/cursebriceCute.png")); //image de brice
        } catch (IOException ex) {
            System.out.println("Fichier manquant"); //absence de l'image
        }

    }



    public void paint(Graphics g) {
        vueBuilding.drawBuildings(g);
        VueFleur.drawFleur(g);
        VueNuisible.drawNuisibles(g);
        JardinierView.drawJardinier(g);
    }
}

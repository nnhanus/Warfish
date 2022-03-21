package View;

import Modele.Fleur;
import Modele.Jardinier;
import Modele.Nuisible;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Movable{

    public static JardinierView jardvue;
    public VueFleur vuefleur;
    public static VueNuisible vueNuisible;
    private BufferedImage meduse = null;
    private BufferedImage f1 = null;
    private BufferedImage B1 = null;
    private BufferedImage fan1 = null;
    private BufferedImage nuis = null;

    public Movable(JardinierView jardvue, VueFleur vuefleur,VueNuisible vueNuisible) {
        this.vuefleur = vuefleur;
        this.jardvue = jardvue;
        this.vueNuisible = vueNuisible;
        try {
            meduse = ImageIO.read(new File("src/View/Image/jelly.png")); //image de méduse
        } catch (IOException ex) {
            System.out.println("Fichier manquant"); //absence de l'image
        }
        try {
            f1 = ImageIO.read(new File("src/View/Image/fleur_rouge.png")); //image de méduse
        } catch (IOException ex) {
            System.out.println("Fichier manquant"); //absence de l'image
        }
        try {
            B1 = ImageIO.read(new File("src/View/Image/bourgeon_rouge.png")); //image de méduse
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
        for (Fleur f : vuefleur.fleurs) {
            if (!(f.getIsPicked())) {
                if (f.lifespan >= 450) {
                    g.drawImage(B1, f.getX(), f.getY(), 40, 40, null);
                } else if (!(f.getIsDead())) {
                    g.drawImage(f1, f.getX(), f.getY(), 40, 40, null);
                } else if (f.getIsDead()) {
                    g.drawImage(fan1, f.getX(), f.getY(), 40, 40, null);
                }
            }
        }

        for (Jardinier jardinier : jardvue.listjardinier) {
            g.drawImage(meduse, jardinier.getX(), jardinier.getY(), 80, 80, null);
        }

        for (Nuisible n : vueNuisible.nuisibles) {
            g.drawImage(nuis, n.getX(), n.getY(), 50, 50, null);
        }


    }
    public static void updateNuisibles(){
        vueNuisible = new VueNuisible();
    }
}

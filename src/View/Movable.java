package View;

import Modele.Fleur;
import Modele.Jardinier;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Movable{

    public static JardinierView jardvue;
    public VueFleur vuefleur;
    private BufferedImage meduse = null;

    public Movable(JardinierView jardvue, VueFleur vuefleur) {
        this.vuefleur = vuefleur;
        this.jardvue = jardvue;
        try {
            meduse = ImageIO.read(new File("src/View/Image/jelly.png")); //image de méduse
        } catch (IOException ex) {
            System.out.println("Fichier manquant"); //absence de l'image
        }
    }


    public void paint(Graphics g) {
        for (Fleur f : vuefleur.fleurs) {
            if (f.getEstLa()) {
                g.setColor(Color.yellow);
                g.fillRect(f.getX(), f.getY(), 40, 40);
            }
        }
        for (Jardinier jardinier : jardvue.listjardinier) {
            g.drawImage(meduse, jardinier.getX(), jardinier.getY(), 80, 80, null);
        }

    }
}

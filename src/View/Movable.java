package View;

import Modele.Fleur;
import Modele.Jardinier;

import javax.swing.*;
import java.awt.*;

public class Movable{

    public static JardinierView jardvue;
    public VueFleur vuefleur;

    public Movable(JardinierView jardvue, VueFleur vuefleur) {
        this.vuefleur = vuefleur;
        this.jardvue = jardvue;
    }


    public void paint(Graphics g) {
        for (Jardinier jardinier : jardvue.listjardinier) {
            g.fillRect(jardinier.getX(), jardinier.getY(), 80, 80);
        }
        for (Fleur f : vuefleur.fleurs) {
            g.setColor(Color.yellow);
            g.fillRect(f.getX(), f.getY(), 40, 40);
        }
    }
}

package View;

import Modele.Jardinier;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**Cette classe permet de creer la grille de jeu**/
public class Grille extends JPanel {

    public static JardinierView jardvue;

    public Grille (JardinierView jardvue) {
        this.setPreferredSize(new Dimension(800,View.HEIGHT_WIN));
        this.setOpaque(false);
        this.jardvue = jardvue;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;

        g.setColor(Color.BLACK);

        while ( x < 800 ){
            g.drawLine(x, 0, x, 750);
            x += 50;
        }

        while ( y < 800 ){
            g.drawLine(0, y, 800, y);
            y += 50;
        }

        for (Jardinier jardinier : jardvue.listjardinier) {
            g.fillRect(jardinier.x,jardinier.y,50,50);
        }


    }





}
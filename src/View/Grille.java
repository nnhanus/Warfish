package View;


import Modele.Case;
import Modele.Jardinier;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**Cette classe permet de creer la grille de jeu**/
public class Grille extends JPanel {

    public Movable move;

    public Grille () {
        this.setPreferredSize(new Dimension(800,View.HEIGHT_WIN));
        this.setOpaque(false);
        this.move = new Movable(new JardinierView(),new VueFleur());
    }

    @Override
    public void paint(Graphics g) {
        /**arriere plan**/
        try {
            Image img = ImageIO.read(new File("src/View/Image/decor_plateau.png"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur image de fond: " +e.getMessage());
        }

        /**Grille**/
        super.paintComponent(g);


        int x = 0;
        int y = 0;

        g.setColor(Color.BLACK);

        while ( x < 800 ){
            g.drawLine(x, 0, x, 800);
            x += 80;
        }

        while ( y < 800 ){
            g.drawLine(0, y, 800, y);

            y += 80;
        }


        move.paint(g);

    }

}
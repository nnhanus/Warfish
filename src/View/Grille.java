package View;


import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**Cette classe permet de creer la grille de jeu**/
public class Grille extends JPanel {
    Image img = null;

    public static Movable move; //gestion de l'affichage des éléments qui changent

    public Grille () {
        this.setPreferredSize(new Dimension(View.TERRAIN_WIDTH,View.HEIGHT_WIN)); //taille
        this.setOpaque(false);
        move = new Movable(new JardinierView(),new VueFleur(),new VueNuisible(), new BuildingView(), new VueLaquais());
        //chargement de l'image de fond du terrain
        try {
            img = ImageIO.read(new File("src/Image/decor_plateau.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur image de fond: " +e.getMessage());
        }
    }

    @Override
    public void paint(Graphics g) {
        /**arriere plan**/
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        /**éléments du terrain*/
        move.paint(g);
    }
}
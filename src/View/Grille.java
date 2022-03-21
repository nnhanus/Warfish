package View;


import Modele.Building;
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
    public BuildingView BuildV;

    public Grille () {
        this.setPreferredSize(new Dimension(800,View.HEIGHT_WIN));
        this.setOpaque(false);
        this.move = new Movable(new JardinierView(),new VueFleur(),new VueNuisible());
        this.BuildV = new BuildingView();
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

        for(Building b : BuildV.buildings){
            g.fillRect(b.getX(),b.getY(),100,100);
        }

        move.paint(g);

    }

}
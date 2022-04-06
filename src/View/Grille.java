package View;


import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**Cette classe permet de creer la grille de jeu**/
public class Grille extends JPanel {
    Image img = null;

    public static Movable move;
    //public static BuildingView buildV;

    public Grille () {
        this.setPreferredSize(new Dimension(View.TERRAIN_WIDTH,View.HEIGHT_WIN));
        this.setOpaque(false);
        move = new Movable(new JardinierView(),new VueFleur(),new VueNuisible(), new BuildingView());
        //buildV = new BuildingView();
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
        //buildV.drawBuildings(g);
        move.paint(g);
    }

    /*public static void updateBuildView(){
        buildV = new BuildingView();
    }*/
}
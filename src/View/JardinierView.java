package View;


import Modele.GrilleMod;
import Modele.Jardinier;
import Modele.Unite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;


public class JardinierView {
    public static int TAILLE_JARDINIER = 80;

    public static ArrayList<Jardinier> listjardinier = new ArrayList<>();


    public JardinierView(){
        for(Unite u:GrilleMod.getUnites()){
            listjardinier.add((Jardinier) u);
        }
    }

    public static void drawJardinier(Graphics g){
        for (Jardinier jardinier : listjardinier) {
            g.drawImage(Movable.meduse, jardinier.getX() - JardinierView.TAILLE_JARDINIER/2, jardinier.getY() - JardinierView.TAILLE_JARDINIER/2, JardinierView.TAILLE_JARDINIER, JardinierView.TAILLE_JARDINIER, null);
            g.setColor(Color.PINK);
            int sq = (int) Math.sqrt(19000);
            g.drawOval(jardinier.getX() - sq, jardinier.getY() - sq, sq*2, sq*2);
        }
    }
}

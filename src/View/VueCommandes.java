package View;

import Modele.Bouquet;
import Modele.GrilleMod;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class VueCommandes {
    private static JPanel listeCommandes = new JPanel();

    static{
        listeCommandes.setLayout(new GridLayout(4, 1));
        listeCommandes.setPreferredSize(new Dimension(View.LARGEUR_S_MENUS, View.HAUTEUR_S_MENUS));
        listeCommandes.setBounds(75 + View.LARGEUR_S_MENUS, 400, View.LARGEUR_S_MENUS, View.HAUTEUR_S_MENUS);
        listeCommandes.setBackground(Color.MAGENTA);
        listeCommandes.setVisible(true);
    }

    private static final HashMap<Integer, String> imageMap =new HashMap<>() {{
        put(0, "src/Image/BouquetJJJ.png");//JJJ
        put(1, "src/Image/BouquetJJR.png");//JJR (mais à l'envers)
        put(2, "src/Image/BouquetJRR.png");//JRR
        put(3, "src/Image/BouquetRRR.png");//RRR
        put(4, "src/Image/BouquetVJJ.png");//VJJ
        put(5, "src/Image/BouquetVJR.png");//VJR
        put(6, "src/Image/BouquetVRR.png");//VRR
        put(7, "src/Image/BouquetVVJ.png");//VVJ
        put(8, "src/Image/BouquetVVR.png");//VVR
        put(9, "src/Image/BouquetVVV.png");//VVV
    }};

    public static JPanel getListeCommandes(){return listeCommandes;}

    public VueCommandes(){
        for(int i = 0; i < 4; i++) {
            JButton b = new JButton();
            b.setVisible(true);
            listeCommandes.add(b);
        }
    }

    public static void updateCommandes(){
        for(int i = 0; i < 4; i++){
            try{
                ((JButton) listeCommandes.getComponent(i)).setIcon(View.scaleImage(new ImageIcon(imageMap.get(Bouquet.getType(GrilleMod.getCommandes().get(i).getValue())))));
            }catch(IndexOutOfBoundsException e){
                ((JButton) listeCommandes.getComponent(i)).setIcon(null);
            }
        }
    }
}

package View;

import Modele.Bouquet;

import javax.swing.*;
import java.awt.*;

/**
 * Gère la barre indiquant l'état du bouquet en cours de confection
 */
public class VueConfection {
    private static JPanel panel = new JPanel();

    /**
     * createSquare
     * @param i un type de fleur
     * @return un JLabel possédant l'image correspondant au type de fleur
     */
    private static JLabel createSquare(int i){
        String s = null;
        switch(i){ //Sélectionne la bonne image
            case 0:
                s = "src/Image/RougeZoom.png";
                break;
            case 1:
                s = "src/Image/JauneZoom.png";
                break;
            case 2:
                s = "src/Image/VerteZoom.png";
                break;
            default:
                break;
        }
        JLabel l = new JLabel(View.scaleImage(new ImageIcon(s))); //Scale et associe l'image
        l.setPreferredSize(new Dimension(40, 40)); //redimensionne le panel
        l.setVisible(true);
        return l;
    }

    public static JPanel getPanel(){
        return panel;
    }

    static{ //Initialise le JPanel
        panel.setBackground(Color.PINK);
        panel.setVisible(true);
        panel.setBounds(50, 410 + View.HAUTEUR_S_MENUS, View.LARGEUR_S_MENUS, 40);
        panel.setLayout(new GridLayout(1, 3, 7, 0));
    }

    public VueConfection(){
    }

    /**
     * Ajoute une fleur à la barre de confection
     * @param id le type de fleur
     */
    public static void updateConfection(int id){
        panel.add(createSquare(id)); //ajoute un jpanel possédant la bonne image
    }

    /**
     * updateVisibility
     * Fixe la visibilité de la barre sur celle du sous-menu de confection
     */
    public static void updateVisibility(){
        panel.setVisible(View.confection.isVisible());
    }

    /**
     * Vide la barre de confection
     */
    public static void clearVueConfection(){
        panel.removeAll();
    }
}

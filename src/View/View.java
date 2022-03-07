package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class View extends JFrame {

    /**dimensions de la fenêtre d'affichage*/
    public static final int WIDTH_WIN = 1200;
    public static final int HEIGHT_WIN = 800;

    public View() {
        this.setTitle("les fleurs des méduses");
        this.setPreferredSize(new Dimension(WIDTH_WIN,HEIGHT_WIN));
        this.setLayout(new BorderLayout());

        /**création de la partie terrain et de la partie control*/
        JPanel terrain = new JPanel();
        JPanel control = new JPanel();
        JPanel boutons = new JPanel();

        /**dimension des panels principaux*/
        terrain.setPreferredSize(new Dimension(800,HEIGHT_WIN));
        control.setPreferredSize(new Dimension(400,HEIGHT_WIN));

        /**affichage**/
        Grille grille = new Grille();
        terrain.add(grille);
        BuildingView bat1 = new BuildingView();
        terrain.add(bat1);




        /**ajout image*/
        BufferedImage meduse = null;
        try {
            meduse = ImageIO.read(new File("src/View/Image/jelly.png"));
        } catch (IOException e) {
            System.out.println("Fichier manquant");
        }

        JLabel cubomeduse = new JLabel(new ImageIcon(meduse));



        /**changement de la couleur des différentes zones*/
        terrain.setBackground(Color.decode("#0090FC"));
        control.setBackground(Color.PINK);
        boutons.setOpaque(false);

        /**permet de mettre les 2 JPanel côte à côte et de leur attribuer un pourcentage de l'écran*/
        /**GridBagConstraints c = new GridBagConstraints();
         c.fill = GridBagConstraints.BOTH;
         c.weightx = 0.75;
         c.weighty = 1;
         c.gridy = 0;
         this.getContentPane().add(terrain,c);
         c.weightx = 0.25;
         c.weighty = 1;
         this.getContentPane().add(control,c);*/

        this.add(terrain,BorderLayout.WEST);
        this.add(control);


        /** permet de placer les jpanels dans celui de droite*/
        control.setLayout(null);

        boutons.setBounds(50,450,300,100);
        cubomeduse.setBounds(0,150,400,300);

        control.add(cubomeduse);
        control.add(boutons);

        /**Partie sur la création des boutons et leur ajout dans le JPanel
         *
         *
         *  création des boutons*/
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        /**taille des boutons*/
        b1.setPreferredSize(new Dimension(30,30));

        /**permet de mettre les boutons en grille*/
        boutons.setLayout(new GridLayout(0,3,10,10));

        /**ajout des boutons au panel des boutons*/
        boutons.add(b1);
        boutons.add(b2);
        boutons.add(b3);
        boutons.add(b4);
        boutons.add(b5);
        boutons.add(b6);

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    /**
     @Override
     public void paint(Graphics g) {
     g.drawImage(meduse, 0, 0, null);
     }*/
}
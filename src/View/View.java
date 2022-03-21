
package View;

import Modele.GrilleMod;
import Modele.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static Modele.GrilleMod.getBatPrincipal;


public class View extends JFrame {

    /**
     * dimensions de la fenêtre d'affichage
     */
    public static final int WIDTH_WIN = 1200;
    public static final int HEIGHT_WIN = 700;
    public static final int WIDTH_CTRL = 400;
    public static int nbfleur1 = ((Jardinier) Modele.GrilleMod.getUnites().get(0)).getInventaire()[0];
    public static int nbgraines = ((Jardinier) Modele.GrilleMod.getUnites().get(0)).getInventaire()[1];
    public static int nbBouquet = ((Jardinier) Modele.GrilleMod.getUnites().get(0)).getInventaire()[2];
    public static JPanel terrain ;
    public static JPanel control ;
    public static JPanel boutons ;
    public static JPanel argent ;
    public static JPanel boutique;
    public static JPanel inventaire;
    public static JLabel soldeL = new JLabel();
    public static JButton b1 = new JButton("Boutique");
    public static JButton b2 = new JButton("Ramasser");
    public static JButton b3 = new JButton("Effrayer");
    public static JButton b4 = new JButton("Planter");
    public static JButton b5 = new JButton("Désherber");
    public static JButton b6 = new JButton("6");
    public static JLabel inv = new JLabel();

    public Grille grille = new Grille();

    /**boutique de fleurs **/
    public static Icon fleur1 = new ImageIcon("src/View/Image/boutons_achat_fleur.png");
    public static Icon fleur2 = new ImageIcon("src/View/Image/boutons_achat_meduse.png");
    public static Icon fleur3 = new ImageIcon("src/View/Image/boutons_achat_bat.png");
    public static JButton bfleur1 = new JButton(fleur1);
    public static JButton bfleur2 = new JButton(fleur2);
    public static JButton bfleur3 = new JButton(fleur3);


    public View() {
        this.setTitle("Project : Warfish");
        this.setPreferredSize(new Dimension(WIDTH_WIN, HEIGHT_WIN));
        this.setLayout(new BorderLayout());

        /**création de la partie terrain et de la partie control*/
        terrain = new JPanel();
        control = new JPanel();
        boutons = new JPanel();
        argent = new JPanel();
        boutique = new JPanel();
        inventaire = new JPanel();

        /**dimension des panels principaux*/

        terrain.setPreferredSize(new Dimension(800,HEIGHT_WIN));
        control.setPreferredSize(new Dimension(WIDTH_CTRL,HEIGHT_WIN));
        //Grille grille = new Grille(new JardinierView());
        terrain.add(grille);
        //BuildingView bat1 = new BuildingView();
        //terrain.add(bat1);
      

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


        /**
        terrain.setLayout(new GridLayout(10,10));
        Border ligne = BorderFactory.createLineBorder(Color.BLACK,1);
        for(int i = 0;i<100;i++){
            JPanel cube = new JPanel();
            listCube.add(cube);
            cube.setPreferredSize(new Dimension(80,80));
            cube.setBorder(ligne);
            cube.setOpaque(false);
            terrain.add(cube);
        }

        terrain.setBorder(ligne);*/

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


        //inventaire.setPreferredSize(new Dimension(300, 100));

        this.add(terrain, BorderLayout.WEST);
        this.add(control);
        //this.add(inventaire);


        /**boutique**/

        bfleur1.setPreferredSize(new Dimension(30,30));
        boutique.setLayout(new GridLayout(0,1,10,10));
        boutique.add(cubomeduse);
        boutique.add(bfleur1);
        boutique.add(bfleur2);
        boutique.add(bfleur3);
        boutique.setOpaque(false);
        boutique.setVisible(false);

        /**gestion de l'affichage de l'argent**/

        soldeL.setFont(new Font("Serif", Font.PLAIN, 25));
        soldeL.setText("solde : " + String.valueOf(getBatPrincipal().getTirelire()));
        argent.add(soldeL);
        argent.setBackground(Color.PINK);


        inventaire.setLayout(new BoxLayout(inventaire, BoxLayout.PAGE_AXIS));
        inventaire.setBackground(Color.PINK);
        JLabel titre = new JLabel();
        titre.setText("Inventaire");
        titre.setFont(new Font("Serif", Font.PLAIN, 20));
        inv.setFont(new Font("Serif", Font.PLAIN, 20));
        inv.setText("<html>nb fleur1 : " + String.valueOf(nbfleur1) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;nb graines : " + String.valueOf(nbgraines)+"<br/>nb Bouquets :" + String.valueOf(nbBouquet)+"</html>");
        inventaire.add(titre);
        inventaire.add(inv);


        /** permet de placer les jpanels dans celui de droite*/
        control.setLayout(null);


        inventaire.setBounds(50, 0, 300, 100);
        cubomeduse.setBounds(100,100,200,150);
        boutons.setBounds(50,250,300,100);
        argent.setBounds(50,350,300,50);
        boutique.setBounds(50,430,300,225);

        control.add(inventaire);
        control.add(cubomeduse);
        control.add(boutons);
        control.add(boutique);
        control.add(argent);

        /**Partie sur la création des boutons et leur ajout dans le JPanel
         *
         *
         *  création des boutons*/


        /**taille des boutons*/
        b1.setPreferredSize(new Dimension(30, 30));

        /**permet de mettre les boutons en grille*/
        boutons.setLayout(new GridLayout(0, 3, 10, 10));

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


    public static void updateSolde(int val) {
        getBatPrincipal().setTirelire(getBatPrincipal().getTirelire()-val);
        soldeL.setText("solde : " + String.valueOf(getBatPrincipal().getTirelire()));
    }

    public static void updateInv(){
        nbfleur1 = nbfleur1 + 1;
        inv.setText("<html>nb fleur1 : " + String.valueOf(nbfleur1) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;nb graines : " + String.valueOf(nbgraines)+"<br/>nb Bouquets :" + String.valueOf(nbBouquet)+"</html>");


    }
    public static void updateGraines(){
        nbgraines = nbgraines + 1;
        inv.setText("<html>nb fleur1 : " + String.valueOf(nbfleur1) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;nb graines : " + String.valueOf(nbgraines)+"<br/>nb Bouquets :" + String.valueOf(nbBouquet)+"</html>");


    }
}



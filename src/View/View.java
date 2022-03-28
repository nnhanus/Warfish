
package View;

import Modele.BatPrincipal;
import Modele.GrilleMod;
import Modele.Jardinier;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class View extends JFrame {

    /**
     * dimensions de la fenêtre d'affichage
     */
    public static final int WIDTH_WIN = 1200;
    public static final int HEIGHT_WIN = 700;
    public static final int TERRAIN_WIDTH = 800;
    public static int solde = BatPrincipal.getTirelire();
    public static int nbfleur1 = ((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[0];
    public static int nbgraine = ((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[1];
    public static JPanel terrain ;
    public static JPanel control ;
    public static JPanel boutons ;
    public static JPanel argent ;
    public static JPanel boutique;
    public static JPanel inventaire;
    public static JLabel soldeL = new JLabel();
    public static JButton b1 = new JButton("Graine");
    public static JButton b2 = new JButton("Ramasser");
    public static JButton b3 = new JButton("Effrayer");
    public static JButton b4 = new JButton("Planter");
    public static JButton b5 = new JButton("Désherber");
    public static JButton b6 = new JButton("BatProduction");
    public static JButton b7 = new JButton("BatDefense");
    public static JButton b8 = new JButton("Bouquet");
    public static JButton b9 = new JButton("Vendre");
    public static JLabel invFleur1 = new JLabel();
    public static JLabel invFleur2 = new JLabel();
    public static JLabel invFleur3 = new JLabel();
    public static JLabel invGraine = new JLabel();
    public static JLabel invBouquet = new JLabel();

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

        terrain.setPreferredSize(new Dimension(TERRAIN_WIDTH,HEIGHT_WIN));
        control.setPreferredSize(new Dimension(400,HEIGHT_WIN));
        terrain.add(grille);
      

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

        this.add(terrain, BorderLayout.WEST);
        this.add(control);
        //this.add(inventaire);


        /**boutique**/

        bfleur1.setPreferredSize(new Dimension(30,30));
        boutique.setLayout(new GridLayout(0,1,10,10));
        //boutique.add(cubomeduse);
        boutique.add(bfleur1);
        boutique.add(bfleur2);
        boutique.add(bfleur3);
        boutique.setOpaque(false);
        boutique.setVisible(false);

        /**gestion de l'affichage de l'argent**/

        soldeL.setFont(new Font("Serif", Font.PLAIN, 25));
        soldeL.setText("solde : " + String.valueOf(solde));
        argent.add(soldeL);
        argent.setBackground(Color.PINK);


        inventaire.setLayout(new BoxLayout(inventaire, BoxLayout.PAGE_AXIS));
        inventaire.setBackground(Color.PINK);
        JLabel titre = new JLabel();
        titre.setText("Inventaire");
        titre.setFont(new Font("Serif", Font.PLAIN, 20));
        invFleur1.setFont(new Font("Serif", Font.PLAIN, 20));
        //invFleur2.setFont(new Font("Serif", Font.PLAIN, 20));
        //invFleur3.setFont(new Font("Serif", Font.PLAIN, 20));
        invGraine.setFont(new Font("Serif", Font.PLAIN, 20));
        invBouquet.setFont(new Font("Serif", Font.PLAIN, 20));
        updateInv();
        inventaire.add(titre);
        inventaire.add(invFleur1);
        //inventaire.add(invFleur2);
        //inventaire.add(invFleur3);
        inventaire.add(invGraine);
        inventaire.add(invBouquet);


        /** permet de placer les jpanels dans celui de droite*/
        control.setLayout(null);


        boutons.setBounds(50,200,300,100);
        //cubomeduse.setBounds(0,100,400,300);
        boutique.setBounds(50,630,300,225);
        argent.setBounds(50,550,300,50);
        inventaire.setBounds(50, 0, 300, 100);


        control.add(inventaire);
        //control.add(cubomeduse);
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
        boutons.add(b7);
        boutons.add(b8);
        boutons.add(b9);

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    public static void updateSolde() {
        soldeL.setText("solde : " + String.valueOf(BatPrincipal.getTirelire()));
    }

    public static void updateInv(){
        invFleur1.setText("nb fleur rouges: " + String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[GrilleMod.indiceFleurR]));
        //invFleur2.setText("nb fleur jaunes: " + String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[GrilleMod.indiceFleurJ]));
        //invFleur3.setText("nb fleur vertes: " + String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[GrilleMod.indiceFleurV]));
        invGraine.setText("nb graine rouges: " + String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[GrilleMod.indiceGraineR]));
        invBouquet.setText("nb bouquet : " + String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[GrilleMod.indiceBouquet]));
    }
}




package View;

import Modele.BatPrincipal;
import Modele.GrilleMod;
import Modele.Jardinier;

import javax.swing.*;
import java.awt.*;


public class View extends JFrame {

    /**dimensions de la fenêtre d'affichage*/
    public static final int WIDTH_WIN = 1200;
    public static final int HEIGHT_WIN = 700;
    public static final int TERRAIN_WIDTH = 800;
    /**des def pour les affichages*/
    public static int solde = BatPrincipal.getTirelire();
   // public static int nbfleur1 = ((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[0];
    //public static int nbgraine = ((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[1];
    /**les différentes zones de l'affichage*/
    public static JPanel terrain ;
    public static JPanel control ;
    public static JPanel boutons ;
    public static JPanel argent ;
    public static JPanel graines;
    public static JPanel inventaire;
    public static JPanel buildings;
    public static JPanel planter;
    /**soldel*/
    public static JLabel soldeL = new JLabel();
    /**boutons*/
    public static JButton b1 = new JButton("Ramasser");
    public static JButton b2 = new JButton("Effrayer");
    public static JButton b3 = new JButton("Désherber");
    public static JButton b4 = new JButton("Planter");
    public static JButton b5 = new JButton("Bouquet");
    public static JButton b6 = new JButton("Vendre");
    public static JButton b7 = new JButton("Graines");
    public static JButton b8 = new JButton("Bâtiments");
    /**des labels again*/
    public static JLabel invFleur1 = new JLabel();
    public static JLabel invFleur2 = new JLabel();
    public static JLabel invFleur3 = new JLabel();
    public static JLabel invGraine1 = new JLabel();
    public static JLabel invGraine2 = new JLabel();
    public static JLabel invGraine3 = new JLabel();
    //public static JLabel invBouquet = new JLabel();

    public Grille grille = new Grille();

    /**boutique de fleurs **/
    public static JButton bfr;
    public static JButton bfj;
    public static JButton bfv;

    /**plantation de graines*/
    public static JButton bpr;
    public static JButton bpj;
    public static JButton bpv;

    /**boutique de batiments*/
    public static JButton prod = new JButton("Production");
    public static JButton def = new JButton("Défense");

    /**c'est pour l'affichage de l'inventaire*/
    protected static ImageIcon fleurRouge = new ImageIcon("src/Image/RougeZoom.png");
    protected static ImageIcon fleurJaune = new ImageIcon("src/Image/JauneZoom.png");
    protected static ImageIcon fleurVerte = new ImageIcon("src/Image/VerteZoom.png");
    protected static ImageIcon graineRouge = new ImageIcon("src/Image/graineRouge.png");
    protected static ImageIcon graineJaune = new ImageIcon("src/Image/graineJaune.png");
    protected static ImageIcon graineVerte = new ImageIcon("src/Image/graineVert.png");
    protected static JLabel fRouge;
    protected static JLabel fJaune;
    protected static JLabel fVerte;
    protected static JLabel gRouge;
    protected static JLabel gJaune;
    protected static JLabel gVerte;

    /**
     * Redimensionne une image icon
     * @param img l'imageIcon a redimensionner
     * @return l'imageIcon redimensionner
     */
    protected ImageIcon scaleImage(ImageIcon img){
        Image im = img.getImage();
        Image scaled = im.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    /**
     * Initialisation de l'affichage de l'inventaire
     */
    protected void Inventaire(){
        inventaire = new JPanel();
        inventaire.setBounds(50, 25, 300, 175);
        /** Gestion de l'affichage de l'inventaire*/
        inventaire.setLayout(new GridLayout(4,3)); //définition du layout
        inventaire.setBackground(Color.PINK); //définition de la couleur de fond
        //Gestion de la taille de police des différents labels
        invFleur1.setFont(new Font("Serif", Font.PLAIN, 20));
        invFleur2.setFont(new Font("Serif", Font.PLAIN, 20));
        invFleur3.setFont(new Font("Serif", Font.PLAIN, 20));
        invGraine1.setFont(new Font("Serif", Font.PLAIN, 20));
        invGraine2.setFont(new Font("Serif", Font.PLAIN, 20));
        invGraine3.setFont(new Font("Serif", Font.PLAIN, 20));
        //invBouquet.setFont(new Font("Serif", Font.PLAIN, 20));
        //récupération des valeurs à afficher
        updateInv();
        //initialisation des images utilisées dans l'affichage de l'inventaire
        fRouge = new JLabel(scaleImage(fleurRouge));
        fJaune = new JLabel(scaleImage(fleurJaune));
        fVerte = new JLabel(scaleImage(fleurVerte));
        gRouge = new JLabel(scaleImage(graineRouge));
        gJaune = new JLabel(scaleImage(graineJaune));
        gVerte = new JLabel(scaleImage(graineVerte));
        //ajout des différents composants de l'inventaire
        inventaire.add(fRouge);
        inventaire.add(fJaune);
        inventaire.add(fVerte);
        inventaire.add(invFleur1);
        inventaire.add(invFleur2);
        inventaire.add(invFleur3);
        inventaire.add(gRouge);
        inventaire.add(gJaune);
        inventaire.add(gVerte);
        inventaire.add(invGraine1);
        inventaire.add(invGraine2);
        inventaire.add(invGraine3);
        //inventaire.add(invBouquet);
    }

    /**
     * Initialisation de l'affichage du terrain
     */
    protected void Terrain(){
        terrain = new JPanel();
        terrain.setPreferredSize(new Dimension(TERRAIN_WIDTH,HEIGHT_WIN));
        terrain.add(grille);
        terrain.setBackground(Color.decode("#0090FC"));
        this.add(terrain, BorderLayout.WEST);
    }

    /**
     * Initialisation de l'affichage de l'argent
     */
    protected void Argent(){
        argent = new JPanel();
        /**gestion de l'affichage de l'argent**/
        soldeL.setFont(new Font("Serif", Font.PLAIN, 25));
        soldeL.setText("solde : " + String.valueOf(solde));
        argent.add(soldeL);
        argent.setBackground(Color.PINK);
        argent.setBounds(50,200,300,50);
    }

    /**
     * Initialisation de l'affichage des controles
     */
    protected void Controle(){
        //Définition de taille, couleur de fond et layout
        control = new JPanel();
        control.setPreferredSize(new Dimension(400,HEIGHT_WIN));
        control.setBackground(Color.PINK);
        control.setLayout(null);

        //inventaire
        JLabel titre = new JLabel();
        titre.setText("Inventaire");
        titre.setFont(new Font("Serif", Font.PLAIN, 20));
        titre.setBounds(50, 0, 300, 25);

        //ajout des différents composants de control
        control.add(titre);
        control.add(inventaire);
        control.add(argent);
        control.add(boutons);
        control.add(graines);
        control.add(planter);
        control.add(buildings);

        //ajout de control a la fenêtre principale
        this.add(control);
    }

    /**
     * Initialisation des boutons
     */
    protected void Boutons(){
        boutons = new JPanel();
        boutons.setOpaque(false);
        boutons.setBounds(50,250,300,100);

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
    }

    /**
     * Initialisation de l'affichage de la boutique de graines
     */
    protected void Graines(){
        graines = new JPanel();
        graines.setBounds(50,400,300,200);
        graines.setVisible(false);
        graines.setOpaque(false);
        bfr = new JButton(scaleImage(fleurRouge));
        bfj = new JButton(scaleImage(fleurJaune));
        bfv = new JButton(scaleImage(fleurVerte));
        bfr.setPreferredSize(new Dimension(30,30));
        graines.setLayout(new GridLayout(3,1,10,10));
        graines.add(bfr);
        graines.add(bfj);
        graines.add(bfv);
    }

    /**
     * Initialisation de l'affichage de la boutique de bâtiments
     */
    protected void Buildings(){
        buildings = new JPanel();
        buildings.setBounds(50,400,300,200);
        buildings.setVisible(false);
        buildings.setOpaque(false);
        def.setPreferredSize(new Dimension(30,30));
        buildings.setLayout(new GridLayout(0,1,10,10));
        buildings.add(prod);
        buildings.add(def);
    }

    /**
     * Initialisation de l'affichage du menu de plantage de fleur
     */
    protected void Planter(){
        planter = new JPanel();
        planter.setBounds(50,400,300,200);
        planter.setVisible(false);
        planter.setOpaque(false);
        bpr = new JButton(scaleImage(fleurRouge));
        bpj = new JButton(scaleImage(fleurJaune));
        bpv = new JButton(scaleImage(fleurVerte));
        bpr.setPreferredSize(new Dimension(30,30));
        planter.setLayout(new GridLayout(3,1,10,10));
        planter.add(bpr);
        planter.add(bpj);
        planter.add(bpv);
    }

    public View() {
        /**Titre de la fenêtre; taille; layout */
        this.setTitle("Project : Warfish");
        this.setPreferredSize(new Dimension(WIDTH_WIN, HEIGHT_WIN));
        this.setLayout(new BorderLayout());

        /**Initialiation des différentes zones de l'affichage*/
        Terrain();
        Inventaire();
        Argent();
        Boutons();
        Graines();
        Buildings();
        Planter();
        Controle();


        /**boutique**/
        /*bfleur1.setPreferredSize(new Dimension(30,30));
        graines.setLayout(new GridLayout(0,1,10,10));
        //boutique.add(cubomeduse);
        graines.add(bfleur1);
        graines.add(bfleur2);
        graines.add(bfleur3);
        graines.setOpaque(false);
        graines.setVisible(false);*/

        /** permet de placer les jpanels dans celui de droite*/




        //le titre de l'inventaire


        /**Partie sur la création des boutons et leur ajout dans le JPanel
         *
         *
         *  création des boutons*/



        //boutons.add(b9);


        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    public static void updateSolde() {
        soldeL.setText("solde : " + String.valueOf(BatPrincipal.getTirelire()));
    }

    public static void updateInv(){
        invFleur1.setText(String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedJard()).getInventaire()[GrilleMod.indiceFleurR]));
        invFleur2.setText(String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedJard()).getInventaire()[GrilleMod.indiceFleurJ]));
        invFleur3.setText(String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedJard()).getInventaire()[GrilleMod.indiceFleurV]));
        invGraine1.setText(String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedJard()).getInventaire()[GrilleMod.indiceGraineR]));
        invGraine2.setText(String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedJard()).getInventaire()[GrilleMod.indiceGraineJ]));
        invGraine3.setText(String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedJard()).getInventaire()[GrilleMod.indiceGraineV]));
        //invBouquet.setText("nb bouquet : " + String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[GrilleMod.indiceBouquet]));
    }
}



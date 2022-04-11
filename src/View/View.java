
package View;

import Modele.BatPrincipal;
import Modele.Bouquet;
import Modele.GrilleMod;
import Modele.Jardinier;

import javax.swing.*;
import java.awt.*;


public class View extends JFrame {

    public static Font police_inventaire = new Font("Comic-Sans", Font.BOLD, 20);
    public static Font police_confection = new Font("Serif", Font.BOLD, 22);
    public static Font police_prix = new Font("Comic-Sans", Font.BOLD, 22);
    public static Font police_score = new Font("Comic-Sans", Font.BOLD, 25);

    /**dimensions de la fenêtre d'affichage*/
    public static final int WIDTH_WIN = 1200;
    public static final int HEIGHT_WIN = 700;
    public static final int TERRAIN_WIDTH = 800;

    public static final int LARGEUR_S_MENUS = 150;
    public static final int HAUTEUR_S_MENUS = 200;

    public static final int LARGEUR_INVENTAIRE = 300;
    public static final int HAUTEUR_INVENTAIRE = 175;

    /**des def pour les affichages*/
    public static int solde = BatPrincipal.getTirelire();
    /**les différentes zones de l'affichage*/
    public static JPanel terrain ;
    public static JPanel control ;
    public static JPanel boutons ;
    public static JPanel argent ;
    public static JPanel graines;
    public static JPanel inventaire;
    public static JPanel buildings;
    public static JPanel planter;
    public static JPanel confection;
    public static JPanel recruter;

    /**soldel*/
    public static JLabel soldeL = new JLabel();

    /**Score*/
    public static JLabel score = new JLabel();
    public static JPanel scorePane = new JPanel();
    static{
        //scorePane.setBounds(75 + View.LARGEUR_S_MENUS, 720, View.LARGEUR_S_MENUS, 15);
        score.setFont(police_score);
        score.setText("Score : " + String.valueOf(GrilleMod.getScore()));
        score.setBounds(75 + View.LARGEUR_S_MENUS, HEIGHT_WIN - 50, View.LARGEUR_S_MENUS, 25);
        score.setVisible(true);
        //scorePane.setBackground(Color.PINK);
    }

    /**boutons*/
    public static JButton ramasserButton = new JButton("Ramasser");
    public static JButton effrayerButton = new JButton("Effrayer");
    public static JButton desherberButton = new JButton("Désherber");
    public static JButton planterMenuButton = new JButton("Planter");
    public static JButton bouquetMenuButton = new JButton("Bouquet");
    public static JButton vendreButton = new JButton("Vendre");
    public static JButton grainesBoutiqueButton = new JButton("Graines");
    public static JButton batimentsBoutiqueButton = new JButton("Bâtiments");
    public static JButton recruterBoutiqueButton = new JButton("Recruter");

    /**des labels again*/
    public static JLabel invFleur1 = new JLabel();
    public static JLabel invFleur2 = new JLabel();
    public static JLabel invFleur3 = new JLabel();
    public static JLabel invGraine1 = new JLabel();
    public static JLabel invGraine2 = new JLabel();
    public static JLabel invGraine3 = new JLabel();

    /**Affichage des différents bouquets de l'inventaire*/
    public static JLabel invBouquetJJJ = new JLabel();
    public static JLabel invBouquetJJR = new JLabel();
    public static JLabel invBouquetJRR = new JLabel();
    public static JLabel invBouquetRRR = new JLabel();
    public static JLabel invBouquetVJJ = new JLabel();
    public static JLabel invBouquetVJR = new JLabel();
    public static JLabel invBouquetVRR = new JLabel();
    public static JLabel invBouquetVVJ = new JLabel();
    public static JLabel invBouquetVVR = new JLabel();
    public static JLabel invBouquetVVV = new JLabel();

    public Grille grille = new Grille();

    /**boutique de fleurs **/
    public static JButton bfr;
    public static JButton bfj;
    public static JButton bfv;

    /**plantation de graines*/
    public static JButton bpr;
    public static JButton bpj;
    public static JButton bpv;

    /**confection de bouquet*/
    public static JButton bpbr;
    public static JButton bpbj;
    public static JButton bpbv;
    public static JButton valider;
    public static JButton annuler;

    /**recrutement*/
    public static JButton jardinierButton;
    public static JButton laquaisButton;

    /**boutique de batiments*/
    public static JButton prod = new JButton();
    public static JButton def = new JButton();

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

    protected static ImageIcon bouquetJJJ = new ImageIcon("src/Image/BouquetJJJ.png");
    protected static ImageIcon bouquetJJR = new ImageIcon("src/Image/BouquetJJR.png");
    protected static ImageIcon bouquetJRR = new ImageIcon("src/Image/BouquetJRR.png");
    protected static ImageIcon bouquetRRR = new ImageIcon("src/Image/BouquetRRR.png");
    protected static ImageIcon bouquetVJJ = new ImageIcon("src/Image/BouquetVJJ.png");
    protected static ImageIcon bouquetVJR = new ImageIcon("src/Image/BouquetVJR.png");
    protected static ImageIcon bouquetVRR = new ImageIcon("src/Image/BouquetVRR.png");
    protected static ImageIcon bouquetVVJ = new ImageIcon("src/Image/BouquetVVJ.png");
    protected static ImageIcon bouquetVVR = new ImageIcon("src/Image/BouquetVVR.png");
    protected static ImageIcon bouquetVVV = new ImageIcon("src/Image/BouquetVVV.png");
    protected static JLabel bJJJ;
    protected static JLabel bJJR;
    protected static JLabel bJRR;
    protected static JLabel bRRR;
    protected static JLabel bVJJ;
    protected static JLabel bVJR;
    protected static JLabel bVRR;
    protected static JLabel bVVJ;
    protected static JLabel bVVR;
    protected static JLabel bVVV;

    /**
     * Redimensionne une image icon
     * @param img l'imageIcon a redimensionner
     * @return l'imageIcon redimensionner
     */
    protected static ImageIcon scaleImage(ImageIcon img){
        Image im = img.getImage();
        Image scaled = im.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    /**
     * Initialisation de l'affichage de l'inventaire
     */
    protected void Inventaire(){
        inventaire = new JPanel();
        inventaire.setBounds(50, 25, LARGEUR_INVENTAIRE, HAUTEUR_INVENTAIRE);

        /** Gestion de l'affichage de l'inventaire*/
        inventaire.setLayout(new GridLayout(1,2)); //définition du layout
        inventaire.setBackground(Color.PINK); //définition de la couleur de fond

        /** Setup de l'inventaire de fleurs*/
        JPanel inventaire_fleurs = new JPanel();
        inventaire_fleurs.setBounds(50, 25, LARGEUR_INVENTAIRE/2, HAUTEUR_INVENTAIRE);
        inventaire_fleurs.setBackground(Color.PINK);
        inventaire_fleurs.setLayout(new GridLayout(4, 3));

        /** Setup de l'inventaire des bouquets*/
        JPanel inventaire_bouquets = new JPanel();
        inventaire_bouquets.setBounds(50+LARGEUR_INVENTAIRE/2, 25, LARGEUR_INVENTAIRE/2, HAUTEUR_INVENTAIRE);
        inventaire_bouquets.setBackground(Color.PINK);
        inventaire_bouquets.setLayout(new GridLayout(4, 5, 5, 0));

        /**Param du texte*/
        //Gestion de la taille de police des différents labels
        invFleur1.setFont(police_inventaire);
        invFleur2.setFont(police_inventaire);
        invFleur3.setFont(police_inventaire);
        invGraine1.setFont(police_inventaire);
        invGraine2.setFont(police_inventaire);
        invGraine3.setFont(police_inventaire);

        invBouquetJJJ.setFont(police_inventaire);
        invBouquetJJR.setFont(police_inventaire);
        invBouquetJRR.setFont(police_inventaire);
        invBouquetRRR.setFont(police_inventaire);
        invBouquetVJJ.setFont(police_inventaire);
        invBouquetVJR.setFont(police_inventaire);
        invBouquetVRR.setFont(police_inventaire);
        invBouquetVVJ.setFont(police_inventaire);
        invBouquetVVR.setFont(police_inventaire);
        invBouquetVVV.setFont(police_inventaire);
        //récupération des valeurs à afficher
        updateInv();
        //initialisation des images utilisées dans l'affichage de l'inventaire
        fRouge = new JLabel(scaleImage(fleurRouge));
        fJaune = new JLabel(scaleImage(fleurJaune));
        fVerte = new JLabel(scaleImage(fleurVerte));
        gRouge = new JLabel(scaleImage(graineRouge));
        gJaune = new JLabel(scaleImage(graineJaune));
        gVerte = new JLabel(scaleImage(graineVerte));

        bJJJ = new JLabel(scaleImage(bouquetJJJ));
        bJJR = new JLabel(scaleImage(bouquetJJR));
        bJRR = new JLabel(scaleImage(bouquetJRR));
        bRRR = new JLabel(scaleImage(bouquetRRR));
        bVJJ = new JLabel(scaleImage(bouquetVJJ));
        bVJR = new JLabel(scaleImage(bouquetVJR));
        bVRR = new JLabel(scaleImage(bouquetVRR));
        bVVJ = new JLabel(scaleImage(bouquetVVJ));
        bVVR = new JLabel(scaleImage(bouquetVVR));
        bVVV = new JLabel(scaleImage(bouquetVVV));

        //ajout des différents composants de l'inventaire
        inventaire_fleurs.add(fRouge);
        inventaire_fleurs.add(fJaune);
        inventaire_fleurs.add(fVerte);
        inventaire_fleurs.add(invFleur1);
        inventaire_fleurs.add(invFleur2);
        inventaire_fleurs.add(invFleur3);
        inventaire_fleurs.add(gRouge);
        inventaire_fleurs.add(gJaune);
        inventaire_fleurs.add(gVerte);
        inventaire_fleurs.add(invGraine1);
        inventaire_fleurs.add(invGraine2);
        inventaire_fleurs.add(invGraine3);

        inventaire_bouquets.add(bJJJ);
        inventaire_bouquets.add(bJJR);
        inventaire_bouquets.add(bJRR);
        inventaire_bouquets.add(bRRR);
        inventaire_bouquets.add(bVJJ);
        inventaire_bouquets.add(invBouquetJJJ);
        inventaire_bouquets.add(invBouquetJJR);
        inventaire_bouquets.add(invBouquetJRR);
        inventaire_bouquets.add(invBouquetRRR);
        inventaire_bouquets.add(invBouquetVJJ);

        inventaire_bouquets.add(bVJR);
        inventaire_bouquets.add(bVRR);
        inventaire_bouquets.add(bVVJ);
        inventaire_bouquets.add(bVVR);
        inventaire_bouquets.add(bVVV);
        inventaire_bouquets.add(invBouquetVJR);
        inventaire_bouquets.add(invBouquetVRR);
        inventaire_bouquets.add(invBouquetVVJ);
        inventaire_bouquets.add(invBouquetVVR);
        inventaire_bouquets.add(invBouquetVVV);

        inventaire.add(inventaire_fleurs);
        inventaire.add(inventaire_bouquets);
    }

    /**
     * Initialisation de l'affichage du terrain
     */
    protected void Terrain(){
        terrain = new JPanel(); //définition
        terrain.setPreferredSize(new Dimension(TERRAIN_WIDTH,HEIGHT_WIN)); //taille
        terrain.add(grille); //ajout de la grille
        terrain.setBackground(Color.decode("#0090FC")); //couleur
        this.add(terrain, BorderLayout.WEST); //ajout à la fenetre principale
    }

    /**
     * Initialisation de l'affichage de l'argent
     */
    protected void Argent(){
        argent = new JPanel(); //définition
        /**gestion de l'affichage de l'argent**/
        soldeL.setFont(police_score);
        soldeL.setText("Solde : " + String.valueOf(solde));
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
        //titre.setFont();
        titre.setFont(police_score);
        titre.setBounds(50, 0, 300, 25);

        //ajout des différents composants de control
        control.add(titre);
        control.add(inventaire);
        control.add(argent);
        control.add(boutons);
        control.add(graines);
        control.add(planter);
        control.add(buildings);
        control.add(confection);
        control.add(VueCommandes.getListeCommandes());
        control.add(VueConfection.getPanel());
        control.add(recruter);
        control.add(score);
        //ajout de control a la fenêtre principale
        this.add(control);
    }

    /**
     * Initialisation des boutons
     */
    protected void Boutons(){
        boutons = new JPanel(); //définition
        boutons.setOpaque(false);
        boutons.setBounds(50,250,300,100);

        /**taille des boutons*/
        ramasserButton.setPreferredSize(new Dimension(30, 30));

        /**permet de mettre les boutons en grille*/
        boutons.setLayout(new GridLayout(0, 3, 10, 10));

        /**ajout des boutons au panel des boutons*/
        boutons.add(ramasserButton);
        boutons.add(planterMenuButton);
        boutons.add(bouquetMenuButton);
        boutons.add(desherberButton);
        boutons.add(grainesBoutiqueButton);
        boutons.add(batimentsBoutiqueButton);
        boutons.add(effrayerButton);
        boutons.add(recruterBoutiqueButton);
    }

    /**
     * Initialisation de l'affichage de la boutique de graines
     */
    protected void Graines(){
        graines = new JPanel(); //définition
        graines.setBounds(50,400,LARGEUR_S_MENUS, HAUTEUR_S_MENUS);
        graines.setVisible(false); //non-visible car sous-menu à ouvrir
        graines.setOpaque(false);
        //ajout des composants
        bfr = new JButton(scaleImage(graineRouge));
        bfr.setIconTextGap(10);
        bfr.setText(String.valueOf(BatPrincipal.PRIX_GRAINE));
        bfr.setFont(police_prix);
        bfj = new JButton(scaleImage(graineJaune));
        bfj.setIconTextGap(10);
        bfj.setText(String.valueOf(BatPrincipal.PRIX_GRAINE));
        bfj.setFont(police_prix);
        bfv = new JButton(scaleImage(graineVerte));
        bfv.setIconTextGap(10);
        bfv.setText(String.valueOf(BatPrincipal.PRIX_GRAINE));
        bfv.setFont(police_prix);
        graines.setPreferredSize(new Dimension(30,30));
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
        buildings.setBounds(50,400,LARGEUR_S_MENUS, HAUTEUR_S_MENUS);
        buildings.setVisible(false); //non-visible car sous-menu à ouvrir
        buildings.setOpaque(false);
        buildings.setPreferredSize(new Dimension(30,30));
        buildings.setLayout(new GridLayout(2,1,0,10));
        prod.setIcon(scaleImage(new ImageIcon("src/Image/maison3.png")));
        prod.setText(String.valueOf(BatPrincipal.PRIX_PRODUCTION));
        prod.setIconTextGap(10);
        prod.setFont(police_prix);
        def.setIcon(scaleImage(new ImageIcon("src/Image/maison1.png")));
        def.setText(String.valueOf(BatPrincipal.PRIX_DEFENSE));
        def.setIconTextGap(10);
        def.setFont(police_prix);
        //ajout des composants
        buildings.add(prod);
        buildings.add(def);
    }

    protected void Planter(){
        planter = new JPanel();
        planter.setBounds(50,400,LARGEUR_S_MENUS, HAUTEUR_S_MENUS);
        planter.setVisible(false); //non-visible car sous-menu à ouvrir
        planter.setOpaque(false);
        //ajout des composants
        bpr = new JButton(scaleImage(fleurRouge));
        bpj = new JButton(scaleImage(fleurJaune));
        bpv = new JButton(scaleImage(fleurVerte));
        bpr.setPreferredSize(new Dimension(30,30)); //c'est pas planter ici plutôt ???)
        planter.setLayout(new GridLayout(3,1,10,10));
        planter.add(bpr);
        planter.add(bpj);
        planter.add(bpv);
    }

    protected void Confectionner(){
        confection = new JPanel();
        confection.setBounds(50,400,LARGEUR_S_MENUS, HAUTEUR_S_MENUS);
        confection.setVisible(false); //non-visible car sous-menu à ouvrir
        confection.setOpaque(false);
        //ajout des composants
        bpbr = new JButton(scaleImage(fleurRouge));
        bpbj = new JButton(scaleImage(fleurJaune));
        bpbv = new JButton(scaleImage(fleurVerte));
        valider = new JButton("✓");
        valider.setFont(police_confection);
        annuler = new JButton("X");
        annuler.setFont(police_confection);
        bpbr.setPreferredSize(new Dimension(30, 30));
        confection.setLayout(new GridLayout(4, 1, 10, 10));
        confection.add(bpbr);
        confection.add(bpbj);
        confection.add(bpbv);
        JPanel lastrow = new JPanel();
        lastrow.setPreferredSize(new Dimension(30, 30));
        lastrow.setLayout(new GridLayout(1, 2, 10, 5));
        lastrow.setBackground(Color.PINK);
        lastrow.add(valider);
        lastrow.add(annuler);
        confection.add(lastrow);
    }

    protected void Recruter(){ //TODO
        recruter = new JPanel();
        recruter.setBounds(50,400,LARGEUR_S_MENUS, HAUTEUR_S_MENUS);
        recruter.setVisible(false); //non-visible car sous-menu à ouvrir
        recruter.setOpaque(false);
        recruter.setLayout(new GridLayout(2, 1, 0, 10));
        //ajout des composants
        jardinierButton = new JButton(scaleImage(new ImageIcon("src/Image/meduseJardiniere.png")));
        jardinierButton.setFont(police_prix);
        jardinierButton.setText(String.valueOf(BatPrincipal.PRIX_JARDINIER));
        jardinierButton.setIconTextGap(10);
        laquaisButton = new JButton(scaleImage(new ImageIcon("src/Image/Laquais.png"))); //TODO
        laquaisButton.setFont(police_prix);
        laquaisButton.setText(String.valueOf(BatPrincipal.PRIX_LAQUAIS));
        laquaisButton.setIconTextGap(10);
        recruter.add(jardinierButton);
        recruter.add(laquaisButton);
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
        Confectionner();
        Recruter();
        Controle();

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * updateSolde
     * Met à jour l'affichage du solde
     */
    public static void updateSolde() {
        soldeL.setText("Solde : " + String.valueOf(BatPrincipal.getTirelire()));
    }

    public static void updateScore(){
        score.setText("Score : " + String.valueOf(GrilleMod.getScore()));
    }
    /**
     * updateInv
     * Met à jour l'affichage de l'inventaire
     */
    public static void updateInv(){
        invFleur1.setText(String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[GrilleMod.indiceFleurR]));
        invFleur2.setText(String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[GrilleMod.indiceFleurJ]));
        invFleur3.setText(String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[GrilleMod.indiceFleurV]));
        invGraine1.setText(String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[GrilleMod.indiceGraineR]));
        invGraine2.setText(String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[GrilleMod.indiceGraineJ]));
        invGraine3.setText(String.valueOf(((Jardinier) Modele.GrilleMod.getSelectedUnite()).getInventaire()[GrilleMod.indiceGraineV]));

        invBouquetJJJ.setText(String.valueOf(GrilleMod.getBouquets()[Bouquet.getType(new int[]{1,1,1})]));
        invBouquetJJR.setText(String.valueOf(GrilleMod.getBouquets()[Bouquet.getType(new int[]{0,1,1})]));
        invBouquetJRR.setText(String.valueOf(GrilleMod.getBouquets()[Bouquet.getType(new int[]{0,0,1})]));
        invBouquetRRR.setText(String.valueOf(GrilleMod.getBouquets()[Bouquet.getType(new int[]{0,0,0})]));
        invBouquetVJJ.setText(String.valueOf(GrilleMod.getBouquets()[Bouquet.getType(new int[]{1,1,2})]));
        invBouquetVJR.setText(String.valueOf(GrilleMod.getBouquets()[Bouquet.getType(new int[]{0,1,2})]));
        invBouquetVRR.setText(String.valueOf(GrilleMod.getBouquets()[Bouquet.getType(new int[]{0,0,2})]));
        invBouquetVVJ.setText(String.valueOf(GrilleMod.getBouquets()[Bouquet.getType(new int[]{1,2,2})]));
        invBouquetVVR.setText(String.valueOf(GrilleMod.getBouquets()[Bouquet.getType(new int[]{0,2,2})]));
        invBouquetVVV.setText(String.valueOf(GrilleMod.getBouquets()[Bouquet.getType(new int[]{2,2,2})]));
    }
}



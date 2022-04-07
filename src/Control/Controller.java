package Control;

import Modele.*;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static Modele.GrilleMod.*;

public class Controller implements ActionListener, MouseListener {

    public View view;

    public
    Controller(View view) {
        this.view = view;
        //Ajout des listener aux boutons
        View.ramasserButton.addActionListener(this);
        View.effrayerButton.addActionListener(this);
        View.desherberButton.addActionListener(this);
        View.planterMenuButton.addActionListener(this);
        View.bouquetMenuButton.addActionListener(this);
        View.vendreButton.addActionListener(this);
        View.grainesBoutiqueButton.addActionListener(this);
        View.batimentsBoutiqueButton.addActionListener(this);
        View.acheterJardinierButton.addActionListener(this);
        //View.b9.addActionListener(this);
        View.terrain.addMouseListener(this);
        View.bfr.addActionListener(this);
        View.bfv.addActionListener(this);
        View.bfj.addActionListener(this);
        View.bpr.addActionListener(this);
        View.bpv.addActionListener(this);
        View.bpj.addActionListener(this);
        View.bpbr.addActionListener(this);
        View.bpbj.addActionListener(this);
        View.bpbv.addActionListener(this);
        View.valider.addActionListener(this);
        View.annuler.addActionListener(this);
        View.prod.addActionListener(this);
        View.def.addActionListener(this);

        for(Component c : VueCommandes.getListeCommandes().getComponents()){
            if(c.getClass() == JButton.class){
                ((JButton) c).addActionListener(this);
            }
        }
    }

    /**
     * Achat d'une graine
     * @param i l'indice de la graine à acheter, cf GrilleMod
     * @param j le jardinier qui achète la graine
     */
    protected void acheteGrain(int i, Jardinier j){
        /*Conditions: avoir assez d'argent et être au bâtiment principal*/
        if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_GRAINE
                && GrilleMod.getSQDist(j.getX(), j.getY(), GrilleMod.getBatX(), GrilleMod.getBatY()) <= GrilleMod.getBatPrincipal().getRange()) {
            //Acaht de la graine et ajout à l'inventaire
            j.acheterGraine(i);
            BatPrincipal.acheterGraine(i);
            //mise a jour de l'affichage
            View.updateSolde();
            View.updateInv();
        }
    }

    /**
     * Plantation d'une fleur
     * @param i le type de fleur à planter
     * @param j le jardinier qui plante une fleur
     */
    protected void planterGraine(int i, Jardinier j){
        /*Conditions: position valide et graine dans l'inventaire du joueur)*/
        if (!(GrilleMod.isNotValidPosition(j.getX(), j.getY())) && j.getInventaire()[i] > 0){
            j.planteFleur(i); //planter la fleur
            //mise à jour de la vue
            View.updateInv();
            VueFleur.updateFleur();
        }
    }

    /**
     * Ferme tous les sous-menus sauf un
     * @param j le sous-menu à ne pas fermer
     */
    public void closeAllElse(JPanel j){
        //Récupération des JPanel
        ArrayList<JPanel> sous_menu = new ArrayList<>();
        sous_menu.add(View.graines);
        sous_menu.add(View.buildings);
        sous_menu.add(View.planter);
        sous_menu.add(View.confection);
        for(JPanel jp : sous_menu){ //parcours
           if(jp.equals(j)){ //si le jpanel est le jpanel passé en paramètres
               j.setVisible(!j.isVisible()); //on inverse sa visibilté
           }else{ //sinon
               jp.setVisible(false); //on ferme le sous-menu
           }
        }
        VueConfection.updateVisibility(); //Fixe l'affichage de la barre de confection sur la visibilité du sous-menu de confection
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Jardinier j = GrilleMod.getSelectedUnite();

        /**Récolter une fleur*/
        if (e.getSource() == View.ramasserButton) {
            Fleur f = j.plusProcheFleur(); //récupération de la fleur la plus proche
            int dist = j.getSQDistFrom(f.getX(), f.getY()); //sa distance au jardinier
            if (f.isPickable() && dist < 3000) { /*Conditions : la fleur peut être ramassée et le jardinier est assez proche*/
                j.recolterRessource(f); //récolter la fleur
                View.updateInv(); //mise à jour de la vue
            }
        }

        /**Effrayer un nuisible*/
        if (e.getSource() == View.effrayerButton) {
            j.effrayer();
        }

        /**Désherber une fleur morte*/
        if (e.getSource() == View.desherberButton) {
            Fleur f = j.plusProcheFleur(); //récupération de la fleur la plus proche
            int dist = j.getSQDistFrom(f.getX(), f.getY()); //sa distance au jardinier
            if (dist < 30000 && (f).getIsDead()) { //Conditions: le jardinier est assez proche et la fleur est morte
                j.desherber(f); //désherber
            }
        }

        /**Ouverture de sous-menu de plantation*/
        if(e.getSource() == View.planterMenuButton){
            closeAllElse(View.planter);
        }

        /**Ouverture de sous-menu de confection de bouquet*/
        if(e.getSource() == View.bouquetMenuButton){
            closeAllElse(View.confection);
        }

        /**Ouverture de la boutique de graines*/
        if(e.getSource() == View.grainesBoutiqueButton){
            closeAllElse(View.graines);
        }

        /**Ouverture de la boutique de bâtiments*/
        if(e.getSource() == View.batimentsBoutiqueButton){
            closeAllElse(View.buildings);
        }

        /**boutons de la boutique de graines**/
        if (e.getSource() == View.bfr) {//Graine rouge
            acheteGrain(indiceGraineR, j);
        }
        if (e.getSource() == View.bfj) { //Graine jaune
            acheteGrain(indiceGraineJ, j);
        }
        if (e.getSource() == View.bfv) { //Graine verte
            acheteGrain(indiceGraineV, j);
        }

        /**boutons de plantations**/
        if (e.getSource() == View.bpr){ //Fleur rouge
            planterGraine(indiceGraineR, j);
        }
        if (e.getSource() == View.bpj){ //Fleur jaune
            planterGraine(indiceGraineJ, j);
        }
        if (e.getSource() == View.bpv){ //Fleur verte
            planterGraine(indiceGraineV, j);
        }

        /**confection de bouquet*/
        if(e.getSource() == View.bpbr){ //fleur rouge sélectionnée
            /*Conditions: bouquet pas complet et fleur rouge dans l'inventaire*/
            if(!Bouquet.isReady() && j.getInventaire()[indiceFleurR] > 0){
                Bouquet.addFlower(indiceFleurR); //ajout de la fleur au bouquet
                j.useFlower(indiceFleurR); //retirer la fleur de l'inventaire
                View.updateInv(); //mise à jour affichage
                VueConfection.updateConfection(indiceFleurR);
            }
        }

        if(e.getSource() == View.bpbj){ //fleur jaune sélectionnée
            /*Conditions: bouquet pas complet et fleur jaune dans l'inventaire*/
            if(!Bouquet.isReady() && j.getInventaire()[indiceFleurJ] > 0){
                Bouquet.addFlower(indiceFleurJ); //ajout de la fleur au bouquet
                j.useFlower(indiceFleurJ); //retirer la fleur de l'inventaire
                View.updateInv(); //mise à jour affichage
                VueConfection.updateConfection(indiceFleurJ);
            }
        }

        if(e.getSource() == View.bpbv){ //fleur verte sélectionnée
            /*Conditions: bouquet pas complet et fleur verte dans l'inventaire*/
            if(!Bouquet.isReady() && j.getInventaire()[indiceFleurV] > 0) {
                Bouquet.addFlower(indiceFleurV); //ajout de la fleur au bouquet
                j.useFlower(indiceFleurV); //retirer la fleur de l'inventaire
                View.updateInv(); //mise à jour affichage
                VueConfection.updateConfection(indiceFleurV);
            }
        }

        if(e.getSource() == View.valider){
            if(Bouquet.isReady()){ //le bouquet est complet
                Bouquet.finishBouquet(); //création du bouquet
                View.updateInv(); //mise à jour affichage
                VueConfection.clearVueConfection();
            }
        }

        if(e.getSource() == View.annuler){
            Bouquet.cancelBouquet(); //réinitialisation du bouquet
            View.updateInv(); //mise à jour affichage
            VueConfection.clearVueConfection();
        }

        /** Boutons de validation des commandes*/
        for(int i = 0; i < VueCommandes.getListeCommandes().getComponentCount(); i++){ //parcours des boutons de commandes
            if(e.getSource() == VueCommandes.getListeCommandes().getComponent(i)){
                try {
                    /*Conditions : bouquet valide correspondant et jardinier dans le rayon du bâtiment principal*/
                    if (GrilleMod.getCommandes().get(i).hasValidBouquet() && GrilleMod.getSQDist(j.getX(), j.getY(), GrilleMod.getBatX(), GrilleMod.getBatY()) <= GrilleMod.getBatPrincipal().getRange()) {
                        GrilleMod.removeBouquet(Bouquet.getType(GrilleMod.getCommandes().get(i).getValue())); //retirer le bouquet de l'inventaire
                        GrilleMod.removeCommande(GrilleMod.getCommandes().get(i)); //retirer la commande du terrain
                        BatPrincipal.setTirelire(BatPrincipal.getTirelire() + BatPrincipal.PRIX_BOUQUET); //ajout du prix
                        //mise à jour affichage
                        View.updateInv();
                        VueCommandes.updateCommandes();
                        View.updateSolde();
                    }
                }catch(IndexOutOfBoundsException e2){}
            }
        }

        /**boutons de la boutique de batiments**/
        if (e.getSource() == View.prod){ //Bat de production
            /*Conditions : avoir assez d'argent et être sur une position valide*/
            if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_PRODUCTION && !GrilleMod.isNotValidPosition(j.getX(), j.getY())){
                j.construitBatProduction(); //construction du bâtiment
                View.updateSolde(); //mise à jour de la vue
            }
        }
        if (e.getSource() == View.def){ //Bat de defense
            /*Conditions : avoir assez d'argent et être sur une position valide*/
            if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_DEFENSE && !GrilleMod.isNotValidPosition(j.getX(), j.getY())){
                j.construitBatDefense(); //construction du bâtiment
                View.updateSolde(); //mise à jour de la vue
            }
        }

        /** bouton de recrutement d'un jardinier*/
        if(e.getSource() == View.acheterJardinierButton){
            if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_JARDINIER){
                GrilleMod.getBatPrincipal().recruterJardinier();
                View.updateSolde();
                JardinierView.updateJardinier();
            }
        }
    }

    /**
     * Gérer les clics
     * Clic gauche : sélection d'une unité
     * Clic droit : déplacement du jardinier
     * @param e mouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int RANGE_SELECTION = 1200; //la distance de sélection d'un jardinier
        //coordonnées cliquées
        double mouseX = e.getPoint().getX();
        double mouseY = e.getPoint().getY();
        if (SwingUtilities.isLeftMouseButton(e)) { //clic gauche
            for (Jardinier u : GrilleMod.getJardiniers()) { //parcours jardinier
                if (GrilleMod.getSQDist((int) mouseX, (int) mouseY, u.getX(), u.getY()) < RANGE_SELECTION) { //si le jardinier est assez proche
                    GrilleMod.setSelectedUnite(u); //le jardinier est sélectionné
                    View.updateInv();
                }
            }
        } else { //clic droit
            GrilleMod.getSelectedUnite().setMoving((int) mouseX, (int) mouseY); //le jardinier se déplace
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
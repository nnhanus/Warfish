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
        if (!(GrilleMod.isNotValidPosition(j.getX(), j.getY())) && j.getInventaire()[i] > 0){
            j.planteFleur(i);
            View.updateInv();
            VueFleur.updateFleur();
        }
    }

    public void closeAllElse(JPanel j){
        ArrayList<JPanel> sous_menu = new ArrayList<>();
        sous_menu.add(View.graines);
        sous_menu.add(View.buildings);
        sous_menu.add(View.planter);
        sous_menu.add(View.confection);
        for(JPanel jp : sous_menu){
               if(jp.equals(j)){
                   j.setVisible(!j.isVisible());
               }else{
                   jp.setVisible(false);
               }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Jardinier j = (Jardinier) GrilleMod.getSelectedUnite();

        if (e.getSource() == View.ramasserButton) { //recolter
            Fleur f = j.plusProcheFleur();
            int dist = j.getSQDistFrom(f.getX(), f.getY());
            if (f.isPickable() && dist < 3000) {
                j.recolterRessource(f);
                View.updateInv();
            }
        }

        if (e.getSource() == View.effrayerButton) { //Effrayer
            j.effrayer();
            //VueNuisible.updateNuisibles();
        }
        if (e.getSource() == View.desherberButton) { //desherber
            Fleur f = j.plusProcheFleur();
            int dist = j.getSQDistFrom(f.getX(), f.getY());
            if (dist < 30000 && (f).getIsDead()) {
                j.desherber(f);
            }
        }

        if(e.getSource() == View.planterMenuButton){ //planter
            closeAllElse(View.planter);
        }

        if(e.getSource() == View.bouquetMenuButton){ //bouquet
            closeAllElse(View.confection);
        }

        if(e.getSource() == View.vendreButton){ //Bouquet
            if(j.getInventaire()[GrilleMod.indiceBouquet] > 0 && GrilleMod.getSQDist(j.getX(), j.getY(), GrilleMod.getBatX(), GrilleMod.getBatY()) <= GrilleMod.getBatPrincipal().getRange()){
                j.vendBouquet();
                View.updateSolde();
                View.updateInv();
            }
        }

        if(e.getSource() == View.grainesBoutiqueButton){ //Graines
            closeAllElse(View.graines);
        }

        if(e.getSource() == View.batimentsBoutiqueButton){ // batiments
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
        if(e.getSource() == View.bpbr){
            if(!Bouquet.isReady() && ((Jardinier) getSelectedUnite()).getInventaire()[0] > 0){
                Bouquet.addFlower(indiceFleurR);
                ((Jardinier) getSelectedUnite()).useFlower(indiceFleurR);
                View.updateInv();
            }
        }

        if(e.getSource() == View.bpbj){
            if(!Bouquet.isReady() && ((Jardinier) getSelectedUnite()).getInventaire()[1] > 0){
                Bouquet.addFlower(indiceFleurJ);
                ((Jardinier) getSelectedUnite()).useFlower(indiceFleurJ);
                View.updateInv();
            }
        }

        if(e.getSource() == View.bpbv){
            if(!Bouquet.isReady() && ((Jardinier) getSelectedUnite()).getInventaire()[2] > 0) {
                Bouquet.addFlower(indiceFleurV);
                ((Jardinier) getSelectedUnite()).useFlower(indiceFleurV);
                View.updateInv();
            }
        }

        if(e.getSource() == View.valider){
            if(Bouquet.isReady()){
                Bouquet.finishBouquet();
                View.updateInv();
            }
        }

        if(e.getSource() == View.annuler){
            Bouquet.cancelBouquet();
            View.updateInv();
        }

        /** Boutons de validation des commandes*/
        for(int i = 0; i < VueCommandes.getListeCommandes().getComponentCount(); i++){
            if(e.getSource() == VueCommandes.getListeCommandes().getComponent(i)){
                try {
                    if (GrilleMod.getCommandes().get(i).hasValidBouquet() && GrilleMod.getSQDist(j.getX(), j.getY(), GrilleMod.getBatX(), GrilleMod.getBatY()) <= GrilleMod.getBatPrincipal().getRange()) {
                        GrilleMod.removeBouquet(Bouquet.getType(GrilleMod.getCommandes().get(i).getValue()));
                        GrilleMod.removeCommande(GrilleMod.getCommandes().get(i));
                        BatPrincipal.setTirelire(BatPrincipal.getTirelire() + BatPrincipal.PRIX_BOUQUET);
                        View.updateInv();
                        VueCommandes.updateCommandes();
                        View.updateSolde();
                    }
                }catch(IndexOutOfBoundsException e2){}
            }
        }

        /**boutons de la boutique de batiments**/
        if (e.getSource() == View.prod){ //Bat de production
            if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_PRODUCTION && !GrilleMod.isNotValidPosition(j.getX(), j.getY())){
                j.construitBatProduction();
                View.updateSolde();
            }
        }
        if (e.getSource() == View.def){ //Bat de defense
            if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_DEFENSE && !GrilleMod.isNotValidPosition(j.getX(), j.getY())){
                j.construitBatDefense();
                View.updateSolde();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double mouseX = e.getPoint().getX();
        double mouseY = e.getPoint().getY();
        if (SwingUtilities.isLeftMouseButton(e)) {
            for (Unite u : GrilleMod.getUnites()) {
                if (GrilleMod.getSQDist((int) mouseX, (int) mouseY, u.getX(), u.getY()) < 20) {
                    GrilleMod.setSelectedUnite(u);
                }
            }
        } else {
            GrilleMod.getSelectedUnite().setMoving((int) mouseX, (int) mouseY);
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
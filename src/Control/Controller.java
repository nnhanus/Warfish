package Control;

import Modele.*;
import View.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Modele.GrilleMod.*;

public class Controller implements ActionListener, MouseListener {

    public View view;

    public Controller(View view) {
        this.view = view;
        //Ajout des listener aux boutons
        view.b1.addActionListener(this);
        view.b2.addActionListener(this);
        view.b3.addActionListener(this);
        view.b4.addActionListener(this);
        view.b5.addActionListener(this);
        view.b6.addActionListener(this);
        View.b7.addActionListener(this);
        View.b8.addActionListener(this);
        //View.b9.addActionListener(this);
        view.terrain.addMouseListener(this);
        view.bfr.addActionListener(this);
        view.bfv.addActionListener(this);
        view.bfj.addActionListener(this);
        view.bpr.addActionListener(this);
        view.bpv.addActionListener(this);
        view.bpj.addActionListener(this);
        view.prod.addActionListener(this);
        view.def.addActionListener(this);
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
            System.out.println(BatPrincipal.PRIX_GRAINE);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Jardinier j = (Jardinier) GrilleMod.getSelectedUnite();

        if (e.getSource() == view.b1) { //recolter
            Fleur f = j.plusProcheFleur();
            int dist = j.getSQDistFrom(f.getX(), f.getY());
            if (f.isPickable() && dist < 3000) {
                //System.out.println("on récolte");
                j.recolterRessource(f);
                View.updateInv();
            }
        }

        if (e.getSource() == view.b2) { //Effrayer
            j.effrayer();
            //VueNuisible.updateNuisibles();
        }
        if (e.getSource() == view.b3) { //desherber
            Fleur f = j.plusProcheFleur();int dist = j.getSQDistFrom(f.getX(), f.getY());
            if (dist < 30000 && (f).getIsDead()) {
                j.desherber(f);
            }
        }

        if(e.getSource() == view.b4){ //planter
            view.graines.setVisible(false);
            view.buildings.setVisible(false);
            view.planter.setVisible(!(view.planter.isVisible()));
        }

        if(e.getSource() == view.b5){ //bouquet
            if(j.getInventaire()[GrilleMod.indiceFleurR] >= 3){
                j.confectionneBouquet();
                View.updateInv();
            }

        }

        if(e.getSource() == view.b6){ //Bouquet
            if(j.getInventaire()[GrilleMod.indiceBouquet] > 0 && GrilleMod.getSQDist(j.getX(), j.getY(), GrilleMod.getBatX(), GrilleMod.getBatY()) <= GrilleMod.getBatPrincipal().getRange()){
                j.vendBouquet();
                View.updateSolde();
                View.updateInv();
            }
        }

        if(e.getSource() == View.b7){ //Graines

            view.planter.setVisible(false);
            view.buildings.setVisible(false);
            view.graines.setVisible(!(view.graines.isVisible()));
        }

        if(e.getSource() == View.b8){ // batiments
            view.planter.setVisible(false);
            view.graines.setVisible(false);
            view.buildings.setVisible(!(view.buildings.isVisible()));
            System.out.println(view.buildings.isVisible());
        }

        /**boutons de la boutique de graines**/
        if (e.getSource() == view.bfr) {//Graine rouge
            acheteGrain(indiceGraineR, j);
        }
        if (e.getSource() == view.bfj) { //Graine jaune
            acheteGrain(indiceGraineJ, j);
        }
        if (e.getSource() == view.bfv) { //Graine verte
            acheteGrain(indiceGraineV, j);
        }

        /**boutons de plantations**/
        if (e.getSource() == view.bpr){ //Fleur rouge
            planterGraine(indiceGraineR, j);
        }
        if (e.getSource() == view.bpj){ //Fleur jaune
            planterGraine(indiceGraineJ, j);
        }
        if (e.getSource() == view.bpv){ //Fleur verte
            planterGraine(indiceGraineV, j);
        }

        /**boutons de la boutique de batiments**/
        if (e.getSource() == view.prod){ //Bat de production
            if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_PRODUCTION && !GrilleMod.isNotValidPosition(j.getX(), j.getY())){
                j.construitBatProduction();
                View.updateSolde();
            }
        }
        if (e.getSource() == view.def){ //Bat de defense
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
            System.out.println(mouseX + " " + mouseY);
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
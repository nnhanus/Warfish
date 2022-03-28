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

public class Controller implements ActionListener, MouseListener {

    public View view;

    public Controller(View view) {
        this.view = view;
        view.b1.addActionListener(this);
        view.b2.addActionListener(this);
        view.b3.addActionListener(this);
        view.b4.addActionListener(this);
        view.b5.addActionListener(this);
        view.b6.addActionListener(this);
        View.b7.addActionListener(this);
        View.b8.addActionListener(this);
        View.b9.addActionListener(this);
        view.terrain.addMouseListener(this);
        view.bfleur1.addActionListener(this);
        view.bfleur2.addActionListener(this);
        view.bfleur3.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Jardinier j = (Jardinier) GrilleMod.getSelectedUnite();

        if (e.getSource() == view.b1) { //acheterGraine
            if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_GRAINE && GrilleMod.getSQDist(j.getX(), j.getY(), GrilleMod.getBatX(), GrilleMod.getBatY()) <= GrilleMod.getBatPrincipal().getRange()){
                j.acheterGraine(1);
                BatPrincipal.acheterGraine(1);
                View.updateSolde();
                View.updateInv();
            }
        }

        if (e.getSource() == view.b2) { //récolter
            Ressource r = j.plusProcheRessource();
            int dist = j.getSQDistFrom(r.getX(), r.getY());
            if (r.isPickable() && dist < 3000) {
                //System.out.println("on récolte");
                j.recolterRessource(r);
                View.updateInv();
            }
        }
        if (e.getSource() == view.b3) { //effrayer
            j.effrayer();
            //VueNuisible.updateNuisibles();
        }

        if(e.getSource() == view.b4){ //planter
            if (!(GrilleMod.isNotValidPosition(j.getX(), j.getY())) && j.getInventaire()[0] > 0){
                j.planteFleur();
                View.updateInv();
                VueFleur.updateFleur();
            }
        }

        if(e.getSource() == view.b5){ //désherber
            Ressource r = j.plusProcheRessource();
            if(r.getClass() == Fleur.class) {
                int dist = j.getSQDistFrom(r.getX(), r.getY());
                if (dist < 30000 && ((Fleur) r).getIsDead()) {
                    j.desherber((Fleur) r);
                }
            }
        }

        if(e.getSource() == view.b6){ //Boutique (plus tard
            if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_PRODUCTION && !GrilleMod.isNotValidPosition(j.getX(), j.getY())){
                j.construitBatProduction();
                View.updateSolde();
            }
            //View.boutique.setVisible(!View.boutique.isVisible());
        }

        if(e.getSource() == View.b7){
            if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_DEFENSE && !GrilleMod.isNotValidPosition(j.getX(), j.getY())){
                j.construitBatDefense();
                View.updateSolde();
            }
        }

        if(e.getSource() == View.b8){
            if(j.getInventaire()[1] >= 3){
                j.confectionneBouquet();
                View.updateInv();
            }
        }

        if(e.getSource() == View.b9){
            if(j.getInventaire()[2] > 0 && GrilleMod.getSQDist(j.getX(), j.getY(), GrilleMod.getBatX(), GrilleMod.getBatY()) <= GrilleMod.getBatPrincipal().getRange()){
                j.vendBouquet();
                View.updateSolde();
                View.updateInv();
            }
        }
        /**boutons de la boutique **/
        if (e.getSource() == view.bfleur1) {//BatProduction
            if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_PRODUCTION && !GrilleMod.isNotValidPosition(j.getX(), j.getY())){
                j.construitBatProduction();
                View.updateSolde();
            }
        }
        if (e.getSource() == view.bfleur2) { //BatDefense
            if(BatPrincipal.getTirelire() >= BatPrincipal.PRIX_DEFENSE && !GrilleMod.isNotValidPosition(j.getX(), j.getY())){
                j.construitBatDefense();
                View.updateSolde();
            }
        }

        /*if (e.getSource() == view.bfleur3) { //Graine
            if (view.solde >= 800) {
                view.updateSolde(800);
            }
        }*/
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
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

import static Modele.GrilleMod.getBatPrincipal;

public class Controller implements ActionListener, MouseListener {

    public static View view;

    public Controller(View view) {
        this.view = view;
        view.b1.addActionListener(this);
        view.b2.addActionListener(this);
        view.b3.addActionListener(this);
        view.b4.addActionListener(this);
        view.b5.addActionListener(this);
        view.b6.addActionListener(this);
        view.terrain.addMouseListener(this);
        view.bfleur1.addActionListener(this);
        view.bfleur2.addActionListener(this);
        view.bfleur3.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.b1) {
            View.boutique.setVisible(true);
        }
        Jardinier j = (Jardinier) GrilleMod.getSelectedUnite();
        /*if(e.getSource() == view.b1){
            System.out.println("360 no scope");
            yolo
        }*/
        if (e.getSource() == view.b2) { //récolter
            Ressource r = j.plusProcheRessource();
            int dist = j.getSQDistFrom(r.getX(), r.getY());
            if (r.isPickable() && dist < 1000) {
                //System.out.println("on récolte");
                j.recolterRessource(r);
                view.updateInv();
            }
        }
        if (e.getSource() == view.b3) { //effrayer
            j.effrayer();
        }

        if(e.getSource() == view.b4){ //planter
            if (!(GrilleMod.isNotValidPosition(j.getX(), j.getY())) && j.getInventaire()[1] > 0){
                j.planteFleur();
            }
        }
        /*if(e.getSource() == view.b5){ //désherber
            Ressource r = j.plusProcheRessource();
            int dist = j.getSQDistFrom(r.getX(), r.getY());
            if (r.isPickable() && dist < 1000 && ((Fleur) r).getIsDead()) {
                j.desherber(r);
            }
        }*/
        /*
        if(e.getSource() == view.b6){
            System.out.println("ui");
        }*/

        /**boutons de la boutique **/
        if (e.getSource() == view.bfleur1) {
            if (getBatPrincipal().getTirelire() >= 10) {
                view.updateSolde(10);
                view.updateGraines();
            }
        }
        if (e.getSource() == view.bfleur2) {
            if (getBatPrincipal().getTirelire() >= 100) {
                view.updateSolde(100);
                view.updateGraines();
            }
        }
        if (e.getSource() == view.bfleur3) {
            if (getBatPrincipal().getTirelire() >= 800) {
                view.updateSolde(800);
                view.updateGraines();
            }
        }
    }


        @Override
        public void mouseClicked (MouseEvent e) {
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
        public void mousePressed (MouseEvent e){
        }

        @Override
        public void mouseReleased (MouseEvent e){
        }

        @Override
        public void mouseEntered (MouseEvent e){
        }

        @Override
        public void mouseExited (MouseEvent e){
        }

    }

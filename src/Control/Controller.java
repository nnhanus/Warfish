package Control;

import Modele.GrilleMod;
import Modele.Jardinier;
import Modele.Ressource;
import View.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Jardinier j = (Jardinier) GrilleMod.getSelectedUnite();
        /*if(e.getSource() == view.b1){
            System.out.println("360 no scope");
        }*/
        if(e.getSource() == view.b2){ //récolter
            Ressource r = j.plusProcheRessource();
            int dist = j.getSQDistFrom(r.getX(), r.getY());
            if ( r.isPickable() && dist < 400){
                j.recolterRessource(r);
            }
        }
        if(e.getSource() == view.b3) { //effrayer
            j.effrayer();
        }
         view.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

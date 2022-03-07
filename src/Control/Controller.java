package Control;

import Modele.BatPrincipal;
import Modele.Building;
import View.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements ActionListener, MouseListener {

    public static View view;
    private static int x = (int) (Math.random() * ( 10 - 0 ));
    private static int y = (int) (Math.random() * ( 10 - 0 ));
    public static BatPrincipal batPrincipal = new BatPrincipal(x,y);

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
        if(e.getSource() == view.b1){
           View.boutique.setVisible(true);
        }
        if(e.getSource() == view.b2){
            System.out.println("headshot");
        }
        if(e.getSource() == view.b3){
            System.out.println("oh my god");
        }
        if(e.getSource() == view.b4){
            System.out.println("medused");
        }
        if(e.getSource() == view.b5){
            System.out.println("what a move faker");
        }
        if(e.getSource() == view.b6){
            System.out.println("ui");
        }

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

package View;

import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueFleur {
    public ArrayList<Fleur> fleurs = new ArrayList<Fleur>();
  
    public VueFleur(){
        for(Fleur f:GrilleMod.getFleurs()){
            fleurs.add(new Fleur(f.getX(),f.getY()));
        }
    }
}

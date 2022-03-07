package View;


import Modele.Jardinier;

import java.util.ArrayList;


public class JardinierView {

    public ArrayList<Jardinier> listjardinier = new ArrayList<>();


    public JardinierView(){
        listjardinier.add(new Jardinier(0,0));
    }
}

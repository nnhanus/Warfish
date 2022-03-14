package View;


import Modele.GrilleMod;
import Modele.Jardinier;
import Modele.Unite;

import java.util.ArrayList;


public class JardinierView {

    public ArrayList<Jardinier> listjardinier = new ArrayList<>();


    public JardinierView(){
        for(Unite u:GrilleMod.getUnites()){
            listjardinier.add((Jardinier) u);
        }
    }
}

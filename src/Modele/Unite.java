package Modele;

import java.util.ArrayList;

public class Unite {
    public int x, y; //les coordonnées du péons
    public ArrayList<Integer> inventaire = new ArrayList<>(1); //pour l'instant

    public Unite(int x, int y){
        this.x = x;
        this.y = y;

        for(int i = 0; i <= this.inventaire.size();i++){
            this.inventaire.set(i, 0);
        }
    }

}
public class Paysan {

    public void planteFleur() {

    }

    public void recolterRessource(Ressource r) {

    }

    public void acheterGraine() {

    }

    public void vendre(Ressource r) { //augmenter
        Bat_Principal.tirelire += r.;

        this.inventaire.get(r.id)--
    }

    public void avance(){

    }

    public void effrayer(){
    }
}
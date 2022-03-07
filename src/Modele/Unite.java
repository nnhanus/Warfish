package Modele;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Unite {
    public int x, y; //les coordonnées de l'unité
    public static int vitesse; //la vitesse de l'unité

    public Unite(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * avance
     * fait avancer l'unité dans une direction (peut être appelé de manière séquentielle pour faire suivre un parcours)
     * @param dir la direction à suivre
     */
    public void avance(double dir){
        this.x += cos(dir)*Jardinier.vitesse;
        this.y += sin(dir)*Jardinier.vitesse;
    }
}


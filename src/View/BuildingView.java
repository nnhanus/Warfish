package View;
import Modele.BatDefense;
import Modele.BatProduction;
import Modele.Building;
import Modele.GrilleMod;

import java.awt.*;
import java.util.ArrayList;

public class BuildingView {
    private static ArrayList<Modele.BatDefense> batDefense = new ArrayList<>(); //liste des bâtiments de défense
    private static ArrayList<Modele.BatProduction> batProductions = new ArrayList<>(); //liste des bâtiments de production
    private static final int TAILLE_BAT = 80; //taille des bâtiments

    public BuildingView(){
        for(Building b : GrilleMod.getBuildings()){ //parcours des bâtiments et confection des listes
            if(b.getClass() == Modele.BatDefense.class){
                batDefense.add((Modele.BatDefense) b);
            }else if (b.getClass() == Modele.BatProduction.class){
                batProductions.add((Modele.BatProduction)b);
            }
        }
    }

    /**
     * drawBuildings
     * Dessine les bâtiments ainsi que cercle indiquant leur rayon d'activité
     * @param g
     */
    public void drawBuildings(Graphics g){
        //batiment de production
        g.setColor(Color.BLUE);
        for(BatProduction b : BuildingView.batProductions){
            //image
            g.drawImage(Movable.batProd, b.getX() - TAILLE_BAT/2, b.getY()-TAILLE_BAT/2, TAILLE_BAT, TAILLE_BAT, null);
            //rayon d'action
            int sq = (int) Math.sqrt(b.getRange());
            g.drawOval(b.getX() - sq, b.getY() - sq, sq*2, (int) sq*2);
        }
        //batiment de défense
        g.setColor(Color.DARK_GRAY);
        for(BatDefense b : BuildingView.batDefense){
            //image
            g.drawImage(Movable.batDef, b.getX() - TAILLE_BAT/2, b.getY()-TAILLE_BAT/2, TAILLE_BAT, TAILLE_BAT, null);
            //rayon d'action
            int sq = (int) Math.sqrt(b.getRange());
            g.drawOval(b.getX() - sq, b.getY() - sq, sq*2, sq*2);
        }
        //batiment principal
        g.setColor(Color.ORANGE);
        //image
        g.drawImage(Movable.batPrinc, GrilleMod.getBatX() - TAILLE_BAT/2, GrilleMod.getBatY()-TAILLE_BAT/2, TAILLE_BAT, TAILLE_BAT, null);
        //rayon d'action
        int sq = (int) Math.sqrt(GrilleMod.getBatPrincipal().getRange());
        g.drawOval(GrilleMod.getBatX() - sq, GrilleMod.getBatY() - sq, sq*2, sq*2);
    }

    /**
     * updateBuildings
     * Met à jour l'affichage des bâtiments
     * @param b
     */
    public static void updateBuildings(Building b){
        //ajout d'un bâtiment
        if(b.getClass() == Modele.BatDefense.class){
            batDefense.add((Modele.BatDefense) b);
        }else{
            batProductions.add((Modele.BatProduction) b);
        }
    }
}

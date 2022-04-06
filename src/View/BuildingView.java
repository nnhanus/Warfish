package View;
import Modele.BatDefense;
import Modele.BatProduction;
import Modele.Building;
import Modele.GrilleMod;

import java.awt.*;
import java.util.ArrayList;

public class BuildingView {
    private static ArrayList<Modele.BatDefense> batDefense = new ArrayList<>();
    private static ArrayList<Modele.BatProduction> batProductions = new ArrayList<>();
    private static final int TAILLE_BAT = 80;

    public BuildingView(){
        for(Building b : GrilleMod.getBuildings()){
            if(b.getClass() == Modele.BatDefense.class){
                batDefense.add((Modele.BatDefense) b);
            }else if (b.getClass() == Modele.BatProduction.class){
                batProductions.add((Modele.BatProduction)b);
            }
        }
    }

    public void drawBuildings(Graphics g){
        //System.out.println(this.batProductions.size());
        g.setColor(Color.BLUE);
        for(BatProduction b : BuildingView.batProductions){
           // g.fillRect(b.getX() - TAILLE_BAT/2, b.getY()-TAILLE_BAT/2, TAILLE_BAT, TAILLE_BAT);
            g.drawImage(Movable.batProd, b.getX() - TAILLE_BAT/2, b.getY()-TAILLE_BAT/2, TAILLE_BAT, TAILLE_BAT, null);
            int sq = (int) Math.sqrt(b.getRange());
            g.drawOval(b.getX() - sq, b.getY() - sq, sq*2, (int) sq*2);
        }
        g.setColor(Color.DARK_GRAY);
        for(BatDefense b : BuildingView.batDefense){
            //g.fillRect(b.getX() - TAILLE_BAT/2, b.getY()-TAILLE_BAT/2, TAILLE_BAT, TAILLE_BAT);
            g.drawImage(Movable.batDef, b.getX() - TAILLE_BAT/2, b.getY()-TAILLE_BAT/2, TAILLE_BAT, TAILLE_BAT, null);
            int sq = (int) Math.sqrt(b.getRange());
            g.drawOval(b.getX() - sq, b.getY() - sq, sq*2, sq*2);
        }
        g.setColor(Color.ORANGE);
        //g.fillRect(GrilleMod.getBatX() - TAILLE_BAT/2, GrilleMod.getBatY()-TAILLE_BAT/2, TAILLE_BAT, TAILLE_BAT );
        g.drawImage(Movable.batPrinc, GrilleMod.getBatX() - TAILLE_BAT/2, GrilleMod.getBatY()-TAILLE_BAT/2, TAILLE_BAT, TAILLE_BAT, null);
        int sq = (int) Math.sqrt(GrilleMod.getBatPrincipal().getRange());
        g.drawOval(GrilleMod.getBatX() - sq, GrilleMod.getBatY() - sq, sq*2, sq*2);
    }

    public static void updateBuildings(Building b){
        if(b.getClass() == Modele.BatDefense.class){
            batDefense.add((Modele.BatDefense) b);
            //g.setColor(Color.PINK);
            //g.fillRect(b.getX()-TAILLE_BAT/2, b.getY()-TAILLE_BAT/2, TAILLE_BAT, TAILLE_BAT);
        }else{
            batProductions.add((Modele.BatProduction) b);
            //g.setColor(Color.GREEN);
            //g.fillRect(b.getX()-TAILLE_BAT/2, b.getY()-TAILLE_BAT/2, TAILLE_BAT, TAILLE_BAT);
        }
    }
}

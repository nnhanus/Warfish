package View;
import Modele.Building;
import Modele.GrilleMod;

import javax.swing.*;
import java.awt.*;

public class BuildingView extends JPanel{
    public BuildingView (){
    }

    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        for(Building b : GrilleMod.getBuildings()){
            g.setColor(Color.RED);
            g.drawRect(b.getX(), b.getY(), 50, 50);
        }
    }
}

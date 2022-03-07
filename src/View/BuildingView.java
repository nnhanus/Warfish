package View;
import Modele.Building;

import javax.swing.*;
import java.awt.*;

public class BuildingView extends JPanel{
    private Building building;

    /*public BuildingView (Building b){
        this.building = b;
    }*/

    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawRect(0, 0, 50, 50);
    }
}

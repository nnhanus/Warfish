package Modele;

import View.BuildingView;

import java.util.ArrayList;

public class Building {
    protected final int x, y;
    protected final int range; //défini le rayon dans lequel les "capacités" du bâtiment sont efficaces

    public Building(int x, int y, int range) {
        this.x = x;
        this.y = y;
        this.range = range;

    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getRange(){
        return this.range;
    }
}

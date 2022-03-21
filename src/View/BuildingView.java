package View;
import Modele.Building;
import Modele.GrilleMod;
import java.util.ArrayList;

public class BuildingView {
    public ArrayList<Building> buildings = new ArrayList<>();

    public BuildingView(){
        for(Building b: GrilleMod.getBuildings()){
            buildings.add(b);
        }
    }
}

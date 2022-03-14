import Control.Controller;
import Modele.GrilleMod;
import Modele.Jardinier;
import View.View;
import View.ThreadAffichage;


public class Warfish {
    public static void main(String [] args){
        GrilleMod gr = new GrilleMod();
        View view = new View();
        Controller controller = new Controller(view);
        (new ThreadAffichage(view)).start();
    }
}

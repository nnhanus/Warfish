import Control.Controller;
import Modele.Jardinier;
import View.View;


public class Warfish {
    public static void main(String [] args){

        View view = new View();
        Controller controller = new Controller(view);
        Jardinier jardinier = new Jardinier(0,0);
    }
}

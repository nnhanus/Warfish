import Control.Controller;
import View.View;
import View.Grille;

import javax.swing.*;

public class Warfish {
    public static void main(String [] args){

        View view = new View();
        Controller controller = new Controller(view);
    }
}

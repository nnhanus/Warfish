package View;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Grille extends JPanel {

    public Grille () {
        this.setPreferredSize(new Dimension(800,View.HEIGHT_WIN));
        this.setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;

        g.setColor(Color.BLACK);

        while ( x < 800 ){
            g.drawLine(x, 0, x, 800);
            x += 50;
        }

        while ( y < 800 ){
            g.drawLine(0, y, 800, y);
            y += 50;
        }

    }


}
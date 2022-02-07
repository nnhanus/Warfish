package View;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

        /**dimensions de la fenêtre d'affichage*/
        public static final int WIDTH_WIN = 800;
        public static final int HEIGHT_WIN = 600;

        public View() {
            this.setTitle("les fleurs des méduses");
            this.setPreferredSize(new Dimension(WIDTH_WIN,HEIGHT_WIN));
            this.setLayout(new GridBagLayout());

            /**création de la partie terrain et de la partie control*/
            JPanel terrain = new JPanel();
            JPanel control = new JPanel();
            JButton pd = new JButton("brice est con");

            /**changement de la couleur des différentes zones*/
            terrain.setBackground(Color.RED);
            control.setBackground(Color.BLUE);

            /**permet de mettre les 2 JPanel côte à côte et de leur attribuer un pourcentage de l'écran*/
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 0.75;
            c.weighty = 1;
            c.gridy = 0;
            this.add(terrain,c);
            c.weightx = 0.25;
            c.weighty = 1;
            this.add(control,c);
            control.add(pd);


            this.pack();
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
        /**
         @Override
         public void paint(Graphics g) {

         g.drawOval(50, 50, 100, 100);

         }*/
    }

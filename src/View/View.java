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
            JPanel boutons = new JPanel();
            JButton b1 = new JButton("1");
            JButton b2 = new JButton("2");
            JButton b3 = new JButton("3");
            JButton b4 = new JButton("4");
            JButton b5 = new JButton("5");
            JButton b6 = new JButton("6");
            b2.setPreferredSize(new Dimension(50,50));

            /**changement de la couleur des différentes zones*/
            terrain.setBackground(Color.RED);
            control.setBackground(Color.BLUE);
            boutons.setOpaque(false);

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
            control.add(boutons);
            boutons.setLayout(new GridLayout(0,3,5,5));
            boutons.add(b1);
            boutons.add(b2);
            boutons.add(b3);
            boutons.add(b4);
            boutons.add(b5);
            boutons.add(b6);


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

package Control;

import View.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    public static View view;

    public Controller(View view) {
        this.view = view;
        view.b1.addActionListener(this);
        view.b2.addActionListener(this);
        view.b3.addActionListener(this);
        view.b4.addActionListener(this);
        view.b5.addActionListener(this);
        view.b6.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("pute");
    }
}

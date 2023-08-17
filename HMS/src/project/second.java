package project;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class second extends JFrame implements ActionListener {
    private JButton b1, b2, b3, b4, b5;

    second() {
        super("Welcome");
        setSize(600, 400);
        setResizable(false);

        Container c = getContentPane();
        c.setBackground(Color.black);
        c.setLayout(new GridLayout(3, 1, 30, 0));


        JPanel tp = new JPanel();
        JLabel ll = new JLabel("WELCOME ADMIN");
        ll.setFont(new Font("Impact", Font.BOLD, 32));
        tp.add(ll);
        add(tp);

        tp = new JPanel();
        b1 = new JButton("DOCTOR");
        b1.addActionListener(this);
        b1.setBackground(Color.yellow);
        b2 = new JButton("PATIENT");
        b2.addActionListener(this);
        tp.add(b1);
        tp.add(b2);
        add(tp);


        tp = new JPanel();
        b3 = new JButton("LAB REPORT");
        b3.addActionListener(this);
        b4 = new JButton("INPATIENT");
        b4.addActionListener(this);
        b5 = new JButton("BILL");
        b5.addActionListener(this);
        tp.add(b3);
        tp.add(b4);
        tp.add(b5);
        add(tp);


        setVisible(true);
    }

    public static void main(String[] args) {
        new second();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1)
        {
             DOCTOR d = new DOCTOR();
             d.setVisible(true);

        }
        if (e.getSource() == b2)
        {

            new PATIENT();
        }
        if (e.getSource() == b3)
        {

            new LAB_REPORT();
        }
        if (e.getSource() == b4)
        {

            new IN_PATIENT();
        }
        if (e.getSource() == b5)
        {

            new BLL();
        }
    }

}
package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SECONDMENU extends  javax.swing.JFrame {
    JPanel Main;
    private JButton BILLButton;
    private JButton PATIENTButton;
    private JButton DOCTORButton;
    private JButton INPATIENTButton;
    private JButton LAB_REPORTButton;

    public static void main(String[] args)
    {

        JFrame frame = new JFrame("SECONDMENU");
        frame.setContentPane(new SECONDMENU().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public SECONDMENU() {
    DOCTORButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("TEST");
            DOCTOR d = new DOCTOR();
            d.setVisible(true);
            d.setContentPane(new DOCTOR().Main);

        }
    });
        PATIENTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                PATIENT d = new PATIENT();
                d.setVisible(true);
                d.setContentPane(new PATIENT().Main);
                d.setVisible(true);

            }
        });
        INPATIENTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                IN_PATIENT d = new IN_PATIENT();
                d.setVisible(true);
                d.setContentPane(new IN_PATIENT().Main);

            }
        });
        LAB_REPORTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                LAB_REPORT d = new LAB_REPORT();
                d.setVisible(true);
                d.setContentPane(new LAB_REPORT().Main);

            }
        });
        BILLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                BLL d = new BLL();
                d.setVisible(true);
                d.setVisible(true);
                d.setContentPane(new BLL().Main);
            }
        });
    }
}

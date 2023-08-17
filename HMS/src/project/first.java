package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class first extends JFrame implements ActionListener
{
    first()
    {
        super("Login");
        setSize(300,300);
        setResizable(false);

        Container c=getContentPane();
        c.setBackground(Color.black);
        c.setLayout(new GridLayout(3,2));


        //PANEL 1
        JPanel tp=new JPanel();

        tp.setBackground(Color.black);
        JLabel l= new JLabel("LOGIN");
        l.setForeground(Color.red);
        l.setFont(new Font("Impact",Font.BOLD,32));
        tp.add(l);
        add(tp);

        //PANEL 2
        tp=new JPanel();

        tp.setBackground(Color.black);
        JLabel l1=new JLabel("Username");
        l1.setForeground(Color.red);
        JTextField t1=new JTextField(20);
        tp.add(l1);
        tp.add(t1);

        JLabel l2=new JLabel("Password");
        l2.setForeground(Color.red);
        JPasswordField p1=new JPasswordField(20);
        tp.add(l2);
        tp.add(p1);

        add(tp);

        //PANEL 3
        tp=new JPanel();

        tp.setBackground(Color.black);
        JButton b1=new JButton("Sign in");
        b1.addActionListener(this);
        tp.add(b1);
        add(tp);

        setVisible(true);
    }
    public static void main(String[] args)
    {
        new first();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     SECONDMENU d = new SECONDMENU();
     d.setVisible(true);
     d.setContentPane(new SECONDMENU().Main);

    }
}
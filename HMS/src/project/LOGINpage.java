package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LOGINpage {
    private JPanel Main;
    private JTextField textField1;
    private JTextField textField2;
    private JButton LOGINButton;

    public LOGINpage() {
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String uname = textField1.getText();
                String pass = textField2.getText();
                if(uname.equals("AMAAN") & pass.equals("AMAAN1234"))
                {
                    SECONDMENU d = new SECONDMENU();
                    d.setVisible(true);
                    d.setContentPane(new SECONDMENU().Main);
                }
                else
                {
                    JOptionPane.showMessageDialog(Main, "INVALID LOGIN CREDENTIALS ENTERED !");
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LOGINpage");
        frame.setContentPane(new LOGINpage().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(900,600);
    }
}

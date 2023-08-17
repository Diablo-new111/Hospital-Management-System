package project;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

public class PATIENT extends JFrame {
    private JButton SAVEButton;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JButton SEARCHButton;
    private JTable table1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField1;
    private JLabel txtPid;
    private JLabel txtName;
    private JLabel TxtDoc_id;
    private JLabel txt;
    JPanel Main;
    private JScrollPane table2;

    Connection con;
    PreparedStatement pst;

    public void connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms1", "root","amaan123");
            System.out.println("Successs");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    void table_load()
    {
        try
        {
            pst = con.prepareStatement("select * from patient");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }



    public static void main(String[] args)
    {
        JFrame frame = new JFrame("PATIENT");
        frame.setContentPane(new PATIENT().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public PATIENT()
    {
        connect();
        table_load();
    SAVEButton.addActionListener(new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String pid,pname,doc_id,disease,age,gender,address;

            pid = textField1.getText();
            pname = textField2.getText();
            doc_id = textField3.getText();
            disease = textField4.getText();
            age = textField5.getText();
            gender = textField6.getText();
            address = textField7.getText();

            try {
                pst = con.prepareStatement("insert into patient (pid,pname,doc_id,disease,age,gender,address)values(?,?,?,?,?,?,?)");
                pst.setString(1, pid);
                pst.setString(2, pname);
                pst.setString(3, doc_id);
                pst.setString(4, disease);
                pst.setString(5, age);
                pst.setString(6, gender);
                pst.setString(7, address);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                table_load();
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField7.setText("");
                textField1.requestFocus();
            }

            catch (SQLException e1)
            {

                e1.printStackTrace();
            }

        }
    });
        SEARCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {

                    String pid = textField8.getText();

                    pst = con.prepareStatement("select pname,doc_id,disease,age,gender,address from patient where pid = ?");
                    pst.setString(1, pid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String pname1 = rs.getString(1);
                        String doc_id1 = rs.getString(2);
                        String disease1 = rs.getString(3);
                        String age1 = rs.getString(4);
                        String gender1 = rs.getString(5);
                        String address1 = rs.getString(6);


                        textField2.setText(pname1);
                        textField3.setText(doc_id1);
                        textField4.setText(disease1);
                        textField5.setText(age1);
                        textField6.setText(gender1);
                        textField7.setText(address1);
                        textField1.requestFocus();

                    }
                    else
                    {
                        textField2.setText("");
                        textField3.setText("");
                        textField4.setText("");
                        textField5.setText("");
                        textField6.setText("");
                        textField7.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Employee No");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String pid,pname,doc_id,disease,age,gender,address;

                pid = textField1.getText();
                pname = textField2.getText();
                doc_id = textField3.getText();
                disease = textField4.getText();
                age = textField5.getText();
                gender = textField6.getText();
                address = textField7.getText();

                try {
                    pst = con.prepareStatement("update patient set pname = ?,doc_id = ?,disease = ?,age = ?,gender = ?,address = ? where pid = ?");
                    pst.setString(1, pname);
                    pst.setString(2, doc_id);
                    pst.setString(3, disease);
                    pst.setString(4, age);
                    pst.setString(5, gender);
                    pst.setString(6, address);
                    pst.setString(7, pid);


                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                    table_load();
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");
                    textField5.setText("");
                    textField6.setText("");
                    textField7.setText("");
                    textField1.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                String pid;
                pid = textField8.getText();

                try {
                    pst = con.prepareStatement("delete from patient  where pid = ?");

                    pst.setString(1, pid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
                    table_load();
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");
                    textField5.setText("");
                    textField6.setText("");
                    textField7.setText("");
                    textField1.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }
        });
    }
}

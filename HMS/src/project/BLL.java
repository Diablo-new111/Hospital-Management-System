package project;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;


public class BLL extends JFrame{
    JPanel Main;
    private JTable table1;
    private JButton SAVEButton;
    private JButton DELETEButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton UPDATEButton;
    private JButton SEARCHButton;
    private JTextField textField8;



    Connection con;
    PreparedStatement pst;

    public void connect()
    {
        try
        {
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
            pst = con.prepareStatement("select * from bill");
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
        JFrame frame = new JFrame("BLL");
        frame.setContentPane(new BLL().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public BLL()
    {
        connect();
        table_load();
    SAVEButton.addActionListener(new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String bill_no,pid,health_card,no_of_days,medicine_charges,operation_charges,lab_charges;

            bill_no = textField1.getText();
            pid = textField2.getText();
            health_card = textField3.getText();
            no_of_days = textField4.getText();
            medicine_charges = textField5.getText();
            operation_charges = textField6.getText();
            lab_charges = textField7.getText();

            try {
                pst = con.prepareStatement("insert into bill (bill_no,pid,health_card,no_of_days,medicine_charges,operation_charges,lab_charges)values(?,?,?,?,?,?,?)");
                pst.setString(1, bill_no);
                pst.setString(2, pid);
                pst.setString(3, health_card);
                pst.setString(4, no_of_days);
                pst.setString(5, medicine_charges);
                pst.setString(6, operation_charges);
                pst.setString(7, lab_charges);

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

                    String bill_no = textField8.getText();

                    pst = con.prepareStatement("select pid,health_card,no_of_days,medicine_charges,operation_charges,lab_charges from bill where bill_no = ?");
                    pst.setString(1, bill_no);
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
                String bill_no,pid,health_card,no_of_days,medicine_charges,operation_charges,lab_charges;

                bill_no = textField1.getText();
                pid = textField2.getText();
                health_card = textField3.getText();
                no_of_days = textField4.getText();
                medicine_charges = textField5.getText();
                operation_charges = textField6.getText();
                lab_charges = textField7.getText();


                try
                {
                    pst = con.prepareStatement("update bill set pid = ?,health_card = ?,no_of_days = ?,medicine_charges = ?,operation_charges = ?,lab_charges = ? where bill_no = ?");
                    pst.setString(1, pid);
                    pst.setString(2, health_card);
                    pst.setString(3, no_of_days);
                    pst.setString(4, medicine_charges);
                    pst.setString(5, operation_charges);
                    pst.setString(6, lab_charges);
                    pst.setString(7, bill_no);


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
                String bill_no;
                bill_no = textField8.getText();

                try {
                    pst = con.prepareStatement("delete from bill  where bill_no = ?");

                    pst.setString(1, bill_no);

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

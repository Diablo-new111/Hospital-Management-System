package project;


import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

public class LAB_REPORT extends  JFrame{
    JPanel Main;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton SAVEButton;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JButton SEARCHButton;
    private JTextField textField7;
    private JLabel txtLab_no;
    private JLabel txtDoc_id;
    private JLabel txtpid;
    private JLabel txt_Date;
    private JLabel txt_Amount;
    private JLabel txt_Cat;

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
            pst = con.prepareStatement("select * from lab_report");
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
        JFrame frame = new JFrame("LAB_REPORT");
        frame.setContentPane(new LAB_REPORT().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public LAB_REPORT()
    {
    connect();
    table_load();
    SAVEButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String lab_no,doc_id,pid,date,amount,category;
            lab_no = textField1.getText();
            doc_id = textField2.getText();
            pid = textField3.getText();
            date = textField4.getText();
            amount = textField5.getText();
            category = textField6.getText();

            try {
                pst = con.prepareStatement("insert into lab_report (lab_no,doc_id,pid,date,amount,category)values(?,?,?,?,?,?)");
                pst.setString(1, lab_no);
                pst.setString(2, doc_id);
                pst.setString(3, pid);
                pst.setString(4, date);
                pst.setString(5, amount);
                pst.setString(6, category);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                table_load();
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
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

                String lab_no = textField7.getText();

                pst = con.prepareStatement("select doc_id,pid,date,amount,category from lab_report where lab_no = ?");
                pst.setString(1, lab_no);
                ResultSet rs = pst.executeQuery();

                if(rs.next()==true)
                {
                    String pname1 = rs.getString(1);
                    String doc_id1 = rs.getString(2);
                    String disease1 = rs.getString(3);
                    String age1 = rs.getString(4);
                    String gender1 = rs.getString(5);

                    textField2.setText(pname1);
                    textField3.setText(doc_id1);
                    textField4.setText(disease1);
                    textField5.setText(age1);
                    textField6.setText(gender1);

                    textField1.requestFocus();

                }
                else
                {
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");
                    textField5.setText("");
                    textField6.setText("");
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
            String lab_no,doc_id,pid,date,amount,category;
            lab_no = textField1.getText();
            doc_id = textField2.getText();
            pid = textField3.getText();
            date = textField4.getText();
            amount = textField5.getText();
            category = textField6.getText();
            try {
                pst = con.prepareStatement("update lab_report set doc_id = ?,pid = ?,date = ?,amount = ?,category = ? where lab_no = ?");
                pst.setString(1, doc_id);
                pst.setString(2, pid);
                pst.setString(3, date);
                pst.setString(4, amount);
                pst.setString(5, category);
                pst.setString(6, lab_no);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                table_load();
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");

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
            String lab_no;
            lab_no = textField7.getText();

            try {
                pst = con.prepareStatement("delete from lab_report  where lab_no = ?");

                pst.setString(1, lab_no);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
                table_load();
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");

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


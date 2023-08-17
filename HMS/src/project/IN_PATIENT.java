package project;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;


public class IN_PATIENT extends JFrame{
    JPanel Main;
    private JTable table1;
    private JButton SAVEButton;
    private JButton DELETEButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton UPDATEButton;
    private JButton SEARCHButton;
    private JTextField textField6;
    private JLabel txtPid;
    private JLabel txtLab_No;
    private JLabel txtRoom_no;
    private JLabel txtA_d;
    private JLabel txtD_d;

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
            pst = con.prepareStatement("select * from inpatient");
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
        JFrame frame = new JFrame("IN_PATIENT");
        frame.setContentPane(new IN_PATIENT().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public IN_PATIENT()
{
    connect();
    table_load();
    SAVEButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String pid,lab_no,room_no,admit_date,dis_date;
            pid = textField1.getText();
            lab_no = textField2.getText();
            room_no = textField3.getText();
            admit_date = textField4.getText();
            dis_date = textField5.getText();


            try {
                pst = con.prepareStatement("insert into inpatient (pid,lab_no,room_no,admit_date,dis_date)values(?,?,?,?,?)");
                pst.setString(1, pid);
                pst.setString(2, lab_no);
                pst.setString(3, room_no);
                pst.setString(4, admit_date);
                pst.setString(5, dis_date);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                table_load();
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");

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

                String pid = textField6.getText();

                pst = con.prepareStatement("select lab_no,room_no,admit_date,dis_date from inpatient where pid = ?");
                pst.setString(1, pid);
                ResultSet rs = pst.executeQuery();

                if(rs.next()==true)
                {
                    String pname1 = rs.getString(1);
                    String doc_id1 = rs.getString(2);
                    String disease1 = rs.getString(3);
                    String age1 = rs.getString(4);


                    textField2.setText(pname1);
                    textField3.setText(doc_id1);
                    textField4.setText(disease1);
                    textField5.setText(age1);


                    textField1.requestFocus();

                }
                else
                {
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");
                    textField5.setText("");

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
            String pid,lab_no,room_no,admit_date,dis_date;
            pid = textField1.getText();
            lab_no = textField2.getText();
            room_no = textField3.getText();
            admit_date = textField4.getText();
            dis_date = textField5.getText();
            try {
                pst = con.prepareStatement("update inpatient set room_no = ?,admit_date = ?,dis_date = ? where pid = ?");
                pst.setString(1, room_no);
                pst.setString(2, admit_date);
                pst.setString(3, dis_date);
                pst.setString(4, pid);


                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                table_load();

                textField3.setText("");
                textField4.setText("");
                textField5.setText("");

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
            pid = textField6.getText();

            try {
                pst = con.prepareStatement("delete from inpatient  where pid = ?");

                pst.setString(1, pid);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
                table_load();
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");

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

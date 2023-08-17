package project;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class DOCTOR extends JFrame
{
    JPanel Main;
    private JLabel t1;
    private JLabel t2;
    private JLabel t3;
    private JTextField textField1;
    private JTextField textField2;
    private JFormattedTextField textField3;
    private JButton SAVEButton;
    private JTable table1;
    private JButton DELETEButton;
    private JButton UPDATEButton1;
    private JScrollPane table_1;
    private JTextField txtid;
    private JButton SEARCHButton;

    public static void main(String[] args)
    {

        JFrame frame = new JFrame("DOCTOR");
        frame.setContentPane(new DOCTOR().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(900,600);

    }

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
            pst = con.prepareStatement("select * from doctor");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }



    public DOCTOR()
    {
        connect();
        table_load();
        SAVEButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                String doc_id,doc_name,department;
                doc_id = textField1.getText();
                doc_name = textField2.getText();
                department = textField3.getText();

                try
                {
                    pst = con.prepareStatement("insert into doctor(doc_id,doc_name,department)values(?,?,?)");
                    pst.setString(1, doc_id);
                    pst.setString(2, doc_name);
                    pst.setString(3, department);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                    table_load();
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");

                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }

            }
        });
        SEARCHButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                try {

                    String doc_id = txtid.getText();

                    pst = con.prepareStatement("select doc_name,department from doctor where doc_id = ?");
                    pst.setString(1, doc_id);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String doc_name1 = rs.getString(1);
                        String department1 = rs.getString(2);

                        textField2.setText(doc_name1);
                        textField3.setText(department1);

                    }
                    else
                    {
                        textField2.setText("");
                        textField3.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Doctor ID No");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }

        });
        UPDATEButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String doc_id,doc_name,department;
                doc_id = textField1.getText();
                doc_name = textField2.getText();
                department = textField3.getText();

                try {
                    pst = con.prepareStatement("update doctor set doc_name = ?,department = ? where doc_id = ?");
                    pst.setString(1, doc_name);
                    pst.setString(2, department);
                    pst.setString(3, doc_id);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                    table_load();
                    textField2.setText("");
                    textField3.setText("");
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
                String doc_id;
                doc_id = txtid.getText();

                try {
                    pst = con.prepareStatement("delete from doctor  where doc_id = ?");

                    pst.setString(1,doc_id);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");
                    table_load();
                    textField2.setText("");
                    textField3.setText("");
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

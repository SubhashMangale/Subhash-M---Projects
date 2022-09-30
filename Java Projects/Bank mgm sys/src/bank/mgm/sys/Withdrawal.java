package bank.mgm.sys;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Withdrawal extends JFrame implements ActionListener
{
        JButton with,back;
        JLabel heading;
        JTextField withdrawal;
        String pin_no;
        Withdrawal(String pin_no)
        {
        
        this.pin_no = pin_no;
        
        setLayout(null);
        getContentPane().setBackground(Color.white);
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icons/bank.jpg"));
        Image image2 = image1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel image = new JLabel(image3); //we can add ImageIcon into JLable but we cannot add Image directly so...
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        heading = new JLabel("Enter the amount you want to");
        heading.setForeground(Color.black);
        heading.setBounds(20,100,600,40);
        heading.setFont(new Font("System",Font.BOLD,28));
        image.add(heading);
        
        JLabel heading1 = new JLabel("Withdraw:");
        heading1.setForeground(Color.black);
        heading1.setBounds(130,140,600,40);
        heading1.setFont(new Font("System",Font.BOLD,28));
        image.add(heading1);
        
        withdrawal = new JTextField();
        withdrawal.setBounds(80, 190, 200, 30);
        withdrawal.setBackground(Color.white);
        withdrawal.setFont(new Font("Arial",Font.PLAIN,26));
        withdrawal.setForeground(Color.black);
        image.add(withdrawal);
        
        with = new JButton("Withdraw");
        with.setBounds(20, 260, 150, 40);
        with.setFont(new Font("Raleways",Font.BOLD,22));
        with.addActionListener(this);
        image.add(with);
        
        back = new JButton("Back");
        back.setBounds(200, 260, 150, 40);
        back.setFont(new Font("Raleways",Font.BOLD,22));
        back.addActionListener(this);
        image.add(back);
        
            
        setLayout(null);
        setSize(800,600);
        setLocation(300,50);
        //setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getSource()==with)
            {
                String amount =  withdrawal.getText();
                Date date = new Date();
                if(amount.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please enter the amount");
                }
                else
                {
                    try {
                        Conn conn = new Conn();
                        ResultSet rsc = conn.s.executeQuery("select * from bank where pin_no = '"+pin_no+"'");
                        int balance = 0;
                        while(rsc.next())
                        {
                            if(rsc.getString("type").equals("Deposit"))
                            {
                                balance += Integer.parseInt(rsc.getString("amount"));
                            }
                            else
                            {
                                balance -= Integer.parseInt(rsc.getString("amount"));
                            }
                        }
                        if(ae.getSource()!= back && balance < Integer.parseInt(amount))
                        {
                            JOptionPane.showMessageDialog(null, "Insufficient balance");
                            return;
                        }
       
                        String query4 = "insert into bank values('"+pin_no+"','"+date+"','Withdrawal','"+amount+"')";
                        conn.s.executeUpdate(query4);
                        JOptionPane.showMessageDialog(null, "Withdrawal of Amount Rs. "+amount+" is successful.");
                        }
                    catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            }
            else if(ae.getSource()==back)
            {
                new Transaction(pin_no).setVisible(true);
            }
        }
        public static void main(String[] args)
        {
            new Withdrawal("");
        }
}
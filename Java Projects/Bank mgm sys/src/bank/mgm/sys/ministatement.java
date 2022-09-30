package bank.mgm.sys;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ministatement extends JFrame implements ActionListener
{   String pin_number;
    JButton back;
    ministatement(String pin_number)
    {
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setTitle("Mini Statement");
        
        JLabel bank_name  =  new JLabel("Bank Of Coders");
        bank_name.setBounds(150, 20, 200, 25);
        bank_name.setForeground(Color.black);
        add(bank_name);
        
        JLabel card  =  new JLabel("Card Number: ");
        card.setBounds(20, 80, 200, 25);
        card.setForeground(Color.black);
        add(card);
        
        try
        {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from login where pin_number = '"+pin_number+"'");
            while(rs.next())
            {
                card.setText("Card No: " + rs.getString("card_number").substring(0,4) + "XXXXXXXX" + rs.getString("card_number").substring(12));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        JLabel mini  =  new JLabel();
        mini.setBounds(20, 150, 400, 25);
        add(mini);
        
        try
        {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from bank where pin_no = '"+pin_number+"'");
            while(rs.next())
            {
                mini.setText(mini.getText()+"<html>"+ rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("Amount")+"<br><br><html>");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        back = new JButton("Back");
        back.setBounds(140, 280, 100, 30);
        back.setForeground(Color.black);
        back.setFont(new Font("Arial",Font.PLAIN,22));
        back.addActionListener(this);
        add(back);
        
        setSize(400,600);
        setLocation(480,20);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==back)
        {
            setVisible(false);
            new Transaction(pin_number).setVisible(true);
        }
    }
    public static void main(String args[])
    {
        new ministatement("");    
    }
}

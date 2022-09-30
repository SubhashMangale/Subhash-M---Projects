package bank.mgm.sys;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class balance extends JFrame implements ActionListener{
    String pin_no;
    JButton back;
    balance(String pin_no)
    {
        setLayout(null);
        this.pin_no = pin_no;
        setLayout(null);
        getContentPane().setBackground(Color.white);
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icons/bank.jpg"));
        Image image2 = image1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel image = new JLabel(image3); //we can add ImageIcon into JLable but we cannot add Image directly so...
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        JLabel check = new JLabel("Check Your A/c balance:");
        check.setBounds(50, 200, 600, 40);
        check.setFont(new Font("Oswalds",Font.BOLD,28));
        image.add(check);
        
        back = new JButton("back");
        back.setBounds(150, 350, 100, 30);
        back.setFont(new Font("Oswalds",Font.BOLD,28));
        back.addActionListener(this);
        image.add(back);
        
        
                    Conn conn = new Conn();
                    int balance = 0;
                    try 
                    {
                        ResultSet rsc = conn.s.executeQuery("select * from bank where pin_no = '"+pin_no+"'");
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
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                    
        JLabel bal = new JLabel("Your Account balance is: "+ balance);
        bal.setForeground(Color.black);
        bal.setBackground(Color.white);
        bal.setFont(new Font("System",Font.PLAIN,26));
        bal.setBounds(60, 270, 600, 40);
        image.add(bal);
        
        setSize(800,600);
        setLocation(300,50);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==back)
            {
                setVisible(false);
                new Transaction(pin_no).setVisible(true);
            }
    }
    public static void main(String args[])
    {
        new balance("");
    }
}

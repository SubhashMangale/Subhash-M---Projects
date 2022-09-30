package bank.mgm.sys;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Deposit extends JFrame implements ActionListener
{
        JButton dep,back;
        JLabel heading;
        JTextField deposit;
        String pin_no;
        Deposit(String pin_no)
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
        
        JLabel heading1 = new JLabel("deposit:");
        heading1.setForeground(Color.black);
        heading1.setBounds(130,140,600,40);
        heading1.setFont(new Font("System",Font.BOLD,28));
        image.add(heading1);
        
        deposit = new JTextField();
        deposit.setBounds(80, 190, 200, 30);
        deposit.setBackground(Color.white);
        deposit.setFont(new Font("Arial",Font.PLAIN,26));
        deposit.setForeground(Color.black);
        image.add(deposit);
        
        dep = new JButton("Deposit");
        dep.setBounds(20, 260, 150, 40);
        dep.setFont(new Font("Raleways",Font.BOLD,22));
        dep.addActionListener(this);
        image.add(dep);
        
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
            if(ae.getSource()==dep)
            {
                String amount =  deposit.getText();
                Date date = new Date();
                if(amount.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please enter the amount");
                }
                else
                {
                    try {
                        Conn conn = new Conn();
                        String query4 = "insert into bank values('"+pin_no+"','"+date+"','Deposit','"+amount+"')";
                        conn.s.executeUpdate(query4);
                        JOptionPane.showMessageDialog(null, "Deposit of Amount "+amount+" is successful.");
                        
                    } catch (SQLException ex) {
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
            new Deposit("");
        }
}
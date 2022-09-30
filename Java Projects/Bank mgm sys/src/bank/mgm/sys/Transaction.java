package bank.mgm.sys;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transaction extends JFrame implements ActionListener
{
    JButton deposit,fast_cash,withdraw,pin_change,statement,balance,exit;
    String pin_no;
    Transaction(String pin_no)
    {   
        this.pin_no = pin_no;
        getContentPane().setBackground(Color.white);
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icons/bank.jpg"));
        Image image2 = image1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel image = new JLabel(image3); //we can add ImageIcon into JLable but we cannot add Image directly so...
        image.setBounds(0, 0, 900, 900);
        add(image);                         //we cannot directly import img path.we have to perform above 3 steps.       
    
        JLabel main_heading = new JLabel("Please select type of transaction");
        main_heading.setForeground(Color.black);
        main_heading.setFont(new Font("System",Font.BOLD,28));
        main_heading.setBounds(20, 100, 700, 40);
        image.add(main_heading);// to add text on image do it like this image.add(Your label);
        
        deposit = new JButton("Deposit");
        deposit.setBounds(20,150,200,40);
        deposit.setFont(new Font("System",Font.PLAIN,22));
        deposit.setForeground(Color.black);
        deposit.setBackground(Color.LIGHT_GRAY);
        deposit.addActionListener(this);
        image.add(deposit);
        
        fast_cash = new JButton("fast_cash");
        fast_cash.setBounds(20,220,200,40);
        fast_cash.setFont(new Font("System",Font.PLAIN,22));
        fast_cash.setForeground(Color.black);
        fast_cash.setBackground(Color.LIGHT_GRAY);
        fast_cash.addActionListener(this);
        image.add(fast_cash);
        
        pin_change = new JButton("Pin Change");
        pin_change.setBounds(20,290,200,40);
        pin_change.setFont(new Font("System",Font.PLAIN,22));
        pin_change.setForeground(Color.black);
        pin_change.setBackground(Color.LIGHT_GRAY);
        pin_change.addActionListener(this);
        image.add(pin_change);
        
        withdraw = new JButton("Withdrawal");
        withdraw.setBounds(240,150,200,40);
        withdraw.setFont(new Font("System",Font.PLAIN,22));
        withdraw.setForeground(Color.black);
        withdraw.setBackground(Color.LIGHT_GRAY);
        withdraw.addActionListener(this);
        image.add(withdraw);
        
        statement = new JButton("Mini Statement");
        statement.setBounds(240,220,200,40);
        statement.setFont(new Font("System",Font.PLAIN,22));
        statement.setForeground(Color.black);
        statement.setBackground(Color.LIGHT_GRAY);
        statement.addActionListener(this);
        image.add(statement);
        
        balance = new JButton("Balance Enquiry");
        balance.setBounds(240,290,200,40);
        balance.setFont(new Font("System",Font.PLAIN,22));
        balance.setForeground(Color.black);
        balance.setBackground(Color.LIGHT_GRAY);
        balance.addActionListener(this);
        image.add(balance);
        
        exit = new JButton("Exit");
        exit.setBounds(150,360,200,40);
        exit.setFont(new Font("System",Font.PLAIN,22));
        exit.setForeground(Color.black);
        exit.setBackground(Color.LIGHT_GRAY);
        exit.addActionListener(this);
        image.add(exit);
        
        
        setLayout(null);
        setSize(800,600);
        setLocation(300,50);
        //setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae)
    { //JButton deposit,fast_cash,cash,pin_change,statement,balance,exit;
        try{
            if(ae.getSource()==deposit)
            {
                setVisible(false);
                new Deposit(pin_no).setVisible(true);
            }
            else if(ae.getSource()==fast_cash)
            {
                setVisible(false);
                new fastcash(pin_no).setVisible(true);
            }
            else if(ae.getSource()==withdraw)
            {
                setVisible(false);
                new Withdrawal(pin_no).setVisible(true);
            }
            else if(ae.getSource()==pin_change)
            {
                setVisible(false);
                new pinchange(pin_no).setVisible(true);
            }
            else if(ae.getSource()==statement)
            {
                setVisible(false);
                new ministatement(pin_no).setVisible(true);
            }
            else if(ae.getSource()==balance)
            {
                setVisible(false);
                new balance(pin_no).setVisible(true);
            }
            else if(ae.getSource()==exit)
            {
                System.exit(0);
            }
      
           
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public static void main(String[] args)
    {
        new Transaction("");
    }
}

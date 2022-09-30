package bank.mgm.sys;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class fastcash extends JFrame implements ActionListener
{
    JButton a,b,c,d,e,f,g,exit;
    String pin_no;
    fastcash(String pin_no)
    {   
        this.pin_no = pin_no;
        getContentPane().setBackground(Color.white);
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icons/bank.jpg"));
        Image image2 = image1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel image = new JLabel(image3); //we can add ImageIcon into JLable but we cannot add Image directly so...
        image.setBounds(0, 0, 900, 900);
        add(image);                         //we cannot directly import img path.we have to perform above 3 steps.       
    
        JLabel main_heading = new JLabel("ENTER WITHDRAWAL AMOUNT ");
        main_heading.setForeground(Color.black);
        main_heading.setFont(new Font("System",Font.BOLD,28));
        main_heading.setBounds(20, 100, 700, 40);
        image.add(main_heading);// to add text on image do it like this image.add(Your label);
        
        a = new JButton("Rs.100");
        a.setBounds(20,150,200,40);
        a.setFont(new Font("System",Font.PLAIN,22));
        a.setForeground(Color.black);
        a.setBackground(Color.LIGHT_GRAY);
        a.addActionListener(this);
        image.add(a);
        
        b = new JButton("Rs.200");
        b.setBounds(20,220,200,40);
        b.setFont(new Font("System",Font.PLAIN,22));
        b.setForeground(Color.black);
        b.setBackground(Color.LIGHT_GRAY);
        b.addActionListener(this);
        image.add(b);
        
        c = new JButton("Rs.500");
        c.setBounds(20,290,200,40);
        c.setFont(new Font("System",Font.PLAIN,22));
        c.setForeground(Color.black);
        c.setBackground(Color.LIGHT_GRAY);
        c.addActionListener(this);
        image.add(c);
        
        d = new JButton("Rs.1000");
        d.setBounds(240,150,200,40);
        d.setFont(new Font("System",Font.PLAIN,22));
        d.setForeground(Color.black);
        d.setBackground(Color.LIGHT_GRAY);
        d.addActionListener(this);
        image.add(d);
        
        e = new JButton("Rs.2000");
        e.setBounds(240,220,200,40);
        e.setFont(new Font("System",Font.PLAIN,22));
        e.setForeground(Color.black);
        e.setBackground(Color.LIGHT_GRAY);
        e.addActionListener(this);
        image.add(e);
        
        f = new JButton("Rs.5000");
        f.setBounds(240,290,200,40);
        f.setFont(new Font("System",Font.PLAIN,22));
        f.setForeground(Color.black);
        f.setBackground(Color.LIGHT_GRAY);
        f.addActionListener(this);
        image.add(f);
        
        g = new JButton("Rs.10000");
        g.setBounds(150,360,200,40);
        g.setFont(new Font("System",Font.PLAIN,22));
        g.setForeground(Color.black);
        g.setBackground(Color.LIGHT_GRAY);
        g.addActionListener(this);
        image.add(g);
        
        exit = new JButton("Back");
        exit.setBounds(150,430,200,40);
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
    { 
        try{
            if(ae.getSource()==exit)
            {
                setVisible(false);
                new Transaction(pin_no).setVisible(true);
            }
else if(ae.getSource()==a || ae.getSource()==b || ae.getSource()==c || ae.getSource()==d || ae.getSource()==e || ae.getSource()==f || ae.getSource()==g)
            {
                String amount = ((JButton)ae.getSource()).getText().substring(3);
                Conn conn = new Conn();
                    try 
                    {
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
                        if(ae.getSource()!= exit && balance < Integer.parseInt(amount))
                        {
                            JOptionPane.showMessageDialog(null, "Insufficient balance");
                            return;
                        }
                        Date d = new Date();
                        String query = "insert into bank values('"+pin_no+"','"+d+"','withdrawal','"+amount+"')";
                        conn.s.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Rs. "+amount+" debited Successfully.");
                    }                  
                    catch (Exception ex) 
                    {
                        System.out.println(ex);
                    }
            }
      
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public static void main(String[] args)
    {
        new fastcash("");
    }
}

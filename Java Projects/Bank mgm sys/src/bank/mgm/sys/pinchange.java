package bank.mgm.sys;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class pinchange extends JFrame implements ActionListener{
    
    JLabel text,npin,repin;
    JPasswordField np,rnp;
    JButton change,back;
    String pin_no;
    pinchange(String pin_no)
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
        
        text = new JLabel("Change your pin");
        text.setForeground(Color.black);
        text.setBounds(80,100,600,40);
        text.setFont(new Font("Oswalds",Font.BOLD,28));
        image.add(text);
        
        npin = new JLabel("Enter New Pin:");
        npin.setForeground(Color.black);
        npin.setBounds(40,170,300,30);
        npin.setFont(new Font("Oswalds",Font.PLAIN,24));
        image.add(npin);
        
        np = new JPasswordField();
        np.setForeground(Color.black);
        np.setBounds(240,170,150,30);
        np.setFont(new Font("Oswalds",Font.PLAIN,28));
        image.add(np);
        
        repin = new JLabel("Confirm Your Pin:");
        repin.setForeground(Color.black);
        repin.setBounds(40,250,300,30);
        repin.setFont(new Font("Oswalds",Font.PLAIN,24));
        image.add(repin);
        
        rnp = new JPasswordField();
        rnp.setForeground(Color.black);
        rnp.setBounds(240,250,150,30);
        rnp.setFont(new Font("Oswalds",Font.PLAIN,28));
        image.add(rnp);
        
        change = new JButton("change");
        change.setBounds(60,330,130,30);
        change.setFont(new Font("Arial",Font.PLAIN,24));
        change.addActionListener(this);
        image.add(change);
        
        back  = new JButton("back");
        back.setBounds(220,330,130,30);
        back.setFont(new Font("Arial",Font.PLAIN,24));
        back.addActionListener(this);
        image.add(back);
        
       
        setSize(800,600);
        setLocation(300,50);
        //setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==change)
        {
            try
            {
                String new_pin = np.getText();
                String reenter_pin = rnp.getText();
            
                if(!new_pin.equals(reenter_pin))
                {
                       JOptionPane.showMessageDialog(null, "Entered pin does not match");
                       return;
                }
                
                if(new_pin.equals(""))
                {
                       JOptionPane.showMessageDialog(null, "Please enter new pin");
                       return;
                }
                
                if(reenter_pin.equals(""))
                {
                       JOptionPane.showMessageDialog(null, "Confirm your new pin");
                       return;
                }
                
                Conn conn = new Conn();
                String query1 = "update bank set pin_no = '"+new_pin+"' where pin_no = '"+pin_no+"'";
                String query2 = "update login set pin_number = '"+new_pin+"' where pin_number = '"+pin_no+"'";
                String query3 = "update signupthree set pin_number = '"+new_pin+"' where pin_number = '"+pin_no+"'";
                
                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);
                
                JOptionPane.showMessageDialog(null, "Pin changed successfully");
                
                setVisible(true);
                new Transaction(new_pin).setVisible(false);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        else if(ae.getSource()==back)
            {
                setVisible(false);
                new Transaction(pin_no).setVisible(true);
            }
    }
    public static void main(String[] args)
    {
        new pinchange("");   
    }
}

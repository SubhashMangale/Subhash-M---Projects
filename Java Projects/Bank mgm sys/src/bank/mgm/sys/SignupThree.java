package bank.mgm.sys;
import java.awt.Color;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class SignupThree extends JFrame implements ActionListener
{   JRadioButton savings,current,fixed_deposit,recurring_deposit;
    JCheckBox atm_card,internet_banking,mobile_banking,email_alerts,check_book,e_statement,final_declaration;
    JButton submit,cancel;
    String form_no;
    SignupThree(String form_no)
    {
        this.form_no = form_no;
        setLayout(null);
        getContentPane().setBackground(Color.white);
        
        JLabel App_details = new JLabel("Page 3: Application Details");
        App_details.setBounds(280, 40, 400, 30);
        App_details.setForeground(Color.BLACK);
        App_details.setFont(new Font("Raleways",Font.BOLD,22)); // for font import awt package.
        add(App_details);
        
        JLabel Acc_type = new JLabel("Account Type");
        Acc_type.setBounds(120, 100, 200, 30);
        Acc_type.setForeground(Color.BLACK);
        Acc_type.setFont(new Font("Raleways",Font.BOLD,20)); // for font import awt package.
        add(Acc_type);
        
        savings = new JRadioButton("Saving Account");
        savings.setBounds(130,140,150,30);
        savings.setFont(new Font("Arial",Font.BOLD,14)); // for font import awt package.
        savings.setBackground(Color.white);
        savings.setForeground(Color.black);
        add(savings);
        
        current = new JRadioButton("Current Account");
        current.setBounds(340,140,150,30);
        current.setFont(new Font("Arial",Font.BOLD,14)); // for font import awt package.
        current.setBackground(Color.white);
        current.setForeground(Color.black);
        add(current);
        
        fixed_deposit = new JRadioButton("Fixed deposit Account");
        fixed_deposit.setBounds(130,175,200,30);
        fixed_deposit.setFont(new Font("Arial",Font.BOLD,14)); // for font import awt package.
        fixed_deposit.setBackground(Color.white);
        fixed_deposit.setForeground(Color.black);
        add(fixed_deposit);
        
        recurring_deposit = new JRadioButton("Recurring deposit Account");
        recurring_deposit.setBounds(340,175,300,30);
        recurring_deposit.setFont(new Font("Arial",Font.BOLD,14)); // for font import awt package.
        recurring_deposit.setBackground(Color.white);
        recurring_deposit.setForeground(Color.black);
        add(recurring_deposit);
        
        ButtonGroup acc = new ButtonGroup();
        acc.add(savings);
        acc.add(current);
        acc.add(fixed_deposit);
        acc.add(recurring_deposit);
        
        JLabel card_no = new JLabel("Card Number");
        card_no.setBounds(120,230,200,30);
        card_no.setFont(new Font("Raleways",Font.BOLD,20));
        card_no.setForeground(Color.BLACK);
        add(card_no);
        
        JLabel card_under_text = new JLabel("Your 16 digit card no");
        card_under_text.setBounds(140,250,220,30);
        card_under_text.setFont(new Font("Raleways",Font.BOLD,12));
        card_under_text.setForeground(Color.BLACK);
        add(card_under_text);
        
        JLabel exact_card = new JLabel("XXXX-XXXX-XXXX-4184");
        exact_card.setBounds(330,230,200,30);
        exact_card.setFont(new Font("Arial",Font.BOLD,16));
        exact_card.setForeground(Color.BLACK);
        add(exact_card);
        
        JLabel pin_no = new JLabel("Pin");
        pin_no.setBounds(120,290,200,30);
        pin_no.setFont(new Font("Raleways",Font.BOLD,20));
        pin_no.setForeground(Color.BLACK);
        add(pin_no);
        
        JLabel pin_under_value = new JLabel("Your 4 digit password: ");
        pin_under_value.setBounds(140,310,250,30);
        pin_under_value.setFont(new Font("Raleways",Font.BOLD,12));
        pin_under_value.setForeground(Color.BLACK);
        add(pin_under_value);
        
        JLabel pin_val = new JLabel("XXXX");
        pin_val.setBounds(330,290,200,30);
        pin_val.setFont(new Font("Arial",Font.BOLD,16));
        pin_val.setForeground(Color.BLACK);
        add(pin_val);
        
        JLabel Service = new JLabel("Service Required");
        Service.setBounds(120,360,300,30);
        Service.setFont(new Font("Arial",Font.BOLD,20));
        Service.setForeground(Color.BLACK);
        Service.setBackground(Color.white);
        add(Service);
        
        atm_card =  new JCheckBox("ATM Card");
        atm_card.setBackground(Color.white);
        atm_card.setBounds(130,400,300,30);
        add(atm_card);
        
        internet_banking =  new JCheckBox("Internet banking");
        internet_banking.setBackground(Color.white);
        internet_banking.setBounds(460,400,200,30);
        add(internet_banking);
        
        mobile_banking =  new JCheckBox("Mobile Banking");
        mobile_banking.setBackground(Color.white);
        mobile_banking.setBounds(130,460,200,30);
        add(mobile_banking);
        
        email_alerts =  new JCheckBox("Email & SMS Alerts");
        email_alerts.setBackground(Color.white);
        email_alerts.setBounds(460,460,200,30);
        add(email_alerts);
        
        check_book =  new JCheckBox("Check book");
        check_book.setBackground(Color.white);
        check_book.setBounds(130,520,200,30);
        add(check_book);
        
        e_statement =  new JCheckBox("E-Statement");
        e_statement.setBackground(Color.white);
        e_statement.setBounds(460,520,200,30);
        add(e_statement);
        
        //JCheckBox atm_card,internet_banking,mobile_banking,email_alerts,check_book,e_statement,final_declaration;
        ButtonGroup once = new ButtonGroup();
        once.add(atm_card);once.add(internet_banking);once.add(mobile_banking);once.add(email_alerts);
        once.add(check_book);once.add(e_statement);
              
        final_declaration =  new JCheckBox("I hereby declares that above details are correct to the best of my knowlwdge.");
        final_declaration.setBackground(Color.white);
        final_declaration.setBounds(130,600,700,30);
        add(final_declaration);
        
        submit = new JButton("Submit");
        submit.setBounds(540,640,100,30);
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLACK);
        submit.addActionListener(this);
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(650,640,100,30);
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.addActionListener(this);
        add(cancel);
        
        setSize(800,730);
        setLocation(350,0);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        
       /*
        JRadioButton savings,current,fixed_deposit,recurring_deposit;
    
    JButton submit,cancel;
        */
       if(ae.getSource() == submit)
       {
           String Account_type = null;
           if(savings.isSelected())
           {
               Account_type = "Savings Account";
           }
           else if(current.isSelected())
           {
               Account_type = "Current Account";
           }
           else if(fixed_deposit.isSelected())
           {
               Account_type = "Fixed Deposit Account";
           }
           else 
           {
               Account_type = "Recurring deposit Account";
           }
           
           Random random = new Random();
           String card_number = ""+ Math.abs((random.nextLong() % 90000000L) + 5040221100000000L);
           
           String pin_number = ""+ Math.abs((random.nextLong() % 9000L) + 1000L);
           
           
           //JCheckBox atm_card,internet_banking,mobile_banking,email_alerts,check_book,e_statement,final_declaration;
           String facility = "";
           if(atm_card.isSelected())
           {
               facility += "Atm Card";
           }
           else if(internet_banking.isSelected())
           {
               facility += "Internet Banking";
           }
           else if(mobile_banking.isSelected())
           {
               facility += "Mobile_banking";
           }
           else if(email_alerts.isSelected())
           {
               facility += "Email alerts";
           }
           else if(check_book.isSelected())
           {
               facility += "Check Book";
           }
           else if(e_statement.isSelected())
           {
               facility += "E-statement";
           }
       try
       {
           if(Account_type.equals(""))
           {
               JOptionPane.showMessageDialog(null,"Account type is required");
           }
           if(final_declaration.isSelected()==false)
           {
               JOptionPane.showMessageDialog(null,"Please enter correct details!!! ");
           }
           else
           {
               Conn conn = new Conn();
               String query1 = "insert into signupthree values('"+form_no+"','"+Account_type+"','"+card_number+"','"+pin_number+"','"+facility+"')";
               String query2 = "insert into login values('"+form_no+"','"+card_number+"','"+pin_number+"')";
               conn.s.executeUpdate(query1);
               conn.s.executeUpdate(query2);
               JOptionPane.showMessageDialog(null,"Your card no. is "+card_number+"\n"+"Your Pin: "+pin_number);
           }
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
       }
       else if(ae.getSource()==cancel)
       {
          setVisible(false);
          new Login().setVisible(true);
          
       }
    }
    public static void main(String[] args)
    {
        new SignupThree("");
    }
}

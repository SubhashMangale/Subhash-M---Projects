package bank.mgm.sys;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
public class SignupOne extends JFrame implements ActionListener {
    long random;
    JLabel form_no;
    JTextField Name,f_name,emailtextfield,addresstextfield,citytextfield,postpintextfield;
    JDateChooser dobtextfield;
    JRadioButton Male,Female,Married,Unmarried;
    
    SignupOne() 
    {
        setLayout(null); // null this to perform custom alignment.
        
        Random ran = new Random();
        random =  Math.abs((ran.nextLong()%9000L) + 1000L);

        form_no = new JLabel("Your Application form No. "+ random);
        form_no.setBounds(220,10,600,40);
        form_no.setFont(new Font("Oswalds", Font.CENTER_BASELINE, 28));
        add(form_no);
        
        JLabel Personal_Details = new JLabel("Page 1: Personal Details");
        Personal_Details.setBounds(250,60,600,40);
        Personal_Details.setFont(new Font("Oswalds", Font.CENTER_BASELINE, 28));
        add(Personal_Details);
        
        JLabel name = new JLabel("Name: ");
        name.setBounds(80,130,300,30);
        name.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(name);
        
        Name =  new JTextField();
        Name.setBounds(250,130,400,30);
        Name.setFont(new Font("Raleways", Font.PLAIN,20));
        Name.setForeground(Color.BLACK);
        add(Name);
        
        JLabel F_name = new JLabel("Father's Name: ");
        F_name.setBounds(80,180,300,30);
        F_name.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(F_name);
        
        f_name =  new JTextField();
        f_name.setBounds(250,180,400,30);
        f_name.setFont(new Font("Raleways", Font.PLAIN,20));
        f_name.setForeground(Color.BLACK);
        add(f_name);
        
        JLabel dob = new JLabel("Date of Birth: ");
        dob.setBounds(80,230,300,30);
        dob.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(dob);
        
        dobtextfield =  new JDateChooser();
        dobtextfield.setBounds(250,230,400,30);
        dobtextfield.setFont(new Font("Raleways", Font.PLAIN,22));
        dobtextfield.setForeground(Color.BLACK);
        add(dobtextfield);
        
        JLabel gender = new JLabel("Gender: ");
        gender.setBounds(80,280,300,30);
        gender.setFont(new Font("Arial ", Font.PLAIN, 20));
        add(gender);
        
        Male  = new JRadioButton("Male");
        Male.setBounds(250, 280, 100, 22);
        Male.setBackground(Color.white);
        add(Male);
        
        Female  = new JRadioButton("Female");
        Female.setBounds(380, 280, 100, 22);
        Female.setBackground(Color.white);
        add(Female);
        
        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(Male);
        gendergroup.add(Female);
        
        
        JLabel email = new JLabel("Email Address: ");
        email.setBounds(80,330,300,30);
        email.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(email);
        
        emailtextfield =  new JTextField();
        emailtextfield.setBounds(250,330,400,30);
        emailtextfield.setFont(new Font("Raleways", Font.PLAIN,20));
        emailtextfield.setForeground(Color.BLACK);
        add(emailtextfield);
        
        JLabel marital = new JLabel("Marital Status: ");
        marital.setBounds(80,380,300,30);
        marital.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(marital);
        
        Married  = new JRadioButton("Married");
        Married.setBounds(250, 380, 100, 22);
        Married.setBackground(Color.white);
        add(Married);
        
        Unmarried  = new JRadioButton("Unmarried");
        Unmarried.setBounds(380, 380, 100, 22);
        Unmarried.setBackground(Color.white);
        add(Unmarried);
        
        ButtonGroup maritalstatus = new ButtonGroup();
        maritalstatus.add(Married);
        maritalstatus.add(Unmarried);
        
        JLabel Address = new JLabel("Address: ");
        Address.setBounds(80,430,300,30);
        Address.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(Address);
        
        addresstextfield =  new JTextField();
        addresstextfield.setBounds(250,430,400,30);
        addresstextfield.setFont(new Font("Raleways", Font.PLAIN,20));
        addresstextfield.setForeground(Color.BLACK);
        add(addresstextfield);
        
        
        JLabel City = new JLabel("City: ");
        City.setBounds(80,480,300,30);
        City.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(City);
        
        citytextfield =  new JTextField();
        citytextfield.setBounds(250,480,400,30);
        citytextfield.setFont(new Font("Raleways", Font.PLAIN,20));
        citytextfield.setForeground(Color.BLACK);
        add(citytextfield);
        
        JLabel PIN_code = new JLabel("Postal PIN: ");
        PIN_code.setBounds(80,530,300,30);
        PIN_code.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(PIN_code);
        
        postpintextfield =  new JTextField();
        postpintextfield.setBounds(250,530,400,30);
        postpintextfield.setFont(new Font("Raleways", Font.PLAIN,20));
        postpintextfield.setForeground(Color.BLACK);
        add(postpintextfield);
        
        JButton Next =  new JButton("Next");
        Next.setBounds(570, 580, 80, 30);
        Next.setBackground(Color.BLACK);
        Next.setForeground(Color.WHITE);
        Next.setFont(new Font("Arial",Font.BOLD,18));
        Next.addActionListener(this);
        add(Next);  
        
        getContentPane().setBackground(Color.white);
        setSize(850,700);
        setLocation(250,10);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String formno = "" + random; // convert long into string
        String name = Name.getText();
        String F_name = f_name.getText();
        String DOB = ((JTextField)dobtextfield.getDateEditor().getUiComponent()).getText(); // convert dob date into string.
        
        String Gender = null;
        if(Male.isSelected())
        {
            Gender = "Male";
        }
        else if(Female.isSelected())
        {
            Gender = "Female";
        }
        String Email = emailtextfield.getText();
        String Marriage_status = null;
        if(Married.isSelected())
        {
            Marriage_status = "Married";
        }
        else if(Unmarried.isSelected())
        {
            Marriage_status = "Unmarried";
        }
        String Addr = addresstextfield.getText();
        String City = citytextfield.getText();
        String Postal_code = postpintextfield.getText();
        
            try
            {
                Conn c = new Conn();
                String query =  "insert into signup values('"+formno+"','"+name+"','"+F_name+"','"+DOB+"','"+Gender+"','"+Email+"','"+Marriage_status+"','"+Addr+"','"+City+"','"+Postal_code+"')";
                c.s.executeUpdate(query);
                
                setVisible(false);
                new SignupTwo(formno).setVisible(true);
            }
            catch (Exception e){
                    System.out.println(e);
            }   
    }
    public static void main(String[] args)
    {
        new SignupOne();
    }
}
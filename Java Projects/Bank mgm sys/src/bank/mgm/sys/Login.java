package bank.mgm.sys;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
// To create login frame of login page import swing lib from javax package.
public class Login extends JFrame implements ActionListener {
    JButton login,sign_up,clear;
    JTextField cardTextField;
    JPasswordField pinTextField;
    Login()
    {
        setTitle("Bank of Coder@Subhash"); // title on frame
        setLayout(null);
        
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image image2 = image1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel label = new JLabel(image3); //we can add ImageIcon into JLable but we cannot add Image directly so...
        add(label);                         //we cannot directly import img path.we have to perform above 3 steps.
        label.setBounds(50, 10, 100, 100);
        
        JLabel top_text = new JLabel("Welcome to E-Bank!!!");
        top_text.setFont(new Font("Osward",Font.BOLD,38));
        top_text.setBounds(200, 40, 400, 40);
        add(top_text);
        
        JLabel card = new JLabel("Card No.");
        card.setFont(new Font("Raleways",Font.BOLD,28));
        card.setBounds(120, 150, 150, 40);
        add(card);
        
        cardTextField = new JTextField();
        cardTextField.setBounds(300,150,250,40);
        cardTextField.setFont(new Font("Arial",Font.BOLD,20));
        add(cardTextField);
        
        JLabel pin = new JLabel("PIN");
        pin.setFont(new Font("Raleways",Font.BOLD,28));
        pin.setBounds(120, 200, 400, 40);
        add(pin);
        
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300,200,250,40);
        pinTextField.setFont(new Font("Arial",Font.BOLD,20));
        add(pinTextField);
        
        login = new JButton("SIGN IN");
        login.setBounds(300,280,100,40);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);
        
        sign_up = new JButton("SIGN UP");
        sign_up.setBounds(450,280,100,40);
        sign_up.setBackground(Color.BLACK);
        sign_up.setForeground(Color.WHITE);
        sign_up.addActionListener(this);
        add(sign_up);
        
        clear = new JButton("CLEAR");
        clear.setBounds(300,350,250,40);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);
        
       
        getContentPane().setBackground(Color.white);
        setSize(800,500); // defines dimension of frame.
        // Frames are by default hidden from user so we have to use below fun.
        setVisible(true);
       
        setLocation(300,100); // set location of pop up frame.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()== clear)
        {
            cardTextField.setText("");
            pinTextField.setText("");
        }
        else if(ae.getSource() == sign_up)
        {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
        else if(ae.getSource() ==  login)
        {
            String card_no = cardTextField.getText();
            String pin_no = pinTextField.getText();
            String query3 = "select * from login where card_number = '"+card_no+"' and pin_number= '"+pin_no+"'";
            
            try
            {
                Conn conn = new Conn();
                ResultSet rsx = conn.s.executeQuery(query3);
                if(rsx.next())
                {
                    setVisible(false);
                    new Transaction(pin_no).setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please enter Correct Details..");
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }   
    }
    
    public static void main(String[] args)
    {
        new Login();
    }
}


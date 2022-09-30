package bank.mgm.sys;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class SignupTwo extends JFrame implements ActionListener {
   
    JComboBox religionbox,categorybox,incomebox,educationbox,occuptionbox;
    JTextField pantextfield,adhartextfield;
    JRadioButton acYes,acNo,cYes,cNo;
    String form_no;
    SignupTwo(String form_no) 
    {
        this.form_no = form_no;
        setLayout(null); // null this to perform custom alignment.
        
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");
       
        JLabel Additional_Details = new JLabel("Page 2: Additional Details");
        Additional_Details.setBounds(250,10,600,40);
        Additional_Details.setFont(new Font("Oswalds", Font.CENTER_BASELINE, 28));
        add(Additional_Details);
        
        
        JLabel Relig = new JLabel("Religion:");
        Relig.setBounds(80,120,300,30);
        Relig.setFont(new Font("Oswalds", Font.PLAIN, 22));
        add(Relig);
        
        String valReligion[] = {null,"Hindu","Muslim","Sikh","Christian","Other"};
        religionbox  = new JComboBox(valReligion);
        religionbox.setBounds(250,120,400,30);
        religionbox.setFont(new Font("Arial", Font.PLAIN,18));
        religionbox.setForeground(Color.white);
        religionbox.setBackground(Color.WHITE);
        add(religionbox);
        
        JLabel Category = new JLabel("Category: ");
        Category.setBounds(80,180,300,30);
        Category.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(Category);
        
        String valCategory[] = {null,"General","OBC","SC/ST","Other"};
        categorybox =  new JComboBox(valCategory);
        categorybox.setBounds(250,180,400,30);
        categorybox.setFont(new Font("Raleways", Font.PLAIN,18));
        categorybox.setForeground(Color.BLACK);
        religionbox.setBackground(Color.WHITE);
        add(categorybox);
        
        JLabel Income = new JLabel("Income: ");
        Income.setBounds(80,230,300,30);
        Income.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(Income);
        
        String valIncome[] = {null,"<100000","100000-500000","500000-800000",">800000"};
        incomebox =  new JComboBox(valIncome);
        incomebox.setBounds(250,230,400,30);
        incomebox.setFont(new Font("Raleways", Font.PLAIN,18));
        incomebox.setForeground(Color.BLACK);
        religionbox.setBackground(Color.WHITE);
        add(incomebox);
        
        JLabel Education = new JLabel("Highest Education: ");
        Education.setBounds(80,280,300,30);
        Education.setFont(new Font("Arial ", Font.PLAIN, 20));
        add(Education);
        
        String valEducation[] = {null,"SSC","HSC","Graduation","Post Graduation","doctorate"};
        educationbox  = new JComboBox(valEducation);
        educationbox.setBounds(250, 280, 400, 30);
        educationbox.setFont(new Font("Arial",Font.PLAIN,18));
        educationbox.setBackground(Color.white);
        religionbox.setForeground(Color.BLACK);
        add(educationbox);
     
        JLabel Occuption = new JLabel("Occuption: ");
        Occuption.setBounds(80,330,300,30);
        Occuption.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(Occuption);
        
        String valOccuption[] = {null,"Salaried","Unsalaried","Self-employed","Business","Other"};
        occuptionbox =  new JComboBox(valOccuption);
        occuptionbox.setBounds(250,330,400,30);
        occuptionbox.setFont(new Font("Raleways", Font.PLAIN,18));
        occuptionbox.setForeground(Color.BLACK);
        occuptionbox.setBackground(Color.WHITE);
        add(occuptionbox);
        
        JLabel PAN = new JLabel("PAN Card: ");
        PAN.setBounds(80,380,300,30);
        PAN.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(PAN);
        
        pantextfield  = new JTextField();
        pantextfield.setBounds(250, 380, 400, 30);
        pantextfield.setFont(new Font("Arial",Font.PLAIN,18));
        pantextfield.setBackground(Color.white);
        pantextfield.setForeground(Color.black);
        add(pantextfield);
      
                
        JLabel Adhar = new JLabel("Adhar Card: ");
        Adhar.setBounds(80,430,300,30);
        Adhar.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(Adhar);
        
        adhartextfield =  new JTextField();
        adhartextfield.setBounds(250,430,400,30);
        adhartextfield.setFont(new Font("Raleways", Font.PLAIN,18));
        adhartextfield.setForeground(Color.BLACK);
        add(adhartextfield);
        
        
        JLabel senior_citizen = new JLabel("Senior Citizen: ");
        senior_citizen.setBounds(80,480,300,30);
        senior_citizen.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(senior_citizen);
           
        cYes  = new JRadioButton("Yes");
        cYes.setBounds(250, 480, 100, 30);
        cYes.setBackground(Color.white);
        cYes.setForeground(Color.BLACK);
        add(cYes);
        
        cNo  = new JRadioButton("No");
        cNo.setBounds(400, 480, 100, 30);
        cNo.setBackground(Color.white);
        cNo.setForeground(Color.BLACK);
        add(cNo);
        
        ButtonGroup citizens = new ButtonGroup();
        citizens.add(cYes);
        citizens.add(cNo);
        
        JLabel Account_existing = new JLabel("Existing Account: ");
        Account_existing.setBounds(80,530,300,30);
        Account_existing.setFont(new Font("Arial ", Font.PLAIN, 22));
        add(Account_existing);
        
        acYes  = new JRadioButton("Yes");
        acYes.setBounds(250, 530, 100, 22);
        acYes.setBackground(Color.white);
        acYes.setForeground(Color.BLACK);
        add(acYes);
        
        acNo  = new JRadioButton("No");
        acNo.setBounds(400, 530, 100, 22);
        acNo.setBackground(Color.white);
        acNo.setForeground(Color.BLACK);
        add(acNo);
        
        ButtonGroup existing_account = new ButtonGroup();
        existing_account.add(acYes);
        existing_account.add(acNo);
       
        
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
//       JComboBox religionbox,categorybox,incomebox,educationbox,occuptionbox;
//       JTextField pantextfield,adhartextfield;
//       JRadioButton Yes,No;
       String sreligion = (String) religionbox.getSelectedItem();
       String scategory = (String) categorybox.getSelectedItem();
       String sincome = (String) incomebox.getSelectedItem();
       String seducation = (String) educationbox.getSelectedItem();
       String soccuption = (String) occuptionbox.getSelectedItem();
       
       String span = pantextfield.getText();
       String sadhaar = adhartextfield.getText();
       
       String seniorc = null;
       if(cYes.isSelected())
       {
           seniorc = "Yes";
       }
       else if(cNo.isSelected())
       {
           seniorc = "No";
       }
       
       String exstacc = null;
       if(acYes.isSelected())
       {
           exstacc = "Yes";
       }
       else if(acNo.isSelected())
       {
           exstacc = "No";
       }
       
       try
       {
           Conn c = new Conn();
           String query =  "insert into signup_additional_details values('"+form_no+"','"+sreligion+"','"+scategory+"','"+sincome+"','"+seducation+"','"+soccuption+"','"+span+"','"+sadhaar+"','"+seniorc+"','"+exstacc+"')";
           c.s.executeUpdate(query);
           
           setVisible(false);
           new SignupThree(form_no).setVisible(true);
           
       }
       catch (SQLException e)
       {
           System.out.println(e);
       }
       
    }
    public static void main(String[] args)
    {
        new SignupTwo("");
    }
}
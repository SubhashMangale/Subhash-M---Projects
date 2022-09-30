package chat.application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import java.text.*;
import java.io.*;
import java.net.*;

public class Client implements ActionListener{
    
    static JFrame f =  new JFrame();
    JTextField msg;
    static JPanel chat;
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dout;
 
    
    Client()
    {
        f.setLayout(null);
        
        JPanel topslide = new JPanel();
        topslide.setBackground(new Color(50,205,50));
        topslide.setBounds(0,0,450,70);
        topslide.setLayout(null);
        f.add(topslide);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        topslide.add(back);
        
        back.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                System.exit(0); 
            }
        });
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/pink.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel server_dp = new JLabel(i6);
        server_dp.setBounds(45, 20, 35, 35);
        topslide.add(server_dp);
        
        ImageIcon i8 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i9 = i8.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        ImageIcon i10 = new ImageIcon(i9);
        JLabel video_icon = new JLabel(i10);
        video_icon.setBounds(280, 25, 45, 25);
        topslide.add(video_icon);
        
        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i12 = i11.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel call_icon = new JLabel(i13);
        call_icon.setBounds(340, 25, 45, 25);
        topslide.add(call_icon);
        
        ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i15 = i14.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i16 = new ImageIcon(i15);
        JLabel moreverrt = new JLabel(i16);
        moreverrt.setBounds(410, 25, 10, 25);
        topslide.add(moreverrt);
        
        JLabel name = new JLabel("Pink-Ranger");
        name.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        name.setBounds(90,20,100,20);
        name.setForeground(Color.white);
        topslide.add(name);
        
        JLabel status = new JLabel("Online");
        status.setFont(new Font("SAN_SERIF",Font.BOLD,12));
        status.setBounds(100,40,100,20);
        status.setForeground(Color.white);
        topslide.add(status);
        
        
        // Creation of chatting panel 
        chat =  new JPanel();
        chat.setBounds(5, 75, 424, 425);
        f.add(chat);
        
        
        msg = new JTextField();
        msg.setBounds(5, 510, 310, 35);
        msg.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(msg);
        
        JButton send = new JButton("Send");
        send.setBounds(320, 510, 100, 35);
        send.setFont(new Font("SAN_SERIF",Font.BOLD,15));
        send.setBackground(new Color(37,211,102));
        send.setForeground(Color.white);
        send.addActionListener(this);
        f.add(send);
        
        
        f.setSize(450,600);
        f.setLocation(200,30);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        try
        {
        String out = msg.getText();
        
        JPanel x = formatLabel(out);
        
        chat.setLayout(new BorderLayout()); // this adjust components at right,left,top,botton,centers.
        
        JPanel right = new JPanel(new BorderLayout());
        right.add(x,BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        chat.add(vertical,BorderLayout.PAGE_START);
        
        dout.writeUTF(out);
        msg.setText("");
        f.repaint();
        f.invalidate();
        f.validate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static JPanel formatLabel(String Label)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style=\"width :150px\">"+Label+"</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(127,255,0));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf =  new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        
        return panel;
    }
    public static void main(String args[])
    {
        new Client();
        
        try
        {
            Socket s = new Socket("127.0.0.1",6001);
            while(true)
            {
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            
            while(true)
                {
                    String messege = din.readUTF();
                    JPanel  panel = formatLabel(messege);
                    
           
                    // for rx msg build left Jpanel
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    
                    vertical.add(left);
                    vertical.add(Box.createVerticalStrut(15));
                    chat.add(vertical,BorderLayout.PAGE_START);
                    
                    f.validate();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

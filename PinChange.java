package bankapp;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class PinChange extends JFrame implements ActionListener {
	
	String username;
	JButton back,submit;
	JPasswordField toPin,tnPin,trnPin;
	
	
	public PinChange(String username) {
		 setLayout(null);
	     this.username=username;
	     ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("bankapp/atm.jpg"));
		 Image i2=i1.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
		 ImageIcon i3=new ImageIcon(i2);
		 JLabel image=new JLabel(i3);
		 image.setBounds(0,0,700,700);
		 add(image);
		
		 JLabel heading=new JLabel("Change your Pin");
		 heading.setBounds(210,220,300,40);
		 heading.setFont(new Font("Areal",Font.BOLD,12));
	     heading.setForeground(Color.white);
		 image.add(heading);
		 
		 JLabel oPin=new JLabel("Enter Old Pin:");
		 oPin.setBounds(130,250,300,40);
		 oPin.setFont(new Font("Areal",Font.BOLD,12));
		 oPin.setForeground(Color.white);
		 image.add(oPin);
		 
		 toPin=new JPasswordField();
		 toPin.setBounds(250, 260, 150, 18);
		 toPin.setFont(new Font("Areal",Font.BOLD,12));
		 toPin.setForeground(Color.BLACK);
		 image.add(toPin);
		 
		 JLabel nPin=new JLabel("Enter New Pin:");
		 nPin.setBounds(130,280,300,40);
		 nPin.setFont(new Font("Areal",Font.BOLD,12));
		 nPin.setForeground(Color.white);
		 image.add(nPin);
		 
		 tnPin=new JPasswordField();
		 tnPin.setBounds(250, 290, 150, 18);
		 tnPin.setFont(new Font("Areal",Font.BOLD,12));
		 tnPin.setForeground(Color.BLACK);
		 image.add(tnPin);
		 
		 JLabel rnPin=new JLabel("Re-enter New Pin:");
		 rnPin.setBounds(130,310,300,40);
		 rnPin.setFont(new Font("Areal",Font.BOLD,12));
		 rnPin.setForeground(Color.white);
		 image.add(rnPin);
		 
		 trnPin=new JPasswordField();
		 trnPin.setBounds(250, 320, 150, 18);
		 trnPin.setFont(new Font("Areal",Font.BOLD,12));
		 trnPin.setForeground(Color.BLACK);
		 image.add(trnPin);
		 
		 submit=new JButton("SUBMIT");
		 submit.setBounds(275,375,120,25);
		 submit.setFont(new Font("Areal",Font.BOLD,9));
		 submit.addActionListener(this);
		 image.add(submit);
		 
		 back=new JButton("BACK");
		 back.setBounds(275,405,120,25);
		 back.setFont(new Font("Areal",Font.BOLD,9));
		 back.addActionListener(this);
		 image.add(back);
		 
		 
		 
		 setSize(700 , 700);
	     setVisible(true);
	     setLocation(250,0);
	}
		
   public void actionPerformed(ActionEvent ae) {
	   if(ae.getSource()==back) {
		   setVisible(false);
		   new Transactions(username).setVisible(true);
	   }else if(ae.getSource()==submit) {
		   String oldPin=toPin.getText();
		   String newPin=tnPin.getText();
		   String renewPin=trnPin.getText();
		   try {
			   Conn conn=new Conn();
			   if(! newPin.equals(renewPin)) {
				   JOptionPane.showMessageDialog(null,"New Pin and Re Entered Pin does not match");
				   return;
			   }
			   if(oldPin.equals("") || newPin.equals("") || renewPin.equals("")) {
				   JOptionPane.showMessageDialog(null,"Please Enter All the Values");
				   return;
			   }
			   String Query="select * from login where pinno = '"+oldPin+"' and username = '"+username+"' ";
			   ResultSet rs=conn.s.executeQuery(Query);
			   if(rs.next()) {
				   String Query1="update login set pinno = '"+renewPin+"' where username= '"+username+"'";
				   String Query2="update signup2 set pinno = '"+renewPin+"' where username= '"+username+"'";
				   conn.s.executeUpdate(Query1);
				   conn.s.executeUpdate(Query2);
				   JOptionPane.showMessageDialog(null,"Pin Changed Successfully");
				   setVisible(false);
				   new Transactions(username).setVisible(true);
			   }else {
				   JOptionPane.showMessageDialog(null, "Entered Old Pin is wrong");
				   return;
			   }
			   
		   }catch(Exception e) {
			   System.out.println(e);
		   }
	   }
   }
   
   
	public static void main(String[] args) {
		new PinChange("");
	}

}

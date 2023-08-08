package bankapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class Balance extends JFrame implements ActionListener{
	
	String username;
	JButton back;
	
	public Balance(String username) {
		setLayout(null);
	    this.username=username;
	    
	    ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("bankapp/atm.jpg"));
		Image i2=i1.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel image=new JLabel(i3);
		image.setBounds(0,0,700,700);
		add(image);
		
		back=new JButton("BACK");
		back.setBounds(275,405,120,25);
		back.setFont(new Font("Areal",Font.BOLD,9));
		back.addActionListener(this);
		image.add(back);
		
		Conn conn = new Conn();
		int balance=0;
		try {
			String query="select * from balance where username= '"+username+"'";
			ResultSet rs=conn.s.executeQuery(query);
			while(rs.next()) {
				balance=Integer.parseInt(rs.getString("balance"));
			}	
		}catch(Exception e) {
			System.out.println(e);
		}
	    
	    JLabel text=new JLabel("Your Account Balance is Rs."+balance);
	    text.setForeground(Color.WHITE);
	    text.setBounds(130,230,400,40);
	    image.add(text);
		
	    setSize(700 , 700);
	    setVisible(true);
	    setLocation(250,0);
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==back) {
			setVisible(false);
			new Transactions(username).setVisible(true);
		}
	}

	public static void main(String[] args) {
		new Balance("");
	}

}

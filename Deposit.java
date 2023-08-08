package bankapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.util.Date;



public class Deposit extends JFrame implements ActionListener{
	
	JTextField fDeposit;
	JButton back,deposit;
	String username;
	
	public Deposit(String Username) { 
		this.username=Username;
		setLayout(null);
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("bankapp/atm.jpg"));
		Image i2=i1.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel image=new JLabel(i3);
		image.setBounds(0,0,700,700);
		add(image);
		
		JLabel heading=new JLabel("Please Enter Amount you want to Deposit");
		heading.setBounds(140,225,700,40);
		heading.setFont(new Font("Areal",Font.BOLD,12));
		heading.setForeground(Color.white);
		image.add(heading);
		
		fDeposit=new JTextField();
		fDeposit.setBounds(140,255,200,20);
		fDeposit.setFont(new Font("Areal",Font.BOLD,12));
		fDeposit.setForeground(Color.BLACK);
		image.add(fDeposit);
		
		
		deposit=new JButton("DEPOSIT");
        deposit.setBounds(275,378,120,25);
        deposit.setFont(new Font("Areal",Font.BOLD,9));
        deposit.addActionListener(this);
        image.add(deposit);
	        
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
		if(ae.getSource() == deposit) {
			String amount=fDeposit.getText();
			Date date=new Date();
			if(amount.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter the amount to Deposit");
			}else {
				try {
				Conn conn=new Conn();
				String Query="insert into bank values('"+username+"','"+date+"','Deposit','"+amount+"')";
				conn.s.executeUpdate(Query);
				JOptionPane.showMessageDialog(null, "Rs"+amount+" is Successfully Deposited");
				setVisible(false);
				new Transactions(username).setVisible(true);
				}catch(Exception e) {
					System.out.println(e);
				}
				
				try {
					Conn conn=new Conn();
					ResultSet rs=conn.s.executeQuery("select * from balance where username='"+username+"'");
					int balance=0;
					while(rs.next()) {
						balance=Integer.parseInt(rs.getString("balance"));
					}
					balance=balance+Integer.parseInt(amount);
					String bal=String.valueOf(balance);
					String query="update balance set balance='"+bal+"' where username='"+username+"'";
					conn.s.executeUpdate(query);
				}catch(Exception e) {
					System.out.print(e);
				}
			}
		}else {
			setVisible(false);
			new Transactions(username).setVisible(true);
		}
	}
	
	public static void main(String args[]) {
		new Deposit("");
	}
	
}





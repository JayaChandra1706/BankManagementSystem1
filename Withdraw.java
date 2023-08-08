package bankapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;


public class Withdraw extends JFrame implements ActionListener{
	
	
	JTextField fWithdraw;
	JButton withdraw,back;
	String username;
	
	
	public Withdraw(String username) {
        setLayout(null);
        
        this.username=username;
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("bankapp/atm.jpg"));
		Image i2=i1.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel image=new JLabel(i3);
		image.setBounds(0,0,700,700);
		add(image);
		
		JLabel heading=new JLabel("Please Enter Amount you want to Withdraw");
		heading.setBounds(140,225,700,40);
		heading.setFont(new Font("Areal",Font.BOLD,12));
		heading.setForeground(Color.white);
		image.add(heading);
		
		fWithdraw=new JTextField();
		fWithdraw.setBounds(140,255,200,20);
		fWithdraw.setFont(new Font("Areal",Font.BOLD,12));
		fWithdraw.setForeground(Color.BLACK);
		image.add(fWithdraw);
		
		
		withdraw=new JButton("Withdraw");
		withdraw.setBounds(275,378,120,25);
		withdraw.setFont(new Font("Areal",Font.BOLD,9));
		withdraw.addActionListener(this);
        image.add(withdraw);
	        
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
		}else if(ae.getSource()==withdraw){
			String amount=fWithdraw.getText();
			Date date=new Date();
			if(amount.equals("")) {
				JOptionPane.showMessageDialog(null,"please enter the amount to withdraw");
			}else {
				try {
					Conn conn=new Conn();
					ResultSet rs=conn.s.executeQuery("select * from balance where username = '"+username+"'");
					int balance=0;
					while(rs.next()) {
						balance=Integer.parseInt(rs.getString("balance"));
					}
					if(balance >= Integer.parseInt(amount)) {
					String query="insert into bank values('"+username+"','"+date+"','Withdraw','"+amount+"')";
					conn.s.executeUpdate(query);
					balance=balance-Integer.parseInt(amount);
					String bal=String.valueOf(balance);
					String query1="update balance set balance='"+bal+"' where username='"+username+"'";
					conn.s.executeUpdate(query1);
					JOptionPane.showMessageDialog(null,"Rs"+amount+" Withdrawn Successfully");
					setVisible(false);
					new Transactions(username).setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Insufficient Balance");
						return;
					}
				}catch(Exception e) {
					System.out.println(e);
				}
			}
		}
	}
	
	public static void main(String args[]) {
		new Withdraw("");
	}
}


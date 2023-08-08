package bankapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;


public class FastCash extends JFrame implements ActionListener {
	
	JButton fc1,fc2,fc3,fc4,fc5,fc6,back;
	String username;
	
	public FastCash(String username) {
		
		this.username=username;
	    setLayout(null);
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("bankapp/atm.jpg"));
		Image i2=i1.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel image=new JLabel(i3);
		image.setBounds(0,0,700,700);
		add(image);
		
		JLabel heading=new JLabel("Please Select Your Amount");
		heading.setBounds(180,230,700,40);
		heading.setFont(new Font("Areal",Font.BOLD,12));
		heading.setForeground(Color.white);
		image.add(heading);
		
        fc1=new JButton("Rs 500");
        fc1.setBounds(130,324,120,25);
        fc1.setFont(new Font("Areal",Font.BOLD,9));
        fc1.addActionListener(this);
        image.add(fc1);
        
        fc2=new JButton("Rs 1000");
        fc2.setBounds(275,324,120,25);
        fc2.setFont(new Font("Areal",Font.BOLD,9));
        fc2.addActionListener(this);
        image.add(fc2);
        
        fc3=new JButton("Rs 2000");
        fc3.setBounds(130,351,120,25);
        fc3.setFont(new Font("Areal",Font.BOLD,9));
        fc3.addActionListener(this);
        image.add(fc3);
        
        fc4=new JButton("Rs 5000");
        fc4.setBounds(275,351,120,25);
        fc4.setFont(new Font("Areal",Font.BOLD,9));
        fc4.addActionListener(this);
        image.add(fc4);
        
        fc5=new JButton("Rs 10000");
        fc5.setBounds(130,378,120,25);
        fc5.setFont(new Font("Areal",Font.BOLD,9));
        fc5.addActionListener(this);
        image.add(fc5);
        
        fc6=new JButton("Rs 20000");
        fc6.setBounds(275,378,120,25);
        fc6.setFont(new Font("Areal",Font.BOLD,9));
        fc6.addActionListener(this);
        image.add(fc6);
        
        back=new JButton("Back");
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
		}else {
			String amount=((JButton)ae.getSource()).getText().substring(3);
			try {
				Conn conn=new Conn();
				ResultSet rs=conn.s.executeQuery("select * from balance where username= '"+username+"' ");
				int balance=0;
				while(rs.next()) {
					balance=Integer.parseInt(rs.getString("balance"));
				}
				
				if(ae.getSource() != back && balance < Integer.parseInt(amount)) {
					JOptionPane.showMessageDialog(null, "Insuffisient Balance");
					return;
				}
				
				    Date date=new Date();
					String Query="insert into bank values('"+username+"','"+date+"','Withdraw','"+amount+"')";
					conn.s.executeUpdate(Query);
					balance=balance-Integer.parseInt(amount);
					String bal=String.valueOf(balance);
					String query1="update balance set balance='"+bal+"' where username='"+username+"'";
					conn.s.executeUpdate(query1);
					JOptionPane.showMessageDialog(null, "Rs"+amount+" Withdraw Successfully");
					setVisible(false);
					new Transactions(username).setVisible(true);
				
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) {
		new FastCash("");
	}

}

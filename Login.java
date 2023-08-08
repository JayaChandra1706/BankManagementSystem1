package bankapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener{
	
	JTextField textUsername;
	JButton login;
	JPasswordField fpassword,fpin;
	
	Login(){
		setLayout(null);
		setTitle("LOGIN PAGE");
		JLabel heading=new JLabel("LOGIN");
		heading.setBounds(325, 10 , 200 , 60);
		heading.setFont(new Font("Areal",Font.BOLD,38));
		add(heading);
		
		JLabel Username=new JLabel("Username:");
		Username.setBounds(150, 100 , 200 , 60);
		Username.setFont(new Font("Areal",Font.BOLD,30));
		add(Username);
		
		textUsername=new JTextField();
		textUsername.setBounds(350,110,300,40);
		textUsername.setFont(new Font("Areal",Font.BOLD,25));
		add(textUsername);
		
		JLabel Password=new JLabel("Password:");
		Password.setBounds(150, 170 , 200 , 60);
		Password.setFont(new Font("Areal",Font.BOLD,30));
		add(Password);
		
		fpassword=new JPasswordField();
		fpassword.setBounds(350, 180, 300, 40);
		fpassword.setFont(new Font("Areal",Font.BOLD,25));
		add(fpassword);
		
		JLabel pinno=new JLabel("PIN :");
		pinno.setBounds(150,240,200,40);
		pinno.setFont(new Font("Areal",Font.BOLD,30));
		add(pinno);
		
		fpin=new JPasswordField();
		fpin.setBounds(350, 250, 300, 40);
		fpin.setFont(new Font("Areal",Font.BOLD,25));
		add(fpin);
		
		 login=new JButton("LOGIN");
		 login.setBounds(300,350,200,60);
		 login.setBackground(Color.blue);
		 login.setForeground(Color.WHITE);
	   	 login.setFont(new Font("Osward",Font.BOLD,20));
		 login.addActionListener(this);
		 add(login);
		
		getContentPane().setBackground(Color.white);
		setSize(800 , 500);
		setVisible(true);
		setLocation(300,100);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==login) {
		String username=textUsername.getText();
		String password=fpassword.getText();
		String pin=fpin.getText();
		if(username.equals("") || password.equals("") || pin.equals("")) {
			JOptionPane.showInternalMessageDialog(null,"plese Enetr all Details");
		}else {
			Conn conn=new Conn();
			try {
				String query="select *from login where username = '"+username+"' and password = '"+password+"' and pinno = '"+pin+"'";
				ResultSet rs=conn.s.executeQuery(query);
				if(rs.next()) {
					setVisible(false);
					new Transactions(username).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null,"Username or Password or pin is Wrong");
				}
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		}
	}
	
	public static void main(String args[]) {
		new Login();
	}
}

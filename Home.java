package bankapp;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

    public class Home extends JFrame implements ActionListener{
     JButton login,signup; 
	Home(){
		setLayout(null);
		setTitle("AUTOMATED TELLEER MECHINE");
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("bankapp/logo.jpg"));
		Image i2=i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel label=new JLabel(i3);
		label.setBounds(70, 10, 80, 80);
		add(label);
		 
        JLabel text=new JLabel("Welcome to Jaya ATM");
		text.setBounds(200, 40, 450, 40);
		text.setFont(new Font("Osward",Font.BOLD,38));
		add(text);
		
		 login=new JButton("LOGIN");
		login.setBounds(190,170,200,60);
		login.setBackground(Color.blue);
		login.setForeground(Color.WHITE);
		login.setFont(new Font("Osward",Font.BOLD,20));
		login.addActionListener(this);
		add(login);
		
		 signup=new JButton("SIGNUP");
		signup.setBounds(400,170,200,60);
		signup.setBackground(Color.blue);
		signup.setForeground(Color.WHITE);
		signup.setFont(new Font("Osward",Font.BOLD,20));
		signup.addActionListener(this);
		add(signup);
		
		

		getContentPane().setBackground(Color.white);
		setSize(800 , 400);
		setVisible(true);
		setLocation(300,200);
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==login) {
			setVisible(false);
			new Login().setVisible(true);
		}else if(ae.getSource()==signup) {
			setVisible(false);
			new Signup1().setVisible(true);
		}
	}
	
	public static void main(String args[]) {
		new Home();
	}
}



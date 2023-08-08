package bankapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;


public class Transactions extends JFrame implements ActionListener{
	
	JButton deposit,cashWithdraw,fastCash,miniStatement,exit,checkBalance,pinChange;
	String username;
	
	public Transactions(String username){
		this.username=username;
		
		setLayout(null);
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("bankapp/atm.jpg"));
		Image i2=i1.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel image=new JLabel(i3);
		image.setBounds(0,0,700,700);
		add(image);
		
		JLabel heading1=new JLabel("USER NAME : "+username);
		heading1.setBounds(135,227,700,30);
		heading1.setFont(new Font("Areal",Font.BOLD,10));
		heading1.setForeground(Color.white);
		image.add(heading1);
		
		JLabel heading=new JLabel("Please Select Your Transaction");
		heading.setBounds(170,250,700,40);
		heading.setFont(new Font("Areal",Font.BOLD,12));
		heading.setForeground(Color.white);
		image.add(heading);
		
        deposit=new JButton("DEPOSIT");
        deposit.setBounds(130,324,120,25);
        deposit.setFont(new Font("Areal",Font.BOLD,9));
        deposit.addActionListener(this);
        image.add(deposit);
        
        cashWithdraw=new JButton("CASH WITHDRAW");
        cashWithdraw.setBounds(275,324,120,25);
        cashWithdraw.setFont(new Font("Areal",Font.BOLD,9));
        cashWithdraw.addActionListener(this);
        image.add(cashWithdraw);
        
        fastCash=new JButton("FAST CASH");
        fastCash.setBounds(130,351,120,25);
        fastCash.setFont(new Font("Areal",Font.BOLD,9));
        fastCash.addActionListener(this);
        image.add(fastCash);
        
        checkBalance=new JButton("CHECK BALANCE");
        checkBalance.setBounds(275,351,120,25);
        checkBalance.setFont(new Font("Areal",Font.BOLD,9));
        checkBalance.addActionListener(this);
        image.add(checkBalance);
        
        pinChange=new JButton("PIN CHANGE");
        pinChange.setBounds(130,378,120,25);
        pinChange.setFont(new Font("Areal",Font.BOLD,9));
        pinChange.addActionListener(this);
        image.add(pinChange);
        
        miniStatement=new JButton("MINI STATEMENT");
        miniStatement.setBounds(275,378,120,25);
        miniStatement.setFont(new Font("Areal",Font.BOLD,9));
        miniStatement.addActionListener(this);
        image.add(miniStatement);
        
        exit=new JButton("Exit");
        exit.setBounds(275,405,120,25);
        exit.setFont(new Font("Areal",Font.BOLD,9));
        exit.addActionListener(this);
        image.add(exit);
        
      
		
		
		setSize(700 , 700);
		setVisible(true);
		setLocation(250,0);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==exit) {
			System.exit(0);
		}else if(ae.getSource()==deposit) {
			setVisible(false);
			new Deposit(username).setVisible(true);
		}else if(ae.getSource()==cashWithdraw) {
			setVisible(false);
			new Withdraw(username).setVisible(true);
		}else if(ae.getSource()==fastCash) {
			setVisible(false);
			new FastCash(username).setVisible(true);
		}else if(ae.getSource()==pinChange) {
			setVisible(false);
			new PinChange(username).setVisible(true);
		}else if(ae.getSource()==miniStatement) {
			new MiniStatement(username).setVisible(true);
		}else if(ae.getSource()==checkBalance) {
			setVisible(false);
			new Balance(username).setVisible(true);
		}
	}
    
	public static void main(String[] args) {
		new Transactions("");
	}

}

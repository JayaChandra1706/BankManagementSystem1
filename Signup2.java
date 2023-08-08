package bankapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;


public class Signup2 extends JFrame implements ActionListener{
	
	JRadioButton rsavings,rcurrent,s1,s2,s3,s4;
	JButton submit;
	JTextField tpan;
	JCheckBox terms;
	String formno,name,username,password;
	
	Signup2(String formno,String name,String username,String password){
		this.formno=formno;
		this.name=name;
		this.username=username;
		this.password=password;
		setLayout(null);
		setTitle("SIGN UP");
		
		JLabel heading=new JLabel("ACCOUNT DETAILS");
		heading.setBounds(220,20,400,40);
		heading.setFont(new Font("Areal",Font.BOLD,38));
		add(heading);
		
		JLabel pan=new JLabel("PAN NUMBER");
		pan.setBounds(50,100,200,40);
		pan.setFont(new Font("Areal",Font.BOLD,20));
        add(pan);
        
        tpan=new JTextField();
    	tpan.setBounds(300,100,400,40);
    	tpan.setFont(new Font("Areal",Font.BOLD,19));
    	add(tpan);
        
        JLabel type=new JLabel("ACCOUNT TYPE");
        type.setBounds(50,150,200,40);
		type.setFont(new Font("Areal",Font.BOLD,20));
        add(type);
        
        rsavings=new JRadioButton("Savings Account");
    	rsavings.setBounds(300,150,200,40);
    	rsavings.setBackground(Color.WHITE);
    	add(rsavings);
    	
    	rcurrent=new JRadioButton("Current Account");
    	rcurrent.setBounds(550,150,200,40);
    	rcurrent.setBackground(Color.WHITE);
    	add(rcurrent);
    	
    	ButtonGroup accountGroup=new ButtonGroup();
    	accountGroup.add(rsavings);
    	accountGroup.add(rcurrent);
        
        JLabel card=new JLabel("CARD NUMBER");
        card.setBounds(50,200,200,40);
		card.setFont(new Font("Areal",Font.BOLD,20));
        add(card);
        
        JLabel ucard=new JLabel("Your card number looks like this");
        ucard.setBounds(50,220,200,40);
        add(ucard);
        
        JLabel jcard=new JLabel("XXXX-XXXX-XXXX-4352");
        jcard.setBounds(300,200,300,40);
		jcard.setFont(new Font("Areal",Font.BOLD,20));
        add(jcard);
        
        JLabel pinl=new JLabel("PIN NUMBER");
        pinl.setBounds(50,270,200,40);
		pinl.setFont(new Font("Areal",Font.BOLD,20));
        add(pinl);
        
        JLabel upinl=new JLabel("Your pin looks like this");
        upinl.setBounds(50,290,200,40);
        add(upinl);
        
        JLabel jpin=new JLabel("XXXX");
        jpin.setBounds(300,270,100,40);
		jpin.setFont(new Font("Areal",Font.BOLD,20));
        add(jpin);
        
        JLabel services=new JLabel("BANK SERVICES");
        services.setBounds(50,340,200,40);
		services.setFont(new Font("Areal",Font.BOLD,20));
        add(services);
        
        s1=new JRadioButton("Net Banking");
    	s1.setBounds(300,340,200,40);
    	s1.setBackground(Color.WHITE);
    	add(s1);
    	
    	s2=new JRadioButton("ATM Card");
    	s2.setBounds(550,340,200,40);
    	s2.setBackground(Color.WHITE);
    	add(s2);
    	
    	 s3=new JRadioButton("Check Book");
     	s3.setBounds(300,390,200,40);
     	s3.setBackground(Color.WHITE);
     	add(s3);
     	
     	s4=new JRadioButton("E-Statement");
     	s4.setBounds(550,390,200,40);
     	s4.setBackground(Color.WHITE);
     	add(s4);
     	
     	terms=new JCheckBox("Hereby I Declare that i provided correct Details and Accepting All terms and Conditions");
     	terms.setBounds(50,470,700,20);
     	terms.setBackground(Color.WHITE);
     	terms.setFont(new Font("Osward",Font.BOLD,16));
     	add(terms);
     	
     	submit =new JButton("SUBMIT");
     	submit.setBounds(600,520,150,40);
     	submit.setBackground(Color.blue);
     	submit.setForeground(Color.WHITE);
     	submit.setFont(new Font("Osward",Font.BOLD,20));
     	submit.addActionListener(this);
    	add(submit);
		
		getContentPane().setBackground(Color.white);
		setSize(800 , 700);
		setVisible(true);
		setLocation(300,0);
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==submit) {
			String panno=tpan.getText();
			String type=null;
			if(rsavings.isSelected()) {
				type="Savings Account";
			}else if(rcurrent.isSelected()) {
				type="Current Account";
			}
			Random ran=new Random();
			String cardno=""+Math.abs((ran.nextLong() % 90000000L) + 5345672300000000L);
			String pinno=""+Math.abs((ran.nextLong() % 9000L) + 1000L);
			
			String services="";
			if(s1.isSelected()) {
				services=services+" Net Banking";
			}else if(s2.isSelected()) {
				services=services+" ATM Card";
			}else if(s3.isSelected()) {
				services=services+" Check Book";
			}else if(s4.isSelected()) {
				services=services+" E-Statement";
			}
				if(panno.equals(null) || type.equals(null) || services.equals(null)) {
					JOptionPane.showMessageDialog(null,"Please fill all details");
			}else {
				try {
					Conn conn=new Conn();
					String query1="insert into signup2 values('"+formno+"','"+name+"','"+username+"','"+panno+"','"+type+"','"+cardno+"','"+pinno+"','"+services+"')";
					String query2="insert into login values('"+formno+"','"+name+"','"+username+"','"+password+"','"+cardno+"','"+pinno+"')";
					String query3="insert into balance values('"+username+"','0')";
					conn.s.executeUpdate(query1);
					conn.s.executeUpdate(query2);
					conn.s.executeUpdate(query3);
					JOptionPane.showMessageDialog(null,"Card Number : "+cardno+"\n"+"Pin No : "+pinno);
					setVisible(false);
					new Home().setVisible(true);
				}catch(Exception e) {
					System.out.println(e);
				}
			}
		}
	}
	
	public static void main(String args[]) {
		new Signup2("","","","");
	}

}

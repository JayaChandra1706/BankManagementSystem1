package bankapp;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MiniStatement extends JFrame implements ActionListener{
	
	String username;
	JLabel transactions;
	JButton print;
	
	public MiniStatement(String username) {
		this.username=username;
		setTitle("Mini Statement");
		setLayout(null);
		
		JLabel heading=new JLabel("JAYA BANK");
		heading.setBounds(150, 20, 150, 20);
		heading.setFont(new Font("Areal",Font.BOLD,20));
		add(heading);
		
		JLabel card=new JLabel();
		card.setBounds(20, 80, 300, 20);
		card.setFont(new Font("Areal",Font.BOLD,15));
		add(card);
		
		transactions=new JLabel();
		transactions.setFont(new Font("Areal",Font.BOLD,15));
		add(transactions);
		
		JLabel lbalance=new JLabel();
		lbalance.setBounds(20, 450, 300, 20);
		lbalance.setFont(new Font("Areal",Font.BOLD,15));
		add(lbalance);
		
		print=new JButton("PRINT");
		print.setBounds(180,500,100,40);
		print.addActionListener(this);
		add(print);
		
		
		try {
			Conn conn=new Conn();
            ResultSet rs=conn.s.executeQuery("select * from login where username='"+username+"'");
            while(rs.next()){
            card.setText("Card Number : "+ rs.getString("cardno").substring(0,4)+"XXXXXXXX"+rs.getString("cardno").substring(12));
            }
		}catch(Exception e) {
			System.out.println(e);
		}
		
        try {
        	Conn conn=new Conn();
			ResultSet rs=conn.s.executeQuery("select * from bank where username='"+username+"' ");
			while(rs.next()) {
				transactions.setText(transactions.getText() + "<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
        
        try {
        	Conn conn=new Conn();
        	int balance=0;
			ResultSet rs=conn.s.executeQuery("select * from balance where username='"+username+"'");
			while(rs.next()) {
				balance=Integer.parseInt(rs.getString("balance"));
			}
			lbalance.setText("Balance : "+balance);
		}catch(Exception e) {
			System.out.println(e);
		}
        
        transactions.setBounds(20, 140, 400, 200);
	
		setSize(500,600);
		setLocation(20,20);
		getContentPane().setBackground(Color.white);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==print) {
			downloadFrame();
		}
	}
	
    private void downloadFrame() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        paint(image.getGraphics());
        File outputFile = new File("downloaded_frame.png");
        try {
            ImageIO.write(image, "png", outputFile);
            System.out.println("JFrame content downloaded to: " + outputFile.getAbsolutePath());
        } catch (IOException ex) {
            System.err.println("Error saving image: " + ex.getMessage());
        }
    }


	public static void main(String[] args) {
		new MiniStatement("");
	}

}

package bankapp;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.DateChooserPanel;
import java.sql.*
;
public class Signup1 extends JFrame implements ActionListener{
	
	JTextField tname,tusername,tnumber,tmail,taddress,tstate,tpinno;
	JPasswordField fpassword;
	JDateChooser date;
	JRadioButton male,female;
	JButton next;
	
	Random ran=new Random();
	long random=Math.abs(ran.nextLong() % 9000L+1000l);
	
	Signup1(){
	setLayout(null);
	setTitle("SIGNUP");
	
	
	JLabel heading=new JLabel("APPLICATION NO: "+random);
	heading.setBounds(190,20,500,40);
	heading.setFont(new Font("Areal",Font.BOLD,38));
	add(heading);
	
	JLabel heading2=new JLabel("PERSONAL DETAILS");
	heading2.setBounds(225,70,400,40);
	heading2.setFont(new Font("Areal",Font.BOLD,35));
	add(heading2);
	
	JLabel name=new JLabel("NAME");
	name.setBounds(50,150,200,40);
	name.setFont(new Font("Areal",Font.BOLD,20));
	add(name);
	
	tname=new JTextField();
	tname.setBounds(300,150,400,40);
	tname.setFont(new Font("Areal",Font.BOLD,19));
	add(tname);
	
	JLabel username=new JLabel("USER NAME");
	username.setBounds(50,200,200,40);
	username.setFont(new Font("Areal",Font.BOLD,20));
	add(username);
	
	tusername=new JTextField();
	tusername.setBounds(300,200,400,40);
	tusername.setFont(new Font("Areal",Font.BOLD,19));
	add(tusername);
	
	JLabel gender=new JLabel("GENDER");
	gender.setBounds(50,250,200,40);
	gender.setFont(new Font("Areal",Font.BOLD,20));
	add(gender);
	
	male=new JRadioButton("Male");
	male.setBounds(300,250,100,40);
	male.setBackground(Color.WHITE);
	add(male);
	
	female=new JRadioButton("Female");
	female.setBounds(450,250,100,40);
	female.setBackground(Color.WHITE);
	add(female);
	
	ButtonGroup genderGroup=new ButtonGroup();
	genderGroup.add(male);
	genderGroup.add(female);
	
	JLabel password=new JLabel("PASS WORD");
	password.setBounds(50,300,200,40);
	password.setFont(new Font("Areal",Font.BOLD,20));
	add(password);
	
	fpassword=new JPasswordField();
	fpassword.setBounds(300, 300, 400, 40);
	fpassword.setFont(new Font("Areal",Font.BOLD,25));
	add(fpassword);
	
	JLabel dob=new JLabel("DOB");
	dob.setBounds(50,350,200,40);
	dob.setFont(new Font("Areal",Font.BOLD,20));
	add(dob);
	
	 date=new JDateChooser();
	 date.setBounds(300,350,200,40);
	 date.setFont(new Font("Areal",Font.BOLD,20));
	 date.setForeground(Color.BLACK);
	 add(date);
	
	JLabel number=new JLabel("PHONE NO");
	number.setBounds(50,400,200,40);
	number.setFont(new Font("Areal",Font.BOLD,20));
	add(number);
	
	tnumber=new JTextField();
	tnumber.setBounds(300,400,400,40);
	tnumber.setFont(new Font("Areal",Font.BOLD,19));
	add(tnumber);
	
	JLabel mail=new JLabel("MAIL");
	mail.setBounds(50,450,200,40);
	mail.setFont(new Font("Areal",Font.BOLD,20));
	add(mail);
	
	tmail=new JTextField();
	tmail.setBounds(300,450,400,40);
	tmail.setFont(new Font("Areal",Font.BOLD,19));
	add(tmail);
	
	JLabel address=new JLabel("ADDRESS");
	address.setBounds(50,500,200,40);
	address.setFont(new Font("Areal",Font.BOLD,20));
	add(address);
	
	taddress=new JTextField();
	taddress.setBounds(300,500,400,40);
	taddress.setFont(new Font("Areal",Font.BOLD,19));
	add(	taddress);
	
	JLabel state=new JLabel("STATE");
	state.setBounds(50,550,200,40);
	state.setFont(new Font("Areal",Font.BOLD,20));
	add(state);
	
	tstate=new JTextField();
	tstate.setBounds(300,550,400,40);
	tstate.setFont(new Font("Areal",Font.BOLD,19));
	add(tstate);
	
	JLabel pinno=new JLabel("PIN NO");
	pinno.setBounds(50,600,200,40);
	pinno.setFont(new Font("Areal",Font.BOLD,20));
	add(pinno);
	
	tpinno=new JTextField();
	tpinno.setBounds(300,600,400,40);
	tpinno.setFont(new Font("Areal",Font.BOLD,19));
	add(tpinno);
	
	next =new JButton("NEXT");
	next.setBounds(600,650,100,40);
	next.setBackground(Color.blue);
	 next.setForeground(Color.WHITE);
  	 next.setFont(new Font("Osward",Font.BOLD,20));
  	 next.addActionListener(this);
	add(next);
	
	getContentPane().setBackground(Color.white);
	setSize(800 , 750);
	setVisible(true);
	setLocation(300,0);
	
	}
	
	public void actionPerformed(ActionEvent ae) {
		String formno = ""+random;
		String name=tname.getText();
		String username=tusername.getText();
		String number=tnumber.getText();
		String mail=tmail.getText();
		String address=taddress.getText();
		String state=tstate.getText();
		String pinno=tpinno.getText();
		String password=fpassword.getText();
		String dob=((JTextField) date.getDateEditor().getUiComponent()).getText();
		String gender=null;
		if(male.isSelected()) {
			gender="Male";
		}else if(female.isSelected()) {
			gender="Female";
		}
		
		try {
			if(name.equals("") || username.equals("") || password.equals("") || number.equals("") || mail.equals("") || address.equals("") || state.equals("") || pinno.equals("") || gender.equals("") || dob.equals("") ) {
				JOptionPane.showMessageDialog(null, "Please Enter All the details");
			}else {
				Conn conn=new Conn();
				String query="select * from login where username='"+username+"'";
				ResultSet rs=conn.s.executeQuery(query);
				if(rs.next()) {
					JOptionPane.showMessageDialog(null, "Username Already Exist");
				}else {
				String query1="insert into signup1 values('"+formno+"','"+name+"','"+gender+"','"+dob+"','"+username+"','"+password+"','"+number+"','"+mail+"','"+address+"','"+state+"','"+pinno+"')";
				conn.s.executeUpdate(query1);
				setVisible(false);
				new Signup2(formno,name,username,password).setVisible(true);
				}
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
	

	public static void main(String[] args) {
		new Signup1();

	}

}

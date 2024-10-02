import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.EventObject;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JOptionPane;

public class LoginPage implements ActionListener {
	
	JFrame frame = new JFrame();
  JFrame signupPage;
	JButton loginButton = new JButton("Login");
	JButton signupButton = new JButton("Sign Up");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("Username:");
	JLabel userPasswordLabel = new JLabel("Password:");
	JLabel messageLabel = new JLabel("This is a test");
  JTextField customerIDField = new JTextField();
  JLabel customerIDLabel = new JLabel("Enter Customer ID: ");
  JButton customerIDButton = new JButton("Get Customer Info");

	
	
	HashMap<String,String> logininfo = new HashMap<String,String>();
	
	LoginPage(HashMap<String,String> loginInfoOriginal){
		
		logininfo = loginInfoOriginal;
		
		userIDLabel.setBounds(50,100,75,25);
		userPasswordLabel.setBounds(50,150,75,25);
		
		messageLabel.setBounds(125,250,250,35);
		messageLabel.setFont(new Font(null,Font.ITALIC,25));
		
		userIDField.setBounds(125,100,200,25);
		userPasswordField.setBounds(125,150,200,25);
		
		loginButton.setBounds(125,200,100,25);
		loginButton.addActionListener(this);
		loginButton.setFocusable(false);
		
		signupButton.setBounds(225,200,100,25);
		signupButton.addActionListener(this);
		signupButton.setFocusable(false);
		
    customerIDField.setBounds(160, 25, 45, 25);
    customerIDLabel.setBounds(25,25,150,25);

    customerIDButton.setBounds(200,25,150,25);
    customerIDButton.addActionListener(this);


		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(signupButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}
		
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==loginButton) {



        JFrame mainPage = new JFrame("Main");
        mainPage.setSize(600, 400);
        mainPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainPage.setLocationRelativeTo(null);

          customerIDField.setBounds(160, 25, 45, 25);

          customerIDLabel.setBounds(25,25,150,25);

          customerIDButton.setBounds(200,25,150,25);


            mainPage.add(customerIDField);
            mainPage.add(customerIDLabel);
            mainPage.add(customerIDButton);

            mainPage.setLayout(null);
            mainPage.setVisible(true);
      }

      // Button for inputting and displaying customer data

      if(e.getSource() == customerIDButton) {


        // Changing this part of the code change what displays on the customer info box
        String customerInfo = customerIDField.getText();
        int customerID = Integer.parseInt(customerInfo);
        database customerData = new database();
        String[] customersName = customerData.getCustomerInformation(customerID);
        JOptionPane.showMessageDialog(frame, "Customer First Name: " + customersName[0] + "\nCustomer Last Name: " + customersName[1] + "\nCustomer Phone Number: " + customersName[2] + "\nCustomer Email: " + customersName[3] + "\n");

      }
      
			if(e.getSource() == signupButton) {

       
          JFrame signupPage = new JFrame("Create Account");
          signupPage.setSize(600, 400);
          signupPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          signupPage.setLocationRelativeTo(null);

        JButton createButton = new JButton("Create Account");

        createButton.setBounds(125,200,200,25);
		    createButton.addActionListener((ActionListener) this);
		    createButton.setFocusable(false);

        signupPage.add(createButton);
	      
            signupPage.setLayout(new java.awt.FlowLayout());
            signupPage.setVisible(true);
				
			} 
			
		}
        
    }
		
		
	




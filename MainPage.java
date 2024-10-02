
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MainPage extends Frame implements ActionListener
{
    // GUI Controls ///////////////////////////////////////////////////////////////////////////////
    JButton exitButton = new JButton("Exit"); 
    JButton addUserButton = new JButton("Add Client");
	JButton addItemButton = new JButton("Add Item");
	JButton checkRoomButton = new JButton("Check Room");
	JTextField firstNameField = new JTextField("");
	JTextField lastNameField = new JTextField("");
	JTextField emailField = new JTextField("");	
	JTextField phoneField = new JTextField("");
	JButton addClientButton = new JButton("Add Client");
	JTextField roomField = new JTextField();
	JTextField itemField = new JTextField();
	JButton assignRoomButton = new JButton("Assign Room");
	JButton viewRoomButton = new JButton("View Room");
	JLabel roomLabel = new JLabel("Room:");
	JLabel itemLabel = new JLabel("Item:");
	JLabel firstNameLabel = new JLabel("First Name:");
	JLabel lastNameLabel = new JLabel("Last Name:");
	JLabel emailLabel = new JLabel("Email:");
	JLabel phoneLabel = new JLabel("Phone:");
    JTextField customerIDField = new JTextField();
  	JLabel customerIDLabel = new JLabel("Enter Customer ID: ");
  	JButton customerIDButton = new JButton("Get Customer Info");
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    // Database Object
    database db = new database();

    MainPage()
    {
        this.setLayout(null);

        ////////////// Buttons ////////////////////////////////////
        // Exit Button
        exitButton.setBounds(500, 200, 200, 25);
        exitButton.addActionListener(this);
        // Customer ID Button
        customerIDButton.setBounds(200,50,150,25);
		customerIDButton.addActionListener(this);
        // Add Client Button
        addClientButton.setBounds(150, 300, 100, 25);
        addClientButton.addActionListener(this);
        // User Button
        addUserButton.setBounds(340,50,100,25);
        addUserButton.addActionListener(this);
        // Assign Button
        assignRoomButton.setBounds(150, 150, 150, 25);
        // View Room Button
        viewRoomButton.setBounds(150, 200, 150, 25);
        // Add Item Button
        addItemButton.setBounds(150, 250, 150, 25);
        /////////////////////////////////////////////////////////////////////

        ////////////// Fields ////////////////////////////////////
        customerIDField.setBounds(160, 50, 45, 25);
        firstNameField.setBounds(150, 100, 200, 25);
        lastNameField.setBounds(150, 150, 200, 25);
        emailField.setBounds(150, 200, 200, 25);
        phoneField.setBounds(150, 250, 200, 25);
        roomField.setBounds(150, 50, 200, 25);
        itemField.setBounds(150, 100, 200, 25);

        /////////////////////////////////////////////////////////////////////

        ////////////// Labels ////////////////////////////////////
        customerIDLabel.setBounds(50,25,150,25);
        firstNameLabel.setBounds(50, 100, 100, 25);
        lastNameLabel.setBounds(50, 150, 100, 25);
        emailLabel.setBounds(50, 200, 100, 25);
        phoneLabel.setBounds(50, 250, 100, 25);
        roomLabel.setBounds(50, 50, 100, 25);
        itemLabel.setBounds(50, 100, 100, 25);

        // Add GUI items to Frame
        this.add(exitButton);
        this.add(customerIDField);
        this.add(customerIDLabel);
        this.add(customerIDButton);
        this.add(addClientButton);
        this.add(firstNameLabel);
        this.add(lastNameLabel);
        this.add(phoneLabel);
        this.add(emailLabel);
        this.add(firstNameField);
        this.add(lastNameField);
        this.add(phoneField);
        this.add(emailField);
        this.setLayout(null);
        this.setVisible(true); 
    }

    public void actionPerformed(ActionEvent e)
    {
        // Button for inputting and displaying customer data
		if(e.getSource() == customerIDButton) 
		{
			// Changing this part of the code change what displays on the customer info box
			String customerInfo = customerIDField.getText();
			int customerID = Integer.parseInt(customerInfo);
            customer theCustomer = db.getCustomerInformation(customerID);
			JOptionPane.showMessageDialog(this, "Customer First Name: " + theCustomer.firstName + "\nCustomer Last Name: " + theCustomer.lastName + "\nCustomer Phone Number: " + theCustomer.phoneNumber + "\nCustomer Email: " + theCustomer.email + "\n");
		}

        // Add Client Button
		if (e.getSource() == addClientButton) 
		{
            String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String email = emailField.getText();
			String phone = phoneField.getText();
            int newCustomerID = db.addCustomerToDatabase(firstName, lastName, phone, email);
            String msg = firstName + " " + lastName + " has been added and has been assigned Customer ID: " + newCustomerID;
			JOptionPane.showMessageDialog(this, msg);		
		}

        // Exit Button (go back to login screen)
        if (e.getSource() == exitButton) 
		{
            this.dispose();
		}

    }
    
}

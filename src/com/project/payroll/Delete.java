package com.project.payroll;



import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Delete {

	public JFrame frame;
	private JTextField textField;
	
	//This method will delete the record of the id which is passed as argument
	public void delete(int id) {
		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String user_name = "root";
		String pass = "";

		String query ="DELETE FROM `payroll` WHERE id="+id+" ";
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user_name, pass);
		Statement st = con.createStatement();
		
		int rs = st.executeUpdate(query);
		System.out.println(rs);
		//When the record gets deleted just show the dialog with following message
		JOptionPane.showMessageDialog(frame, "Record Deleted Succesfully");
		}catch(Exception e){
			//if some error happens just throw the exception
			JOptionPane.showMessageDialog(frame, "ID not present in the table");
		}
	}

	

	/**
	 * Create the application.
	 */
	public Delete() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//TextField of the id
		textField = new JTextField();
		textField.setBounds(163, 61, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		//Label - id
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(87, 61, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		
		//Label Delete Record which is on the top
		JLabel lblNewLabel_1 = new JLabel("Delete Record");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(158, 21, 105, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		//Delete Button
		JButton duttonDelete = new JButton("delete");
		duttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If the textField value if of int type it will get 
				//parse or else the catch block will get called that means the 
				//value entered in the field in not of type int so we give an exception
				try{
					int id = Integer.parseInt(textField.getText());
					//This method will delete the record hole record of that id
					delete(id);
				}catch(Exception exception){
					//This will show a dialog with the message below
					JOptionPane.showMessageDialog(frame, "ID not present in the table");
				}
			}
		});
		duttonDelete.setBounds(163, 92, 89, 23);
		frame.getContentPane().add(duttonDelete);
	}
}

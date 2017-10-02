package com.project.payroll;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Search {

	public JFrame frame;
	private JTextField textFieldName;
	private JTextField textFieldDept;
	private JTextField textFieldDesig;

	// This method will return true if the name is in the database
	public boolean checkName(String name) throws Exception {
		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String user_name = "root";
		String pass = "";
		String query = "SELECT * FROM payroll";

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user_name, pass);

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		while (rs.next()) {
			if (rs.getString(2).equalsIgnoreCase(name)) {
				// This means name is there is the data base
				st.close();
				con.close();
				return true;
			}
		}
		st.close();
		con.close();
		return false;
	}

	// This method will return true if the department is in the database
	public boolean checkDept(String dept) throws Exception {
		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String user_name = "root";
		String pass = "";
		String query = "SELECT * FROM payroll";

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user_name, pass);

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		while (rs.next()) {
			if (rs.getString(3).equalsIgnoreCase(dept)) {
				// This means department is there is the data base
				st.close();
				con.close();
				return true;
			}
		}
		st.close();
		con.close();
		return false;
	}

	// This method will return true if the designation is in the database
	public boolean checkDesig(String desig) throws Exception {
		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String user_name = "root";
		String pass = "";
		String query = "SELECT * FROM payroll";

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user_name, pass);

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		while (rs.next()) {
			if (rs.getString(5).equalsIgnoreCase(desig)) {
				// This means designation is there is the data base
				st.close();
				con.close();
				return true;
			}
		}
		st.close();
		con.close();
		return false;
	}

	// This method will bring the dialog and will print all the record
	// as per the coloumName and value which will be passed as arguments
	public void print(String coloumnName, String value) throws Exception {

		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String user_name = "root";
		String pass = "";
		String query = "SELECT * FROM `payroll` WHERE " + coloumnName + " = '" + value + "'";

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user_name, pass);

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		String data = "";
		while (rs.next()) {
			data += "ID :" + rs.getInt(1) + " Name: " + rs.getString(2) + " Department: " + rs.getString(3)
					+ " Address: " + rs.getString(4) + " Designation: " + rs.getString(5) + " Salary: " + rs.getInt(6)
					+ " start date: " + rs.getString(7) + " end date: " + rs.getString(8) + " dob: " + rs.getString(9)
					+ " gender: " + rs.getString(10) + " pf: " + rs.getInt(11) + "No of days worked"+ rs.getInt(12)+"\n";

		}
		JOptionPane.showMessageDialog(frame, data);
		st.close();
		con.close();

	}

	/**
	 * Create the application.
	 */
	public Search() {
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

		// Search label at the top
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSearch.setBounds(179, 11, 63, 14);
		frame.getContentPane().add(lblSearch);

		// Search By label
		JLabel lblSearchBy = new JLabel("Search By");
		lblSearchBy.setBounds(10, 27, 82, 14);
		frame.getContentPane().add(lblSearchBy);

		// Name
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 52, 56, 14);
		frame.getContentPane().add(lblNewLabel);

		textFieldName = new JTextField();
		textFieldName.setBounds(123, 49, 104, 20);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);

		//Department
		JLabel lblNewLabel_1 = new JLabel("Department");
		lblNewLabel_1.setBounds(10, 95, 82, 14);
		frame.getContentPane().add(lblNewLabel_1);

		textFieldDept = new JTextField();
		textFieldDept.setBounds(123, 92, 104, 20);
		frame.getContentPane().add(textFieldDept);
		textFieldDept.setColumns(10);

		//Designation
		JLabel lblNewLabel_2 = new JLabel("Designation");
		lblNewLabel_2.setBounds(10, 148, 82, 14);
		frame.getContentPane().add(lblNewLabel_2);

		textFieldDesig = new JTextField();
		textFieldDesig.setBounds(123, 145, 104, 20);
		frame.getContentPane().add(textFieldDesig);
		textFieldDesig.setColumns(10);

		//Search button for name i.e search by name
		JButton btnSearchName = new JButton("Search");
		btnSearchName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textFieldName.getText();
				try {
					// Check if the name is in the database
					//if yes then only we can print the record
					if (checkName(name)) {
						print("name", name);
					} else {
						JOptionPane.showMessageDialog(frame, "No such Name found");
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(frame, "No such Name found");
				}
			}
		});
		btnSearchName.setBounds(269, 48, 89, 23);
		frame.getContentPane().add(btnSearchName);

		//Search button for department i.e search by department name
		JButton btnSearchDept = new JButton("search");
		btnSearchDept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dept = textFieldDept.getText();
				try {
					// Check if the department name is in the database
					//if yes then only we can print the record
					if (checkDept(dept)) {
						print("department", dept);
					} else {
						JOptionPane.showMessageDialog(frame, "No such department found");
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(frame, "No such department found");
				}
			}
		});
		btnSearchDept.setBounds(269, 91, 89, 23);
		frame.getContentPane().add(btnSearchDept);

		//Search button for designation  i.e search by designation name
		JButton btnSearchDesig = new JButton("search");
		btnSearchDesig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String desig = textFieldDesig.getText();
				try {
					// Check if the designation name is in the database
					//if yes then only we can print the record
					if (checkDesig(desig)) {
						print("designation", desig);
					} else {
						JOptionPane.showMessageDialog(frame, "No such designation found");
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(frame, "No such designation found");
				}

			}
		});
		btnSearchDesig.setBounds(269, 144, 89, 23);
		frame.getContentPane().add(btnSearchDesig);
	}
}

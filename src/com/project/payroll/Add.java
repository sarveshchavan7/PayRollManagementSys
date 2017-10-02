package com.project.payroll;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import java.awt.Font;
import javax.swing.JRadioButton;

public class Add {
	public JFrame frame;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldDesig;
	private JTextField textFieldStartD;
	private JTextField textFieldEndD;
	private JTextField textFieldSalary;
	private JTextField textFieldDeparetment;
	private JTextField textFieldDob;
	String gender;
	private JTextField textNoOfDays;

	// return true if entered string is in correct format
	boolean DateFormat(String Date) {

		if (Date.substring(4, 5).equals("-") && Date.substring(7, 8).equals("-")) {
			return true;
		} else {
			JOptionPane.showMessageDialog(frame, "Date format should be - yyyy/mm/dd");
			return false;
		}

	}

	// This method will add the records in the database
	public void addRecords(int id, String name, String dept, String adds, String desig, int salary, String startDate,
		String endDate, String dob, String gender, int no_OfDays) throws Exception {
		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String user_name = "root";
		String pass = "";
		salary *= no_OfDays;
		int pf = (12 * salary) / 100;
		String queryTwo = "insert into payroll values (" + id + ",'" + name + "','" + dept + "','" + adds + "','"
				+ desig + "'," + salary + ",'" + startDate + "','" + endDate + "','" + dob + "','" + gender + "'," + pf
				+ "," + no_OfDays + ")";

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user_name, pass);
		Statement st = con.createStatement();

		int rs = st.executeUpdate(queryTwo);
		System.out.println(rs);
		st.close();
		con.close();
	}

	// If the id is already used then it will return true else false
	boolean checkID(int id) throws Exception {
		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String name = "root";
		String pass = "";
		String query = "SELECT * FROM payroll";

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, name, pass);

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		while (rs.next()) {
			if (rs.getInt(1) == id) {
				// This means id is already used
				st.close();
				con.close();
				return true;
			}
		}
		st.close();
		con.close();
		return false;
	}

	/**
	 * Create the application.
	 */
	public Add() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel myMessage = new JLabel("");
		myMessage.setBounds(108, 258, 351, 14);
		frame.getContentPane().add(myMessage);

		// Label at the top
		JLabel addRecord = new JLabel("ADD RECORD");
		addRecord.setFont(new Font("Tahoma", Font.BOLD, 14));
		addRecord.setBounds(256, 11, 154, 14);
		frame.getContentPane().add(addRecord);

		// ID
		textFieldID = new JTextField();
		textFieldID.setBounds(119, 35, 132, 20);
		frame.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(26, 38, 46, 14);
		frame.getContentPane().add(lblId);

		// Name
		textFieldName = new JTextField();
		textFieldName.setBounds(119, 80, 132, 20);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(26, 83, 46, 14);
		frame.getContentPane().add(lblName);

		// Department
		textFieldDeparetment = new JTextField();
		textFieldDeparetment.setBounds(119, 209, 132, 20);
		frame.getContentPane().add(textFieldDeparetment);
		textFieldDeparetment.setColumns(10);

		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(23, 212, 86, 14);
		frame.getContentPane().add(lblDepartment);

		// Address
		JTextArea textArea = new JTextArea();
		textArea.setBounds(118, 258, 132, 36);
		frame.getContentPane().add(textArea);

		JLabel lblAdress = new JLabel("Adress");
		lblAdress.setBounds(26, 263, 46, 14);
		frame.getContentPane().add(lblAdress);

		// Designation
		textFieldDesig = new JTextField();
		textFieldDesig.setBounds(432, 35, 86, 20);
		frame.getContentPane().add(textFieldDesig);
		textFieldDesig.setColumns(10);

		JLabel designation = new JLabel("Designation");
		designation.setBounds(346, 38, 76, 14);
		frame.getContentPane().add(designation);

		// Salary
		textFieldSalary = new JTextField();
		textFieldSalary.setBounds(432, 80, 86, 20);
		frame.getContentPane().add(textFieldSalary);
		textFieldSalary.setColumns(10);

		JLabel salary = new JLabel("Dialy Salary");
		salary.setBounds(345, 83, 77, 14);
		frame.getContentPane().add(salary);

		// Start Date
		textFieldStartD = new JFormattedTextField();
		textFieldStartD.setToolTipText("eg-2017/07/20");
		textFieldStartD.setBounds(432, 130, 86, 20);
		frame.getContentPane().add(textFieldStartD);
		textFieldStartD.setColumns(10);

		JLabel startDate = new JLabel("Start Date");
		startDate.setBounds(346, 133, 76, 14);
		frame.getContentPane().add(startDate);

		// End Date
		textFieldEndD = new JFormattedTextField();
		textFieldEndD.setToolTipText("2017/07/20");
		textFieldEndD.setBounds(432, 187, 86, 20);
		frame.getContentPane().add(textFieldEndD);
		textFieldEndD.setColumns(10);

		JLabel endDate = new JLabel("End Date");
		endDate.setBounds(346, 190, 76, 17);
		frame.getContentPane().add(endDate);

		// Gender
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(26, 175, 46, 14);
		frame.getContentPane().add(lblGender);

		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gender = "Male";
			}
		});
		rdbtnMale.setBounds(116, 171, 59, 23);
		frame.getContentPane().add(rdbtnMale);

		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "Female";
			}
		});
		rdbtnFemale.setBounds(181, 171, 86, 23);
		frame.getContentPane().add(rdbtnFemale);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnFemale);
		group.add(rdbtnMale);

		// DOB
		JLabel lblNewLabel = new JLabel("DOB");
		lblNewLabel.setBounds(26, 133, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		textFieldDob = new JTextField();
		textFieldDob.setBounds(119, 130, 132, 20);
		frame.getContentPane().add(textFieldDob);
		textFieldDob.setColumns(10);

		JLabel lblNoOfDays = new JLabel("No. of days worked");
		lblNoOfDays.setBounds(346, 243, 113, 14);
		frame.getContentPane().add(lblNoOfDays);

		textNoOfDays = new JTextField();
		textNoOfDays.setBounds(472, 240, 46, 20);
		frame.getContentPane().add(textNoOfDays);
		textNoOfDays.setColumns(10);

		// Add button
		JButton add = new JButton("ADD");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// If any of the operation in the try block fails catch is
				// called
				try {

					int id = Integer.parseInt(textFieldID.getText());
					String name = textFieldName.getText().toString();
					String dept = textFieldDeparetment.getText().toString();
					String add = textArea.getText().toString();
					int salary = Integer.parseInt(textFieldSalary.getText());
					String designation = textFieldDesig.getText().toString();
					String startDate = textFieldStartD.getText();
					String endDate = textFieldEndD.getText();
					String dob = textFieldDob.getText();
					int no_OfDays = Integer.parseInt(textNoOfDays.getText());

					// This if block will check that all the data in the field
					// are of the correct type
					if (name instanceof String && add instanceof String && dept instanceof String
							&& designation instanceof String && DateFormat(startDate) && DateFormat(endDate)
							&& DateFormat(dob)) {
						// If id is not been used then execute if block
						if (!checkID(id)) {

							addRecords(id, name, dept, add, designation, salary, startDate, endDate, dob, gender,
									no_OfDays);
							JOptionPane.showMessageDialog(frame, "Records added sucessfully");
						} else {
							JOptionPane.showMessageDialog(frame, "ID is already used");
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Some fileds are invalid");
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Some fileds are invalid");
				}

			}
		});
		add.setBounds(251, 316, 89, 23);
		frame.getContentPane().add(add);

	}
}

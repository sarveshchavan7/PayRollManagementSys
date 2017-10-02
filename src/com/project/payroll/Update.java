package com.project.payroll;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Update {

	public JFrame frame;
	private JTextField textFieldId;
	private JLabel label_Name;
	private JLabel label_Dept;
	private JLabel label_Add;
	private JLabel label_Desig;
	private JLabel label_Salary;
	private JLabel label_StartD;
	private JLabel label_EndD;
	private JButton btn_View;
	private JLabel lblDesignation;
	private JLabel lblDob;
	private JLabel lblNewLabel_2;
	private JLabel lblPf;
	private JTextField txtName;
	private JTextField txtDob;
	private JTextField txtGender;
	private JTextField txtDept;
	private JTextField txtAdd;
	private JLabel txtSalary;
	private JTextField txtDesig;
	private JTextField txtStartD;
	private JTextField txtEndD;
	JLabel pf_label;
	private JLabel lblNoOfDays;
	private JTextField textNoOfDays;

	// Return true if entered string is in correct format
	boolean DateFormat(String Date) {

		if (Date.substring(4, 5).equals("-") && Date.substring(7, 8).equals("-")) {
			return true;
		} else {
			JOptionPane.showMessageDialog(frame, "Date format should be - yyyy/mm/dd");
			return false;
		}
	}

	// If id used then it will return true
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

	// This method will update the record of the particular id
	public void updateRecords(int id, String name, String dept, String adds, String desig, int salary, String startDate,
			String endDate, String dob, String gender, int no_OfDays) throws Exception {
		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String user_name = "root";
		String pass = "";
		int pf = (12 * salary) / 100;

		String query = "UPDATE `payroll` SET name = '" + name + "' , department = '" + dept + "' , address = '" + adds
				+ "' , designation ='" + desig + "' , salary = '" + salary + "' , start_date = '" + startDate
				+ "' , end_date = '" + endDate + "' , dob = '" + dob + "' , Gender = '" + gender + "' , pf = '" + pf
				+ "' , no_ofdays='" + no_OfDays  +"' WHERE id = " + id + "";

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user_name, pass);
		Statement st = con.createStatement();

		int rs = st.executeUpdate(query);
		System.out.println(rs);
		st.close();
		con.close();
	}

	// To update the record we will get the record first in the textView
	// So this method will get the data from table and set them in there
	// respective
	// textFiled
	public void View_Details(int id) throws Exception {
		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String user_name = "root";
		String pass = "";

		String query = "SELECT * FROM `payroll` WHERE id=" + id + " ";

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user_name, pass);
		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery(query);
		rs.next();
		txtName.setText(rs.getString(2));
		txtDept.setText(rs.getString(3));
		txtAdd.setText(rs.getString(4));
		txtDesig.setText(rs.getString(5));
		txtSalary.setText(rs.getString(6));
		txtStartD.setText(rs.getString(7));
		txtEndD.setText(rs.getString(8));
		txtDob.setText(rs.getString(9));
		txtGender.setText(rs.getString(10));
		pf_label.setText(rs.getString(11));
		textNoOfDays.setText(rs.getString(12));
		st.close();
		con.close();
	}

	/**
	 * Create the application.
	 */
	public Update() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 450);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblView = new JLabel("UPDATE DETAILS");
		lblView.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblView.setBounds(193, 22, 150, 14);
		frame.getContentPane().add(lblView);

		textFieldId = new JTextField();
		textFieldId.setBounds(232, 56, 86, 20);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(176, 59, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		label_Name = new JLabel("Name");
		label_Name.setBounds(10, 168, 78, 14);
		frame.getContentPane().add(label_Name);

		label_Dept = new JLabel("Department");
		label_Dept.setBounds(10, 272, 95, 14);
		frame.getContentPane().add(label_Dept);

		label_Add = new JLabel("Address");
		label_Add.setBounds(10, 306, 95, 14);
		frame.getContentPane().add(label_Add);

		label_Desig = new JLabel("Designation");
		label_Desig.setBounds(274, 274, 78, -57);
		frame.getContentPane().add(label_Desig);

		label_Salary = new JLabel("Salary");
		label_Salary.setBounds(274, 168, 46, 14);
		frame.getContentPane().add(label_Salary);

		label_StartD = new JLabel("Start Date");
		label_StartD.setBounds(274, 272, 78, 14);
		frame.getContentPane().add(label_StartD);

		label_EndD = new JLabel("End Date");
		label_EndD.setBounds(274, 306, 78, 14);
		frame.getContentPane().add(label_EndD);

		btn_View = new JButton("View");
		btn_View.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Details(Integer.parseInt(textFieldId.getText().toString()));
				} catch (Exception exception) {
					//
					JOptionPane.showMessageDialog(frame, "Check your Id");
				}

			}
		});
		btn_View.setBounds(204, 87, 89, 23);
		frame.getContentPane().add(btn_View);

		lblDesignation = new JLabel("Designation");
		lblDesignation.setBounds(274, 239, 69, 14);
		frame.getContentPane().add(lblDesignation);

		lblDob = new JLabel("DOB");
		lblDob.setBounds(10, 203, 63, 14);
		frame.getContentPane().add(lblDob);

		lblNewLabel_2 = new JLabel("Gender");
		lblNewLabel_2.setBounds(10, 239, 63, 14);
		frame.getContentPane().add(lblNewLabel_2);

		lblPf = new JLabel("PF");
		lblPf.setBounds(272, 203, 46, 14);
		frame.getContentPane().add(lblPf);

		txtName = new JTextField();
		txtName.setBounds(92, 165, 86, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);

		txtDob = new JTextField();
		txtDob.setBounds(92, 200, 86, 20);
		frame.getContentPane().add(txtDob);
		txtDob.setColumns(10);

		txtGender = new JTextField();
		txtGender.setBounds(92, 236, 86, 20);
		frame.getContentPane().add(txtGender);
		txtGender.setColumns(10);

		txtDept = new JTextField();
		txtDept.setBounds(92, 269, 86, 20);
		frame.getContentPane().add(txtDept);
		txtDept.setColumns(10);

		txtAdd = new JTextField();
		txtAdd.setBounds(92, 303, 86, 20);
		frame.getContentPane().add(txtAdd);
		txtAdd.setColumns(10);

		txtSalary = new JLabel();
		txtSalary.setBounds(354, 165, 86, 20);
		frame.getContentPane().add(txtSalary);

		txtDesig = new JTextField();
		txtDesig.setBounds(354, 236, 86, 20);
		frame.getContentPane().add(txtDesig);
		txtDesig.setColumns(10);

		txtStartD = new JTextField();
		txtStartD.setBounds(354, 269, 86, 20);
		frame.getContentPane().add(txtStartD);
		txtStartD.setColumns(10);

		txtEndD = new JTextField();
		txtEndD.setBounds(354, 303, 86, 20);
		frame.getContentPane().add(txtEndD);
		txtEndD.setColumns(10);

		lblNoOfDays = new JLabel("No of days worked");
		lblNoOfDays.setBounds(274, 343, 95, 14);
		frame.getContentPane().add(lblNoOfDays);

		textNoOfDays = new JTextField();
		textNoOfDays.setBounds(384, 340, 57, 20);
		frame.getContentPane().add(textNoOfDays);
		textNoOfDays.setColumns(10);

		// Update Button
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// If any of the operation in the try block fails catch is
				// called
				try {

					int id = Integer.parseInt(textFieldId.getText());
					String name = txtName.getText().toString();
					String dept = txtDept.getText().toString();
					String add = txtAdd.getText().toString();
					int salary = Integer.parseInt(txtSalary.getText());
					String designation = txtDesig.getText().toString();
					String startDate = txtStartD.getText();
					String endDate = txtEndD.getText();
					String dob = txtDob.getText();
					int no_OfDays = Integer.parseInt(textNoOfDays.getText());
					// This if block will check that all the data in the field
					// are of the correct type
					if (name instanceof String && add instanceof String && dept instanceof String
							&& designation instanceof String && DateFormat(startDate) && DateFormat(endDate)
							&& DateFormat(dob)) {
						// If id is been used then execute if block
						if (checkID(id)) {

							String male = "Male";
							String female = "Female";
							String gender = txtGender.getText();
							// We will check the gender filed
							if (gender.equalsIgnoreCase(male) || gender.equalsIgnoreCase(female)) {
								// this method will update the record
								updateRecords(id, name, dept, add, designation, salary, startDate, endDate, dob, gender,
										no_OfDays);
								// After update this will show a dialog of
								// message below
								JOptionPane.showMessageDialog(frame, "Records updated sucessfully");
							}
							// If gender filed has any other value except male
							// or female execute else block
							else {
								JOptionPane.showMessageDialog(frame, "check gender");
							}

						}
						// if the id is not is table and trying to update its
						// value
						// Show the dialog below
						else {
							JOptionPane.showMessageDialog(frame, "ID is not in the tabel");
						}
					}
					// if any of the entered value in the text field are not in
					// the
					// respective format just execute the else block
					else {
						JOptionPane.showMessageDialog(frame, "Some fileds are invalid");
					}

				}
				// IF any of the operations in the try fails catch is called
				catch (Exception exception) {
					JOptionPane.showMessageDialog(frame, "Some fileds are invalid");

				}

			}
		});

		btnNewButton.setBounds(204, 360, 89, 23);
		frame.getContentPane().add(btnNewButton);

		pf_label = new JLabel("");
		pf_label.setBounds(354, 203, 86, 14);
		frame.getContentPane().add(pf_label);

	}
}

package com.project.payroll;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class View {

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
	private JLabel display_Name;
	private JLabel display_Dept;
	private JLabel display_add;
	private JLabel display_Desig;
	private JLabel display_Salary;
	private JLabel display_Start_d;
	private JLabel display_End_d;
	private JLabel lblDesignation;
	private JLabel lblDob;
	private JLabel displayDOB;
	private JLabel lblNewLabel_2;
	private JLabel displayGender;
	private JLabel lblPf;
	private JLabel displayPf;

	
	public void resetLabels(){
		display_Name.setText("");
		display_Dept.setText("");
		display_add.setText("");
		display_Desig.setText("");
		display_Salary.setText("");
		display_Start_d.setText("");
		display_End_d.setText("");
		displayDOB.setText("");
		displayGender.setText("");
		displayPf.setText("");
	}
	
	//This method will view the data of the particular id
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
		display_Name.setText(rs.getString(2));
		display_Dept.setText(rs.getString(3));
		display_add.setText(rs.getString(4));
		display_Desig.setText(rs.getString(5));
		display_Salary.setText(rs.getString(6));
		display_Start_d.setText(rs.getString(7));
		display_End_d.setText(rs.getString(8));
		displayDOB.setText(rs.getString(9));
		displayGender.setText(rs.getString(10));
		displayPf.setText(rs.getString(11));
		st.close();
		con.close();
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblView = new JLabel("View Details");
		lblView.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblView.setBounds(222, 25, 104, 14);
		frame.getContentPane().add(lblView);

		textFieldId = new JTextField();
		textFieldId.setBounds(232, 56, 86, 20);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(176, 59, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		label_Name = new JLabel("Name");
		label_Name.setBounds(27, 168, 61, 14);
		frame.getContentPane().add(label_Name);

		label_Dept = new JLabel("Department");
		label_Dept.setBounds(27, 272, 78, 14);
		frame.getContentPane().add(label_Dept);

		label_Add = new JLabel("Address");
		label_Add.setBounds(27, 306, 78, 14);
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

		
		//Button view
		btn_View = new JButton("View");
		btn_View.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					resetLabels();
					View_Details(Integer.parseInt(textFieldId.getText().toString()));
				} catch (Exception exception) {
					
					JOptionPane.showMessageDialog(frame, "Check your Id");
				}

			}
		});
		btn_View.setBounds(204, 87, 89, 23);
		frame.getContentPane().add(btn_View);

		display_Name = new JLabel("");
		display_Name.setBounds(98, 168, 147, 14);
		frame.getContentPane().add(display_Name);

		display_Dept = new JLabel("");
		display_Dept.setBounds(98, 272, 147, 14);
		frame.getContentPane().add(display_Dept);

		display_add = new JLabel("");
		display_add.setBounds(98, 303, 147, 17);
		frame.getContentPane().add(display_add);

		display_Desig = new JLabel("");
		display_Desig.setBounds(365, 239, 147, 14);
		frame.getContentPane().add(display_Desig);

		display_Salary = new JLabel("");
		display_Salary.setBounds(365, 168, 147, 14);
		frame.getContentPane().add(display_Salary);

		display_Start_d = new JLabel("");
		display_Start_d.setBounds(362, 272, 147, 14);
		frame.getContentPane().add(display_Start_d);

		display_End_d = new JLabel("");
		display_End_d.setBounds(362, 306, 147, 14);
		frame.getContentPane().add(display_End_d);

		lblDesignation = new JLabel("Designation");
		lblDesignation.setBounds(274, 239, 69, 14);
		frame.getContentPane().add(lblDesignation);

		lblDob = new JLabel("DOB");
		lblDob.setBounds(27, 203, 46, 14);
		frame.getContentPane().add(lblDob);

		displayDOB = new JLabel("");
		displayDOB.setBounds(98, 203, 100, 14);
		frame.getContentPane().add(displayDOB);

		lblNewLabel_2 = new JLabel("Gender");
		lblNewLabel_2.setBounds(27, 239, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);

		displayGender = new JLabel("");
		displayGender.setBounds(98, 239, 46, 14);
		frame.getContentPane().add(displayGender);

		lblPf = new JLabel("PF");
		lblPf.setBounds(272, 203, 46, 14);
		frame.getContentPane().add(lblPf);

		displayPf = new JLabel("");
		displayPf.setBounds(365, 203, 46, 14);
		frame.getContentPane().add(displayPf);
	}
}

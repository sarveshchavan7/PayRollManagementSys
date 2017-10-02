package com.project.payroll;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainWindow {

	
	
	//ssss
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
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

		// This is the heading at the top - label
		JLabel lblNewLabel = new JLabel("PayRoll Management System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(173, 31, 229, 14);
		frame.getContentPane().add(lblNewLabel);

		// Add - button
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// here we have called the class Add and made it visible
				new Add().frame.setVisible(true);

			}
		});
		btnNewButton.setBounds(96, 105, 89, 23);
		frame.getContentPane().add(btnNewButton);

		// Delete -  button
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// here we have called the class Delete and made it visible
				new Delete().frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(342, 105, 89, 23);
		frame.getContentPane().add(btnNewButton_1);

		// Update - button
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// here we have called class Update and made it visible
				new Update().frame.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(96, 195, 89, 23);
		frame.getContentPane().add(btnNewButton_2);

		// View - button
		JButton btnNewButton_3 = new JButton("View");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// here we have called class View and made it visible
				new View().frame.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(342, 195, 89, 23);
		frame.getContentPane().add(btnNewButton_3);

		// Search -button
		JButton btnNewButton_4 = new JButton("search");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// here we have called class Search and made it visible
				new Search().frame.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(222, 274, 89, 23);
		frame.getContentPane().add(btnNewButton_4);
	}
}

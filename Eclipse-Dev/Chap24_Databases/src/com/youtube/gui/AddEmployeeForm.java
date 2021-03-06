package com.youtube.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AddEmployeeForm extends JFrame {

	private final JPanel mainPanel;
	private final JPanel buttonPanel;
	private final JLabel firstNameLabel;
	private final JLabel lastNameLabel;
	private final JLabel emailLabel;
	private final JLabel salaryLabel;
	private final JTextField firstNameTextField;
	private final JTextField lastNameTextField;
	private final JTextField emailTextField;
	private final JTextField salaryTextField;
	private final JButton saveButton;
	private final JButton cancelButton;
	private Employee employee;
	private EmployeeDAO employeeDAO;
	private EmployeeSearchApp employeeSearchApp;
	
	public AddEmployeeForm(EmployeeSearchApp employeeSearchApp, EmployeeDAO employeeDAO) {
		this();
		this.employeeDAO = employeeDAO;
		this.employeeSearchApp = employeeSearchApp;
	}
	
	public AddEmployeeForm() {
		super("Add Employee");
		
		FlowLayout mainPanelLayout = new FlowLayout(FlowLayout.CENTER);
		mainPanel = new JPanel(mainPanelLayout);
		
		firstNameLabel = new JLabel("First Name");
		firstNameTextField = new JTextField(20);
		lastNameLabel = new JLabel("Last Name");
		lastNameTextField = new JTextField(20);
		emailLabel = new JLabel("Email");
		emailTextField = new JTextField(20);
		salaryLabel = new JLabel("Salary");
		salaryTextField = new JTextField(20);
		
		mainPanel.add(firstNameLabel);
		mainPanel.add(firstNameTextField);
		mainPanel.add(lastNameLabel);
		mainPanel.add(lastNameTextField);
		mainPanel.add(emailLabel);
		mainPanel.add(emailTextField);
		mainPanel.add(salaryLabel);
		mainPanel.add(salaryTextField);
		
		add(mainPanel, BorderLayout.CENTER);
		
		FlowLayout buttonPanelLayout = new FlowLayout(FlowLayout.RIGHT);
		buttonPanel = new JPanel(buttonPanelLayout);
		
		ButtonHandler buttonHandler = new ButtonHandler();
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		saveButton.addActionListener(buttonHandler);
		cancelButton.addActionListener(buttonHandler);
		buttonPanel.add(cancelButton);
		buttonPanel.add(saveButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 200);
	}
	
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {		
			if (e.getSource() == cancelButton) {
				dispose();
			}
			else if (e.getSource() == saveButton) {
				// get the employee info from gui
				employee = new Employee(lastNameTextField.getText(),
									    firstNameTextField.getText(),
										emailTextField.getText(),
										BigDecimal.valueOf((
												Double.valueOf(
														salaryTextField.getText()))));
				
				// save to database
				employeeDAO.addEmployee(employee);

				// close dialog
				setVisible(false);
				dispose();
				
				// refresh gui list
				employeeSearchApp.refreshEmployeeView();
				
				// show success message
				JOptionPane.showMessageDialog(employeeSearchApp, 
						"Employee added successfully.", 
						"Employee Added", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
}














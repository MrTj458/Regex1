package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import controller.Controller;

public class Panel extends JPanel
{
	private Controller baseController;
	private SpringLayout baseLayout;
	
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField phoneNumberField;
	private JTextField emailField;
	
	private JButton submitButton;
	
	public Panel(Controller baseController)
	{
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		
		firstNameField = new JTextField("First Name");
		lastNameField = new JTextField("Last Name");
		phoneNumberField = new JTextField("Phone Number");
		emailField = new JTextField("Email");
		submitButton = new JButton("Submit");
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.GRAY);
		this.add(firstNameField);
		this.add(lastNameField);
		this.add(phoneNumberField);
		this.add(emailField);
		this.add(submitButton);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.SOUTH, submitButton, -10, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, phoneNumberField, 6, SpringLayout.SOUTH, emailField);
		baseLayout.putConstraint(SpringLayout.NORTH, emailField, 6, SpringLayout.SOUTH, lastNameField);
		baseLayout.putConstraint(SpringLayout.WEST, emailField, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, phoneNumberField, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, phoneNumberField, 0, SpringLayout.EAST, firstNameField);
		baseLayout.putConstraint(SpringLayout.EAST, emailField, 0, SpringLayout.EAST, firstNameField);
		baseLayout.putConstraint(SpringLayout.NORTH, lastNameField, 6, SpringLayout.SOUTH, firstNameField);
		baseLayout.putConstraint(SpringLayout.EAST, firstNameField, 218, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, lastNameField, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, lastNameField, 0, SpringLayout.EAST, firstNameField);
		baseLayout.putConstraint(SpringLayout.NORTH, firstNameField, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, firstNameField, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, submitButton, -10, SpringLayout.EAST, this);
	}
	
	private void setupListeners()
	{
		submitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				if(checkFirstName())
				{
					JOptionPane.showMessageDialog(null, "First Name Pass");
				}
				if(checkLastName())
				{
					JOptionPane.showMessageDialog(null, "Last Name Pass");
				}
			}
		});
	}
	
	private boolean checkFirstName()
	{
		String name = firstNameField.getText();
		
		if(name.length() < 2 || name.length() > 30)
		{
			JOptionPane.showMessageDialog(this, "First name is too small or too big.");
			return false;
		}
		else
		{
			char[] chars = name.toCharArray();
			
			for(char letter : chars)
			{
				if(!Character.isLetter(letter))
				{
					JOptionPane.showMessageDialog(this, "First Name cannot contain symbols.");
					return false;
				}
			}
			
			return true;
		}
	}
	
	private boolean checkLastName()
	{
		String name = lastNameField.getText();
		
		if(name.length() < 2 || name.length() > 40)
		{
			JOptionPane.showMessageDialog(this, "Last name is too small or too big.");
			return false;
		}
		else
		{
			char[] chars = name.toCharArray();
			
			for(char letter : chars)
			{
				if(!Character.isLetter(letter))
				{
					if(letter != '-' || letter != ',' || letter != '.')
					{
						JOptionPane.showMessageDialog(this, "Last Name can only use - , . symbols");
						return false;
					}
				}
			}
			
			return true;
		}
	}
	
	private void checkPhoneNumber()
	{
		
	}
	
	private void checkEmail()
	{
		
	}
}

package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import java.util.regex.*;

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
	
	private int numberPassed;

	public Panel(Controller baseController)
	{
		this.baseController = baseController;
		baseLayout = new SpringLayout();

		firstNameField = new JTextField("First Name");
		lastNameField = new JTextField("Last Name");
		phoneNumberField = new JTextField("Phone Number");
		emailField = new JTextField("Email");
		submitButton = new JButton("Submit");
		numberPassed = 0;

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
				checkFields();
			}
		});
	}
	
	private void checkFields()
	{
		boolean passed = true;
		int failed = 0;
		
		if(!checkFirstName())
		{
			passed = false;
			failed++;
		}
		if(!checkLastName())
		{
			passed = false;
			failed++;
		}
		if(!checkEmail())
		{
			passed = false;
			failed++;
		}
		if(!checkPhoneNumber())
		{
			passed = false;
			failed++;
		}
		
		if(passed)
		{
			JOptionPane.showMessageDialog(null, "All areas passed!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, failed + " areas failed");
		}
	}

	private boolean checkFirstName()
	{
		String name = firstNameField.getText();

		if (name.length() < 2 || name.length() > 30)
		{
			JOptionPane.showMessageDialog(this, "First name is too small or too big.");
			return false;
		}
		else
		{
			char[] chars = name.toCharArray();

			for (char letter : chars)
			{
				if (!Character.isLetter(letter))
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

		if (name.length() < 2 || name.length() > 40)
		{
			JOptionPane.showMessageDialog(this, "Last name is too small or too big.");
			return false;
		}
		else
		{
			char[] chars = name.toCharArray();

			for (char letter : chars)
			{
				if (!Character.isLetter(letter))
				{
					if(letter == '-' || letter == ',' || letter == '.')
					{
					}
					else
					{
						JOptionPane.showMessageDialog(this, letter + " Last Name can only use - , . symbols");
						return false;
					}
				}
			}

			return true;
		}
	}

	private boolean checkEmail()
	{
		String email = emailField.getText();
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		if (m.matches())
		{
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(this, "email failed");
			return false;
		}
	}

	private boolean checkPhoneNumber()
	{
		if (phoneNumberField.getText().length() == 10)
		{
			try
			{
				Long.parseLong(phoneNumberField.getText());
				return true;
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(this, "Phone number must only contain numbers");
				return false;
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Phone number is not the correct length");
			return false;
		}
	}
}

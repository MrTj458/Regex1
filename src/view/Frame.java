package view;

import javax.swing.JFrame;

import controller.Controller;

public class Frame extends JFrame
{
	private Controller baseController;
	private Panel basePanel;
	
	public Frame(Controller baseController)
	{
		this.baseController = baseController;
		basePanel = new Panel(baseController);
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setTitle("Regex 1");
		this.setResizable(false);
		this.setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

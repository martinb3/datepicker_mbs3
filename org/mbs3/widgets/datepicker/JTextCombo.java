package org.mbs3.widgets.datepicker;

import java.awt.Dimension;

import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JFrame;

public class JTextCombo extends JLayeredPane {
	private JButton button;
	private JTextField textField;
	
	double partPercent = 0.8d;

	public JTextCombo ()
	{
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		textField = new JTextField();
		this.add(textField);
		textField.setText("text field");
		
		Dimension textDimension = textField.getPreferredSize();
		
		int btnWidth = (int)(textDimension.getWidth()*partPercent);
		int btnHeight = (int)textDimension.getHeight();
		Dimension buttonDimension = new Dimension(btnWidth,btnHeight);
		
		int btnX = Integer.MAX_VALUE; //this.getX() + (textDimension.width - btnWidth);
		int btnY = Integer.MAX_VALUE;
		
		button = new JButton();
		button.setPreferredSize(buttonDimension);
		this.add(button);

		layout.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.WEST, button);
		layout.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, button, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, this);
		
		addListeners();
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	
	public void addListeners()
	{
		textField.addComponentListener(new java.awt.event.ComponentListener() {
			public void componentShown(java.awt.event.ComponentEvent evt)
			{
				System.out.println(evt);
			}
			public void componentMoved(java.awt.event.ComponentEvent evt)
			{
				System.out.println(evt);
			}
			public void componentHidden(java.awt.event.ComponentEvent evt)
			{
				System.out.println(evt);
			}
			public void componentResized(java.awt.event.ComponentEvent evt)
			{
				System.out.println(evt);
			}
		});
	}
	
	
}

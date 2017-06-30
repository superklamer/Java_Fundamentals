package com.ex149_1412;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class StringManupulation extends JPanel implements ActionListener, KeyListener {
	
	private final FlowLayout flowLayout;
	private final JPanel userInputRevers;
	private final JPanel userInputSearch;
	private final JPanel stringAletrationOptions;
	private final JPanel textAreaPanel;
	private final JButton buttonReverse;
	private final JButton buttonSearch;
	private final JButton buttonClear;
	private final JCheckBox checkBoxUpperCase;
	private final JCheckBox checkBoxLowerCase;
	private final JScrollPane scroller;
	private final JLabel userLabelReverse;
	private final JLabel userLabelSearch;
	private final JTextField userTextFieldReverse;
	private final JTextField userTextFIeldSearch;
	private final JTextArea textArea;
	private DrawString strToDraw;
	private static String returnedString = "";
	

	public StringManupulation() {
		
		flowLayout = new FlowLayout();
		userInputRevers = new JPanel();
		userInputSearch = new JPanel();
		stringAletrationOptions = new JPanel();
		textAreaPanel = new JPanel();
		buttonReverse = new JButton("Reverse");
		buttonSearch = new JButton("Search");
		buttonClear = new JButton("Clear");
		checkBoxUpperCase = new JCheckBox("UppserCase", false);
		checkBoxLowerCase = new JCheckBox("LowerCase", false);
		scroller = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		userLabelReverse = new JLabel("String to reverse:");
		userLabelSearch = new JLabel("Character to search for:");
		userTextFieldReverse = new JTextField(20);
		userTextFIeldSearch = new JTextField(1);
		textArea = new JTextArea();
		strToDraw = new DrawString();
		
		userInputRevers.add(userLabelReverse, flowLayout);
		userInputRevers.add(userTextFieldReverse, flowLayout);
		userInputRevers.add(buttonReverse, flowLayout);
		buttonReverse.addActionListener(this);
		
		userInputSearch.add(userLabelSearch, flowLayout);
		userInputSearch.add(userTextFIeldSearch, flowLayout);
		userInputSearch.add(buttonSearch, flowLayout);
		buttonSearch.addActionListener(this);
		
		stringAletrationOptions.add(buttonClear, flowLayout);
		buttonClear.addActionListener(this);
		
		stringAletrationOptions.add(checkBoxLowerCase, flowLayout);
		stringAletrationOptions.add(checkBoxUpperCase, flowLayout);
		
		textAreaPanel.add(strToDraw);
		
		textArea.setVisible(true);
		textArea.setFont(new Font("Serif", Font.PLAIN, 14));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scroller.add(textArea);
		textAreaPanel.add(scroller, flowLayout);
		
		add(userInputRevers, flowLayout);
		add(userInputSearch, flowLayout);
		add(stringAletrationOptions, flowLayout);
		add(textAreaPanel, flowLayout);
		
		

	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && userTextFieldReverse.isFocusOwner())  {
			buttonReverse.doClick();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER && userTextFIeldSearch.isFocusOwner()) {
			buttonSearch.doClick();
		}
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonReverse) {
			reverseText(userTextFieldReverse.getText());
			strToDraw = new DrawString();
			strToDraw.repaint();
		}
		
	}
	
	public static String getReversedString() {
		return returnedString;
	}
	
	private void reverseText (String text) {
		// TO DO: implement this
		// receive a string of text, split it and reverese the 
		// order of the words
		//output the reversed string
		
		// SB to hold the newly generated string
		StringBuilder sb = new StringBuilder();
		
		String[] splittedText = text.split(" ");
		for (int i = splittedText.length - 1; i >= 0; i--) {
			sb.append(splittedText[i].concat(" "));
		}
		returnedString = sb.toString();
	}
	
	private String countChars(String text, char c) {
		// TO DO: Implement this
		// receive a text and a character
		// using IndexOf, find how many times 
		// a char "c" is being used in the string,
		// if any at all
		// return the number of occurances
		return null;
	}
	
	// FOR BOTH METHODS USE DRAWSTRING CLASS
}






















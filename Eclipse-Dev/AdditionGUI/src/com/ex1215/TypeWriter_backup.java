package com.ex1215;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class TypeWriter_backup extends JFrame implements KeyListener {
	
	//private final JTextArea textArea;
	private final JButton[] letterButtons = new JButton[127]; // array that will contain the number of letters from z to a
	private final Character[] chars = new Character[letterButtons.length];
	private final HashMap<String, JButton> buttonMap;
	private final JTextArea textArea;
	private final BorderLayout textAreaBorderLayout;
	private final JPanel textAreaJpanel;
	private final JTextArea upperTextArea;
	private final JPanel upperTextAreaJPanel;
	
//	private final JPanel keyboard;
	
	public TypeWriter_backup()	 {
		super("TypeWriter");
			
		upperTextAreaJPanel = new JPanel();
		upperTextArea = new JTextArea("This is a type writing app. Any text you type using the keyobord not the mouse,\n" + 
				"will be displayed in the text box below.");
		
		upperTextArea.setVisible(true);
		upperTextArea.setEditable(false);
		upperTextAreaJPanel.add(upperTextArea);
		upperTextArea.setOpaque(false);
		
		textAreaBorderLayout = new BorderLayout(10, 45);
		textAreaJpanel = new JPanel();
		textArea = new JTextArea(textAreaBorderLayout.getHgap(), textAreaBorderLayout.getVgap());
		textArea.setEditable(true);
		textArea.setVisible(true);
		textArea.addKeyListener(this);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textAreaJpanel.add(textArea, textAreaBorderLayout);
		
		buttonMap = new HashMap<String, JButton>();
		createButtons();
		
		add(upperTextArea, BorderLayout.NORTH);
		add(textAreaJpanel, BorderLayout.CENTER);

	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		JButton button = buttonMap.get(String.valueOf(e.getKeyChar()));
		if (button != null) {
			button.doClick();
		}
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			textArea.setText(textArea.getText().substring(0, textArea.getText().length() - 1));
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			for (JButton button : letterButtons) {
				if (button != null) {
					if (button.getText().charAt(0) >= 97 && button.getText().charAt(0) <= 122) {
						button.setText(String.valueOf(String.valueOf((char)(button.getText().charAt(0) - 32))));
					}					
				}
			}
			textAreaJpanel.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			for (JButton button : letterButtons) {
				if (button != null) {
					if (button.getText().charAt(0) >= 65 && button.getText().charAt(0) <= 90) {
						button.setText(String.valueOf(String.valueOf((char)(button.getText().charAt(0) + 32))));
					}					
				}
			}
			textAreaJpanel.repaint();
		}
	}
	
	private void createButtons() {
		for (int i = 0; i < 127; i++) {
			chars[i] = (char)i;
			if (i == 8) {
				letterButtons[i] = new JButton("Backspace");
				textAreaJpanel.add(letterButtons[i], BorderLayout.SOUTH);
				letterButtons[i].addKeyListener(this);
				buttonMap.put("Backspace", letterButtons[i]);
			} else if (i == 14) {
				letterButtons[i] = new JButton(String.format("%s%10s", "Shift", ""));
				textAreaJpanel.add(letterButtons[i], BorderLayout.SOUTH);
				letterButtons[i].addKeyListener(this);
				buttonMap.put("Backsp", letterButtons[i]);
			} else if (i == 32) {
				letterButtons[i] = new JButton(String.format("%10s%10s%14s", "","Space", ""));
				textAreaJpanel.add(letterButtons[i]);
			} else if (i >= 97 && i <= 122) {
				letterButtons[i] = new JButton(chars[i].toString());
				textAreaJpanel.add(letterButtons[i], BorderLayout.SOUTH);
				letterButtons[i].addKeyListener(this);
				buttonMap.put(chars[i].toString(), letterButtons[i]);
			}
		}
	}
}
















































//private final String firstRow[] = {"~","1","2","3","4","5","6","7","8","9","0","-","+","Backspace"};
//private final String secondRow[] = {"Tab","Q","W","E","R","T","Y","U","I","O","P","[","]","\\"};
//private final String thirdRow[] = {"Caps","A","S","D","F","G","H","J","K","L",":","\"","Enter"};
//private final String fourthRow[] = {"Shift","Z","X","C","V","B","N","M",",",".","?","   ^" };
//private final String fifthRow[]={"      " ,"<" ,"\\/",">" };





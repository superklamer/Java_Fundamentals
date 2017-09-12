package com.fig225;

import java.awt.Color;
import java.awt.Font;
import java.awt.Menu;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class MenuFrame extends JFrame {

	private final Color[] colorValues = 
		{Color.BLACK, Color.BLUE, Color.RED, Color.GREEN};
	private final JRadioButtonMenuItem[] colorItems; // color menu items
	private final JRadioButtonMenuItem[] fonts;	// font menu items
	private final JCheckBoxMenuItem[] styleItems;	// font style menu items
	private final JLabel displayJLabel; // display sample text
	private final ButtonGroup fontButtonGroup; // manages font menu items
	private final ButtonGroup colorButtonGroup; // manages color menu items
	private int style; // used to create style for font
	
	// no args c-tor set up GUI
	public MenuFrame() {
		super("Using JMenus");
		
		// Create file menu
		JMenu fileMenu = new JMenu("File"); 
		fileMenu.setMnemonic('F');  // set mnemonic to F
		
		// create About ... menu item
		JMenuItem aboutItem = new JMenuItem("About...");
		aboutItem.setMnemonic('A'); // set mnemonic to A
		fileMenu.add(aboutItem);	// add about item to file menu
		aboutItem.addActionListener(
			// terminate application when user clicks exitItem
			event -> JOptionPane.showMessageDialog(MenuFrame.this, 
					"This is an example\nof using menus",
					"About", JOptionPane.PLAIN_MESSAGE)	
			);
		
		JMenuBar bar = new JMenuBar(); // create menu bar
		setJMenuBar(bar);  // add menu bar to application
		bar.add(fileMenu);  // add file menu to menu bar
		
		JMenu formatMenu = new JMenu("Format");  // create format menu
		formatMenu.setMnemonic('r');  // set mnemonic to r
		
		// array listing string colors
		String[] colors = {"Black", "Blue", "Red", "Green"};
		
		JMenu colorMenu = new JMenu("Color");  // create color menu
		colorMenu.setMnemonic('C');  // set mnemonic to C
		
		// create radio button menu items for colors
		colorItems = new JRadioButtonMenuItem[colors.length];
		colorButtonGroup = new ButtonGroup(); // manage colors
		ItemHandler itemHandler = new ItemHandler();
		
		
		// create color radio button menu items
		for(int count = 0; count < colors.length; count++) {
			colorItems[count] = new JRadioButtonMenuItem(colors[count]);	// create item
			colorMenu.add(colorItems[count]);	// add item to color menu
			colorButtonGroup.add(colorItems[count]); // add to group
			colorItems[count].addActionListener(itemHandler);
		}
		
		colorItems[0].setSelected(true); // select first Color item
		formatMenu.add(colorMenu);	 // add color menu to format menu
		formatMenu.addSeparator();  // add separator in menu 
		
		// array listing font names
		String[] fontNames = {"Serif", "Monospaced", "SansSerif" };
		JMenu fontMenu = new JMenu("Font");	// create font menu
		fontMenu.setMnemonic('n');  // set mnemonic to n
		
		// create radio button menu items for font names
		fonts = new JRadioButtonMenuItem[fontNames.length];
		fontButtonGroup = new ButtonGroup();  // manages font names
		
		// create Font radio button menu items
		for (int count = 0; count < fonts.length; count++) {
			fonts[count] = new JRadioButtonMenuItem(fontNames[count]);
			fontMenu.add(fonts[count]); // add font to font menu
			fontButtonGroup.add(fonts[count]);  // add to button group
			fonts[count].addActionListener(itemHandler); // add handler
		}
		
		fonts[0].setSelected(true);  // select first font menu item
		fontMenu.addSeparator();	// add separator bar to font menu
		
		
		String[] styleNames = {"Bold", "Italic"};	// names of styles
		styleItems = new JCheckBoxMenuItem[styleNames.length];
		StyleHandler styleHandler = new StyleHandler(); // style handler
		
		// create style checkbox menu items
		for (int count = 0; count < styleNames.length; count++) {
			styleItems[count] = new JCheckBoxMenuItem(styleNames[count]);  // for style
			fontMenu.add(styleItems[count]);  // add to font menu
			styleItems[count].addItemListener(styleHandler); // handler
		}
		
		formatMenu.add(fontMenu); // add font menu to Format menu
		bar.add(formatMenu);  // add format menu to menu bar
		
		// setup label to display text
		displayJLabel = new JLabel("Sample Text", SwingConstants.CENTER);
		displayJLabel.setForeground(colorValues[0]);
		displayJLabel.setFont(new Font("Serif", Font.PLAIN, 72));
		
		getContentPane().setBackground(Color.CYAN); // set background
		add(displayJLabel, BorderLayout.CENTER);  // add displayJLabel
	} // end MenuFrame constructor
	
	// inner class to handle action events from menu items
	private class ItemHandler implements ActionListener {

		// process color and fonts selections
		@Override
		public void actionPerformed(ActionEvent event) {
			// process color selection
			for (int count = 0; count < colorItems.length;count++) {
				if (colorItems[count].isSelected()) {
					displayJLabel.setForeground(colorValues[count]);
					break;
				}
			}
			
			// process font selection
			for (int count = 0; count < fonts.length; count++) {
				if (event.getSource() == fonts[count]) {
					displayJLabel.setFont(new Font(fonts[count].getText(), style, 72));
				}
			}
			
			repaint(); // redraw application
		} 

	} // end class ItemHandler
	
	// inner class to handle item events from checkbox menu items
	private class StyleHandler implements ItemListener {
		// process font style selections
		@Override
		public void itemStateChanged(ItemEvent e) {
			String name = displayJLabel.getFont().getName(); // current font
			Font font; // new font based on user selections
			
			// determin which items are checked and crate font
			if (styleItems[0].isSelected() &&
					styleItems[1].isSelected())
				font = new Font(name, Font.BOLD + Font.ITALIC, 72);
			else if (styleItems[0].isSelected())
				font = new Font(name, Font.BOLD, 72);
			else if (styleItems[1].isSelected())
				font = new Font(name, Font.ITALIC, 72);
			else
				font = new Font(name, Font.PLAIN, 72);
			
			displayJLabel.setFont(font);
			repaint();  // redraw application
		}
	}
} // end class MenuFrame



































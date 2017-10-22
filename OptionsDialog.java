import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class UserPreferences{

	static int bombAmout = 10;
	
	static int boardHeight = 10;
	
	static int boardWidth = 10;
}

public class OptionsDialog extends JDialog implements ActionListener{
	
	JTextField bombAmountField;
	JTextField rowsField;
	JTextField columnFields;
	
	private UserPreferences userPrefernces;
	
	public OptionsDialog(final JFrame parentFrame, String title, boolean modality){
		
		super(parentFrame, title, modality);
		
		Box box = Box.createVerticalBox();
		
		userPrefernces = new UserPreferences();
		
		setSize(new Dimension(250,300));
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBackground(Color.GRAY);
		
		JButton agrementButton = new JButton("Save");
		agrementButton.addActionListener(this);
		
		JLabel minesAmountLabel = new JLabel("Please choose the number of mines");
		JLabel boardHeightLabel = new JLabel("Please choose the board height");
		JLabel boardWidthLabel = new JLabel("Please choose the board width");
		
		bombAmountField = new JTextField("8");
		rowsField = new JTextField("10");
		columnFields = new JTextField("10");
		
		box.add(Box.createVerticalStrut(20));
		box.add(minesAmountLabel);
		box.add(bombAmountField);
		
		box.add(Box.createVerticalStrut(20));
		box.add(boardHeightLabel);
		box.add(rowsField);
		
		box.add(Box.createVerticalStrut(20));
		box.add(boardWidthLabel);
		box.add(columnFields);
		
		box.add(Box.createVerticalStrut(20));
		box.add(agrementButton);
		
		optionsPanel.add(box);
		add(optionsPanel);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		userPrefernces.bombAmout = Integer.parseInt(bombAmountField.getText());
		
		userPrefernces.boardHeight = Integer.parseInt(rowsField.getText());
		
		userPrefernces.boardWidth = Integer.parseInt(columnFields.getText());
		
		this.dispose();
	}
	
	public UserPreferences getUserPreferences(){
		
		return userPrefernces;
	}
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuPanel extends JPanel implements Observable{
	
	private List<Observer> observerList;
	
	private OptionsDialog optionsDialog;
	
	private JFrame parentFrame;
	
	public MenuPanel(JFrame parentFrame){
		
		observerList = new ArrayList<Observer>();
		this.parentFrame = parentFrame;
		Box box = Box.createVerticalBox();
		optionsDialog = new OptionsDialog(parentFrame, "Options", true);
		setLayout(new GridBagLayout());
		Dimension size = getPreferredSize();
		size.width = 125;
		setPreferredSize(size);
		this.setBackground(new Color(140,100,70));
		
		JButton startGameButton = new JButton("Start Game");
		JButton scoreTableButton = new JButton("Score Table");
		JButton optionsButton = new JButton("Options");
		JButton rulesButton = new JButton("Rules");
		JButton exitButton = new JButton("Exit");
		
		startGameButton.setToolTipText("Starts the game.");
		
		scoreTableButton.setToolTipText("Not active yet.");
		
		optionsButton.setToolTipText("Allows you change the board dimensions and the mines amount.");
		
		rulesButton.setToolTipText("Not active yet.");
		
		exitButton.setToolTipText("Exits the Game.");
		
		startGameButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
			//	optionsButton.setEnabled(false);
				notifyObservers();
			}
			
		});
		
		scoreTableButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		optionsButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				optionsDialog.setVisible(true);
				
			}
			
		});
		

		rulesButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		exitButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				parentFrame.dispose();
			}
			
		});
		
		box.add(startGameButton);
		box.add(Box.createVerticalStrut(50));
		box.add(scoreTableButton);
		box.add(Box.createVerticalStrut(50));
		box.add(optionsButton);
		box.add(Box.createVerticalStrut(50));
		box.add(rulesButton);
		box.add(Box.createVerticalStrut(50));
		box.add(exitButton);
		
		add(box);
	}
	
	
	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		observerList.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		observerList.remove(observer);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(Observer observer : observerList)
			observer.notifyOfGameStart(optionsDialog.getUserPreferences());
	}

}

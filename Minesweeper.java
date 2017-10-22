import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Menu;

import javax.swing.JFrame;

public class Minesweeper {

	public static void main(String[] args) {

	/*	GamePanel gamePanel = new GamePanel(new GridLayout());
		GameInformationPanel gameInformationPanel = new GameInformationPanel(new BorderLayout());
		
		GameWindowFrame mainFrame = new GameWindowFrame(gamePanel,gameInformationPanel);
		
		MenuPanel menuPanel = new MenuPanel(mainFrame);
		
		menuPanel.addObserver(mainFrame);
		
		mainFrame.add(menuPanel,BorderLayout.WEST);
		mainFrame.setVisible(true);*/
		
		Controller controller = new Controller();
		controller.start();
	
		
	}

}

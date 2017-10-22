import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindowFrame extends JFrame implements Observer{

//	private GamePanel gamePanel;
//	private GameInformationPanel gameInformationPanel;
	
	public GameWindowFrame(){//GamePanel gamePanel, GameInformationPanel gameInformationPanel){
		
		//this.gamePanel = gamePanel;
		//this.gameInformationPanel = gameInformationPanel;
		
		this.setSize(800,640);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void addPanel(JPanel panel){
		
		String borderLayout = null;
		
		if(panel instanceof GamePanel)
				
			borderLayout = BorderLayout.CENTER;
		
		else if( panel instanceof MenuPanel)

			borderLayout = BorderLayout.WEST;
		
		else if( panel instanceof GameInformationPanel)
			
			borderLayout = BorderLayout.PAGE_START;
			
		add(panel, borderLayout);
		revalidate();
		repaint();
		
	}
	
	@Override
	public void notifyOfGameStart(Object dimensions) {
		
		/*if(dimensions instanceof UserPreferences){
			
			gamePanel.setDimentions(((UserPreferences) dimensions).boardHeight,((UserPreferences) dimensions).boardWidth);
			gamePanel.initializeMineField();
			add(gamePanel,BorderLayout.CENTER);
			
			gameInformationPanel.initializeFlagLabel(((UserPreferences) dimensions).bombAmout);
			add(gameInformationPanel,BorderLayout.PAGE_START);
			
			revalidate();
			repaint();
		}
		repaint();*/
	}

	@Override
	public void notifyOfUserSelection(Object userSelection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyOfTilesToReveal(Object tilesToReveal) {
		// TODO Auto-generated method stub
		
	}
	

}

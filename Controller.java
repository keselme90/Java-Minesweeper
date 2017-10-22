import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Controller implements Observer{

	private GameWindowFrame gameWindowFrame;
	
	private GamePanel gamePanel;

	private MenuPanel menuPanel;
	
	private GameInformationPanel gameInformationPanel;
	
	private GameLogic gameLogic;

	public Controller(){
		
		gameWindowFrame = new GameWindowFrame();
		
		menuPanel = new MenuPanel(gameWindowFrame);
		gamePanel = new GamePanel(new GridLayout());
		gameInformationPanel = new GameInformationPanel(new BorderLayout());
		
		gameLogic = new GameLogic();
		
		gamePanel.addObserver(gameLogic);
		gameLogic.addObserver(gamePanel);
		gamePanel.addObserver(gameInformationPanel);
		
		menuPanel.addObserver(this);

	}
	
	public void start(){
		
		gameWindowFrame.addPanel(menuPanel);
		
	}
	
	public void notifyOfGameStart(Object dimensions) {
		System.out.println("I am here");
		// TODO Auto-generated method stub
		if(dimensions instanceof UserPreferences){
			
			/*gameWindowFrame.remove(menuPanel);
			gameWindowFrame.revalidate();
			gameWindowFrame.repaint();*/
			
			gamePanel.initializeMineField(((UserPreferences) dimensions).boardHeight, ((UserPreferences) dimensions).boardHeight);
			gameInformationPanel.initializeFlagLabel(((UserPreferences) dimensions).bombAmout);
			
			gameWindowFrame.addPanel(gamePanel);
			gameWindowFrame.addPanel(gameInformationPanel);
			
			gameLogic.initializeGameBoard(((UserPreferences) dimensions).boardHeight, ((UserPreferences) dimensions).boardWidth, ((UserPreferences) dimensions).bombAmout);
		}
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

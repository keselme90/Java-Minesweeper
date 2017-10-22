import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class UserClick{
	
	public int tileRowIndex;
	
	public int tileColumnIndex;
	
	public int clickType;
	
	public UserClick(){
		
		this.tileRowIndex = 0;
		this.tileColumnIndex = 0;
		this.clickType = 0;
	}
	
}

public class GamePanel extends JPanel implements Observer, Observable, MouseListener{

	private Button[][] mineField;
	
	private Dimension mineFieldDimensions;
	
	private UserClick userClick;
	
	GridLayout gridLayout;
	
	List<Observer> observerList;
	
	private ImageIcon flagIcon;
	private ImageIcon bombIcon;

	private Set<Tile> revealedTilesList;
	
	public GamePanel(GridLayout gridLayout){
		
		super(gridLayout);
		
		this.gridLayout = gridLayout;
		
		setBackground(Color.ORANGE);
		
		observerList = new ArrayList<Observer>();
		
		userClick = new UserClick();
		
		flagIcon = new ImageIcon("images//flag.png");
		
		bombIcon = new ImageIcon("images//bomb.png");
	}
		
	public void initializeMineField(final int hieght, final int width){
		
		if(mineFieldDimensions == null){
					
					mineFieldDimensions = new Dimension(hieght, width);
					
					gridLayout.setRows(hieght);
					
					gridLayout.setColumns(width);
					
				}
		
		mineField = new Button[(int) mineFieldDimensions.getHeight()][(int) mineFieldDimensions.getWidth()];
		
		int buttonCounter = 0;
		for(int i = 0; i < (int) mineFieldDimensions.getHeight(); i++){
			
			for(int j = 0; j < (int) mineFieldDimensions.getWidth(); j++){
			
				mineField[i][j] = new Button();
				mineField[i][j].setName(Integer.toString(buttonCounter++));
				mineField[i][j].setBackground(Color.GRAY);
				mineField[i][j].setPreferredSize(new Dimension(10,10));//Magic Numbers TODO
				mineField[i][j].addMouseListener(this);
				this.add(mineField[i][j]);
			}
		}
	}
	
	@Override
	public void notifyOfGameStart(Object dimensions) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyOfUserSelection(Object userSelection) {
		// TODO Auto-generated method stub
		
	}

	private Color getColor(Tile tile){
		
		switch(tile.getNumberOfMinedNeighbors()){
		case 1:
			return Color.BLUE;
		case 2:
			return Color.GREEN;
		case 3:
			return Color.RED;
		case 4: 
			return Color.MAGENTA;
		case 5: 
			return Color.ORANGE;
		case 6:
			return Color.PINK;
		case 7:
			return Color.WHITE;
		default:
				return Color.YELLOW;
		}
	}
	
	public void notifyOfTilesToReveal(Object tilesToReveal) {
		// TODO Auto-generated method stub
		if( tilesToReveal instanceof Set<?>){
			
			Set<Tile> tilesToPresent = new HashSet<Tile>();
			tilesToPresent = (Set<Tile>)tilesToReveal;
			
			
			for(Tile tile : tilesToPresent){
				
				
				int row = tile.getRowIndex();
				int column = tile.getColumnIndex();
				
					
					mineField[row][column].isButtonClickedToReveal = true;
					mineField[row][column].setBackground(Color.LIGHT_GRAY);
						
					if(tile.getNumberOfMinedNeighbors() > 0){
						mineField[row][column].setForeground(getColor(tile));
						mineField[row][column].setText(Integer.toString(tile.getNumberOfMinedNeighbors()));
					}
					else if(tile.isMined()){
						
						mineField[row][column].setForeground(Color.RED);
						mineField[row][column].setIcon(bombIcon);
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Button clickedButton = (Button)e.getSource();
		
		int tileIndex = Integer.parseInt(clickedButton.getName());
		int row = tileIndex / mineFieldDimensions.height;
		int column = tileIndex % mineFieldDimensions.height;
		
		userClick.tileRowIndex = row;
		userClick.tileColumnIndex = column;
		
		if(e.getButton() == MouseEvent.BUTTON1){
			
			userClick.clickType = 1;
			
			if(!clickedButton.isButtonClickedToReveal && !clickedButton.isButtonClickedToFlag){
				mineField[row][column].isButtonClickedToReveal = true;
				notifyObservers();
			}
			
		}
		
		else if(e.getButton() == MouseEvent.BUTTON3){
			
			if(!clickedButton.isButtonClickedToReveal){
				
				if(!clickedButton.isButtonClickedToFlag){
					
					userClick.clickType = 2;
					
					mineField[row][column].isButtonClickedToFlag = true;
					
					mineField[row][column].setIcon(flagIcon);

				}
				else if(clickedButton.isButtonClickedToFlag){
					
					userClick.clickType = 3;
					
					mineField[row][column].isButtonClickedToFlag = false;
					
					mineField[row][column].setIcon(null);
				}
				notifyObservers();
			}
		}	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		observerList.add(observer);
	}

	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		observerList.remove(observer);
	}
	

	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(Observer observer : observerList)
			observer.notifyOfUserSelection(userClick);
	}

}

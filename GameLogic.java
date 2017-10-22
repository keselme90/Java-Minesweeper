import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Message{
	
	int victory;
	
	String message;
	
	Set<Tile> minedTiles;
	
	Message(int victory, String message, Set<Tile> minedTiles){
		
		this.victory = victory;
		
		this.message = message;
		
		this.minedTiles = minedTiles;
	}
}

public class GameLogic implements Observer, Observable {
	
	private Gameboard gameBoard;
	
	private Tile clickedButton;
	
	private List<Observer> observerList;
	
	private int boardHight;
	
	private int boardWidth;
 
	public GameLogic(){
		
		observerList = new ArrayList<Observer>();
		
	}

	private void revealTile(Set<Tile> visited, Tile tile){
		
		tile.revealTile();
		visited.add(tile);
	}
	
	private void getNeighbors(Tile tile, Set<Tile> visited, Queue<Tile> neighbors){
		
		for(int i = tile.getRowIndex() - 1; i <= tile.getRowIndex() + 1; i++){
			
			for(int j = tile.getColumnIndex() - 1; j <= tile.getColumnIndex() + 1; j++){
				
				if( i < 0 || i > boardHight - 1 || j < 0 || j > boardWidth -1 || (i == tile.getRowIndex() && j == tile.getColumnIndex()) || gameBoard.getTile(i, j).getFlagStatus())
					
					continue;
				
				else{
					
					if(gameBoard.checkTile(i, j) == 0){
						Tile tmp = gameBoard.getTile(i, j);
						if(!tmp.isRevealed()){
							tmp.revealTile();
							neighbors.add(tmp);
						}
					}
					else if(gameBoard.checkTile(i, j) > 0){
						Tile tmp = gameBoard.getTile(i, j);
						if(!tmp.isRevealed())
							revealTile(visited, tmp);
					}
				}
			}
		}
	}
		
	private void makeMove(){
		
		Queue<Tile> neighborsQueue = new LinkedList<Tile>();
		Set<Tile> visitedNeighbors = new HashSet<Tile>();
		
		if(clickedButton.isMined()){
			
			revealTile(visitedNeighbors, clickedButton);
			
			//Message gameOverMessage = new Message(1,"You Lost!\nGame Over!\nPress Yes to start over!", gameBoard.getMineLocations());
			
			for(Observer observer : observerList){
				observer.notifyOfTilesToReveal(visitedNeighbors);
				//observer.gameStats(gameOverMessage);
				System.out.println("Game Over! You Lost!");
			}
			return;
		}
		
		neighborsQueue.add(clickedButton);
		while(!neighborsQueue.isEmpty()){
			
			Tile temp = neighborsQueue.poll();
			if(gameBoard.checkTile(temp.getRowIndex(), temp.getColumnIndex()) == 0){
				temp.revealTile();
				visitedNeighbors.add(temp);
				getNeighbors(temp, visitedNeighbors, neighborsQueue);
			}
			else
				
				revealTile(visitedNeighbors, temp);
		}
		
		gameBoard.updateNumberOfFreeTiles(visitedNeighbors.size());
		for(Observer observer : observerList){
			observer.notifyOfTilesToReveal(visitedNeighbors);
			if(gameBoard.getNumberOfFreeTiles() == 0){
				/*Message gameOverMessage = new Message(2,"You Won!\nGame Over!\nPress Yes to start over!",gameBoard.getMineLocations());
				observer.gameStats(gameOverMessage);*/
				System.out.println("Game Over! You Won!");
			}
		}
	}	
	
	@Override
	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		observerList.remove(observer);
	}

	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		observerList.add(observer);
	}

	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}

	public void initializeGameBoard(final int boardHight, final int boardWidth, final int numberOfBombs){
		
		this.boardHight = boardHight;
		
		this.boardWidth = boardWidth;
		
		gameBoard = new Gameboard(boardHight, boardWidth, numberOfBombs);
		gameBoard.printBoard();
		
	}
	
	@Override
	public void notifyOfGameStart(Object dimensions) {
		// TODO Auto-generated method stub
	}

	public void notifyOfUserSelection(Object userSelection) {
		// TODO Auto-generated method stub
		if(userSelection instanceof UserClick){
			
			UserClick userClick = (UserClick)userSelection;
			
			clickedButton = gameBoard.getTile(userClick.tileRowIndex, userClick.tileColumnIndex);
			
			if(userClick.clickType == 1){
				
				if(!clickedButton.isRevealed())
					
					makeMove();
			}
			
			else if(userClick.clickType == 2){
				
				if(clickedButton.getFlagStatus())
					
					clickedButton.setFlagDown();
				
				else
					
					clickedButton.setFlagUp();
			}
		}
		
	}

	@Override
	public void notifyOfTilesToReveal(Object tilesToReveal) {
		// TODO Auto-generated method stub
		
	}

}

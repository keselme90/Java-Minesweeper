import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Location{
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	public Location(final int row, final int column){
		
		this.row = row;
		
		this.column = column;
	}
	
    int row;
	
	int column;
}

public class Gameboard {

	private Tile[][] gameBoard;
	
	private int boardHight;
	
	private int boardWidth;
	
	private int numberOfMines;
	
	private int numberOfFreeTiles;
	
	private Set<Tile> mineLocations;
	
	public Gameboard(final int boardHight, final int boardWidth, final int numberOfMines){
		
		this.boardHight = boardHight;
		this.boardWidth = boardWidth;
		this.numberOfMines = numberOfMines;
		
		this.gameBoard = new Tile[this.boardHight][this.boardWidth];
		
		this.mineLocations = new HashSet<Tile>();
		
		for(int i = 0; i < this.boardHight; i ++ ){
			
			for(int j = 0; j < this.boardWidth; j ++){
				
				this.gameBoard[i][j] = new Tile(i,j);
			}
		}
		
		initializeBoard();
		
	}
	
	public void initializeBoard(){
		
		numberOfFreeTiles = boardHight*boardWidth - numberOfMines;
		
		mineLocations.clear();
		
		Random random = new Random();
		Set<Location> mineLocations = new HashSet<Location>();
		
		while(mineLocations.size() < numberOfMines){
			
			int temp = random.nextInt(boardHight*boardWidth);
			
			Location location = new Location(temp/boardHight,temp%boardWidth);
			
			gameBoard[location.row][location.column].setTileToBeMined();
			
			mineLocations.add(location);
		}
		
		for(int i = 0; i < boardHight; i ++){
			
			for(int j = 0; j < boardWidth; j ++){
				
				if(!gameBoard[i][j].isMined())
					
					gameBoard[i][j].setNumberOfMinedNeighbors(countMinedNeighbors(i,j));
				
				else 
					
					this.mineLocations.add(gameBoard[i][j]);
				
				gameBoard[i][j].hideTile();
			}
		}
	}
	
	public void clearBoard(){
		
		for(int i = 0 ; i < boardHight; i ++){
			
			for( int j = 0; j < boardWidth; j++)
				
				gameBoard[i][j].nullifyTile();
		}
	}
	
	private int countMinedNeighbors(final int i, final int j){
			
		int counter = 0;
		
		for(int row = i - 1; row <= i + 1; row ++){
			
			for(int column = j - 1; column <= j + 1; column ++){
				
				
				if(row < 0 || row >= boardHight || column < 0 || column >= boardWidth || (row == i && column == j))
					
					continue;
				
				else if(gameBoard[row][column].isMined()) counter ++;
			}
		}
		
		return counter;
	}

	public void updateNumberOfFreeTiles(final int numberOfFreeTiles){
		
		this.numberOfFreeTiles -= numberOfFreeTiles;
	}

	public final int getNumberOfFreeTiles(){
		
		return this.numberOfFreeTiles;
	}
	
	/**
	 * Function returns reference to a specific tile from the mine board. 
	 * @param i is the row index.
	 * @param j is the column index.
	 * @return reference to a tile from the mine board.
	 */
	public Tile getTile(final int i, final int j){ 
		
		return this.gameBoard[i][j];
	}
	
	/***
	 * Function checks if the title contains a mine or at least of the tile's neighbors contains a mine.
	 * @param i is the row index.
	 * @param j is the column index.
	 * @return -1 if the tiles contain mine, 0 if none of the tile's neighbors contain a mine, 1 if at least one the neighbors contains a mine.
	 */
	public int checkTile(final int i, final int j){
		
		if(this.gameBoard[i][j].isMined())
			return -1;
		
		else if(this.gameBoard[i][j].getNumberOfMinedNeighbors() == 0)
			
			return 0;
		
		return 1;
		
	}
	
	/***
	 * Function prints the mine filed to terminal.
	 */
	public void printBoard(){
		
		System.out.println("-------------------------------");
		for(int i = 0; i < boardHight; i ++){
			
			for(int j = 0; j < boardWidth; j++){
				
				System.out.print("| ");
				gameBoard[i][j].print();
			}
			System.out.println();
		}
		System.out.println("-------------------------------");
	}

	public Set<Tile> getMineLocations(){
		
		return this.mineLocations;
	}
}

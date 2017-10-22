/***
 * Class that represents a tile on the mine field.
 * @author Edward
 *
 */
public class Tile {

	private int numberOfMinedNeighbors;
	
	private int rowIndex;
	
	private int columnIndex;
	
	private boolean isMined;
	
	private boolean isFlaged;
	
	private boolean isRevealed;
	
	/**
	 * Tile constructor.
	 * 
	 * @param rowIndex
	 * @param columnIndex
	 */
	public Tile(final int rowIndex, final int columnIndex){
		
		this.numberOfMinedNeighbors = 0;
		
		this.isRevealed = false;
		
		this.isFlaged = false;
		
		this.rowIndex = rowIndex;
		
		this.columnIndex = columnIndex;
	}
	
	/***
	 * Sets the number of surrounding neighbors with a mine.
	 * @param numberOfMinedNeighbors - an integer that holds the number of tiles with a mine.
	 */
	public void setNumberOfMinedNeighbors(final int numberOfMinedNeighbors){
		
		this.numberOfMinedNeighbors = numberOfMinedNeighbors;
		
	}
	
	/**
	 * Returns the number of tiles with a mine, that surround the current tile.
	 * @return numberOfMinedNeighbors - an integer that holds the number of tiles with a mine.
	 */
	public final int getNumberOfMinedNeighbors(){
		
		return numberOfMinedNeighbors;
	}
	
	/***
	 * Sets the value of the row index.
	 * @param rowIndex - an integer that holds the value of the row index.
	 */
	public void setRowIndex(final int rowIndex){
		
		this.rowIndex = rowIndex;
	}
	
	/**
	 * Returns the row index of the current tile.
	 * @return rowIndex - an integer that holds the row integer of the current tile.
	 */
	public final int getRowIndex(){
		
		return rowIndex;
	}
	
	/***
	 * Sets the column index the current tile.
	 * @param columnIndex - an integer that holds the index of the column.
	 */
	public void setColumnIndex(final int columnIndex){
		
		this.columnIndex = columnIndex;
	}
	
	/***
	 * Returns the column of this mine.
	 * @return columnIndex - an integer value that holds the column of the current tile.
	 */
	public final int getColumnIndex(){
		
		return columnIndex;
	}
	
	/***
	 * Sets the state of the current tile to have a mine, by changing the status of isMined to true.
	 */
	public void setTileToBeMined(){
		
		isMined = true;
	}
	
	/***
	 * Returns whether the tile has a mine or not.
	 * @return isMined - a boolean value that indicates if this tile has a mine or not.
	 */
	public final boolean isMined(){
		
		return isMined;
	}
	
	/**
	 * Returns whether the tile is flagged as a suspicious tile or not.
	 * @return isFlaged - a boolean value that indicates flag status.
	 */
	public final boolean getFlagStatus(){
		
		return isFlaged;
	}

	/**
	 * Makes the tile flagged as a suspicious tile to contain a mine. Changes the value of isFlaged to true.
	 */
	public void setFlagUp(){
		
		isFlaged = true;
	}
	
	/**
	 * Makes the tile unflagged. Changes the value of isFlaged to false.
	 */
	public void setFlagDown(){
		
		isFlaged = false;
	}
	
	/***
	 * Makes the tile revealed. Changes the value of isRevealed to true
	 */
	public void revealTile(){
		
		isRevealed = true;
	}
	
	/**
	 * Makes the tile hidden. Changes the value of isRevealed to false.
	 */
	public void hideTile(){
		
		isRevealed = false;
	}
	
	/***
	 * Returns whether the tile was already revealed or not.
	 * @return isRevealed - a boolean value to indicate if the tile was revealed or not.
	 */
	public final boolean isRevealed(){
		
		return isRevealed;
	}

	/***
	 * Resets the tile to the initial values.
	 */
	public void nullifyTile(){
		
		numberOfMinedNeighbors = 0;
		isMined = false;
		isRevealed = false;
		isFlaged = false;
	}
	
	/**
	 * Prints the content of the tile. If the tile is mined print B for bomb, otherwise print number of neighbors with a mine.
	 */
 	public void print(){
		
		if(this.isMined)
			System.out.print(" " + "B");
		else
			System.out.print(" " +numberOfMinedNeighbors);
	}

 	/***
 	 * Returns the locations of the tile on the board in a string format '(row, column)'
 	 * 
 	 */
 	public String toString(){
 		
 		return ("(" + rowIndex + " , " + columnIndex + ")");
 	}
}

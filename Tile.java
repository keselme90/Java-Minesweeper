
public class Tile {

	private int numberOfMinedNeighbors;
	
	private int rowIndex;
	
	private int columnIndex;
	
	private boolean isMined;
	
	private boolean isFlaged;
	
	private boolean isRevealed;
	
	public Tile(final int rowIndex, final int columnIndex){
		
		this.numberOfMinedNeighbors = 0;
		
		this.isRevealed = false;
		
		this.isFlaged = false;
		
		this.rowIndex = rowIndex;
		
		this.columnIndex = columnIndex;
	}
	
	public void setNumberOfMinedNeighbors(final int numberOfMinedNeighbors){
		
		this.numberOfMinedNeighbors = numberOfMinedNeighbors;
		
	}
	
	public final int getNumberOfMinedNeighbors(){
		
		return this.numberOfMinedNeighbors;
	}
	
	public void setRowIndex(final int rowIndex){
		
		this.rowIndex = rowIndex;
	}
	
	public final int getRowIndex(){
		
		return this.rowIndex;
	}
	
	public void setColumnIndex(final int columnIndex){
		
		this.columnIndex = columnIndex;
	}
	
	public final int getColumnIndex(){
		
		return this.columnIndex;
	}
	
	public void setTileToBeMined(){
		
		this.isMined = true;
	}
	
	public final boolean isMined(){
		
		return this.isMined;
	}
	
	public final boolean getFlagStatus(){
		
		return isFlaged;
	}

	public void setFlagUp(){
		
		this.isFlaged = true;
	}
	
	public void setFlagDown(){
		
		this.isFlaged = false;
	}
	
	public void revealTile(){
		
		this.isRevealed = true;
	}
	
	public void hideTile(){
		
		this.isRevealed = false;
	}
	
	public final boolean isRevealed(){
		
		return this.isRevealed;
	}

	public void nullifyTile(){
		
		this.numberOfMinedNeighbors = 0;
		this.isMined = false;
		this.isRevealed = false;
		this.isFlaged = false;
	}
	
 	public void print(){
		
		if(this.isMined)
			System.out.print(" " + "B");
		else
			System.out.print(" " +numberOfMinedNeighbors);
	}

 	public String toString(){
 		
 		return ("(" + rowIndex + " , " + columnIndex + ")");
 	}
}

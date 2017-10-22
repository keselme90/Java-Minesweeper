
public interface Observer {

	public void notifyOfGameStart(Object dimensions);
	
	public void notifyOfUserSelection(Object userSelection);
	
	public void notifyOfTilesToReveal(Object tilesToReveal);
	
}

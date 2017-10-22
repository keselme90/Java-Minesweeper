import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameInformationPanel extends JPanel implements Observer{

	private JLabel timeCounter = new JLabel("Time passed: 0");
	private JLabel bombCounter = new JLabel();
	
	
	public GameInformationPanel(BorderLayout borderLayout){
		 
		super(borderLayout);
		this.setBackground(new Color(153,153,0));
		Dimension size = getPreferredSize();
		size.height = 30;
		setPreferredSize(size);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		timeCounter.setForeground(new Color(128,0,0));
		timeCounter.setFont(new Font("Monospaced", Font.BOLD, 32));
		bombCounter.setForeground(new Color(51,102,0));
		bombCounter.setFont(new Font("Monospaced", Font.BOLD, 32));
		this.add(timeCounter);
		this.add(Box.createGlue());
		this.add(bombCounter);
	}
	
	public void initializeFlagLabel(final int initialFlagsNumber){
		
		bombCounter.setText("Flags available: " + initialFlagsNumber);
	}
	
	public void updateTime(final int time){
		
		this.timeCounter.setText("Time passed: " + Integer.toString(time));
	}
	
	public void updateFlagCounter(final int mouseClick){
		
		String numStr = bombCounter.getText().substring(bombCounter.getText().indexOf(": ")+2);
		int tempValue = Integer.parseInt(numStr);
		
		if(mouseClick == 2)
			
			tempValue--;
			
		else if(mouseClick == 3)
			
			tempValue++;
		
		bombCounter.setText("Flags available: " + Integer.toString(tempValue));
	}
	
	@Override
	public void notifyOfGameStart(Object dimensions) {
		// TODO Auto-generated method stub
	}

	@Override
	public void notifyOfUserSelection(Object userSelection) {
		// TODO Auto-generated method stub
		if( userSelection instanceof UserClick)
			updateFlagCounter(((UserClick) userSelection).clickType);
	}

	@Override
	public void notifyOfTilesToReveal(Object tilesToReveal) {
		// TODO Auto-generated method stub
		
	}

}

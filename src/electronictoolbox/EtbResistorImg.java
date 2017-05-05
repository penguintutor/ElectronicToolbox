// Creates a 4 band resistor image
package electronictoolbox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class EtbResistorImg extends JPanel {

	private static final long serialVersionUID = 3586899645278866484L;
	
	// bandStartX is the starting x pos for each band - numbered from 0 0 and double spacing to band 4
	final static int[] bandStartX = new int[] {10,30,50,80}; 
	final static int RESISTOR_X = 70;
	final static int RESISTOR_Y = 30;
	final static int BAND_WIDTH = 10;
	
	// Store state of bands and then update during paintComponent (and use repaint as required)
	int bandColors[];
	
	public EtbResistorImg() {
		// set bands to -5 initially (beige / invisible)
		bandColors = new int[]{-5,-5,-5,-5};
	}
	

	public void paintComponent(Graphics g) {
		this.setPreferredSize(new Dimension(300, 100));
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(224,224,224));			// Beige
		g.fillRoundRect(RESISTOR_X, RESISTOR_Y, 160, 40, 15, 15);
		
		/*
		 * band pos = 1 to 3 (resistor value) and 4 = tolerance
		 * Value should be 0 to 9 (black to white) then -1 = gold, -2 = silver
		 */
		for (int i=0; i < bandColors.length; i++) {
			g.setColor(valueToColor(bandColors[i]));
			g.fillRect(RESISTOR_X + bandStartX[i], RESISTOR_Y, BAND_WIDTH, 40);
		}

	}
	
	public void updateBands (int[]bandValues) {
		// If too many bands then return (should not occur) 
		// May want to raise exception in future?
		if (bandValues.length > bandStartX.length) return;
		for (int i = 0; i < bandValues.length; i ++) {
			bandColors[i]=bandValues[i];
		}
		// If no 4th digit (tolerance) then use default 5% = gold
		if (bandValues.length < 4) {
			bandColors[3] = -1;
		}
		
		// Once updated saved details update display
		repaint();
	}
	
	
	
	public Color valueToColor (int value) {
		switch (value){
		case 0: return (Color.black);
		case 1: return (new Color(153,76,0));		// Brown
		case 2: return (Color.red);
		case 3: return (new Color(204,102,0));		// Orange (standard orange is too close to gold)
		case 4: return (Color.yellow);
		case 5: return (Color.green);
		case 6: return (Color.blue);
		case 7: return (new Color(127,0,255));		// Violet
		case 8: return (Color.gray);
		case 9: return (Color.white);
		case -1: return (new Color(255,215,0));		// Gold
		case -2: return (new Color(192,192,192));	// Silver
		// invalid return beige colour (not shown)
		default: return (new Color(224,224,224));
		}
	}
	
}

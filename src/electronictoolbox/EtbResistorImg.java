package electronictoolbox;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class EtbResistorImg extends JPanel {

	private static final long serialVersionUID = 3586899645278866484L;

	public void paintComponent(Graphics g) {
		g.setColor(Color.orange);
	    g.fillRect(0, 0, getWidth(), getHeight());
	    g.setColor(Color.red);
	    g.fillOval(getWidth()/4, getHeight()/4, 
			getWidth()/2, getHeight()/2);
	}
	
}

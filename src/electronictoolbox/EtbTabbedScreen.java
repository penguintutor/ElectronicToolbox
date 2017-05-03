/*
 * Copyright (C) 2016 - 2017 Stewart Watkiss
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package electronictoolbox;

import javax.swing.*;

/**
 *
 * @author stewart
 */
public class EtbTabbedScreen extends JTabbedPane {
    
	private static final long serialVersionUID = 4811007989710025723L;
	JPanel resistorPanel;
    
    EtbTabbedScreen() {
    	
    	resistorPanel = new EtbResistor();
    	JScrollPane scrollResistor = new JScrollPane(resistorPanel);

        this.addTab("Resistor Calculator", scrollResistor);
        this.addTab("Test2", new JLabel("Test2"));
    }
    
}

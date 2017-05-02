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

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author stewart
 */
public class EtbMenuBar extends JMenuBar {
    
	private static final long serialVersionUID = -362672125284752286L;
	JMenu jmFile;
    JMenuItem jmiExit;
    JMenu jmHelp;
    JMenuItem jmiAbout;
    
    
    EtbMenuBar() {
        // File Menu
        jmFile = new JMenu("File");
        jmiExit = new JMenuItem("Exit");
        jmFile.add(jmiExit);
        this.add(jmFile);
        
        // Help Menu
        jmHelp = new JMenu("Help");
        jmiAbout = new JMenuItem("About");
        jmHelp.add(jmiAbout);
        this.add(jmHelp);
        
        
    }
    public void addActionListenerToMenu(ActionListener listener) {
        jmiExit.addActionListener(listener);
        jmiAbout.addActionListener(listener);
    }
    
    
}

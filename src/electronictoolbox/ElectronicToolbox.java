/*
 * Copyright (C) 2016 Stewart Watkiss
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
public class ElectronicToolbox {

    ElectronicToolbox() {
        // Create JFrame with border layout
        JFrame jfrm = new JFrame("Electronic Toolbox");
        // initial size
        jfrm.setSize(800, 600);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add Menu
        EtbMenuBar menu = new EtbMenuBar();
        jfrm.setJMenuBar(menu);
        
        // Add tabbed screen
        EtbTabbedScreen tabs = new EtbTabbedScreen();
        jfrm.add(tabs);
        jfrm.setVisible(true);
        
        // Add about dialog
        EtbAbout about = new EtbAbout(jfrm);
        
        /** 
         * ActionListener for GUI items
        */
        menu.addActionListenerToMenu((ActionEvent ae) -> {
            String comStr = ae.getActionCommand();
            
            // Exit
            if(comStr.equals("Exit")) System.exit(0);
            if(comStr.equals("About")) {about.setVisible(true);}
        });
        
        about.addActionListenerToAbout((ActionEvent ae) -> {
            String comStr = ae.getActionCommand();
            
            // Close dialog (make it not visible)
            if(comStr.equals("OK")) {about.setVisible(false);}
        });
    
        
    }
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create frame on event dispatching thread
        // Uses lambda function ->
        SwingUtilities.invokeLater(() -> {
            new ElectronicToolbox();
        });
        
        
        
    }
    
}

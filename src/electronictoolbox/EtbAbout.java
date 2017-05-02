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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 *
 * @author stewart
 */
//public class EtbAbout extends JDialog implements ActionListener {
public class EtbAbout extends JDialog {
    
	private static final long serialVersionUID = 1822819975035997666L;
	JButton jbtnOK;
    ImageIcon imgLogo;
    JLabel jlabLogo;
    JLabel jlabAboutText;
    
    
    EtbAbout (JFrame parentFrame) {
        setLayout(new BorderLayout());
        this.setSize(300,450);
        this.setResizable(false);
        this.setTitle("About Electronic Toolbox");
        
        // use of getResource to load a file from the same folder as the class files
        imgLogo = new ImageIcon(this.getClass().getResource("EtbPLogo.png"));
        jlabLogo = new JLabel(imgLogo);
        
        this.add(jlabLogo, BorderLayout.NORTH);
        
        String aboutText = "<h2>Electronic Toolbox</h2>" +
                "<p>" +
                "Created by Stewart Watkiss<br>" + 
                "<a href=\"http://www.penguintutor.com\">www.penguintutor.com</a><br>" +
                "Version 0.0.1<br>" +
                "Copyright 2016 Stewart Watkiss<br>" +
                "This software is distributed under the terms of the GNU General Public License V3" +
                "</p>";
        
        JEditorPane jhtmlAbout = new JEditorPane();
        jhtmlAbout.setContentType( "text/html" );
        jhtmlAbout.setText(aboutText);
        this.add(jhtmlAbout, BorderLayout.CENTER);
        
        // Add button in own panel to respect the size of the button
        JPanel jpOK = new JPanel ();
        jpOK.setLayout(new FlowLayout());
        
                
        // Add OK button
        jbtnOK = new JButton("OK");
        jbtnOK.setPreferredSize(new Dimension(80, 30));
        
        jpOK.add(jbtnOK);
        
        this.add(jpOK, BorderLayout.SOUTH);
    }
    
    public void addActionListenerToAbout(ActionListener listener) {
        jbtnOK.addActionListener(listener);
    }
    
    
}

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
import java.text.DecimalFormat;
import java.util.LinkedList;

import javax.swing.*;

/**
 *
 * @author stewart
 */
public class EtbResistor extends JPanel {
    
	private static final long serialVersionUID = -761550344508481533L;

	// Define those that need to be updated as class variables
    JTextField jtxtVolts;
    JTextField jtxtAmps;
    JLabel jlabResistorVal;
    JLabel jlabPowerVal;
    JLabel jlabSelectResisValue;
    
    // These are values to read only (ie checkboxes)
    JComboBox<String> jcombPref;
    JCheckBox jckbxE3;
    JCheckBox jckbxE6;
    JCheckBox jckbxE12;
    JCheckBox jckbxE24;
    JCheckBox jckbxE48;
    JCheckBox jckbxE96;
    
    EtbResistorImg resistorImg;
    
    // Resistance is recalculated whenever it needs to be updated
    // but is saved so that it can be read by preferred resistor methods
    double resistance;
    
    /* These static variables are used to define the GUI positions based on gridbaglayout 
     * This allows chnaging the positions here rather than on each component */
    /* Positions left to right / top to bottom*/
    /* COL = x coords */
    final static int COL_LEFT = 0;						// Title + text
    final static int COL_LABEL_0 = 0;					// initial form fields (eg. Voltage:)
    final static int COL_FORM_0 = 1;					// initial form entry field (eg. textfield)
    final static int COL_FORM_0A = 2;					// split in half to allow ohm of nearest to be closer
    final static int COL_POST_0 = 3;					// post character (eg. V)
    final static int COL_PAD_0 = 4;						// Padding between left and right
    final static int COL_LABEL_1 = 5;					// form fields (eg. Resistance:)
    final static int COL_FORM_1 = 6;					// form entry field (eg. textfield)
    final static int COL_POST_1 = 7;					// post character (eg. ohms)

    
    /* ROW = y coords */
    final static int ROW_TITLE = 0;						// Title
    final static int ROW_IMG_0 = 1;						// Image of resistor
    final static int ROW_TEXT_0 = 2;					// First title
    final static int ROW_FORM_0 = 3;					// First form (eg. voltage)
    final static int ROW_FORM_1 = 4;					// Second form (eg. current)
    final static int ROW_SUBMIT_0 = 5;					// Second form (eg. current)
    final static int ROW_TEXT_1 = 6;					// First title
    final static int ROW_FORM_2 = 7;					// Resistor pref
    final static int ROW_FORM_3 = 8;					// Series checkboxes
    final static int ROW_FORM_4 = 9;					// Nearest resistor
    

    
    EtbResistor() {
    	
    	this.setLayout(new GridBagLayout());
    	
    	/* GridBagLayout constraints */
    	GridBagConstraints c = new GridBagConstraints();
    	c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 10, 10, 10);
    	
    	
        JLabel jlabResTitle = new JLabel("Resistor Calculator Tool", SwingConstants.CENTER);
        jlabResTitle.setFont(jlabResTitle.getFont().deriveFont(18.0f));
        c.gridx = COL_LEFT;
        c.gridy = ROW_TITLE; 
        c.gridwidth = 11;
        c.fill=GridBagConstraints.BOTH;
        c.anchor=GridBagConstraints.NORTH;
        this.add(jlabResTitle,c);
        
        c.fill=GridBagConstraints.NONE;
        
        
        /* Insert image of resistor */
        resistorImg = new EtbResistorImg();
        c.gridx = COL_LEFT;
        c.gridy = ROW_IMG_0;
        c.gridwidth = 11;
        this.add(resistorImg,c);
        
        
        c.anchor=GridBagConstraints.WEST;

        JLabel jlabInstr = new JLabel("Add details of voltage and current for details of nearest resistor size.");
        // Make this into a font we can apply to other labels
        Font fntBody = jlabInstr.getFont().deriveFont(13.0f);
        jlabInstr.setFont(fntBody);
        c.gridx = COL_LEFT;
        c.gridy = ROW_TEXT_0;
        c.gridwidth = 11;
        this.add(jlabInstr, c);

        
        /* Voltage and Current input */
        JLabel jlabVoltage = new JLabel("Voltage : ");
        jlabVoltage.setFont(fntBody);
        c.gridx = COL_LABEL_0;
        c.gridy = ROW_FORM_0;
        c.gridwidth = 1;
        this.add(jlabVoltage,c);
        
        
        jtxtVolts = new JTextField(5);
        jtxtVolts.setFont(fntBody);
        c.gridx = COL_FORM_0;
        c.gridy = ROW_FORM_0;
        c.gridwidth = 2;
        this.add(jtxtVolts,c);
        
        JLabel jlabVolts = new JLabel(" Volts");
        jlabVolts.setFont(fntBody);
        c.gridx = COL_POST_0;
        c.gridy = ROW_FORM_0;
        c.gridwidth = 1;
        this.add(jlabVolts,c);
        
        
        JLabel jlabCurrent = new JLabel("Current : ");
        jlabCurrent.setFont(fntBody);
        c.gridx = COL_LABEL_0;
        c.gridy = ROW_FORM_1;
        c.gridwidth = 1;
        this.add(jlabCurrent,c);
        
        jtxtAmps = new JTextField(5);
        jtxtAmps.setFont(fntBody);
        c.gridx = COL_FORM_0;
        c.gridy = ROW_FORM_1;
        c.gridwidth = 2;
        this.add(jtxtAmps,c);
        
        JLabel jlabAmps = new JLabel(" Amps");
        jlabAmps.setFont(fntBody);
        c.gridx = COL_POST_0;
        c.gridy = ROW_FORM_1;
        c.gridwidth = 1;
        this.add(jlabAmps,c);

        /* Add padding between left and right */
        JLabel jlabPad = new JLabel("       ");
        jlabPad.setFont(fntBody);
        c.gridx = COL_PAD_0;
        c.gridy = ROW_FORM_1;
        c.gridwidth = 1;
        this.add(jlabPad,c);
        
        
        /* Resistance and power - right hand side */
        /* Set to 0 initially refresh and update exact values later */
       
        JLabel jlabResistance = new JLabel ("Resistance : ");
        jlabResistance.setFont(fntBody);
        c.gridx = COL_LABEL_1;
        c.gridy = ROW_FORM_0;
        c.gridwidth = 1;
        this.add(jlabResistance,c);
        
        jlabResistorVal = new JLabel ("0", SwingConstants.RIGHT);
        jlabResistorVal.setFont(fntBody);
        c.gridx = COL_FORM_1;
        c.gridy = ROW_FORM_0;
        c.gridwidth = 1;
        this.add(jlabResistorVal,c);
        
        JLabel jlabOhm = new JLabel ("\u2126");
        jlabOhm.setFont(fntBody);
        c.gridx = COL_POST_1;
        c.gridy = ROW_FORM_0;
        c.gridwidth = 1;
        this.add(jlabOhm,c);
        
        
        JLabel jlabPower = new JLabel ("Power : ");
        jlabPower.setFont(fntBody);
        c.gridx = COL_LABEL_1;
        c.gridy = ROW_FORM_1;
        c.gridwidth = 1;
        this.add(jlabPower,c);
        
        jlabPowerVal = new JLabel ("0", SwingConstants.RIGHT);
        jlabPowerVal.setFont(fntBody);
        c.gridx = COL_FORM_1;
        c.gridy = ROW_FORM_1;
        c.gridwidth = 1;
        this.add(jlabPowerVal,c);
        
        JLabel jlabWatts = new JLabel ("W");
        jlabWatts.setFont(fntBody);
        c.gridx = COL_POST_1;
        c.gridy = ROW_FORM_1;
        c.gridwidth = 1;
        this.add(jlabWatts,c);
        
        
        /* Refresh button */
        JButton jbtnRefresh = new JButton("Refresh");
        jbtnRefresh.addActionListener((ActionEvent ae) -> {
            updateResistance();
        });
        jbtnRefresh.setFont(fntBody);
        c.gridx = COL_LABEL_1;		// Line up against left of right fields
        c.gridy = ROW_SUBMIT_0;
        c.gridwidth = 3;
        this.add(jbtnRefresh,c);
        

        JLabel jlabSeriesInstr = new JLabel("Select resistor series and whether minimum, maximum or nearest");
        jlabInstr.setFont(fntBody);
        c.gridx = COL_LEFT;
        c.gridy = ROW_TEXT_1;
        c.gridwidth = 11;
        this.add(jlabSeriesInstr,c);
        
      
        JLabel jlabSeries = new JLabel("Preference : ");
        jlabSeries.setFont(fntBody);
        c.gridx = COL_LABEL_0;
        c.gridy = ROW_FORM_2;
        c.gridwidth = 1;
        this.add(jlabSeries,c);
        

        jcombPref = new JComboBox<String>();
        jcombPref.setFont(fntBody);
        c.gridx = COL_FORM_0;
        c.gridy = ROW_FORM_2;
        c.gridwidth = 3;
        jcombPref.addItem("Nearest");
        jcombPref.addItem("Lower value");
        jcombPref.addItem("Higher value");
        this.add(jcombPref,c);
        

        JLabel jlabSeriesLab = new JLabel("Resistor Series : ");
        jlabSeriesLab.setFont(fntBody);
        c.gridx = COL_LABEL_1;
        c.gridy = ROW_FORM_2;
        c.gridwidth = 1;
        this.add(jlabSeriesLab,c);
 
        /* Check boxes */
        // uses it's own Jpanel to maintain consistant spacing
        JPanel jpnlSeries = new JPanel();
        c.gridx = COL_LABEL_1;
        c.gridy = ROW_FORM_3;
        c.gridwidth = 4;
        this.add(jpnlSeries,c);
        
        jckbxE3 = new JCheckBox("E3");
        jckbxE3.setSelected(true);
        jckbxE3.setFont(fntBody);
        jpnlSeries.add(jckbxE3);
                
        jckbxE6 = new JCheckBox("E6");
        jckbxE6.setSelected(true);
        jckbxE6.setFont(fntBody);
        jpnlSeries.add(jckbxE6);
        
        jckbxE12 = new JCheckBox("E12");
        jckbxE12.setSelected(true);
        jckbxE12.setFont(fntBody);
        jpnlSeries.add(jckbxE12);
        
        jckbxE24 = new JCheckBox("E24");
        jckbxE24.setSelected(true);
        jckbxE24.setFont(fntBody);
        jpnlSeries.add(jckbxE24);

        // by default only E12 and E24 selected (most common)
        jckbxE48 = new JCheckBox("E48");
        jckbxE48.setSelected(false);
        jckbxE48.setFont(fntBody);
        jpnlSeries.add(jckbxE48);
        
        // by default only E12 and E24 selected (most common)
        jckbxE96 = new JCheckBox("E96");
        jckbxE96.setSelected(false);
        jckbxE96.setFont(fntBody);
        jpnlSeries.add(jckbxE96);
        
        /* Add action listeners for prefs*/
        jcombPref.addActionListener(aclstPrefResistor);
        jckbxE3.addActionListener(aclstPrefResistor);
        jckbxE6.addActionListener(aclstPrefResistor);
        jckbxE12.addActionListener(aclstPrefResistor);
        jckbxE24.addActionListener(aclstPrefResistor);
        jckbxE48.addActionListener(aclstPrefResistor);
        jckbxE96.addActionListener(aclstPrefResistor);
        
        
        JLabel jlabSelectResisLabel = new JLabel("Nearest resistor");
        jlabSelectResisLabel.setFont(fntBody);
        c.gridx = COL_LABEL_0;
        c.gridy = ROW_FORM_4;
        c.gridwidth = 1;
        this.add(jlabSelectResisLabel,c);
        
        jlabSelectResisValue = new JLabel("0");
        jlabSelectResisValue.setFont(fntBody);
        c.gridx = COL_FORM_0;
        c.gridy = ROW_FORM_4;
        c.gridwidth = 1;
        this.add(jlabSelectResisValue,c);

        JLabel jlabOhm2 = new JLabel ("\u2126");
        jlabOhm2.setFont(fntBody);
        c.gridx = COL_FORM_0A;
        c.gridy = ROW_FORM_4;
        c.gridwidth = 1;
        this.add(jlabOhm2,c);
    }
    
    
    public void updateResistance() {
        // update to false if there is a problem and then don't try to calc value
        boolean allValid = true;
        
        // uses NumberWithSI to handle units - will also update text field
        NumberWithSI voltage = new NumberWithSI(this.jtxtVolts.getText());
        // Test this is valid
        if (voltage.isValid()) {
            // Update voltage text field
            this.jtxtVolts.setText(voltage.formattedString());
        }
        else {
            this.jtxtVolts.setText("Invalid value");
            allValid = false;
        }
        
        // uses NumberWithSI to handle units - will also update text field
        NumberWithSI amps = new NumberWithSI(this.jtxtAmps.getText());
        // Test this is valid
        if (amps.isValid()) {
            // Update voltage text field
            this.jtxtAmps.setText(amps.formattedString());
        }
        else {
            this.jtxtAmps.setText("Invalid value");
            allValid = false;
        }
        
        // As well as checking for valid - also check that we won't have a divide by zero
        // Alternatively we could have let it raise an exception and handle that.
        if (allValid && amps.getValue() > 0) {
            
            resistance = voltage.getValue() / amps.getValue();
            double power = voltage.getValue() * amps.getValue();
            // check for sensible resistance (ie. not less than 0) 
            // convert resistance to a scientific notation with 1 decimal place (if 1 digit in higher - else no decimal places)
            
            jlabResistorVal.setText(formatResistance(resistance));
            
            // Do same for power
            jlabPowerVal.setText(formatSI(power));
            

        }
        else {
            jlabResistorVal.setText("Invalid input");
            jlabPowerVal.setText("Invalid input");
        }
        
        updateSelectedResistor();

        
    }   
      
    
    // Only works with suitable values for resistor (ie. no milliohms)
    // Assumes we've already checked that it's a number - but check number is in range
    private String formatResistance(double actualValue) {
        String approxValue;
        Character charSI = '\u0000';
        // First check it's a valid resistance - set highest = 999MΩ 
        // technically possible to have higher, but not in normal electronics use
        if (actualValue > 999000000) return "Too high";
        // Should not get < 0 as would be interpreted as invalid anyway
        if (actualValue < 0) return "Not allowed";
        // Now check for different values and apply SI unit
        if (actualValue >= 1000000) {
            charSI = 'M';
            actualValue /= 1000000;
        }
        else if (actualValue >= 1000) {
            charSI = 'k';
            actualValue /= 1000;
        }
        // Now determine whether we keep a single decimal place (if 1 digit) or none
        // If less than 1 then need to add 0 in front
        if (actualValue < 1) {
            DecimalFormat df1 = new DecimalFormat(".#");
            approxValue = "0" + df1.format(actualValue);
        }
        // Uses DecimalFormat which performs rounding up / down as required
        else if (actualValue < 10) {
            
            DecimalFormat df1 = new DecimalFormat(".#");
            approxValue = df1.format(actualValue);
        }
        else {
            long longValue = Math.round(actualValue);
            approxValue = Long.toString(longValue);
        }
        // return with SI if relevant
        if (charSI != '\u0000') {
            return (approxValue + ' ' + charSI);
        }
        // Still put a space if no char to pad to Ω
        return approxValue + ' ';
    }
    
    
    // Supports more SI units
    // Assumes we've already checked that it's a number - but check number is in range
    private String formatSI(double actualValue) {
        String approxValue;
        Character charSI = '\u0000';
        // First check it's a valid set highest = 999M 
        // technically possible to have higher, but not in normal electronics use
        if (actualValue > 999000000) return "Too high";
        // Should not get < 0 as would be interpreted as invalid anyway
        if (actualValue < 0) return "Not allowed";
        // Now check for different values and apply SI unit
        if (actualValue >= 1000000) {
            charSI = 'M';
            actualValue /= 1000000;
        }
        else if (actualValue >= 1000) {
            charSI = 'k';
            actualValue /= 1000;
        }
        // allows milli watts
        else if (actualValue <= 1) {
            charSI = 'm';
            actualValue *= 1000;
        }
        // Now determine whether we keep a single decimal place (if 1 digit) or none
        // If less than 1 then need to add 0 in front
        if (actualValue < 1) {
            DecimalFormat df1 = new DecimalFormat(".#");
            approxValue = "0" + df1.format(actualValue);
        }
        // Uses DecimalFormat which performs rounding up / down as required
        else if (actualValue < 10) {
            
            DecimalFormat df1 = new DecimalFormat(".#");
            approxValue = df1.format(actualValue);
        }
        else {
            long longValue = Math.round(actualValue);
            approxValue = Long.toString(longValue);
        }
        // return with SI if relevant
        if (charSI != '\u0000') {
            return (approxValue + ' ' + charSI);
        }
        // Still put a space if no char to pad 
        return approxValue + ' ';
    }
    
    // Convert resistance value to band values (digit1, digit2, multiplier)
    // Does not support fractional resistances (eg. 1.3 ohm, would be considered as 1 ohm)
    // needs values as a long rather than a double (ie. for nearest resistor value)
    public int[] resistanceToBands (long resistance) {
    	System.out.println("Resistance " + String.valueOf(resistance));
    	int[] bandValues = new int[3];
    	// resistance invalid
    	if (resistance < 1) return (new int[]{-5,-5,-5});

    	// Convert the number into a array of digits by pushing 1 digit at time onto stack
    	LinkedList<Integer> stack = new LinkedList<Integer>();
    	while (resistance > 0) {
    	    stack.push((int)(resistance % 10));
    	    System.out.println("Adding to stack " + (int)(resistance % 10));
    	    resistance = resistance / 10;
    	}
    	
    	// If we have less than 2 digits then have 10^-1 multiplier  = gold
    	if (stack.size() == 1) {
    		bandValues[0]=stack.pop();
    		bandValues[1]=0;
    		bandValues[2]=-1;
    	}
    	// Otherwise return the last two digits on the stack and the size of the stack is the multiplier
    	else {
    		bandValues[2] = stack.size()-2;
    		bandValues[0] = stack.pop();
    		bandValues[1] = stack.pop();
    		
    	}
    	
    	
    	System.out.println("Values " + String.valueOf(bandValues[0]) + " " + String.valueOf(bandValues[1]) + " " + String.valueOf(bandValues[2]));
    	return bandValues;
    }
    
    
    /* Updates the nearest resistor value */
    void updateSelectedResistor() {
        
        jlabSelectResisValue.setText(formatResistance(resistance));
        
        
        resistorImg.updateBands(resistanceToBands((long)resistance));
        
    }
    
    // ActionListener used by combobox and checkboxes
    ActionListener aclstPrefResistor = (ActionEvent actionEvent) -> {
        updateSelectedResistor();
    };
    
}

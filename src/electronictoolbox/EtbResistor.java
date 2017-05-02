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
    
    // Resistance is recalculated whenever it needs to be updated
    // but is saved so that it can be read by preferred resistor methods
    double resistance;
    
    /* These static variables are used to define the GUI positions based on fix layout */
    /* Positions left to right / top to bottom*/
    /* COL = x coords */
    final static int COL_POS_XCENTRE0 = 300;			// Title
    final static int COL_POS_X0 = 20;					// Text / instructions
    final static int COL_POS_X1 = 40;					// First Label
    final static int COL_POS_X2 = 130;					// First txt field
    final static int COL_POS_X3 = 200;					// post field Label
    final static int COL_POS_X3A = 240;					// (intermediate - eg. last omega)
    final static int COL_POS_X4 = 400;					// Second Label
    final static int COL_POS_X5 = 500;					// Second Value 
    final static int COL_POS_X6 = 540;					// Second Label post (eg. omega - assuming X5 non-edit)
    final static int COL_POS_X7 = 460;					// 2nd checkbox (note these are shifted back compared with X5 and X6)
    final static int COL_POS_X8 = 520;					// 3rd checkbox
    final static int COL_POS_X9 = 580;					// 4th checkbox
    final static int COL_POS_X10 = 640;					// 5th checkbox
    final static int COL_POS_X11 = 700;					// 6th checkbox
    
    /* ROW = y coords */
    final static int ROW_POS_Y0 = 20;					// Title
    final static int ROW_POS_Y1 = 60;					// Image
    final static int ROW_POS_Y2 = 160;					// Text
    final static int ROW_POS_Y3 = 210;					// First entry fields eg. voltage	
    final static int ROW_POS_Y4 = 250;					// Second entry fields eg. current	
    final static int ROW_POS_Y5 = 290;					// Refresh button	
    final static int ROW_POS_Y6 = 360;					// Second text
    final static int ROW_POS_Y7 = 400;					// Resistor pref
    final static int ROW_POS_Y8 = 440;					// Series checkboxes
    final static int ROW_POS_Y9 = 480;					// Nearest resistor
    
    
    // Some common fields have fixed sizes
    final static int SIZE_CBOX_X = 50;					// Checkbox
    final static int SIZE_STDLAB_X = 80;				// Eg. Voltage label
    final static int SIZE_STDLAB_X1 = 90;				// Slightly larger eg. resistance
    final static int SIZE_STDLAB_X2 = 130;				// Larger still eg. Resistor series
    final static int SIZE_SMLENTRY_X = 60;				// Eg. volts
    final static int SIZE_STDENTRY_X = 130;				// Eg. pull down - preference
    final static int SIZE_SMLLAB_X = 30;				// Eg. short value non edit eg. Resistance
    final static int SIZE_BTN_X = 120;
    final static int SIZE_STD_Y = 30;					// Most fields incl labels and text fields
    final static int SIZE_BTN_Y = 45;					// standard button size

    
    EtbResistor() {
    	
    	this.setLayout(null);
        //this.setBackground(Color.white);
    	
        JLabel jlabResTitle = new JLabel("Resistor Calculator Tool");
        jlabResTitle.setFont(jlabResTitle.getFont().deriveFont(18.0f));
        jlabResTitle.setBounds(COL_POS_XCENTRE0,ROW_POS_Y0,300,35);
        this.add(jlabResTitle);
        
        /* Insert image of resistor */
        

        JLabel jlabInstr = new JLabel("Add details of voltage and current for details of nearest resistor size.");
        // Make this into a font we can apply to other labels
        Font fntBody = jlabInstr.getFont().deriveFont(13.0f);
        jlabInstr.setFont(fntBody);
        jlabInstr.setBounds(COL_POS_X0,ROW_POS_Y2,600,25);
        this.add(jlabInstr);

        
        /* Voltage and Current input */
        JLabel jlabVoltage = new JLabel("Voltage : ");
        jlabVoltage.setFont(fntBody);
        jlabVoltage.setBounds(COL_POS_X1, ROW_POS_Y3, SIZE_STDLAB_X, SIZE_STD_Y);
        this.add(jlabVoltage);
        
        
        jtxtVolts = new JTextField(10);
        jtxtVolts.setFont(fntBody);
        jtxtVolts.setBounds(COL_POS_X2, ROW_POS_Y3, SIZE_SMLENTRY_X, SIZE_STD_Y);
        this.add(jtxtVolts);
        
        JLabel jlabVolts = new JLabel(" Volts");
        jlabVolts.setFont(fntBody);
        jlabVolts.setBounds(COL_POS_X3, ROW_POS_Y3, SIZE_STDLAB_X, SIZE_STD_Y);
        this.add(jlabVolts);
        
        
        JLabel jlabCurrent = new JLabel("Current : ");
        jlabCurrent.setFont(fntBody);
        jlabCurrent.setBounds(COL_POS_X1, ROW_POS_Y4, SIZE_STDLAB_X, SIZE_STD_Y);
        this.add(jlabCurrent);
        
        jtxtAmps = new JTextField(10);
        jtxtAmps.setFont(fntBody);
        jtxtAmps.setBounds(COL_POS_X2, ROW_POS_Y4, SIZE_SMLENTRY_X, SIZE_STD_Y);
        this.add(jtxtAmps);
        
        JLabel jlabAmps = new JLabel(" Amps");
        jlabAmps.setFont(fntBody);
        jlabAmps.setBounds(COL_POS_X3, ROW_POS_Y4, SIZE_STDLAB_X, SIZE_STD_Y);
        this.add(jlabAmps);

        /* Resistance and power - right hand side */
        /* Set to 0 initially refresh and update exact values later */
       
        JLabel jlabResistance = new JLabel ("Resistance : ");
        jlabResistance.setFont(fntBody);
        jlabResistance.setBounds(COL_POS_X4, ROW_POS_Y3, SIZE_STDLAB_X1, SIZE_STD_Y);
        this.add(jlabResistance);
        
        jlabResistorVal = new JLabel ("9999", SwingConstants.RIGHT);
        jlabResistorVal.setFont(fntBody);
        jlabResistorVal.setBounds(COL_POS_X5, ROW_POS_Y3, SIZE_SMLLAB_X, SIZE_STD_Y);
        this.add(jlabResistorVal);
        
        JLabel jlabOhm = new JLabel ("\u2126");
        jlabOhm.setFont(fntBody);
        jlabOhm.setBounds(COL_POS_X6, ROW_POS_Y3, SIZE_SMLLAB_X, SIZE_STD_Y);
        this.add(jlabOhm);
        
        
        JLabel jlabPower = new JLabel ("Power : ");
        jlabPower.setFont(fntBody);
        jlabPower.setBounds(COL_POS_X4, ROW_POS_Y4, SIZE_STDLAB_X1, SIZE_STD_Y);
        this.add(jlabPower);
        
        jlabPowerVal = new JLabel ("0", SwingConstants.RIGHT);
        jlabPowerVal.setFont(fntBody);
        jlabPowerVal.setBounds(COL_POS_X5, ROW_POS_Y4, SIZE_SMLLAB_X, SIZE_STD_Y);
        this.add(jlabPowerVal);
        
        JLabel jlabWatts = new JLabel ("W");
        jlabWatts.setFont(fntBody);
        jlabWatts.setBounds(COL_POS_X6, ROW_POS_Y4, SIZE_SMLLAB_X, SIZE_STD_Y);
        this.add(jlabWatts);
        
        
        /* Refresh button */
        JButton jbtnRefresh = new JButton("Refresh");
        jbtnRefresh.addActionListener((ActionEvent ae) -> {
            updateResistance();
        });
        jbtnRefresh.setFont(fntBody);
        jbtnRefresh.setBounds(COL_POS_X4, ROW_POS_Y5, SIZE_BTN_X, SIZE_BTN_Y);
        this.add(jbtnRefresh);
        

        JLabel jlabSeriesInstr = new JLabel("Select resistor series and whether minimum, maximum or nearest");
        jlabInstr.setFont(fntBody);
        jlabInstr.setBounds(COL_POS_X0,ROW_POS_Y6,600,25);
        this.add(jlabSeriesInstr);
        

        JPanel jpanPref = new JPanel();
        this.add(jpanPref);
        
        JLabel jlabSeries = new JLabel("Preference : ");
        jlabSeries.setFont(fntBody);
        jlabSeries.setBounds(COL_POS_X1, ROW_POS_Y7, SIZE_STDLAB_X1, SIZE_STD_Y);
        this.add(jlabSeries);
        

        jcombPref = new JComboBox<String>();
        jcombPref.setFont(fntBody);
        jcombPref.setBounds(COL_POS_X2, ROW_POS_Y7, SIZE_STDENTRY_X, SIZE_STD_Y);
        jcombPref.addItem("Nearest");
        jcombPref.addItem("Lower value");
        jcombPref.addItem("Higher value");
        this.add(jcombPref);
        

        JLabel jlabSeriesLab = new JLabel("Resistor Series : ");
        jlabSeriesLab.setFont(fntBody);
        jlabSeriesLab.setBounds(COL_POS_X4, ROW_POS_Y7, SIZE_STDLAB_X2, SIZE_STD_Y);
        this.add(jlabSeriesLab);
 
        /* Check boxes */
        
        jckbxE3 = new JCheckBox("E3");
        jckbxE3.setSelected(true);
        jckbxE3.setFont(fntBody);
        jckbxE3.setBounds(COL_POS_X4, ROW_POS_Y8, SIZE_CBOX_X, SIZE_STD_Y);
        this.add(jckbxE3);
                
        jckbxE6 = new JCheckBox("E6");
        jckbxE6.setSelected(true);
        jckbxE6.setFont(fntBody);
        jckbxE6.setBounds(COL_POS_X7, ROW_POS_Y8, SIZE_CBOX_X, SIZE_STD_Y);
        this.add(jckbxE6);
        
        jckbxE12 = new JCheckBox("E12");
        jckbxE12.setSelected(true);
        jckbxE12.setFont(fntBody);
        jckbxE12.setBounds(COL_POS_X8, ROW_POS_Y8, SIZE_CBOX_X, SIZE_STD_Y);
        this.add(jckbxE12);
        

        
        jckbxE24 = new JCheckBox("E24");
        jckbxE24.setSelected(true);
        jckbxE24.setFont(fntBody);
        jckbxE24.setBounds(COL_POS_X9, ROW_POS_Y8, SIZE_CBOX_X, SIZE_STD_Y);
        this.add(jckbxE24);

        // by default only E12 and E24 selected (most common)
        jckbxE48 = new JCheckBox("E48");
        jckbxE48.setSelected(false);
        jckbxE48.setFont(fntBody);
        jckbxE48.setBounds(COL_POS_X10, ROW_POS_Y8, SIZE_CBOX_X, SIZE_STD_Y);
        this.add(jckbxE48);
        
        // by default only E12 and E24 selected (most common)
        jckbxE96 = new JCheckBox("E96");
        jckbxE96.setSelected(false);
        jckbxE96.setFont(fntBody);
        jckbxE96.setBounds(COL_POS_X11, ROW_POS_Y8, SIZE_CBOX_X, SIZE_STD_Y);
        this.add(jckbxE96);
        
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
        jlabSelectResisLabel.setBounds(COL_POS_X1, ROW_POS_Y9, SIZE_STDLAB_X2, SIZE_STD_Y);
        this.add(jlabSelectResisLabel);
        
        jlabSelectResisValue = new JLabel("0");
        jlabSelectResisValue.setFont(fntBody);
        jlabSelectResisValue.setBounds(COL_POS_X3, ROW_POS_Y9, SIZE_STDLAB_X, SIZE_STD_Y);
        this.add(jlabSelectResisValue);

        JLabel jlabOhm2 = new JLabel ("\u2126");
        jlabOhm2.setFont(fntBody);
        jlabOhm2.setBounds(COL_POS_X3A, ROW_POS_Y9, SIZE_SMLLAB_X, SIZE_STD_Y);
        this.add(jlabOhm2);
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
        // Should not get < 0 as would be interpretted as invalid anyway
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
        // Still put a space if no char to pad to Ω
        return approxValue + ' ';
    }
    
    void updateSelectedResistor() {
        
        jlabSelectResisValue.setText(formatResistance(resistance));
        
    }
    
    // ActionListener used by combobox and checkboxes
    ActionListener aclstPrefResistor = (ActionEvent actionEvent) -> {
        updateSelectedResistor();
    };
    
}

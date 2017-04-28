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
    
    EtbResistor() {
        
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 8;
        c.weightx = 1.0;
        this.add(new JLabel("Resistor Calculator Tool", SwingConstants.CENTER), c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 8;
        c.weightx = 1.0;
        JLabel jlabInstr = new JLabel("Add details of voltage and current for details of nearest resistor size.");
        this.add(jlabInstr, c);

        /* Voltage and Current input */
        
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.weightx = 1.00;
        JPanel jpanVoltage = new JPanel();
        this.add(jpanVoltage, c);
        
        JLabel jlabVoltage = new JLabel("Voltage : ");
        jpanVoltage.add(jlabVoltage);
        
        
        jtxtVolts = new JTextField(10);
        jpanVoltage.add(jtxtVolts);
        
        JLabel jlabVolts = new JLabel(" Volts");
        jpanVoltage.add(jlabVolts);
        
        
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        c.weightx = 1.00;
        JPanel jpanCurrent = new JPanel();
        this.add(jpanCurrent, c);
        
        JLabel jlabCurrent = new JLabel("Current : ");
        jpanCurrent.add(jlabCurrent);
        
        jtxtAmps = new JTextField(10);
        jpanCurrent.add(jtxtAmps);
        
        JLabel jlabAmps = new JLabel(" Amps");
        jpanCurrent.add(jlabAmps);

        /* Refresh and update exact values */
        
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 2;
        c.weightx = 1.00;
        JPanel jpanResistance = new JPanel();
        this.add(jpanResistance, c);
        
        JLabel jlabResistance = new JLabel ("Resistance : ");
        jpanResistance.add(jlabResistance);
        
        jlabResistorVal = new JLabel ("0");
        jpanResistance.add(jlabResistorVal);
        
        JLabel jlabOhm = new JLabel ("\u2126");
        jpanResistance.add(jlabOhm);
        
        
        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 2;
        c.weightx = 1.00;
        JPanel jpanPower = new JPanel();
        this.add(jpanPower, c);

        JLabel jlabPower = new JLabel ("Power : ");
        jpanPower.add(jlabPower);
        
        jlabPowerVal = new JLabel ("0");
        jpanPower.add(jlabPowerVal);
        
        JLabel jlabWatts = new JLabel ("W");
        jpanPower.add(jlabWatts);
        
        c.gridx = 3;
        c.gridy = 4;
        c.gridwidth = 2;
        c.weightx = 1.00;
        JPanel jpanRefresh = new JPanel();
        this.add(jpanRefresh, c);
        
        JButton jbtnRefresh = new JButton("Refresh");
        jbtnRefresh.addActionListener((ActionEvent ae) -> {
            updateResistance();
        });

        jpanRefresh.add(jbtnRefresh);
        
        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 1.0;
        c.gridwidth = 6;
        JLabel jlabSeriesInstr = new JLabel("Select resistor series and whether minimum, maximum or nearest");
        this.add(jlabSeriesInstr, c);
        
        c.gridx = 0;
        c.gridy = 6;
        c.weightx = 0.5;
        c.gridwidth = 1;
        JPanel jpanPref = new JPanel();
        this.add(jpanPref, c);
        
        JLabel jlabSeries = new JLabel("Preference : ");
        jpanPref.add(jlabSeries);
        

        jcombPref = new JComboBox<String>();
        jcombPref.addItem("Nearest");
        jcombPref.addItem("Lower value");
        jcombPref.addItem("Higher value");
        //this.add(jcombPref, c);
        jpanPref.add(jcombPref);
        
        c.gridx = 2;
        c.gridy = 6;
        c.weightx = 0.5;
        c.gridwidth = 1;
        JLabel jlabSeriesLab = new JLabel("Resistor Series : ");
        this.add(jlabSeriesLab, c);
        
        
        c.gridx = 3;
        c.gridy = 6;
        c.weightx = 0.5;
        c.gridwidth = 1;
        JPanel jpanE3 = new JPanel();
        this.add(jpanE3, c);
        
        jckbxE3 = new JCheckBox("E3");
        jckbxE3.setSelected(true);
        jpanE3.add(jckbxE3);
        
        c.gridx = 4;
        c.gridy = 6;
        c.weightx = 0.5;
        c.gridwidth = 1;
        JPanel jpanE6 = new JPanel();
        this.add(jpanE6, c);
        
        jckbxE6 = new JCheckBox("E6");
        jckbxE6.setSelected(true);
        jpanE6.add(jckbxE6);
        
        c.gridx = 5;
        c.gridy = 6;
        c.weightx = 0.5;
        c.gridwidth = 1;
        JPanel jpanE12 = new JPanel();
        this.add(jpanE12, c);
        
        jckbxE12 = new JCheckBox("E12");
        jckbxE12.setSelected(true);
        jpanE12.add(jckbxE12);
        
        c.gridx = 3;
        c.gridy = 7;
        c.weightx = 0.5;
        c.gridwidth = 1;
        JPanel jpanE24 = new JPanel();
        this.add(jpanE24, c);
        
        jckbxE24 = new JCheckBox("E24");
        jckbxE24.setSelected(true);
        jpanE24.add(jckbxE24);
        
        c.gridx = 4;
        c.gridy = 7;
        c.weightx = 0.5;
        c.gridwidth = 1;
        JPanel jpanE48 = new JPanel();
        this.add(jpanE48, c);
        
        // by default only E12 and E24 selected (most common)
        jckbxE48 = new JCheckBox("E48");
        jckbxE48.setSelected(false);
        jpanE48.add(jckbxE48);
        
        c.gridx = 5;
        c.gridy = 7;
        c.weightx = 0.5;
        c.gridwidth = 1;
        JPanel jpanE96 = new JPanel();
        this.add(jpanE96, c);
        
        // by default only E12 and E24 selected (most common)
        jckbxE96 = new JCheckBox("E96");
        jckbxE96.setSelected(false);
        jpanE96.add(jckbxE96);
        
        /* Add action listeners for prefs*/
        jcombPref.addActionListener(aclstPrefResistor);
        jckbxE3.addActionListener(aclstPrefResistor);
        jckbxE6.addActionListener(aclstPrefResistor);
        jckbxE12.addActionListener(aclstPrefResistor);
        jckbxE24.addActionListener(aclstPrefResistor);
        jckbxE48.addActionListener(aclstPrefResistor);
        jckbxE96.addActionListener(aclstPrefResistor);
        
        
        c.gridx = 0;
        c.gridy = 8;
        c.weightx = 1.0;
        c.gridwidth = 6;
        JPanel jpanSelectedResistor = new JPanel();
        this.add(jpanSelectedResistor, c);
        
        JLabel jlabSelectResisLabel = new JLabel("Nearest resistor");
        jpanSelectedResistor.add(jlabSelectResisLabel);
        jlabSelectResisValue = new JLabel("0");
        jpanSelectedResistor.add(jlabSelectResisValue);
        jpanSelectedResistor.add(jlabOhm);
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
        // technically posible to have higher, but not in normal electronics use
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
        // technically posible to have higher, but not in normal electronics use
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

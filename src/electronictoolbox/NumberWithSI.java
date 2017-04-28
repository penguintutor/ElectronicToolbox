/*
 * Copyright (C) 2016 stewart
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

/**
 *
 * @author stewart
 */
/*
* Stores a number with optional SI unit identify 
* currently supports M, k, m, μ (does not include centi etc.)
* Stores as string and actual value interpretted - this is required so that we can 
* show in the same unit of measurement that the user entered
*/
public class NumberWithSI {
    
    // All variables are private - need to access / update through methods
    // Note that the number is stored as a string to keep what the user specified
    private String numberString;
    // SI unit is kept separate so we don't need to keep removing and readding it
    private Character charSI = '\u0000';
    // Use to track if we have a valid value or not
    private Boolean valid = false;
    
    
    // All other values are generated as required
    
    // empty constructor
    public NumberWithSI() {
        this.numberString = new String();
    }
    
    // constructor with a given string - creates and parses value
    public NumberWithSI(String inputString) {
        this.numberString = new String();
        
        parseString (inputString);
        
    }
    
    // parse the input string - and store in the object
    // returns true for success, false if unable to interpret input
    // To get value back then need to call one of the other ethods
    public boolean parseString(String inputString) {
        
    
        // Set stringbuffer to size of original string (so can always fit entire string)
        StringBuilder stringBuffer = new StringBuilder(inputString.length());
        
        // Set character to null - if we get a character then try that instead
        Character possibleSI = '\u0000';
        
        // loop through copying digits into stringbuffer - until non number / whitespace character
        int i;
        for (i=0; i < inputString.length(); i++) {
            // skip any whitespace
            if (Character.isWhitespace(inputString.charAt(i))) continue;
            // if it's a digit comma or dot then add to the string
            if (Character.isDigit(inputString.charAt(i)) || inputString.charAt(i)==',' || inputString.charAt(i)=='.') {
                stringBuffer.append(inputString.charAt(i));
                continue;
            }
            // Any other character break out of loop
            possibleSI = inputString.charAt(i);
            break;
        }
        // check to see if the string is a valid number - otherwise we return false
        // Only need to do this in a try block here as long as check valid first 
        // First check it's not 
        try {
            Double.parseDouble(stringBuffer.toString());
            // only get here if it doesn't through an exception
            numberString = stringBuffer.toString();
            valid = true;
        }
        catch (NumberFormatException e) {
            valid = false;
            return false;
        }
        
        
        // If latest character is an SI character then handle that here
        // First reset the current value in case of error
        charSI = '\u0000';
        // now test for each of the supported characters
        switch (possibleSI) {
            case 'M': charSI = 'M';         // Mega 1000000
                      break;
            case 'K':                       // Should be lower case - but common error
            case 'k': charSI = 'k';         // Kilo 1000
                      break;
            case 'm': charSI = 'm';         // milli 0.001
                      break;
            case 'u':  
            case  (char)0x00B5: charSI = (char)0x00B5;     // micro 0.000001
                      break;
            default:
                      break;                // No SI char / invalid - ignore
        }
        
        return true;

    }
    
    public boolean isValid () {
        return valid;
    }
    
    public String formattedString () {
        // If not valid then return empty string
        if (!valid) return "";
        // If no units then return string as is, otherwise add SI unit
        if (charSI == '\u0000') return numberString;    
        return numberString + ' ' + charSI;
    }
    
    // get as a double value after applying SI modifier
    public Double getValue() {
        // Higher code should check validity first, but if not return 0
        if (!valid) return 0.0;
        // No error checking - we already performed a parseDouble during parse so we know it works
        Double value = Double.parseDouble(numberString);
        // Now apply SI if applicable
        switch (charSI) {
            case 'M': value *= 1000000;         // Mega 1000000
                      break;
            case 'k': value *= 1000;            // Kilo 1000
                      break;
            case 'm': value *= 0.001;           // milli 0.001
                      break;
            case (char)0x00B5: value *= 0.000001;        // micro 0.000001
                      break;
            default:
                      break;                    // No SI char ignore
        }
        return value;
    }
    
}

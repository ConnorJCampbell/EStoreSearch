/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estoresearch;

import estoresearch.productExceptions.*;
/**
 * Electronics is a subclass of Product that is used to store objects in a list
 * @author Connor
 */
public class Electronics extends Product{
    private String maker;
    
    /**
     * The default constructor used for assigning ID, name and year to an
     * Electronic object. If the object needs to assign price or maker it is done
     * with mutators
     * @param ID  String for the ID number of the Electronic
     * @param name String for the name of the Electronic
     * @param year String for the Electronic year
     * @throws estoresearch.productExceptions.ImproperIDException for checking ID
     * @throws estoresearch.productExceptions.RepeatIDException for checking ID repeats
     * @throws estoresearch.productExceptions.RequiredInputException for checking name
     * @throws estoresearch.productExceptions.InvalidYearException for checking year
     */
    public Electronics(String ID, String name, String year) throws ImproperIDException, RepeatIDException, RequiredInputException, InvalidYearException {
        super(ID, name, year);
        maker = "";
    }
    
    /**
     * getMaker returns the maker for an Electronic
     * @return String representing maker
     */
    public String getMaker() {
        return this.maker;
    }
    
    /**
     * setMaker reassigns the maker for an Electronic
     * @param maker String representing the maker
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }
    
    /**
     * toString converts the contents of an Electronics object into a String that is 
     * in user-readable format
     * @return String representing the information to be printed 
     */
    @Override
    public String toString() {
        return super.toString() + 
               "Manufacturer: " + this.maker +
               "\n";
    }
    
    /**
     * Compares the contents of two Electronics objects to see if all their instance
     * variables match. Returns true if all the variables match, false if not.
     * @param other Book representing the object you want to compare with.
     * @return boolean true if the two Electronics match and false if they do not
     */
    @Override
    public boolean equals(Object other) {
        if(other == null)
            return false;
        else if(getClass() != other.getClass()) 
            return false;
        else {
            Electronics toCompare = (Electronics)other;  
            return super.equals(other) && maker.equals(toCompare.maker);
        }
    }
    
    /**
     * dataDump converts the instance variables in an Electronics into a single String
     * that is in the format of the input/output file.
     * @return String representing the contents of a list to be printed to a file
     */
    @Override
    public String dataDump() {
        String tempString;
        String eol = System.getProperty("line.separator");
        tempString = "type = \"electronics\"" + eol + super.dataDump();
        
        if(this.maker.equals(""))
            tempString += "maker = \" ";
        else
            tempString += "maker = \"" + this.maker;
        
        tempString += "\"" + eol;
        return tempString;
    }
}

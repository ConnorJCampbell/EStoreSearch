/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estoresearch;

import static estoresearch.ProductFunctions.*;
import estoresearch.productExceptions.*;

/**
 * Product is a parent class for other objects that are all stored in a single
 * product list.
 * @author Connor
 */
public abstract class Product {
    private String productID;
    private String name;
    private String price;
    private String year;
    
    /**
     * The default constructor used for assigning ID, name and year to a
     * product object. If the object needs to assign price it is done with mutators
     * @param ID  String for the ID number of the product
     * @param name String for the name of the product
     * @param year String for the product year
     * @throws estoresearch.productExceptions.ImproperIDException for checking ID
     * @throws estoresearch.productExceptions.RepeatIDException for checking ID repeats
     * @throws estoresearch.productExceptions.RequiredInputException for checking name
     * @throws estoresearch.productExceptions.InvalidYearException for checking year
     */
    public Product(String ID, String name, String year) throws ImproperIDException, RepeatIDException, RequiredInputException, InvalidYearException{
        productExceptions exception = new productExceptions();
        if (validInputID(ID) == false){	
            throw exception.new ImproperIDException("ERROR: Please Enter a valid ID\n");
        }
        else if(listContainsID(ID, ProductFunctions.getList()) == true) {
            throw exception.new RepeatIDException("ERROR: List already contains the provided ID\n");
        }
        if (name.equals("")){	//Check if the name is empty
            throw exception.new RequiredInputException("ERROR: Required Fields have not been filled out\n");
        }
        if (validInputYear(year) == false){
            throw exception.new InvalidYearException("ERROR: Improper value for year\n");
        }
        else {
            this.productID = ID;
            this.name      = name;
            this.price     = "";
            this.year      = year;
        }     
    }
    
    /**
     * getProductID returns the product ID for a Product
     * @return String representing the ID
     */
    public String getProductID() {
        return this.productID;
    }
    
    /**
     * getName returns the name for a Product
     * @return String representing the name 
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * getPrice returns the price for a Product
     * @return String representing the price
     */
    public String getPrice() {
        return this.price;
    }
    
    /**
     * getYear returns the product Year for a Product
     * @return String representing the Year 
     */
    public String getYear() {
        return this.year;
    }
    
    /**
     * setProductID reassigns the productID for a Product
     * @param ID String representing the new ID
     * @throws estoresearch.productExceptions.ImproperIDException for checking the ID format
     * @throws estoresearch.productExceptions.RepeatIDException for checking if ID is a repeat
     */ 
    public void setProductID(String ID) throws ImproperIDException, RepeatIDException{
        productExceptions exception = new productExceptions();
        if (validInputID(ID) == false){	
            throw exception.new ImproperIDException("ERROR: Please Enter a valid ID\n");
        }
        else if(listContainsID(ID, ProductFunctions.getList()) == true) {
            throw exception.new RepeatIDException("ERROR: List already contains the provided ID\n");
        }
        this.productID = ID;
    }
    
    /**
     * setName reassigns the name for a Product
     * @param name String representing the new name
     * @throws estoresearch.productExceptions.RequiredInputException for checking if nothing is entered
     */
    public void setName(String name) throws RequiredInputException{
        productExceptions exception = new productExceptions();
        if (name.equals("")){	//Check if the name is empty
            throw exception.new RequiredInputException("ERROR: Required Fields have not been filled out\n");
        }
        this.name = name;
    }
    
    /**
     * setPrice reassigns the price for a Product
     * @param price String representing the new price
     * @throws estoresearch.productExceptions.InvalidPriceException for checking the price format
     */
    public void setPrice(String price) throws InvalidPriceException{
        productExceptions exception = new productExceptions();
        if (validInputPrice(price) == false){
            throw exception.new InvalidPriceException("ERROR: Invalid price. Must be in the format \"100\" or \"100.99\"\n");
        }
        this.price = price;
    }
    
    /**
     * setYear reassigns the year for a Product
     * @param year String representing the year
     * @throws estoresearch.productExceptions.InvalidYearException for checking the year format
     */
    public void setYear(String year) throws InvalidYearException{
        productExceptions exception = new productExceptions();
        if (validInputYear(year) == false){
            throw exception.new InvalidYearException("ERROR: Improper value for year\n");
        }
        this.year = year;
    }
    
    /**
     * toString converts the contents of a product object into a String that is 
     * in user-readable format
     * @return String representing the information to be printed 
     */
    @Override
    public String toString() {
        return "Prodct ID: " + this.productID + 
               "\nName: " + this.name + 
               "\nPrice: " + this.price + 
               "\nYear: " + this.year + 
               "\n";
    }
    
    /**
     * Compares the contents of two product objects to see if all their instance
     * variables match. Returns true if all the variables match, false if not.
     * @param other Product representing the object you want to compare with
     * @return boolean true if the two Products match and false if they do not
     */
    @Override
    public boolean equals(Object other) {
        if(other == null)
            return false;
        else if(getClass() != other.getClass()) 
            return false;
        else {
            Product toCompare = (Product)other;  
            return productID.equals(toCompare.productID) && name.equals(toCompare.name)  &&
                    price.equals(toCompare.price) && year.equals(toCompare.year);
        }
    }
    
    /**
     * dataDump converts the instance variables in a Product into a single String
     * that is in the format of the input/output file.
     * @return String representing the contents of a list to be printed to a file
     */
    public String dataDump() {
        String tempString;
        String eol = System.getProperty("line.separator");
        tempString = "productID = \"" + this.productID + 
                 "\"" + eol + "name = \"" + this.name;
        
        if(this.price.equals(""))
            tempString += "\"" + eol + "price = \" ";
        else
            tempString += "\"" + eol + "price = \"" + this.price;
        
        tempString += "\"" + eol + "year = \"" + this.year + 
                      "\"" + eol;
        return tempString;
    }
}

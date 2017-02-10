/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estoresearch;
import estoresearch.productExceptions.*;

/**
 * Book is a subclass of Product that is used to store objects in a list
 * @author Connor
 */
public class Book extends Product {
    private String author;
    private String publisher;
    
    /**
     * The default constructor used for assigning ID, name and year to a
     * Book object. If the object needs to assign price, author or publisher it
     * is done with mutators
     * @param ID  String for the ID number of the Book
     * @param name String for the name of the Book
     * @param year String for the Book year
     * @throws estoresearch.productExceptions.ImproperIDException for checking ID
     * @throws estoresearch.productExceptions.RepeatIDException for checking ID repeats
     * @throws estoresearch.productExceptions.RequiredInputException for checking name
     * @throws estoresearch.productExceptions.InvalidYearException for checking year
     */
    public Book(String ID, String name, String year) throws ImproperIDException, RepeatIDException, RequiredInputException, InvalidYearException{
        super(ID, name, year);
        author    = "";
        publisher = "";
    }
    
    /**
     * getAuthor returns the author for a Book
     * @return String representing the author
     */
    public String getAuthor() {
        return this.author;
    }
    
    /**
     * getPublisher returns the publisher for a Book
     * @return String representing the publisher
     */
    public String getPublisher() {
        return this.publisher;
    }
    
    /**
     * setAuthor reassigns the author for a Book
     * @param author String representing the new author
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**'
     * setPublisher reassigns the publisher for a Book
     * @param publisher String representing the publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    /**
     * toString converts the contents of a Book object into a String that is 
     * in user-readable format
     * @return String representing the information to be printed 
     */
    @Override
    public String toString() {
        return super.toString() + 
               "Author: " + this.author + 
               "\nPublisher: " + this.publisher +
               "\n";
    }
    
    /**
     * Compares the contents of two Book objects to see if all their instance
     * variables match. Returns true if all the variables match, false if not.
     * @param other Book representing the object you want to compare with
     * @return boolean true if the two Books match and false if they do not
     */
    @Override
    public boolean equals(Object other) {
        if(other == null)
            return false;
        else if(getClass() != other.getClass()) 
            return false;
        else {
            Book toCompare = (Book)other;  
            return super.equals(other) && author.equals(toCompare.author) &&
                publisher.equals(toCompare.publisher);
        }
    }
    
    /**
     * dataDump converts the instance variables in a Book into a single String
     * that is in the format of the input/output file.
     * @return String representing the contents of a list to be printed to a file
     */
    public String dataDump() {
        String tempString;
        String eol = System.getProperty("line.separator");
        tempString = "type = \"book\"" + eol + super.dataDump();
        
        if(this.author.equals(""))
            tempString += "author(s) = \" ";
        else
            tempString += "author(s) = \"" + this.author;
        
        if(this.publisher.equals(""))
            tempString += "\"" + eol + "publisher = \" ";
        else
            tempString += "\"" + eol + "publisher = \"" + this.publisher;
        
        tempString += "\"" + eol;
        return tempString;
    }
}

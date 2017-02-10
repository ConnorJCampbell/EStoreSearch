/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estoresearch;

import java.util.ArrayList;
import java.util.HashMap;
import estoresearch.productExceptions.*;
import java.io.*;
import java.util.Scanner;

/**
 * ProductFunctions includes many methods that all involve manipulating the 
 * list of products in some way.
 * @author Connor
 */
public class ProductFunctions {
    //collection of all Products instances
    public static ArrayList<Product> products = new ArrayList<>();
    public static HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    
    /**
     * Takes the parameters for a new book product as input and confirms whether or
     * not they are valid. If they are, a new book product is created and added to the list.
     * Returns true if the book is created, false if not.
     * 
     * @param ID String representing the ID
     * @param name String representing the name
     * @param year String representing the year
     * @param price String representing the price
     * @param author String representing the author
     * @param publisher String representing the publisher
     * @return boolean representing whether or not the instance creation was successful
     */
    protected static boolean addBook(String ID, String name, String year, String price, String author, String publisher) {
        Book tempBook;
        ID = ID.trim();
        name = name.trim();
        year = year.trim();
        price = price.trim();
        author = author.trim();
        publisher = publisher.trim();
        String keyList[];
        int i;
        try{
            //create new book instance and add it to the appropriate arraylist
            tempBook = new Book(ID, name, year);
            if (!price.equals("") && !price.equals(" ")) {
                tempBook.setPrice(price);
            }
            if (!author.equals("") && !author.equals(" ")) {
                tempBook.setAuthor(author);
            }
            if (!publisher.equals("") && !publisher.equals(" ")) {
                tempBook.setPublisher(publisher);
            }
            products.add(tempBook);
            
            keyList = name.split("[ ]+");
            for(i = 0; i<keyList.length;i++) {
                map.putIfAbsent(keyList[i].toLowerCase(), new ArrayList<>());
                map.get(keyList[i].toLowerCase()).add(products.size()-1);
            }
            //return true if car is successfully dreated and added
            return true;    
        //check for thrown conditions and perfrom approriate action
        } catch (ImproperIDException | RepeatIDException | RequiredInputException | InvalidYearException | InvalidPriceException e){
                StoreGUI.appendBook(e.getMessage());
        } catch (NumberFormatException e){
                StoreGUI.appendBook("ERROR: Input for things like ID and year contain only numbers.\n"); 
        }    
        //return false if the car is not successfully created
        return false;
    }
    
    /**
     * Takes the parameters for a new electronic product as input and confirms whether or
     * not they are valid. If they are, a new electronics product is created and added to the list.
     * Returns true if the electronics is created, false if not.
     * 
     * @param ID String representing the ID
     * @param name String representing the name
     * @param year String representing the year
     * @param price String representing the price
     * @param maker String representing the maker
     * @return boolean representing whether or not the instance creation was successful
     */
    protected static boolean addElec(String ID, String name, String year, String price, String maker) {
        productExceptions exception = new productExceptions();
        Electronics tempElec;
        ID = ID.trim();
        name = name.trim();
        year = year.trim();
        price = price.trim();
        maker = maker.trim();
        String keyList[];
        int i;
        try{
            
            //create new electronics instance and add it to the appropriate arraylist
            tempElec = new Electronics(ID, name, year);
            if (!price.equals("") && !price.equals(" ")) {
                tempElec.setPrice(price);
            }
            if (!maker.equals("") && !maker.equals(" ")) {
                tempElec.setMaker(maker);
            }
            products.add(tempElec);
            keyList = name.split("[ ]+");
            for(i = 0; i<keyList.length;i++) {
                map.putIfAbsent(keyList[i].toLowerCase(), new ArrayList<>());
                map.get(keyList[i].toLowerCase()).add(products.size()-1);
            }
            //return true if car is successfully dreated and added
            return true;
            
        //check for thrown conditions and perfrom approriate action
        } catch (ImproperIDException | RepeatIDException | RequiredInputException | InvalidYearException | InvalidPriceException e){
                StoreGUI.appendElec(e.getMessage());
        } catch (NumberFormatException e){
                StoreGUI.appendElec("ERROR: Input for things like ID and year contain only numbers.\n"); 
        }
        
        //return false if the car is not successfully created
        return false;
    }
    
    /**
     * This method takes in the parameters for searching and makes sure that they are valid.
     * Returns true if the input is valid, false if not.
     * 
     * @param ID String representing the ID to search for
     * @param name String representing the name to search for
     * @param year1 String representing the start year
     * @param year2 String representing the end year
     * @return boolean representing whether or not the input is valid
     */
    protected static boolean checkSearch(String ID, String name, String year1, String year2){
        productExceptions exception = new productExceptions();
        ID = ID.trim();
        name = name.trim();
        year1 = year1.trim();
        year2 = year2.trim();

        int temp, temp2;
        int i;
        try{
            //check ID
            if(!ID.equals("")) {
                if (validInputID(ID) == false){	
                    throw exception.new ImproperIDException("ERROR: Please Enter a valid ID\n");
                }
            }
            //check years
            if(!year1.equals("")) {
                if (validInputYear(year1) == false){
                    throw exception.new InvalidYearException("ERROR: Improper value for year fields\n");
                }
            }
            if(!year2.equals("")) {
                if(validInputYear(year2) == false) {
                    throw exception.new InvalidYearException("ERROR: Improper value for year fields\n");
                }
            }
            if(!year1.equals("") && !year2.equals("")) {
                temp = Integer.parseInt(year1);
                temp2 = Integer.parseInt(year2);
                if(temp2 < temp) {
                    throw exception.new InvalidYearException("ERROR: Improper value for year fields\n");
                }
            }
            //return true if all input is proper
            return true;
        //check for thrown conditions and perfrom approriate action
        } catch (ImproperIDException | InvalidYearException e){
                StoreGUI.appendSearch(e.getMessage());
        } catch (NumberFormatException e){
                StoreGUI.appendSearch("ERROR: Input for things like ID and year contain only numbers.\n"); 
        }
        
        //return false if there is a prblem with the input
        return false;
    }
    
    /**
     * validInputID checks a given string to see if it is in the proper format
     * to be an ID.
     * @param tempString String representing the ID to check
     * @return boolean true if the ID is valid, false if it is not.
     */
    protected static boolean validInputID(String tempString) {
        //check length, negative and
        return !(tempString.length() != 6 || tempString.charAt(0) == '-' || isInt(tempString) == false);
    }
    
    /**
     * This method is used to check whether or not a string representing a
     * product ID is already present in the arrayList of books
     *
     * @param toBeAdded an ID in the form of a string
     * @param products an arrayList of type products
     * @return boolean Returns true if the ID already exists and false if not
     */
    protected static boolean listContainsID(String toBeAdded, ArrayList<Product> products) {
        int i;
        if(products.isEmpty())
            return false;
        for (i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID().equals(toBeAdded)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method is used to check whether or not a string could be changed
     * into an int without any errors
     *
     * @param test a string to be tested
     * @return boolean Returns true if test can be an int and false if not
     */
    protected static boolean isInt(String test) {
        try {
            Integer.parseInt(test);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * This method is used to check whether or not a string could be changed
     * into a double without any errors
     *
     * @param test a string to be tested
     * @return boolean Returns true if test can be a double and false if not
     */
    protected static boolean isDouble(String test) {
        try {
            Double.parseDouble(test);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * validInputYear checks a given string to see if it is in the proper format
     * to be an Year.
     * @param tempString String representing the Year to check
     * @return boolean true if the Year is valid, false if it is not
     */
    protected static boolean validInputYear(String tempString) {
        int tempInt;
        
        if (isInt(tempString) == false) {
            System.out.println("The year can only contain numbers.");
            return false;
        } 
        //check for negative
        else if (tempString.charAt(0) == '-') {
            System.out.println("No negative numbers");
            return false;
        } 
        //check if input is a within correct range
        else {
            tempInt = Integer.parseInt(tempString);
            if (tempInt < 1000 || tempInt > 9999) {
                System.out.println("The year must be within the range 1000-9999");
                return false;
            } else {
                return true;
            }
        }
    }
    
    /**
     * validInputPrice checks a given string to see if it is in the proper format
     * to be a price.
     * @param tempString String representing the price to check
     * @return boolean true if the price is valid, false if it is not
     */
    protected static boolean validInputPrice(String tempString) {
        int i;
        //check if input is empty
        if (tempString.equals("") || tempString.equals(" ")) {
            return true;
        }
        //check for negative
        else if (tempString.charAt(0) == '-') {
            return false;
        } 
        //check if input is a number
        else if (isDouble(tempString) == false) {
            //System.out.println("The ID can only contain numbers, a single decimal is optional.");
            return false;
        } else {
            for (i = 0; i < tempString.length(); i++) {
                if (tempString.charAt(i) == '.' && (tempString.length() - i - 1 != 2)) {
                    System.out.println("There should only be 2 digits after the decimal.");
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * A method that simply returns the arraylist so that it can be accessed by other classes
     * @return ArrayList representing the list of products
     */
    protected static ArrayList<Product> getList() {
        return products;
    }
    
    /**
     * This method scans through the input file provided from the command line.
     * It parses the data and then creates products that are added to the list.
     * The program will exit if the file does not exist
     */
    protected static void scanFile() {
        File inputFile;
        int i;
        String line, type, ID, name, price, year, maker, author, publisher; 
        String words[];
        String keyList[];
        Book tempBook;
        Electronics tempElec;
        boolean result;
        
        //scan in new file
        try {
            inputFile = new File(EstoreSearch.getFilename());
            Scanner fScan = new Scanner(inputFile);
            //parse file and add new cars
            while(fScan.hasNextLine()) {
                line = fScan.nextLine();
                if(!line.trim().equals("") && line.contains("\"")){
                    words = line.split("\"");
                    type = words[1];
                    
                    if(type.equals("book") || type.equals("electronics")) {
                        //collect ID from file
                        line = fScan.nextLine();
                        words = line.split("\"");
                        ID = words[1];
                        if(listContainsID(ID, products) == true) {
                            System.out.println("A product in the input file contained a duplicate ID number, so it was not added.");
                            continue;
                        }
                        

                        //collect name from file
                        name = "";
                        line = fScan.nextLine();
                        words = line.split("\"");
                        name+=words[1];
                        if(words.length != 2) {
                            for(i = 2; i<words.length;i++) {
                                name += "\"" + words[i]; 
                            }
                        }

                        //collect price from file
                        line = fScan.nextLine();
                        words = line.split("\"");
                        price = words[1];

                        //collect year from file
                        line = fScan.nextLine();
                        words = line.split("\"");
                        year = words[1];

                        //entry specific for books
                        if(type.equals("book")) {
                            //collect author
                            author = "";
                            line = fScan.nextLine();
                            words = line.split("\"");
                            author+=words[1];
                            if(words.length != 2) {
                                for(i = 2; i<words.length;i++) {
                                    author += "\"" + words[i]; 
                                }
                            }

                            //collect publisher
                            publisher = "";
                            line = fScan.nextLine();
                            words = line.split("\"");
                            publisher+=words[1];
                            if(words.length != 2) {
                                for(i = 2; i<words.length;i++) {
                                    publisher += "\"" + words[i]; 
                                }
                            }
                            try {
                            //create new book instance and add it to the appropriate arraylist
                                tempBook = new Book(ID, name, year);
                                if (!price.equals(" ")) 
                                    tempBook.setPrice(price);

                                if (!author.equals(" ")) 
                                    tempBook.setAuthor(author);

                                if (!publisher.equals(" ")) 
                                    tempBook.setPublisher(publisher);

                                products.add(tempBook);
                                keyList = name.split("[ ]+");
                                for(i = 0; i<keyList.length;i++) {
                                    map.putIfAbsent(keyList[i].toLowerCase(), new ArrayList<>());
                                    map.get(keyList[i].toLowerCase()).add(products.size()-1);
                                }
                            } catch (ImproperIDException | RepeatIDException | RequiredInputException | InvalidYearException | InvalidPriceException e){
                                System.out.println(e.getMessage());
                            } catch (NumberFormatException e){
                                System.out.println("ERROR: Input for things like ID and year contain only numbers.\n"); 
                            }   
                        }

                        //specific entry for electronics
                        else if(type.equals("electronics")) {

                            //collect maker
                            maker = "";
                            line = fScan.nextLine();
                            words = line.split("\"");
                            maker+=words[1];
                            if(words.length != 2) {
                                for(i = 2; i<words.length;i++) {
                                    maker += "\"" + words[i]; 
                                }
                            }
                            try {
                                //create new electronics instance and add it to the appropriate arraylist
                                tempElec = new Electronics(ID, name, year);
                                if (!price.equals(" ")) 
                                    tempElec.setPrice(price);

                                if (!maker.equals(" ")) 
                                    tempElec.setMaker(maker);

                                products.add(tempElec);
                                keyList = name.split("[ ]+");
                                for(i = 0; i<keyList.length;i++) {
                                    map.putIfAbsent(keyList[i].toLowerCase(), new ArrayList<>());
                                    map.get(keyList[i].toLowerCase()).add(products.size()-1);
                                }
                            } catch (ImproperIDException | RepeatIDException | RequiredInputException | InvalidYearException | InvalidPriceException e){
                                System.out.println(e.getMessage());
                            } catch (NumberFormatException e){
                                System.out.println("ERROR: Input for things like ID and year contain only numbers.\n"); 
                            } 
                        }
                    }         
                }
            }   
        //return to main menu if the file cannot be opened
        } catch(FileNotFoundException | NumberFormatException e) {
            System.out.println("Could not read the file. Exiting program.");
            System.exit(0);
        }
        System.out.println("The contents of the file were copied into a list.");
    }
    
    /**
     * This method makes use of the dataDump method to print out the contents of the
     * list to a file. After this operation is performed, the program exits.
     */
    protected static void printToFile() {
        int i;
        //print new list content to a file so it can be loaded back in later.
        try  {
            PrintWriter filePrint = new PrintWriter(EstoreSearch.getFilename(), "UTF-8");
            //loop for printing to file
            for (i = 0; i < products.size(); i++) {
                filePrint.println(products.get(i).dataDump());
            }
            filePrint.close();
            System.out.println("The new list was printed to the file.");
            System.exit(0);
        //output message if an error is encountered writing to the file
        } catch(FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("Failed to write info to the file.");
        }
        System.exit(0);
    }
    
    /**
     * Accepts the parameters that have been confirmed to be valid by checkSearch
     * and uses them to search the List. If any products that match the parameters
     * are found, they are printed to the screen.
     * 
     * @param ID String representing the ID to search for
     * @param name String representing the name to search for
     * @param year1 String representing the start year
     * @param year2 String representing the end year
     */
    protected static void search(String ID, String name, String year1, String year2) {
        //first condition checks if all parameters are empty
        String searchID = ID;
        String searchName = name;
        String searchYear;
        //the search parameter for year will change depending on what fields were entered
        if(year1.equals("") && year2.equals(""))
            searchYear = "";
        else if(year1.equals("") && !year2.equals(""))
            searchYear = "-" + year2;
        else if(year2.equals("") && !year1.equals(""))
            searchYear = year1 + "-";
        else if(year1.equals(year2))
            searchYear = year1;
        else
            searchYear = year1 + "-" + year2;
        
        boolean itemPrinted, foundID, foundYear;
        ArrayList<Integer> indexList;
            
        int i;
        //print stars to seperate searches
        StoreGUI.appendSearch("/****************************************************/\n");
        //first condition checks if all are empty
        if (searchID.equals("") && searchName.equals("") && searchYear.equals("")) {
            if(products.isEmpty())
                StoreGUI.appendSearch("List is empty, there are no products.");
            for (i = 0; i < products.size(); i++) {
                StoreGUI.appendSearch("\nProduct Found\n:");
                StoreGUI.appendSearch(products.get(i).toString());

            }
        }

        //second condition checks if only year is entered
        else if (searchID.equals("") && searchName.equals("")) {
            //check list for product
            itemPrinted = false;
            for (i = 0; i < products.size(); i++) {
                if (checkYear(searchYear, i) == true) {
                    StoreGUI.appendSearch("\nProduct Found:\n");
                    StoreGUI.appendSearch(products.get(i).toString());
                    itemPrinted = true;
                }
            }
            if (itemPrinted == false) {
                StoreGUI.appendSearch("\nNo products were found.\n");
            }
        }

        //third condition checks if only name is entered
        else if (searchID.equals("") && searchYear.equals("")) {

            //check list
            indexList = new ArrayList<>(checkNameHash(searchName));
            if(indexList.isEmpty() == true) {
                StoreGUI.appendSearch("\nNo products were found.\n");
            }
            else {
                for (i = 0; i < indexList.size(); i++) {
                        StoreGUI.appendSearch("\nProduct Found:\n");
                        StoreGUI.appendSearch(products.get(indexList.get(i)).toString());
                }
            }

        }

        //fourth condition checks if only ID is entered
        else if(searchName.equals("") && searchYear.equals("")) {

            //search the list
            itemPrinted = false;
            for(i = 0; i < products.size(); i++){
                if (products.get(i).getProductID().equals(searchID)) {
                    StoreGUI.appendSearch("\nProduct Found:\n");
                    StoreGUI.appendSearch(products.get(i).toString());
                    itemPrinted = true;
                    break;
                }
            }
            if (itemPrinted == false)
                StoreGUI.appendSearch("\nNo Products were found.\n");
        }

        //fifth condition checks if year and name are entered
        else if(searchID.equals("")) {

            //check list
            indexList = new ArrayList<>(checkNameHash(searchName));
            if(indexList.isEmpty() == true) {
                StoreGUI.appendSearch("\nNo products were found.\n");
            }
            else {
                itemPrinted = false;
                for (i = 0; i < indexList.size(); i++) {
                    if (checkYear(searchYear, indexList.get(i)) == true) {
                        StoreGUI.appendSearch("\nProduct Found:\n");
                        StoreGUI.appendSearch(products.get(indexList.get(i)).toString());
                        itemPrinted = true;
                    }
                }
                if (itemPrinted == false) 
                    StoreGUI.appendSearch("\nNo products were found.\n");
            } 
        }

        //sixth condition checks if both ID and Year are entered 
        else if(searchName.equals("")) {
            //check list
            itemPrinted = false;
            for (i = 0; i < products.size(); i++) {
                foundYear = false;
                foundID = false;

                if (products.get(i).getProductID().equals(searchID)) 
                    foundID = true;

                if (checkYear(searchYear, i) == true)
                    foundYear = true;

                //search successful
                if( foundYear == true && foundID == true) {
                    StoreGUI.appendSearch("\nProduct Found:\n");
                    StoreGUI.appendSearch(products.get(i).toString());
                    itemPrinted = true;
                }
            }
            if (itemPrinted == false) {
                StoreGUI.appendSearch("\nNo products were found.\n");
            }  
        }

        //seventh condition checks if ID and Name are entered
        else if(searchYear.equals("")) {
            //check list
            indexList = new ArrayList<>(checkNameHash(searchName));
            if(indexList.isEmpty() == true) {
                StoreGUI.appendSearch("\nNo products were found.\n");
            }
            else {
                itemPrinted = false;
                for (i = 0; i < indexList.size(); i++) {
                    if (products.get(indexList.get(i)).getProductID().equals(searchID)) {
                        StoreGUI.appendSearch("Product Found:\n");
                        StoreGUI.appendSearch(products.get(indexList.get(i)).toString());
                        itemPrinted = true;
                    }
                }
                if (itemPrinted == false) 
                    StoreGUI.appendSearch("\nNo products were found.\n");
            }

        }

        //final condition is if all parameters are entered
        else {
            //check list                
            indexList = new ArrayList<>(checkNameHash(searchName));
            if(indexList.isEmpty() == true) {
                StoreGUI.appendSearch("\nNo products were found.\n");
            }
            else {
                itemPrinted = false;
                for (i = 0; i < indexList.size(); i++) {
                    foundID = false;
                    foundYear = false;

                    //check ID
                    if (products.get(indexList.get(i)).getProductID().equals(searchID))
                        foundID = true;

                    //check year
                    if (checkYear(searchYear, indexList.get(i)) == true)
                        foundYear = true;

                    //search successful
                    if(foundID == true && foundYear == true) {
                        StoreGUI.appendSearch("\nProduct Found:\n");
                        StoreGUI.appendSearch(products.get(indexList.get(i)).toString());
                        itemPrinted = true;
                    }
                }
                if (itemPrinted == false) 
                    StoreGUI.appendSearch("\nNo products were found.\n");
            }  
        }
    }
    
    /**
     * searchYear checks whether or not a string representing a year is present
     * in an given object. Returns true if the year is found, false if it is not.
     * @param searchYear String representing the year(s) to search for
     * @param index integer representing the current position in the list 
     * @return boolean true if the object contains the year, false if not.
     */
    public static boolean checkYear(String searchYear, int index) {
        int tempInt;
        int tempInt2;
        String years[];

        //"2000" format
        if (searchYear.contains("-") == false) {
            tempInt = Integer.parseInt(searchYear);

            //check list
            if (Integer.parseInt(products.get(index).getYear()) == tempInt) {
                return true;
            }
        } //"-2000" format
        else if (searchYear.charAt(0) == '-') {
            tempInt = Integer.parseInt(searchYear.replaceAll("[-]", ""));

            //check list
            if (Integer.parseInt(products.get(index).getYear()) <= tempInt) {
                return true;
            }
        } //"2000-" format
        else if (searchYear.charAt(searchYear.length() - 1) == '-') {
            tempInt = Integer.parseInt(searchYear.replaceAll("[-]", ""));

            //check list
            if (Integer.parseInt(products.get(index).getYear()) >= tempInt) {
                return true;
            }
        } //"2000-2001" format
        else {
            years = searchYear.split("-");
            tempInt = Integer.parseInt(years[0]);
            tempInt2 = Integer.parseInt(years[1]);

            //check list
            if (Integer.parseInt(products.get(index).getYear()) >= tempInt
                    && Integer.parseInt(products.get(index).getYear()) <= tempInt2) {
                return true;
            }
        }
        return false;
    }

    
    /**
     * checkNameHash searches for the objects in an ArrayList that contain the
     * provided keywords. Returns a list of integers representing the objects that
     * contain the string. Returns an empty list on a failed search.
     * @param searchName A String representing the search keys
     * @return  ArrayList of integers representing indexes in a list
     */
    public static ArrayList<Integer> checkNameHash(String searchName) {
        ArrayList<Integer> theIndex = new ArrayList<>();
        ArrayList<Integer> tempIndex;
        int length;
        int i;
        String words[];
        
        words = searchName.split(" ");
        length = words.length;
        
        //search failed if key is not present
        if(map.containsKey(words[0].toLowerCase()) == false) {
            theIndex.clear();
            return theIndex;
        }
        
        else{
            theIndex = new ArrayList<Integer>(map.get(words[0].toLowerCase()));
        }
        //return indexes
        if(length == 1)
            return theIndex;
        else {
            for(i = 1; i<length; i++) {
                //search faild
                if(map.containsKey(words[i].toLowerCase()) == false) {
                    theIndex.clear();
                    return theIndex;
                }
                else {
                    tempIndex = new ArrayList<Integer>(map.get(words[i].toLowerCase()));
                    theIndex = intersection(theIndex, tempIndex);
                }  
            }
            return theIndex;
        }   
    } 
    
    /**
     * Compares two Integer ArrayLists and returns the common numbers as a 
     * new ArrayList.
     * @param <Integer> Type of the ArrayList object
     * @param list1 ArrayList representing a list of indexes as integers
     * @param list2 ArrayList representing a list of indexes as integers
     * @return ArrayList that contains the common elements of a list. Empty if nothing common.
     */
    public static <Integer> ArrayList<Integer> intersection(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> list = new ArrayList<>();

        //compare lists
        for(Integer t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            } else {
            }
        }

        return list;
    }
}


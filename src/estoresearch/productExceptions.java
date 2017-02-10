/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estoresearch;

/**
 * A list of exceptions that will be called if the user inputs an improper kind of data
 * @author Connor
 */
public class productExceptions {
    /**
     * A class for checking ID
     */
    protected class ImproperIDException extends Exception {
        /**
         * thrown if ID is not correctly formatted
         * @param e String representing a message
         */
        protected ImproperIDException(String e) {
            super(e);
        }
    }
    
    /**
     * A class for checking ID repeats
     */
    protected class RepeatIDException extends Exception {
        /**
         * thrown if ID is a repeat
         * @param e String representing a message
         */
        protected RepeatIDException(String e) {
            super(e);
        }
    }
    
    /**
     * A class for checking that all required input has been entered
     */
    protected class RequiredInputException extends Exception {
        /**
         * thrown if all required input has not been provided
         * @param e String representing a message
         */
        protected RequiredInputException(String e) {
            super(e);
        }
    }
    
    /**
     * A class for checking that the year format is proper
     */
    protected class InvalidYearException extends Exception {
        /**
         * thrown if the year does not have the correct format
         * @param e String representing a message
         */
        protected InvalidYearException(String e) {
            super(e);
        }
    }
    
    /**
     * A class for checking if the price is properly formatted
     */
    protected class InvalidPriceException extends Exception {
        /**
         * thrown if the price does not have the correct format
         * @param e String representing a message
         */
        protected InvalidPriceException(String e) {
            super(e);
        }
    }
}

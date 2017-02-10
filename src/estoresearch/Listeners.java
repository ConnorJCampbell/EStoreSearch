/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estoresearch;

import javax.swing.*;
import java.awt.event.*;


/**
 * Listeners is a class of ActionListeners that execute various methods throughout
 * the program based on the button that a user clicks on in the GUI
 * @author Connor
 */
public class Listeners {
    
    /**
     * Action to switch to the book entry interface
     */
    protected class RevealBook implements ActionListener {
        /**
         * Swaps the GUI to the book entry interface
         * @param e representing the action performed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            StoreGUI.eraseFields();
            StoreGUI.switchCard("Book");
        }
    }
    
    /**
     * Action to switch to the Electronics entry interface
     */
    protected class RevealElectronics implements ActionListener {
        /**
         * Swaps the GUI to the Electronics entry interface
         * @param e representing the action performed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            StoreGUI.eraseFields();
            StoreGUI.switchCard("Electronics");
        }
    }
    
    /**
     * Action to switch to the search entry interface
     */
    protected class RevealSearch implements ActionListener {
        /**
         * Swaps the GUI to the Search entry interface
         * @param e representing the action performed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            StoreGUI.eraseFields();
            StoreGUI.switchCard("Search");
        }
    }
    
    /**
     * Action to exit the program
     */
    protected class ExitProg implements ActionListener {
        /**
         * Prints the current list to a file and then exits the program
         * @param e representing the action performed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductFunctions.printToFile();
        }
    }
    
    /**
     * Action to add a Book to the list
     */
    protected class AddBook implements ActionListener {
        /**
         * Executes a method that attempts to add a Book to the list
         * @param ae representing the action performed
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
                StoreGUI.createBook();
        }
    }
    
    /**
     * Action to add an electronic to the list
     */
    protected class AddElectronics implements ActionListener {
        /**
         * Executes a method that attempts to add an Electronic to the list
         * @param e representing the action performed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            
                StoreGUI.createElec();
            
        }
    }
    
    /**
     * Action to reset all text input fields
     */
    protected class ResetFields implements ActionListener {
        /**
         * Executes a method that erases all of the current user input
         * @param e representing the action performed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            StoreGUI.eraseFields();
        }
    }
    
    /**
     * Action to search through the list
     */
    protected class SearchList implements ActionListener {
        /**
         * Executes a method that attempts to search the list
         * @param e representing the action performed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            StoreGUI.runSearch();
        }
    }
    
    /**
     * Action to switch between Book and Electronics interfaces
     */
    protected class SwapProducts implements ActionListener {
        /**
         * Switches between the Electronics and Book interface. Also swaps the
         * label in the JComboBox
         * @param e representing the action performed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            StoreGUI.eraseFields();
            JComboBox cb = (JComboBox)e.getSource();
            String path = (String)cb.getSelectedItem();
            if(path.equals("Book")) {
                StoreGUI.switchCard("Book");
            }
            else if(path.equals("Electronics"))
                StoreGUI.switchCard("Electronics");
        }
    }
    
    /**
     * Action to execute proper exiting procedures with the window is closed
     */
    protected class CloseProgram implements WindowListener {
        /**
         * Does not do anything
         * @param e representing the action performed
         */
        @Override
        public void windowOpened(WindowEvent e)
        {}
        /**
         * Prints the current list to a file and then exits the program
         * @param e representing the action performed
         */
        @Override
        public void windowClosing(WindowEvent e)
        {
            ProductFunctions.printToFile();
        }
        /**
         * Does not do anything
         * @param e representing the action performed
         */
        @Override
        public void windowDeactivated(WindowEvent e)
        {}
        /**
         * Does not do anything
         * @param e representing the action performed
         */
        @Override
        public void windowClosed(WindowEvent e)
        {}
        /**
         * Does not do anything
         * @param e representing the action performed
         */
        @Override
        public void windowIconified(WindowEvent e)
        {}
        /**
         * Does not do anything
         * @param e representing the action performed
         */
        @Override
        public void windowDeiconified(WindowEvent e)
        {}
        /**
         * Does not do anything
         * @param e representing the action performed
         */
        @Override
        public void windowActivated(WindowEvent e)
        {}
    }
}

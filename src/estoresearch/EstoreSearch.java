/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estoresearch;

/**
 * EstoreSearch creates a list of products and allows the user to freely search and
 * add to the list through a GUI. The contents of the list are printed to a file at the end of the
 * program so the list can be loaded back in when the program is next executed.
 * @author Connor
 */
public class EstoreSearch {

    private static String filename;
    
    protected static String getFilename() {
        return filename;
    }

    
    /**
     * The main function contains the interface for the user, which allows 
     * them to add new products and search the list
     * @param args the name of the file containing the list
     */
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Improper number of command line arguements. Exiting program.");
            System.exit(0);
        }
        filename = args[0];
        
        StoreGUI store = new StoreGUI();
    } 
}

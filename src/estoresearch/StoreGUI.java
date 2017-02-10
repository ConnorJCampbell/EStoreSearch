/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estoresearch;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * StoreGUI is a class used to create a GUI for the user to interact with when
 * manipulating the list of products
 * @author Connor
 */
public class StoreGUI extends JFrame{
    
    //main window
    private static JFrame mainFrame;
    private static JPanel mainPanel, cards;
    
    
    //command menu
    private static JMenu command;
    private static JMenuBar menuBar;
    private static JMenuItem add, search, exit;
    
    //Card Layout components
    private static JPanel bookCard, eCard, searchCard, welcomeCard;
    private final static String[] list = {"Book", "Electronics"};
    
    //components for Welcome Panel within card
    private static JLabel welcome;
    
    //components for add Book Panel within card
    private static JLabel bTypeLabel, bIDLabel, bNameLabel, bYearLabel, bPriceLabel, bAuthorLabel, bPublisherLabel, bTitle;				//There can only be 1 Component per Panel
    private static JTextField bIDField, bNameField, bYearField, bPriceField, bAuthorField, bPublisherField;
    private static JComboBox bTypeBox;
    private static JPanel bAddandReset, bInputPanel, bTextPanel, bTop, bExtra, bOutputPanel;
    private static JButton bAddButton, bResetButton;
    private static TitledBorder bOutTitle;
    private static JTextArea bOutput;
    
    //components for add Electronic Panel within card
    private static JLabel eTypeLabel, eIDLabel, eNameLabel, eYearLabel, ePriceLabel, eMakerLabel, eTitle;				//There can only be 1 Component per Panel
    private static JTextField eIDField, eNameField, eYearField, ePriceField, eMakerField;
    private static JComboBox eTypeBox;
    private static JPanel eAddandReset, eInputPanel, eTextPanel, eTop, eExtra, eOutputPanel;
    private static JButton eAddButton, eResetButton;
    private static TitledBorder eOutTitle;
    private static JTextArea eOutput;
    
    
    //components for searching Panel within card
    private static JLabel sIDLabel,sNameLabel, sYearLabel1, sYearLabel2, sTitle;				//There can only be 1 Component per Panel
    private static JTextField sIDField, sNameField, sYearField1, sYearField2;
    private static JPanel sSearchandReset, sInputPanel, sTextPanel, sTop, sExtra, sOutputPanel;
    private static JButton sSearchButton, sResetButton;
    private static TitledBorder sOutTitle;
    private static JTextArea sOutput;
    
    //listener for adding actions to buttons
    Listeners listener = new Listeners();
    
    /**
     * Default Constructor for initializing the GUI, calls on many functions
     * to initialize GUI components
     */
    protected StoreGUI() {
        super();
        ProductFunctions.scanFile();
        initMenuBar();
        createOutput();
        createCards();
        createWindow();
    }
    
    /**
     * Creates the main window of the the GUI, which contains the main menu bar and
     * all the cards for the different menus
     */
    private void createWindow() {
        mainFrame = new JFrame("EStoreSearch");
        mainFrame.setSize(700,620);
        //mainFrame.setResizable(false);
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setResizable(false);	
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(listener.new CloseProgram());
        mainFrame.setLayout(new FlowLayout());
        
        //add card menus and change to the wlecome message
        mainPanel = new JPanel();
        mainPanel.add(cards);
        switchCard("Welcome Panel");
        
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }
    
    /**
     * Initializes the menu bar that will be added to the main window for accessing
     * the different components of the program
     */
    private void initMenuBar() {
        
        //create main button
        menuBar = new JMenuBar();
        command = new JMenu("Commands");
        menuBar.add(command);
        
        //add in the drop-down options
        add    = new JMenuItem("Add");
        search = new JMenuItem("Search");
        exit   = new JMenuItem("Quit");
        command.add(add);
        command.add(search);
        command.add(exit);
        
        //add actions to each drop-down button
        add.addActionListener(listener.new RevealBook());
        search.addActionListener(listener.new RevealSearch());
        exit.addActionListener(listener.new ExitProg());     
    }
    
    /**
     * Initializes the card panel and calls on other functions to create each
     * individual card so they can all be added
     */
    private void createCards() {
        cards = new JPanel();
        cards.setLayout(new CardLayout());
        
        //create each card
        createWelcomeCard();
        createBookCard();
        createElectronicsCard();
        createSearchCard();
        
        //add new cards to the card panel
        cards.add(welcomeCard, "Welcome");
        cards.add(bookCard, "Book");
        cards.add(eCard, "Electronics");
        cards.add(searchCard, "Search");
    }
    
    /**
     * Creates each of the output windows for book, electronics and search.
     */
    private void createOutput() {
        //create components of book panel
        bOutputPanel = new JPanel();
        bOutput = new JTextArea(20, 50);
        bOutTitle = new TitledBorder("Messages");
        
        //edit properties
        bOutput.setLineWrap(false);
        bOutput.setEditable(false);
        JScrollPane scroll = new JScrollPane(bOutput);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        //add in the properties.
        bOutputPanel.setBorder(bOutTitle);
        bOutputPanel.add(scroll);
           
        
        //create components of electronics panel
        eOutputPanel = new JPanel();
        eOutput = new JTextArea(20, 50);
        eOutTitle = new TitledBorder("Messages");
        
        //edit properties
        eOutput.setLineWrap(false);
        eOutput.setEditable(false);
        JScrollPane scroll2 = new JScrollPane(eOutput);
        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        //add in the properties.
        eOutputPanel.setBorder(eOutTitle);
        eOutputPanel.add(scroll2);
        
        
        //create components of search panel
        sOutputPanel = new JPanel();
        sOutput = new JTextArea(20, 50);
        sOutTitle = new TitledBorder("Search results");
        
        //edit properties
        sOutput.setLineWrap(false);
        sOutput.setEditable(false);
        JScrollPane scroll3 = new JScrollPane(sOutput);
        scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        //add in the properties.
        sOutputPanel.setBorder(sOutTitle);
        sOutputPanel.add(scroll3);
        
    }
    
    /**
     * Creates the card for the initial screen that is displayed to the user when the program is first run.
     * Provides instructions on how to use the program.
     */
    private void createWelcomeCard() {
        welcomeCard = new JPanel();
        welcome = new JLabel("<html>Welcome to  the eStore.<br><br>Choose a command from the" 
                + " \"Commands\" menu above for adding a product, searching " + 
                "products,<br>or quitting the program.<html>");
        welcome.setBorder(new EmptyBorder(0,20,0,0));
        welcomeCard.add(welcome);
    }
    
    /**
     * Creates the card that contains the interface for adding a book product to the list
     */
    private void createBookCard() {      
        //set layouts for various panels
        bookCard = new JPanel();
        bInputPanel = new JPanel();
        bTextPanel = new JPanel();
        bAddandReset = new JPanel();
        bExtra = new JPanel();
        bAddandReset.setLayout(new BoxLayout(bAddandReset, BoxLayout.Y_AXIS));
        bInputPanel.setLayout(new FlowLayout(30, 100, 5));
        bTextPanel.setLayout(new GridBagLayout());		
        
        GridBagConstraints g = new GridBagConstraints();
        
        //type
        bTypeLabel = new JLabel("Type: ");
        bTypeBox = new JComboBox(list);
        bTypeBox.setSelectedIndex(0);
        bTypeBox.addActionListener(listener.new SwapProducts());
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 0;                    //1st row
        bTextPanel.add(bTypeLabel, g);										
        g.gridx = 1;                    //text field, column 2
        g.gridy = 0;                    //1st row
        bTextPanel.add(bTypeBox, g);
        
        
        //ID
        bIDLabel = new JLabel("Product ID: ");
        bIDLabel.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 1;                    //2nd row
        bTextPanel.add(bIDLabel, g);						
        bIDField = new JTextField(15);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 1;                    //2nd row
        bTextPanel.add(bIDField, g);
        
        //Name
        bNameLabel = new JLabel("Name: ");
        bNameLabel.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 2;                    //3rd row
        bTextPanel.add(bNameLabel, g);						
        bNameField = new JTextField(15);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 2;                    //3rd row
        bTextPanel.add(bNameField, g);
        
        //Price
        bPriceLabel = new JLabel("Price: ");
        bPriceLabel.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 3;                    //4th row
        bTextPanel.add(bPriceLabel, g);						
        bPriceField = new JTextField(15);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 3;                    //4th row
        bTextPanel.add(bPriceField, g);
        
        
        //Year
        bYearLabel = new JLabel("Year: ");
        bYearLabel.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 4;                    //5th row
        bTextPanel.add(bYearLabel, g);						
        bYearField = new JTextField(15);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 4;                    //5th row
        bTextPanel.add(bYearField, g);
        
        //Authour
        bAuthorLabel = new JLabel("Authors: ");
        bAuthorLabel.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 5;                    //6th row
        bTextPanel.add(bAuthorLabel, g);						
        bAuthorField = new JTextField(15);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 5;                    //6th row
        bTextPanel.add(bAuthorField, g);
        
        //Publisher
        bPublisherLabel = new JLabel("Publisher: ");
        bPublisherLabel.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 6;                    //7th row
        bTextPanel.add(bPublisherLabel, g);						
        bPublisherField = new JTextField(15);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 6;                    //7th row
        bTextPanel.add(bPublisherField, g);
        
        //Add and Reset Buttons
        bAddButton = new JButton("Add");
        //bAddButton.setPreferredSize(new Dimension(200, 20));
        bAddButton.addActionListener(listener.new AddBook());
        bAddandReset.add(bAddButton);
        bAddandReset.add(Box.createRigidArea(new Dimension (0,40)));
        bResetButton = new JButton("Reset");
       // bResetButton.setPreferredSize(new Dimension(200, 20));
        bResetButton.addActionListener(listener.new ResetFields());
        bAddandReset.add(bResetButton);
        
        //combine the two input panels
        bInputPanel.add(bTextPanel);
        bInputPanel.add(bAddandReset);
        
        //create the title that describes the current window
        bTop = new JPanel(new GridBagLayout());
        g.anchor = GridBagConstraints.NORTHWEST;
        g.fill = GridBagConstraints.NONE;		
        g.gridx = 0;						
        g.gridy = 0;
        bTitle = new JLabel("Adding a product");
        bTop.add(bTitle, g);
        
        g.gridx = 0;
        g.gridy = 1;
        bTop.add(bInputPanel, g);
        
        //add output for text
        g.gridx = 0;
        g.gridy = 2;
        bTop.add(bOutputPanel, g);
        
        //combine everything into the final window
        bExtra.add(bTop, BorderLayout.PAGE_START);
        bookCard.add(bExtra, BorderLayout.LINE_START);    
    }
    
    /**
     * Creates the card that contains the interface for adding an electronic product to the list
     */
    private void createElectronicsCard () {
        //set layouts for various panels
        eCard = new JPanel();
        eInputPanel = new JPanel();
        eTextPanel = new JPanel();
        eAddandReset = new JPanel();
        eExtra = new JPanel();
        eAddandReset.setLayout(new BoxLayout(eAddandReset, BoxLayout.Y_AXIS));
        eInputPanel.setLayout(new FlowLayout(30, 100, 5));
        eTextPanel.setLayout(new GridBagLayout());		
        
        GridBagConstraints g = new GridBagConstraints();
        
        //type
        eTypeLabel = new JLabel("Type: ");
        eTypeBox = new JComboBox(list);
        eTypeBox.setSelectedIndex(1);
        eTypeBox.addActionListener(listener.new SwapProducts());
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 0;                    //1st row
        eTextPanel.add(eTypeLabel, g);										
        g.gridx = 1;                    //text field, column 2
        g.gridy = 0;                    //1st row
        eTextPanel.add(eTypeBox, g);
        
        //ID
        eIDLabel = new JLabel("Product ID: ");
        eIDLabel.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 1;                    //2nd row
        eTextPanel.add(eIDLabel, g);						
        eIDField = new JTextField(15);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 1;                    //2nd row
        eTextPanel.add(eIDField, g);
        
        //Name
        eNameLabel = new JLabel("Name: ");
        eNameLabel.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 2;                    //3rd row
        eTextPanel.add(eNameLabel, g);						
        eNameField = new JTextField(15);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 2;                    //3rd row
        eTextPanel.add(eNameField, g);
        
        //Price
        ePriceLabel = new JLabel("Price: ");
        ePriceLabel.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 3;                    //4th row
        eTextPanel.add(ePriceLabel, g);						
        ePriceField = new JTextField(15);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 3;                    //4th row
        eTextPanel.add(ePriceField, g);
        
        
        //Year
        eYearLabel = new JLabel("Year: ");
        eYearLabel.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 4;                    //5th row
        eTextPanel.add(eYearLabel, g);						
        eYearField = new JTextField(15);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 4;                    //5th row
        eTextPanel.add(eYearField, g);
        
        //Maker
        eMakerLabel = new JLabel("Maker: ");
        eMakerLabel.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 5;                    //6th row
        eTextPanel.add(eMakerLabel, g);						
        eMakerField = new JTextField(15);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 5;                    //6th row
        eTextPanel.add(eMakerField, g);
        
        //Add and Reset Buttons
        eAddButton = new JButton("Add");
        //eAddButton.setPreferredSize(new Dimension(200, 20));
        eAddButton.addActionListener(listener.new AddElectronics());
        eAddandReset.add(eAddButton);
        eAddandReset.add(Box.createRigidArea(new Dimension (0,40)));
        eResetButton = new JButton("Reset");
        //eResetButton.setPreferredSize(new Dimension(200, 20));
        eResetButton.addActionListener(listener.new ResetFields());
        eAddandReset.add(eResetButton);
        
        //combine inputs
        eInputPanel.add(eTextPanel);
        eInputPanel.add(eAddandReset);
        
        //add title
        eTop = new JPanel(new GridBagLayout());
        g.anchor = GridBagConstraints.NORTHWEST;
        g.fill = GridBagConstraints.NONE;		
        g.gridx = 0;						
        g.gridy = 0;
        eTitle = new JLabel("Adding a product");
        eTop.add(eTitle, g);
        
        g.gridx = 0;
        g.gridy = 1;
        eTop.add(eInputPanel, g);
        
        //output
        g.gridx = 0;
        g.gridy = 2;
        eTop.add(eOutputPanel, g);
        
        //finalize
        eExtra.add(eTop, BorderLayout.PAGE_START);
        eCard.add(eExtra, BorderLayout.LINE_START); 
    }
    
     /**
     * Creates the card that contains the interface for searching for a product in the list
     */
    private void createSearchCard () {
        //set layouts for various panels
        searchCard = new JPanel();
        sInputPanel = new JPanel();
        sTextPanel = new JPanel();
        sSearchandReset = new JPanel();
        sExtra = new JPanel();
        sSearchandReset.setLayout(new BoxLayout(sSearchandReset, BoxLayout.Y_AXIS));
        sInputPanel.setLayout(new FlowLayout(30, 100, 5));
        sTextPanel.setLayout(new GridBagLayout());		
        
        GridBagConstraints g = new GridBagConstraints();
        
        //ID
        sIDLabel = new JLabel("Product ID: ");
        sIDLabel.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 1;                    //2nd row
        sTextPanel.add(sIDLabel, g);						
        sIDField = new JTextField(15);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 1;                    //2nd row
        sTextPanel.add(sIDField, g);
        
        //Name
        sNameLabel = new JLabel("Name Keywords: ");
        sNameLabel.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 2;                    //3rd row
        sTextPanel.add(sNameLabel, g);						
        sNameField = new JTextField(15);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 2;                    //3rd row
        sTextPanel.add(sNameField, g);
        
        //Year1
        sYearLabel1 = new JLabel("Start Year: ");
        sYearLabel1.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 3;                    //4th row
        sTextPanel.add(sYearLabel1, g);						
        sYearField1 = new JTextField(5);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 3;                    //4th row
        sTextPanel.add(sYearField1, g);
        
        //Year2
        sYearLabel2 = new JLabel("End Year: ");
        sYearLabel2.setBorder(new EmptyBorder(0,0,0,5));
        g.fill = GridBagConstraints.HORIZONTAL;					
        g.gridx = 0;			//label, column 1
        g.gridy = 4;                    //4th row
        sTextPanel.add(sYearLabel2, g);						
        sYearField2 = new JTextField(5);				
        g.gridx = 1;                    //text field, column 2
        g.gridy = 4;                    //4th row
        sTextPanel.add(sYearField2, g);
        
        //Add and Reset Buttons
        sSearchButton = new JButton("Search");
        //eAddButton.setPreferredSize(new Dimension(200, 20));
        sSearchButton.addActionListener(listener.new SearchList());
        sSearchandReset.add(sSearchButton);
        sSearchandReset.add(Box.createRigidArea(new Dimension (0,40)));
        sResetButton = new JButton("Reset");
        //eResetButton.setPreferredSize(new Dimension(200, 20));
        sResetButton.addActionListener(listener.new ResetFields());
        sSearchandReset.add(sResetButton);
        
        //combine input panels
        sInputPanel.add(sTextPanel);
        sInputPanel.add(sSearchandReset);
        
        //assign the title
        sTop = new JPanel(new GridBagLayout());
        g.anchor = GridBagConstraints.NORTHWEST;
        g.fill = GridBagConstraints.NONE;		
        g.gridx = 0;						
        g.gridy = 0;
        sTitle = new JLabel("Searching products");
        sTop.add(sTitle, g);
        
        g.gridx = 0;
        g.gridy = 1;
        sTop.add(sInputPanel, g);
        
        g.gridx = 0;
        g.gridy = 2;
        sTop.add(sOutputPanel, g);
        
        sExtra.add(sTop, BorderLayout.PAGE_START);
        searchCard.add(sExtra, BorderLayout.LINE_START); 
    }
    
    /**
     * takes a string representing a key and uses it to switch to a different interface
     * created through reference to Irenaeus Chan's example code
     * @param s representing which card should be switched to
     */
    protected static void switchCard(String s) {
        CardLayout layout = (CardLayout)(cards.getLayout());
        layout.show(cards, s);  
        if(s.equals("Book"))
            bTypeBox.setSelectedIndex(0);
        else if(s.equals("Electronics"))
            eTypeBox.setSelectedIndex(1);
    }
    
    /**
     * method that is called on by a listener to create a new book object and add it to the list
     */
    protected static void createBook() {
        boolean result;
        result = ProductFunctions.addBook(bIDField.getText(), bNameField.getText(), bYearField.getText(), bPriceField.getText(), bAuthorField.getText(), bPublisherField.getText());
        if(result)
            bOutput.append("A new Book was added to the list.\n\n");
        else
            bOutput.append("A Book was NOT added to the list.\n\n");
    }
    
    /**
     * method that is called on by a listener to create a new Electronic and add it to the list
     */
    protected static void createElec() {
        boolean result;
        result = ProductFunctions.addElec(eIDField.getText(), eNameField.getText(), eYearField.getText(), ePriceField.getText(), eMakerField.getText());
        if(result)
            eOutput.append("A new Electronic was added to the list.\n\n");
        else
            eOutput.append("A Electronic was NOT added to the list.\n\n");
    }
    
    /**
     * A method that is called on by a listener to check the input for search parameters
     * and then perform a search based on them.
     */
    protected static void runSearch() {
        boolean result;
        result = ProductFunctions.checkSearch(sIDField.getText(), sNameField.getText(), sYearField1.getText(), sYearField2.getText());
        if(result)
            ProductFunctions.search(sIDField.getText(), sNameField.getText(), sYearField1.getText(), sYearField2.getText());
    }
    
    /**
     * A method for writing to the book interface text box
     * @param s String representing the message to be printed
     */
    protected static void appendBook(String s){
	bOutput.append(s);
    }
    
    /**
     * A method for writing to the electronic interface text box
     * @param s String representing the message to be printed
     */
    protected static void appendElec(String s){
        eOutput.append(s);
    }
    
    /**
     * A method for writing to the search interface text box
     * @param s String representing the message to be printed
     */
    protected static void appendSearch(String s){
        sOutput.append(s);
    }

    /**
     * A method that simply erased all text entered into the input fields.
     */
    protected static void eraseFields(){
        bIDField.setText("");
        bNameField.setText("");
        bYearField.setText("");
        bPriceField.setText("");
        bAuthorField.setText("");
        bPublisherField.setText("");
        eIDField.setText("");
        eNameField.setText("");
        eYearField.setText("");
        ePriceField.setText("");
        eMakerField.setText("");
        sIDField.setText("");
        sNameField.setText("");
        sYearField1.setText("");
        sYearField2.setText("");         
    }
    
}

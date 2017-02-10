# EStoreSearch

*****
The General Problem
*****
  - To create an electronic store for kepping inventory on books and electronics. They should 
    be stored in a single single list that can be searched efficiently using a hashmap, and 
    the user should be able to both add to the lists and search them. The user should be able
    to do all of this through an input-friendly GUI. The program also outputs list to a text
    file so that the contents can be reloaded the next time it runs.

*****	
The assumptions and limitations of my solution:
*****
  - This program can only run properly assuming the intitial text file is in the correct format,
    which is very similar to the example in the outline. The included text file "products.txt" 
    has the correct format.
		NOTE: Unlike in the outline, names that have a quotation do not need a backslash in front
			  of the quotation.
		NOTE: Strings/names that are empty must have a space in between the quotations in the text
			  file. They are treated as empty in the program, but for the purpose of parsing they
			  must have a space. E.g. author = " " NOT author = ""
  - Probably the biggest assumption I made in this program was the functionality of 
    all the different kinds of cases. There are so many different types and amounts of
    input that I didn't have time to test it all. And even if I did, I'm not sure I
    could even know for sure that every aspect of the program was functioning properly.
	
*****	
How can the user build and test this program:
*****
  - Load the provided files into NetBeans.
  - Go to Run>Set Project Configuration> Customize and enter the name
    of the input file into the arguements field. 
  - The text file must be located in tbe root folder of the project,
    otherwsie a path name must be specified.
  - The provided text file "products.txt" is already in the correct format for the program.
  - After that simply follow the prompts in the GUI.

*****  
Test Plan:
*****
- Testing for file I/O
  - Tested having no command line aruguments/too many
  - Tested if the provided file didn't exist. If not, the program exits.
  - Tested to see if the program would continue correctly if the file was empty.
  - Tested to make sure a product in the text file was not added to the list if its ID was a 
		  duplicate.
  - Tetsed each line to see if it was empty or if it didn't contain the delimiting character (")
  - Checked the file when the program exits to make sure it was updated with the new list.
  - Checked to make sure empty Strings were printed with a space, and then converted back to
		  empty when scanned back in.
  
- Testing the main menu
  - Tested that case insensitive verions of add, search, and quit worked.
  - Test that all initial letters q, s, and a worked.
  - tested to make sure any other letters, numbers or characters were rejected, eg !, EXIT, 124
		
- Testing the "Add" option
  - Collecting ID: 
    - Tested that the user could not enter more or less than 6 digits, eg 1234567
    - Tested that the user cannot enter nothing.
    - Tested that the user cannot enter a number that already exists.
    - Tested that the user cannot enter negative numbers, eg -12345
  - Collecting Name: 
    - Tested to make sure that the user at least entered something
  - Collecting Year: 
    - Tested to make sure that the user can't enter negative number eg -1999
		- Tested entering noting to make sure it failed
		- Tried entering a number that was less than 1000 or greater than 9999 to make sure it failed, eg 500
		- Tried entering characters and letters other than numbers.
  - Collecting Price: 
    - Tested that the user is able to enter nothing.
    - Tested that the user cannot enter negative numbers, eg -100
    - Tested that the user cannot enter anything other than numbers and a single decimal. If they do enter a decimal, it must be immediately followed by two numbers, eg 234rt or 1234.3491204145
   
   - No error checking for author, publisher or maker.

- Testing the "Search" option
	- Tested entering all parameters at once, as well as none to make sure nothing happened.
	- Tested to make sure the program accepted nothing for ID, name, and year.
	- Made sure the program did not accept anything other than positive numbers for ID, and that it could only be six digits long.
	- checked to make sure year was between 1000 to 9999 and it contained no more than a single hyphen.
	- There weren't any tests for name.
	- Checked the output to make sure it displayed the proper products given the provided search parameters.
	- Tested by searching for items that did exists, as well as items that did not.
		  
- Testing the GUI
		- Tested all the options in the JMenuBar to make sure they revealed the appropriate screen,
                  for example selecting "Search" opens the corresponding search interface
		- Tested that both the "Exit" and the top-right "X" button properly exited the program and
		  saved the list into the command line file.
		- Tested to see that when the program was first run, the welcome message was properly printed.
		- Tested to make sure that the output for book and electronics was accurately displaying error
		  warnings, and that the output for search was accurately displaying search results.
		- Tested all reset buttons to make sure they removed currently entered text, but did not affec
		  the output boxes.
		- Checked that the add and search buttons were properly accepting the user input and executing
		  the desired methods.


- Tested "Quit" to make sure the program exited and the contents of the list were printed back into the input file in the proper format
    				
*****
What improvements could be done if there was extra time:
*****
  - If I could do this project again I may have tried adding even more modularity to my program
    to shorten the length.
  - Do more testing.
  
**********
References:
**********
- Referenced EstroeSearch code from my last assignment
- Refrenced Irenaeus Chan's Lab Assignment 5 example
- Referenced Professor Song's lecture slides "Swing 1" and "Swing 2"
- Referenced the intersection algorithm from http://stackoverflow.com/questions/5283047/intersection-and-union-of-arraylists-in-java


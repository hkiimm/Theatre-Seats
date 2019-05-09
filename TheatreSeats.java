/*
Hannah Kim
January 16, 2018
Ms. Krasteva
Seating plan for a theatre

This program will allow the users to buy tickets for a 20 by 30 theatre. This program will be saved until the user decides to reset the plan.

In the splashScreen () screen, the introduction to the program is shown, as well as a background colour and graphics. It tells the user what
the program will be doing.
In the mainMenu () screen, it asks the user if they would like to see the instructions, buy a ticket, view the seating plan, reset the seating plan, or
exit the program. Depending on the users choice, it takes them to a different screen that corresponds with their selection.
In the instructions () screen, the instructions on how to buy and view tickets are told to the user. It explains the components of the
program, to allow the user to have a smooth experience.
In the askData () screen, it asks the user for the row and aisle they would like to sit in. It also displays the seating plan for reference.
In the display () screen, it displays the users initial input of the seat as well as the price of the seat after tax.
In the viewPlan () screen, it asks the user for the seat their looking for. It tells the user whether that seat is available or not. If it is available,
it states the price of the seat before tax
In the final goodbye () screen, it thanks the user for using this program, and where to contact for more information about it.

In this program these are the rules you should follow.
1. Your inputs for the choices should be between 1 and 5, it should be on the list.
2. Your inputs for the seats should be row and then column.
3. Rows should be in upper cases, and columns should not exceed 30.
4. Do not enter the location of a seat that is unavailable. This seat has already been purchased.
5. If there are no more seats available or you don't want to buy that ticket anymore and you clicked to buy a ticket, press 'c'.

Instructions on how to use this volume calculating program.
1.  The splash screen will automatically take you to the main menu.
2.  Once you get to the main menu, you will have 5 options to choose from. Your options are instructions, buy tickets, view available seats and prices,
    reset seating plan, and exit.
3.  If you choose to go to instructions, it will display the isntructions of the program. You can press any key to go back to the main menu.
4.  If you choose to buy a ticket, the screen will ask you for the row and aisle you would like to sit in. Make sure to follow the rules above to
    avoid error messages.
5.  If you do enter an invalid input or an invalid seat, an error message will display and you will be asked to re-enter the input.
6.  After you have finished inputting your seats, you will have the option to cancel your transaction. To do this, press 'b'.
7.  Once it takes you to the display, the final price after tax will be shown. Press any key to return to the main menu.
8.  If you want to view the plan, the input is the same as when you buy a ticket.
9.  After you input your seat, you will either be told the price or that the seat is unavailable. Press any key to return to the main menu.
10. If you reset the seating plan, a message will pop up telling you the seating plan has been reset.
11. At the main menu, you have the option to go read the instructions again, buy more tickets, view the plan, reset the plan, or exit the program.
12. If you decide to exit the program, a goodbye screen will state where and who to contact for more information.
13. Lastly, press any key to close the program entirely.
14. If you decided to run the program again to buy more tickets, your previous entries will be saved.

Variable Name       Type        Description
???????????????????????????????????????????????????????
seatNum             int         This variable stores the user input of the column the user wants to sit in.
rowNum              int         This variable stores the array position of the row the user inputs.
seatRow             char        This variable stores the ascii value of the character the user inputs as the row.
key                 char        This variable checks if the user inputs 'c' to cancel their transaction or not.
choice              String      This variable stores what the user chose as their option between 1 and 5. It is used to determine which method to execute.
prices              double      This 2D array stores the prices of the theatre seats.
seatCheck           boolean     This 2D array stores whether the seat has been purhcased yet or not.
lastName            String      This 2D array stores the last name of the person that purhcased the ticket at that seat.
*/

import java.awt.*;
import java.io.*;
import hsa.*;

public class TheatreSeats
{
    Console c;

    //global variables
    int seatNum;
    int rowNum;
    char seatRow;
    char key;
    String choice;
    double[] [] prices = new double [30] [20];
    boolean[] [] seatCheck = new boolean [30] [20];
    String[] [] lastName = new String [30] [20];


    /*
    This method contains the title of the program.
    The clear screen is called before the title so whenever the title is called, the screen is cleared.
    */
    public void title ()
    {
	c.clear ();
	c.print (' ', 30);
	c.println ("Theatre Seating Plan");
	c.println ();
    }


    /*
    This method contains the introduction and graphics.
    - The first for loop draws out the seats
    - The second for loop only changes some of the seats to grey to draw a smiley face to welcome the user

    Variable Name       Type        Description
    ???????????????????????????????????????????????????????
    background          Color       This variable sets the colour of the background.
    seats               Color       This variable sets the colour of the seats.
    seatX               int[]       This variable stores the x coordinates of the seats that are unavailable.
    seatY               int[]       This variable stores the y coordinates of the seats that are unavailable.
    */
    public void splashScreen ()
    {
	//local variables
	int[] seatX = {283, 347, 251, 267, 283, 299, 315, 331, 347, 363, 379};
	int[] seatY = {289, 289, 321, 337, 353, 353, 353, 353, 353, 337, 321};

	//creates a variable and sets the color
	Color background = new Color (255, 234, 230);
	Color seats = new Color (210, 200, 220);

	//sets the background colour of the program
	c.setTextBackgroundColor (background);

	//sets the font
	c.setFont (new Font ("Times New Roman", 1, 20));

	title ();

	//introductory sentence
	c.println ("This program will allow you to buy tickets for a 20 by 30 theatre.");

	//graphics
	c.drawRect (220, 150, 200, 30); //rectangle outline around 'stage'
	c.drawString ("S T A G E", 279, 172); //stage title
	//layout of the seats
	c.setColor (seats);
	for (int y = 225 ; y < 450 ; y = y + 16)
	{
	    for (int x = 123 ; x < 515 ; x = x + 16)
	    {
		//draws out the boxes
		c.fillRect (0 + x, 0 + y, 13, 13);
	    }
	}

	//seats that are booked
	c.setColor (Color.gray);
	for (int x = 0 ; x < 11 ; x++)
	{
	    //colours the seats in the array grey
	    c.fillRect (seatX [x], seatY [x], 13, 13);
	    //delay
	    try
	    {
		Thread.sleep (500);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    /*
    This method reads the file so all the data saved inside the file is transferred back into the program.
    - There is a for loop that runs through all the data inside the file
    - The first if statement is for when "true" is read inside the file, which sets the coordinates after it to true
    - The else if statement is for when "false" is read inside the file, which sets the coordinates after it to false

    Variable Name       Type            Description
    ???????????????????????????????????????????????????????
    column              int             This variable stores the coordinate of the column (x).
    row                 int             This variable stores the coordinate of the row (y).
    tempC               String          This reads the line with the coordinate for the column, it stores the coordinate temporarily.
    tempR               String          This reads the line with the coordinate for the row, it stores the coordinate temporarily.
    temp                String          This variable stores the lines being read inside the file.
    input               BufferedReader  It is used to read from the file.
    */
    public void readFile ()
    {
	//local variables
	int column = 0;
	int row = 0;
	String tempC = "";
	String tempR = "";
	String temp = "";
	BufferedReader input;

	try
	{
	    input = new BufferedReader (new FileReader ("TheatreSeatingPlan.hk"));
	    //runs through the array
	    for (int i = 0 ; i < 1800 ; i++)
	    {
		//reads the file
		temp = input.readLine ();
		//once the file reads "true"
		if (temp.equals ("true"))
		{
		    //reads the column coordinate
		    tempC = input.readLine ();
		    //reads the row coordinate
		    tempR = input.readLine ();
		    column = Integer.parseInt (tempC);
		    row = Integer.parseInt (tempR);
		    //sets the array to true
		    seatCheck [column] [row] = true;
		}
		//once the file reads "false"
		else if (temp.equals ("false"))
		{
		    //reads the column coordinate
		    tempC = input.readLine ();
		    //reads the row coordinate
		    tempR = input.readLine ();
		    column = Integer.parseInt (tempC);
		    row = Integer.parseInt (tempR);
		    //sets the array to false
		    seatCheck [column] [row] = false;
		}
	    }
	}
	catch (IOException e)
	{
	}
    }


    /*
    This method is used to set the prices of the seats.
    - The first for loop runs through the x values of the array
    - The second for loop runs through the y values of the array
    - The first if statement is used to see how many times the loop hsa run, and if it has run 20 times it resets the values
    - The else is used to always add to the counter

    Variable Name       Type        Description
    ???????????????????????????????????????????????????????
    counter             int         This variable counts the number of times the for loop has ran.
    num                 int         This variable stores the value being assigned to the 2D price array.
    */
    public void setPrice ()
    {
	//local variables
	int counter = 1;
	int num = 200;

	//runs through the for loop of the prices array
	for (int x = 0 ; x < 30 ; x++)
	{
	    for (int y = 0 ; y < 20 ; y++)
	    {
		prices [x] [y] = num;
		num = num - 10; //subtract $10 every row

		//if the for loop has set the price for the entire column
		if (counter == 20)
		{
		    //counter and value resets
		    counter = 1;
		    num = 200;
		}
		else
		    counter++;
	    }
	}
    }


    /*
    This method draws out the seating plan that is shown when the user buys or views tickets.
    - The first for loop runs from 65 to 85 which are the ascii values for A - T
    - The second for loop runs from 1 to 31 which are the aisles of the theatre seating
    - The third for loop runs through the x values of the array
    - The fourth for loop runs through the y values of the array
    - The first if statement is used to check if the seat is false
    - The else if statement is used to check if the seat is true

    Variable Name       Type        Description
    ???????????????????????????????????????????????????????
    num                 int         This variable stores the y coordinate for the spacing of the letters.
    num1                int         This variable stores the x coordinate for the spacing of the numbers.
    row                 String      This variable stores the string of the letters, so it can be drawn.
    column              String      This variable stores the string of the numbers, so it can be drawn.
    */
    public void graphicPlan ()
    {
	//local variables
	int num = 190;
	int num1 = 81;
	String row = "";
	String column = "";

	//assigns a colour to the variable seats
	Color seats = new Color (210, 200, 220);

	//graphics
	//sets the font
	c.setFont (new Font ("Times New Roman", 1, 20));
	c.setColor (Color.black);
	c.drawRect (220, 120, 200, 30); //rectangle outline around 'stage'
	c.drawString ("S T A G E", 279, 142); //stage title
	//sets the font
	c.setFont (new Font ("Times New Roman", 1, 12));
	c.drawString ("available", 100, 142); //word beside the purple box
	c.drawString ("unavailable", 490, 142); //word beside the grey box
	c.setColor (seats);
	c.fillRect (80, 133, 13, 13); //box beside available
	c.setColor (Color.gray);
	c.fillRect (464, 133, 13, 13); //box beside unavailable
	//sets colour to black
	c.setColor (Color.black);

	//writes the row letters
	for (int i = 65 ; i < 85 ; i++)
	{
	    //converts ascii values to string
	    row = Character.toString ((char) i); //'Character.toString' learnt from the internet
	    c.drawString (row, 65, num);
	    num = num + 16; //spacing between each letter
	}

	//writes the column letters
	for (int i = 1 ; i < 31 ; i++)
	{
	    //converts integer values to string
	    column = Integer.toString ((int) i); //'Integer.toString' taken from 'Character.toString'
	    c.drawString (column, num1, 165);
	    num1 = num1 + 16; //spacing between each number
	}

	//layout of the seats
	for (int y = 0 ; y < 20 ; y++)
	{
	    for (int x = 0 ; x < 30 ; x++)
	    {
		//if the seat has not been purchased
		if (seatCheck [x] [y] == false)
		{
		    //colour is purple
		    c.setColor (seats);
		}
		//if the seat has been purchased
		else if (seatCheck [x] [y] == true)
		{
		    //colour is grey
		    c.setColor (Color.gray);
		}
		//draws the seat position
		c.fillRect (80 + (x * 16), 170 + (y * 16), 13, 13);
	    }
	}
    }


    /*
    This method asks the user which option they would like to choose.
    The program always comes back to the main menu, unless the user decides to exit.
    There is one while loop inside this method that asks the user which option they would like to choose.
    - Inside the while loop, there is an if statement that error traps wrong inputs.
    - If the input is not between 1 and 5, the while loop runs again and asks for the user input again.
    */
    public void mainMenu ()
    {
	title ();

	//options
	c.println ("1. Instructions");
	c.println ("2. Buy Tickets");
	c.println ("3. View Available Seats and Prices");
	c.println ("4. Reset the Seating Plan");
	c.println ("5. Exit");

	while (true)
	{
	    c.setCursor (9, 1);
	    //ask data for options
	    c.print ("Please enter one of the options above: ");
	    c.setCursor (9, 40);
	    c.println ();
	    c.setCursor (9, 40);
	    //gets user input
	    choice = c.readLine ();

	    if (!((choice.equals ("1")) || (choice.equals ("2")) || (choice.equals ("3")) || (choice.equals ("4")) || (choice.equals ("5"))))
		//error trap
		new Message ("Please enter an integer between 1 and 5");
	    else
		break;
	}
    }


    /*
    This method will output the instructions on how to use this program.
    A pause program is added at the end so the user has time to read the instructions.
    */
    public void instructions ()
    {
	title ();

	//instructions of the program
	c.println ("1. If you are ready to buy a ticket, click 2");
	c.println ("2. You will be asked to select the seats that you would like to purchase");
	c.println ("3. If the seat has already been purchased, it will be grey and unavailable");
	c.println ("4. You will be asked for the row and column you would like to sit in separately");
	c.println ("5. Press any key to continue, or press 'c' to cancel your purchase");
	c.println ("6. Once you decide to purchase your tickets, you will be asked to enter your");
	c.println ("   last name to store your tickets");
	c.println ("7. If you wish to view the seating plan, to see which seats are already");
	c.println ("   purchased and the prices of the seats, click 3");
	c.println ("8. If you would like to reset the seating plan, click 4");
	c.println ("9. Once you're done using this program, click 5");

	pauseProgram ();
    }


    /*
    This method asks the user for the row and aisle they want to sit in
    - The first while loop checks if the user inputs are not seats that are already taken
    - The second while loop is used to error trap the input for the row
    - The third while loop is used to error trap the input for the column
    - The if statements inside the second loop are used to error trap the input for the row
    - The if statements inside the third loop are used to error trap the input for the column
    - The if statements inside the first while loop are used to error trap if the seat is already purchased
    - The final if statement checks if the user has cancelled or not cancelled the transaction
	- The 2D array is only true if they procede to checkout

    Variable Name       Type        Description
    ???????????????????????????????????????????????????????
    seatstr              String     This variable gets the user input for the row number.
    seatNumStr           String     This variable gets the user input for the column number.
    */
    public void askData ()
    {
	title ();
	graphicPlan ();

	//local variables
	String seatstr = " ";
	String seatNumStr = " ";

	//this loop runs again untl the input is correct
	while (true)
	{
	    //this loop gets the row
	    while (true)
	    {
		//this gets the seatRow
		c.setCursor (3, 1);
		//ask data for enter data
		c.print ("Please enter the row you would like to sit in: ");
		c.setCursor (3, 48);
		c.println ();
		c.setCursor (3, 48);

		//gets user input
		seatstr = c.readLine ();
		seatRow = seatstr.charAt (0);

		if (seatstr.length () != 1)
		    //error trap
		    new Message ("That is not a valid row. Please enter another location!");
		else if (!((seatRow >= 65 && seatRow <= 84) || (seatRow >= 97 && seatRow <= 116)))
		    //error trap
		    new Message ("That is not a valid row. Please enter another location!");
		else if ((seatRow >= 97 && seatRow <= 116))
		    //error trap
		    new Message ("Please enter the capital letter.");
		else
		{
		    rowNum = seatRow - 65;
		    break;
		}
	    }

	    //this loop gets the aisle number
	    while (true)
	    {
		//this gets the seatNum
		c.setCursor (4, 1);
		//ask data for enter data
		c.print ("Please enter the aisle you would like to sit in: ");
		c.setCursor (4, 50);
		c.println ();
		c.setCursor (4, 50);

		try
		{
		    //gets user input
		    seatNumStr = c.readLine ();
		    seatNum = Integer.parseInt (seatNumStr);
		    if (seatNum <= 0)
			//error trap
			new Message ("That is not a valid aisle. Please enter another location!");
		    else if (seatNum > 30)
			//error trap
			new Message ("That is not a valid aisle. Please enter another location!");
		    else
			break;
		}
		catch (NumberFormatException e)  //this will catch any errors if the input is not parsable into an integer
		{
		    new Message ("Please enter a valid input"); //error message if the input is not parsable
		}
	    }

	    //error traps if the seat has already been purchased
	    if (seatCheck [seatNum - 1] [rowNum] == true)
	    {
		new Message ("This seat has already been purchased. Please enter another location!");
	    }
	    else
		break;
	}

	//if the user wants to go back to the main menu
	c.println ("Press 'c' to cancel the transaction or Press any key to continue");
	key = c.getChar ();


	//if they decide to cancel the transaction
	if (key != 'c')
	{
	    //sets the seats to true signifying that they are purchased and unavailable
	    seatCheck [seatNum - 1] [rowNum] = true;

	}
    }


    /*
    This method is a private method, so it will not display a screen.
    - It calculates the price of the seat multiplied by the tax

    Variable Name       Type        Description
    ???????????????????????????????????????????????????????
    finalOutput         double      This variable stores the price of the seat multiplied by the tax.
    TAX                 double      This variable is a constant that stores the tax (1.13).
    */
    private double seatPrice (double prices[] [], int seatNum, int rowNum)
    {
	//local variable
	double finalOutput = 0; //final price
	final double TAX = 1.13; //tax

	//final price is the price of the seat multiplied by tax
	finalOutput = prices [seatNum - 1] [rowNum] * TAX;

	return finalOutput;
    }


    /*
    This method displays the price and seat purchased.
    The calculations from the return method is used to display the final price.
    - The two for loops run through the x and y values of the array

    Variable Name       Type            Description
    ???????????????????????????????????????????????????????
    finalReturn         double          This variable stores the result from the return method so the price is two decimal places
    output              PrintWriter     It is used to write to the file.
    */
    public void display ()
    {
	title ();

	//local variables
	PrintWriter output;
	double finalReturn = 0;

	//tells the user their seats have been bought
	new Message ("Your seats were bought successfully!");

	//display total price
	finalReturn = seatPrice (prices, seatNum, rowNum);
	c.print ("Your total with tax comes to $");
	c.print (finalReturn, 0, 2); //price with 2 decimal places
	c.println (".");

	//this gets the users last name
	c.setCursor (5, 1);
	//ask data for enter data
	c.print ("Please enter your last name to save your seats: ");
	c.setCursor (5, 49);
	c.println ();
	c.setCursor (5, 49);

	//gets the last name
	lastName [seatNum - 1] [rowNum] = c.readLine ();

	try
	{
	    output = new PrintWriter (new FileWriter ("TheatreSeatingPlan.hk"));
	    //in the file runs through the array
	    for (int x = 0 ; x < 30 ; x++)
	    {
		for (int y = 0 ; y < 20 ; y++)
		{
		    //prints the last name
		    output.println (lastName [x] [y]);
		    //prints the price
		    output.println (prices [x] [y]);
		    //prints true or false
		    output.println (seatCheck [x] [y]);
		    //prints the x coordinate
		    output.println (x);
		    //prints the y coordinate
		    output.println (y);
		}
	    }
	    output.close ();
	}
	catch (IOException e)
	{
	}

	pauseProgram ();
    }


    /*
    This method allows the user to view the seating plan without purchasing them.
    - The first and second while loop runs until the user input is valid
    - The first if statements error trap the user input for the row
    - The second if statements error trap the user input for the column
    - The third set of if statements checks if the seat the user input has inputted is taken or not, and has different outputs for true and false
    - The for loops run through the x and y values of the array

    Variable Name       Type        Description
    ???????????????????????????????????????????????????????
    counter             int         This variable stores the number of total seats that are available.
    seatstr              String     This variable gets the user input for the row number.
    seatNumStr           String     This variable gets the user input for the column number.
    */
    public void viewPlan ()
    {
	title ();
	graphicPlan ();

	//local variables
	int counter = 0;
	String seatNumStr = " ";
	String seatstr = " ";

	//this loop gets the row
	while (true)
	{
	    //this gets the seatRow
	    c.setCursor (3, 1);
	    //ask data for enter data
	    c.print ("Please enter the row you are looking for: ");
	    c.setCursor (3, 43);
	    c.println ();
	    c.setCursor (3, 43);

	    //gets user input
	    seatstr = c.readLine ();
	    seatRow = seatstr.charAt (0);

	    if (seatstr.length () != 1)
		//error trap
		new Message ("That is not a valid row. Please enter another location!");
	    else if (!((seatRow >= 65 && seatRow <= 84) || (seatRow >= 97 && seatRow <= 116)))
		//error trap
		new Message ("That is not a valid row. Please enter another location!");
	    else if ((seatRow >= 97 && seatRow <= 116))
		//error trap
		new Message ("Please enter the capital letter.");
	    else
	    {
		rowNum = seatRow - 65;
		break;
	    }
	}

	//this loop gets the aisle number
	while (true)
	{
	    //this gets the seatNum
	    c.setCursor (4, 1);
	    //ask data for enter data
	    c.print ("Please enter the aisle you are looking for: ");
	    c.setCursor (4, 45);
	    c.println ();
	    c.setCursor (4, 45);

	    try
	    {
		//gets user input
		seatNumStr = c.readLine ();
		seatNum = Integer.parseInt (seatNumStr);
		if (seatNum <= 0)
		    //error trap
		    new Message ("That is not a valid aisle. Please enter another location!");
		else if (seatNum > 30)
		    //error trap
		    new Message ("That is not a valid aisle. Please enter another location!");
		else
		    break;
	    }
	    catch (NumberFormatException e)  //this will catch any errors if the input is not parsable into an integer
	    {
		new Message ("Please enter a valid input"); //error message if the input is not parsable
	    }
	}

	title ();

	//if the seat has been purchased
	if (seatCheck [seatNum - 1] [rowNum] == true)
	{
	    //displays that the seat is no longer available
	    c.println ("Seat " + seatRow + seatNum + " is no longer available.");
	}
	//if the seat has not been purchased yet
	else if (seatCheck [seatNum - 1] [rowNum] == false)
	{
	    //displays that the seat is available
	    c.println ("Seat " + seatRow + seatNum + " is available for purchase.");
	    //displays the price of the seat
	    c.print ("The subtotal is $");
	    c.print (prices [seatNum - 1] [rowNum], 0, 2);
	    c.println (".");
	}

	//runs through all the seats
	for (int x = 0 ; x < 30 ; x++)
	{
	    for (int y = 0 ; y < 20 ; y++)
	    {
		//if the seat has not been purchased yet
		if (seatCheck [x] [y] == false)
		    //adds to counter
		    counter++;
	    }
	}

	c.println ();
	//displays the number of seats available
	c.println ("There are " + counter + " more seats available for purchase.");

	pauseProgram ();
    }


    /*
    This method resets the plan so that all previous purchases are erased.
    - The two for loops runs through the x and y values of the array
    - All the variables that are written to the file are reset

    Variable Name       Type            Description
    ???????????????????????????????????????????????????????
    output              PrintWriter     It is used to write to the file.
    */
    public void resetPlan ()
    {
	//local variable
	PrintWriter output;

	try
	{
	    output = new PrintWriter (new FileWriter ("TheatreSeatingPlan.hk"));
	    //in the file runs through the array
	    for (int x = 0 ; x < 30 ; x++)
	    {
		for (int y = 0 ; y < 20 ; y++)
		{
		    //prints the last name
		    output.println (lastName [x] [y] = "");
		    //prints the price
		    output.println (prices [x] [y]);
		    //prints true or false
		    output.println (seatCheck [x] [y] = false);
		    //prints the x coordinate
		    output.println (x);
		    //prints the y coordinate
		    output.println (y);
		}
	    }
	    output.close ();
	}
	catch (IOException e)
	{
	}

	//tells the user that the seating plan has been reset
	new Message ("The seating plan has been reset.");
    }


    /*
    In this method, all the goodbye messages are displayed.
    The user is thanked, and contact information is displayed incase the user wants more information.
    A pause program is added at the end so the user has time to read the goodbye message.
    Once the user presses any key, the screen closes.
    - The first for loop draws out the seats
    - The second for loop only changes some of the seats to grey to draw a smiley face to welcome the user

    Variable Name       Type        Description
    ???????????????????????????????????????????????????????
    seats               Color       This variable sets the colour of the seats.
    seatX               int[]       This variable stores the x coordinates of the seats that are unavailable.
    seatY               int[]       This variable stores the y coordinates of the seats that are unavailable.
    */
    public void goodbye ()
    {
	//local variables
	int[] seatX = {283, 347, 266, 267, 283, 299, 315, 331, 347, 363, 363};
	int[] seatY = {293, 293, 357, 341, 325, 325, 325, 325, 325, 341, 357};

	//creates a variable and sets the color
	Color seats = new Color (210, 200, 220);
	
	//sets the font
	c.setFont (new Font ("Times New Roman", 1, 20));

	title ();

	//goodbye message and information of the programmer
	c.println ("Thank you for using this program! We hope to see you next time.");
	c.println ("For more information, contact hannah.seyun.kim@gmail.com");
	c.println ();
	c.println ("By: Hannah Kim");

	//graphics
	c.drawRect (220, 170, 200, 30); //rectangle outline around 'stage'
	c.drawString ("S T A G E", 279, 192); //stage title
	//layout of the seats
	c.setColor (seats);
	for (int y = 245 ; y < 470 ; y = y + 16)
	{
	    for (int x = 123 ; x < 515 ; x = x + 16)
	    {
		//draws out the boxes
		c.fillRect (0 + x, 0 + y, 13, 13);
	    }
	}

	//seats that are booked
	c.setColor (Color.gray);
	for (int x = 0 ; x < 11 ; x++)
	{
	    //colours the seats in the array grey
	    c.fillRect (seatX [x], seatY [x], 13, 13);
	    //delay
	    try
	    {
		Thread.sleep (500);
	    }
	    catch (Exception e)
	    {
	    }
	}

	pauseProgram ();

	//closes the screen
	c.close ();
    }


    /*
    This method is used to pause the program.
    This method is called in another method when you need the program to stop on a screen, since screens do not pause.
    */
    public void pauseProgram ()
    {
	c.println ();
	c.print ("Press any key to continue");
	c.getChar ();
    }


    public TheatreSeats ()
    {
	c = new Console ("Theatre Seating Plan");
    }



    public static void main (String[] args)
    {
	TheatreSeats t = new TheatreSeats ();
	t.splashScreen ();
	t.readFile ();
	t.setPrice ();
	while (true)
	{
	    t.mainMenu ();
	    if (t.choice.equals ("1"))
		t.instructions ();
	    else if (t.choice.equals ("2"))
	    {
		t.askData ();
		if (t.key == 'c')
		    new Message ("Your transaction has been cancelled. You will be redirected to the main menu.");
		else
		    t.display ();
	    }
	    else if (t.choice.equals ("3"))
		t.viewPlan ();
	    else if (t.choice.equals ("4"))
		t.resetPlan ();
	    else if (t.choice.equals ("5"))
		break;
	}


	t.goodbye ();
    }
}



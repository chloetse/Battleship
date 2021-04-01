import java.util.Scanner; //import statement

public class Board {

    //variables
    private char[][] myBoard;
    private char[][] mySolution;
    private int row;
    private int col;
    int numberOfMissilesFired;
    int numberOfSuccessfulMissiles;
    int xCoordinate;
    int yCoordinate;
    Ship[] ships;

    //constructor
    public Board() {
        row = 0;
        col = 0;
        numberOfMissilesFired = 0; //sets the number of missiles fired to 0
        numberOfSuccessfulMissiles = 0; //sets the number of successful missiles to 0
        myBoard = new char[10][10]; //ten by ten user's board
        mySolution = new char[10][10]; //ten by ten solution board
        fillBoard(myBoard); //fills the user's board
        fillBoard(mySolution); //fills the solution board
        //creates an array of object Ship with the different types of ships that need to be placed
        ships = new Ship[]{ new Aircraft(), new Battleship(), new Cruiser(), new Destroyers(), new Destroyers() };
        placeShips(mySolution);
    }

    //fills the 10 x 10 board with "."
    private void fillBoard(char[][] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '.';
            }
        }
    }

    //displays the user's board
    public void printBoard() {
        printBoard(myBoard);
    }

    //displays the solution board
    public void printSolution() {
        printBoard(mySolution);
    }

    private void printBoard(char[][] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /*
    places the different types of ships randomly onto the solution board
    demonstrates inheritance - the different types of ships place onto the board are the child classes, and they
    all inherit from the parent class Ship
    list of ships:
    1 x (a)ircraft carrier, size 5
    1 x (b)attleship, size 4
    1 x (c)ruiser, size 3
    2 x (d)estroyers, size 2
    */
    private void placeShips(char[][] board) {
        for (int i = 0; i < ships.length; i++) { //iterates through the ship array
            ships[i].placeOnBoard(board);
        }
    }

    /*
   asks a question that requires an integer input and then checks the validity of the input
   if the input is not a valid integer (eg. String, char), the program will not continue
   (hence, the while loop) until a valid integer is inputted
   this prevents the program from continuing with an incorrect input
   */
    private int getValidIntegerInput(Scanner inputS, String question) {
        System.out.print(question + " "); //prints the question the user must respond to
        while (!inputS.hasNextInt()) { //while the input is not a valid integer
            System.err.println("Error: not a valid integer input"); //print the error message of "invalid input"
            inputS.nextLine(); //keep waiting for input
        }
        return Integer.parseInt(inputS.nextLine()); //returns the valid input (may be set to a variable)
    }

    //same as getValidIntegerInput, but includes the minimum and maximum  value
    private int getValidIntegerInputInRange(Scanner inputS, String question, int minValue, int maxValue) {
        while (true) {
            int userInputValue = getValidIntegerInput(inputS, question);
            if (userInputValue < minValue || userInputValue > maxValue) {
                System.err.println("Error: input not within the valid range"); //prints the error message of "invalid input"
            } else {
                return userInputValue;
            }
        }

    }

    //uses the scanner to get the user's desired x-coordinate and y-coordinate
    public void userMove(Scanner inputS) {
        //the minimum and maximum value ensure that the user can only choose coordinates on the board
        xCoordinate = getValidIntegerInputInRange(inputS, "Choose your x-coordinate:", 0, 10);
        yCoordinate = getValidIntegerInputInRange(inputS, "Choose your y-coordinate:", 0, 10);
        //prints out the user's coordinates
        System.out.println("The coordinates you have chosen are: (" + xCoordinate + ", " + yCoordinate + ")");
        //increases the number of missiles fired by one
        numberOfMissilesFired++;
    }

    //checks whether the user has hit a ship
    public void hitOrMiss() {
        if (mySolution[yCoordinate - 1][xCoordinate - 1] == '.') { //if there is no ship
            myBoard[yCoordinate - 1][xCoordinate - 1] = 'O'; //replaces "." with "O"
            System.out.println("Miss!"); //lets the user know that they have missed
        } else { //otherwise, if there is a ship
            myBoard[yCoordinate - 1][xCoordinate - 1] = 'X'; //replace "." with "X"
            System.out.println("Hit!"); //lets the user know that they have hit
            numberOfSuccessfulMissiles++; //increases the number of successful missiles by one
        }
        printBoard(); //prints the user's board
    }

    public void statistics() {
        System.out.println("Number of missiles fired: " + numberOfMissilesFired); //number of missiles fired
        //number of successful missiles
        System.out.println("Number of successful missiles: " + numberOfSuccessfulMissiles);
        //percentage hit ratio
        double hitRatio = ((double) numberOfSuccessfulMissiles / (double) numberOfMissilesFired) * 100;
        //rounds the value to two decimal points
        System.out.println("Hit ratio: " + String.format("%.2f", hitRatio) + "%");
        System.out.println("--------------------------------------------------------------"); //prints divider
    }
}

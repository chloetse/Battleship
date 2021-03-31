import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Board {

    //initialising variables
    private char[][] myBoard;
    private char[][] mySolution;
    private int row;
    private int col;
    int numberOfMissilesFired;
    int numberOfSuccessfulMissiles;
    int xCoordinate;
    int yCoordinate;

    /*
     * Instantiate a new Maze object.
     */
    public Board() {
        row = 0;
        col = 0;
        numberOfMissilesFired = 0;
        numberOfSuccessfulMissiles = 0;
        myBoard = new char[10][10];
        mySolution = new char[10][10];
        fillBoard(myBoard);
        fillBoard(mySolution);
        placeShips();
    }

    private void fillBoard(char[][] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '.';
            }
        }
    }

    /*
     * Display the maze. Dots represent unexplored spaces, x is your current position,
     * - and | are walls, 0 are pits, and * are explored spaces.
     */
    public void printBoard() {
        printBoard(myBoard);
    }

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

    private boolean canMove(int rowMove, int colMove) {
        if (col + colMove > 10 || col + colMove < 0 ||
                row + rowMove > 10 || row + rowMove < 0) {
            return false;
        }
        else if (mySolution[row + rowMove][col + colMove] == '*') {
            myBoard[row+rowMove][col+colMove] = '*';
            return true;
        } else if (mySolution[row + rowMove][col + colMove] == '0') {
            myBoard[row+rowMove][col+colMove] = '*';
            return false;
        } else {
            myBoard[row+rowMove][col+colMove] = '-';
            return false;
        }
    }

    /*
    Places the ships randomly on the board
    List of ships:
    1 x (A)ircraft Carrier, size 5
    1 x (B)attleship, size 4
    1 x (C)ruiser, size 3
    2 x (D)estroyers, size 2
     */

    private void placeShips() {
        //Aircraft
        int randomInt = ThreadLocalRandom.current().nextInt(5, 6);
        //System.out.println("Aircraft random integer: " + randomIntA);
        for (int i = 0; i < 5; i++) {
            while (mySolution[randomInt][randomInt + i] != '.') {
                randomInt = ThreadLocalRandom.current().nextInt(5, 6);
            }
            mySolution[randomInt][randomInt + i] = 'A';
        }
        //Battleship
        randomInt = ThreadLocalRandom.current().nextInt(4, 7);
        //System.out.println("Battleship random integer: " + randomIntB);
        for (int i = 0; i < 4; i++) {
            while (mySolution[randomInt][randomInt + i] != '.') {
                randomInt = ThreadLocalRandom.current().nextInt(4, 7);
            }
            mySolution[randomInt + i][randomInt] = 'B';
        }
        //Cruiser
        randomInt = ThreadLocalRandom.current().nextInt(3, 8);
        //System.out.println("Cruiser random integer: " + randomIntB);
        for (int i = 0; i < 3; i++) {
            while (mySolution[randomInt][randomInt + i] != '.') {
                randomInt = ThreadLocalRandom.current().nextInt(3, 8);
            }
            mySolution[randomInt][randomInt + i] = 'C';
        }
        //Destroyers
        randomInt = ThreadLocalRandom.current().nextInt(2, 9);
        //System.out.println("Destroyer random integer: " + randomIntD);
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 2; i++) {
                while (mySolution[randomInt][randomInt + i] != '.') {
                    randomInt = ThreadLocalRandom.current().nextInt(2, 9);
                }
                mySolution[randomInt + i][randomInt] = 'D';
            }
        }
    }

    /* asks a question that requires an integer input and then checks the validity of the input
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

    public void userMove(Scanner inputS) {
        xCoordinate = getValidIntegerInputInRange(inputS, "Choose your x-coordinate:", 0, 10);
        yCoordinate = getValidIntegerInputInRange(inputS, "Choose your y-coordinate:", 0, 10);
        System.out.println("The coordinates you have chosen are: (" + xCoordinate + ", " + yCoordinate + ")");
        numberOfMissilesFired++;
    }

    public void hitOrMiss() {
        if (mySolution[xCoordinate - 1][yCoordinate - 1] == '.') {
            myBoard[xCoordinate - 1][yCoordinate - 1] = 'O';
            System.out.println("Miss!");
        } else {
            myBoard[xCoordinate - 1][yCoordinate - 1] = 'X';
            System.out.println("Hit!");
            numberOfSuccessfulMissiles++;
        }
        printBoard();
    }

    public void statistics() {
        System.out.println("Number of missiles fired: " + numberOfMissilesFired);
        System.out.println("Number of successful missiles: " + numberOfSuccessfulMissiles);
        double hitRatio = (double) numberOfSuccessfulMissiles / (double) numberOfMissilesFired;
        System.out.println("Hit ratio: " + String.format("%.2f", hitRatio) + "%");
        System.out.println("--------------------------------------------------------------");
    }
}

import java.util.concurrent.ThreadLocalRandom;

public class Board {

    //initialising variables
    private char[][] myBoard;
    private char[][] mySolution;
    private int row;
    private int col;
    int numberOfMissilesFired;
    int numberOfShipsSunk;

    /*
     * Instantiate a new Maze object.
     */
    public Board() {
        row = 0;
        col = 0;
        numberOfMissilesFired = 0;
        numberOfShipsSunk = 0;
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

    /*
     * Determines if the user reached the end of the maze.
     * @return true if the user is at the end, false otherwise.
     */
    public boolean didIWin() {
        if (row == 10 && col == 19) {
            return true;
        } else {
            return false;
        }
    }

    public void hitOrMiss(int xCoordinate, int yCoordinate) {
        if (mySolution[xCoordinate][yCoordinate] == '*') {
            myBoard[xCoordinate][yCoordinate] = 'O';
            System.out.println("Miss!");
        } else {
            myBoard[xCoordinate][yCoordinate] = 'X';
            System.out.println("Hit!");
            numberOfShipsSunk++;
        }
        printBoard();
    }

    public void statistics() {
        System.out.println("Number of missiles fired: " + numberOfMissilesFired);
        System.out.println("Number of ships sunk: " + numberOfShipsSunk);
        int hitRatio = numberOfShipsSunk / numberOfMissilesFired;
        System.out.println("Hit ratio: " + hitRatio);
    }
}

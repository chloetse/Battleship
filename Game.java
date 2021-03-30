import java.util.Scanner;

public class Game {

    //initializing variables
    Board myBoard;

    //constructor
    Game() {
        myBoard = new Board();
    }

    public void userMove(Scanner inputS) {
        int xCoordinate = getValidIntegerInputInRange(inputS, "Choose your x-coordinate:", 0, 10);
        int yCoordinate = getValidIntegerInputInRange(inputS, "Choose your y-coordinate:", 0, 10);
        System.out.println("The coordinates you have chosen are: (" + xCoordinate + ", " + yCoordinate + ")");
        myBoard.numberOfMissilesFired++;
    }

    public void intro() {
        System.out.println("Welcome to Battleships!");
        System.out.println("User board: ");
        myBoard.printBoard();
        System.out.println("Solution board: ");
        myBoard.printSolution();
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

    //temporary main method
    public static void main(String[] args) {
        Game myGame = new Game();
        myGame.intro();
    }
}

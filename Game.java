import java.util.Scanner; //import statement

public class Game {

    //initializing variables
    Board myBoard;

    //constructor
    Game() {
        myBoard = new Board();
    }

    //welcomes the user, and prints the user's board and the solution board
    public void intro() {
        System.out.println("Welcome to Battleships!"); //introduction statement
        System.out.println("User board: ");
        myBoard.printBoard(); //prints the user's board
        System.out.println("Solution board: ");
        myBoard.printSolution(); //prints the solution board
    }

    //asks the user for their move and checks whether the user's missile successfully hits the ship
    public void playingGame(Scanner inputS) {
        myBoard.userMove(inputS); //calls the user move method to ask the user for their move
        myBoard.hitOrMiss(); //calls the hit or miss method to check whether the user's missile hits or misses the ship
    }
}

import java.util.Scanner;

public class Game {

    //initializing variables
    Board myBoard;

    //constructor
    Game() {
        myBoard = new Board();
    }

    public void intro() {
        System.out.println("Welcome to Battleships!");
        System.out.println("User board: ");
        myBoard.printBoard();
        System.out.println("Solution board: ");
        myBoard.printSolution();
    }


    public void playingGame(Scanner inputS) {
        myBoard.userMove(inputS);
        myBoard.hitOrMiss();
    }
}

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

    //This method ask a question (that requires a String input) and then returns the String input
    private static String getValidStringInput(Scanner inputS, String question) {
        System.out.print(question + " "); //prints the question the user must respond to
        return inputS.nextLine(); //returns the String input
    }

    //main method
    public static void main(String[] args) {
        Game myGame = new Game();
        Scanner inputS = new Scanner(System.in);
        String getStatistics = getValidStringInput(inputS, "Do you want to see the statistics for the game? " +
                "Type 'y' for yes, and another other key for no.");
        if (getStatistics.equals("y")) {
            myGame.myBoard.statistics();
        }
        myGame.intro();
        while (myGame.myBoard.numberOfSuccessfulMissiles < 16) {
            myGame.playingGame(inputS);
        }
        System.out.println("Congratulations, you have won Battleship!");
        System.exit(0);
    }
}

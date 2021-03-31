import java.util.*;

public class Main {

    //main method
    public static void main(String[] args) {
        Game myGame = new Game();
        Scanner inputS = new Scanner(System.in);
        String getStatistics = getValidStringInput(inputS, "Do you want to see the statistics for the game? " +
                "Type 'y' for yes, and another other key for no.");
        myGame.intro();
        while (myGame.myBoard.numberOfSuccessfulMissiles < 16) {
            myGame.playingGame(inputS);
            if (getStatistics.equals("y")) {
                myGame.myBoard.statistics();
            }
        }
        System.out.println("Congratulations, you have won Battleship!");
        System.exit(0);
    }

    //This method ask a question (that requires a String input) and then returns the String input
    private static String getValidStringInput(Scanner inputS, String question) {
        System.out.print(question + " "); //prints the question the user must respond to
        return inputS.nextLine(); //returns the String input
    }
}

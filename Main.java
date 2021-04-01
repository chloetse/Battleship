import java.util.*; //import statement

public class Main {

    //main method
    public static void main(String[] args) {
        Game myGame = new Game();
        Scanner inputS = new Scanner(System.in); //Scanner
        //asks the user whether they want to see game statistics
        String getStatistics = getValidStringInput(inputS, "Do you want to see the statistics for the game? " +
                "Type 'y' for yes, and another other key for no.");
        myGame.intro(); //calls the introduction method
        //keeps asking the user for their next move until they have hit all parts of the ship
        while (myGame.myBoard.numberOfSuccessfulMissiles < 16) {
            myGame.playingGame(inputS); //calls the playing game method
            if (getStatistics.equals("y")) {
                myGame.myBoard.statistics(); //calls the statistics method
            }
        }
        System.out.println("Congratulations, you have won Battleship!"); //end statement
        System.exit(0); //exits the program
    }

    //asks a question (that requires a String input) and then returns the String input
    private static String getValidStringInput(Scanner inputS, String question) {
        System.out.print(question + " "); //prints the question the user must respond to
        return inputS.nextLine(); //returns the String input
    }
}

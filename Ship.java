import java.util.concurrent.ThreadLocalRandom; //import statement

public class Ship {

    //variables
    char symbol;
    int size;

    //constructor
    public Ship(char mySymbol, int mySize) {
        symbol = mySymbol;
        size = mySize;
    }

    //places the ships on the board randomly, either vertically or horizontally
    public void placeOnBoard(char[][] board) {
        //randomly chooses whether the ship should be placed horizontally or vertically
        int orientation = ThreadLocalRandom.current().nextInt(0, 1);

        //boundary of the board
        int xBound = board.length - 1;
        int yBound = board[0].length - 1;

        while (true) {
            //generates random coordinates
            int x = ThreadLocalRandom.current().nextInt(0, xBound);
            int y = ThreadLocalRandom.current().nextInt(0, yBound);

            //places horizontally
            if (orientation == 0 && x + size < xBound) {
                if (checkAndPlaceOnBoard(board, x, y, 1, 0)) {
                    break;
                }
            }

            //places vertically
            else if (y + size < yBound) {
                if (checkAndPlaceOnBoard(board, x, y, 0, 1)) {
                    break;
                }
            }
        }
    }

    //checks whether the ships can be placed on the board, and places the ships if allowed
    public boolean checkAndPlaceOnBoard(char[][] b, int x, int y, int dx, int dy) {
        //checks to ensure that the ship can be placed
        for (int i = 0; i < size; i++) {
            if (b[x + dx * i][y + dy * i] != '.') {
                return false; //cannot place the ship
            }
        }

        //updates the solution board
        for (int i = 0; i < size; i++) {
            b[x + dx * i][y + dy * i] = symbol;
        }
        return true;
    }
}

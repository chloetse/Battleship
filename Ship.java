import java.util.concurrent.ThreadLocalRandom;

public class Ship {

    //variables
    char symbol;
    int size;
    int bound;

    //constructor
    public Ship(char mySymbol, int mySize) {
        symbol = mySymbol;
        size = mySize;
        bound = 10 - size + 1;
    }

    public void placeOnBoard(char[][] b) {

        int randomInt = getRand();
        for (int i = 0; i < size; i++) {
            while (b[randomInt][randomInt + i] != '.') {
                randomInt = getRand();
            }
            b[randomInt][randomInt + i] = symbol;
        }
    }

    private int getRand() {
        return ThreadLocalRandom.current().nextInt(size, bound);
    }
}

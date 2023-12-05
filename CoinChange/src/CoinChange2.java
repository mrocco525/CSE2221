import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class CoinChange2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange2() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        final int[] coinDenominations = new int[] { 100, 50, 25, 10, 5, 1 };
        int[] coinCounts = new int[6];

        out.print("Enter a number of cents: ");
        int userMoney = in.nextInteger();

        coinCounts[0] = userMoney / coinDenominations[0];
        int remainingChange = userMoney - coinCounts[0] * coinDenominations[0];
        out.println(coinCounts[0] + " Dollars");

        coinCounts[1] = remainingChange / coinDenominations[1];
        remainingChange = remainingChange
                - coinCounts[1] * coinDenominations[1];
        out.println(coinCounts[1] + " Half Dollars");

        coinCounts[2] = remainingChange / coinDenominations[2];
        remainingChange = remainingChange
                - coinCounts[2] * coinDenominations[2];
        out.println(coinCounts[2] + " Quarters");

        coinCounts[3] = remainingChange / coinDenominations[3];
        remainingChange = remainingChange
                - coinCounts[3] * coinDenominations[3];
        out.println(coinCounts[3] + " Dimes");

        coinCounts[4] = remainingChange / coinDenominations[4];
        remainingChange = remainingChange
                - coinCounts[4] * coinDenominations[4];
        out.println(coinCounts[4] + " Nickles");

        coinCounts[5] = remainingChange / coinDenominations[5];
        remainingChange = remainingChange
                - coinCounts[5] * coinDenominations[5];
        out.println(coinCounts[5] + " Pennies");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}

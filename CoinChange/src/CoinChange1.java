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
public final class CoinChange1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange1() {
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

        final int dollar = 100;
        final int halfDollar = 50;
        final int quarter = 25;
        final int dime = 10;
        final int nickel = 5;
        final int penny = 1;

        out.print("Enter a number of cents: ");
        int userMoney = in.nextInteger();

        int numDollars = userMoney / dollar;
        int remainingChange = userMoney - numDollars * dollar;
        out.println(numDollars + " Dollars");

        int numHalfDollars = remainingChange / halfDollar;
        remainingChange = remainingChange - numHalfDollars * halfDollar;
        out.println(numHalfDollars + " Half Dollars");

        int numQuarters = remainingChange / quarter;
        remainingChange = remainingChange - numQuarters * quarter;
        out.println(numQuarters + " Quarters");

        int numDimes = remainingChange / dime;
        remainingChange = remainingChange - numDimes * dime;
        out.println(numDimes + " Dimes");

        int numNickels = remainingChange / nickel;
        remainingChange = remainingChange - numNickels * nickel;
        out.println(numNickels + " Nickles");

        int numPennies = remainingChange / penny;
        remainingChange = remainingChange - numPennies * penny;
        out.println(numPennies + " Pennies");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}

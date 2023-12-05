import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A short program that evaluates a user inputted integer with the Hailstone
 * series.
 *
 * @author Mason Rocco
 *
 */
public final class Hailstone4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone4() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int x = n;
        int seriesLength = 1;
        out.print(x + " ");

        while (x != 1) {

            if (x % 2 == 0) {
                x = x / 2;
            } else {
                x = (x + x + x) + 1;
            }
            out.print(x + " ");
            seriesLength++;
        }
        out.print("\nSeries length: " + seriesLength);
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        char programRun = 'y';
        while (programRun == 'y') {
            out.print("Enter an integer: ");
            int x = in.nextInteger();
            generateSeries(x, out);
            out.print("\nDo you wish to calculate another series?");
            programRun = in.nextLine().charAt(0);
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}

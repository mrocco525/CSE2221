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
public final class Hailstone1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone1() {
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
        out.print(x + " ");

        while (x != 1) {

            if (x % 2 == 0) {
                x = x / 2;
            } else {
                x = (x + x + x) + 1;
            }
            out.print(x + " ");
        }
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
        out.print("Enter an integer: ");
        int x = in.nextInteger();
        generateSeries(x, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}

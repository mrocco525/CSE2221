import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
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
public final class Hailstone2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone2() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        final int number = 3;
        out.print(n + " ");
        NaturalNumber one = new NaturalNumber1L(1);
        NaturalNumber two = new NaturalNumber1L(2);
        NaturalNumber three = new NaturalNumber1L(number);
        NaturalNumber x = new NaturalNumber1L(n);
        int seriesLength = 0;
        boolean y = true;
        while (y) {
            if (x.equals(one)) {
                y = false;
            } else {
                if (x.toInt() % 2 == 0) {
                    x.copyFrom(n);
                    x.divide(two);
                    n.copyFrom(x);
                } else {
                    x.copyFrom(n);
                    x.multiply(three);
                    x.add(one);
                    n.copyFrom(x);
                }
                out.print(x + " ");
                seriesLength++;
            }
        }
        out.print("\nThe length of the series is: " + seriesLength);
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
        out.print("Enter a positive integer: ");
        int userNum = Integer.parseInt(in.nextLine());
        NaturalNumber x = new NaturalNumber1L(userNum);
        generateSeries(x, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}

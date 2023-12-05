import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to test {@code NaturalNumberInstanceOps} methods subtract and power.
 *
 * @author Mason Rocco
 *
 */
public final class DivideBy2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private DivideBy2() {
    }

    /**
     * gets num of digits.
     *
     * @param n
     *            the number
     */
    public static void divideBy2(NaturalNumber n) {
        final int ten = 10;
        int x = n.divideBy10();
        int y = n.divideBy10();

        if (n.isZero()) {
            // Base case.
            int result = (y * ten + x) / 2;
            n.multiplyBy10(result / ten);
            n.multiplyBy10(result % ten);
        } else if (y % 2 == 0) {
            n.multiplyBy10(y);
            divideBy2(n);
            n.multiplyBy10(x / 2);
        } else {
            n.multiplyBy10(y - 1);
            divideBy2(n);
            n.multiplyBy10((ten + x) / 2);
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
        final int num = 38;
        NaturalNumber n = new NaturalNumber2(num);
        divideBy2(n);
        out.print(n);

        in.close();
        out.close();
    }

}

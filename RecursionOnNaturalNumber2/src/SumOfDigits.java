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
public final class SumOfDigits {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SumOfDigits() {
    }

    /**
     * gets num of digits.
     *
     * @param n
     *            the number
     * @return int number of digits
     */
    public static NaturalNumber sumOfDigits(NaturalNumber n) {
        int lastDigit = n.divideBy10();
        NaturalNumber sum = new NaturalNumber2(lastDigit);
        if (!n.isZero()) {
            sum.add(sumOfDigits(n));
        }
        n.multiplyBy10(lastDigit);
        return sum;
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
        out.print("Enter a postive integer: ");
        int num = in.nextInteger();
        NaturalNumber n = new NaturalNumber2(num);
        out.print("The sum of the digits is: " + sumOfDigits(n));

        in.close();
        out.close();
    }

}

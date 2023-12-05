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
public final class IsPalindrome {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private IsPalindrome() {
    }

    /**
     * gets num of digits.
     *
     * @param s
     *            the string
     * @return true or false
     */
    public static boolean isPalindrome(String s) {
        boolean x;
        if (s.length() < 2) {
            x = true;
            return x;
        } else {
            if (s.charAt(0) == s.charAt(s.length() - 1)) {
                x = true;
                String y = s.substring(1, s.length() - 1);
                x = isPalindrome(y);
            } else {
                x = false;
            }

            return x;
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
        final String s = "jib";
        boolean x = isPalindrome(s);
        out.print(x);

        in.close();
        out.close();
    }

}

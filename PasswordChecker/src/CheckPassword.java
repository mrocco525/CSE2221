import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program will check whether the user's password meets the requirements.
 *
 * @author Mason Rocco
 *
 */
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {
        final int lengthRequirement = 8;
        if (s.length() >= lengthRequirement) {
            out.println("Your password meets the length requirement.");
        } else {
            out.println("Your password is not long enough!");
        }
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {
        int i = 0;
        boolean x = false;
        while (i < s.length()) {
            if (Character.isUpperCase(s.charAt(i))) {
                x = true;
            }
            i++;
        }
        return x;
    }

    /**
     * Checks if the given String contains an lower case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String s) {
        int i = 0;
        boolean x = false;
        while (i < s.length()) {
            if (Character.isLowerCase(s.charAt(i))) {
                x = true;
            }
            i++;
        }
        return x;
    }

    /**
     * Checks if the given String contains a digit.
     *
     * @param s
     *            the String to check
     * @return true if s contains a digit, false otherwise
     */
    private static boolean containsDigit(String s) {
        int i = 0;
        boolean x = false;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                x = true;
            }
            i++;
        }
        return x;
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

        out.print("Enter your password: ");
        String userPass = in.nextLine();

        checkPassword(userPass, out);
        int criteriaCount = 0;

        boolean upper = containsUpperCaseLetter(userPass);
        if (upper) {
            criteriaCount++;
        }

        boolean lower = containsLowerCaseLetter(userPass);
        if (lower) {
            criteriaCount++;
        }

        boolean digit = containsDigit(userPass);
        if (digit) {
            criteriaCount++;
        }

        if (criteriaCount >= 2) {
            out.print(
                    "Your password meets at least 2 of the 3 character requirements!");
        } else {
            out.print(
                    "Your password does not meet enough character requirements.");
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}

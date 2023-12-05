import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Estimates a number using the de Jager formula.
 *
 * @author Mason Rocco
 *
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        boolean x = true;
        double userNumber = 0.0;
        while (x) {
            out.print("Enter a number to be estimated: ");
            String input = in.nextLine();
            userNumber = Double.parseDouble(input);

            /**
             * makes sure that the user's number is positive and a real number
             * if so, sets the boolean to false to make sure the while loop does
             * not repeat, and sets the userNumber variable to that number.
             * otherwise, tells the user their number is incorrect and then the
             * loop repeats.
             */
            if (FormatChecker.canParseDouble(input) && userNumber > 0) {
                x = false;
                userNumber = Double.parseDouble(input);
            } else {
                out.println(
                        "Error: Your number does not meet the requirements. Try again.");
            }
        }
        return userNumber;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        boolean x = true;
        double userNumber = 0.0;
        while (x) {
            out.print("Enter a positive real number (not 1): ");
            String input = in.nextLine();
            userNumber = Double.parseDouble(input);

            /**
             * makes sure that the user's number is positive and a real number
             * that is not 1. if so, sets the boolean to false to make sure the
             * while loop does not repeat, and sets the userNumber variable to
             * that number. otherwise, tells the user their number is incorrect
             * and then the loop repeats.
             */
            if (FormatChecker.canParseDouble(input) && userNumber > 0
                    && userNumber != 1) {
                x = false;
                userNumber = Double.parseDouble(input);
            } else {
                out.println(
                        "Error: Your number does not meet the requirements. Try again.");
            }
        }
        return userNumber;
    }

    /**
     * Returns the estimation using the de Jager formula using the user's
     * numbers.
     *
     * @param userNumber
     *            User's inputted number to be estimated
     * @param w
     *            First number used to estimate
     * @param x
     *            Second number used to estimate
     * @param y
     *            Third number used to estimate
     * @param z
     *            Last number used to estimate
     * @param out
     *            the output stream
     */
    private static void estimate(double userNumber, double w, double x,
            double y, double z, SimpleWriter out) {
        /**
         * initializes the variables that will be used in the method to estimate
         * numExponents is the number of exponents, used for the while loops the
         * array 'exponents' includes all exponents in the de Jager formula sets
         * the exponents for a through d to 0, to be updated later sets 'change'
         * to the current difference using 0 as the exponent in the de Jager
         * formula. will be updated later using the same formula
         */

        final int numExponents = 16;
        final double[] exponents = { -5, -4, -3, -2, -1, -1.0 / 2, -1.0 / 3,
                -1.0 / 4, 0, 1.0 / 4, 1.0 / 3, 1.0 / 2, 1, 2, 3, 4, 5 };
        int a = 0, b = 0, c = 0, d = 0;
        double aExponent = 0, bExponent = 0, cExponent = 0, dExponent = 0;
        double change = ((Math.pow(w, exponents[0]))
                * (Math.pow(x, exponents[0])) * (Math.pow(y, exponents[0]))
                * (Math.pow(z, exponents[0]))) - userNumber;
        /**
         * nests 4 for loops to use every possible iteration of the exponents
         * for each of the user-inputted numbers. inside of the final for loop,
         * finds the difference from the estimation and the old value 'change.'
         * if the new estimation is closer to the true value, it replaces the
         * old value, which gives the best value after the while loops are
         * finished. the variables for the exponents are also updated if the new
         * value is a better estimation. while loops are updated using
         * (variable)++ and resetting a variable to 0 after its loop is done.
         */
        for (d = 0; d <= numExponents; d++) {
            for (c = 0; c <= numExponents; c++) {
                for (b = 0; b <= numExponents; b++) {
                    for (a = 0; a <= numExponents; a++) {
                        double estimate = ((Math.pow(w, exponents[a]))
                                * (Math.pow(x, exponents[b]))
                                * (Math.pow(y, exponents[c]))
                                * (Math.pow(z, exponents[d]))) - userNumber;
                        if (Math.abs(estimate) < Math.abs(change)) {
                            change = estimate;
                            aExponent = exponents[a];
                            bExponent = exponents[b];
                            cExponent = exponents[c];
                            dExponent = exponents[d];
                        }
                    }
                    a = 0;
                }
                b = 0;
            }
            c = 0;
        }
        /**
         * outprints the final number, as well as the percentage error for it.
         * along with this, outprints the chosen exponent for each of the
         * numbers inputted by the user.
         */
        final int percentage = 100;
        double percentError = (change / userNumber) * percentage;
        out.println("The calculated number is: " + (userNumber + change));
        out.println("The percent error is: " + percentError + "%");
        out.println("The exponent for a is : " + aExponent);
        out.println("The exponent for b is : " + bExponent);
        out.println("The exponent for c is : " + cExponent);
        out.println("The exponent for d is : " + dExponent);
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

        double userNumber = getPositiveDouble(in, out); //gets the number to be estimated

        /**
         * gets the 4 numbers that are used for estimation
         */
        out.println(
                "Now, choose 4 numbers that will be used for the estimation: ");
        out.print("First number: ");
        double w = getPositiveDoubleNotOne(in, out);
        out.print("Second number: ");
        double x = getPositiveDoubleNotOne(in, out);
        out.print("Third number: ");
        double y = getPositiveDoubleNotOne(in, out);
        out.print("Fourth number: ");
        double z = getPositiveDoubleNotOne(in, out);

        estimate(userNumber, w, x, y, z, out); //runs the estimation method

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}

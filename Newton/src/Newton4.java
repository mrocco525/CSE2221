import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A short program that will calculate a square root using Newton Iteration.
 *
 * @author Mason Rocco
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error inputted
     * by the user.
     *
     * @param x
     *            positive number to compute square root of
     * @param y
     *            relative error for the square root
     * @return estimate of square root
     */
    private static double sqrt(double x, double y) {
        final double epsilon = y;
        //sets up epsilon from the user's input
        final double half = 0.5;
        // is used to take half of a number when multiplied.
        double r = x;
        //sets the initial 'guess' to the user inputted number.
        while ((Math.abs((r * r) - x)) >= (epsilon * epsilon) * x) {
            //uses a formula given to run until the relative error is low enough.
            r = half * ((r + (x / r)));
            //updates the estimation of the square root according to Newton Iteration.
        }
        return r;
        //the square root is returned to the main method.
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

        out.print("Enter a number: "); //asks the user for a number
        double userNumber = in.nextDouble(); //takes a user inputted number
        /**
         * This must be done before the loop in order to only ask the user once
         * per iteration what number they wish to evaluate. Otherwise, the
         * program would update the user number twice before evaluating the
         * square root.
         */

        while (userNumber >= 0) { //as long as the number is positive or 0 the loop runs.

            out.print("Enter epsilon: "); //asks the user for epsilon
            double userError = in.nextDouble(); //takes user epsilon for the sqrt method.

            userNumber = sqrt(userNumber, userError);
            //calls the sqrt method to update the user number, returning the square root.
            out.println(userNumber); //prints out the estimated square root value.

            out.println("Enter another value to calculate: ");
            //gives the user the opportunity to run the program again.
            userNumber = in.nextDouble();
            //if the input is positive or 0 the loop runs again. if not, the program ends.
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}

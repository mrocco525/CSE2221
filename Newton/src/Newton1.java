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
public final class Newton1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        final double epsilon = 0.0001;
        //sets up final double epsilon for the relative error
        final double half = 0.5;
        // is used to take half of a number when multiplied.
        double r = x;
        //sets the initial 'guess' to the user inputted number.

        while ((Math.abs((r * r) - x) / x >= (epsilon * epsilon))) {
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

        char userResponse = 'y';
        //in order to make the while loop initially run, set char userResponse to y.
        while (userResponse == 'y') { //as long as the response is y, the loop will run.
            out.print("Enter a number: "); //asks the user for a number
            double userNumber = in.nextDouble(); //takes a user inputted number

            userNumber = sqrt(userNumber);
            //calls the sqrt method to update the user number, returning the square root.
            out.println(userNumber); //prints out the estimated square root value.

            out.println("Do you wish to calculate another square root?");
            //gives the user the opportunity to run the program again.
            userResponse = in.nextLine().charAt(0);
            //if the input is y, the loop will run again. otherwise, the program ends.
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}

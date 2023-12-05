import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Mason Rocco
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"] and
     *  [the attribute value of "divide" is not zero]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        if (exp.label().equals("times")) {
            NaturalNumber times1 = new NaturalNumber1L(evaluate(exp.child(0)));
            NaturalNumber times2 = new NaturalNumber1L(evaluate(exp.child(1)));
            times1.multiply(times2);
            return times1;
        } else if (exp.label().equals("plus")) {
            NaturalNumber plus1 = new NaturalNumber1L(evaluate(exp.child(0)));
            NaturalNumber plus2 = new NaturalNumber1L(evaluate(exp.child(1)));
            plus1.add(plus2);
            return plus1;
        } else if (exp.label().equals("divide")) {
            NaturalNumber divide2 = new NaturalNumber1L(evaluate(exp.child(1)));
            if (divide2.isZero()) {
                //divide by 0 error
                Reporter.fatalErrorToConsole("Error: Cannot divide by zero");
            }
            NaturalNumber divide1 = new NaturalNumber1L(evaluate(exp.child(0)));
            divide1.divide(divide2);
            return divide1;
        } else if (exp.label().equals("minus")) {
            NaturalNumber minus1 = new NaturalNumber1L(evaluate(exp.child(0)));
            NaturalNumber minus2 = new NaturalNumber1L(evaluate(exp.child(1)));
            minus1.subtract(minus2);
            return minus1;
        } else if (exp.label().equals("number")) {
            NaturalNumber x = new NaturalNumber1L(exp.attributeValue("value"));
            return x;
        } else {
            NaturalNumber zero = new NaturalNumber1L(0);
            return zero; //if there is no expression zero is returned
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}

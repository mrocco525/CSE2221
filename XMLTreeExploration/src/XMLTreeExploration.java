import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeExploration() {
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
        final int child = 10;
        XMLTree xml = new XMLTree1(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/"
                        + "extras/instructions/xmltree-model/columbus-weather.xml");
        out.print(xml.toString());
        xml.display();
        XMLTree results = xml.child(0);
        out.print(results.toString());
        XMLTree channel = results.child(0);
        out.print(channel.toString());
        out.println(
                "Number of children in channel: " + channel.numberOfChildren());
        XMLTree title = channel.child(2);
        XMLTree titleText = title.child(0);
        out.println(titleText.label());
        XMLTree astronomy = channel.child(child);
        out.println(astronomy.toString());
        boolean x = astronomy.hasAttribute("sunset");
        out.println(x);
        out.println("Sunrise: " + astronomy.attributeValue("sunrise"));
        out.println("Sunset: " + astronomy.attributeValue("sunset"));

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}

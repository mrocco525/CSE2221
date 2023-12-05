import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    /*
     * Tests for combination
     */
    @Test
    public void combinationTest1() {
        final int two = 2;
        String str = StringReassembly.combination("abcde", "defgh", two);
        SimpleWriter out = new SimpleWriter1L();
        out.println(str);
        out.close();
        assertEquals("abcdefgh", str);
    }

    @Test
    public void combinationTest2() {
        final int six = 6;
        String str = StringReassembly.combination("123456", "1234567890", six);
        assertEquals("1234567890", str);
    }

    /*
     * Tests for addToSetAvoidingSubstrins
     */
    @Test
    public void addToSetAvoidingSubstringsTest1() {
        Set<String> strSet = new Set1L<String>();
        Set<String> strSetCheck = new Set1L<String>();
        String str1 = "Michigan~";
        String str2 = "o Bucks -- B";
        String subStr = "ichigan~";

        strSet.add(str1);
        strSet.add(str2);
        strSetCheck.add(str1);
        strSetCheck.add(str2);

        StringReassembly.addToSetAvoidingSubstrings(strSet, subStr);

        assertEquals(strSetCheck, strSet);
    }

    @Test
    public void addToSetAvoidingSubstringsTest2() {
        Set<String> strSet = new Set1L<String>();
        Set<String> strSetCheck = new Set1L<String>();
        String str1 = "Michigan~";
        String str2 = "o Bucks -- B";
        String subStr = "Beat Mich";

        strSet.add(str1);
        strSet.add(str2);
        strSetCheck.add(str1);
        strSetCheck.add(str2);
        strSetCheck.add(subStr);

        StringReassembly.addToSetAvoidingSubstrings(strSet, subStr);

        assertEquals(strSetCheck, strSet);
    }

    /*
     * tests of printWithLineSeparators
     */
    @Test
    public void printWithLineSeparatorsTest1() {
        SimpleWriter out = new SimpleWriter1L("testoutput.txt");
        SimpleReader in = new SimpleReader1L("testoutput.txt");
        String text = "This is a test~I hope it works";
        String expect = "This is a test" + "\n" + "I hope it works";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2);
    }

    @Test
    public void printWithLineSeparatorsTest2() {
        SimpleWriter out = new SimpleWriter1L("testoutput.txt");
        SimpleReader in = new SimpleReader1L("testoutput.txt");
        String text = "This~is a test~I hope it works";
        String expect = "This" + "\n" + "is a test" + "\n" + "I hope it works";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        String test3 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2 + "\n" + test3);
    }

    @Test
    public void printWithLineSeparatorsTest3() {
        SimpleWriter out = new SimpleWriter1L("testoutput.txt");
        SimpleReader in = new SimpleReader1L("testoutput.txt");
        String text = "Hello~my name is~Mason";
        String expect = "Hello" + "\n" + "my name is" + "\n" + "Mason";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        String test3 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2 + "\n" + test3);
    }

}

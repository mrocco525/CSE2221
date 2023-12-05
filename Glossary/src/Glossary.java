import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Creates a group of .html files including words and definitions when given a
 * .txt with a list of words and definitions.
 *
 * @author Mason Rocco
 *
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Read lines from given file and convert them into a maps that saves
     * {@code key} as key, {@code value} as value.
     *
     * @param in
     *            the input file reader
     * @param termsAndDefinitions
     *            the output map that saves word and definition pairs
     * @update termsAndDefinitions
     */
    public static void inputData(SimpleReader in,
            Map<String, String> termsAndDefinitions) {
        String key = "";
        String value = "";

        while (!in.atEOS()) {
            String temp = in.nextLine();
            if (isSingleWord(temp)) {
                key = temp;
            } else if (isSentence(temp)) {
                value += temp;
            } else {
                termsAndDefinitions.add(key, value);
                key = "";
                value = "";
            }
        }
        if (key.length() > 0 && value.length() > 0) {
            termsAndDefinitions.add(key, value);
        }
    }

    /**
     * Return whether or not {@code str} is a single word.
     *
     * @param str
     *            the input string
     * @return true if {@code str} is a single word, false otherwise.
     *
     */
    public static boolean isSingleWord(String str) {
        boolean isWord = true;
        if (str.length() < 1) {
            isWord = false;
        } else {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == ' ') {
                    isWord = false;
                }
            }
        }
        return isWord;
    }

    /**
     * Return whether or not {@code str} is a sentence.
     *
     * @param str
     *            the input string
     * @return true if {@code str} is a sentence, false otherwise.
     */
    public static boolean isSentence(String str) {
        boolean isSentence = false;
        if (str.length() < 1) {
            isSentence = false;
        } else {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == ' ') {
                    isSentence = true;
                }
            }
        }
        return isSentence;
    }

    /**
     * Calls the Comparator class to override a compare method.
     */
    private static class AtoZ implements Comparator<String> {

        /**
         * Compares the sizes of two strings, returning the result.
         *
         * @param str1
         *            the first string
         * @param str2
         *            the second string
         * @return 1 if {@code str1} is larger than {@code str2}, -1 if
         *         {@code str1} is larger than {@code str2}, 0 if {@code str1}
         *         is equal to {@code str2}
         */
        @Override
        public int compare(String str1, String str2) {
            if (str1.compareTo(str2) > 0) {
                return 1;
            } else if (str1.compareTo(str2) < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Outprints index.html.
     *
     * @param entries
     *            all the words that need to be printed with hyperlink
     * @param finalFilePath
     *            the location that the file is generated
     */
    public static void printIndex(Queue<String> entries, String finalFilePath) {
        SimpleWriter index = new SimpleWriter1L(
                finalFilePath + "/" + "index.html");
        index.print("<html>\n<head>\n<title>Glossary</title>\n</head>\n");
        index.print(
                "<body>\n<h2>Glossary</h2>\n<hr />\n<h3>Index</h3>\n<ul>\n");
        for (String word : entries) {
            index.println(
                    "<li><a href=\"" + word + ".html\">" + word + "</a></li>");
        }
        index.print("</ul>\n</body>\n</html>\n");
        index.close();
    }

    /**
     * Takes words from a Map and converts them into a sorted Queue.
     *
     * @param termsAndDefinitions
     *            the given Map that stores all words and definitions
     * @return A sorted Queue<String> that stores all words
     */
    public static Queue<String> mapToSortedQueue(
            Map<String, String> termsAndDefinitions) {
        Queue<String> entries = new Queue1L<String>();
        for (Map.Pair<String, String> words : termsAndDefinitions) {
            entries.enqueue(words.key());
        }

        Comparator<String> strCompare = new AtoZ();
        entries.sort(strCompare);
        return entries;
    }

    /**
     * Prints all words and their definitions to a file, each with
     * "words".index.
     *
     * @param termsAndDefinitions
     *            a Map<String, String> that stores all words and definitions
     * @param finalFilePath
     *            the location that the file is generated
     */
    public static void outprintWords(Map<String, String> termsAndDefinitions,
            String finalFilePath) {

        for (Map.Pair<String, String> word : termsAndDefinitions) {
            Set<String> words = findWordsInSentence(word.value(),
                    termsAndDefinitions);
            SimpleWriter out = new SimpleWriter1L(
                    finalFilePath + "/" + word.key() + ".html");
            out.print("<html>\n<head>\n<title>" + word.key()
                    + "</title>\n</head>\n");
            out.print("<body>\n<h2><b><i><font color=\"red\">" + word.key()
                    + "</font></i></b></h2>\n");
            String definition = word.value();
            for (String words1 : words) {
                definition = definition.substring(0, definition.indexOf(words1))
                        + "<a href=\"" + words1 + ".html\">" + words1 + "</a>"
                        + definition.substring(
                                definition.indexOf(words1) + words1.length());
            }
            out.print("<blockquote>" + definition + "</blockquote>");
            out.println("<hr />");
            out.println("<p>Return to <a href=\"index.html\">index</a>.</p>");
            out.print("</body>\n</html>");
            out.close();
        }
    }

    /**
     * Finds the terms that are in a definition.
     *
     * @param str
     *            the given definition
     * @param termsAndDefinitions
     *            a Map<String, String> that stores all words
     * @return a Set<String> with all words that exist in the given definition
     */
    public static Set<String> findWordsInSentence(String str,
            Map<String, String> termsAndDefinitions) {
        Set<String> words = new Set1L<String>();
        for (Map.Pair<String, String> word : termsAndDefinitions) {
            if (str.contains(word.key()) && !words.contains(word.key())) {
                words.add(word.key());
            }
        }
        return words;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        Map<String, String> termsAndDefinitions = new Map1L<String, String>();

        out.print("Enter an input file(.txt):");
        String fileName = in.nextLine();
        out.print("Enter a save location:");
        String finalFilePath = in.nextLine();
        SimpleReader fileReader = new SimpleReader1L(fileName);
        inputData(fileReader, termsAndDefinitions);

        out.println(termsAndDefinitions.toString());

        Queue<String> entries = mapToSortedQueue(termsAndDefinitions);
        printIndex(entries, finalFilePath);
        outprintWords(termsAndDefinitions, finalFilePath);

        in.close();
        out.close();
    }

}

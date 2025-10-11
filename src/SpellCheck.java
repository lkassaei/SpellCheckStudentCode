import java.util.ArrayList;

/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: Lily Kassaei
 * */

public class SpellCheck {
    /**
     * checkWords finds all words in text that are not present in dictionary
     *
     * @param text The list of all words in the text.
     * @param dictionary The list of all accepted words.
     * @return String[] of all mispelled words in the order they appear in text. No duplicates.
     */
    public String[] checkWords(String[] text, String[] dictionary) {
        // Initialize data structure: Either a TST or Trie
        TST words = new TST(dictionary);
        //Trie words = new Trie(dictionary);

        // Create an Array List for misspelled words
        ArrayList<String> misspelled = new ArrayList<>();

        // Create either a Trie or TST for misspelled words that have already been accounted for
        //Trie seenMisspelled = new Trie(null);
        TST seenMisspelled = new TST(null);

        // Go through each word in the text
        for (String word : text) {
            // If we could not find the word in the TST and we have not accounted for it being misspelled already
            if (!words.lookup(word) && !seenMisspelled.lookup(word)) {
                // Add the word to the misspelled list
                misspelled.add(word);
                // Mark it as accounted for
                seenMisspelled.insert(word);
            }
        }
        // Return the Array List in array form
        return misspelled.toArray(new String[0]);
    }
}

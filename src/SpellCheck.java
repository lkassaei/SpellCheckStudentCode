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
    // First code the trie: Need trie class
    // (Node root, Fncs: insert(), lookup()) and
    // Node class (boolean isTerminal, array of Nodes to children. Fncs: isWord() setWord() getNext()
    // HYPHENS

    // Then for the final solution code the TST (tertiary search trie)

    // Trie Pseudocode:
    // Trie for words in dict
    // Trie for misspelled words
    // For word in text:
    //      if not in either mispelled or dict:
    //          add to misspelled
    // Return num of misspelled
    public String[] checkWords(String[] text, String[] dictionary) {
        Trie words = new Trie();
        for (String word : dictionary) {
            words.insert(word);
        }
        ArrayList<String> misspelled = new ArrayList<>();
        Trie seenMisspelled = new Trie();

        for (String word : text) {
            if (!words.lookup(word) && !seenMisspelled.lookup(word)) {
                misspelled.add(word);
                seenMisspelled.insert(word);
            }
        }
        return misspelled.toArray(new String[0]);
    }
}

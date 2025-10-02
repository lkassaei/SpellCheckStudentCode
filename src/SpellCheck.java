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
    // First code the trie: Need trie class (Node root, Fncs: insert(), lookup()) and Node class (boolean isTerminal, array of Nodes to children. Fncs: isWord() setWord() getNext()
    // Trie Pseudocode:
    // Trie for words in dict
    // Trie for misspelled words
    // For word in text:
    //      if not in either mispelled or dict:
    //          add to misspelled
    // Return num of misspelled

    // Then for the final solution code the TST (tertiary search trie)
    public String[] checkWords(String[] text, String[] dictionary) {
        ArrayList<String> visited = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < text.length; i ++) {
            if (!binarySearch(text[i], dictionary) && !visited.contains(text[i])) {
                words.add(text[i]);
                visited.add(text[i]);
            }
        }
        return words.toArray(new String[0]);
    }

    public boolean binarySearch(String word, String[] dictionary) {
        int low = 0;
        int high = dictionary.length - 1;
        while (low <= high) {
            int mid = (low + high)/2;
            int comparison = word.compareTo(dictionary[mid]);
            if (comparison == 0) {
                return true;
            }
            else if (comparison < 0) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return false;
    }
}

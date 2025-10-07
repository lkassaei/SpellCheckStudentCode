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
    public class Node {
        private boolean isTerminal;
        private Node[] children;

        public Node() {
            this.isTerminal = false;
            this.children = new Node[256];
        }

        public boolean isWord() {
            return this.isTerminal;
        }

        public void setWord() {
            this.isTerminal = true;
        }

        public Node getNext(char c) {
            if (c < 0 || c >= 256) {
                return null;
            }
            return this.children[c];
        }

        public void setNext(char c, Node node) {
            if (c >= 0 && c < 256) {
                this.children[c] = node;
            }
        }
    }

    public class Trie {
        private Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String word) {
            Node current = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                Node next = current.getNext(c);
                if (next == null) {
                    next = new Node();
                    current.setNext(c, next);
                }
                current = next;
            }
            current.setWord();
        }

        public boolean lookup(String word) {
            Node current = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                current = current.getNext(c);

                if (current == null) {
                    return false;
                }
            }
            return current.isWord();
        }
    }

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

// Trie by Lily Kassaei

public class Trie {
    // Node class for Trie that has if it ends a word and all of its children
    private static class Node {
        private boolean isTerminal;
        private Node[] children;
        private int ALPHABET_SIZE = 256;

        // Set children to empty array big enough for extended ASCII alphabet
        public Node() {
            this.children = new Node[ALPHABET_SIZE];
        }

        // Check if the Node marks the end of a word
        public boolean isWord() {
            return this.isTerminal;
        }

        // Declare that the node marks the end of a word
        public void setWord() {
            this.isTerminal = true;
        }

        // Get the next node
        public Node getNext(char c) {
            // Check if the letter is valid (in ASCII alphabet)
            if (c < 0 || c >= ALPHABET_SIZE) {
                return null;
            }
            // Return child at that place
            return this.children[c];
        }

        // Set the next node
        public void setNext(char c, Node node) {
            // Check if letter is valid
            if (c >= 0 && c < ALPHABET_SIZE) {
                // Set that place equal to the next node
                this.children[c] = node;
            }
        }
    }

    // Declare root of Trie
    private Node root;

    // Initialize the Trie
    public Trie(String[] data) {
        // Create new node for the root
        this.root = new Node();
        // If data is not null, fill in Trie with each word
        if (data != null) {
            for (String s: data) {
                insert(s);
            }
        }
    }

    // Insert a word into the Trie
    public void insert(String word) {
        // Start at the top of the Trie
        Node current = root;

        // Go through the given word
        for (int i = 0; i < word.length(); i++) {
            // Grab the current letter
            char currentLetter = word.charAt(i);

            // Find the next node
            Node next = current.getNext(currentLetter);

            // If the next node is null, create a new one and set it to be next
            if (next == null) {
                next = new Node();
                current.setNext(currentLetter, next);
            }

            // Reset current
            current = next;
        }
        // Mark the end of the word
        current.setWord();
    }

    // Lookup if a given word is in the Trie
    public boolean lookup(String word) {
        // Start at the root of the TRie
        Node current = root;

        // Go through the word
        for (int i = 0; i < word.length(); i++) {
            // Grab the current letter
            char currentLetter = word.charAt(i);

            // Find the next node
            current = current.getNext(currentLetter);

            // If the next node is null, then we did not match so return false
            if (current == null) {
                return false;
            }
        }
        // Return if the end of the lookup word is a valid word in the Trie
        return current.isWord();
    }
}
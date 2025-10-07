public class Trie {
    private class Node {
        private boolean isTerminal;
        private Node[] children;

        public Node() {
            this.isTerminal = false;
            this.children = new Node[ALPHABET_SIZE];
        }

        public boolean isWord() {
            return this.isTerminal;
        }

        public void setWord() {
            this.isTerminal = true;
        }

        public Node getNext(char c) {
            if (c < 0 || c >= ALPHABET_SIZE) {
                return null;
            }
            return this.children[c];
        }

        public void setNext(char c, Node node) {
            if (c >= 0 && c < ALPHABET_SIZE) {
                this.children[c] = node;
            }
        }
    }

    private Node root;
    public static final int ALPHABET_SIZE = 256;

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
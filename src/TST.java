public class TST {
    private class Node {
        private boolean isTerminal;
        private Node leftChild;
        private Node middleChild;
        private Node rightChild;
        private char value;

        public Node(char value) {
            this.isTerminal = false;
            this.value = value;
        }

        public boolean isWord() {
            return this.isTerminal;
        }

        public void setWord() {
            this.isTerminal = true;
        }
    }

    private Node root;

    public TST(String[] data) {
        if (data != null) {
            for (String word : data) {
                insert(word);
            }
        }
    }

    public void insert(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        root = insertHelper(root, word, 0);
    }

    private Node insertHelper(Node node, String word, int index) {
        char c = word.charAt(index);
        if (node == null) {
            node = new Node(c);
        }

        if (c < node.value) {
            node.leftChild = insertHelper(node.leftChild, word, index);
        }
        if (c > node.value) {
            node.rightChild = insertHelper(node.rightChild, word, index);
        }
        else {
            if (index < word.length() - 1) {
                node.middleChild = insertHelper(node.middleChild, word, index + 1);
            }
            else {
                node.setWord();
            }
        }
        return node;
    }

    public boolean lookup(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        return lookupHelper(root, word, 0);
    }

    public boolean lookupHelper(Node node, String word, int index) {
        if (node == null) {
            return false;
        }
        char c = word.charAt(index);
        if (c < node.value) {
            return lookupHelper(node.leftChild, word, index);
        }
        else if (c > node.value) {
            return lookupHelper(node.rightChild, word, index);
        }
        else {
            if (index < word.length() - 1) {
                return lookupHelper(node.middleChild, word, index + 1);
            }
            return node.isWord();
        }
    }
}

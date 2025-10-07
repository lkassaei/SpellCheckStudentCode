public class TST {
    private class Node {
        private boolean isTerminal;
        private Node[] children;
        private char value;

        public Node(char value) {
            this.isTerminal = false;
            this.children = new Node[3];
            this.value = value;
        }

        public boolean isWord() {
            return this.isTerminal;
        }

        public void setWord() {
            this.isTerminal = true;
        }

        public void getNext(Node check, Node compare) {
            if (check.value < compare.value) {
                compare.children[0] = check;
            }
            else if (check.value == compare.value) {
                compare.children[1] = check;
            }
            else if (check.value > compare.value) {
                compare.children[2] = check;
            }
        }

        public void setNext(char c, Node node) {

        }
    }

    private Node root;

    public TST() {

    }

    public void insert(String word) {
//        Node current = root;
//        for (int i = 0; i < word.length(); i++) {
//            char c = word.charAt(i);
//            current.getNext();
//            Node next = current;
//            if () {
//
//            }
//        }
    }

    public boolean lookup() {
        return false;
    }
}

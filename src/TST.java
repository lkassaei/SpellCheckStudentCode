// TST by Lily Kassaei

public class TST {
    // Node class for TST that has the left middle and right children for each node
    private static class Node {
        private boolean isTerminal;
        private Node leftChild, middleChild, rightChild;
        private char value;

        // Set each node with its value
        public Node(char value) {
            this.value = value;
        }

        // Returns true if the node signifies the end of a word
        public boolean isWord() {
            return this.isTerminal;
        }

        // Sets the node to mark the end of a word
        public void setWord() {
            this.isTerminal = true;
        }
    }

    // The root for the TST
    private Node root;

    // Fill the TST with data
    public TST(String[] data) {
        // Make sure data is not null
        if (data != null) {
            // Insert each word of the data into the TST
            for (String word : data) {
                insert(word);
            }
        }
    }

    // Inserts a word into the TST
    public void insert(String word) {
        // If the word is null or empty nothing can be inserted so return
        if (word == null || word.isEmpty()) {
            return;
        }

        // If the root is null then create a new node starting at the first letter of the word
        if (root == null) {
            root = new Node(word.charAt(0));
        }

        // Insert two different ways: One iteratively, and one recursively
        insertIterative(root, word);
        //root = insertRecursive(root, word, 0);
    }

    // Insert the word iteratively
    private void insertIterative(Node node, String word) {
        // Initialize index and calculate last index of word to avoid multiple calculations during the loop
        int index = 0;
        int last = word.length() - 1;

        // Iterate through the word
        while(true) {
            // Get each letter
            char current = word.charAt(index);

            // Check if the current letter against the value of the current node in the TST
            // If the current letter is less than the current node value, then go left
            if (current < node.value) {
                // Create new node if nothing is there
                if (node.leftChild == null) {
                    node.leftChild = new Node(current);
                }
                node = node.leftChild;
            }
            // If the current letter is greater than the current node value, then go right
            else if (current > node.value) {
                // Create new node if nothing is there
                if (node.rightChild == null) {
                    node.rightChild = new Node(current);
                }
                node = node.rightChild;
            }
            // If the current letter equals the current node value
            else {
                // If we are on the last letter mark that this is a word in the TST and exit
                if (index == last) {
                    node.setWord();
                    return;
                }
                // If we are not on the last letter than continue to the next letter by increasing index and move on
                index++;
                current = word.charAt(index);
                // Create new node if nothing is there
                if (node.middleChild == null) {
                    node.middleChild = new Node(current);
                }
                node = node.middleChild;
            }
        }
    }

    // Insert recursively
    private Node insertRecursive(Node node, String word, int index) {
        // Get the current letter
        char current = word.charAt(index);

        // Create new node if nothing is there
        if (node == null) {
            node = new Node(current);
        }

        // Check current against the value of the current node
        // If the current letter is less, go left
        if (current < node.value) {
            node.leftChild = insertRecursive(node.leftChild, word, index);
        }
        // If the current letter is greater, go right
        else if (current > node.value) {
            node.rightChild = insertRecursive(node.rightChild, word, index);
        }
        // If the current letter equals the current node value
        else {
            // If we are NOT on the last letter
            if (index < word.length() - 1) {
                // Move on to the next letter by increasing the index and continue down
                node.middleChild = insertRecursive(node.middleChild, word, index + 1);
            }
            // If we are on the last letter
            else {
                // Make sure our node marks that it ends a word
                node.setWord();
            }
        }
        // Return current place
        return node;
    }

    // Lookup if a given word is in the TST
    public boolean lookup(String word) {
        // Return false if there is no word to look up
        if (word == null || word.isEmpty()) {
            return false;
        }
        // Lookup two different ways: One iteratively, and one recursively
        return lookupIterative(root, word);
        //return lookupRecursive(root, word, 0);
    }

    // Iteratively lookup a word
    public boolean lookupIterative(Node node, String word) {
        // Return false if there is nothing in the TST
        if (node == null) {
            return false;
        }

        // Initialize index and calculate last index of word to avoid multiple calculations during the loop
        int index = 0;
        int last = word.length() - 1;

        // Keep going until we don't match
        while (node != null) {
            // Get current letter
            char current = word.charAt(index);

            // Check the current letter against value of current node
            // If current letter is less than node value go left
            if (current < node.value) {
                node = node.leftChild;
            }
            // If current letter is greater than node value go right
            else if (current > node.value) {
                node = node.rightChild;
            }
            // If current letter equals node value
            else {
                // If we are on the last letter
                if (index == last) {
                    // Return if we found a word or note
                    return node.isWord();
                }
                // If we aren't on the last letter move on and go down
                index++;
                node = node.middleChild;
            }
        }
        // Return false if we didn't get a match and exited the loop
        return false;
    }

    // Lookup Recursively
    public boolean lookupRecursive(Node node, String word, int index) {
        // If there is nothing in the TST return false
        if (node == null) {
            return false;
        }

        // Get the current letter
        char current = word.charAt(index);

        // Check the current letter against the current node value
        // If the current letter is less than the current node value go left
        if (current < node.value) {
            return lookupRecursive(node.leftChild, word, index);
        }
        // If the current letter is greater than the current node value go right
        else if (current > node.value) {
            return lookupRecursive(node.rightChild, word, index);
        }
        // If the current letter is equal than the current node value
        else {
            // If we are NOT on the last the move on to next letter by incrementing index
            if (index < word.length() - 1) {
                return lookupRecursive(node.middleChild, word, index + 1);
            }
            // If we are on the last letter return if the node ends a word or not
            return node.isWord();
        }
    }
}

package algo.arraysandstrings;

import java.util.HashMap;

/**
 * Created by ndreddy on 13/04/17. We can use a trie. If you've never heard of a trie, think of it this way:
 * <p>
 * Let's make visited a nested hash map where each map has keys of just one character. So we would store 'google.com' as
 * visited['g']['o']['o']['g']['l']['e']['.']['c']['o']['m']['*'] = true.
 * <p>
 * The '*' at the end means 'this is the end of an entry'. Otherwise we wouldn't know what parts of visited are real
 * URLs and which parts are just prefixes. In the example above, 'google.co' is a prefix that we might think is a
 * visited URL if we didn't have some way to mark 'this is the end of an entry.'
 * <p>
 * Now when we go to enqueue 'google.com/maps' to visited, we only have to enqueue the characters '/maps', because the
 * 'google.com' prefix is already there. Same with 'google.com/about/jobs'.
 * <p>
 * We can visualize this as a tree, where each character in a string corresponds to a node. To check if a string is in
 * the trie, we just descend from the root of the tree to a leaf, checking for a node in the tree for each character of
 * in string.
 */
public class Crawler {

    class TrieNode {

        private HashMap<Character, TrieNode> nodeChildren;

        public TrieNode() {
            this.nodeChildren = new HashMap<Character, TrieNode>();
        }

        public boolean hasChildNode(char character) {
            return this.nodeChildren.containsKey(character);
        }

        public void makeChildNode(char character) {
            this.nodeChildren.put(character, new TrieNode());
        }

        public TrieNode getChildNode(char character) {
            return this.nodeChildren.get(character);
        }
    }

    public class Trie {

        private TrieNode rootNode;
        private final char endOfWordMarker = '\0';

        public Trie() {
            this.rootNode = new TrieNode();
        }

        public boolean checkPresentAndAdd(String word) {

            TrieNode currentNode = this.rootNode;
            boolean isNewWord = false;

            // Work downwards through the trie, adding nodes
            // as needed, and keeping track of whether we enqueue
            // any nodes.
            for (int i = 0; i < word.length(); i++) {
                char character = word.charAt(i);

                if (!currentNode.hasChildNode(character)) {
                    isNewWord = true;
                    currentNode.makeChildNode(character);
                }

                currentNode = currentNode.getChildNode(character);
            }

            // Explicitly mark the end of a word.
            // Otherwise, we might say a word is
            // present if it is a prefix of a different,
            // longer word that was added earlier.
            if (!currentNode.hasChildNode(endOfWordMarker)) {
                isNewWord = true;
                currentNode.makeChildNode(endOfWordMarker);
            }

            return isNewWord;
        }
    }
}

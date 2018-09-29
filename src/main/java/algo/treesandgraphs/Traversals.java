package algo.treesandgraphs;

public class Traversals {
    public static void visit(TreeNode node) {
        if (node != null) {
            System.out.println(node.data);
        }
    }

    /**
     * In-order (LNR)
     *
     * Check if the current node is empty or null.
     * Traverse the left subtree by recursively calling the in-order function.
     * Display the data part of the root (or current node).
     * Traverse the right subtree by recursively calling the in-order function.
     *
     * In a binary search tree, in-order traversal retrieves data in sorted order.
     *
     * @param node
     */
    public static void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            visit(node);
            inOrderTraversal(node.right);
        }
    }

    /**
     * Pre-order (NLR)
     *
     * Check if the current node is empty or null.
     * Display the data part of the root (or current node).
     * Traverse the left subtree by recursively calling the pre-order function.
     * Traverse the right subtree by recursively calling the pre-order function.
     *
     * @param node
     */
    public static void preOrderTraversal(TreeNode node) {
        if (node != null) {
            visit(node);
            inOrderTraversal(node.left);
            inOrderTraversal(node.right);
        }
    }

    /**
     * Post-order (LRN)
     *
     * Check if the current node is empty or null.
     * Traverse the left subtree by recursively calling the post-order function.
     * Traverse the right subtree by recursively calling the post-order function.
     * Display the data part of the root (or current node).
     *
     * @param node
     */
    public static void postOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            inOrderTraversal(node.right);
            visit(node);
        }
    }


    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // We needed this code for other files, so check out the code in the library
        TreeNode root = TreeNode.createMinimalBST(array);
        inOrderTraversal(root);
    }

}
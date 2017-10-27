package algo.tree;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Write a function to see if a binary tree is "balanced".
 *
 * A tree is "balanced" if the difference between the depths of any two leaf nodes is no greater than one.
 */
public class BinaryTreeBalancedCheck {


    /**
     * Gets height of a node
     * @param node
     * @return
     */
    public static int getHeight(TreeNode node) {

        // Tree with no nodes is balanced.
        if (node == null) {
            return -1;
        }

        // Get the left height recursively
        int leftHeight = getHeight(node.left);
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // Propagate error up


        // Get the right height recursively
        int rightHeight = getHeight(node.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // Propagate error up

        System.out.println(" Height of Node - " + node.data + ": left = " + leftHeight + " right = " + rightHeight) ;
        // Calculate the difference
        int heightDiff = Math.abs(leftHeight - rightHeight);

        // If diff > 1 then not balanced
        // If diff <=1
        if (heightDiff > 1) {
            return Integer.MIN_VALUE; // Found error -> pass it back
        }
        //height = 1 + max of left height and right heights
        else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }


    /**
     * Checks for balance
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        return getHeight(root) != Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        // Create balanced tree
        int[] array = {0, 1, 2, 3, 5, 6, 7, 8, 9, 10};
        TreeNode root = TreeNode.createMinimalBST(array);


        System.out.println("Is balanced? " + isBalanced(root));

        root.insertInOrder(4); // Add 4 to make it unbalanced

        System.out.println("Is balanced? " + isBalanced(root));

        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.left.left.left = new TreeNode(8);

        System.out.println("Is My tree balanced? " + isBalanced(tree));


    }


    /*************************** Interview cake Solution *****************/
    private static class NodeDepthPair {

        TreeNode node;
        int depth;

        NodeDepthPair(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public static boolean isBalancedTree(TreeNode treeRoot) {

        // a tree with no nodes is superbalanced, since there are no leaves!
        if (treeRoot == null) {
            return true;
        }

        // we short-circuit as soon as we find more than 2
        List<Integer> depths = new ArrayList<>(3);

        // nodes will store pairs of a node and the node's depth
        Stack<NodeDepthPair> nodes = new Stack<>();
        nodes.push(new NodeDepthPair(treeRoot, 0));

        while (!nodes.empty()) {

            // pop a node and its depth from the top of our stack
            NodeDepthPair nodeDepthPair = nodes.pop();
            TreeNode node = nodeDepthPair.node;
            int depth = nodeDepthPair.depth;

            // case: we found a leaf
            if (node.left == null && node.right == null) {

                // we only care if it's a new depth
                if (!depths.contains(depth)) {
                    depths.add(depth);

                    // two ways we might now have an unbalanced tree:
                    //   1) more than 2 different leaf depths
                    //   2) 2 leaf depths that are more than 1 apart
                    if (depths.size() > 2 || (depths.size() == 2
                            && Math.abs(depths.get(0) - depths.get(1)) > 1)) {
                        return false;
                    }
                }

                // case: this isn't a leaf - keep stepping down
            } else {
                if (node.left != null) {
                    nodes.push(new NodeDepthPair(node.left, depth + 1));
                }
                if (node.right != null) {
                    nodes.push(new NodeDepthPair(node.right, depth + 1));
                }
            }
        }

        return true;
    }
}
package algo.treesandgraphs;

import java.util.Stack;

/**
 * Java implementation to check if given Binary tree is a BST or not.
 *
 */
public class BinarySearchTreeCheck {


    /* Class containing left and right child of current
     node and key data*/
    static class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    //Root of the Binary Tree
    Node root;

    /* can give min and max data according to your code or
    can write a function to find min and max data of tree. */

    /* returns true if given search tree is binary
     search tree (efficient version) */
    boolean checkBST() {
        return checkBST(root, Integer.MIN_VALUE,
                Integer.MAX_VALUE);
    }

    /* Returns true if the given tree is a BST and its
      values are >= min and <= max. */
    boolean checkBST(Node n, int min, int max) {
        /* Constraint 1: an empty tree is BST */
        if (n == null) return true;

        /* Constraint 2: false if this node violates the min/max constraints */
        if (n.data < min || n.data > max) return false;

        /* otherwise check the subtrees recursively
        tightening the min/max constraints */
        // Allow only distinct values
        return (checkBST(n.left, min, n.data - 1) &&
                checkBST(n.right, n.data + 1, max));
    }


    private static class NodeBounds {

        Node node;
        int min;
        int max;

        NodeBounds(Node node, int lowerBound, int upperBound) {
            this.node = node;
            this.min = lowerBound;
            this.max = upperBound;
        }
    }

    public static boolean isBinarySearchTree(Node root) {

        // start at the root, with an arbitrarily low lower bound
        // and an arbitrarily high upper bound
        Stack<NodeBounds> stack = new Stack<>();
        stack.push(new NodeBounds(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

        // depth-first traversal
        while (!stack.empty()) {
            NodeBounds nb = stack.pop();
            Node node = nb.node;
            int lowerBound = nb.min;
            int upperBound = nb.max;

            // if this node is invalid, we return false right away
            if (node.data <= lowerBound || node.data >= upperBound) {
                return false;
            }

            if (node.left != null) {
                // this node must be less than the current node
                stack.push(new NodeBounds(node.left, lowerBound, node.data));
            }
            if (node.right != null) {
                // this node must be greater than the current node
                stack.push(new NodeBounds(node.right, node.data, upperBound));
            }
        }

        // if none of the nodes were invalid, return true
        // (at this point we have checked all nodes)
        return true;
    }


    /* Driver program to test above functions */
    public static void main(String args[]) {
        BinarySearchTreeCheck tree = new BinarySearchTreeCheck();
        tree.root = new Node(4);
        tree.root.left = new Node(2);
        tree.root.right = new Node(5);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(3);

        if (tree.checkBST())
            System.out.println("IS BST");
        else
            System.out.println("Not a BST");
    }
}

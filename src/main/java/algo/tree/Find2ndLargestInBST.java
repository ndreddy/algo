package algo.tree;

/**
 * Write a function to find the 2nd largest element in a binary search tree
 */
public class Find2ndLargestInBST {

    private static int findLargest(TreeNode root) {
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    public static int findLargestRecr(TreeNode root) {
        if (root.right != null) {
            return findLargest(root.right);
        }
        return root.data;
    }

    public static int findSecondLargest(TreeNode rootNode) {
        if (rootNode == null || (rootNode.left == null
                && rootNode.right == null)) {
            throw new IllegalArgumentException("Tree must have at least 2 nodes");
        }

        TreeNode current = rootNode;

        while (true) {

            // case: current is largest and has a left subtree
            // 2nd largest is the largest in that subtree
            if (current.left != null && current.right == null) {
                return findLargest(current.left);
            }

            // case: current is parent of largest, and largest has no children,
            // so current is 2nd largest
            if (current.right != null &&
                    current.right.left == null &&
                    current.right.right == null) {
                return current.data;
            }

            current = current.right;
        }
    }



    public static void main(String[] args) {
        // Create balanced tree
        int[] array = {0, 1, 2, 3, 5, 6, 7, 8, 9, 10};
        TreeNode tree1 = TreeNode.createMinimalBST(array);

        System.out.println("Second Largest in this BST2 is :   " + findSecondLargest(tree1));

        TreeNode tree2 = new TreeNode(5);
        tree2.left = new TreeNode(3);
        tree2.left.left = new TreeNode(1);
        tree2.left.right = new TreeNode(4);

        tree2.right = new TreeNode(8);
        tree2.right.left = new TreeNode(7);
        tree2.right.right = new TreeNode(12);
        tree2.right.right.left = new TreeNode(10);
        tree2.right.right.left.left = new TreeNode(9);
        tree2.right.right.left.right = new TreeNode(11);

        System.out.println("Second Largest in this BST2 is :   " + findSecondLargest(tree2));


    }
}

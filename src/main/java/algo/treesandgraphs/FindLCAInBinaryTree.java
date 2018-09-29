package algo.treesandgraphs;

/**
 * Given a binary tree (not a binary search tree) and two values say n1 and n2,
 * write a program to find the least common ancestor.
 */
public class FindLCAInBinaryTree {


    /**
     * This function returns pointer to LCA of two given
     * values n1 and n2. This function assumes that n1 and
     * n2 are present in Binary Tree
     *
     * @param node
     * @param n1
     * @param n2
     * @return
     */
    static TreeNode findLCA(TreeNode node, int n1, int n2) {
        // Base case
        if (node == null)
            return null;

        // If either n1 or n2 matches with root's key, report
        // the presence by returning root (Note that if a key is
        // ancestor of other, then the ancestor key becomes LCA
        if (node.data == n1 || node.data == n2)
            return node;

        // Look for keys in left and right subtrees
        TreeNode left_lca = findLCA(node.left, n1, n2);
        TreeNode right_lca = findLCA(node.right, n1, n2);

        // If both of the above calls return Non-NULL, then one key
        // is present in once subtree and other is present in other,
        // So this node is the LCA
        if (left_lca != null && right_lca != null)
            return node;

        // Otherwise check if left subtree or right subtree is LCA
        return (left_lca != null) ? left_lca : right_lca;
    }


    /* The following is for BST  */

    /**
     * Function to find LCA of n1 and n2. The function assumes that both
     * n1 and n2 are present in BST
     *
     * @param node
     * @param n1
     * @param n2
     * @return
     */
    TreeNode findLCAInBST(TreeNode node, int n1, int n2) {
        if (node == null)
            return null;

        // If both n1 and n2 are smaller than root, then LCA lies in left
        if (node.data > n1 && node.data > n2)
            return findLCAInBST(node.left, n1, n2);

        // If both n1 and n2 are greater than root, then LCA lies in right
        if (node.data < n1 && node.data < n2)
            return findLCAInBST(node.right, n1, n2);

        return node;
    }

    public static void main(String args[]) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(7);
        System.out.println("LCA(4, 5) = " +
                findLCA(tree, 4, 5).data);
        System.out.println("LCA(4, 6) = " +
                findLCA(tree, 4, 6).data);
        System.out.println("LCA(3, 4) = " +
                findLCA(tree, 3, 4).data);
        System.out.println("LCA(2, 4) = " +
                findLCA(tree, 2, 4).data);
    }
}


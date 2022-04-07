import pytest
from binary_tree import list_tree_to_binary_tree


def is_binary_search_tree(root):
    # Start at the root, with an arbitrarily low lower bound
    # and an arbitrarily high upper bound
    node_and_bounds_stack = [(root, -float('inf'), float('inf'))]

    # Depth-first traversal
    while len(node_and_bounds_stack):
        node, lower_bound, upper_bound = node_and_bounds_stack.pop()

        # If this node is invalid, we return false right away
        if (node.value <= lower_bound) or (float(node.value) >= upper_bound):
            return False

        if node.left:
            # This node must be less than the current node
            node_and_bounds_stack.append((node.left, lower_bound, node.value))
        if node.right:
            # This node must be greater than the current node
            node_and_bounds_stack.append((node.right, node.value, upper_bound))

    # If none of the nodes were invalid, return true
    # (at this point we have checked all nodes)
    return True


def is_binary_search_tree_recursive(root,
                                    lower_bound=-float('inf'),
                                    upper_bound=float('inf')):
    if not root:
        return True

    if root.value >= upper_bound or root.value <= lower_bound:
        return False

    return (is_binary_search_tree(root.left, lower_bound, root.value)
            and is_binary_search_tree(root.right, root.value, upper_bound))


# [valid_bst, list_tree]
test_cases = [
    # [True, [1]],
    [False, [1, 2, None]],
    [True, [2, 1, 3]],
    [False, [2, 1, 2]],
    [False, [2, 1, 1]],
    [False, [3, 2, 1]],
    [True, [12, [10, 8, 11], [15, 14, 16]]],
    [False, [12, [10, 8, 13], [15, 10, 16]]],
    [False, [12, [10, 8, 11], [11, 12, 16]]],
    [False, [12, [10, 8, 11], [13, 14, 16]]],
]


@pytest.mark.parametrize("expected, list_tree", test_cases)
def test_is_bst(expected, list_tree):
    bt = list_tree_to_binary_tree(list_tree)
    assert is_binary_search_tree(bt) == expected


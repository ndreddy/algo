import pytest
from binary_tree import list_tree_to_binary_tree


######################################################################
# https://www.interviewcake.com/question/python3/balanced-binary-tree

# Write a function to see if a binary tree is "superbalanced" (a new
# tree property we just made up).

# A tree is "superbalanced" if the difference between the depths of any
# two leaf nodes is no greater than one.

######################################################################

def is_balanced(tree_root):
    # A tree with no nodes is superbalanced, since there are no leaves!
    if tree_root is None:
        return True

    # We short-circuit as soon as we find more than 2
    depths = []

    # We'll treat this list as a stack that will store tuples of (node, depth)
    nodes = [(tree_root, 0)]

    while len(nodes):
        # Pop a node and its depth from the top of our stack
        node, depth = nodes.pop()

        # Case: we found a leaf
        if (not node.left) and (not node.right):
            # We only care if it's a new depth
            if depth not in depths:
                depths.append(depth)

                # Two ways we might now have an unbalanced tree:
                #   1) more than 2 different leaf depths
                #   2) 2 leaf depths that are more than 1 apart
                if ((len(depths) > 2) or
                        (len(depths) == 2 and abs(depths[0] - depths[1]) > 1)):
                    return False
        else:
            # Case: this isn't a leaf - keep stepping down
            if node.left:
                nodes.append((node.left, depth + 1))
            if node.right:
                nodes.append((node.right, depth + 1))

    return True


# [caseno, superbalanced, list_tree]
test_cases = [
    [0, True, [1]],
    [1, True, [1, None, None]],
    [2, True, [1, 2, None]],
    [3, True, [1, 2, 3]],
    [4, True, [1, [2, None, None], None]],
    [5, True, [1,
               ['2l',
                '3ll',
                '3lr'],
               None]],
    [6, True, [1,
               ['2l', ['3ll', '4lll', '4llr'], '3lr'],
               ['2r', ['3rl', '4rll', '4rlr'], '3rr']]],
    [7, True, [5, [10, 20, 25], [15, 30, 35]]],
    [8, False, [
        5, [10, 20, [25, 40, None]], [15, None, None]]],
    [9, False, [5,
                [10, None, None],
                [15,
                 None,
                 [20,
                  [25,
                   [30, 40]]]]]],
    [10, False, [5,
                 10,
                 [15, None, [20, [25, [30, 40]]]]]],
    [11, True, [5,
                [10, [20, 40, 45], [25, 50, 55]],
                [15, [30, 60, 65], [35, 70, 75]]]],
    [12, True, [5,
                [10,
                 [20,
                  [40, 80, 90],
                  [45, 95, 100]],
                 [25,
                  [50, 105, 110],
                  [55, 115, 120]]],
                [15,
                 [30,
                  [60, 125, 130],
                  [65, 135, 140]],
                 [35,
                  [70, 145, 150],
                  [75, 155, 160]]]]],
    [13, False, [5,
                 [10,
                  [20,
                   [40, 80, 90],
                   [45, 95, 100]],
                  25],
                 [15,
                  [30,
                   [60, 125, 130],
                   [65, 135, 140]],
                  [35,
                   [70, 145, 150],
                   [75, 155, 160]]]]],
    [14, False, [5,
                 [10,
                  [20,
                   [40, 80],
                   [45, 95, 100]],
                  [25,
                   [50],
                   [55, 115, 120]]],
                 [15,
                  [30,
                   [60, 125, [130, 165]],
                   [65, 135, 140]],
                  [35,
                   [70, 145, 150],
                   [75, 155, 160]]]]],
    [15, True, [5,
                [10,
                 [20,
                  [40, 80]]]]],
]


# [caseno, superbalanced, list_tree]
@pytest.mark.parametrize("caseno, expected, list_tree", test_cases)
def test_is_balanced(caseno, expected, list_tree):
    bt = list_tree_to_binary_tree(list_tree)
    assert is_balanced(bt) == expected

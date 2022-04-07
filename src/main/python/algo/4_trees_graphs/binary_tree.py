class BinaryTreeNode(object):

    def __init__(self, value: int) -> object:
        self.value = value
        self.left = None
        self.right = None

    def insert_left(self, value):
        self.left = value
        return self.left

    def insert_right(self, value):
        self.right = value
        return self.right


def list_tree_to_binary_tree(list_tree):
    # this just helps with short hand "trees"
    # of forms 1, or [1] changing it into [1, None, None]
    # then truncating
    if type(list_tree) is not list:
        list_tree = [list_tree, None, None]
    list_tree = list_tree + [None, None, None]
    list_tree = list_tree[0:3]

    (value, left, right) = list_tree

    node = BinaryTreeNode(value)

    if left:
        node.insert_left(list_tree_to_binary_tree(left))

    if right:
        node.insert_right(list_tree_to_binary_tree(right))

    return node

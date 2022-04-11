class BinaryTree:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None


def nodeDepths(root, level=0):
    if root is None:
        return 0

    return level + nodeDepths(root.left, level + 1) + nodeDepths(root.right, level + 1)

def test_one_node():
    tree = None
    assert nodeDepths(tree) == 0

def test_one_node():
    tree = BinaryTree(1)
    assert nodeDepths(tree) == 0

def test_one_left_node():
    tree = BinaryTree(1)
    tree.left = BinaryTree(2)
    assert nodeDepths(tree) == 1

def test_one_right_node():
    tree = BinaryTree(1)
    tree.right = BinaryTree(3)
    assert nodeDepths(tree) == 1

def test_one_left_and_right_node():
    tree = BinaryTree(1)
    tree.left = BinaryTree(2)
    tree.right = BinaryTree(3)
    assert nodeDepths(tree) == 2
class BST:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None


def findClosestValueInBst(tree, target):
    def isCloser(value, target, best):
        return abs(target - value) < abs(target - best)

    def search(tree, target, best):
        if not tree:
            return best

        current = tree.value

        if current is target:
            return current

        if isCloser(current, target, best):
            best = current

        direction = tree.left if target < current else tree.right

        return search(direction, target, best)

    return search(tree, target, tree.value)


def test_sample():
    root = BST(10)
    root.left = BST(5)
    root.left.left = BST(2)
    root.left.left.left = BST(1)
    root.left.right = BST(5)
    root.right = BST(15)
    root.right.left = BST(13)
    root.right.left.right = BST(14)
    root.right.right = BST(22)
    expected = 13
    actual = findClosestValueInBst(root, 12)
    assert expected == actual

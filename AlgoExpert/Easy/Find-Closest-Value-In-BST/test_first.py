class BST:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None


def foo(tree, target, best):
    if not tree:
        if not best:
            return best
        else:
            return best[0]

    difference = abs(target - tree.value)

    if not best:
        best = [tree.value, difference]

    _, b_diff = best
    if difference <= b_diff:
        best = [tree.value, difference]

    if tree.value == target:
        return tree.value

    if target < tree.value:
        return foo(tree.left, target, best)

    if target >= tree.value:
        return foo(tree.right, target, best)


def findClosestValueInBst(tree, target):
    return foo(tree, target, None)


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

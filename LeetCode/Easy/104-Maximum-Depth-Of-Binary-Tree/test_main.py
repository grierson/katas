import main


def test_null():
    sut = main.Solution()
    assert sut.maxDepth(None) == 0
    assert sut.maxDepthIter(None) == 0


def test_One():
    node = main.TreeNode(1)
    sut = main.Solution()
    assert sut.maxDepth(node) == 1
    assert sut.maxDepthIter(node) == 1


def test_only_left():
    left = main.TreeNode(2)
    node = main.TreeNode(1, left)
    sut = main.Solution()
    assert sut.maxDepth(node) == 2
    assert sut.maxDepthIter(node) == 2


def test_right_right():
    right_right = main.TreeNode(3)
    right = main.TreeNode(2, None, right_right)
    node = main.TreeNode(1, None, right)
    sut = main.Solution()
    assert sut.maxDepth(node) == 3
    assert sut.maxDepthIter(node) == 3

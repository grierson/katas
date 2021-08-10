class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def rangeSumBST(self, root: TreeNode, low: int, high: int) -> int:
        if not root:
            return 0
        elif root.val >= low and root.val <= high:  # Within range
            return root.val + self.rangeSumBST(root.left, low, high) + self.rangeSumBST(root.right, low, high)
        elif root.val < low:  # Only look at greater than
            return self.rangeSumBST(root.right, low, high)
        else:  # Only look at less than
            return self.rangeSumBST(root.left, low, high)


def test_example1():
    tree = TreeNode(10,
                    TreeNode(5, TreeNode(3), TreeNode(7)),
                    TreeNode(15, None, TreeNode(18)))

    sut = Solution()
    assert sut.rangeSumBST(tree, 7, 15) == 32

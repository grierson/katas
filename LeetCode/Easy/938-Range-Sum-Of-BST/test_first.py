from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def __init__(self):
        self.array = []

    def rangeSumBST(self, root: Optional[TreeNode], low: int, high: int) -> int:
        queue = [root]

        while len(queue) > 0:
            current = queue.pop(0)
            if (current.val >= low and current.val <= high):
                self.array.append(current.val)

            if (current.right):
                queue.append(current.right)
            if (current.left):
                queue.append(current.left)

        return sum(self.array)


def test_example1():
    tree = TreeNode(10,
                    TreeNode(5, TreeNode(3), TreeNode(7)),
                    TreeNode(15, None, TreeNode(18)))

    sut = Solution()
    assert sut.rangeSumBST(tree, 7, 15) == 32

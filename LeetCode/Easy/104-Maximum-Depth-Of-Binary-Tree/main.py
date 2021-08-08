class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def maxDepth(self, root):
        if (root is None):
            return 0

        return 1 + max(self.maxDepth(root.left), self.maxDepth(root.right))

    def maxDepthIter(self, root):
        stack = [[root, 1]]
        counter = 0

        while stack:
            node, depth = stack.pop()

            if node:
                counter = max(counter, depth)
                new_depth = depth + 1
                stack.append([node.left, new_depth])
                stack.append([node.right, new_depth])

        return counter

class BinaryTree:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None

    def insert(self, values, i=0):
        if i >= len(values):
            return
        queue = [self]
        while len(queue) > 0:
            current = queue.pop(0)
            if current.left is None:
                current.left = BinaryTree(values[i])
                break
            queue.append(current.left)
            if current.right is None:
                current.right = BinaryTree(values[i])
                break
            queue.append(current.right)
        self.insert(values, i + 1)
        return self


def branchSums(root):
    sums = []

    def calculateSum(node, running):
        if not node:
            return

        running += node.value

        if not node.left and not node.right:
            sums.append(running)
            return

        calculateSum(node.left, running)
        calculateSum(node.right, running)

    calculateSum(root, 0)
    return sums


def test_foo():
    tree = BinaryTree(1).insert([2, 3, 4, 5, 6, 7, 8, 9, 10])
    assert branchSums(tree) == [15, 16, 18, 10, 11]
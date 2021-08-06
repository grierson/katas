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


def foo(node, running, sums):
    if not node:
        return sums

    running += node.value

    if not node.left and not node.right:
        sums.append(running)
        return

    foo(node.left, running, sums)
    foo(node.right, running, sums)


def branchSums(root):
    sums = []
    foo(root, 0, sums)
    return sums


def test_foo():
    tree = BinaryTree(1).insert([2, 3, 4, 5, 6, 7, 8, 9, 10])
    assert branchSums(tree) == [15, 16, 18, 10, 11]


test_foo()

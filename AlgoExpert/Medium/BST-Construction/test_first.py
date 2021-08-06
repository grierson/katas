class BST:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None

    def insert(self, value):
        if value < self.value:
            if self.left is None:
                self.left = BST(value)
            else:
                self.left.insert(value)

        if value > self.value:
            if self.right is None:
                self.right = BST(value)
            else:
                self.right.insert(value)

        return self

    def contains(self, value):
        if value == self.value:
            return True

        if value < self.value and self.left:
            return self.left.contains(value)
        if self.right:
            return self.right.contains(value)

        return False

    def remove(self, value):
        if value < self.value:
            if self.left:
                if value == self.left.value:
                    temp = self.left.left
                    self.left = self.left.left
                    self.left.right = temp

        if value > self.value:
            if self.right:
                if value == self.right.value:
                    temp = self.right.right
                    self.right = self.right.left
                    self.right.right = temp

        return self


def test_contains():
    actual = BST(1)
    assert actual.contains(1) == True


def test_not_contains():
    actual = BST(1)
    assert actual.contains(2) == False


def test_contains_nested():
    actual = BST(1)
    actual.right = BST(2)
    assert actual.contains(2) == True


def test_inserst():
    actual = BST(1)
    actual.insert(2)
    expected = BST(1)
    expected.right = BST(2)
    assert actual.right.value == expected.right.value


def test_inserst_right_left():
    actual = BST(1)
    actual.right = BST(3)
    actual.insert(2)

    expected = BST(1)
    expected.right = BST(3)
    expected.right.left = BST(2)
    assert actual.right.left.value == expected.right.left.value

def test_remove():
    actual = BST(1)
    actual.right = BST(3)
    actual.right.left()
    actual.insert(2)

    expected = BST(1)
    expected.right = BST(3)
    expected.right.left = BST(2)
    assert actual.right.left.value == expected.right.left.value
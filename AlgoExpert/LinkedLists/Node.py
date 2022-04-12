class Node:
    def __init__(self, value):
        self.value = value
        self.next = None


def insert(node, value):
    current = node
    while(current.next):
        current = current.next

    current.next = Node(value)


def printLL(node):
    current = node
    state = []

    while(current):
        state.append(current.value)
        current = current.next

    return state


def equal(left, right):
    while left and right and left.value == right.value:
        left = left.next
        right = right.next
    if not left and not right:
        return True
    return False

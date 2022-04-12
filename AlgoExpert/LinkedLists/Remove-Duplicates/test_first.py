import sys
sys.path.append("..")
from Node import Node, insert, printLL, equal


def removeDuplicates(node):
    current = node

    while current:
        while current.next and current.value == current.next.value:
            current.next = current.next.next
        current = current.next

    return node


def test_repeat():
    actual = Node(1)
    insert(actual, 2)
    insert(actual, 2)

    removeDuplicates(actual)

    expected = Node(1)
    insert(expected, 2)

    print(printLL(actual))
    print(printLL(expected))
    assert equal(actual, expected)

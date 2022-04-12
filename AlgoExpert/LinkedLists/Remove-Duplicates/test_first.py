import sys
sys.path.append("..")
from Node import Node, insert, printLL, equal


def removeDuplicates(node):
    if node and node.next:
        node.next = removeDuplicates(node.next)

        return node.next if node.next.value is node.value else node

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

class Node:
    def __init__(self, name):
        self.children = []
        self.name = name

    def addChild(self, name):
        self.children.append(Node(name))
        return self

    def depthFirstSearch(self, array):
        array.append(self.name)

        for child in self.children:
            child.depthFirstSearch(array)

        return array

A = Node("A")
B = Node("B")
C = Node("C")
D = Node("D")
E = Node("E")
F = Node("F")
G = Node("G")
H = Node("H")
I = Node("I")
J = Node("J")
K = Node("K")

A.children = [B, C, D]
B.children = [E, F]
F.children = [I, J]
D.children = [G, H]
G.children = [K]

A.depthFirstSearch([])

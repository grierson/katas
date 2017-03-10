import unittest
from src import chop


class ChopTest(unittest.TestCase):
    def test_empyt_list(self):
        assert chop(3, []) == -1

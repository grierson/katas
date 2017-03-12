import unittest
from chop import chop


class TestChop(unittest.TestCase):
    def test_empty_list(self):
        self.assertEqual(chop(3, []), -1)

    def test_number_not_in_numbers(self):
        self.assertEqual(chop(4, [1, 2, 3]), -1)
        self.assertEqual(chop(0, [1, 3, 5]), -1)
        self.assertEqual(chop(2, [1, 3, 5]), -1)
        self.assertEqual(chop(4, [1, 3, 5]), -1)
        self.assertEqual(chop(6, [1, 3, 5]), -1)

    def test_number_at_first_index(self):
        self.assertEqual(chop(1, [1]), 0)

    def test_number_at_second_index(self):
        self.assertEqual(chop(2, [1, 2]), 1)

    def test_number_at_last_index(self):
        self.assertEqual(chop(7, [1, 3, 5, 7]), 3)

    def test_orginal_tests(self):
        self.assertEqual(chop(3, []), -1)
        self.assertEqual(chop(3, [1]), -1)
        self.assertEqual(chop(1, [1]), 0)

        self.assertEqual(chop(1, [1, 3, 5]), 0)
        self.assertEqual(chop(3, [1, 3, 5]), 1)
        self.assertEqual(chop(5, [1, 3, 5]), 2)
        self.assertEqual(chop(0, [1, 3, 5]), -1)
        self.assertEqual(chop(2, [1, 3, 5]), -1)
        self.assertEqual(chop(4, [1, 3, 5]), -1)
        self.assertEqual(chop(6, [1, 3, 5]), -1)

        self.assertEqual(chop(1, [1, 3, 5, 7]), 0)
        self.assertEqual(chop(3, [1, 3, 5, 7]), 1)
        self.assertEqual(chop(5, [1, 3, 5, 7]), 2)
        self.assertEqual(chop(7, [1, 3, 5, 7]), 3)
        self.assertEqual(chop(0, [1, 3, 5, 7]), -1)
        self.assertEqual(chop(2, [1, 3, 5, 7]), -1)
        self.assertEqual(chop(4, [1, 3, 5, 7]), -1)
        self.assertEqual(chop(6, [1, 3, 5, 7]), -1)
        self.assertEqual(chop(8, [1, 3, 5, 7]), -1)

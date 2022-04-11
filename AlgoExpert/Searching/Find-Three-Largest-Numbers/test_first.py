def findThreeLargestNumbers(array):
    return sorted(array)[-3:]


def test_none():
    assert findThreeLargestNumbers([]) == []


def test_one():
    assert findThreeLargestNumbers([1]) == [1]


def test_two():
    assert findThreeLargestNumbers([1, 2]) == [1, 2]


def test_three():
    assert findThreeLargestNumbers([1, 2, 3]) == [1, 2, 3]


def test_four_asc():
    assert findThreeLargestNumbers([1, 2, 3, 4]) == [2, 3, 4]


def test_four_desc():
    assert findThreeLargestNumbers([4, 3, 2, 1]) == [2, 3, 4]

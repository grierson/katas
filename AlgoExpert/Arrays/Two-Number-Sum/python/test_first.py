def twoNumberSum(numbers, target):
    if len(numbers) <= 1:
        return []

    current, *rest = numbers

    for value in rest:
        if current + value == target:
            return [current, value]

    return twoNumberSum(rest, target)


def test_no_numbers():
    assert twoNumberSum([], 10) == []


def test_no_target():
    assert twoNumberSum([1, 2, 3], None) == []


def test_one_number():
    assert twoNumberSum([1], 1) == []


def test_simple_match():
    assert twoNumberSum([1, 2], 3) == [1, 2]


def test_sample():
    assert twoNumberSum([3, 5, -4, 8, 11, 1, -1, 6], 10) == [11, -1]


def test_no_match():
    assert twoNumberSum([1, 2, 3], 10) == []

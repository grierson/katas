def twoNumberSum(numbers, target):
    if target is None:
        return []

    seen = set()

    for number in numbers:
        difference = target - number
        if difference in seen:
            return [number, difference]
        else:
            seen.add(number)

    return []


def test_no_numbers():
    assert twoNumberSum([], 10) == []


def test_no_target():
    assert twoNumberSum([1, 2, 3], None) == []


def test_one_number():
    assert twoNumberSum([1], 1) == []


def test_simple_match():
    assert sorted(twoNumberSum([1, 2], 3)) == [1, 2]


def test_sample():
    assert sorted(twoNumberSum([3, 5, -4, 8, 11, 1, -1, 6], 10)) == [-1, 11]


def test_no_match():
    assert twoNumberSum([1, 2, 3], 10) == []

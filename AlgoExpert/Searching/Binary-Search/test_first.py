def binarySearch(numbers, target):
    def finder(left, right):
        if left > right:
            return -1

        middle = (left + right) // 2
        middle_number = numbers[middle]

        if middle_number == target:
            return middle

        if middle_number > target:
            return finder(left, middle - 1)

        return finder(middle + 1, right)

    return finder(0, len(numbers) - 1)


def test_none():
    assert binarySearch([], 1) == -1


def test_one_non_match():
    assert binarySearch([1], 9) == -1


def test_one_match():
    assert binarySearch([1], 1) == 0


def test_sample():
    assert binarySearch([0, 1, 21, 33, 45, 45, 61, 71, 72, 73], 33) == 3

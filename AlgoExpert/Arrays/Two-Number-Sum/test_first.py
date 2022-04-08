def twoNumberSum(array, targetSum):
    for idx, val in enumerate(array):
        for b in array[idx+1:]:
            if val + b == targetSum:
                return [val, b]

    return []


def test_bar():
    nums = [3, 5, -4, 8, 11, 1, -1, 6]
    target = 10

    actual = twoNumberSum(nums, target)

    assert 11 in actual
    assert -1 in actual


def test_none_match():
    nums = [1, 2, 3]
    target = 10

    actual = twoNumberSum(nums, target)

    assert actual == []


test_bar()

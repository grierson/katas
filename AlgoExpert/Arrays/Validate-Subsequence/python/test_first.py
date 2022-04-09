def isValidSubsequence(numbers, subsequence):
    state = []

    for number in numbers:
        if number in subsequence:
            state.append(number)
            if state == subsequence:
                return True

    return False


def test_numbers_not_in_coll():
    assert isValidSubsequence([1, 2, 3], [9]) == False

def test_all_ones():
    assert isValidSubsequence([1, 1, 1], [1]) == True

def test_example_cases():
    assert isValidSubsequence([1, 2, 3, 4], [1, 3, 4]) == True
    assert isValidSubsequence([1, 2, 3, 4], [2, 4]) == True
    assert isValidSubsequence(
        [5, 1, 22, 25, 6, -1, 8, 10], [1, 6, -1, 10]) == True

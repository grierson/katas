def isValidSubsequence(numbers, subsequence):
    state = []

    def inner(numbers):
        if not numbers:
            return False

        head, *tail = numbers

        if head in subsequence:
            state.append(head)
            if state == subsequence:
                return True

        return inner(tail)

    return inner(numbers)

def test_numbers_not_in_coll():
    assert isValidSubsequence([1, 2, 3], [9]) == False

def test_all_ones():
    assert isValidSubsequence([1, 1, 1], [1]) == True

def test_example_cases():
    assert isValidSubsequence([1, 2, 3, 4], [1, 3, 4]) == True
    assert isValidSubsequence([1, 2, 3, 4], [2, 4]) == True
    assert isValidSubsequence(
        [5, 1, 22, 25, 6, -1, 8, 10], [1, 6, -1, 10]) == True
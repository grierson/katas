import pytest


def isValidSubsequence(array, sequence):
    state = []

    for x in sequence:
        try:
            y = array.index(x)
            state.append(y)
            del array[y]
        except:
            return False

    return state == sorted(state)


@pytest.mark.parametrize("array, sequence, expected", [
    ([5, 1, 22, 25, 6, -1, 8, 10, 8], [1, 6, -1, 10], True),
    ([1, 2, 3], [1, 3], True),
    ([], [], True),
    ([1], [], True),
    ([], [1], False),
    ([1, 2, 3], [3, 1], False),
    ([1, 1, 2], [1, 2], True),
])
def test_sample(array, sequence, expected):
    assert isValidSubsequence(array, sequence) == expected
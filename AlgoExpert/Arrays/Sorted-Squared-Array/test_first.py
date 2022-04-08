import pytest


def sortedSquaredArray(array):
    return sorted(map(lambda x: x * x, array))


@pytest.mark.parametrize("array, expected", [
    ([1, 2, 3, 5, 6, 8, 9], [1, 4, 9, 25, 36, 64, 81]),
])
def test_sample(array, expected):
    assert sortedSquaredArray(array) == expected

def sortedSquaredArray(array):
    return sorted(map(lambda x: x * x, array))


def test_sample():
    assert sortedSquaredArray([1, 2, 3, 5, 6, 8, 9]) == [
        1, 4, 9, 25, 36, 64, 81]

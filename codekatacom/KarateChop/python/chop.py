def chop(number, numbers):
    minimum = 0
    maximum = len(numbers) - 1

    while minimum <= maximum:
        middle = (minimum + maximum)
        guess = numbers[middle]

        if guess == number:
            return middle
        if guess > number:
            maximum = middle - 1
        else:
            minimum = middle + 1

    return -1

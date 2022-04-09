def nonConstructibleChange(coins):
    coins.sort()

    current = 0

    for coin in coins:
        maximum = current + 1

        if coin > maximum:
            return maximum
        
        current += coin

    return current + 1
    

def test_two_coins():
    assert nonConstructibleChange([1, 1]) == 3
    assert nonConstructibleChange([1, 2]) == 4
    assert nonConstructibleChange([2, 3]) == 1
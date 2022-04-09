def isPalindrome(string):
    return string == string[::-1]

def test_samples():
    assert isPalindrome("a") == True
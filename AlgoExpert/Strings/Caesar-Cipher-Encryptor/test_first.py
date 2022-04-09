def caesarCipherEncryptor(string, offset):
    def rotate(char):
        unicodeToAlphabet = ord(char) - 97
        new_position = (unicodeToAlphabet + offset) % 26
        alphabetToUnicode = new_position + 97
        return chr(alphabetToUnicode)

    temp = ""

    for char in string:
        temp += rotate(char)

    return temp

def test_single_character():
    assert caesarCipherEncryptor("a", 1) == "b" 
    assert caesarCipherEncryptor("b", 1) == "c" 
    assert caesarCipherEncryptor("z", 1) == "a" 
    assert caesarCipherEncryptor("y", 2) == "a" 
    assert caesarCipherEncryptor("x", 2) == "z" 

def test_two_characters():
    assert caesarCipherEncryptor("abc", 1) == "bcd" 
    assert caesarCipherEncryptor("xyz", 2) == "zab" 

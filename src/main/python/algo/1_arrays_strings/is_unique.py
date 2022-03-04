import pytest


def is_unique_chars_algorithmic(string):
    # Assuming character set is ASCII (128 characters)
    if len(string) > 128:
        return False

    # this is a pythonic and faster way to initialize an array with a fixed value.
    char_set = [False] * 128
    for char in string:
        val = ord(char)
        if char_set[val]:
            return False
        else:
            char_set[val] = True

    return True


def is_unique_chars_pythonic(string: str) -> bool:
    char_set = set(string)
    return len(char_set) == len(string)


def is_unique_chars_dict(string):
    char_dict = {}
    for c in string:
        if c in char_dict:
            return False
        char_dict[c] = 1
    return True


def is_unique_chars_set(string: str) -> bool:
    char_set = set()
    for char in string:
        if char in char_set:
            return False
        char_set.add(char)
    return True


# O(NlogN)
def is_unique_chars_sorting(string: str) -> bool:
    sorted_string = sorted(string)
    last_character = None
    for char in sorted_string:
        if char == last_character:
            return False
        last_character = char
    return True


test_cases = [
    ("abcd", True),
    ("s4fad", True),
    ("", True),
    ("23ds2", False),
    ("hb 627jh=j ()", False),
    ("".join([chr(val) for val in range(128)]), True),  # unique 128 chars
    ("".join([chr(val // 2) for val in range(129)]), False),  # non-unique 129 chars
]


@pytest.mark.parametrize("text, expected", test_cases)
def test_is_unique_chars_algorithmic(text, expected):
    assert is_unique_chars_algorithmic(text) == expected
    assert is_unique_chars_pythonic(text) == expected
    assert is_unique_chars_dict(text) == expected

import unittest


# Write a function fib() that a takes an integer nn and returns the nnth fibonacci number.
# Let's say our fibonacci series is 0-indexed and starts with 0. So:

# fib(0) # => 0
# fib(1) # => 1
# fib(2) # => 1
# fib(3) # => 2
# fib(4) # => 3

def fib(n):
    if n < 0:
        raise Exception("Index was negative")
    elif n in [0, 1]:
        return n

    # we'll be building the fibonacci series from the bottom up
    # so we'll need to track the previous 2 numbers at each step
    # prev_prev = 0   # 0th fibonacci
    # prev = 1        # 1st fibonacci

# range creates a list, so if you do range(1, 10000000) it creates a list in memory with 9999999 elements.
# xrange is a sequence object that evaluates lazily. In python 3, range() does what xrange() used to do and xrange()
    # does not exists.



    a, b = 0, 1
    for _ in xrange(n):
        a, b = b, a + b

    return a


# Test case
class FibTests(unittest.TestCase):
    def test_fib(self):
        self.assertEqual(fib(7), 13)

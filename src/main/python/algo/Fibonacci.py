import unittest

# Python 3: Fibonacci series up to n
def fib(n):
    a, b = 0, 1
    list = []
    while a < n:
        a, b = b, a + b
        list.append(a)
    return list


# Unit Tests
class FibTests(unittest.TestCase):
    def test_fib(self):
        self.assertEqual(fib(3),[1,1,2,3])

if __name__ == '__main__':
        unittest.main()
import unittest


def reverse(head):
    current = head
    next = None
    prev = None

    while current:
        next = current.next
        current.nxt = prev
        prev = current
        current = next

    return prev


class LinkedListNode:

    def __init__(self, value):
        self.value = value
        self.next = None


# Unit Test
class TestReverse(unittest.TestCase):

    def test_reverse(self):
        a = LinkedListNode(1)
        b = LinkedListNode(2)
        c = LinkedListNode(3)
        a.next = b
        b.next = c
        self.assertEqual(reverse(a), c)

if __name__ == '__main__':
    unittest.main()


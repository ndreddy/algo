class QueueTwoStacks(object):
    def __init__(self):
        self.in_stack = []
        self.out_stack = []

    def enqueue(self, item):
        self.in_stack.append(item)

    def dequeue(self):
        if len(self.out_stack) == 0:

            # Move items from in_stack to out_stack, reversing order
            while len(self.in_stack) > 0:
                self.out_stack.append(self.in_stack.pop())

            # If out_stack is still empty, raise an error
            if len(self.out_stack) == 0:
                raise IndexError("Can't dequeue the empty queue")

        return self.out_stack.pop()

if __name__ == '__main__':
    queue = QueueTwoStacks();
    queue.enqueue(10)
    queue.enqueue(20)
    queue.enqueue(40)
    queue.enqueue(80)
    queue.enqueue(20)
    print(queue.in_stack)
    print(queue.dequeue())
    print(queue.dequeue())
    print(queue.dequeue())

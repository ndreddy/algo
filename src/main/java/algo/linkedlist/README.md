
A linked list organizes items sequentially, with each item storing a pointer to the next one.

Picture a linked list like a chain of paperclips linked together. It's quick to add another paperclip to the top or bottom. It's even quick to insert one in the middle—just disconnect the chain at the middle link, add the new paperclip, then reconnect the other half.

An item in a linked list is called a node. The first node is called the head. The last node is called the tail.

_Unlike an array, consecutive items in a linked list are not necessarily next to each other in memory._

##### Worst Case
- space  - O(n)
- prepend -- O(1)
- append- O(1)
- lookup- O(n)
- insert- O(n)
- delete- O(n)

##### Strengths:
Fast operations on the ends. Adding elements at either end of a linked list is O(1). Removing the first element is also O(1).
Flexible size. There's no need to specify how many elements you're going to store ahead of time. You can keep adding elements as long as there's enough space on the machine.

##### Weaknesses:
Costly lookups. To access or edit an item in a linked list, you have to take O(i) time to walk from the head of the list to the ith item.

Not cache-friendly. Most computers have caching systems that make reading from sequential addresses in memory faster than reading from scattered addresses.
Array items are always located right next to each other in computer memory, but linked list nodes can be scattered all over.

So iterating through a linked list is usually quite a bit slower than iterating through the items in an array, even though they're both theoretically O(n) time.

##### Uses:
Stacks and queues only need fast operations on the ends, so linked lists are ideal.

##### Deleting a Node from a Singly Linked List
Deleting a node from a linked list is fairly straightforward. Given a node n, we find the previous node prev and set prev.next equal to n. next. If the list is doubly linked, we must also update n. next to set
n. next. prev equal to n. prev. The important things to remember are (1) to check for the null pointer and (2) to update the head or tail pointer as necessary.

#####The "Runner"Technique
The "runner" (or second pointer) technique is used in many linked list problems. The runner technique
means that you iterate through the linked list with two pointers simultaneously, with one ahead of the
other. The "fast " node might be ahead by a fixed amount, or it might be hopping multiple nodes for each
one node that the "slow" node iterates through.
For example, suppose you had a linked list 

a1->a2-> ••• - >an->b1- >b2- > ••• - >bn and you wanted to rearrange it into 

a1- >b1- >a2- > •.• - >an- >bn

You do not know the length of the linked list (but you
do know that the length is an even number).
You could have one pointer pl (the fast pointer) move every two elements for everyone move that p2
makes. When pl hits the end of the linked list, p2 will be at the midpoint. Then, move pl back to the front
and begin "weaving" the elements. On each iteration, p2 selects an element and inserts it after pl.
##### Recursive Problems
A number of linked list problems rely on recursion. If you're having trouble solving a linked list problem,
you should explore if a recursive approach will work. We won't go into depth on recursion here, since a later
chapter is devoted to it.




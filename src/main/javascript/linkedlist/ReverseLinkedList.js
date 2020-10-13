const LinkedList = require("../util/LinkedList");

// reverse a linked list
var reverseLinkedList = function(list) {
    let curr = list.head;
    let prev = null;

    while(curr) {
        let next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;   // Change the list head !!!
}
// quick test
let linkedlist = new LinkedList();

for (let elem of [1, 2, 3, 4, 5, 6, 7]) {
    linkedlist.append(elem);
}

console.log("Original list");
console.log(linkedlist._toArray());

console.log("Reversed list")
newHead = reverseLinkedList(linkedlist);
console.log("New Head = "+ newHead.value);
linkedlist.head = newHead;
console.log(linkedlist._toArray()); // [1, 5, 6, 8]

const LinkedList = require("../util/LinkedList");

function removeDuplicates(list) {
    const _set = new Set();
    let curr = list.head;
    let prev = null;
    while (curr) {
        if (_set.has(curr.value)) {
            // duplicate found
            // de-link it from the list
            // cur jumps next but previous stays
            // right behind cur (as always)
            let elem = curr;
            prev.next = curr.next;
            curr = curr.next;
            elem.next = null;
        }
        else {
            // add to the set
            _set.add(curr.value);
            prev = curr;
            curr = curr.next;
        }
    }

    return list;
}

// quick test
let list = new LinkedList();
for (let elem of [1, 5, 1, 6, 8, 6, 8, 8, 8, 8]) {
    list.append(elem);
}

removeDuplicates(list);

console.log(list._toArray()); // [1, 5, 6, 8]
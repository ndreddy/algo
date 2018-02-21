package algo.search;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ndreddy on 13/04/17.
 *
 * I have a list of words that are mostly alphabetical, except they start somewhere in the middle of the alphabet,
 * reach the end, and then start from the beginning of the alphabet. In other words, this is an alphabetically ordered
 * list that has been "rotated." For example:
 * <p>
 * <code> words = [ 'ptolemaic', 'retrograde', 'supplant', 'undulate', 'xenoepist', 'asymptote', # <-- rotates here!
 * 'babka', 'banoffee', 'engender', 'karpatka', 'othellolagkage', ] </code>
 * <p>
 * Write a function for finding the index of the "rotation point," which is where I started working from the beginning
 * of the dictionary. This list is huge (there are lots of words I don't know) so we want to be efficient here.
 * <p>
 * Breakdown The list is mostly ordered. We should exploit that fact.
 * <p>
 * What's a common algorithm that takes advantage of the fact that a list is sorted to find an item efficiently? Binary
 * search! ↴ We can write an adapted version of binary search for this. In each iteration of our binary search, how do
 * we know if the rotation point is to our left or to our right?
 * <p>
 * Try drawing out an example list!
 * <p>
 * words = [ 'k','v','a','b','c','d','e','g','i' ] ^ If our "count guess" is the middle item, which is 'c' in this
 * case, is the rotation point to the left or to the right? How do we know?
 * <p>
 * <p>
 * Notice that every item to the right of our rotation point is always alphabetically before the first item in the
 * list.
 * <p>
 * So the rotation point is to our left if the count item is less than the first item. Else it's to our right.
 * <p>
 * This is a modified version of binary search ↴ . At each iteration, we go right if the item we're looking at is
 * greater than the first item and we go left if the item we're looking at is less than the first item.
 * <p>
 * We keep track of the lower and upper bounds on the rotation point, calling them floorIndex and ceilingIndex
 * (initially we called them "floor" and "ceiling," but because we didn't imply the type in the name we got confused and
 * created bugs). When floorIndex and ceilingIndex are directly next to each other, we know the floor is the last item
 * we added before starting from the beginning of the dictionary, and the ceiling is the first item we added after.
 */
public class FindRotationPoint {
    /**
     Complexity
     O(\lg{n})O(lgn) time and O(1)O(1) space, just like binary search.

     We're assuming that our word lengths are bound by some constant—if they were bounded by a non-constant ll, each of our string comparisons would cost O(l)O(l), for a total of O(l*\lg{n})O(l∗lgn) runtime.

     Bonus
     This function assumes that the arraysandstrings is rotated. If it isn't, what index will it return? How can we fix our function to return 0 for an unrotated arraysandstrings?

     What We Learned
     The answer was a modified version of binary search.

     This is a great example of the difference between "knowing" something and knowing something. You might have seen binary search before, but that doesn't help you much unless you've learned the lessons of binary search.

     Binary search teaches us that when an arraysandstrings is sorted or mostly sorted:

     The value at a given index tells us a lot about what's to the left and what's to the right.
     We don't have to look at every item in the arraysandstrings. By inspecting the middle item, we can "rule out" half of the arraysandstrings.
     We can use this approach over and over, cutting the problem in half until we have the answer. This is sometimes called "divide and conquer."
     So whenever you know an arraysandstrings is sorted or almost sorted, think about these lessons from binary search and see if they apply.
     */

    public int findRotationPoint(String[] arr) {
//        final String firstWord = arr[0];

        int l = 0;      // left inded
        int r = arr.length - 1; // right index
        int m; // mid index

        while (arr[l].compareTo(arr[r]) > 0) {

            // guess a point halfway between floor and ceiling
             m = (l + r ) / 2;

            // if guess comes after first word or is the first word
            if (arr[m].compareTo(arr[r]) > 0) {
                // go right
                l = m + 1 ;
            } else {
                // go left
                r = m;
            }
        }

        return l;
    }



    @Test
    public void testFindRotationPoint() {

       String [] words = { "ptolemaic", "retrograde", "supplant", "undulate", "xenoepist", "asymptote", "babka",
               "banoffee", "engender", "karpatka", "othellolagkage" };
        assertEquals(5,findRotationPoint(words));
    }

    @Test
    public void testNoRotationPoint() {

        String [] words = { "ptolemaic", "retrograde", "supplant", "undulate", "xenoepist" };
        assertEquals(0,findRotationPoint(words));
    }

    @Test
    public void testOnePoint() {

        String [] words = { "ptolemaic" };
        assertEquals(0,findRotationPoint(words));
    }


}

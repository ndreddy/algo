package algo.array;

import junit.framework.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ndreddy on 30/03/17. You've built an in-flight entertainment system with on-demand movie streaming. Users
 * on longer flights like to start a second movie right when their first one ends, but they complain that the plane
 * usually lands before they can see the ending. So you're building a feature for choosing two movies whose total
 * runtimes will equal the exact flight length.
 * <p>
 * Write a function that takes an integer flightLength (in minutes) and an array of integers movieLengths (in minutes)
 * and returns a boolean indicating whether there are two numbers in movieLengths whose sum equals flightLength.
 * <p>
 * When building your function:
 * <p>
 * Assume your users will watch exactly two movies Don't make your users watch the same movie twice Optimize for runtime
 * over memory Gotchas Gotchas We can do this in O(n)O(n) time, where nnnnnn is the length of movieLengths.
 * <p>
 * Remember: your users shouldn't watch the same movie twice. Are you sure your method won’t give a false positive if
 * the array has one element that is half flightLength?
 * <p>
 * Breakdown Breakdown How would we solve this by hand? We know our two movie lengths need to sum to flightLength. So
 * for a given firstMovieLength, we need a secondMovieLength that equals flightLength - firstMovieLength.
 * <p>
 * To do this by hand we might go through movieLengths from beginning to end, treating each item as firstMovieLength,
 * and for each of those check if there's a secondMovieLength equal to flightLength - firstMovieLength.
 * <p>
 * How would we implement this in code? We could nest two loops (the outer choosing firstMovieLength, the inner choosing
 * secondMovieLength). That’d give us a runtime of O(n^2)O(n ​2 ​​ ). We can do better.
 * <p>
 * To bring our runtime down we'll probably need to replace that inner loop (the one that looks for a matching
 * secondMovieLength) with something faster.
 * <p>
 * We could sort the movieLengths first—then we could use binary search ↴ ↴
 * <p>
 * to find secondMovieLength in O(\lg{n})O(lgn) time instead of O(n)O(n) time. But sorting would cost
 * O(nlg(n))O(nlg(n)), and we can do even better than that. Could we check for the existence of our secondMovieLength in
 * constant time?
 * <p>
 * What data structure gives us convenient constant-time lookups?
 * <p>
 * A hash set!
 * <p>
 * So we could throw all of our movieLengths into a hash set first, in O(n)O(n) time. Then we could loop through our
 * possible firstMovieLengths and replace our inner loop with a simple check in our hash set. This'll give us O(n)O(n)
 * runtime overall!
 * <p>
 * Of course, we need to add some logic to make sure we're not showing users the same movie twice...
 * <p>
 * But first, we can tighten this up a bit. Instead of two sequential loops, can we do it all in one loop? (Done
 * carefully, this will give us protection from showing the same movie twice as well.)
 * <p>
 * Solution Solution We make one pass through movieLengths, treating each item as the firstMovieLength. At each
 * iteration, we:
 * <p>
 * See if there's a matchingSecondMovieLength we've seen already (stored in our movieLengthsSeen hash set) that is equal
 * to flightLength - firstMovieLength. If there is, we short-circuit and return true. Keep our movieLengthsSeen hash set
 * up to date by throwing in the current firstMovieLength.
 * <p>
 * <p>
 * <p>
 * O(n) time, and O(n) space. Note while optimizing runtime we added a bit of space cost.
 * <p>
 * What We Learned The trick was to use a hash set to access our movies by length, in O(1) time.
 * <p>
 * Using hash-based data structures, like hash maps or hash sets, is so common in coding challenge solutions, it should
 * always be your first thought. Always ask yourself, right from the start: "Can I save time by using a hash map?"
 *
 * <code>
 public boolean canTwoMoviesFillFlight(int[] movieLengths, int flightLength) {

 // movie lengths we've seen so far
 Set<Integer> movieLengthsSeen = new HashSet<Integer>();

 for (int firstMovieLength : movieLengths) {

 int matchingSecondMovieLength = flightLength - firstMovieLength;
 if (movieLengthsSeen.contains(matchingSecondMovieLength)) {
 return true;
 }

 movieLengthsSeen.add(firstMovieLength);
 }

 // we never found a match, so return false
 return false;
 }
 </code>
 */
public class Fill2MoviesFlightLength {

    public boolean canTwoMoviesFillFlight(int flightLenght, int[] arr) {

        Set<Integer> set = new HashSet<Integer>();

        for (int firstMovie : arr) {
            int secondMovie = flightLenght - firstMovie;
            if (set.contains(secondMovie)) {
                System.out.printf("Movies are %d %d to cover full flight of %d minutes", firstMovie, secondMovie, flightLenght);
                return true;
            }
            set.add(firstMovie);
        }
        return false;

    }

    @Test
    public void test() {
        int[] movies = new int[]{10, 30, 70, 60, 90};
        Assert.assertTrue(canTwoMoviesFillFlight(120, movies));
    }
}

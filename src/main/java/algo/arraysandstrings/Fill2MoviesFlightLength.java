package algo.arraysandstrings;

import junit.framework.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Write a function that takes an integer flightLength (in minutes) and an arraysandstrings of integers movieLengths (in minutes)
 * and returns a boolean indicating whether there are two numbers in movieLengths whose sum equals flightLength.
 * <p>
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

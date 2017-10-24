package algo.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ndreddy on 08/03/17.
 */
public class ParenthesisMatching {


    /**
     * Write a function that, given a sentence along with the position of an opening parenthesis,
     * finds the corresponding closing parenthesis.
     * <p>
     * Complexity
     * <p>
     * O(n) time, where n is the number of chars in the string.
     * <p>
     * O(1) space.
     *
     * @param sentence
     * @param startIndex
     * @return
     */
    public int getMatchingParenthesis(String sentence, int startIndex) {

        int openParenCount = 0;

        // 1. Start one pos after the opening parenthesis.
        for (int i = startIndex + 1; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (c == '(') {
                openParenCount++;
            } else if (c == ')') {

                //2. check should be here. Not at the end.
                if (openParenCount == 0) {
                    return i;
                } else {
                    openParenCount--;
                }
            }
        }

        // 3. This is also important
        throw new IllegalArgumentException("No closing parenthesis found");
    }


    @Test
    public void testMatching() {

        String sentence = "Sometimes (when I nest them (my parentheticals) too much (like this (and this))) they get confusing.";
        int pos = getMatchingParenthesis(sentence, 10);
        System.out.printf("Closeing ) is at %s", pos);
        Assert.assertEquals(79, pos);

    }

}

/* javascript

function getPermutations(string) {

    // base case
    if (string.length <= 1) {
        return new Set(string);
    }

    var allCharsExceptLast = string.slice(0, -1);
    var lastChar = string[string.length - 1];

    // recursive call: get all possible permutations for all chars except last
    var permutationsOfAllCharsExceptLast = getPermutations(allCharsExceptLast);

    // put the last char in all possible positions for each of the above permutations
    var permutations = new Set();
    permutationsOfAllCharsExceptLast.forEach(function(permutationOfAllCharsExceptLast) {
        for (var position = 0; position <= allCharsExceptLast.length; position++) {
            var permutation = permutationOfAllCharsExceptLast.slice(0, position) + lastChar + permutationOfAllCharsExceptLast.slice(position);
            permutations.enqueue(permutation);
        }
    });

    return permutations;
}
 */
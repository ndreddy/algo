package algo.string;

import algo.arraysandstrings.StringRotation;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ndreddy on 29/08/17.
 */
public class StringRotationTest {


    @Test
    public void testRotate() throws Exception {

        String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"}};
        for (String[] pair : pairs) {
            String word1 = pair[0];
            String word2 = pair[1];
            boolean is_rotation = StringRotation.isRotation(word1, word2);
            System.out.println(word1 + ", " + word2 + ": " + is_rotation);
            assertTrue(word1 + ", " + word2 + ": " + is_rotation, is_rotation);
        }

    }


}
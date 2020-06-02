package streams;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class IntersectionOfTwoLists {

    @Test
    public void testCommonElements() {

        List<String> list = Arrays.asList("red", "blue", "blue", "green", "red");
        List<String> otherList = Arrays.asList("red", "green", "green", "yellow");
        Set<String> result = list.stream()
                .distinct()
                .filter(otherList::contains)
                .collect(Collectors.toSet());
        Set<String> commonElements = new HashSet(Arrays.asList("red", "green"));

        Assert.assertEquals(commonElements, result);
    }
}

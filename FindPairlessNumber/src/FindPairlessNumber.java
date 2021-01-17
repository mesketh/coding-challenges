import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * From TechProInterview:
 *
 * <pr>
 * Given a list of numbers, where every number shows up twice except for one number, find that one number.
 * Example:
 *  Input: [4, 3, 2, 4, 1, 3, 2]
 *  Output: 1
 *  </pr>
 *
 * @author mark hesketh
 */
public class FindPairlessNumber {

    static int[] someInts = IntStream.of(4, 5, 7, 2, 4, 6, 6, 5, 7).sorted().toArray();// answer should be '6'

    private int findPairlessInt() {

        var pairLessInt = -1;
        for (int i = 0; i < someInts.length - 2; i += 2) {
            if (someInts[i] != someInts[i + 1]) {
                pairLessInt = someInts[i];
                break;
            }
        }

        if (pairLessInt == -1) {
            pairLessInt = someInts[someInts.length-1];
        }

        return pairLessInt;
    }

    public static void main(String[] args) {
        System.out.println(String.format("Pairless int in %s --> '%s'", Arrays.toString(someInts), new FindPairlessNumber().findPairlessInt()));
    }
}

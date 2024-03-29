package numofdecodeways;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Daily Coding Challenge (December 2020). Rated: Medium.
 *
 * <pre>
 * Good morning! Here's your coding interview problem for today.
 *
 * This problem was asked by Facebook.
 *
 * Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
 *
 * For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.
 *
 * You can assume that the messages are decodable. For example, '001' is not allowed.
 * </pre>
 *
 * @author Mark Hesketh
 */
public class DecoderWays {

    private static final String SHORT_ENCODING = "145645";
    private static final String LONG_ENCODING = "1456456351435";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        new DecoderWays().findDecoderCount(SHORT_ENCODING);
        new DecoderWays().findDecoderCount(LONG_ENCODING);

        SecureRandom sha1s = SecureRandom.getInstanceStrong();

        new DecoderWays().findDecoderCount(
                IntStream.generate(() -> sha1s.nextInt(50) + 1).
                        limit(100).
                        mapToObj((i) -> String.valueOf(i)).collect(Collectors.joining()));
    }

    private void findDecoderCount(String encodedStr) {

        if (encodedStr.length() > 20) {
            System.out.println(String.format("Encoded string = %1$.20s", encodedStr));
        }

        // baseline number is the number of digits in the string or length
        int decoderCount = encodedStr.length();

        char[] encChars = encodedStr.toCharArray();
        for (int i = 0; i < encChars.length - 1; i++) {

            // increment count for valid pairs i.e. in code range (max=26).Ø
            decoderCount += (Character.digit(encChars[i], 10) * 10 + Character.digit(encChars[i + 1], 10)) <= 26 ? 1 : 0;
        }

        System.out.println("No of ways to decode " + encodedStr + " = " + decoderCount);
    }

}

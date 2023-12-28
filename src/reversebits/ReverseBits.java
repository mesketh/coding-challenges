package reversebits;

/**
 * [Daily Problem] Reverse bits (bit flipping). Problem asked by Amazon - Given a number, flip each
 * bit of it and output the new number.
 */
public class ReverseBits {

  private static final int SOME_NUM = 12346567;

  public static void main(String[] args) {
    System.out.printf("%20s %s\n", "as binary => ", ReverseBits.toBits((SOME_NUM)));
    System.out.printf("%20s %s\n", "1s complement => ",
        ReverseBits.toBits((~SOME_NUM))); // 1s complement (bit flipping)
    System.out.printf("%20s %s\n", "2s complement => ",
        ReverseBits.toBits((~SOME_NUM + 1))); // 2s complement (bit flipping + 1)
  }

  // cheating solution - use static methods on Integer class to output the 1's complement
  private static String toBits(int someNum) {
    return String.format("%-32s", Integer.toBinaryString(someNum));
  }

}

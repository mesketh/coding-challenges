

/**
 * [Daily Problem] Reverse bits (bit flipping).
 * Problem asked by Amazon - Given a number, flip each bit of it and output the new number.
 */
public class ReverseBits {

    private static final int SOME_NUM = 1234;

    public static void main(String[] args) {
        System.out.println(" = "+ReverseBits.toBits((SOME_NUM)));
        System.out.println(" = "+ReverseBits.toBits((~SOME_NUM)));
        System.out.println(" = "+ReverseBits.toBits((~SOME_NUM+1)));
    }

    private static String toBits(int someNum) {
        return String.format("%32s", Integer.toBinaryString(someNum));
    }

}

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <quote>
 * This problem was asked by Triplebyte.
 * <p>
 * You are given n numbers as well as n probabilities that sum up to 1. Write a function to generate one of
 * the numbers with its corresponding probability.
 * <p>
 * For example, given the numbers [1, 2, 3, 4] and probabilities [0.1, 0.5, 0.2, 0.2], your function should
 * return 1 10% of the time, 2 50% of the time, and 3 and 4 20% of the time.
 * <p>
 * You can generate random numbers between 0 and 1 uniformly.
 * </quote>
 *
 * @author Mark Hesketh
 */
public class Probabilities {
    Map<Integer, Double> numProbMap = Map.of(1, 0.2, 2, 0.3, 3, 0.3, 4, 0.2);
    Map<Integer, Integer> numRunningTotals = new HashMap(Map.of(1, 0, 2, 0, 3, 0, 4, 0));
    int totalCount;

    public static void main(String[] args) {
        new Probabilities().perform();
    }

    private void perform() {
        List<Integer> results = new ArrayList<>(10000);
        do {
            results.add(generateNumber());
        } while (totalCount <= 9999);

        System.out.println(String.format("\nResults: totalCount = %d \n", totalCount));
        numProbMap.keySet().stream().forEach(i -> System.out.println(String.format("Average of %s = %.2f", i,
                averageOf(results,
                        i))));

    }

    private double averageOf(List<Integer> results, int num) {
        return BigDecimal.valueOf(results.stream().filter(i -> i == num).count() / (double) totalCount).setScale(2, RoundingMode.UP).doubleValue();
    }

    private int generateNumber() {
        boolean numFound = false;
        int nextNum = -1;
        int nextNumTotals;
        do {
            nextNum = (int) (Math.random() * 4) + 1;
            totalCount++;
            nextNumTotals = numRunningTotals.get(nextNum) + 1;
            numFound =
                    nextNumTotals < numProbMap.get(nextNum) * totalCount;
            if (numFound)
            System.out.println("Adding " + nextNum + " to list");
        else
            System.out.println("skipping "+nextNum+" current count is = "+nextNumTotals/(double)totalCount);
        } while (!numFound);

//        System.out.println("nextNum = "+nextNum);
        numRunningTotals.put(nextNum, nextNumTotals);
        return nextNum;
    }
}
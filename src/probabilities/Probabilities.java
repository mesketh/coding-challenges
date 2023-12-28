package probabilities;

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
 * You are given n numbers as well as n probabilities that sum up to 1. Write a function to generate
 * one of the numbers with its corresponding probability.
 * <p>
 * For example, given the numbers [1, 2, 3, 4] and probabilities [0.1, 0.5, 0.2, 0.2], your function
 * should return 1 10% of the time, 2 50% of the time, and 3 and 4 20% of the time.
 * <p>
 * You can generate random numbers between 0 and 1 uniformly.
 * </quote>
 *
 * @author Mark Hesketh
 */
public class Probabilities {

  Map<Integer, Double> numProbMap = Map.of(1, 0.1, 2, 0.4, 3, 0.1, 4, 0.4);
  Map<Integer, Integer> numRunningTotals = new HashMap<>(Map.of(1, 0, 2, 0, 3, 0, 4, 0));
  int totalCount;

  Map<Integer, Boolean> trackingMap = new HashMap<>(Map.of(1, false, 2, false, 3, false, 4, false));

  public static void main(String[] args) {
    new Probabilities().perform();
  }

  private void perform() {
    List<Integer> results = new ArrayList<>(10000);
    do {
      results.add(generateNumber());
    } while (totalCount <= 9999);

    System.out.printf("\nResults: totalCount = %d \n%n", totalCount);
    numProbMap.keySet()
        .forEach(i -> System.out.printf("Average of %s = %.2f%n", i,
            averageOf(results,
                i)));
  }

  private double averageOf(List<Integer> results, int num) {
    return BigDecimal.valueOf(results.stream().filter(i -> i == num).count() / (double) totalCount)
        .setScale(2, RoundingMode.UP)
        .doubleValue();
  }

  private int generateNumber() {
    boolean numFound;
    int nextNum;
    int nextNumTotals;
    do {
      nextNum = (int) (Math.random() * 4) + 1;
      nextNumTotals = numRunningTotals.get(nextNum) + 1;
      numFound =
          nextNumTotals < numProbMap.get(nextNum) * 10000;
      if (numFound) {
        System.out.printf("(%d) Adding " + nextNum + " to list%n", totalCount);
        totalCount++;
      } else if (trackingMap.values().stream().allMatch(m -> m == Boolean.TRUE)) {
        numFound = true;
        totalCount = 10000;
      } else {
        trackingMap.put(nextNum, true);
        System.out.println(
            "skipping " + nextNum + " current count is = "
                + nextNumTotals / (double) totalCount);
      }
    } while (!numFound);

//        System.out.println("nextNum = "+nextNum);
    numRunningTotals.put(nextNum, nextNumTotals);
    return nextNum;
  }
}
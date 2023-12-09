import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * From The Daily Coding Problem #731 [Easy]:
 * <pr>
 * This problem was asked by Facebook.
 * <p>
 * Given a array of numbers representing the stock prices of a company in chronological order, write
 * a function that calculates the maximum profit you could have made from buying and selling that
 * stock once. You must buy before you can sell it.
 * <p>
 * For example, given [9, 11, 8, 5, 7, 10], you should return 5, since you could buy the stock at 5
 * dollars and sell it at 10 dollars.
 * </pr>
 *
 * @author mark hesketh
 */
public class FindMaxProfit {

  private static int[] prices;

  public static void main(String[] args) {

    var numStocks = Optional.of(args)
        .filter(a -> a.length > 0)
        .map(a -> args[0])
        .stream()
        .mapToInt(Integer::parseInt)
        .findFirst()
        .orElse(5);

    prices = stockPrices(numStocks);

    System.out.printf("Max profit of prices (%s) = %d",
        Arrays.stream(prices).boxed().map(String::valueOf).collect(Collectors.joining(",")),
        new FindMaxProfit().calculateProfit(prices.length - 1, 0));
    IntSummaryStatistics intSummaryStatistics = Arrays.stream(prices).summaryStatistics();
    System.out.printf("\nMax price = %d, \nMin price = %d",
        intSummaryStatistics.getMax(),
        intSummaryStatistics.getMin());
  }

  private int calculateProfit(int startPos, int maxProfit) {

    if (startPos <= 0) {
      return maxProfit;
    }

    for (int i = startPos; i > 0; i--) {
      var profit = prices[startPos] - prices[i - 1] ;
      if (profit > maxProfit) {
        maxProfit = profit;
      }
    }

    // tail recursion by passing maxProfit
    return calculateProfit(startPos - 1, maxProfit);
  }

  private static int[] stockPrices(int noOfStocks) {
    Random stockGenerator = new Random();
    return stockGenerator.ints(noOfStocks, 25, 150).toArray();
  }

}

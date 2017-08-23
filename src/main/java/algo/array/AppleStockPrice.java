package algo.array;


import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ndreddy on 23/02/17.
 * <p>
 * <p>
 * The brute force  approach would be to try every pair of times (treating the earlier time as the buy time and the
 * later time as the sell time) and see which one is higher. But that will take O(n^2) time, since we have two nested
 * loops—for every time, we're going through every other time.
 * <code>
 *
 * </code>
 * <p>
 * Well, we’re doing a lot of extra work. We’re looking at every pair twice. If we're going to do better than O(n^2),
 * we're probably going to do it in either O(n\lg{n}) or O(n). O(n\lg{n}) comes up in sorting and searching algorithms
 * where we're recursively cutting the set in half. It's not obvious that we can save time by cutting the set in half
 * here.
 * <p>
 * The max profit we can get by selling at the currentPrice is simply the difference between the currentPrice and the
 * minPrice from earlier in the day. If this difference is greater than the current maxProfit, we have a new maxProfit.
 * So for every price, we’ll need to: keep track of the lowest price we’ve seen so far see if we can get a better profit
 */
public class AppleStockPrice {


    public static int getMaxProfit(int[] arr) {

        // make sure we have at least 2 prices
        if (arr.length < 2) {
            throw new IllegalArgumentException("Getting a profit requires at least 2 prices");
        }

        // we initialize them to the first price and the first possible profit
        int minPrice = arr[0];
        int maxProfit = arr[1] - arr[0];

        // start at the second (index 1) time
        // we can't sell at the first time, since we must buy first,
        // and we can't buy and sell at the same time!
        // if we started at index 0, we'd try to buy *and* sell at time 0.
        // this would give a profit of 0, which is a problem if our
        // maxProfit is supposed to be *negative*--we'd return 0!
        for (int i = 1; i < arr.length; i++) {
            int currentPrice = arr[i];

            // see what our profit would be if we bought at the
            // min price and sold at the current price
            int currentProfit = currentPrice - minPrice;

            // update maxProfit if we can do better
            maxProfit = Math.max(maxProfit, currentProfit);

            // update minPrice so it's always
            // the lowest price we've seen so far
            minPrice = Math.min(minPrice, currentPrice);
        }

        return maxProfit;
    }

    @Test
    public void testPriceUpandDown() {
        int[] stockPricesYesterday = new int[]{10, 7, 5, 8, 11, 9};

        int profit = getMaxProfit(stockPricesYesterday);
        System.out.printf("Profit is %d", profit);
        Assert.assertEquals(profit,6);
    }

    @Test
    public void testPriceGoingDown() {
        int[] stockPricesYesterday = new int[]{10, 7, 5, 4, 2, 1};
        int profit = getMaxProfit(stockPricesYesterday);
        System.out.printf("Profit is %d", profit);
        Assert.assertEquals(profit, -1);

    }


}

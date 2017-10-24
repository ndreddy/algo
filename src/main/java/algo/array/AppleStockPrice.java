package algo.array;


import org.junit.Assert;
import org.junit.Test;

/**
 * Write an efficient method that takes stockPricesYesterday and returns the best profit I could have made from 1
 * purchase and 1 sale of 1 Apple stock yesterday.
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

            // see what our profit would be if we bought at the
            // min price and sold at the current price
            int currentProfit = arr[i] - minPrice;

            // update maxProfit if we can do better
            maxProfit = Math.max(maxProfit, currentProfit);

            // update minPrice so it's always
            // the lowest price we've seen so far
            minPrice = Math.min(minPrice, arr[i]);
        }

        return maxProfit;
    }

    @Test
    public void testPriceUpandDown() {
        int[] stockPricesYesterday = new int[]{10, 7, 5, 8, 11, 9};

        int profit = getMaxProfit(stockPricesYesterday);
        System.out.printf("Profit is %d", profit);
        Assert.assertEquals(profit, 6);
    }

    @Test
    public void testPriceGoingDown() {
        int[] stockPricesYesterday = new int[]{10, 7, 5, 4, 2, 1};
        int profit = getMaxProfit(stockPricesYesterday);
        System.out.printf("Profit is %d", profit);
        Assert.assertEquals(profit, -1);

    }


}

def get_max_profit(stock_prices):

    # Min 2 required for profit
    if len(stock_prices) < 2:
        raise IndexError("Min len 2 required")

    min_price = stock_prices[0]
    max_profit = stock_prices[1] - stock_prices[0]

    for i, current_price in enumerate(stock_prices):

        if i == 0:
            continue

        current_profit = current_price - min_price

        max_profit = max(current_profit, max_profit)

        min_price = min(min_price, current_price)

    return max_profit


# Run the program.
print "max profit is:"
print get_max_profit([10, 7, 5, 11, 30, 12])

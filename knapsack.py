from sys import stdin

def main():
    arr = [int(x) for x in stdin.readline().split()]
    capacity = arr[0]
    n = arr[1]
    values = []
    weights = []
    for i in range(n):
        value_weight = [int(x) for x in stdin.readline().split()]
        values.append(value_weight[0])
        weights.append(value_weight[1])
    # Compute answer
    indexes = knapsack(values, weights, n, capacity)
    indexes.sort()
    # Output answer
    print(len(indexes))
    for i, itemIndex in enumerate(indexes):
        # Print indexOf index
        print(itemIndex, end=" ")
    print()

def printTable(table):
    # print 2d list
    for i in (range(len(table))):
        print(table[i])

def knapsack(values, weights, n, capacity):
    # first row and column are 0 so we do [n+1][n+1]
    table = [ [0] * (capacity+1) for _ in range(n+1) ]
    for i in range(1, n+1):
        for w in range(0, n+1):
            # current weight plus previous weight
            totalWeight = w + weights[i-1]
            # total value plus previous value
            totalValue = table[i-1][w] + values[i-1]
            # Take whether current or previous row index is greater
            table[i][w] = max(table[i][w], table[i-1][w])
            # only include items if total weight is within capacity
            if totalWeight <= capacity:
                # curr cell is the max of either (totalValue + prev value) OR curr cell
                table[i][totalWeight] = max(totalValue, table[i][totalWeight])
    itemsChosen = []
    # iterate over the rows backwards (no need to iterate over columns explicitly)
    for i in range(n, 0, -1):
        # add if item is different from item at row above
        if table[i][capacity] != table[i-1][capacity]:
            itemIndex = i-1
            itemsChosen.append(itemIndex)
            # subtract item's weight from capacity
            capacity -= weights[itemIndex]
    #printTable(table)
    return itemsChosen

if __name__ == "__main__":
    main()

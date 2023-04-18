# Knapsack-problem

The knapsack problem is a classic optimization problem in computer science and mathematics. It involves trying to fit a set of items into a knapsack, with each item having a certain weight and value. The goal is to maximize the value of the items included in the knapsack while keeping the total weight of the items below or equal to the knapsack's capacity.

<img src="./knapsack-problem.png" alt="overview" width="500" height="500">


# Proposed Solution
This problem can be formulated in different ways, but one common approach is to use dynamic programming, here is the proposed algorithm used in this repository

```
// Input:
// Values (stored in array v)
// Weights (stored in array w)
// Number of distinct items (n)
// Knapsack capacity (W)
// NOTE: The array "v" and array "w" are assumed to store all relevant values starting at index 1.

array m[0..n, 0..W];

for j from 0 to W do:
    m[0, j] := 0

for i from 1 to n do:
    m[i, 0] := 0

for i from 1 to n do:
    for j from 0 to W do:
        if w[i] > j then:
            m[i, j] := m[i-1, j]
        else:
            m[i, j] := max(m[i-1, j], m[i-1, j-w[i]] + v[i])

```

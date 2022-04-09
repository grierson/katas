# Non-Constructible change

Find smallest impossible amount of change for a given amount of coins

- function
  - arguments
    - coins [int]
  - return
    - Success: int
    - Fail: 1 (Minimum that you can't create)
- constraints
  - Can have multiple of the same value coin

Example

```python
coins = [1, 2, 5]

# f(coins) => 4
# As (1 + 2) + 1 = 4
```
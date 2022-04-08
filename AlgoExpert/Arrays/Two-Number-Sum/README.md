# Two Number Sum

Find two numbers in `numbers` which sum equals `target`

- arguments
  - numbers [int]
  - target: int
- return
  - Success: [n1, n2] or [n2, n1]
  - Fail: []

Example

```python
numbers = [3, 5, -4, 8, 11, 1, -1, 6]
target = 10

# f(numbers, target) => [-1, 11]
# As -1 + 11 = 10 (target)
```

## Solution 1 - Brute force

Try all the permutations

```mermaid
flowchart TD
numbers --> isOne{"one number?"}
isOne --> |True|return["return []"]
isOne --> |False|Check{"if any in rest + head = target"}
Check --> |True| True["return [head, rest[n]]"]
Check --> |"rest numbers"| numbers
```

### Solution 1 - Passed

| Category | Value | Reason                    |
| -------- | ----- | ------------------------- |
| Space    | O(n)  | Running through same loop |
| Time     | O(n)  | For loop                  |

## Solution 2 - Record every difference

- Step through each number
- Get the difference from the target
- if we have seen the difference
  - return difference and current number
  - otherwise record difference and continue

```mermaid
flowchart TD
any{"any numbers?"}
any -->|True| difference["difference = target - head"] --> seen{"Seen difference?"}
any -->|False| D["return []"]

seen --> |True| return["return [head, difference]"]
seen --> |False| record["record difference"]
record -->|rest numbers| any
```

### Solution 2 - Passed


| Category | Value | Reason                    |
| -------- | ----- | ------------------------- |
| Space    | O(n)  | Running through same loop |
| Time     | O(n)  | For loop                  |

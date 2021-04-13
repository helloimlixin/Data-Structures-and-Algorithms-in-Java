# Computational Complexity Analysis

## Big-O Notation

Big-O Notation gives an upper bound of the complexity in the **worst** case, helping to quantify performance as the input size becomes **arbitrarily large**.

**List of Complexities from smallest to largest**:

$n$: the size of the input

- Constant Time: $O(1)$
- Logarithmic Time: $O(\log (n))$
- Linear Time: $O(n)$
- Linearithmic Time: $O(n\log (n))$
- Quadratic Time: $O(n^2)$
- Cubic Time: $O(n^3)$
- Exponential Time: $O(b^n), b > 1$
- Factorial Time: $O(n!)$
- And many others...

## Big-O Properties

$O(n + c) = O(n)$

$O(cn) = O(n), c > 0$

Let $f$ be a function that describes the running time of a particular algorithm for an input of size $n$:

$f(n) = 7 \log (n)^3 + 15 n^2 + 2 n^3 + 8$

$O(f(n)) = o(n^3)$

## Big-O Examples

1. The following run in **constant** time: $O(1)$,

    ```pseudocode
    i := 0
    While i < 11 Do
        i = i + 1
    ```

2. The following run in **linear** time: $O(n)$,

    ```pseudocode
    i := 0
    While i < n Do
        i = i + 1
    ```

    Here $f(n) = n \rightarrow O(f(n)) = O(n)$.

    ```pseudocode
    i := 0
    While i < n Do
        i = i + 3
    ```

    Here $f(n) = n / 3 \rightarrow O(f(n)) = O(n)$.

3. The following run in quadratic time $O(n^2)$,

    ```pseudocode
    For (i := 0; i < n; i = i + 1)
        For (j := 0; j < n; j = j + 1)
    ```

    Here $f(n) = n \times n \rightarrow O(f(n)) = O(n^2)$

    ```pseudocode
    For (i := 0; i < n; i = i + 1)
        For (j := i; j < n; j = j + 1)
    ```

    Here $f(n) = n + (n - 1) + \cdots + 1 = \frac{n(n + 1)}{2} \rightarrow O(f(n)) = O(n^2 / 2 + n / 2) = O(n^2)$

4. Some more complicated examples:

   ```pseudocode
   low := 0
   high := n - 1
   While low <= high Do
        mid := (low + high) / 2
        If array[mid] == value: return mid
        Else If array[mid] < value: lo = mid + 1
        Else If array[mid] > value: hi = mid - 1
   return -1 // value not found
   ```

   Here the runtime complexity: $O(\log n)$.

   ```pseudocode
    i := 0
    While i < n Do
        j = 0
        While j < 3 * n Do
            j = j + 1
        j = 0
        While j < 2 * n Do
            j = j + 1
        i = i + 1
    ```

    Here $f(n) = n * (3n + 2n) = 5n^2 \rightarrow O(f(n)) = O(n^2)$.

    ```pseudocode
    i := 0
    While i < 3 * n Do
        j := 10
        While j <= 50 Do
            j = j + 1
        j = 0
        While j < n * n * n Do
            j = j + 2
        i = i + 1
    ```

    Here $f(n) = 3n * (1 / 40 + n^3 / 2) = 3n / 40 + 3n^4 / 2$

5. Some other complexities:

    - Finding all subsets of a set: $O(2^n)$.
    - Finding all permutations of a string: $O(n!)$.
    - Sorting using mergesort: $O(n \log n)$.
    - Iterating over all the cells in a matrix of size $n \times m$: $o(nm)$.

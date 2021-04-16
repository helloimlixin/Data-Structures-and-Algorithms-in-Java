# Static and Dynamic Arrays

A static array is a fixed length container containing $n$ elements **indexable** from the range $[0, n - 1]$.

A static array is alotted as a contiguous chunk of memory.

## When and where is a static array used?

1. Storing and accessing sequential data
2. Temporarily storing objects
3. Used by IO routines as buffers
4. Lookup tables and inverse lookup tables because of their indexing property
5. Can be used to return multiple values from a function
6. Used in dynamic programming to cache answers to subproblems

## Complexity

|           	| Static Array 	| Dynamic Array 	|
|-----------	|--------------	|---------------	|
| Access    	| O(1)         	| O(1)          	|
| Search    	| O(n)         	| O(n)          	|
| Insertion 	| N/A          	| O(n)          	|
| Appending 	| N/A          	| O(1)          	|
| Deletion  	| N/A          	| O(n)          	|

## Operations on Dynamic Arrays

The dynamic array can **grow** and **shrink** in size.

### Implementation

1. Create a static array with an initial capacity.
2. Add elements to the underlying static array, keeping track of the number of elements.
3. If adding another element will exceed the capacity, then create a new static array with twice the capacity and copy the original elements into it.

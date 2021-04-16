import java.util.*;

/**
 * Implementation of an integer only array which can outperform Java's own
 * ArrayList by about a factor of 10-15x.
 * 
 * @author William Fiset
 */
public class IntArray implements Iterable<Integer> {
    private static final int DEFAULT_CAP = 1 << 3;

    public int[] arr;
    public int len = 0;
    private int capacity = 0;

    /**
     * Initialize the array with a default capacity.
     */
    public IntArray() {
        this(DEFAULT_CAP);
    }

    /**
     * Initialize the array with a certain capacity.
     * @param capacity initial capacity of the array
     */
    public IntArray(int capacity) {
        if (capacity < 0) 
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = new int[capacity];
    }

    /**
     * Inialize by converting a static array to a dynamic array.
     * @param array a static integer array
     */
    public IntArray(int[] array) {
        if (array == null)
            throw new IllegalArgumentException("Array cannot be null");
        arr = Arrays.copyOf(array, array.length);
        capacity = len = array.length;
    }

    /**
     * Returns the size of the array
     * @return the size (int) of the array
     */
    public int size() {
        return len;
    }

    /**
     * Returns true/false on whether the array is empty.
     * @return
     */
    public boolean isEmpty() {
        return len == 0;
    }

    /**
     * To get/set values without the method call overhead can gain 10x the
     * speed, by calling array_obj.arr[index] instead.
     * @param index target index
     * @return array element
     */
    public int get(int index) {
        return arr[index];
    }

    /**
     * Sets array element with given index and number.
     * @param index target index
     * @param elem replacement
     */
    public void set(int index, int elem) {
        arr[index] = elem;
    }

    /**
     * Adds an element to the end of the dynamic array, resize by doubling when 
     * overflow happens. Amortized constant cost.
     * @param elem element to add at the end of the array
     */
    public void add(int elem) {
        if (len + 1 >= capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2; // double array size when overflow
            arr = Arrays.copyOf(arr, capacity); // pads with extra 
                                                // 0/null elements
        }
        arr[len++] = elem;
    }

    /**
     * Removes the element at a specified index. Avoid calling this method if
     * possible as it takes O(n) time to remove an element (since you have to
     * reconstruct the array).
     * @param rm_index index of the element to remove
     */
    public void removeAt(int rm_index) {
        System.arraycopy(arr, rm_index + 1, arr, rm_index, len - rm_index - 1);
        --len;
        --capacity;
    }

    /**
     * Search and remove an element if it is present in the array. Similarly,
     * avoid calling this method since it takes O(n) time.
     * @param elem element to remove
     * @return true/false indicating whether the removal is successful
     */
    public boolean remove(int elem) {
        for (int i = 0; i < len; i++) {
            if (arr[i] == elem) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Reverse the contents of the array.
     */
    public void reverse() {
        for (int i = 0; i < len / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[len - i - 1];
            arr[len - i - 1] = tmp;
        }
    }

    /**
     * Find an element in the array using binary search when the array is
     * sorted. Takes O(log(n)) time.
     * @param key key to search
     * @return index if the key is present in the array, a value < 0 if the key
     * is not found
     */
    public int binarySearch(int key) {
        int index = Arrays.binarySearch(arr, 0, len, key);
        if (index < 0) index = -index -1;
        return index;
    }

    /**
     * Sort the array.
     */
    public void sort() {
        Arrays.sort(arr, 0, len);
    }

    /**
     * Slower than iterative for-loop.
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int index = 0;

            public boolean hasNext() {
                return index < len;
            }

            public Integer next() {
                return arr[index++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        if (len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; i++) sb.append(arr[i] + ", ");
            return sb.append(arr[len - 1] + "]").toString();
        }
    }

    // Example usage.
    public static void main(String[] args) {

        IntArray ar = new IntArray(50);
        ar.add(3);
        ar.add(7);
        ar.add(6);
        ar.add(-2);

        ar.sort(); // [-2, 3, 6, 7]

        // Prints [-2, 3, 6, 7]
        for (int i = 0; i < ar.size(); i++) System.out.println(ar.get(i));

        // Prints [-2, 3, 6, 7]
        System.out.println(ar);
    }
}

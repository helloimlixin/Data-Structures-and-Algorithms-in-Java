import java.util.*;

@SuppressWarnings("unchecked")
public class DynamicArray <T> implements Iterable <T> {
    private T [] arr;
    private int len = 0; // obvious array length
    private int capacity = 0; // actual array length

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) 
            throw new IllegalArgumentException(
                "Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        return arr[index];
    }

    public void set(int index, T elem) {
        arr[index] = elem;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            arr[i] = null;
        }
        len = 0;
    }

    /**
     * Adds an element at the end of an array. The array will be resized by
     * doubling if the capacity is reached.
     * 
     * This operation takes amortized O(1) time.
     * 
     * @param elem element to add to the array
     */
    public void add(T elem) {
        // Resize the array by doubling when capacity is reached.
        if (len + 1 >= capacity) {
            System.out.println("DEBUG: Array capacity reached, resizing...");
            if (capacity == 0) {
                capacity = 1;
            } else {
                capacity *= 2; // double the size when overflow
            }
            T[] new_arr = (T[]) new Object[capacity];
            for (int i = 0; i < len; i++) {
                new_arr[i] = arr[i];
            }
            arr = new_arr;
        }
        arr[len++] = elem;
    }

    public T removeAt(int rm_index) {
        if (rm_index >= len && rm_index < 0) 
            throw new IndexOutOfBoundsException();
        T data = arr[rm_index];
        T[] new_arr = (T[]) new Object[len - 1];
        // Copy everything into the new array except at the rm_index.
        for (int i = 0, j = 0; i < len; i++, j++) {
            if (i == rm_index) j--; // skip over rm_index by fixing
                                    // j temporarily (lag behind)
            else new_arr[j] = arr[i];
        }
        arr = new_arr;
        capacity = --len;
        return data;
    }

    /**
     * Remove the object in the array.
     * @param obj object to remove
     * @return true if object is present in the array, false if object is absent
     */
    public boolean remove(Object obj) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Return the index of the given object.
     * @param obj query object
     * @return integer representing the index of the query object, -1 if
     *  the object does not exsit
     */
    public int indexOf(Object obj) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if object is present in the array, i.e., the index returned by
     * indexOf query method is not -1.
     * @param obj query object
     * @return true if the array contains the object, false otherwise
     */
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    /**
     * Provides an abstraction method for iterating the array.
     * Iterator is still fast but not as fast as an iterative for-loop.
     */
    @Override public Iterator<T> iterator() {
        return new Iterator<T> () {
            int index = 0;
            public boolean hasNext() { return index < len; }
            public T next() { return arr[index++]; }
        };
    }

    @Override public String toString() {
        if (len == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; i++) {
                sb.append(arr[i] + ", ");
            }
            return sb.append(arr[len - 1] + "]").toString();
        }
    }

    // Example usage.
    public static void main(String[] args) {
        DynamicArray<String> array = new DynamicArray<>(2);
        array.add("vqSGD");
        array.add("Vector");
        array.add("Quantized");
        array.add("Stochastic");
        array.add("Gradient");
        array.add("Descent");

        System.out.println(array);

        array.remove("Stochastic");

        System.out.println(array);
    }
}

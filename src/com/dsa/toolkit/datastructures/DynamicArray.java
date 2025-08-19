package com.dsa.toolkit.datastructures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic dynamic array implementation that automatically resizes as needed.
 * Similar to ArrayList but with educational focus on understanding the mechanics.
 * 
 * Time Complexities:
 * - Access: O(1)
 * - Search: O(n)
 * - Insertion: O(1) amortized, O(n) worst case
 * - Deletion: O(n) due to shifting elements
 * 
 * Space Complexity: O(n)
 * 
 * @param <T> the type of elements stored in this array
 */
public class DynamicArray<T> implements Iterable<T> {
    
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private static final double SHRINK_THRESHOLD = 0.25;
    
    private T[] array;
    private int size;
    private int capacity;
    
    /**
     * Constructs an empty dynamic array with default capacity
     */
    @SuppressWarnings("unchecked")
    public DynamicArray() {
        this.capacity = DEFAULT_CAPACITY;
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }
    
    /**
     * Constructs an empty dynamic array with specified initial capacity
     * @param initialCapacity the initial capacity of the array
     * @throws IllegalArgumentException if initialCapacity is negative
     */
    @SuppressWarnings("unchecked")
    public DynamicArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative: " + initialCapacity);
        }
        this.capacity = Math.max(initialCapacity, 1);
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }
    
    /**
     * Returns the number of elements in the array
     * @return the size of the array
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns true if the array contains no elements
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns the current capacity of the underlying array
     * @return the capacity
     */
    public int capacity() {
        return capacity;
    }
    
    /**
     * Adds an element to the end of the array
     * @param element the element to add
     */
    public void add(T element) {
        ensureCapacity();
        array[size++] = element;
    }
    
    /**
     * Inserts an element at the specified index
     * @param index the index at which to insert
     * @param element the element to insert
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public void add(int index, T element) {
        checkIndexForAdd(index);
        ensureCapacity();
        
        // Shift elements to the right
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }
    
    /**
     * Returns the element at the specified index
     * @param index the index of the element to return
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }
    
    /**
     * Replaces the element at the specified index with the specified element
     * @param index the index of the element to replace
     * @param element the new element
     * @return the previous element at the specified index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public T set(int index, T element) {
        checkIndex(index);
        T oldElement = array[index];
        array[index] = element;
        return oldElement;
    }
    
    /**
     * Removes the element at the specified index
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public T remove(int index) {
        checkIndex(index);
        T removedElement = array[index];
        
        // Shift elements to the left
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null; // Clear reference for GC
        
        shrinkIfNeeded();
        return removedElement;
    }
    
    /**
     * Removes the first occurrence of the specified element
     * @param element the element to remove
     * @return true if the element was found and removed
     */
    public boolean remove(T element) {
        int index = indexOf(element);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }
    
    /**
     * Returns the index of the first occurrence of the specified element
     * @param element the element to search for
     * @return the index of the element, or -1 if not found
     */
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? array[i] == null : element.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Returns true if the array contains the specified element
     * @param element the element to check for
     * @return true if the element is found
     */
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }
    
    /**
     * Removes all elements from the array
     */
    public void clear() {
        // Clear all references for garbage collection
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        // Optionally shrink to default capacity
        if (capacity > DEFAULT_CAPACITY) {
            resize(DEFAULT_CAPACITY);
        }
    }
    
    /**
     * Returns an array containing all elements in proper sequence
     * @return an array containing all elements
     */
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }
    
    /**
     * Ensures the array has sufficient capacity for at least one more element
     */
    private void ensureCapacity() {
        if (size >= capacity) {
            resize((int) (capacity * GROWTH_FACTOR));
        }
    }
    
    /**
     * Shrinks the array if it's significantly under-utilized
     */
    private void shrinkIfNeeded() {
        if (size <= capacity * SHRINK_THRESHOLD && capacity > DEFAULT_CAPACITY) {
            resize(Math.max((int) (capacity / GROWTH_FACTOR), DEFAULT_CAPACITY));
        }
    }
    
    /**
     * Resizes the underlying array to the specified capacity
     * @param newCapacity the new capacity
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        capacity = newCapacity;
    }
    
    /**
     * Checks if the given index is valid for accessing elements
     * @param index the index to check
     * @throws IndexOutOfBoundsException if index is invalid
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    
    /**
     * Checks if the given index is valid for adding elements
     * @param index the index to check
     * @throws IndexOutOfBoundsException if index is invalid
     */
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new DynamicArrayIterator();
    }
    
    /**
     * Iterator implementation for DynamicArray
     */
    private class DynamicArrayIterator implements Iterator<T> {
        private int currentIndex = 0;
        private int expectedSize = size;
        
        @Override
        public boolean hasNext() {
            checkForModification();
            return currentIndex < size;
        }
        
        @Override
        public T next() {
            checkForModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[currentIndex++];
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported by iterator");
        }
        
        private void checkForModification() {
            if (expectedSize != size) {
                throw new java.util.ConcurrentModificationException();
            }
        }
    }
    
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Demonstration of DynamicArray usage
     */
    public static void main(String[] args) {
        System.out.println("=== Dynamic Array Demo ===\n");
        
        // Create a dynamic array
        DynamicArray<String> arr = new DynamicArray<>();
        System.out.println("Created empty array: " + arr);
        System.out.println("Size: " + arr.size() + ", Capacity: " + arr.capacity());
        
        // Add elements
        System.out.println("\n--- Adding Elements ---");
        String[] fruits = {"Apple", "Banana", "Cherry", "Date", "Elderberry"};
        for (String fruit : fruits) {
            arr.add(fruit);
            System.out.println("Added '" + fruit + "': " + arr + 
                             " (Size: " + arr.size() + ", Capacity: " + arr.capacity() + ")");
        }
        
        // Access elements
        System.out.println("\n--- Accessing Elements ---");
        System.out.println("Element at index 2: " + arr.get(2));
        System.out.println("Index of 'Banana': " + arr.indexOf("Banana"));
        System.out.println("Contains 'Cherry': " + arr.contains("Cherry"));
        
        // Insert element
        System.out.println("\n--- Inserting Element ---");
        arr.add(2, "Coconut");
        System.out.println("Inserted 'Coconut' at index 2: " + arr);
        
        // Remove elements
        System.out.println("\n--- Removing Elements ---");
        String removed = arr.remove(1);
        System.out.println("Removed element at index 1 ('" + removed + "'): " + arr);
        
        boolean removedByValue = arr.remove("Date");
        System.out.println("Removed 'Date': " + removedByValue + ", Array: " + arr);
        
        // Iteration
        System.out.println("\n--- Iteration ---");
        System.out.print("Using iterator: ");
        for (String fruit : arr) {
            System.out.print(fruit + " ");
        }
        System.out.println();
    }
}
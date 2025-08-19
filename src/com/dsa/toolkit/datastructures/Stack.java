package com.dsa.toolkit.datastructures;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * A generic stack implementation using ArrayList as the underlying data structure.
 * Follows LIFO (Last In, First Out) principle.
 * 
 * Time Complexities:
 * - Push: O(1) amortized
 * - Pop: O(1)
 * - Peek: O(1)
 * - Search: O(n)
 * 
 * Space Complexity: O(n)
 * 
 * @param <T> the type of elements stored in this stack
 */
public class Stack<T> implements Iterable<T> {
    
    private ArrayList<T> stack;
    
    /**
     * Constructs an empty stack
     */
    public Stack() {
        this.stack = new ArrayList<>();
    }
    
    /**
     * Constructs a stack with specified initial capacity
     * @param initialCapacity the initial capacity of the underlying ArrayList
     * @throws IllegalArgumentException if initialCapacity is negative
     */
    public Stack(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative: " + initialCapacity);
        }
        this.stack = new ArrayList<>(initialCapacity);
    }
    
    /**
     * Pushes an element onto the top of the stack
     * 
     * Time Complexity: O(1) amortized
     * Space Complexity: O(1)
     * 
     * @param element the element to be pushed onto the stack
     */
    public void push(T element) {
        stack.add(element);
    }
    
    /**
     * Removes and returns the element at the top of the stack
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.remove(stack.size() - 1);
    }
    
    /**
     * Returns the element at the top of the stack without removing it
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.get(stack.size() - 1);
    }
    
    /**
     * Returns the element at the top of the stack without removing it.
     * Alternative name for peek() method.
     * 
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T top() {
        return peek();
    }
    
    /**
     * Tests if the stack is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    
    /**
     * Returns the number of elements in the stack
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the number of elements in the stack
     */
    public int size() {
        return stack.size();
    }
    
    /**
     * Searches for an element in the stack and returns its 1-based position
     * from the top of the stack. The top element has position 1.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param element the element to search for
     * @return the 1-based position from the top, or -1 if not found
     */
    public int search(T element) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            T current = stack.get(i);
            if (element == null ? current == null : element.equals(current)) {
                return stack.size() - i; // 1-based position from top
            }
        }
        return -1; // Element not found
    }
    
    /**
     * Removes all elements from the stack
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void clear() {
        stack.clear();
    }
    
    /**
     * Returns true if the stack contains the specified element
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param element the element to check for
     * @return true if the element is found
     */
    public boolean contains(T element) {
        return stack.contains(element);
    }
    
    /**
     * Returns an array containing all elements in the stack from bottom to top
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @return an array containing all elements
     */
    public Object[] toArray() {
        return stack.toArray();
    }
    
    /**
     * Creates a copy of this stack
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @return a new stack containing the same elements
     */
    @SuppressWarnings("unchecked")
    public Stack<T> clone() {
        Stack<T> clonedStack = new Stack<>();
        clonedStack.stack = (ArrayList<T>) this.stack.clone();
        return clonedStack;
    }
    
    /**
     * Returns an iterator that iterates from top to bottom of the stack
     * Note: The iterator is fail-fast and will throw ConcurrentModificationException
     * if the stack is modified during iteration
     * 
     * @return an iterator for this stack
     */
    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }
    
    /**
     * Iterator implementation that traverses from top to bottom
     */
    private class StackIterator implements Iterator<T> {
        private int currentIndex;
        
        public StackIterator() {
            currentIndex = stack.size() - 1; // Start from top
        }
        
        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }
        
        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return stack.get(currentIndex--);
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported by stack iterator");
        }
    }
    
    /**
     * Returns a string representation of the stack showing elements from bottom to top
     * 
     * @return string representation of the stack
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Stack: [] (empty)";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        for (int i = 0; i < stack.size(); i++) {
            sb.append(stack.get(i));
            if (i < stack.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("] (top: ").append(peek()).append(")");
        return sb.toString();
    }
    
    /**
     * Returns a visual representation of the stack showing elements vertically
     * 
     * @return visual string representation
     */
    public String toVisualString() {
        if (isEmpty()) {
            return "Stack is empty\n|_____|\n";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Stack (top to bottom):\n");
        sb.append("┌─────────┐\n");
        
        for (int i = stack.size() - 1; i >= 0; i--) {
            String element = String.valueOf(stack.get(i));
            // Truncate long elements
            if (element.length() > 7) {
                element = element.substring(0, 4) + "...";
            }
            sb.append("│ ").append(String.format("%-7s", element)).append(" │");
            if (i == stack.size() - 1) {
                sb.append(" ← top");
            }
            sb.append("\n");
        }
        sb.append("└─────────┘\n");
        return sb.toString();
    }
    
    /**
     * Demonstration and testing of Stack implementation
     */
    public static void main(String[] args) {
        System.out.println("=== Stack Implementation Demo ===\n");
        
        // Create a new stack
        Stack<String> stack = new Stack<>();
        System.out.println("Created empty stack: " + stack);
        System.out.println("Is empty: " + stack.isEmpty());
        System.out.println("Size: " + stack.size());
        
        // Test empty stack operations
        System.out.println("\n--- Testing Empty Stack Safety ---");
        try {
            stack.pop();
        } catch (EmptyStackException e) {
            System.out.println("✓ pop() on empty stack throws EmptyStackException");
        }
        
        try {
            stack.peek();
        } catch (EmptyStackException e) {
            System.out.println("✓ peek() on empty stack throws EmptyStackException");
        }
        
        // Push elements
        System.out.println("\n--- Pushing Elements ---");
        String[] items = {"First", "Second", "Third", "Fourth", "Fifth"};
        for (String item : items) {
            stack.push(item);
            System.out.println("Pushed '" + item + "': " + stack);
        }
        
        System.out.println("\nVisual representation:");
        System.out.println(stack.toVisualString());
        
        // Peek operations
        System.out.println("--- Peek Operations ---");
        System.out.println("Top element (peek): " + stack.peek());
        System.out.println("Top element (top): " + stack.top());
        System.out.println("Stack after peek: " + stack);
        System.out.println("Size: " + stack.size());
        
        // Search operations
        System.out.println("\n--- Search Operations ---");
        System.out.println("Position of 'Third' from top: " + stack.search("Third"));
        System.out.println("Position of 'First' from top: " + stack.search("First"));
        System.out.println("Position of 'NotFound': " + stack.search("NotFound"));
        System.out.println("Contains 'Second': " + stack.contains("Second"));
        
        // Pop operations
        System.out.println("\n--- Popping Elements ---");
        while (!stack.isEmpty()) {
            String popped = stack.pop();
            System.out.println("Popped: " + popped + ", Remaining: " + stack);
        }
        
        System.out.println("\nFinal state - Is empty: " + stack.isEmpty());
        
        // Test with different data types
        System.out.println("\n--- Testing with Integers ---");
        Stack<Integer> intStack = new Stack<>();
        for (int i = 1; i <= 5; i++) {
            intStack.push(i * 10);
        }
        System.out.println("Integer stack: " + intStack);
        System.out.println("Visual:");
        System.out.println(intStack.toVisualString());
        
        // Iterator test
        System.out.println("--- Iterator Test (top to bottom) ---");
        System.out.print("Using iterator: ");
        for (Integer num : intStack) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Clone test
        System.out.println("\n--- Clone Test ---");
        Stack<Integer> clonedStack = intStack.clone();
        System.out.println("Original: " + intStack);
        System.out.println("Clone: " + clonedStack);
        clonedStack.push(999);
        System.out.println("After pushing 999 to clone:");
        System.out.println("Original: " + intStack);
        System.out.println("Clone: " + clonedStack);
        
        // Performance demonstration
        System.out.println("\n--- Performance Demo ---");
        Stack<Integer> perfStack = new Stack<>(1000);
        long startTime = System.nanoTime();
        
        for (int i = 0; i < 10000; i++) {
            perfStack.push(i);
        }
        
        for (int i = 0; i < 10000; i++) {
            perfStack.pop();
        }
        
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        System.out.printf("10,000 push + 10,000 pop operations took: %.2f ms%n", duration);
        
        System.out.println("\n=== Stack Demo Complete! ===");
    }
}
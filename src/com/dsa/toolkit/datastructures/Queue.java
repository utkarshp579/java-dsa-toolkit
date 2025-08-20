package com.dsa.toolkit.datastructures;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * A generic queue implementation using LinkedList as the underlying data structure.
 * Follows FIFO (First In, First Out) principle.
 * 
 * Time Complexities:
 * - Enqueue: O(1)
 * - Dequeue: O(1)
 * - Peek: O(1)
 * - Size: O(1)
 * 
 * Space Complexity: O(n)
 * 
 * @param <T> the type of elements stored in this queue
 */
public class Queue<T> implements Iterable<T> {
    
    private LinkedList<T> queue;
    
    /**
     * Constructs an empty queue
     */
    public Queue() {
        this.queue = new LinkedList<>();
    }
    
    /**
     * Adds an element to the rear of the queue
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param element the element to add to the queue
     */
    public void enqueue(T element) {
        queue.addLast(element);
    }
    
    /**
     * Alternative method name for enqueue
     * Adds an element to the rear of the queue
     * 
     * @param element the element to add
     */
    public void offer(T element) {
        enqueue(element);
    }
    
    /**
     * Removes and returns the element at the front of the queue
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue.removeFirst();
    }
    
    /**
     * Alternative method name for dequeue
     * Removes and returns the element at the front of the queue
     * 
     * @return the element at the front, or null if queue is empty
     */
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        return queue.removeFirst();
    }
    
    /**
     * Returns the element at the front of the queue without removing it
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue.peekFirst();
    }
    
    /**
     * Alternative method name for peek
     * Returns the element at the front without removing it
     * 
     * @return the front element, or null if queue is empty
     */
    public T front() {
        return isEmpty() ? null : queue.peekFirst();
    }
    
    /**
     * Returns the element at the rear of the queue without removing it
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the element at the rear of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T rear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue.peekLast();
    }
    
    /**
     * Tests if the queue is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    /**
     * Returns the number of elements in the queue
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the number of elements in the queue
     */
    public int size() {
        return queue.size();
    }
    
    /**
     * Removes all elements from the queue
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void clear() {
        queue.clear();
    }
    
    /**
     * Returns true if the queue contains the specified element
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param element the element to check for
     * @return true if the element is found
     */
    public boolean contains(T element) {
        return queue.contains(element);
    }
    
    /**
     * Returns an array containing all elements in queue order (front to rear)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @return an array containing all elements
     */
    public Object[] toArray() {
        return queue.toArray();
    }
    
    /**
     * Creates a copy of this queue
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @return a new queue containing the same elements in the same order
     */
    @SuppressWarnings("unchecked")
    public Queue<T> clone() {
        Queue<T> clonedQueue = new Queue<>();
        clonedQueue.queue = (LinkedList<T>) this.queue.clone();
        return clonedQueue;
    }
    
    /**
     * Returns an iterator that iterates from front to rear of the queue
     * 
     * @return an iterator for this queue
     */
    @Override
    public Iterator<T> iterator() {
        return queue.iterator();
    }
    
    /**
     * Returns a string representation of the queue showing elements from front to rear
     * 
     * @return string representation of the queue
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Queue: [] (empty)";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: [");
        Iterator<T> it = queue.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("] (front: ").append(peek()).append(", rear: ").append(rear()).append(")");
        return sb.toString();
    }
    
    /**
     * Returns a visual representation of the queue showing front and rear
     * 
     * @return visual string representation
     */
    public String toVisualString() {
        if (isEmpty()) {
            return "Queue is empty\nFRONT [                    ] REAR\n";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Queue (front to rear):\n");
        sb.append("FRONT [");
        
        Iterator<T> it = queue.iterator();
        while (it.hasNext()) {
            String element = String.valueOf(it.next());
            // Truncate long elements
            if (element.length() > 8) {
                element = element.substring(0, 5) + "...";
            }
            sb.append(" ").append(element).append(" ");
            if (it.hasNext()) {
                sb.append("|");
            }
        }
        sb.append("] REAR\n");
        
        return sb.toString();
    }
    
    /**
     * Demonstration and testing of Queue implementation
     */
    public static void main(String[] args) {
        System.out.println("=== Queue Implementation Demo ===\n");
        
        // Create a new queue
        Queue<String> queue = new Queue<>();
        System.out.println("Created empty queue: " + queue);
        System.out.println("Is empty: " + queue.isEmpty());
        System.out.println("Size: " + queue.size());
        
        // Test empty queue operations
        System.out.println("\n--- Testing Empty Queue Safety ---");
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("✓ dequeue() on empty queue throws NoSuchElementException");
        }
        
        try {
            queue.peek();
        } catch (NoSuchElementException e) {
            System.out.println("✓ peek() on empty queue throws NoSuchElementException");
        }
        
        // Test poll and front methods (safe alternatives)
        System.out.println("poll() on empty queue returns: " + queue.poll());
        System.out.println("front() on empty queue returns: " + queue.front());
        
        // Enqueue elements
        System.out.println("\n--- Enqueuing Elements ---");
        String[] customers = {"Alice", "Bob", "Charlie", "Diana", "Eve"};
        for (String customer : customers) {
            queue.enqueue(customer);
            System.out.println("Enqueued '" + customer + "': " + queue);
        }
        
        System.out.println("\nVisual representation:");
        System.out.println(queue.toVisualString());
        
        // Peek operations
        System.out.println("--- Peek Operations ---");
        System.out.println("Front element (peek): " + queue.peek());
        System.out.println("Rear element: " + queue.rear());
        System.out.println("Queue after peek: " + queue);
        System.out.println("Size: " + queue.size());
        
        // Mixed enqueue and dequeue operations
        System.out.println("\n--- Mixed Operations ---");
        System.out.println("Current queue: " + queue);
        
        String served = queue.dequeue();
        System.out.println("Served customer: " + served);
        System.out.println("Queue after serving: " + queue);
        
        queue.enqueue("Frank");
        System.out.println("New customer 'Frank' joined: " + queue);
        
        served = queue.dequeue();
        System.out.println("Served customer: " + served);
        System.out.println("Queue after serving: " + queue);
        
        // Dequeue all elements
        System.out.println("\n--- Dequeuing All Elements ---");
        while (!queue.isEmpty()) {
            String customer = queue.dequeue();
            System.out.println("Served: " + customer + ", Remaining: " + queue);
        }
        
        System.out.println("\nFinal state - Is empty: " + queue.isEmpty());
        
        // Test with different data types
        System.out.println("\n--- Testing with Integer Data ---");
        Queue<Integer> intQueue = new Queue<>();
        for (int i = 1; i <= 5; i++) {
            intQueue.enqueue(i * 100);
        }
        System.out.println("Integer queue: " + intQueue);
        System.out.println("Visual:");
        System.out.println(intQueue.toVisualString());
        
        // Iterator test
        System.out.println("--- Iterator Test (front to rear) ---");
        System.out.print("Using for-each loop: ");
        for (Integer num : intQueue) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Contains and search operations
        System.out.println("\n--- Search Operations ---");
        System.out.println("Contains 300: " + intQueue.contains(300));
        System.out.println("Contains 999: " + intQueue.contains(999));
        
        // Clone test
        System.out.println("\n--- Clone Test ---");
        Queue<Integer> clonedQueue = intQueue.clone();
        System.out.println("Original: " + intQueue);
        System.out.println("Clone: " + clonedQueue);
        clonedQueue.enqueue(999);
        System.out.println("After enqueuing 999 to clone:");
        System.out.println("Original: " + intQueue);
        System.out.println("Clone: " + clonedQueue);
        
        // Performance demonstration
        System.out.println("\n--- Performance Demo ---");
        Queue<Integer> perfQueue = new Queue<>();
        long startTime = System.nanoTime();
        
        // Enqueue and dequeue operations
        for (int i = 0; i < 100000; i++) {
            perfQueue.enqueue(i);
        }
        
        for (int i = 0; i < 50000; i++) {
            perfQueue.dequeue();
        }
        
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        System.out.printf("100,000 enqueue + 50,000 dequeue operations took: %.2f ms%n", duration);
        System.out.println("Final queue size: " + perfQueue.size());
        
        // Real-world simulation: Print Queue
        System.out.println("\n--- Real-World Simulation: Print Queue ---");
        Queue<String> printQueue = new Queue<>();
        System.out.println("🖨️ Print Queue Simulation");
        
        // Add print jobs
        String[] printJobs = {"Document1.pdf", "Photo.jpg", "Report.docx", "Invoice.pdf"};
        for (String job : printJobs) {
            printQueue.enqueue(job);
            System.out.println("📄 Added print job: " + job);
            System.out.println("   Queue: " + printQueue);
        }
        
        System.out.println("\n🖨️ Processing print jobs...");
        while (!printQueue.isEmpty()) {
            String currentJob = printQueue.dequeue();
            System.out.println("🖨️ Printing: " + currentJob);
            System.out.println("   Remaining jobs: " + printQueue);
            
            // Simulate printing time
            try {
                Thread.sleep(100); // Brief pause for demo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("✅ All print jobs completed!");
        
        // Clear test
        System.out.println("\n--- Testing clear() ---");
        Queue<String> testQueue = new Queue<>();
        testQueue.enqueue("Test1");
        testQueue.enqueue("Test2");
        System.out.println("Before clear - Size: " + testQueue.size() + ", Queue: " + testQueue);
        testQueue.clear();
        System.out.println("After clear - Size: " + testQueue.size() + ", Queue: " + testQueue);
        
        System.out.println("\n=== Queue Demo Complete! ===");
    }
}
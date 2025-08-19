package com.dsa.toolkit.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Node class for Singly Linked List
 * Represents a single node in the linked list containing data and reference to next node
 * 
 * @param <T> the type of data stored in the node
 */
class Node<T> {
    T data;
    Node<T> next;
    
    /**
     * Constructs a node with given data
     * @param data the data to store in this node
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
    
    /**
     * Constructs a node with given data and next reference
     * @param data the data to store in this node
     * @param next reference to the next node
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
    
    @Override
    public String toString() {
        return String.valueOf(data);
    }
}

/**
 * A generic singly linked list implementation
 * Each node contains data and a reference to the next node
 * 
 * Time Complexities:
 * - addAtHead(): O(1)
 * - addAtTail(): O(n) - O(1) if we maintain tail reference
 * - delete(): O(n)
 * - search(): O(n)
 * - access by index: O(n)
 * 
 * Space Complexity: O(n)
 * 
 * @param <T> the type of elements stored in this list
 */
public class SinglyLinkedList<T> implements Iterable<T> {
    
    private Node<T> head;
    private int size;
    
    /**
     * Constructs an empty linked list
     */
    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    /**
     * Adds an element at the head (beginning) of the list
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the element to add
     */
    public void addAtHead(T data) {
        Node<T> newNode = new Node<>(data, head);
        head = newNode;
        size++;
    }
    
    /**
     * Adds an element at the tail (end) of the list
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param data the element to add
     */
    public void addAtTail(T data) {
        Node<T> newNode = new Node<>(data);
        
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    /**
     * Adds an element at the specified index
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param index the index where to insert the element
     * @param data the element to add
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        if (index == 0) {
            addAtHead(data);
            return;
        }
        
        Node<T> newNode = new Node<>(data);
        Node<T> current = head;
        
        // Navigate to position index-1
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }
    
    /**
     * Deletes the first occurrence of the specified element
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param data the element to delete
     * @return true if element was found and deleted, false otherwise
     */
    public boolean delete(T data) {
        if (head == null) {
            return false;
        }
        
        // Special case: deleting head
        if (head.data == null ? data == null : head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }
        
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data == null ? data == null : current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        
        return false; // Element not found
    }
    
    /**
     * Deletes the element at the specified index
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param index the index of the element to delete
     * @return the deleted element
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public T deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        if (index == 0) {
            T data = head.data;
            head = head.next;
            size--;
            return data;
        }
        
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        
        T data = current.next.data;
        current.next = current.next.next;
        size--;
        return data;
    }
    
    /**
     * Deletes the head (first) element
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the deleted element
     * @throws NoSuchElementException if list is empty
     */
    public T deleteHead() {
        if (head == null) {
            throw new NoSuchElementException("Cannot delete from empty list");
        }
        
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }
    
    /**
     * Deletes the tail (last) element
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @return the deleted element
     * @throws NoSuchElementException if list is empty
     */
    public T deleteTail() {
        if (head == null) {
            throw new NoSuchElementException("Cannot delete from empty list");
        }
        
        if (head.next == null) {
            T data = head.data;
            head = null;
            size--;
            return data;
        }
        
        Node<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        
        T data = current.next.data;
        current.next = null;
        size--;
        return data;
    }
    
    /**
     * Gets the element at the specified index
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param index the index of the element to get
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    /**
     * Sets the element at the specified index
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param index the index of the element to set
     * @param data the new data
     * @return the previous element at the specified index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public T set(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        T oldData = current.data;
        current.data = data;
        return oldData;
    }
    
    /**
     * Returns the index of the first occurrence of the specified element
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param data the element to search for
     * @return the index of the element, or -1 if not found
     */
    public int indexOf(T data) {
        Node<T> current = head;
        int index = 0;
        
        while (current != null) {
            if (current.data == null ? data == null : current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1; // Not found
    }
    
    /**
     * Returns true if the list contains the specified element
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param data the element to check for
     * @return true if the element is found
     */
    public boolean contains(T data) {
        return indexOf(data) != -1;
    }
    
    /**
     * Returns the number of elements in the list
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the size of the list
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns true if the list is empty
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }
    
    /**
     * Removes all elements from the list
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void clear() {
        head = null;
        size = 0;
    }
    
    /**
     * Returns the first element in the list
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the first element
     * @throws NoSuchElementException if list is empty
     */
    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return head.data;
    }
    
    /**
     * Returns the last element in the list
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @return the last element
     * @throws NoSuchElementException if list is empty
     */
    public T getLast() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.data;
    }
    
    /**
     * Reverses the linked list in place
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void reverse() {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next = null;
        
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }
    
    /**
     * Prints all elements in the list
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void printList() {
        if (head == null) {
            System.out.println("List is empty: []");
            return;
        }
        
        System.out.print("List: [");
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
    
    /**
     * Returns a visual representation of the linked list
     * 
     * @return visual string representation
     */
    public String toVisualString() {
        if (head == null) {
            return "Empty list: NULL";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("HEAD -> ");
        Node<T> current = head;
        
        while (current != null) {
            sb.append("[").append(current.data).append("]");
            if (current.next != null) {
                sb.append(" -> ");
            } else {
                sb.append(" -> NULL");
            }
            current = current.next;
        }
        
        return sb.toString();
    }
    
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
    
    /**
     * Iterator implementation for the linked list
     */
    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }
        
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported by iterator");
        }
    }
    
    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Demonstration and testing of SinglyLinkedList implementation
     */
    public static void main(String[] args) {
        System.out.println("=== Singly Linked List Demo ===\n");
        
        // Create a new linked list
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        System.out.println("Created empty list: " + list);
        System.out.println("Is empty: " + list.isEmpty());
        System.out.println("Size: " + list.size());
        list.printList();
        
        // Test addAtHead
        System.out.println("\n--- Testing addAtHead() ---");
        String[] headItems = {"Third", "Second", "First"};
        for (String item : headItems) {
            list.addAtHead(item);
            System.out.println("Added '" + item + "' at head: " + list);
            System.out.println("Visual: " + list.toVisualString());
        }
        
        // Test addAtTail
        System.out.println("\n--- Testing addAtTail() ---");
        String[] tailItems = {"Fourth", "Fifth", "Sixth"};
        for (String item : tailItems) {
            list.addAtTail(item);
            System.out.println("Added '" + item + "' at tail: " + list);
        }
        
        System.out.println("\nFinal list after additions:");
        list.printList();
        System.out.println("Visual: " + list.toVisualString());
        System.out.println("Size: " + list.size());
        
        // Test access methods
        System.out.println("\n--- Testing Access Methods ---");
        System.out.println("First element: " + list.getFirst());
        System.out.println("Last element: " + list.getLast());
        System.out.println("Element at index 2: " + list.get(2));
        System.out.println("Index of 'Fourth': " + list.indexOf("Fourth"));
        System.out.println("Contains 'Third': " + list.contains("Third"));
        System.out.println("Contains 'NotFound': " + list.contains("NotFound"));
        
        // Test addAtIndex
        System.out.println("\n--- Testing addAtIndex() ---");
        list.addAtIndex(3, "Inserted");
        System.out.println("Inserted 'Inserted' at index 3: " + list);
        
        // Test set method
        System.out.println("\n--- Testing set() ---");
        String oldValue = list.set(1, "Modified");
        System.out.println("Set index 1 to 'Modified' (old value: '" + oldValue + "'): " + list);
        
        // Test delete methods
        System.out.println("\n--- Testing delete() method ---");
        boolean deleted = list.delete("Third");
        System.out.println("Deleted 'Third': " + deleted + ", List: " + list);
        
        deleted = list.delete("NotFound");
        System.out.println("Deleted 'NotFound': " + deleted + ", List: " + list);
        
        System.out.println("\n--- Testing deleteAtIndex() ---");
        String deletedElement = list.deleteAtIndex(2);
        System.out.println("Deleted element at index 2 ('" + deletedElement + "'): " + list);
        
        System.out.println("\n--- Testing deleteHead() and deleteTail() ---");
        String deletedHead = list.deleteHead();
        System.out.println("Deleted head ('" + deletedHead + "'): " + list);
        
        String deletedTail = list.deleteTail();
        System.out.println("Deleted tail ('" + deletedTail + "'): " + list);
        
        // Test iteration
        System.out.println("\n--- Testing Iterator ---");
        System.out.print("Using for-each loop: ");
        for (String item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
        
        // Test reverse
        System.out.println("\n--- Testing reverse() ---");
        System.out.println("Before reverse: " + list);
        System.out.println("Visual: " + list.toVisualString());
        list.reverse();
        System.out.println("After reverse: " + list);
        System.out.println("Visual: " + list.toVisualString());
        
        // Test with integers
        System.out.println("\n--- Testing with Integer Data ---");
        SinglyLinkedList<Integer> intList = new SinglyLinkedList<>();
        for (int i = 1; i <= 5; i++) {
            intList.addAtTail(i * 10);
        }
        System.out.println("Integer list: " + intList);
        intList.printList();
        System.out.println("Visual: " + intList.toVisualString());
        
        // Performance demonstration
        System.out.println("\n--- Performance Demo ---");
        SinglyLinkedList<Integer> perfList = new SinglyLinkedList<>();
        long startTime = System.nanoTime();
        
        // Add elements at head (O(1) each)
        for (int i = 0; i < 10000; i++) {
            perfList.addAtHead(i);
        }
        
        // Delete elements from head (O(1) each)
        for (int i = 0; i < 5000; i++) {
            perfList.deleteHead();
        }
        
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        System.out.printf("10,000 addAtHead + 5,000 deleteHead operations took: %.2f ms%n", duration);
        System.out.println("Final list size: " + perfList.size());
        
        // Clear test
        System.out.println("\n--- Testing clear() ---");
        System.out.println("Before clear - Size: " + list.size() + ", List: " + list);
        list.clear();
        System.out.println("After clear - Size: " + list.size() + ", List: " + list);
        list.printList();
        
        System.out.println("\n=== Linked List Demo Complete! ===");
    }
}
package com.dsa.toolkit.algorithms;

import java.util.Arrays;
import java.util.List;

/**
 * Search algorithms implementation focusing on Binary Search and its variations.
 * Provides both iterative and recursive approaches with comprehensive testing.
 * 
 * Binary Search Time Complexity: O(log n)
 * Binary Search Space Complexity: O(1) iterative, O(log n) recursive
 * 
 * @author DSA Toolkit
 * @version 1.0
 */
public class Search {
    
    /**
     * Performs binary search on a sorted array (iterative approach)
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param arr the sorted array to search in
     * @param target the element to search for
     * @return the index of the target element, or -1 if not found
     * @throws IllegalArgumentException if array is null
     */
    public static int binarySearch(int[] arr, int target) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            // Avoid overflow with (left + right) / 2
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1; // Element not found
    }
    
    /**
     * Performs binary search on a sorted array (recursive approach)
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) due to recursion stack
     * 
     * @param arr the sorted array to search in
     * @param target the element to search for
     * @return the index of the target element, or -1 if not found
     * @throws IllegalArgumentException if array is null
     */
    public static int binarySearchRecursive(int[] arr, int target) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        return binarySearchRecursiveHelper(arr, target, 0, arr.length - 1);
    }
    
    /**
     * Helper method for recursive binary search
     * 
     * @param arr the sorted array
     * @param target the target element
     * @param left the left boundary
     * @param right the right boundary
     * @return the index of the target, or -1 if not found
     */
    private static int binarySearchRecursiveHelper(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1; // Base case: element not found
        }
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursiveHelper(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursiveHelper(arr, target, left, mid - 1);
        }
    }
    
    /**
     * Finds the first occurrence of the target element in a sorted array with duplicates
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param arr the sorted array (may contain duplicates)
     * @param target the element to search for
     * @return the index of the first occurrence, or -1 if not found
     */
    public static int findFirst(int[] arr, int target) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;
                right = mid - 1; // Continue searching in left half
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Finds the last occurrence of the target element in a sorted array with duplicates
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param arr the sorted array (may contain duplicates)
     * @param target the element to search for
     * @return the index of the last occurrence, or -1 if not found
     */
    public static int findLast(int[] arr, int target) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;
                left = mid + 1; // Continue searching in right half
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Finds the insertion point for a target value in a sorted array
     * Returns the index where the target should be inserted to maintain sorted order
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param arr the sorted array
     * @param target the value to find insertion point for
     * @return the insertion index
     */
    public static int findInsertionPoint(int[] arr, int target) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
    
    /**
     * Searches for a target in a rotated sorted array
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * @param arr the rotated sorted array
     * @param target the element to search for
     * @return the index of the target, or -1 if not found
     */
    public static int searchRotated(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            }
            
            // Check which half is sorted
            if (arr[left] <= arr[mid]) {
                // Left half is sorted
                if (target >= arr[left] && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (target > arr[mid] && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Linear search for comparison purposes
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param arr the array to search in
     * @param target the element to search for
     * @return the index of the target, or -1 if not found
     */
    public static int linearSearch(int[] arr, int target) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Generic binary search for any Comparable type
     * 
     * @param arr the sorted array of Comparable elements
     * @param target the element to search for
     * @param <T> the type of elements (must be Comparable)
     * @return the index of the target, or -1 if not found
     */
    public static <T extends Comparable<T>> int binarySearch(T[] arr, T target) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = arr[mid].compareTo(target);
            
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    /**
     * Utility method to check if an array is sorted
     * 
     * @param arr the array to check
     * @return true if the array is sorted in ascending order
     */
    public static boolean isSorted(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return true;
        }
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Demonstration and testing of Search algorithms
     */
    public static void main(String[] args) {
        System.out.println("=== Search Algorithms Demo ===\n");
        
        // Test data
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25};
        int[] arrayWithDuplicates = {1, 2, 2, 2, 3, 4, 4, 5, 5, 5, 5, 6};
        int[] rotatedArray = {7, 8, 9, 1, 2, 3, 4, 5, 6};
        
        System.out.println("Test Arrays:");
        System.out.println("Sorted Array: " + Arrays.toString(sortedArray));
        System.out.println("Array with Duplicates: " + Arrays.toString(arrayWithDuplicates));
        System.out.println("Rotated Array: " + Arrays.toString(rotatedArray));
        System.out.println();
        
        // Test basic binary search
        System.out.println("--- Basic Binary Search Tests ---");
        int[] testTargets = {1, 7, 15, 25, 0, 30, 13};
        
        for (int target : testTargets) {
            int iterativeResult = binarySearch(sortedArray, target);
            int recursiveResult = binarySearchRecursive(sortedArray, target);
            int linearResult = linearSearch(sortedArray, target);
            
            System.out.printf("Target %2d: Iterative=%2d, Recursive=%2d, Linear=%2d", 
                            target, iterativeResult, recursiveResult, linearResult);
            
            if (iterativeResult != -1) {
                System.out.printf(" (Found at index %d, value=%d)", 
                                iterativeResult, sortedArray[iterativeResult]);
            } else {
                System.out.print(" (Not found)");
            }
            System.out.println();
        }
        
        // Test first and last occurrence
        System.out.println("\n--- First/Last Occurrence Tests ---");
        int[] duplicateTargets = {2, 4, 5, 7};
        
        for (int target : duplicateTargets) {
            int first = findFirst(arrayWithDuplicates, target);
            int last = findLast(arrayWithDuplicates, target);
            int regular = binarySearch(arrayWithDuplicates, target);
            
            System.out.printf("Target %d: First=%2d, Last=%2d, Regular=%2d", 
                            target, first, last, regular);
            
            if (first != -1) {
                int count = last - first + 1;
                System.out.printf(" (Count: %d)", count);
            }
            System.out.println();
        }
        
        // Test insertion point
        System.out.println("\n--- Insertion Point Tests ---");
        int[] insertionTargets = {0, 2, 4, 8, 16, 20, 30};
        
        for (int target : insertionTargets) {
            int insertionPoint = findInsertionPoint(sortedArray, target);
            System.out.printf("Target %2d: Insert at index %2d", target, insertionPoint);
            
            if (insertionPoint < sortedArray.length) {
                System.out.printf(" (before %d)", sortedArray[insertionPoint]);
            } else {
                System.out.print(" (at end)");
            }
            System.out.println();
        }
        
        // Test rotated array search
        System.out.println("\n--- Rotated Array Search Tests ---");
        int[] rotatedTargets = {1, 3, 5, 7, 9, 0, 10};
        
        for (int target : rotatedTargets) {
            int result = searchRotated(rotatedArray, target);
            System.out.printf("Target %2d in rotated array: %s", target, 
                            result != -1 ? "Found at index " + result : "Not found");
            if (result != -1) {
                System.out.printf(" (value=%d)", rotatedArray[result]);
            }
            System.out.println();
        }
        
        // Test generic binary search
        System.out.println("\n--- Generic Binary Search Tests ---");
        String[] stringArray = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape"};
        String[] stringTargets = {"banana", "date", "mango", "apple", "grape"};
        
        System.out.println("String Array: " + Arrays.toString(stringArray));
        for (String target : stringTargets) {
            int result = binarySearch(stringArray, target);
            System.out.printf("Target '%s': %s%n", target, 
                            result != -1 ? "Found at index " + result : "Not found");
        }
        
        // Performance comparison
        System.out.println("\n--- Performance Comparison ---");
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i * 2; // Even numbers
        }
        
        int searchTarget = 123456;
        int iterations = 1000;
        
        // Binary Search Performance
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            binarySearch(largeArray, searchTarget);
        }
        long binaryTime = System.nanoTime() - startTime;
        
        // Linear Search Performance
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            linearSearch(largeArray, searchTarget);
        }
        long linearTime = System.nanoTime() - startTime;
        
        System.out.printf("Array size: %,d elements%n", largeArray.length);
        System.out.printf("Target: %,d, Iterations: %,d%n", searchTarget, iterations);
        System.out.printf("Binary Search: %.2f ms (avg: %.4f ms per search)%n", 
                        binaryTime / 1_000_000.0, binaryTime / 1_000_000.0 / iterations);
        System.out.printf("Linear Search: %.2f ms (avg: %.4f ms per search)%n", 
                        linearTime / 1_000_000.0, linearTime / 1_000_000.0 / iterations);
        System.out.printf("Binary Search is %.1fx faster%n", (double) linearTime / binaryTime);
        
        // Edge cases testing
        System.out.println("\n--- Edge Cases Testing ---");
        
        // Empty array
        int[] emptyArray = {};
        System.out.println("Empty array search: " + binarySearch(emptyArray, 5));
        
        // Single element array
        int[] singleElement = {42};
        System.out.println("Single element array (target 42): " + binarySearch(singleElement, 42));
        System.out.println("Single element array (target 10): " + binarySearch(singleElement, 10));
        
        // Two element array
        int[] twoElements = {10, 20};
        System.out.println("Two elements [10, 20] (target 10): " + binarySearch(twoElements, 10));
        System.out.println("Two elements [10, 20] (target 20): " + binarySearch(twoElements, 20));
        System.out.println("Two elements [10, 20] (target 15): " + binarySearch(twoElements, 15));
        
        // Test array sorting validation
        System.out.println("\n--- Array Sorting Validation ---");
        int[] unsortedArray = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        System.out.println("Unsorted array: " + Arrays.toString(unsortedArray));
        System.out.println("Is sorted: " + isSorted(unsortedArray));
        System.out.println("Is sortedArray sorted: " + isSorted(sortedArray));
        
        // Real-world example: Phone book search
        System.out.println("\n--- Real-World Example: Phone Directory ---");
        String[] phoneDirectory = {
            "Alice Johnson", "Bob Smith", "Charlie Brown", "Diana Prince", 
            "Eve Wilson", "Frank Miller", "Grace Lee", "Henry Davis"
        };
        
        System.out.println("📞 Phone Directory: " + Arrays.toString(phoneDirectory));
        String[] searchNames = {"Charlie Brown", "Eve Wilson", "John Doe", "Alice Johnson"};
        
        for (String name : searchNames) {
            int index = binarySearch(phoneDirectory, name);
            if (index != -1) {
                System.out.println("📱 Found '" + name + "' at position " + (index + 1));
            } else {
                System.out.println("❌ '" + name + "' not found in directory");
            }
        }
        
        System.out.println("\n=== Search Algorithms Demo Complete! ===");
    }
}
package com.dsa.toolkit.algorithms;

import java.util.Arrays;

/**
 * Merge Sort implementation with detailed analysis and comprehensive testing.
 * A stable, divide-and-conquer sorting algorithm with guaranteed O(n log n) performance.
 * 
 * Time Complexity: O(n log n) in all cases (best, average, worst)
 * Space Complexity: O(n) for the temporary arrays during merging
 * 
 * Key Characteristics:
 * - Stable sort (maintains relative order of equal elements)
 * - Divide and conquer approach
 * - Predictable performance
 * - Not in-place (requires additional space)
 * 
 * @author DSA Toolkit
 * @version 1.1
 */
public class MergeSort {
    
    private static int comparisonCount = 0;
    private static int mergeCount = 0;
    
    public static void sort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (arr.length <= 1) {
            return;
        }
        comparisonCount = 0;
        mergeCount = 0;
        mergeSort(arr, 0, arr.length - 1);
    }
    
    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        mergeCount++;
        int leftSize = mid - left + 1;
        int rightSize = right - mid;
        
        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];
        
        System.arraycopy(arr, left, leftArray, 0, leftSize);
        System.arraycopy(arr, mid + 1, rightArray, 0, rightSize);
        
        int i = 0, j = 0, k = left;
        
        while (i < leftSize && j < rightSize) {
            comparisonCount++;
            if (leftArray[i] <= rightArray[j]) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
        }
        
        while (i < leftSize) {
            arr[k++] = leftArray[i++];
        }
        while (j < rightSize) {
            arr[k++] = rightArray[j++];
        }
    }
    
    public static void sortWithVisualization(int[] arr, boolean showSteps) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (arr.length <= 1) {
            return;
        }
        comparisonCount = 0;
        mergeCount = 0;
        if (showSteps) {
            System.out.println("🔄 Starting Merge Sort...");
            System.out.println("Initial array: " + Arrays.toString(arr));
            System.out.println();
        }
        mergeSortWithVisualization(arr, 0, arr.length - 1, 0, showSteps);
        if (showSteps) {
            System.out.println("✅ Final sorted array: " + Arrays.toString(arr));
            System.out.println("Total comparisons: " + comparisonCount);
            System.out.println("Total merge operations: " + mergeCount);
        }
    }
    
    private static void mergeSortWithVisualization(int[] arr, int left, int right, int depth, boolean showSteps) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            if (showSteps) {
                String indent = "  ".repeat(depth);
                System.out.printf("%s📂 Dividing: %s (indices %d-%d)%n", 
                    indent, Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)), left, right);
                System.out.printf("%s   ↳ Left: %s (indices %d-%d)%n", 
                    indent, Arrays.toString(Arrays.copyOfRange(arr, left, mid + 1)), left, mid);
                System.out.printf("%s   ↳ Right: %s (indices %d-%d)%n", 
                    indent, Arrays.toString(Arrays.copyOfRange(arr, mid + 1, right + 1)), mid + 1, right);
                System.out.println();
            }
            mergeSortWithVisualization(arr, left, mid, depth + 1, showSteps);
            mergeSortWithVisualization(arr, mid + 1, right, depth + 1, showSteps);
            if (showSteps) {
                String indent = "  ".repeat(depth);
                System.out.printf("%s🔗 Merging: %s + %s%n", 
                    indent, Arrays.toString(Arrays.copyOfRange(arr, left, mid + 1)),
                    Arrays.toString(Arrays.copyOfRange(arr, mid + 1, right + 1)));
            }
            merge(arr, left, mid, right);
            if (showSteps) {
                String indent = "  ".repeat(depth);
                System.out.printf("%s   ✅ Result: %s%n%n", 
                    indent, Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));
            }
        }
    }
    
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (arr.length <= 1) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }
    
    private static <T extends Comparable<T>> void mergeSort(T[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void merge(T[] arr, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;
        
        T[] leftArray = (T[]) new Comparable[leftSize];
        T[] rightArray = (T[]) new Comparable[rightSize];
        
        System.arraycopy(arr, left, leftArray, 0, leftSize);
        System.arraycopy(arr, mid + 1, rightArray, 0, rightSize);
        
        int i = 0, j = 0, k = left;
        
        while (i < leftSize && j < rightSize) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
        }
        while (i < leftSize) {
            arr[k++] = leftArray[i++];
        }
        while (j < rightSize) {
            arr[k++] = rightArray[j++];
        }
    }
    
    public static void sortIterative(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        int n = arr.length;
        for (int size = 1; size < n; size *= 2) {
            for (int start = 0; start < n - size; start += 2 * size) {
                int mid = start + size - 1;
                int end = Math.min(start + 2 * size - 1, n - 1);
                merge(arr, start, mid, end);
            }
        }
    }
    
    /**
     * Checks if an array is sorted in ascending order
     * @param arr the array to check
     * @return true if sorted
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
    
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};
        System.out.println("Before: " + Arrays.toString(arr));
        MergeSort.sort(arr);
        System.out.println("After:  " + Arrays.toString(arr));
        System.out.println("Is Sorted? " + MergeSort.isSorted(arr));
        
        System.out.println("\nVisualization Example:");
        int[] arr2 = {38, 27, 43, 3, 9, 82, 10};
        MergeSort.sortWithVisualization(arr2, true);
    }
}

public class Test {
    public static void main(String[] args) {
        // Test array to be sorted
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 10};

        // Check if the array is null or empty
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty or null");
            return;
        }

        // Start the merge sort process on the array
        mergeSort(arr, 0, arr.length - 1);

        // Print the sorted array
        System.out.println("Sorted array:");
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    /**
     * Recursive function to divide the array and call the merge function.
     *
     * @param arr the array to be sorted
     * @param start the starting index of the array/subarray
     * @param end the ending index of the array/subarray
     */
    public static void mergeSort(int[] arr, int start, int end) {
        // Base condition to exit recursion if only one element is left
        if (start >= end) {
            return;
        }

        // Calculate the middle index to divide the array into two halves
        int mid = start + (end - start) / 2;

        // Recursively sort the left half of the array
        mergeSort(arr, start, mid);

        // Recursively sort the right half of the array
        mergeSort(arr, mid + 1, end);

        // Merge the two sorted halves
        merge(arr, start, mid, end);
    }

    /**
     * Function to merge two sorted subarrays into a single sorted array.
     *
     * @param arr the original array containing both subarrays
     * @param start the starting index of the left subarray
     * @param mid the ending index of the left subarray and start of the right subarray
     * @param end the ending index of the right subarray
     */
    public static void merge(int[] arr, int start, int mid, int end) {
        // Temporary array to hold the merged result
        int[] merged = new int[end - start + 1];

        int leftPointer = start;  // Pointer for the left subarray
        int rightPointer = mid + 1;  // Pointer for the right subarray
        int mergedIndex = 0;  // Pointer for the merged array

        // Merge the two sorted subarrays
        while (leftPointer <= mid && rightPointer <= end) {
            if (arr[leftPointer] <= arr[rightPointer]) {
                merged[mergedIndex++] = arr[leftPointer++];
            } else {
                merged[mergedIndex++] = arr[rightPointer++];
            }
        }

        // Copy any remaining elements from the left subarray, if any
        while (leftPointer <= mid) {
            merged[mergedIndex++] = arr[leftPointer++];
        }

        // Copy any remaining elements from the right subarray, if any
        while (rightPointer <= end) {
            merged[mergedIndex++] = arr[rightPointer++];
        }

        // Copy the merged result back into the original array
        for (int i = 0; i < merged.length; i++) {
            arr[start + i] = merged[i];
        }
    }
}

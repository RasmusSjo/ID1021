package sorting;

public class ArraySorter {

    public static void selection(int[] array) {
        int min_index;

        for (int i = 0; i < array.length - 1; i++) {
            min_index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min_index] > array[j]) {
                    // Save the index of the smallest element
                    min_index = j;
                }
            }
            // Swap the smallest element with the current one
            swap(array, i, min_index);
        }
    }

    public static void insertion(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j - 1] > array[j]; j--) {
                // Swap the element with the one before it if's larger than
                // that element, this will put the element in its right location
                swap(array, j, j - 1);
            }
        }
    }

    public static void quickSort(int[] array, int start, int end) {
        if (end <= start) return;

        int pivot = array[end];
        int pivotIndex = start - 1;

        for (int i = start; i < end; i++) {
            if (array[i] < pivot) {
                pivotIndex++;
                swap(array, i, pivotIndex);
            }
        }
        swap(array, ++pivotIndex, end);

        quickSort(array, start, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, end);
    }

    public static void mergeSort(int[] array) {
        // Base case, if we get an array that either holds one element or is empty
        if (array.length == 1) return;

        int middleIndex = array.length / 2; // Index of the element in the middle

        int[] leftArray = new int[middleIndex];
        int[] rightArray = new int[array.length - middleIndex];
        // Make a copy of the left and right part of the array
        for (int i = 0, j = 0, index = 0; index < array.length; index++) {
            if (index < middleIndex) {
                leftArray[i++] = array[index];
            }
            else {
                rightArray[j++] = array[index];
            }
        }

        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);
    }

    public static void merge(int[] leftArray, int[] rightArray, int[] array) {
        int i = 0; // Left array index
        int j = 0; // Right array index

        // Given two arrays that are sorted, merge them into one sorted array
        for (int index = 0; index < array.length; index++) {
            if (i == leftArray.length) {
                array[index] = rightArray[j++];
            }
            else if (j == rightArray.length) {
                array[index] = leftArray[i++];
            }
            else if (leftArray[i] < rightArray[j]) {
                array[index] = leftArray[i++];
            }
            else {
                array[index] = rightArray[j++];
            }
        }
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index2];
        array[index2] = array[index1];
        array[index1] = temp;
    }
}

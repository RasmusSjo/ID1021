package quick_sort;

public class ArraySort {

    public static void sort(int[] array, int start, int end) {
        if (end <= start) return;

        int pivotIndex = partition(array, start, end);

        sort(array, start, pivotIndex - 1);
        sort(array, pivotIndex + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = array[start];

        int i = start;
        int j = end;

        while (i < j) {
            while (pivot < array[j]) {
                j--;
            }
            while (i < j && array[i] <= pivot) {
                i++;
            }
            if (i < j){
                swap(array, i, j);
            }
        }
        swap(array, start, j);

        return j;
    }

    private static int broCodePartition(int[] array, int start, int end) {
        int pivot = array[end];
        int pivotIndex = start - 1;

        for (int i = start; i < end; i++) {
            if (array[i] < pivot) {
                pivotIndex++;
                swap(array, i, pivotIndex);
            }
        }
        swap(array, ++pivotIndex, end);
        return pivotIndex;
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index2];
        array[index2] = array[index1];
        array[index1] = temp;
    }
}

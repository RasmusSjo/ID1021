package sorted_data;

public class Search {

    public static boolean search_unsorted(int[] array, int key) {
        for (int value : array) {
            if (value == key) {
                return true;
            }
        }
        return false;
    }

    public static boolean search_sorted(int[] array, int key) {
        for (int value : array) {
            if (value == key) {
                return true;
            } else if (value > key) {
                return false;
            }
        }
        return false;
    }

    public static boolean binary_search(int[] array, int key) {
        int first = 0;
        int last = array.length - 1;

        if (array.length == 0) {
            return false;
        }

        while (true) {
            // Jump to the middle
            int middle = first + (last - first) / 2;

            // The key has been found
            if (array[middle] == key) {
                return true;
            }

            if (array[middle] < key && middle < last) {
                // The middle position holds something that is less than
                // what we're looking for, what is the first possible page?
                first = middle + 1;
                continue;
            }

            if (array[middle] > key && middle > first) {
            // The middle position holds something that is larger than
            // what we're looking for
                last = middle - 1;
                continue;
            }

            return false;
        }
    }

    public static int[] find_duplicates(int[] array1, int[] array2) {
        int[] duplicates = new int[0];

        for (int value : array1) {
            if (Search.search_unsorted(array2, value)) {
                if (!search_unsorted(duplicates, value)) {
                    duplicates = insert_elem(duplicates, value);
                }
            }
        }
        return duplicates;
    }

    public static int[] find_duplicates_binary(int[] array1, int[] array2) {
        int[] duplicates = new int[0];

        for (int value : array1) {
            if (Search.binary_search(array2, value)) {
                if (duplicates.length == 0) {
                    duplicates = new int[1];
                    duplicates[0] = value;
                }
                else if (!Search.binary_search(duplicates, value)) {
                    duplicates = insert_elem(duplicates, value);
                }
            }
        }
        return duplicates;
    }

    public static int[] even_better(int[] array1, int[] array2) {
        int[] duplicates = new int[0];

        int first = 0;
        int second = 0;
        while (first < array1.length && second < array2.length) {
            if (array1[first] == array2[second]) {
                duplicates = insert_elem(duplicates, array1[first]);
                first++;
                second++;
            }
            else if (array1[first] < array2[second]) {
                first++;
            }
            else {
                second++;
            }
        }
        return duplicates;
    }

    public static void even_better_but_trash(int[] array1, int[] array2) {
        int first = 0;
        int second = 0;

        while (first < array1.length && second < array2.length) {
            if (array2[second] < array1[first]) {
                second++;
            }
            else {
                first++;
            }
        }
    }

    private static int[] insert_elem(int[] array, int value) {
        int[] newArray = new int[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = value;
        return newArray;
    }
}

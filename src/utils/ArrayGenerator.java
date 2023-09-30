package utils;

import java.util.*;

public class ArrayGenerator {

    private final static Random RND = new Random();

    /**
     * Creates a sorted array of length n. The spacing between
     * the elements is 1-3.
     *
     * @param n length of the array to be created
     * @return a sorted list that doesn't contain any duplicates
     */
    public static int[] sorted(int n) {
        int[] array = new int[n];
        int nxt = 0;
        for (int i = 0; i < n ; i++) {
            nxt += RND.nextInt(3) + 1;
            array[i] = nxt;
        }
        return array;
    }

    /**
     * Creates a sorted array of length n that has a possibility of containing
     * duplicate elements. The spacing between the elements is 0-2.
     *
     * @param n length of the array to be created
     * @return a sorted array that can contain duplicates
     */
    public static int[] sortedDup(int n) {
        int[] array = new int[n];
        int nxt = 0;
        for (int i = 0; i < n ; i++) {
            nxt += RND.nextInt(3);
            array[i] = nxt;
        }
        return array;
    }

    /**
     * Creates an unsorted array of length n that doesn't contain any duplicates.
     * The elements are randomised between 0-5n
     *
     * @param n the length of the array
     * @return an unsorted array that doesn't contain any duplicates
     */
    public static int[] unsorted(int n) {
        Set<Integer> generated = new LinkedHashSet<>();
        int[] array = new int[n];

        while (generated.size() < n) {
            Integer next = RND.nextInt(n * 5);
            generated.add(next);
        }

        int i = 0;
        for (int num : generated) {
            array[i++] = num;
        }

        return array;
    }

    /**
     * Creates an unsorted array of length n that doesn't contain any duplicates.
     * The max value of the elements is max.
     *
     * @param n the length of the array
     * @param max the maximum value of the elements
     * @return an unsorted array that doesn't contain any duplicates
     */
    public static int[] unsorted(int n, int max) {
        if (n > max) throw new IllegalArgumentException();
        Set<Integer> generated = new LinkedHashSet<>();
        int[] array = new int[n];

        while (generated.size() < n) {
            Integer next = RND.nextInt(max);
            generated.add(next);
        }

        int i = 0;
        for (int num : generated) {
            array[i++] = num;
        }

        return array;
    }

    /**
     * Creates an unsorted array of length n that can contain duplicates.
     * The max value of the elements is max.
     *
     * @param n the length of the array
     * @param max the maximum value of the elements
     * @return an unsorted array that can contain duplicates
     */
    public static int[] unsortedDup(int n, int max) {
        List<Integer> generated = new ArrayList<>();
        int[] array = new int[n];

        while (generated.size() < n) {
            Integer next = RND.nextInt(max);
            generated.add(next);
        }

        int i = 0;
        for (int num : generated) {
            array[i++] = num;
        }

        return array;
    }

    public static int[] linearArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }
        return array;
    }

    public static int[] shuffle(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int newIndex = RND.nextInt(array.length);
            int temp = array[newIndex];
            array[newIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public static void insert_key_sorted(int[] array, int key) {
        boolean inserted = false;
        int i = 0;
        while (!inserted) {
            if (array[i++] >= key) {
                array[--i] = key;
                inserted = true;
            }
        }
    }

    public static void insert_key_unsorted(int[] array, int key) {
        int index = RND.nextInt(array.length - 1);
        array[index] = key;
    }
}

package sorted_data;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class ArrayGenerator {
    private final static Random RND = new Random();

    public static int[] sorted(int n) {
        int[] array = new int[n];
        int nxt = 0;
        for (int i = 0; i < n ; i++) {
            nxt += RND.nextInt(10) + 1;
            array[i] = nxt;
        }
        return array;
    }

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

    public static int[] unsorted(int n, int max) {
        Set<Integer> generated = new LinkedHashSet<>();
        int[] array = new int[n];

        while (generated.size() < n) {
            Integer next = RND.nextInt(max * 5);
            generated.add(next);
        }

        int i = 0;
        for (int num : generated) {
            array[i++] = num;
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

package sorted_data;

import utils.ArrayGenerator;

public class Benchmark {

    private static void linearUnsorted(int[] array, int[] keys) {
        for (int key : keys) {
            Search.search_unsorted(array, key);
        }
    }

    private static void linearSorted(int[] array, int[] keys) {
        for (int key : keys) {
            Search.search_sorted(array, key);
        }
    }

    private static void binary(int[] array, int[] index) {
        for (int key : index) {
            Search.binary_search(array, key);
        }
    }

    private static void benchSearch() {
        int[] sizes = {100, 200, 400, 800, 2000, 4000, 8000, 16000, 32000, 64000};

        System.out.print("# searching through an array of length n, time in ns\n");
        System.out.printf("#%7s%9s%8s%8s\n", "n", "unsorted", "sorted", "binary");
        for ( int n : sizes) {

            int numOfKeys = n / 2;

            int[] index = ArrayGenerator.unsorted(numOfKeys, n);
            int[] unsortedArray = ArrayGenerator.unsorted(n);
            int[] sortedArray = ArrayGenerator.sorted(n);

            System.out.printf("%8d", n);

            int k = 1000;

            double min = Double.POSITIVE_INFINITY;
            for (int i = 0; i < k; i++) {
                long t0 = System.nanoTime();
                linearUnsorted(unsortedArray, index);
                long t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("%9.0f\n", (min/numOfKeys));

            min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < k; i++) {
                long t0 = System.nanoTime();
                linearSorted(sortedArray, index);
                long t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("%8.0f\n", (min/numOfKeys));
            min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < k; i++) {
                long t0 = System.nanoTime();
                binary(sortedArray, index);
                long t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("%8.0f\n" , (min/numOfKeys));
        }
    }

    public static void main(String[] arg) {
        benchSearch();
    }
}

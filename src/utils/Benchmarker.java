package utils;

import single_linked_list.ArrayList;
import single_linked_list.LinkedList;
import sorted_data.Search;
import sorting.ArraySorter;

import java.util.Arrays;

public class Benchmarker {

    static int[] sizes;
    static int numOfInput;

    static int iterations;
    static String[] rowNames;
    static String[] columnNames;

    private static void setupBench(int assignmentNumber) {

        switch (assignmentNumber) {
            case 1: {

            }
            case 2: {
                sizes = new int[]{100, 200, 400, 800, 2000, 4000, 8000, 16000, 32000, 64000};
                numOfInput = 1;
                iterations = 1000;
                rowNames = LatexHelper.intArrayToStrArray(sizes);
                columnNames = new String[]{"Array size", "Linear search (unsorted)", "Linear search (sorted)", "Binary search"};
                break;
            }
            case 3: {
                sizes = new int[]{100, 300, 500, 700, 900};
                numOfInput = 1;
                iterations = 1000;
                rowNames = LatexHelper.intArrayToStrArray(sizes);
                columnNames = new String[]{"Array size", "Selection sort", "Insertion sort", "Merge sort", "Quick sort"};
                break;
            }
            case 4: {
                sizes = new int[]{1000, 2000, 4000, 8000, 16000, 32000, 64000};
                numOfInput = 2;
                iterations = 1000;
                rowNames = LatexHelper.intArrayToStrArray(sizes);
                columnNames = new String[]{"List1", "List2", "Runtime (us)", "List1", "List2", "Runtime (us)",
                        "List1", "List2", "Runtime (us)", "List1", "List2", "Runtime (us)"};
                break;
            }
        }
    }

    public static double[][] bench(int assignmentNumber) {

        return switch (assignmentNumber) {
            //case 2 -> sortedData();
            case 3 -> sorting();
            case 4 -> singleLinked();
            default -> throw new IllegalStateException("Unexpected value: " + assignmentNumber);
        };
    }

    private static double[] sortedData() {
        int rowLength = columnNames.length - 1;

        double[] benchmark = new double[sizes.length * rowLength];

        long t1, t2, t3;
        long time1, time2, time3;
        double minTime1, minTime2, minTime3;

        for (int sizeNum = 0; sizeNum < sizes.length; sizeNum++) {
            int size = sizes[sizeNum];

            int numOfKeys = 100;

            int[] keys = ArrayGenerator.unsorted(numOfKeys, size);
            int[] unsortedArray = ArrayGenerator.unsorted(size);
            int[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
            ArraySorter.mergeSort(sortedArray);

            minTime1 = Double.POSITIVE_INFINITY;
            minTime2 = Double.POSITIVE_INFINITY;
            minTime3 = Double.POSITIVE_INFINITY;

            // Every iteration the same keys will be searched for in the same array.
            // While this can seem unnecessary it should result in any bad runtimes being
            // eliminated and each algorithm should get at least one good run in.
            // The arrays are switched every 10 iterations to randomize things a bit.
            for (int i = 0; i < iterations; i++) {
                if (i % 10 == 0) {
                    unsortedArray = ArrayGenerator.unsorted(size);
                    sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
                    ArraySorter.mergeSort(sortedArray);
                }

                t1 = System.nanoTime();
                for (int key : keys) {
                    Search.search_unsorted(unsortedArray, key);
                }
                time1 = System.nanoTime() - t1;
                minTime1 = minTime1 > time1 ? time1 : minTime1;

                t2 = System.nanoTime();
                for (int key : keys) {
                    Search.search_sorted(sortedArray, key);
                }
                time2 = System.nanoTime() - t2;
                minTime2 = minTime2 > time2 ? time2 : minTime2;

                t3 = System.nanoTime();
                for (int key : keys) {
                    Search.binary_search(sortedArray, key);
                }
                time3 = System.nanoTime() - t3;
                minTime3 = minTime3 > time3 ? time3 : minTime3;
            }

            // The average time to search for one key
            benchmark[rowLength * sizeNum] = minTime1 / numOfKeys;
            benchmark[rowLength * sizeNum + 1] = minTime2 / numOfKeys;
            benchmark[rowLength * sizeNum + 2] = minTime3 / numOfKeys;
        }

        return benchmark;
    }

    public static double[][] sorting() {
        int rowLength = columnNames.length;
        int columnLength = sizes.length;

        // Array to store the benchmark data
        double[][] benchmark = new double[columnLength][rowLength];

        long t1, t2, t3, t4;
        long time1, time2, time3, time4;
        double minTime1, minTime2, minTime3, minTime4;

        for (int sizeNum = 0; sizeNum < sizes.length; sizeNum++) {
            int[] array1;

            minTime1 = Double.POSITIVE_INFINITY;
            minTime2 = Double.POSITIVE_INFINITY;
            minTime3 = Double.POSITIVE_INFINITY;
            minTime4 = Double.POSITIVE_INFINITY;

            for (int i = 0; i < iterations; i++) {
                array1 = ArrayGenerator.unsorted(sizes[sizeNum]);
                ArraySorter.quickSort(array1, 0, array1.length - 1);
                int[] array2 = Arrays.copyOf(array1, array1.length);
                int[] array3 = Arrays.copyOf(array1, array1.length);
                int[] array4 = Arrays.copyOf(array1, array1.length);

                t1 = System.nanoTime();
                ArraySorter.selection(array1);
                time1 = System.nanoTime() - t1;
                minTime1 = minTime1 > time1 ? time1 : minTime1;

                t2 = System.nanoTime();
                ArraySorter.insertion(array2);
                time2 = System.nanoTime() - t2;
                minTime2 = minTime2 > time2 ? time2 : minTime2;

                t3 = System.nanoTime();
                ArraySorter.mergeSort(array3);
                time3 = System.nanoTime() - t3;
                minTime3 = minTime3 > time3 ? time3 : minTime3;

                t4 = System.nanoTime();
                ArraySorter.quickSort(array4, 0, array4.length - 1);
                time4 = System.nanoTime() - t4;
                minTime4 = minTime4 > time4 ? time4 : minTime4;
            }

            benchmark[sizeNum][0] = sizes[sizeNum];
            benchmark[sizeNum][1] = minTime1;
            benchmark[sizeNum][2] = minTime2;
            benchmark[sizeNum][3] = minTime3;
            benchmark[sizeNum][4] = minTime4;
        }

        return benchmark;
    }

    private static double[][] singleLinked() {
        int rowLength = columnNames.length;
        int columnLength = sizes.length;

        int fixedSize = 10 * sizes[0];

        // Array to store the benchmark data
        double[][] benchmark = new double[columnLength][rowLength];

        long t1;
        long time1;
        double minTime1;

        for (int size = 0; size < columnLength; size++) {
            minTime1 = Double.POSITIVE_INFINITY;
            LinkedList fixedLinked = new LinkedList(fixedSize);
            LinkedList dynamicLinked = new LinkedList(sizes[size]);
            ArrayList fixedArray = new ArrayList(fixedSize);
            ArrayList dynamicArray = new ArrayList(sizes[size]);

            // Append a LinkedList with fixed size to one with an increasing size of sizes[i]
            for (int i = 0; i < iterations; i++) {
                dynamicLinked = new LinkedList(sizes[size]);

                t1 = System.nanoTime();
                dynamicLinked.append(fixedLinked);
                time1 = System.nanoTime() - t1;
                minTime1 = minTime1 > time1 ? time1 : minTime1;
            }

            benchmark[size][0] = sizes[size];
            benchmark[size][1] = fixedSize;
            benchmark[size][2] = minTime1;

            minTime1 = Double.POSITIVE_INFINITY;
            // Append a LinkedList with dynamic size to one with a fixed size of 10 * sizes[0]
            for (int i = 0; i < iterations; i++) {
                fixedLinked = new LinkedList(fixedSize);

                t1 = System.nanoTime();
                fixedLinked.append(dynamicLinked);
                time1 = System.nanoTime() - t1;
                minTime1 = minTime1 > time1 ? time1 : minTime1;
            }

            benchmark[size][3] = fixedSize;
            benchmark[size][4] = sizes[size];
            benchmark[size][5] = minTime1;

            minTime1 = Double.POSITIVE_INFINITY;
            // Append an ArrayList with fixed size to one with an increasing size of sizes[i]
            for (int i = 0; i < iterations; i++) {
                dynamicArray = new ArrayList(sizes[size]);

                t1 = System.nanoTime();
                dynamicArray.append(fixedArray);
                time1 = System.nanoTime() - t1;
                minTime1 = minTime1 > time1 ? time1 : minTime1;
            }

            benchmark[size][6] = sizes[size];
            benchmark[size][7] = fixedSize;
            benchmark[size][8] = minTime1;

            minTime1 = Double.POSITIVE_INFINITY;
            // Append an ArrayList with increasing size to one with a fixed size of sizes[i]
            for (int i = 0; i < iterations; i++) {
                fixedArray = new ArrayList(fixedSize);

                t1 = System.nanoTime();
                fixedArray.append(dynamicArray);
                time1 = System.nanoTime() - t1;
                minTime1 = minTime1 > time1 ? time1 : minTime1;
            }

            benchmark[size][9] = fixedSize;
            benchmark[size][10] = sizes[size];
            benchmark[size][11] = minTime1;
        }

        return benchmark;
    }

    public static void main(String[] args) {
        int assignmentNumber = 4;

        setupBench(assignmentNumber);

        double[][] result = bench(assignmentNumber);

        String tableData = LatexHelper.createTable(result, columnNames, numOfInput);
        String graphData = LatexHelper.createGraph(result, numOfInput);

        LatexHelper.write(tableData, "linked_table.txt");
        LatexHelper.write(graphData, "linked_graph.dat");
    }
}

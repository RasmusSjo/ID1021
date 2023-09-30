package utils;

import binary_tree.BinaryTree;
import double_linked_list.*;
import single_linked_list.ArrayList;
import single_linked_list.*;
import sorted_data.Search;
import sorting.ArraySorter;

import java.util.Arrays;
import java.util.Random;

public class Benchmarker {

    private final static Random RND = new Random();

    // Variables related to the file
    private static String fileName;
    private final static boolean type = true; // table or graph

    // Variables related to the format of the table/graph
    static String[] rowNames;
    static String[] columnNames;
    static int[] rowsToIgnore;

    // Variables related to the benchmark
    static int[] sizes;
    static int iterations;

    private static void setupBench(int assignmentNumber) {

        switch (assignmentNumber) {
            case 2 -> {
                fileName = "search";
                columnNames = new String[]{"Array size", "Linear search (unsorted)", "Linear search (sorted)", "Binary search"};
                rowsToIgnore = new int[]{0};

                sizes = new int[]{100, 200, 400, 800, 2000, 4000, 8000, 16000, 32000, 64000};
                iterations = 1000;
            }
            case 3 -> {
                fileName = "sorting";
                columnNames = new String[]{"Array size", "Selection sort", "Insertion sort", "Merge sort", "Quick sort"};
                rowsToIgnore = new int[]{0};

                sizes = new int[]{100, 300, 500, 700, 900};
                iterations = 1000;
            }
            case 4 -> {
                fileName = "single";
                columnNames = new String[]{"List1", "List2", "Runtime (us)", "List1", "List2", "Runtime (us)",
                        "List1", "List2", "Runtime (us)", "List1", "List2", "Runtime (us)"};
                rowsToIgnore = new int[]{0,1,3,4,6,7,9,10};

                sizes = new int[]{1000, 2000, 4000, 8000, 16000, 32000, 64000};
                iterations = 1000;
            }
            case 5 -> {
                fileName = "doubly";
                columnNames = new String[]{"List size", "Doubly (us)", "Linked (us)", "Ratio"};
                rowsToIgnore = new int[]{0};

                sizes = new int[]{10, 20, 40, 100, 200, 400, 1000, 2000, 4000, 8000, 16000, 32000, 64000};
                iterations = 1000;
            }
            case 6 -> {
                fileName = "binary_tree2";
                columnNames = new String[]{"Size", "Binary Tree (us)", "Binary Search (us)"};
                rowsToIgnore = new int[]{0};

                sizes = new int[]{10, 20, 40, 100, 200, 400, 1000, 2000, 4000, 8000, 16000,
                        32000, 64000, 128000, 256000, 512000, 1024000, 1500000};
                iterations = 1000;
            }
        }
    }

    public static double[][] bench(int assignmentNumber) {

        return switch (assignmentNumber) {
            case 2 -> sortedData();
            case 3 -> sorting();
            case 4 -> singleLinked();
            case 5 -> doublyLinked();
            case 6 -> binaryTree();
            default -> throw new IllegalStateException("Unexpected value: " + assignmentNumber);
        };
    }

    private static double[][] sortedData() {
        int rowLength = columnNames.length;
        int columnLength = sizes.length;

        double[][] benchmark = new double[columnLength][rowLength];

        long t1, t2, t3;
        long time1, time2, time3;
        double minTime1, minTime2, minTime3;

        for (int size = 0; size < columnLength; size++) {
            int arraySize = sizes[size];

            int numOfKeys = 100;
            int[] keys = ArrayGenerator.unsorted(numOfKeys, arraySize);

            int[] unsortedArray = ArrayGenerator.unsorted(arraySize);
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
                    unsortedArray = ArrayGenerator.unsorted(arraySize);
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
            benchmark[size][0] = sizes[size];
            benchmark[size][1] = minTime1 / numOfKeys;
            benchmark[size][2] = minTime2 / numOfKeys;
            benchmark[size][3] = minTime3 / numOfKeys;
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

        for (int size = 0; size < columnLength; size++) {
            int[] array1;

            minTime1 = Double.POSITIVE_INFINITY;
            minTime2 = Double.POSITIVE_INFINITY;
            minTime3 = Double.POSITIVE_INFINITY;
            minTime4 = Double.POSITIVE_INFINITY;

            for (int i = 0; i < iterations; i++) {
                array1 = ArrayGenerator.unsorted(sizes[size]);
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

            benchmark[size][0] = sizes[size];
            benchmark[size][1] = minTime1;
            benchmark[size][2] = minTime2;
            benchmark[size][3] = minTime3;
            benchmark[size][4] = minTime4;
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

    private static double[][] doublyLinked() {
        int rowLength = columnNames.length;
        int columnLength = sizes.length;

        // Array to store the benchmark data
        double[][] benchmark = new double[columnLength][rowLength];

        long t1;
        long time1;

        int average = 100;

        int k = 1000;

        for (int size = 0; size < columnLength; size++) {

            double minTime1 = Double.POSITIVE_INFINITY;
            double minTime2 = Double.POSITIVE_INFINITY;

            for (int i = 0; i < average; i++) {

                LinkedList singleLinked = new LinkedList(sizes[size]);
                DoublyLinkedList doublyLinked = new DoublyLinkedList(sizes[size]);

                LinkedList.Cell[] singleCellArray = singleLinked.getCellArray();
                DoublyLinkedList.Cell[] doublyCellArray = doublyLinked.getCellArray();

                int[] keys = ArrayGenerator.unsortedDup(k, sizes[size]);

                // Unlink and insert a cell in a doubly linked list
                t1 = System.nanoTime();
                for (int key : keys) {
                    DoublyLinkedList.Cell cell = doublyCellArray[key];
                    doublyLinked.unlink(cell);
                    doublyLinked.insert(cell);
                }
                time1 = System.nanoTime() - t1;
                minTime1 = minTime1 > time1 ? time1 : minTime1;

                // Unlink and insert a cell in a single linked list
                t1 = System.nanoTime();
                for (int key : keys) {
                    LinkedList.Cell cell = singleCellArray[key];
                    singleLinked.unlink(cell);
                    singleLinked.insert(cell);
                }
                time1 = System.nanoTime() - t1;
                minTime2 = minTime2 > time1 ? time1 : minTime2;
            }

            benchmark[size][0] = sizes[size];
            benchmark[size][1] = minTime1;
            benchmark[size][2] = minTime2;
            benchmark[size][3] = minTime2 / minTime1;
        }
        return benchmark;
    }

    private static double[][] binaryTree() {
        int rowLength = columnNames.length;
        int columnLength = sizes.length;

        // Array to store the benchmark data
        double[][] benchmark = new double[columnLength][rowLength];

        int[] array;
        BinaryTree tree = new BinaryTree();

        long time;
        double minTime1, minTime2;

        for (int size = 0; size < sizes.length; size++) {
            array = ArrayGenerator.linearArray(sizes[size]);
            tree.constructTreeFromArray(array, 0, array.length - 1);

            minTime1 = Double.POSITIVE_INFINITY;
            minTime2 = Double.POSITIVE_INFINITY;

            int keysSize = 1000;

            for (int i = 0; i < iterations; i++) {
                int[] keys = ArrayGenerator.unsortedDup(keysSize, array[array.length - 1]);

                time = System.nanoTime();
                for (int key : keys) {
                    tree.lookup(key);
                }
                time = System.nanoTime() - time;
                minTime1 = minTime1 > time ? time : minTime1;

                time = System.nanoTime();
                for (int key : keys) {
                    Search.binary_search(array, key);
                }
                time = System.nanoTime() - time;
                minTime2 = minTime2 > time ? time : minTime2;
            }

            benchmark[size][0] = sizes[size];
            benchmark[size][1] = minTime1;
            benchmark[size][2] = minTime2;
        }

        return benchmark;
    }


    public static void main(String[] args) {
        int assignmentNumber = 6;

        setupBench(assignmentNumber);

        double[][] result = bench(assignmentNumber);

        String tableData = LatexHelper.createTable(result, columnNames, rowsToIgnore);
        String graphData = LatexHelper.createGraph(result, rowsToIgnore);

        LatexHelper.write(tableData, fileName, type);
        LatexHelper.write(graphData, fileName, !type);
    }
}

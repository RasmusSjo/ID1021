package utils;

import binary_tree.BinaryTree;
import doubly_linked_list.*;
import hash.*;
import priority_queue.*;
import heap.*;
import single_linked_list.ArrayList;
import single_linked_list.*;
import sorted_data.Search;
import sorting.ArraySorter;

import java.io.BufferedReader;
import java.io.FileReader;
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
            case 7 -> throw new IllegalStateException("No benchmark for this assignment!");
            case 8 -> {
                fileName = "quick_sort";
                columnNames = new String[]{"Array/List size", "Array (us)", "LinkedList (us)", "Insertion sort (for comparison)"};
                rowsToIgnore = new int[]{0};

                sizes = new int[]{10, 20, 40, 80, 160, 320, 640, 1280};
                iterations = 1000;
            }
            case 9 -> {
                fileName = "priority_queue";
                columnNames = new String[]{"Queue size", "Enqueue (Unsorted)", "Dequeue (Unsorted)", "Enqueue (Sorted)", "Dequeue (Sorted)"};
                rowsToIgnore = new int[]{0};

                sizes = new int[]{10, 20, 40, 100, 200, 400, 1000, 2000, 4000, 10000};
                iterations = 1000;
            }
            case 10 -> {
                fileName = "heap_time";
                columnNames = new String[]{"Number of pushes/removes-adds", "Push (µs)", "Remove then add (µs)"};
                rowsToIgnore = new int[]{0};

                sizes = new int[]{100, 200, 400, 1000, 2000, 4000, 10000};
                iterations = 1000;
            }
            case 11 -> {
                fileName = "heap_depth";
                columnNames = new String[]{"Increment value", "Depth"};
                rowsToIgnore = new int[]{0, 1};

                sizes = new int[]{1000};
                iterations = 1;
            }
            case 12 -> {
                fileName = "heap_linked_comp";
                columnNames = new String[]{"Elements added/removed", "Linked heap", "Array heap", "Linked (sorted)", "Linked (unsorted)"};
                rowsToIgnore = new int[]{0};

                sizes = new int[]{10,100, 200, 400, 1000, 2000, 4000, 10000};
                iterations = 1000;
            }
            case 13 -> {
                fileName = "hashLinBin";
                columnNames = new String[]{
                        "Zip code",
                        "Linear (String)",
                        "Binary (String)",
                        "Linear (int)",
                        "Binary (int)",
                };
                rowsToIgnore = new int[]{0};
                sizes = new int[]{0, 0};
                iterations = 1000;
            }
            case 14 -> {
                fileName = "hash4";
                columnNames = new String[]{
                        "Zip codes searched",
                        "Zip string - linear",
                        "Zip string - binary",
                        "Zip int - linear",
                        "Zip int - binary",
                        "Hash (no hashing) - lookup",
                        "Hash (hashing) - lookup",
                        "Hash (improved) - lookup"
                };
                rowsToIgnore = new int[]{0};

                sizes = new int[]{10, 20, 40, 100};
//                sizes = new int[]{200, 400, 1000};
//                sizes = new int[]{2000, 4000, 10000};
//                sizes = new int[]{10, 20, 40, 100,200, 400, 1000};
                iterations = 5000; // Change number of iterations depending on the sizes used
            }
            case 15 -> {
                fileName = "hash_collisions";
                columnNames = new String[]{
                        "Zip codes searched",
                        "Collisions (bucket)",
                        "Collisions (improved)"
                };
                rowsToIgnore = new int[]{0,1,2};

                sizes = new int[]{10, 20, 40, 100, 200, 400, 1000, 2000, 4000, 10000};
                iterations = 1;
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
            case 7 -> throw new IllegalStateException("No benchmark for this assignment!");
            case 8 -> quickSort();
            case 9 -> priorityQueue();
            case 10 -> linkedHeapTime();
            case 11 -> linkedHeapDepth();
            case 12 -> queueComparison();
            case 13 -> hashLinearBinary();
            case 14 -> hash();
            case 15 -> hashCollisions();
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
            int[] keys = ArrayGenerator.unsortedNoDup(numOfKeys, 0, arraySize);

            int[] unsortedArray = ArrayGenerator.unsortedNoDup(arraySize);
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
                    unsortedArray = ArrayGenerator.unsortedNoDup(arraySize);
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
                array1 = ArrayGenerator.unsortedNoDup(sizes[size]);
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

                int[] keys = ArrayGenerator.unsortedDup(k, 0, sizes[size]);

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
                int[] keys = ArrayGenerator.unsortedDup(keysSize, 0, array[array.length - 1]);

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

    private static double[][] quickSort() {

        int rowLength = columnNames.length;
        int columnLength = sizes.length;

        int benchSize = 100;

        // Array to store the benchmark data
        double[][] benchmark = new double[columnLength][rowLength];

        int[][] arrays;
        int[][] arrays2;
        quick_sort.LinkedList[] lists;

        long time;
        double minTime1, minTime2, minTime3;

        for (int size = 0; size < sizes.length; size++) {
            // Create 'benchSize' different arrays

            minTime1 = Double.POSITIVE_INFINITY;
            minTime2 = Double.POSITIVE_INFINITY;
            minTime3 = Double.POSITIVE_INFINITY;

            for (int i = 0; i < iterations; i++) {

                arrays = new int[benchSize][sizes[size]];
                arrays2 = new int[benchSize][sizes[size]];
                for (int j = 0; j < arrays.length; j++) {
                    arrays[j] = ArrayGenerator.unsortedNoDup(sizes[size]);
                    arrays2[j] = Arrays.copyOf(arrays[j], arrays[j].length);
                }

                // Create 'benchSize' different LinkedLists corresponding to the arrays
                lists = new quick_sort.LinkedList[benchSize];
                for (int j = 0; j < arrays.length; j++) {
                    lists[j] = quick_sort.LinkedList.createLinkedFromArray(arrays[j]);
                }


                // Measure the time it takes to sort 'benchSize' number of LinkedLists
                time = System.nanoTime();
                for (int[] array: arrays) {
                    //quick_sort.ArraySort.sort(array, 0, array.length - 1);
                    ArraySorter.quickSort(array, 0, array.length - 1);
                }
                time = System.nanoTime() - time;
                minTime1 = minTime1 > time ? time : minTime1;

                // Measure the time it takes to sort 'benchSize' number of arrays
                time = System.nanoTime();
                for (quick_sort.LinkedList list : lists) {
                    quick_sort.LinkedSort.quickSort(list);
                }
                time = System.nanoTime() - time;
                minTime2 = minTime2 > time ? time : minTime2;

                // Measure the time it takes to sort 'benchSize' number of arrays with insertion sort
                time = System.nanoTime();
                for (int[] array2: arrays2) {
                    ArraySorter.insertion(array2);
                }
                time = System.nanoTime() - time;
                minTime3 = minTime3 > time ? time : minTime3;
            }

            benchmark[size][0] = sizes[size];
            benchmark[size][1] = minTime1;
            benchmark[size][2] = minTime2;
            benchmark[size][3] = minTime3;
        }

        return benchmark;
    }

    private static double[][] priorityQueue() {
        int rowLength = columnNames.length;
        int columnLength = sizes.length;

        // Array to store the benchmark data
        double[][] benchmark = new double[columnLength][rowLength];

        int[] array;
        UnsortedPriorityQueue unsortedPQueue = new UnsortedPriorityQueue();
        SortedPriorityQueue sortedPQueue = new SortedPriorityQueue();

        long time;
        double enqueueTime1, dequeueTime1, enqueueTime2, dequeueTime2;

        for (int size = 0; size < sizes.length; size++) {
            // Create 'benchSize' different arrays

            enqueueTime1 = Double.POSITIVE_INFINITY;
            dequeueTime1 = Double.POSITIVE_INFINITY;
            enqueueTime2 = Double.POSITIVE_INFINITY;
            dequeueTime2 = Double.POSITIVE_INFINITY;

            for (int i = 0; i < iterations; i++) {
                // Generate a new array
                array = ArrayGenerator.unsortedNoDup(sizes[size]);

                // Measure the time it takes to sort 'benchSize' number of LinkedLists
                time = System.nanoTime();
                for (int value: array) {
                    unsortedPQueue.add(value);
                }
                time = System.nanoTime() - time;
                enqueueTime1 = enqueueTime1 > time ? time : enqueueTime1;

                time = System.nanoTime();
                for (int j = 0; j < sizes[size]; j++) {
                    unsortedPQueue.remove();
                }
                time = System.nanoTime() - time;
                dequeueTime1 = dequeueTime1 > time ? time : dequeueTime1;

                // Measure the time it takes to enqueue sizes[size] number of items in a sorted queue
                time = System.nanoTime();
                for (int value: array) {
                    sortedPQueue.add(value);
                }
                time = System.nanoTime() - time;
                enqueueTime2 = enqueueTime2 > time ? time : enqueueTime2;

                time = System.nanoTime();
                for (int j = 0; j < sizes[size]; j++) {
                    sortedPQueue.remove();
                }
                time = System.nanoTime() - time;
                dequeueTime2 = dequeueTime2 > time ? time : dequeueTime2;
            }

            benchmark[size][0] = sizes[size];
            benchmark[size][1] = enqueueTime1;
            benchmark[size][2] = dequeueTime1;
            benchmark[size][3] = enqueueTime2;
            benchmark[size][4] = dequeueTime2;
        }

        return benchmark;
    }

    private static double[][] linkedHeapTime() {
        int rowLength = columnNames.length;
        int columnLength = sizes.length;

        // Array to store the benchmark data
        double[][] benchmark = new double[columnLength][rowLength];

        int heapSize = 1023;
        LinkedHeap heapPush = new LinkedHeap();
        LinkedHeap heapRmAdd = new LinkedHeap();

        long time;
        double minTime1, minTime2;

        int[] array, increments;

        for (int size = 0; size < sizes.length; size++) {
            minTime1 = Double.POSITIVE_INFINITY;
            minTime2 = Double.POSITIVE_INFINITY;

            for (int i = 0; i < iterations; i++) {
                // Constant size of the heap for this benchmark
                array = ArrayGenerator.unsortedNoDup(heapSize, 0, 10000);
                increments = ArrayGenerator.unsortedDup(sizes[size], 10, 100);

                for (int value : array) {
                    heapPush.add(value);
                    heapRmAdd.add(value);
                }

                time = System.nanoTime();
                for (int increment : increments) {
                    heapPush.push(increment);
                }
                time = System.nanoTime() - time;
                minTime1 = minTime1 > time ? time : minTime1;

                int value;
                time = System.nanoTime();
                for (int increment : increments) {
                    value = heapRmAdd.remove();
                    value += increment;
                    heapRmAdd.add(value);
                }
                time = System.nanoTime() - time;
                minTime2 = minTime2 > time ? time : minTime2;
            }

            System.out.println("Min time for pushing " + sizes[size] + " times: " + minTime1);
            System.out.println("Min time for removing/adding " + sizes[size] + " times: " + minTime2);

            benchmark[size][0] = sizes[size];
            benchmark[size][1] = minTime1;
            benchmark[size][2] = minTime2;
        }

        return benchmark;
    }

    private static double[][] linkedHeapDepth() {
        int rowLength = columnNames.length;
        int columnLength = sizes[0];

        // Array to store the benchmark data
        double[][] benchmark = new double[columnLength][rowLength];

        int heapSize = 1023;
        LinkedHeap heap = new LinkedHeap();

        long time;
        double minTime1, minTime2;

        int[] array, increments;

        array = ArrayGenerator.unsortedNoDup(heapSize, 0, 10000);
        increments = ArrayGenerator.unsortedDup(sizes[0], 10, 100);

        for (int value : array) {
            heap.add(value);
        }

        int depth, incr;
        for (int i = 0; i < increments.length; i++) {
            incr = increments[i];
            depth = heap.push(incr);

            benchmark[i][0] = incr;
            benchmark[i][1] = depth;
        }

        return benchmark;
    }

    private static double[][] queueComparison() {
        int rowLength = columnNames.length;
        int columnLength = sizes.length;

        // Array to store the benchmark data
        double[][] benchmark = new double[columnLength][rowLength];

        LinkedHeap linkedHeap = new LinkedHeap();
        ArrayHeap arrayHeap;
        UnsortedPriorityQueue queueUnsorted = new UnsortedPriorityQueue();
        SortedPriorityQueue queueSorted = new SortedPriorityQueue();

        long startTime, endTime, elapsedTime;
        double linkedTime, arrayTime, unsortedTime, sortedTime;

        int[] array;

        for (int size = 0; size < sizes.length; size++) {
            linkedTime = Double.POSITIVE_INFINITY;
            arrayTime = Double.POSITIVE_INFINITY;
            unsortedTime = Double.POSITIVE_INFINITY;
            sortedTime = Double.POSITIVE_INFINITY;
            arrayHeap = new ArrayHeap(sizes[size]);

            for (int i = 0; i < iterations; i++) {
                array = ArrayGenerator.unsortedNoDup(sizes[size]);

                startTime = System.nanoTime();
                for (int value : array) {
                    linkedHeap.add(value);
                }
                for (int j = 0; j < array.length; j++) {
                    linkedHeap.remove();
                }
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                linkedTime = linkedTime > elapsedTime ? elapsedTime : linkedTime;

                startTime = System.nanoTime();
                for (int value : array) {
                    arrayHeap.add(value);
                }
                for (int j = 0; j < array.length; j++) {
                    arrayHeap.remove();
                }
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                arrayTime = arrayTime > elapsedTime ? elapsedTime : arrayTime;

                startTime = System.nanoTime();
                for (int value : array) {
                    queueUnsorted.add(value);
                }
                for (int j = 0; j < array.length; j++) {
                    queueUnsorted.remove();
                }
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                unsortedTime = unsortedTime > elapsedTime ? elapsedTime : unsortedTime;

                startTime = System.nanoTime();
                for (int value : array) {
                    queueSorted.add(value);
                }
                for (int j = 0; j < array.length; j++) {
                    queueSorted.remove();
                }
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                sortedTime = sortedTime > elapsedTime ? elapsedTime : sortedTime;
            }

            System.out.println("Linked heap - min time for adding/removing " + sizes[size] + " elements: " + linkedTime);
            System.out.println("Array heap - min time for adding/removing " + sizes[size] + " elements: " + arrayTime);
            System.out.println("Unsorted queue - min time for adding/removing " + sizes[size] + " elements: " + unsortedTime);
            System.out.println("Sorted queuue - min time for adding/removing " + sizes[size] + " elements: " + sortedTime);
            System.out.println();

            benchmark[size][0] = sizes[size];
            benchmark[size][1] = linkedTime;
            benchmark[size][2] = arrayTime;
            benchmark[size][3] = unsortedTime;
            benchmark[size][4] = sortedTime;
        }

        return benchmark;
    }

    private static double[][] hashLinearBinary() {
        String fileName = "src/hash/postnummer.csv";

        int rowLength = columnNames.length;
        int columnLength = sizes.length;
        // Array to store the benchmark data
        double[][] benchmark = new double[columnLength][rowLength];

        ZipString zipString = new ZipString(fileName);
        ZipInt zipInt = new ZipInt(fileName);

        long startTime, endTime, elapsedTime;
        double zipStrLinTime, zipStrBinTime, zipIntLinTime, zipIntBinTime;

        String[] strKeys = new String[]{"111 15", "984 99"};

        int row = 0;
        for (String key : strKeys) {

            zipStrLinTime = zipStrBinTime = Double.POSITIVE_INFINITY;
            zipIntLinTime = zipIntBinTime = Double.POSITIVE_INFINITY;

            int intKey = Integer.parseInt(key.replaceAll("\\s",""));

            for (int i = 0; i < iterations; i++) {

                startTime = System.nanoTime();
                zipString.linear(key);
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                zipStrLinTime = elapsedTime < zipStrLinTime ? elapsedTime : zipStrLinTime;

                startTime = System.nanoTime();
                zipString.binary(key);
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                zipStrBinTime = elapsedTime < zipStrBinTime ? elapsedTime : zipStrBinTime;


                startTime = System.nanoTime();
                zipInt.linear(intKey);
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                zipIntLinTime = elapsedTime < zipIntLinTime ? elapsedTime : zipIntLinTime;

                startTime = System.nanoTime();
                zipInt.binary(intKey);
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                zipIntBinTime = elapsedTime < zipIntBinTime ? elapsedTime : zipIntBinTime;
            }

            System.out.println("Zip string version - linear search for " + key + " values: " + zipStrLinTime);
            System.out.println("Zip string version - binary search for " + key + " values: " + zipStrBinTime);
            System.out.println("Zip int version - linear search for " + intKey + " values: " + zipIntLinTime);
            System.out.println("Zip int version - binary search for " + intKey + " values: " + zipIntBinTime);
            System.out.println();

            benchmark[row][0] = intKey;
            benchmark[row][1] = zipStrLinTime;
            benchmark[row][2] = zipStrBinTime;
            benchmark[row][3] = zipIntLinTime;
            benchmark[row++][4] = zipIntBinTime;
        }

        return benchmark;
    }

    private static double[][] hash() {
        int keysSize = 0;
        String[] keys = new String[10000];
        String fileName = "src/hash/postnummer.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                keys[i++] = row[0];
            }
            keysSize = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }

        int rowLength = columnNames.length;
        int columnLength = sizes.length;
        // Array to store the benchmark data
        double[][] benchmark = new double[columnLength][rowLength];

        ZipString zipString = new ZipString(fileName);
        ZipInt zipInt = new ZipInt(fileName);
        Hash hashBad = new Hash(fileName);
        HashBuckets hash = new HashBuckets(fileName, 100000);
        HashImproved hashImproved = new HashImproved(fileName, 100000);

        long startTime, endTime, elapsedTime;
        double zipStrLinTime, zipStrBinTime, zipIntLinTime, zipIntBinTime, hashLargeTime, hashStdTime, hashImprovedTime;

        int[] randomIndices;
        int[] intKeys;
        String[] strKeys;

        int row = 0;
        for (int size : sizes) {

            zipStrLinTime = zipStrBinTime = Double.POSITIVE_INFINITY;
            zipIntLinTime = zipIntBinTime = Double.POSITIVE_INFINITY;
            hashLargeTime = Double.POSITIVE_INFINITY;
            hashStdTime = Double.POSITIVE_INFINITY;
            hashImprovedTime = Double.POSITIVE_INFINITY;

            for (int i = 0; i < iterations; i++) {
                randomIndices = ArrayGenerator.unsortedDup(size, 0, keysSize);
                intKeys = new int[size];
                strKeys = new String[size];

                int j = 0;
                for (int index : randomIndices) {
                    intKeys[j] = Integer.parseInt(keys[index].replaceAll("\\s", ""));
                    strKeys[j++] = keys[index];
                }

                startTime = System.nanoTime();
                for (String key : strKeys) {
                    zipString.linear(key);
                }
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                zipStrLinTime = elapsedTime < zipStrLinTime ? elapsedTime : zipStrLinTime;

                startTime = System.nanoTime();
                for (String key : strKeys) {
                    zipString.binary(key);
                }
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                zipStrBinTime = elapsedTime < zipStrBinTime ? elapsedTime : zipStrBinTime;


                startTime = System.nanoTime();
                for (int key : intKeys) {
                    zipInt.linear(key);
                }
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                zipIntLinTime = elapsedTime < zipIntLinTime ? elapsedTime : zipIntLinTime;

                startTime = System.nanoTime();
                for (int key : intKeys) {
                    zipInt.binary(key);
                }
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                zipIntBinTime = elapsedTime < zipIntBinTime ? elapsedTime : zipIntBinTime;

                startTime = System.nanoTime();
                for (int key : intKeys) {
                    hashBad.lookup(key);
                }
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                hashLargeTime = elapsedTime < hashLargeTime ? elapsedTime : hashLargeTime;

                startTime = System.nanoTime();
                for (int key : intKeys) {
                    hash.lookup(key);
                }
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                hashStdTime = elapsedTime < hashStdTime ? elapsedTime : hashStdTime;

                startTime = System.nanoTime();
                for (int key : intKeys) {
                    hashImproved.lookup(key);
                }
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                hashImprovedTime = elapsedTime < hashImprovedTime ? elapsedTime : hashImprovedTime;
            }

            System.out.println("Zip string version - linear search for " + size + " values: " + zipStrLinTime);
            System.out.println("Zip string version - binary search for " + size + " values: " + zipStrBinTime);
            System.out.println("Zip int version - linear search for " + size + " values: " + zipIntLinTime);
            System.out.println("Zip int version - binary search for " + size + " values: " + zipIntBinTime);
            System.out.println("Hash simple version - lookup for " + size + " values: " + hashLargeTime);
            System.out.println("Hash std version - lookup for " + size + " values: " + hashStdTime);
            System.out.println("Hash improved version - lookup for " + size + " values: " + hashImprovedTime);
            System.out.println();

            benchmark[row][0] = size;
            benchmark[row][1] = zipStrLinTime;
            benchmark[row][2] = zipStrBinTime;
            benchmark[row][3] = zipIntLinTime;
            benchmark[row][4] = zipIntBinTime;
            benchmark[row][5] = hashLargeTime;
            benchmark[row][6] = hashStdTime;
            benchmark[row++][7] = hashImprovedTime;
        }

        return benchmark;
    }

    private static double[][] hashCollisions() {
        int keysSize = 0;
        String[] keys = new String[10000];
        String fileName = "src/hash/postnummer.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                keys[i++] = row[0];
            }
            keysSize = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }

        int rowLength = columnNames.length;
        int columnLength = sizes.length;
        // Array to store the benchmark data
        double[][] benchmark = new double[columnLength][rowLength];

        HashBuckets hashBucket = new HashBuckets(fileName, 19412);
        HashImproved hashImproved = new HashImproved(fileName, 58625);

        int[] randomIndices;
        int[] intKeys;

        int row = 0;
        for (int size : sizes) {

            int collisionsBucket = 0;
            int collisionsImproved = 0;

            for (int i = 0; i < iterations; i++) {
                randomIndices = ArrayGenerator.unsortedDup(size, 0, keysSize);
                intKeys = new int[size];

                int j = 0;
                for (int index : randomIndices) {
                    intKeys[j++] = Integer.parseInt(keys[index].replaceAll("\\s", ""));
                }

                int[] collisionsBucketTable = new int[10];
                for (int key : intKeys) {
                    int collision = hashBucket.lookup(key);
                    if (collision >= collisionsBucketTable.length) {
                        int[] temp = new int[collision + 10];
                        System.arraycopy(collisionsBucketTable, 0, temp, 0, collisionsBucketTable.length);
                        collisionsBucketTable = temp;
                    }
                    collisionsBucketTable[collision]++;
                    collisionsBucket += (collision);
                }

                int[] collisionsImprovedTable = new int[10];
                for (int key : intKeys) {
                    int collision = hashImproved.lookup(key);
                    if (collision >= collisionsImprovedTable.length) {
                        int[] temp = new int[collision + 10];
                        System.arraycopy(collisionsImprovedTable, 0, temp, 0, collisionsImprovedTable.length);
                        collisionsImprovedTable = temp;
                    }
                    collisionsImprovedTable[collision]++;
                    collisionsImproved += (collision);
                }

                System.out.println();
                System.out.println("Zip codes searched: " + size);
                int num = 0;
                for (int i1 : collisionsBucketTable) {
                    System.out.println(num++ + " collisions: " + i1);
                }
                System.out.println("Number of collisions in hash bucket: " + collisionsBucket);
                System.out.println();

                num = 0;
                for (int i1 : collisionsImprovedTable) {
                    System.out.println(num++ + " collisions: " + i1);
                }
                System.out.println("Number of collisions in hash improved: " + collisionsImproved);
                System.out.println();

            }


            benchmark[row][0] = size;
            benchmark[row][1] = collisionsBucket;
            benchmark[row++][2] = collisionsImproved;
        }

        return benchmark;
    }

    public static void main(String[] args) {
        int assignmentNumber = 15;

        setupBench(assignmentNumber);

        double[][] result = bench(assignmentNumber);

        String tableData = LatexHelper.createTable(result, columnNames, rowsToIgnore);
        String graphData = LatexHelper.createGraph(result, rowsToIgnore);

        LatexHelper.write(tableData, fileName, type);
        LatexHelper.write(graphData, fileName, !type);
    }
}

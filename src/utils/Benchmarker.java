package utils;

import sorting.ArraySorter;

import java.util.Arrays;

public class Benchmarker {

    static int[] sizes = {100, 300, 500, 700, 900};
    static int iterations = 10000;

    public static double[] bench(int[] sizes, int numOfColumns) {

        // Array to store the benchmark data
        double[] benchmark = new double[sizes.length * numOfColumns];

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

            benchmark[numOfColumns * sizeNum] = minTime1;
            benchmark[numOfColumns * sizeNum + 1] = minTime2;
            benchmark[numOfColumns * sizeNum + 2] = minTime3;
            benchmark[numOfColumns * sizeNum + 3] = minTime4;
        }

        return benchmark;
    }

    public static void main(String[] args) {

        String[] rowNames = LatexHelper.intArrayToStrArray(sizes);
        String[] columnNames = {"Array size", "Selection sort", "Insertion sort", "Merge sort", "Quick sort"};

        double[] result = bench(sizes, columnNames.length - 1);

        String tableData = LatexHelper.createTable(result, rowNames, columnNames);
        String graphData = LatexHelper.createGraph(result, sizes, columnNames.length - 1);

        LatexHelper.write(tableData, "sorting_table.txt");
        LatexHelper.write(graphData, "sorting_graph.dat");
    }
}

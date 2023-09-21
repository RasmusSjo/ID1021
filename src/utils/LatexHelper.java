package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class LatexHelper {

    public static String createTable(double[][] data, String[] columnNames, int numOfInputCol) {
        int rowLength = data[0].length;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\\begin{table}[h]\n");
        stringBuilder.append("\\begin{center}\n");

        stringBuilder.append("\\begin{tabular}{l");
        stringBuilder.append("|c".repeat(rowLength - 1));
        stringBuilder.append("}\n");

        for (String columnName : columnNames) {
            stringBuilder.append("\\textbf{").append(columnName).append("} & ");
        }
        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "\\\\\n");

        stringBuilder.append("\\hline\n");

        // Create the actual table
        for (double[] row : data) {
            for (int j = 0; j < rowLength; j++) {
                if (j < numOfInputCol) {
                    stringBuilder.append((int) row[j]).append(" & ");
                } else {
                    stringBuilder.append(dataOutput(row[j])).append(" & ");
                }
            }
            stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "\\\\\n");
        }

        stringBuilder.append("\\end{tabular}\n");
        stringBuilder.append("\\caption{Write caption here}\n");
        stringBuilder.append("\\label{table:Replace this with a number}\n");
        stringBuilder.append("\\end{center}\n");
        stringBuilder.append("\\end{table}");

        return stringBuilder.toString();
    }

    public static String createTableMod(double[][] data, String[] columnNames, int modValue) {
        int mod = modValue;
        int rowLength = data[0].length;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\\begin{table}[h]\n");
        stringBuilder.append("\\begin{center}\n");

        stringBuilder.append("\\begin{tabular}{l");
        stringBuilder.append("|c".repeat(rowLength - 1));
        stringBuilder.append("}\n");

        for (String columnName : columnNames) {
            stringBuilder.append("\\textbf{").append(columnName).append("} & ");
        }
        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "\\\\\n");

        stringBuilder.append("\\hline\n");

        // Create the actual table
        for (double[] row : data) {
            for (int j = 0; j < rowLength; j++) {
                if (j % mod == 0 && j != 0) {
                    stringBuilder.append(dataOutput(row[j])).append(" & ");
                    mod += 3;
                } else {
                    stringBuilder.append((int) row[j]).append(" & ");
                }
            }
            mod = modValue;
            stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "\\\\\n");
        }

        stringBuilder.append("\\end{tabular}\n");
        stringBuilder.append("\\caption{Write caption here}\n");
        stringBuilder.append("\\label{table:Replace this with a number}\n");
        stringBuilder.append("\\end{center}\n");
        stringBuilder.append("\\end{table}");

        return stringBuilder.toString();
    }

    public static String createGraph(double[][] data, int numOfInputCol) {
        int rowLength = data[0].length;

        StringBuilder stringBuilder = new StringBuilder();

        for (double[] row : data) {
            for (int j = 0; j < rowLength; j++) {
                if (j < numOfInputCol) {
                    stringBuilder.append((int) row[j]).append(" ");
                } else {
                    stringBuilder.append(dataOutput(row[j])).append(" ");
                }
            }
            stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "\\\\\n");
        }

        return stringBuilder.toString();
    }

    public static String createGraphMod(double[][] data, int modValue) {
        int mod = modValue;
        int rowLength = data[0].length;

        StringBuilder stringBuilder = new StringBuilder();

        for (double[] row : data) {
            for (int j = 0; j < rowLength; j++) {
                if (j % mod == 0 && j != 0) {
                    stringBuilder.append(dataOutput(row[j])).append(" ");
                    mod += 3;
                } else {
                    stringBuilder.append((int) row[j]).append(" ");
                }
            }
            stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "\\\\\n");
            mod = modValue;
        }

        return stringBuilder.toString();
    }

    private static String dataOutput(double data) {
        // Change the following two to get the correct data output. A precision of 1
        // gives nanoseconds, 1000 microseconds, etc. The numbers of 0 in decimal is
        // the amount of decimals in the output.
        int precision = 1000;
        int numOfDecimals = 1;
        String numFormat = "#." + "0".repeat(numOfDecimals);
        DecimalFormat df = new DecimalFormat(numFormat);
        return df.format(data / precision);
    }

    public static void write(String data, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] intArrayToStrArray(int[] array) {
        String[] strings = new String[array.length];

        for (int i = 0; i < array.length; i++) {
            strings[i] = Integer.toString(array[i]);
        }

        return strings;
    }
}

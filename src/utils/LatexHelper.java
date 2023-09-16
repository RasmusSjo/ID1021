package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class LatexHelper {

    public static String createTable(double[] data, String[] rowNames, String[] columnNames) {
        int rowLength = rowNames.length;
        int columnLength = columnNames.length - 1;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\\begin{table}[h]\n");
        stringBuilder.append("\\begin{center}\n");

        stringBuilder.append("\\begin{tabular}{l");
        stringBuilder.append("|c".repeat(columnLength));
        stringBuilder.append("}\n");

        for (String columnName : columnNames) {
            stringBuilder.append("\\textbf{").append(columnName).append("} & ");
        }
        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "\\\\\n");

        stringBuilder.append("\\hline\n");

        // Create the actual table
        for (int i = 0; i < rowLength; i++) {
            stringBuilder.append(rowNames[i]).append(" & ");
            for (int j = 0; j < columnLength; j++) {
                stringBuilder.append(dataOutput(data[i * (columnLength) + j])).append(" & ");
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

    public static String createGraph(double[] data, int[] columns, int rowLength) {
        int columnLength = columns.length;

        StringBuilder stringBuilder = new StringBuilder();
        int columnNum = 0;

        for (int column : columns) {
            stringBuilder.append(column).append(" ");

            for (int i = 0; i < rowLength; i++) {
                if (i == rowLength - 1) {
                    stringBuilder.append(dataOutput(data[rowLength * columnNum++ + i])).append("\n");
                }
                else {
                    stringBuilder.append(dataOutput(data[rowLength * columnNum + i])).append(" ");
                }
            }
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

package utils;

import java.io.FileWriter;
import java.io.IOException;

public class LatexHelper {

    private final static int prefix = 1000;
    private final static int precision = 3;

    public static String createTable(double[][] data, String[] columnNames, int[] colsToIgnore) {
        int rowLength = data[0].length;

        StringBuilder stringBuilder = new StringBuilder();

        tableStart(stringBuilder, columnNames);

        // Create the actual table
        for (double[] row : data) {
            for (int column = 0; column < rowLength; column++) {
                if (contains(colsToIgnore, column)) {
                    stringBuilder.append((int) row[column]).append(" & ");
                } else {
                    stringBuilder.append(formatNumber(row[column])).append(" & ");
                }
            }
            stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "\\\\\n");
        }

        tableEnd(stringBuilder);

        return stringBuilder.toString();
    }

    private static void tableStart(StringBuilder stringBuilder, String[] columnNames) {
        int rowLength = columnNames.length;

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
    }

    private static void tableEnd(StringBuilder stringBuilder) {
        stringBuilder.append("\\end{tabular}\n");
        stringBuilder.append("\\caption{Write caption here}\n");
        stringBuilder.append("\\label{table:Replace this with a number}\n");
        stringBuilder.append("\\end{center}\n");
        stringBuilder.append("\\end{table}");
    }

    public static String createGraph(double[][] data, int[] colsToIgnore) {

        StringBuilder stringBuilder = new StringBuilder();

        for (double[] row : data) {
            for (int column = 0; column < row.length; column++) {
                if (contains(colsToIgnore, column)) {
                    stringBuilder.append((int) row[column]).append(" ");
                } else {
                    stringBuilder.append(formatNumber(row[column])).append(" ");
                }
            }
            stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "\n");
        }

        return stringBuilder.toString();
    }

    private static String formatNumber(double number) {
        StringBuilder result = new StringBuilder();

        number /= prefix;

        String numString = Double.toString(number);

        int digitsToLeftOfDecimal = numString.indexOf('.');
        int zerosToRightOfDecimal = 0;
        if (digitsToLeftOfDecimal == 1){
            if (numString.charAt(0) == '0') {
                digitsToLeftOfDecimal--;
                int i = 2;
                while (numString.charAt(i++) == '0') zerosToRightOfDecimal++;
            }
        }

        int diff = precision - digitsToLeftOfDecimal + zerosToRightOfDecimal;

        double scale = Math.pow(10, diff);
        number *= scale;
        number = Math.round(number);
        number /= scale;

        numString = Double.toString(number);

        result.append(numString);

        double decimalPart = number % 1.0;
        if (decimalPart == 0) {
            result.delete(result.length() - 2, result.length());
            if (numOfDigits(result.toString().toCharArray()) < precision) {
                result.append(".");
            }
            while (numOfDigits(result.toString().toCharArray()) < precision) {
                result.append("0");
            }
        }
        else {
            if (number < 1) {
                int start = 2 + zerosToRightOfDecimal;

                while (numOfDigits(result.substring(start).toCharArray()) < precision) {
                    result.append("0");
                }
            }
            else {
                while (numOfDigits(result.toString().toCharArray()) < precision) {
                    result.append("0");
                }
            }
        }
        return result.toString();
    }

    private static int numOfDigits(char[] number) {
        int count = 0;
        char[] digits = new char[]{'0','1','2','3','4','5','6','7','8','9'};
        for (char num: number) {
            for (int digit : digits) {
                if (num == digit) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static void write(String data, String fileName, boolean type) {
        fileName += type ? "_table.txt" : "_graph.dat";
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

    private static boolean contains(int[] array, int key) {
        for (int num : array) {
            if (num == key) return true;
        }
        return false;
    }
}

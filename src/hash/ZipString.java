package hash;

import java.io.BufferedReader;
import java.io.FileReader;

public class ZipString {

    Node[] data;
    int size;

    public static class Node {

        public String zipCode;
        public String name;
        public Integer population;

        public Node(String zipCode, String name, Integer population) {
            this.zipCode = zipCode;
            this.name = name;
            this.population = population;
        }
    }

    public ZipString(String fileName) {
        int dataSize = 10000;
        data = new Node[dataSize];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data[i++] = new Node(row[0], row[1], Integer.valueOf(row[2]));
            }
            size = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }
    }

    public Node linear(String zipCode) {
        for (int i = 0; i < size; i++) {
            if (data[i].zipCode.equals(zipCode)) {
                return data[i];
            }
        }
        return null;
    }

    public Node binary(String zipCode) {
        int start = 0;
        int end = size;
        int middle;

        while (start <= end) {
            // Find index of element between start and end
            middle = start + (end - start) / 2;

            // Comp < 0 if zipCode < data[middle]
            // Comp = 0 if zipCode = data[middle]
            // Comp > 0 if zipCode > data[middle]
            int comp  = zipCode.compareTo(data[middle].zipCode);

            if (comp == 0) {
                return data[middle];
            }
            else if (comp < 0) {
                end = middle - 1;
            }
            else start = middle + 1;
        }

        return null;
    }
}

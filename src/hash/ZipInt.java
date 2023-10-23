package hash;

import java.io.BufferedReader;
import java.io.FileReader;

public class ZipInt {

    Node[] data;
    int size;

    public static class Node {

        public int zipCode;
        public String name;
        public Integer population;

        public Node(Integer zipCode, String name, Integer population) {
            this.zipCode = zipCode;
            this.name = name;
            this.population = population;
        }
    }

    public ZipInt(String fileName) {
        int dataSize = 10000;
        data = new Node[dataSize];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.parseInt(row[0].replaceAll("\\s", ""));
                data[i++] = new Node(code, row[1], Integer.valueOf(row[2]));
            }
            size = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }
    }

    public Node linear(int zipCode) {
        for (int i = 0; i < size; i++) {
            if (data[i].zipCode == zipCode) {
                return data[i];
            }
        }
        return null;
    }

    public Node binary(int zipCode) {
        int start = 0;
        int end = size;
        int middle;

        while (start <= end) {
            // Find index of element between start and end
            middle = start + (end - start) / 2;

            if (zipCode == data[middle].zipCode) {
                return data[middle];
            }
            else if (zipCode < data[middle].zipCode) {
                end = middle - 1;
            }
            else start = middle + 1;
        }

        return null;
    }
}

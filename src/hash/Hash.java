package hash;

import java.io.BufferedReader;
import java.io.FileReader;

public class Hash {

    Node[] data;
    int size;

    public static class Node {

        public Integer zipCode;
        public String name;
        public Integer population;

        public Node(Integer zipCode, String name, Integer population) {
            this.zipCode = zipCode;
            this.name = name;
            this.population = population;
        }
    }

    public Hash(String fileName) {
        int dataSize = 100000;
        data = new Node[dataSize];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.parseInt(row[0].replaceAll("\\s", ""));
                data[code] = new Node(code, row[1], Integer.valueOf(row[2]));
            }
            size = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }
    }

    public Node lookup(int zipCode) {
        return data[zipCode];
    }
}

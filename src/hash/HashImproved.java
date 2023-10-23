package hash;

import utils.ArrayGenerator;

import java.io.BufferedReader;
import java.io.FileReader;

public class HashImproved {

    Node[] hashMap;
    int hashMod;

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

    public HashImproved(String fileName, int hashMod) {
        // Number taken from modulo benchmark
        this.hashMod = hashMod;
        hashMap = new Node[hashMod];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");

                int zipCode = Integer.parseInt(row[0].replaceAll("\\s", ""));
                int mapIndex = getHashCode(zipCode);
                while (hashMap[mapIndex] != null) {
                    mapIndex = getHashCode(++mapIndex);
                }

                hashMap[mapIndex] = new Node(zipCode, row[1], Integer.valueOf(row[2]));

            }
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }
    }

    public int lookup(int zipCode) {
        int collisions = 0;

        int mapIndex = getHashCode(zipCode);
        while (hashMap[mapIndex].zipCode != zipCode) {
            mapIndex = getHashCode(++mapIndex);
            collisions++;
        }

        return collisions;
    }

    private int getHashCode(int zip) {
        return zip % hashMod;
    }
}

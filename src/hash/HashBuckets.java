package hash;

import java.io.BufferedReader;
import java.io.FileReader;

public class HashBuckets {

    Node[][] hashMap;
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

    public HashBuckets(String fileName, int hashMod) {
        // Number taken from modulo benchmark
        this.hashMod = hashMod;
        hashMap = new Node[hashMod][1];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");

                int zipCode = Integer.parseInt(row[0].replaceAll("\\s", ""));
                int mapIndex = getHashCode(zipCode);

                if (hashMap[mapIndex][0] == null) {
                    hashMap[mapIndex][0] = new Node(zipCode, row[1], Integer.valueOf(row[2]));
                }
                else {
                    // If there is a node at mapIndex already, we need to increase size of that
                    // array by one and copy over old array and then add new node
                    Node[] newBucket = new Node[hashMap[mapIndex].length + 1];
                    System.arraycopy(hashMap[mapIndex], 0, newBucket, 0, newBucket.length - 1);
                    newBucket[newBucket.length - 1] = new Node(zipCode, row[1], Integer.valueOf(row[2]));
                    hashMap[mapIndex] = newBucket;
                }
            }
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }
    }

    public int lookup(int zipCode) {
        int collisions = 0;

        int mapIndex = getHashCode(zipCode);
        int bucketIndex = 0;
        while (hashMap[mapIndex][bucketIndex].zipCode != zipCode) {
            bucketIndex++;
            collisions++;
        }

        return collisions;
    }

    private int getHashCode(int zip) {
        return zip % hashMod;
    }
}

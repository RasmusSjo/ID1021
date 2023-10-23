package hash;

import utils.ArrayGenerator;

import java.io.BufferedReader;
import java.io.FileReader;

public class HashModBench {

    int[] zipCodes;
    int size;

    public HashModBench(String fileName) {
        // Create an array that holds every zip code
        int dataSize = 10000;
        zipCodes = new int[dataSize];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                int zipCode = Integer.parseInt(row[0].replaceAll("\\s", ""));
                zipCodes[i++] = zipCode;
            }
            size = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }
    }

    public int[] collisions(int mod) {
        // Array of length "hashMod" that contains 0's to begin with,
        // will then keep track of the number of zip codes that are contained
        // in a bucket at the hashed index
        int[] hashMap = new int[mod];
        int[] cols = new int[10];

        for (int i = 0; i < size; i++) {
            int mapIndex = getHashCode(zipCodes[i], mod);

            // Assuming the buckets don't contain to many items,
            // hashMap[mapIndex] should be less than 10
            cols[hashMap[mapIndex]]++;

            // Increase the size of the bucket at the hashed index by one
            hashMap[mapIndex]++;
        }

        return cols;
    }

    private int getHashCode(int zipCode, int hashMod) {
        return zipCode % hashMod;
    }

    public static void main(String[] args) {
        HashModBench cities = new HashModBench("src/hash/postnummer.csv");

        // Find the best modulo value between lower- and upper limit
        int lowerLimit = 10000;
        int upperLimit = 20000;

        int[] values = ArrayGenerator.linearArray(lowerLimit, upperLimit);

        int[] result;
        int bestZeroCol = -1;
        int bestModulo = -1;
        for (int modulo : values) {
            result = cities.collisions(modulo);
            if (result[0] > bestZeroCol) {
                bestZeroCol = result[0];
                bestModulo = modulo;
            }
        }

        System.out.println("Best modulo value in range " + lowerLimit + "-" + upperLimit + " is: " + bestModulo);
        result = cities.collisions(bestModulo);
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " collisions: " + result[i]);
        }
    }
}

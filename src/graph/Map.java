package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map {

    private final int hashMod = 541;

    private City[] citiesHash;
    private int numOfCities;
    private int numOfConnections;

    public Map(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            citiesHash = new City[hashMod];
            numOfCities = 0;
            numOfConnections = 0;

            String line;
            while ((line = br.readLine()) != null) {
                String[] information = line.split(",");

                String fromName = information[0];
                String toName = information[1];
                int minutes = Integer.parseInt(information[2]);

                // Will add the cities to the array of cities
                City from = lookup(fromName);
                City to = lookup(toName);

                // Add connection to both cities
                from.addConnection(to, minutes);
                to.addConnection(from, minutes);
                numOfConnections++;
            }
        }
        catch (IOException e) {
            System.out.println(" file " + fileName + " not found");
            e.printStackTrace();
        }
    }

    public void addCity(City city) {
        int hashIndex = getHashCode(city.name);

        int collisions = 0;
        // If there is a collision, go to the next index
        while (collision(hashIndex)) {
            hashIndex++;
            collisions++;
        }

//        System.out.println(collisions);
        citiesHash[hashIndex] = city;
        numOfCities++;
    }

    public City getCity(String name) {
        int hashIndex = getHashCode(name);

        // If the city being looked at is null, the city hasn't been added to the array of cities
        while (citiesHash[hashIndex] != null && !citiesHash[hashIndex].name.equals(name)) {
            hashIndex++;
            // Keep the hash index in bounds
            if (hashIndex >= hashMod) {
                hashIndex = 0;
            }
        }

        return citiesHash[hashIndex];
    }

    private City lookup(String name) {
        City city = getCity(name);

        if (city == null) {
            city = new City(name);

            addCity(city);
        }

        return city;
    }

    private int getHashCode(String name) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 % hashMod) + name.charAt(i);
        }
        return hash % hashMod;
    }

    private boolean collision(int index) {
        return citiesHash[index] != null;
    }

    public static void main(String[] args) {
        String fileName = "src/graph/trains.csv";
        Map map = new Map(fileName);

        City test = new City("test");

        map.addCity(test);
    }
}

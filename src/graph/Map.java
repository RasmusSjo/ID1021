package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {

    private final int MODULO = 541;
    private City[] cities;

    public Map(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            cities = new City[MODULO];

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
            }
        }
        catch (IOException e) {
            System.out.println(" file " + fileName + " not found");
            e.printStackTrace();
        }
    }

    public void addCity(City city) {
        int hashIndex = getHashCode(city.name);

        // If there is a collision, go to the next index
        while (collision(hashIndex)) {
            hashIndex++;
        }

        cities[hashIndex] = city;
    }

    public City getCity(String name) {
        int hashIndex = getHashCode(name);

        // If the city being looked at is null, the city hasn't been added to the array of cities
        while (cities[hashIndex] != null && !cities[hashIndex].name.equals(name)) {
            hashIndex++;
            // Keep the hash index in bounds
            if (hashIndex >= MODULO) {
                hashIndex = 0;
            }
        }

        return cities[hashIndex];
    }

    public ArrayList<City> getCities(String name) {
        City from = getCity(name);
        ArrayList<City> cities = new ArrayList<>();
        cities.add(from);

        for (int i = 0; i < cities.size(); i++) {
            City city = cities.get(i);
            for (int j = 0; j < city.neighbours.length; j++) {
                Connection neighbour = city.neighbours[j];
                if (!cities.contains(neighbour.city)) {
                    cities.add(neighbour.city);
                }
            }
        }

        // Remove initial city from list
        cities.remove(0);
        return cities;
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
            hash = (hash * 31 % MODULO) + name.charAt(i);
        }
        return hash % MODULO;
    }

    private boolean collision(int index) {
        return cities[index] != null;
    }

    public static void main(String[] args) {
        String fileName = "src/graph/trains.csv";
        Map map = new Map(fileName);

        City test = new City("test");

        map.addCity(test);
    }
}

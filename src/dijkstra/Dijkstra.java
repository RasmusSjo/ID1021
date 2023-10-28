package dijkstra;

import java.util.ArrayList;

public class Dijkstra {

    Map map;
    Path[] done;

    PriorityQueue queue;

    public Dijkstra(String fileName) {
        map = new Map(fileName);

        done = new Path[map.getNumOfCities()];

        queue = new PriorityQueue();
    }

    public Path shortest(String source, String destination) {
        City from = map.getCity(source);
        City to = map.getCity(destination);

        if (from != null && to != null) {
            return shortestPath(from, to);
        }

        return null;
    }

    private Path shortestPath(City from, City destination) {
        // Remove any previously stored paths in the array and queue
        done = new Path[done.length];
        queue.clear();

        // Create a path of the source destination and
        // add it to the queue and to the done array
        Path source = new Path(from, null, 0, 0);
        queue.add(source);
        done[source.city.id] = source;

        while (!queue.isEmpty()) {
            // The city being removed should always be "done"
            Path current = queue.poll();
            current.index = null;

            // If the current city is the destination
            if (current.city == destination) {
                current.numOfCities = exploredCities();
                return current;
            }

            // Iterate over the neighbours of the city in the current path
            for (Connection connection : current.city.neighbours) {
                City neighbour = connection.city;

                // Check if the neighbour has been visited already
                if (visited(neighbour)) {
                    int newDistance = current.distance + connection.minutes;

                    // Check if the "new distance" is shorter than the neighbours
                    // previous distance and if it is, update the path of that city
                    if (done[neighbour.id].distance > newDistance) {
                        done[neighbour.id].distance = newDistance;
                        done[neighbour.id].prev = current.city;

                        // Get the queue index of that node and bubble it up if needed
                        int queueIndex = done[neighbour.id].index;
                        queue.bubble(queueIndex);
                    }

                }
                else {
                    // Add a new path to the neighbouring city
                    int distance = current.distance + connection.minutes;
                    Path neighbourPath = new Path(neighbour, current.city, distance);

                    queue.add(neighbourPath);
                    done[neighbour.id] = neighbourPath;
                }
            }
        }

        return null;
    }

    private Integer exploredCities() {
        int cities = 0;
        for (Path path : done) {
            if (path != null) cities++;
        }
        return cities;
    }

    private boolean visited(City city) {
        return done[city.id] != null;
    }

    public void printPath(Path destination) {
        Path temp = destination;

        ArrayList<City> path = new ArrayList<>();

        while (temp != null) {
            path.add(temp.city);
            if (temp.prev == null) break;
            temp = done[temp.prev.id];
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.println(path.get(i).name);
        }
    }
}

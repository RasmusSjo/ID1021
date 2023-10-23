package graph;

public class City {

    public String name;
    public Connection[] neighbors;


    public City(String name) {
        this.name = name;
        neighbors = new Connection[0];
    }

    public void addConnection(City neighbor, int distance) {
        Connection[] temp = new Connection[neighbors.length + 1];
        System.arraycopy(neighbors, 0, temp, 0, neighbors.length);
        neighbors = temp;
        neighbors[neighbors.length - 1] = new Connection(neighbor, distance);
    }
}

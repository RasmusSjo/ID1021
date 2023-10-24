package graph;

public class City {

    public String name;
    public Connection[] neighbours;


    public City(String name) {
        this.name = name;
        neighbours = new Connection[0];
    }

    public void addConnection(City neighbour, int distance) {
        Connection[] temp = new Connection[neighbours.length + 1];
        System.arraycopy(neighbours, 0, temp, 0, neighbours.length);
        neighbours = temp;
        neighbours[neighbours.length - 1] = new Connection(neighbour, distance);
    }
}

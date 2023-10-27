package dijkstra;

public class City {

    public String name;
    public Integer id;
    public Connection[] neighbours;


    public City(String name, Integer id) {
        this.name = name;
        this.id = id;
        neighbours = new Connection[0];
    }

    public void addConnection(City neighbour, int distance) {
        Connection[] temp = new Connection[neighbours.length + 1];
        System.arraycopy(neighbours, 0, temp, 0, neighbours.length);
        neighbours = temp;
        neighbours[neighbours.length - 1] = new Connection(neighbour, distance);
    }
}

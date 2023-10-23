package graph;

public class City {

    public String name;
    public Connection[] connections;


    public City(String name) {
        this.name = name;
        connections = new Connection[0];
    }

    public void addConnection(City destination, int distance) {
        Connection[] temp = new Connection[connections.length + 1];
        System.arraycopy(connections, 0, temp, 0, connections.length);
        connections = temp;
        connections[connections.length - 1] = new Connection(this, destination, distance);
    }
}

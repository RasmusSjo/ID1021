package graph;

public class Connection {

    public City from;
    public City to;
    public int distance;

    public Connection(City from, City to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
}

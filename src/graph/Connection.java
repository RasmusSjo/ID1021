package graph;

public class Connection {

    public City from;
    public City to;
    public int minutes;

    public Connection(City from, City to, int minutes) {
        this.from = from;
        this.to = to;
        this.minutes = minutes;
    }
}

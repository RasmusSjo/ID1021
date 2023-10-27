package dijkstra;

public class Connection {

    public City city;
    public int minutes;

    public Connection(City city, int minutes) {
        this.city = city;
        this.minutes = minutes;
    }
}

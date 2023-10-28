package dijkstra;

public class Path {

    public City city;
    public City prev;
    public Integer distance;
    public Integer index;
    public Integer numOfCities;

    public Path(City city, City prev, Integer distance) {
        this(city, prev, distance, null);
    }

    public Path(City city, City prev, Integer distance, Integer index) {
        this.city = city;
        this.prev = prev;
        this.distance = distance;
        this.index = index;
        numOfCities = 0;
    }
}

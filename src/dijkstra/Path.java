package dijkstra;

import graph.City;

public class Path {

    public City city;
    public City prev;
    public Integer distance;
    public Integer index;

    public Path(City city, Integer index) {
        this(city, null, Integer.MAX_VALUE);
    }

    public Path(City city, City prev, Integer distance) {
        this(city, prev, distance, 0);
    }

    public Path(City city, City prev, Integer distance, Integer index) {
        this.city = city;
        this.prev = prev;
        this.distance = distance;
        this.index = index;
    }
}

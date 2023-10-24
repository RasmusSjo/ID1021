package graph;

public class Naive {

    public static Integer shortest(City from, City destination, Integer max) {
        if (max < 0)
            return null;

        if (from == destination)
            return 0;

        Integer shortest = null;

        for (Connection neighbour : from.neighbours) {
            Integer time = shortest(neighbour.city, destination, max - neighbour.minutes);

            if (time != null) {
                time += neighbour.minutes;
                if (shortest != null) {
                    if (time < shortest) shortest = time;
                }
                else {
                    shortest = time;
                }
            }
        }

        return shortest;
    }

    public static void main(String[] args) {
        Map map = new Map("src/graph/trains.csv");

        String from = "Umeå";
        String to = "Göteborg";
        Integer max = 707;

        long t0 = System.nanoTime();
        Integer dist = shortest(map.getCity(from), map.getCity(to), max);
        long time = (System.nanoTime() - t0) / 1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");
        System.out.println();
    }
}

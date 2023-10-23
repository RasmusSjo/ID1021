package graph;

public class Naive {

    public static Integer shortest(City from, City destination, Integer max) {
        if (max < 0)
            return null;

        if (from == destination)
            return 0;

        Integer shortest = null;

        for (Connection neighbor : from.neighbors) {
            Integer time = shortest(neighbor.to, destination, max - neighbor.minutes);

            if (time != null) {
                time += neighbor.minutes;
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
        args = new String[]{"Göteborg", "Umeå", "707"};

        Map map = new Map("src/graph/trains.csv");

        String from = args[0];
        String to = args[1];
        Integer max = Integer.valueOf(args[2]);

        long t0 = System.nanoTime();
        Integer dist = shortest(map.getCity(from), map.getCity(to), max);
        long time = (System.nanoTime() - t0) / 1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");
        System.out.println();
    }
}

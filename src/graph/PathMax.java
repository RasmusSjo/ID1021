package graph;

public class PathMax {

    City[] path;
    int next;

    public PathMax() {
        path = new City[54];
        next = 0;
    }

    public Integer shortest(City from, City destination, Integer max) {
        // If "from" city has been visited, abort search
        if (visited(from)) return null;

        if (max != null && max < 0) return null;

        if (from == destination) {
            return 0;
        }

        path[next++] = from;

        Integer shortest;
        for (Connection neighbor : from.neighbors) {
            Integer timeLeft;
            if (max != null) {
                timeLeft = max - neighbor.minutes;
            }
            else {
                timeLeft = max;
            }

            shortest = shortest(neighbor.city, destination, timeLeft);

            if (shortest != null) {
                shortest += neighbor.minutes;
                if (max == null) {
                    max = shortest;
                }
                else if (shortest < max) {
                    max = shortest;
                }
            }
        }

        path[next--] = null;

        return max;
    }

    private boolean visited(City city) {
        for (int i = 0; i < next; i++) {
            if (path[i].equals(city)) return true;
        }
        return false;
    }

    public void print() {
        for (int i = 0; i < next; i++) {
            if (i == next - 1) {
                System.out.print(path[i].name);
            }
            else {
                System.out.print(path[i].name + " - ");
            }
        }
    }

    public static void main(String[] args) {
        Map map = new Map("src/graph/trains.csv");
        PathMax path = new PathMax();

        String from = "Malmö";
        String to = "Kiruna";
        Integer max = null;

        long t0 = System.nanoTime();
        Integer dist = path.shortest(map.getCity(from), map.getCity(to), max);
        long time = (System.nanoTime() - t0) / 1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");
        System.out.println();
    }
}

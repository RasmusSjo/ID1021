package graph;

public class Path {

    City[] path;
    int next;

    public Path() {
        path = new City[54];
        next = 0;
    }

    public Integer shortest(City from, City destination) {
        // If "from" city has been visited, abort search
        if (visited(from)) return null;

        if (from == destination) {
            return 0;
        }

        path[next++] = from;

        Integer shortest = null;
        for (Connection neighbor : from.neighbors) {
            Integer time = shortest(neighbor.city, destination);

            if (time != null) {
                time += neighbor.minutes;
                if (shortest != null) {
                    if (time < shortest) {
                        shortest = time;
                    }
                }
                else {
                    shortest = time;
                }
            }
        }

        path[next--] = null;

        return shortest;
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
        Path path = new Path();

        String from = "Malmö";
        String to = "Kiruna";

        long t0 = System.nanoTime();
        Integer dist = path.shortest(map.getCity(from), map.getCity(to));
        long time = (System.nanoTime() - t0) / 1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");
        path.print();
        System.out.println();
    }
}

package TH1;
import java.util.*;

public class Bai3 {

    static class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            if (this.x == p.x) return Integer.compare(this.y, p.y);
            return Integer.compare(this.x, p.x);
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    private static long crossProduct(Point O, Point A, Point B) {
        return (long)(A.x - O.x) * (B.y - O.y) - (long)(A.y - O.y) * (B.x - O.x);
    }

    public static List<Point> getWarningStations(List<Point> points) {
        int n = points.size();
        if (n <= 2) return points;

        Collections.sort(points);
        List<Point> hull = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {
            Point p = points.get(i);
            while (hull.size() >= 2 && crossProduct(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(p);
        }

        int t = hull.size() + 1;
        for (Point p : points) {
            while (hull.size() >= t && crossProduct(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(p);
        }

        hull.remove(hull.size() - 1);
        return hull;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            List<Point> stations = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                stations.add(new Point(x, y));
            }

            List<Point> warnings = getWarningStations(stations);

            for (Point p : warnings) {
                System.out.println(p);
            }
        }

        scanner.close();
    }
}
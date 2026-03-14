package TH1;
public class Bai1 {
    public static double approximateArea(double r, int numPoints) {
        int pointsInside = 0;
        for (int i = 0; i < numPoints; i++) {
            double x = (Math.random() * 2 * r) - r;
            double y = (Math.random() * 2 * r) - r;

            if (x * x + y * y <= r * r) s{
                pointsInside++;
            }
        }
        return ((double) pointsInside / numPoints) * (4 * r * r);
    }

    public static void main(String[] args) {
        double r = 5.0;
        int numPoints = 1000000;
        System.out.println("Diện tích xấp xỉ của hình tròn bán kính " + r + " là: " + approximateArea(r, numPoints));
    }
}
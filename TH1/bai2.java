package TH1;
public class Bai2 {
    public static double approximatePi(int numPoints) {
        int pointsInside = 0;
        for (int i = 0; i < numPoints; i++) {
            double x = (Math.random() * 2) - 1;
            double y = (Math.random() * 2) - 1;

            if (x * x + y * y <= 1) {
                pointsInside++;
            }
        }
        return 4.0 * pointsInside / numPoints;
    }

    public static void main(String[] args) {
        int numPoints = 1000000;
        System.out.println("Giá trị Pi xấp xỉ là: " + approximatePi(numPoints));
    }
}
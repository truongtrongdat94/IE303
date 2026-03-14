package TH1;
import java.util.*;

public class Bai4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        scanner.useDelimiter("[\\s,]+");

        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }

        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int s = 0; s <= k; s++) {
                dp[i][s] = dp[i - 1][s];

                // Trường hợp có thể chọn A[i-1]
                if (s >= A[i - 1] && dp[i - 1][s - A[i - 1]] != -1) {
                    dp[i][s] = Math.max(dp[i][s], dp[i - 1][s - A[i - 1]] + 1);
                }
            }
        }

        if (dp[n][k] == -1) {
            System.out.println("Khong co day con thoa man.");
            scanner.close();
            return;
        }

        List<Integer> result = new ArrayList<>();
        int currentSum = k;

        for (int i = n; i > 0 && currentSum > 0; i--) {
            if (dp[i][currentSum] == dp[i - 1][currentSum]) {
                continue;
            } else {
                result.add(A[i - 1]);
                currentSum -= A[i - 1];
            }
        }

        Collections.reverse(result);

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + (i == result.size() - 1 ? "" : ", "));
        }
        System.out.println();

        scanner.close();
    }
}
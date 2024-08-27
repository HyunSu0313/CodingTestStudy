package baekjoon.dp;

import java.util.Scanner;

/**
 * 백준 2193 (DP 예제)
 * 이친수 구하기
 */
public class P2193 {
    static long[][] D;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        D = new long[n + 1][2];
        D[1][1] = 1;
        D[1][0] = 0;

        for (int i = 2; i < n + 1; i++) {
            D[i][0] = D[i - 1][1] + D[i - 1][0];
            D[i][1] = D[i - 1][0];
        }

        System.out.println(D[n][0] + D[n][1]);
    }
}

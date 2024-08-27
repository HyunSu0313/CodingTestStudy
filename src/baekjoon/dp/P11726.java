package baekjoon.dp;

import java.util.Scanner;

/**
 * 백준 11726 (DP 예제)
 * 2*N 타일 채우기
 */
public class P11726 {
    static long mod = 10007;
    static long[] D;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        D = new long[1001];

        D[1] = 1;
        D[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            D[i] = (D[i - 1] + D[i - 2]) % mod;
        }
        System.out.println(D[n]);
    }
}

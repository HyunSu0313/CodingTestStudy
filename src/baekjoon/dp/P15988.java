package baekjoon.dp;

import java.util.Scanner;

public class P15988 {
    static long[] D;
    static int mod = 1000000009;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        D = new long[1000001];
        D[1] = 1;
        D[2] = 2;
        D[3] = 4;

        for (int i = 4; i < D.length; i++) {
            D[i] = (D[i - 1] % mod + D[i - 2] % mod + D[i - 3] % mod) % mod;
        }

        for (int i = 0; i < T; i++) {
            int n = scanner.nextInt();
            System.out.println(D[n]);
        }
    }
}

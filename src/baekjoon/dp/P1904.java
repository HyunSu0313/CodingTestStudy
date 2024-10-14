package baekjoon.dp;

import java.util.Scanner;

public class P1904 {
    static long[] D;
    static int mod = 15746;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        D = new long[1000001];
        D[1] = 1;
        D[2] = 2;

        for (int i = 3; i < 1000001; i++) {
            D[i] = (D[i - 1] % mod + D[i - 2] % mod) % mod;
        }

        System.out.println(D[N]);
    }
}

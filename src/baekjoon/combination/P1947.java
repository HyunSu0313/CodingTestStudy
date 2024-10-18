package baekjoon.combination;

import java.util.Scanner;

public class P1947 {
    static long[] D;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        long mod = 1000000000;
        D = new long[1000001];

        D[2] = 1;

        for (int i = 3; i < N + 1; i++) {
            D[i] = (i - 1) * (D[i - 1] + D[i - 2]) % mod;
        }

        System.out.println(D[N]);
    }
}

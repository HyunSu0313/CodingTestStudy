package baekjoon.dp;

import java.util.Scanner;

public class P2579 {
    static int[] D;
    static int[] T;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        D = new int[N + 1];
        T = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            T[i] = scanner.nextInt();
        }

        D[1] = T[1];
        if (N >= 2) D[2] = T[1] + T[2];

        for (int i = 3; i < N + 1; i++) {
            D[i] = Math.max(D[i - 2], D[i - 3] + T[i - 1]) + T[i];
        }

        System.out.println(D[N]);
    }
}

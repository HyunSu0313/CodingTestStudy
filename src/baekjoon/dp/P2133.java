package baekjoon.dp;

import java.util.Scanner;

public class P2133 {
    static int[] D;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        if (N % 2 != 0) {
            System.out.println(0);
            return;
        }

        D = new int[N + 1];

        D[0] = 1;
        D[2] = 3;

        for (int i = 4; i <= N; i += 2) {
            D[i] = D[i - 2] * 3;

            for (int j = i - 4; j >= 0; j -= 2) {
                D[i] += D[j] * 2;
            }
        }

        System.out.println(D[N]);
    }
}

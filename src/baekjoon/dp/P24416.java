package baekjoon.dp;

import java.util.Scanner;

public class P24416 {
    static int[] D;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        D = new int[N + 1];
        D[1] = 1;
        D[2] = 1;

        for (int i = 3; i < N + 1; i++) {
            D[i] = D[i - 1] + D[i - 2];
        }

        System.out.print(D[N] + " ");
        System.out.println(N - 2);
    }
}

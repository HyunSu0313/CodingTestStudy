package baekjoon.dp;

import java.util.Scanner;

public class P9655 {
    static int[] D;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        D = new int[N + 1];
        D[1] = 1;

        for (int i = 2; i < N + 1; i++) {
            D[i] = D[i - 1] + 1;
            if (i > 2) {
                D[i] = Math.min(D[i], D[i - 3] + 1);
            }
        }

        if (D[N] % 2 == 1) System.out.println("SK");
        else System.out.println("CY");
    }
}

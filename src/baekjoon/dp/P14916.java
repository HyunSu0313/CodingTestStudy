package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

public class P14916 {
    static int[] D;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        D = new int[N + 1];
        Arrays.fill(D, 1000001);
        D[0] = 0;

        for (int i = 2; i < N + 1; i++) {
            D[i] = Math.min(D[i], D[i - 2] + 1);
            if (i >= 5) D[i] = Math.min(D[i], D[i - 5] + 1);
        }

        if (D[N] >= 1000001) System.out.println("-1");
        else System.out.println(D[N]);
    }
}

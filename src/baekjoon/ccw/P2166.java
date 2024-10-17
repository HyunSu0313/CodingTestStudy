package baekjoon.ccw;

import java.util.Scanner;

public class P2166 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[] x = new long[N + 1];
        long[] y = new long[N + 1];

        for (int i = 0; i < N; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }

        x[N] = x[0];
        y[N] = y[0];

        double result = 0;

        for (int i = 0; i < N; i++) {
            result += (x[i] * y[i + 1]) - (x[i + 1] * y[i]);
        }

        String answer = String.format("%.1f", Math.abs(result) / 2.0);
        System.out.println(answer);
    }
}

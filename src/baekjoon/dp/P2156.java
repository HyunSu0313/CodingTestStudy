package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2156 {
    static int[] A;
    static long[] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        D = new long[10001];
        A = new int[10001];

        for (int i = 1; i < N + 1; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        D[1] = A[1];
        D[2] = A[1] + A[2];

        for (int i = 3; i < N + 1; i++) {
            D[i] = Math.max(Math.max(D[i - 1], D[i - 3] + A[i - 1] + A[i]), D[i - 2] + A[i]);
        }

        System.out.println(D[N]);
    }
}
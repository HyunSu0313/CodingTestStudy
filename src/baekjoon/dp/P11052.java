package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11052 {
    static long[] D;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        D = new long[1001];
        A = new int[1001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        D[1] = A[1];

        for (int i = 2; i < N + 1; i++) {
            for (int j = 1; j <= i; j++) {
                D[i] = Math.max(D[i], D[i - j] + A[j]);
            }
        }

        System.out.println(D[N]);
    }
}
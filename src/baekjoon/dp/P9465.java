package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P9465 {
    static long[][] D;
    static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            D = new long[3][100001];
            A = new int[3][100001];

            for (int j = 1; j < 3; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k < N + 1; k++) {
                    A[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            D[1][1] = A[1][1];
            D[2][1] = A[2][1];

            for (int j = 2; j < N + 1; j++) {
                D[1][j] = Math.max(Math.max(D[1][j - 2], D[2][j - 2]), D[2][j - 1]) + A[1][j];
                D[2][j] = Math.max(Math.max(D[1][j - 2], D[2][j - 2]), D[1][j - 1]) + A[2][j];
            }

            System.out.println(Math.max(D[1][N], D[2][N]));
        }
    }
}

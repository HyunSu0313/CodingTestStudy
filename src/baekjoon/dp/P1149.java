package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1149 {
    static int[][] A, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        A = new int[1001][3];
        D = new int[1001][3];

        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i][0] = Integer.parseInt(st.nextToken());
            A[i][1] = Integer.parseInt(st.nextToken());
            A[i][2] = Integer.parseInt(st.nextToken());
        }

        D[1][0] = A[1][0];
        D[1][1] = A[1][1];
        D[1][2] = A[1][2];

        for (int i = 2; i < N + 1; i++) {
            D[i][0] = Math.min(D[i - 1][1], D[i - 1][2]) + A[i][0];
            D[i][1] = Math.min(D[i - 1][0], D[i - 1][2]) + A[i][1];
            D[i][2] = Math.min(D[i - 1][0], D[i - 1][1]) + A[i][2];
        }

        int result = Math.min(Math.min(D[N][0], D[N][1]), D[N][2]);
        System.out.println(result);
    }
}
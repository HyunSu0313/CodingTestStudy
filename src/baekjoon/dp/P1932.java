package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1932 {
    static int[][] D;
    static int[][] T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        D = new int[N + 1][N + 1];
        T = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < i + 1; j++) {
                T[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        D[1][1] = T[1][1];

        for (int i = 2; i < N + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                if (j == 1) {
                    D[i][1] = D[i - 1][1] + T[i][1];
                } else if (j == i) {
                    D[i][j] = D[i - 1][j - 1] + T[i][j];
                } else {
                    D[i][j] = Math.max(D[i - 1][j - 1], D[i - 1][j]) + T[i][j];
                }
            }
        }

        int result = 0;
        for (int i = 1; i < N + 1; i++) {
            result = Math.max(D[N][i], result);
        }
        System.out.println(result);
    }
}

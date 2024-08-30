package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2098 (DP 활용 -TSP)
 * 외판원의 순회 경로 짜기
 */
public class P2098 {
    static final int INF = 1000000 * 16 + 1;
    static int N;
    static int[][] W;
    static int[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine().trim());
        W = new int[16][16];
        d = new int[16][1 << 16];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(TSP(0, 1));
    }

    static int TSP(int c, int v) {
        if (v == (1 << N) - 1) {
            return W[c][0] == 0 ? INF : W[c][0];
        }
        if (d[c][v] != 0) {
            return d[c][v];
        }
        int min_val = INF;
        for (int i = 0; i < N; i++) {
            if ((v & (1 << i)) == 0 && W[c][i] != 0) {
                min_val = Math.min(min_val, TSP(i, (v | (1 << i))) + W[c][i]);
            }
        }
        d[c][v] = min_val;
        return d[c][v];
    }
}

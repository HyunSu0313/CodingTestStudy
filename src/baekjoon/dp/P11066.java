package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11066 {
    static int[] S;
    static int[] prefixSum;
    static int[][] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            S = new int[N + 1];
            prefixSum = new int[N + 1];
            D = new int[N + 1][N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                S[i] = Integer.parseInt(st.nextToken());
                prefixSum[i] = prefixSum[i - 1] + S[i];
            }

            for (int size = 2; size <= N; size++) {
                for (int i = 1; i <= N - size + 1; i++) {
                    int j = i + size - 1;
                    D[i][j] = Integer.MAX_VALUE;

                    for (int k = i; k < j; k++) {
                        D[i][j] = Math.min(D[i][j], D[i][k] + D[k + 1][j] + (prefixSum[j] - prefixSum[i - 1]));
                    }
                }
            }

            System.out.println(D[1][N]);
        }
    }
}

package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1535 {
    static int[] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] L = new int[N + 1];
        int[] J = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            J[i] = Integer.parseInt(st.nextToken());
        }

        D = new int[101];

        for (int i = 1; i <= N; i++) {
            for (int h = 100; h > L[i]; h--) {
                D[h] = Math.max(D[h], D[h - L[i]] + J[i]);
            }
        }

        System.out.println(D[100]);
    }
}

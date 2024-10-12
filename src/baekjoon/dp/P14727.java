package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P14727 {
    static long[] D, S, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        D = new long[N + 1];
        S = new long[N + 1];
        H = new long[N + 1];

        for (int i = 1; i < N + 1; i++) {
            H[i] = Long.parseLong(br.readLine());
        }

        long minHeight = 1000001;
        for (int i = 1; i < N + 1; i++) {
            minHeight = Math.min(minHeight, H[i]);
            S[i] = minHeight;
        }

        for (int i = 1; i < N + 1; i++) {
            D[i] = Math.max(Math.max(S[i] * i, H[i]), D[i - 1]);
        }

        System.out.println(D[N]);
    }
}

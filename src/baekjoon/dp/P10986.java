package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10986 {
    static long[] A, D, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        A = new long[N + 1];
        D = new long[N + 1];
        result = new long[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        D[1] = A[1] % M;

        for (int i = 2; i < N + 1; i++) {
            D[i] = (D[i - 1] % M + A[i] % M) % M;
        }

        for (int i = 1; i < N + 1; i++) {
            int n = (int)D[i];
            result[n]++;
        }

        long count = result[0];

        for (int i = 0; i < M; i++) {
            if (result[i] > 1) count += (result[i] * (result[i] - 1) / 2);
        }

        System.out.println(count);
    }
}

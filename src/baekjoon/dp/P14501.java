package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 14501 (DP 활용)
 * 퇴사 준비 하기
 */
public class P14501 {
    static int[] D, T, P;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        D = new int[N + 2];
        T = new int[N + 1];
        P = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            T[i] = a;
            P[i] = b;
        }

        for (int i = N; i > 0; i--) {
            if (i + T[i] > N + 1) {
                D[i] = D[i + 1];
            } else {
                D[i] = Math.max(D[i + 1], P[i] + D[i + T[i]]);
            }
        }

        System.out.println(D[1]);
    }
}

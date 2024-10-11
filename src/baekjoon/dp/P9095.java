package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9095 {
    static int[] D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        D = new int[11];
        D[1] = 1;
        D[2] = 2;
        D[3] = 4;

        for (int j = 4; j < 11; j++) {
            D[j] = D[j - 1] + D[j - 2] + D[j - 3];
        }

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(D[N]);
        }
    }
}

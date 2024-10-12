package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2670 {
    static double[] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        D = new double[N + 1];

        for (int i = 1; i < N + 1; i++) {
            D[i] = Double.parseDouble(br.readLine());
        }

        double maxResult = D[0];
        double currentMul = D[0];

        for (int i = 1; i < N + 1; i++) {
            currentMul = Math.max(D[i], currentMul * D[i]);
            maxResult = Math.max(currentMul, maxResult);
        }

        System.out.printf("%.3f%n", maxResult);
    }
}

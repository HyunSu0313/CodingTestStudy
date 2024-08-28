package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 13398 (DP 예제)
 * 연속된 정수의 합 구하기
 */
public class P13398 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] A = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] L = new int[n];
        int[] R = new int[n];
        L[0] = A[0];
        R[n - 1] = A[n - 1];

        int result = L[0];
        for (int i = 1; i < n; i++) {
            L[i] = Math.max(A[i], L[i - 1] + A[i]);
            result = Math.max(result, L[i]);
        }

        for (int i = n - 2; i >= 0; i--) {
            R[i] = Math.max(A[i], R[i + 1] + A[i]);
        }

        for (int i = 1; i < n - 1; i++) {
            int temp = L[i - 1] + R[i + 1];
            result = Math.max(result, temp);
        }

        System.out.println(result);
    }
}

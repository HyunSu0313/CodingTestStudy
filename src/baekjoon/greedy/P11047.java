package baekjoon.greedy;

import java.util.*;
import java.io.*;

/**
 * 백준 11047 (greedy 기본)
 * 동전 갯수의 최솟값 구하기
 */
public class P11047 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int count = 0;

        int[] A = new int[N];

        for (int i = 0; i < A.length; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N - 1; i >= 0; i--) {
            if (K / A[i] > 0) {
                int n = K / A[i];
                int m = n * A[i];
                count += n;
                K -= m;
            }
        }

        System.out.println(count);
    }
}

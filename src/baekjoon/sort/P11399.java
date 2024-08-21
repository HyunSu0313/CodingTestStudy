package baekjoon.sort;

import java.util.*;
import java.io.*;

/**
 * 백준 11399 (삽입 정렬)
 */
public class P11399 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] A = new int[n];
        int[] S = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n; i++) {
            int insert_point = i;
            int insert_value = A[i];

            for (int j = i - 1; j >= 0; j--) {
                if (A[j] < A[i]) {
                    insert_point = j + 1;
                    break;
                }

                if (j == 0) insert_point = 0;
            }

            for (int j = i; j > insert_point; j--) {
                A[j] = A[j - 1];
            }

            A[insert_point] = insert_value;
        }

        S[0] = A[0];

        for (int i = 1; i < n; i++) {
            S[i] = S[i - 1] + A[i];
        }

        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += S[i];
        }

        System.out.println(sum);
    }
}

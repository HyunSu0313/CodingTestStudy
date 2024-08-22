package baekjoon.binarysearch;

import java.util.*;
import java.io.*;

/**
 * 백준 1920 (이분 탐색 기초)
 */
public class P1920 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] A = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            boolean find = false;
            int target = Integer.parseInt(st.nextToken());
            int start = 0;
            int end = A.length - 1;

            while (start <= end) {
                int midIndex = (start + end) / 2;
                int midValue = A[midIndex];

                if (target < midValue) {
                    end = midIndex - 1;
                } else if (target > midValue) {
                    start = midIndex + 1;
                } else {
                    find = true;
                    break;
                }
            }

            if (find) System.out.println(1);
            else System.out.println(0);
        }
    }
}

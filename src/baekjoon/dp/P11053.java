package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11053 {
    static int N, maxLength;
    static int[] B = new int[1000001];
    static int[] A = new int[1000001];
    static int[] D = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int index;
        B[++maxLength] = A[1];
        D[1] = 1;

        for (int i = 2; i < N + 1; i++) {
            if (B[maxLength] < A[i]) {
                B[++maxLength] = A[i];
                D[i] = maxLength;
            } else {
                index = binarySearch(1, maxLength, A[i]);
                B[index] = A[i];
                D[i] = index;
            }
        }

        System.out.println(maxLength);
    }

    static int binarySearch(int l, int r, int now) {
        int mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (B[mid] < now) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}

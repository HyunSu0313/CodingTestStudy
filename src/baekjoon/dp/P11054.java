package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * น้มุ 11054 LIS + LDS
 */
public class P11054 {
    static int N;
    static int[] A, increase, decrease, LIS, LDS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        increase = new int[N];
        decrease = new int[N];
        LIS = new int[N];
        LDS = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int lengthLIS = 0;
        for (int i = 0; i < N; i++) {
            int pos = binarySearch(LIS, lengthLIS, A[i]);
            LIS[pos] = A[i];
            increase[i] = pos + 1;
            if (pos == lengthLIS) {
                lengthLIS++;
            }
        }

        int lengthLDS = 0;
        for (int i = N - 1; i >= 0; i--) {
            int pos = binarySearch(LDS, lengthLDS, A[i]);
            LDS[pos] = A[i];
            decrease[i] = pos + 1;
            if (pos == lengthLDS) {
                lengthLDS++;
            }
        }

        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            maxLength = Math.max(maxLength, increase[i] + decrease[i] - 1);
        }

        System.out.println(maxLength);
    }

    static int binarySearch(int[] array, int length, int key) {
        int left = 0;
        int right = length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}


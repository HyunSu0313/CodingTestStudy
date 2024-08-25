package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1976 (Union-Find 활용)
 * 연결된 도시 찾기
 */
public class P1976 {
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        A = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            A[i] = i;
        }

        int[][] data = new int[n + 1][n + 1];

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] route = new int[m + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < m + 1; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (data[i][j] == 1) Union(i, j);
            }
        }

        int index = Find(route[1]);

        for (int i = 2; i < route.length; i++) {
            if (index != Find(route[i])) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    static void Union(int i, int j) {
        i = Find(i);
        j = Find(j);

        if (i != j) {
            A[j] = i;
        }
    }

    static int Find(int i) {
        if (A[i] == i) {
            return i;
        } else {
            return A[i] = Find(A[i]);
        }
    }
}

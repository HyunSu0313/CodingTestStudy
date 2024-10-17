package baekjoon.ccw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2162 {
    static int[] parent = new int[3001];
    static int[][] L = new int[3001][4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N + 1; i++) {
            parent[i] = -1;
        }

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            L[i][0] = Integer.parseInt(st.nextToken());
            L[i][1] = Integer.parseInt(st.nextToken());
            L[i][2] = Integer.parseInt(st.nextToken());
            L[i][3] = Integer.parseInt(st.nextToken());

            for (int j = 1; j < i; j++) {
                if (isCross(L[i][0], L[i][1], L[i][2], L[i][3], L[j][0], L[j][1], L[j][2], L[j][3])) {
                    Union(i, j);
                }
            }
        }

        int ans = 0;
        int res = 0;

        for (int i = 1; i < N + 1; i++) {
            if (parent[i] < 0) {
                ans++;
                res = Math.min(res, parent[i]);
            }
        }

        System.out.println(ans);
        System.out.println(-res);
    }

    private static void Union (int i, int j) {
        int p = Find(i);
        int q = Find(j);
        if (p == q) return;

        parent[p] += parent[q];
        parent[q] = p;
    }

    private static int Find(int i) {
        if (parent[i] < 0) return i;
        else return parent[i] = Find(parent[i]);
    }

    static int CCW(long x1, long y1, long x2, long y2, long x3, long y3) {
        long temp = (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3);
        if (temp > 0) return 1;
        else if (temp < 0) return -1;
        return 0;
    }

    private static boolean isOverlab(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        if (Math.min(x1, x2) <= Math.max(x3, x4) && Math.min(x3, x4) <= Math.max(x1, x2) && Math.min(y1, y2) <= Math.max(y3, y4) && Math.min(y3, y4) <= Math.max(y1, y2)) return true;
        return false;
    }

    private static boolean isCross(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        int abc = CCW(x1, y1, x2, y2, x3, y3);
        int abd = CCW(x1, y1, x2, y2, x4, y4);
        int cda = CCW(x3, y3, x4, y4, x1, y1);
        int cdb = CCW(x3, y3, x4, y4, x2, y2);

        if (abc * abd == 0 && cda * cdb == 0) {
            return isOverlab(x1, y1, x2, y2, x3, y3, x4, y4);
        } else if (abc * abd <= 0 && cda * cdb <= 0) {
            return true;
        }
        return false;
    }
}

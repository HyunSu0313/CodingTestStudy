package baekjoon.dp;

import java.util.Scanner;

/**
 * 백준 11049 (DP 활용 예제)
 * 행렬 곱 연산 횟수의 최솟값 구하기
 */
public class P11049 {
    static int N;
    static Matrix[] M;
    static int[][] D;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = new Matrix[N + 1];
        D = new int[N + 1][N + 1];

        for (int i = 0; i < D.length; i++) {
            for (int j = 0; j < D[i].length; j++) {
                D[i][j] = -1;
            }
        }

        for (int i = 1; i < N + 1; i++) {
            int y = scanner.nextInt();
            int x = scanner.nextInt();
            M[i] = new Matrix(y, x);
        }
        System.out.println(execute(1, N));
    }

    static int execute(int s, int e) {
        int result = Integer.MAX_VALUE;
        if (D[s][e] != -1) {
            return D[s][e];
        }
        if (s == e) return 0;
        if (s + 1 == e) return M[s].y * M[s].x * M[e].x;
        for (int i = s; i < e; i++) {
            result = Math.min(result, M[s].y * M[i].x * M[e].x + execute(s, i) + execute(i + 1, e));
        }
        return D[s][e] = result;
    }

    static class Matrix {
        private int y;
        private int x;

        public Matrix(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}

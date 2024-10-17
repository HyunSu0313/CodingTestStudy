package baekjoon.dp;

import java.util.Scanner;

public class P11444 {

    static final long MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        if (n == 0) {
            System.out.println(0);
            return;
        }

        long[][] result = {{1, 0}, {0, 1}};
        long[][] fibMatrix = {{1, 1}, {1, 0}};

        result = matrixPower(fibMatrix, n - 1);

        System.out.println(result[0][0]);
    }

    private static long[][] matrixPower(long[][] matrix, long power) {
        long[][] result = {{1, 0}, {0, 1}}; // 단위 행렬
        long[][] base = matrix;

        while (power > 0) {
            if (power % 2 == 1) {
                result = multiplyMatrix(result, base);
            }
            base = multiplyMatrix(base, base);
            power /= 2;
        }

        return result;
    }

    private static long[][] multiplyMatrix(long[][] a, long[][] b) {
        long[][] result = new long[2][2];

        result[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % MOD;
        result[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % MOD;
        result[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % MOD;
        result[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % MOD;

        return result;
    }
}

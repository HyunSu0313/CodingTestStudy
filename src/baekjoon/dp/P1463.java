package baekjoon.dp;

import java.util.Scanner;

/**
 * 백준 1463 (DP 예제)
 * 정수를 1로 만들기
 */
public class P1463 {
    static int[] D;
    static int N;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        D = new int[N + 1];
        D[1] = 0;

        for (int i = 2; i < N + 1; i++) {
            D[i] = D[i - 1] + 1;
            if (i % 2 == 0) D[i] = Math.min(D[i], D[i / 2] + 1);
            if (i % 3 == 0) D[i] = Math.min(D[i], D[i / 3] + 1);
        }

        System.out.println(D[N]);
    }
}

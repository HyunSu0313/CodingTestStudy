package baekjoon.dp;

import java.util.Scanner;

/**
 * 백준 2747 (DP 기본 예제 - 피보나치 수열)
 * TopDown 방식
 * TopDown - 재귀 함수 형태로 구현
 */
public class P2747_TopDown {
    static int[] D; //DP 테이블 용도 - 메모이제이션 (부분 문제를 풀었을 때 DP 테이블에 저장해 놓고 다시 계산 하지 않는다!!)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        D = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            D[i] = -1;
        }

        D[0] = 0;
        D[1] = 1;

        Fibonacci(n);
        System.out.println(D[n]);
    }

    static int Fibonacci(int n) {
        if (D[n] != -1) {
            return D[n];
        }
        return D[n] = Fibonacci(n - 2) + Fibonacci(n - 1);
    }
}

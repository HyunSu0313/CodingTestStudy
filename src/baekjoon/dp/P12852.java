package baekjoon.dp;

import java.util.ArrayList;
import java.util.Scanner;

public class P12852 {
    static int[] D;
    static int[] path;
    static int N;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        D = new int[N + 1];
        path = new int[N + 1];
        D[1] = 0;

        for (int i = 2; i <= N; i++) {
            D[i] = D[i - 1] + 1;
            path[i] = i - 1;

            if (i % 2 == 0 && D[i] > D[i / 2] + 1) {
                D[i] = D[i / 2] + 1;
                path[i] = i / 2;
            }

            if (i % 3 == 0 && D[i] > D[i / 3] + 1) {
                D[i] = D[i / 3] + 1;
                path[i] = i / 3;
            }
        }

        System.out.println(D[N]);

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = N; i != 0; i = path[i]) {
            result.add(i);
        }

        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}

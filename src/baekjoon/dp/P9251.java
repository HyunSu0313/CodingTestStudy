package baekjoon.dp;

import java.util.Scanner;

public class P9251 {
    static int[][] D;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String A = scanner.nextLine();
        String B = scanner.nextLine();

        D = new int[A.length() + 1][B.length() + 1];

        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < B.length(); j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    D[i + 1][j + 1] = D[i][j] + 1;
                } else {
                    D[i + 1][j + 1] = Math.max(D[i][j + 1], D[i + 1][j]);
                }
            }
        }

        System.out.println(D[A.length()][B.length()]);
    }
}

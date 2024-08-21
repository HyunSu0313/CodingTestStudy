package baekjoon.sort;

import java.util.*;

/**
 * 백준 1427 (선택 정렬 기본)
 */
public class P1427 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        int[] n = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            n[i] = Integer.parseInt(s.substring(i, i + 1));
        }

        for (int i = 0; i < n.length - 1; i++) {
            int max = i;
            for (int j = i + 1; j < n.length; j++) {
                if (n[max] < n[j]) max = j;
            }

            int temp = n[i];
            n[i] = n[max];
            n[max] = temp;
        }

        for (int i = 0; i < n.length; i++) {
            System.out.print(n[i]);
        }
    }
}

package baekjoon.greedy;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Arrays;

/**
 * 백준 1715 (greedy 활용 최소 힙)
 * 카드 묶기
 */
public class P1715 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int data = scanner.nextInt();
            pq.add(data);
        }

        int data1 = 0;
        int data2 = 0;
        int sum = 0;
        while (pq.size() != 1) {
            data1 = pq.remove();
            data2 = pq.remove();
            sum += data1 + data2;
            pq.add(data1 + data2);
        }
        System.out.println(sum);
    }
}

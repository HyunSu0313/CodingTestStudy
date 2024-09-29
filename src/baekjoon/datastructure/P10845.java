package baekjoon.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P10845 {

    public static void main(String[] args) throws IOException {
        Deque<Integer> deque = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            if (s.equals("push")) {
                int number = Integer.parseInt(st.nextToken());
                deque.offer(number);
            } else if (s.equals("pop")) {
                if (!deque.isEmpty()) {
                    System.out.println(deque.poll());
                } else {
                    System.out.println(-1);
                }
            } else if (s.equals("size")) {
                System.out.println(deque.size());
            } else if (s.equals("empty")) {
                if (!deque.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                }
            } else if (s.equals("front")) {
                if (!deque.isEmpty()) {
                    System.out.println(deque.peek());
                } else {
                    System.out.println(-1);
                }
            } else {
                if (!deque.isEmpty()) {
                    System.out.println(deque.peekLast());
                } else {
                    System.out.println(-1);
                }
            }
        }
    }
}

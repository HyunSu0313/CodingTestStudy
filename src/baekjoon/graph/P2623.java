package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2623 위상정렬
 * 음악프로그램
 */
public class P2623 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            A.add(new ArrayList<>());
        }

        int[] indegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] order = new int[n];

            for (int j = 0; j < n; j++) {
                order[j] = Integer.parseInt(st.nextToken());
                if (j > 0) {
                    A.get(order[j - 1]).add(order[j]);
                    indegree[order[j]]++;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();

        while (!queue.isEmpty()) {
            int now = queue.poll();
            answer.add(now);
            for (int next : A.get(now)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        if (answer.size() == N) {
            for (Integer integer : answer) {
                System.out.println(integer);
            }
        } else {
            System.out.println(0);
        }
    }
}

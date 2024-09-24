package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2056 위상정렬
 * 작업 시간 계산
 */
public class P2056 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[N + 1];
        int[] time = new int[N + 1];
        int[] result = new int[N + 1];

        // 입력 처리
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int numPrecedingTasks = Integer.parseInt(st.nextToken());

            for (int j = 0; j < numPrecedingTasks; j++) {
                int precedingTask = Integer.parseInt(st.nextToken());
                graph.get(precedingTask).add(i);
                indegree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                result[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            int currentTask = queue.poll();

            for (int nextTask : graph.get(currentTask)) {
                result[nextTask] = Math.max(result[nextTask], result[currentTask] + time[nextTask]);

                indegree[nextTask]--;

                if (indegree[nextTask] == 0) {
                    queue.offer(nextTask);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, result[i]);
        }

        System.out.println(answer);
    }
}

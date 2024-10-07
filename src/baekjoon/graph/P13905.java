package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P13905 {
    static ArrayList<Edge>[] A;
    static int N, M;
    static int s, e;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        int maxWeight = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            A[h1].add(new Edge(h2, k));
            A[h2].add(new Edge(h1, k));
            maxWeight = Math.max(maxWeight, k);
        }

        System.out.println(binarySearch(1, maxWeight));
    }

    private static int binarySearch(int left, int right) {
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (bfs(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }

    private static boolean bfs(int limit) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == e) {
                return true;
            }

            for (Edge edge : A[current]) {
                if (!visited[edge.to] && edge.weight >= limit) {
                    visited[edge.to] = true;
                    queue.add(edge.to);
                }
            }
        }

        return false;
    }

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}

package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 14938 Dijkstra
 * 서강그라운드
 */
public class P14938 {
    static ArrayList<Edge>[] A;
    static int[] item;
    static int[] distance;
    static boolean[] visited;
    static int N, M, R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 지역의 개수 N, 수색 범위 M, 길의 개수 R 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 각 지역에 있는 아이템 수 입력
        item = new int[N + 1];
        A = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        // 길 정보 입력
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            A[S].add(new Edge(V, E));
            A[V].add(new Edge(S, E));  // 양방향 길이므로 반대쪽도 추가
        }

        // 각 지역에서 최대 아이템을 얻을 수 있는 경우 계산
        int maxItems = 0;
        for (int i = 1; i <= N; i++) {
            maxItems = Math.max(maxItems, Dijkstra(i));
        }

        // 결과 출력
        System.out.println(maxItems);
    }

    // 다익스트라 알고리즘을 사용해 특정 시작점에서 얻을 수 있는 최대 아이템 수 계산
    private static int Dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        distance = new int[N + 1];
        visited = new boolean[N + 1];

        // 초기 거리값을 무한대로 설정
        for (int i = 1; i <= N; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentIndex = current.index;

            if (visited[currentIndex]) continue;
            visited[currentIndex] = true;

            for (Edge next : A[currentIndex]) {
                if (distance[next.index] > distance[currentIndex] + next.value) {
                    distance[next.index] = distance[currentIndex] + next.value;
                    pq.add(new Edge(next.index, distance[next.index]));
                }
            }
        }

        // 수색 범위 내에서 얻을 수 있는 아이템 계산
        int totalItems = 0;
        for (int i = 1; i <= N; i++) {
            if (distance[i] <= M) {
                totalItems += item[i];
            }
        }

        return totalItems;
    }

    // Edge 클래스 정의
    static class Edge implements Comparable<Edge> {
        int index;
        int value;

        public Edge(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Edge o) {
            return this.value - o.value;
        }
    }
}
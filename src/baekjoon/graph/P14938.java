package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * ���� 14938 Dijkstra
 * �����׶���
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

        // ������ ���� N, ���� ���� M, ���� ���� R �Է�
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // �� ������ �ִ� ������ �� �Է�
        item = new int[N + 1];
        A = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        // �� ���� �Է�
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            A[S].add(new Edge(V, E));
            A[V].add(new Edge(S, E));  // ����� ���̹Ƿ� �ݴ��ʵ� �߰�
        }

        // �� �������� �ִ� �������� ���� �� �ִ� ��� ���
        int maxItems = 0;
        for (int i = 1; i <= N; i++) {
            maxItems = Math.max(maxItems, Dijkstra(i));
        }

        // ��� ���
        System.out.println(maxItems);
    }

    // ���ͽ�Ʈ�� �˰����� ����� Ư�� ���������� ���� �� �ִ� �ִ� ������ �� ���
    private static int Dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        distance = new int[N + 1];
        visited = new boolean[N + 1];

        // �ʱ� �Ÿ����� ���Ѵ�� ����
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

        // ���� ���� ������ ���� �� �ִ� ������ ���
        int totalItems = 0;
        for (int i = 1; i <= N; i++) {
            if (distance[i] <= M) {
                totalItems += item[i];
            }
        }

        return totalItems;
    }

    // Edge Ŭ���� ����
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
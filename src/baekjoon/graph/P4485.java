package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 4485 Dijkstra
 * 젤다
 */
public class P4485 {
    static int[][] distance;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int N;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 1;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) break;

            distance = new int[N][N];
            visited = new boolean[N][N];
            matrix = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Dijkstra(0, 0);

            System.out.println("Problem " + count + ": " + distance[N - 1][N - 1]);
            count++;
        }
    }

    private static void Dijkstra(int startX, int startY) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(startX, startY, matrix[startX][startY])); // 시작점 추가
        distance[startX][startY] = matrix[startX][startY];

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            int nowX = now.x;
            int nowY = now.y;

            if (visited[nowX][nowY]) continue;
            visited[nowX][nowY] = true;

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
                    if (!visited[nextX][nextY]) {
                        int newDist = distance[nowX][nowY] + matrix[nextX][nextY];
                        if (newDist < distance[nextX][nextY]) {
                            distance[nextX][nextY] = newDist;
                            pq.offer(new Edge(nextX, nextY, newDist));
                        }
                    }
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int x, y, cost;

        public Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}

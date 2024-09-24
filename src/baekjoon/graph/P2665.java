package baekjoon.graph;

import java.util.PriorityQueue;
import java.util.Scanner;

public class P2665 {
    static int N;
    static int[][] maze;
    static int[][] dist;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        N = sc.nextInt();
        maze = new int[N][N];
        dist = new int[N][N];

        // 미로 정보 입력받기
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < N; j++) {
                maze[i][j] = line.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;  // 초기화: 매우 큰 값으로 설정
            }
        }

        // 다익스트라 알고리즘 수행
        Dijkstra();

        // 출력: 끝 방까지 가는 최소 비용
        System.out.println(dist[N - 1][N - 1]);
    }

    // 다익스트라 알고리즘을 이용한 최단 경로 탐색
    private static void Dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 시작점 설정
        pq.add(new Node(0, 0, 0));
        dist[0][0] = 0;

        // 우선순위 큐를 이용해 최단 경로 탐색
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // 현재 위치에서 상하좌우로 이동 가능한 좌표 탐색
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                // 좌표가 유효한지 확인
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    int newCost = dist[current.x][current.y];

                    // 검은 방일 경우 비용 1 증가
                    if (maze[nx][ny] == 0) {
                        newCost++;
                    }

                    // 현재 경로가 기존 경로보다 더 적은 비용일 경우 갱신
                    if (newCost < dist[nx][ny]) {
                        dist[nx][ny] = newCost;
                        pq.add(new Node(nx, ny, newCost));
                    }
                }
            }
        }
    }

    // 노드 정보: 위치 (x, y)와 해당 위치까지의 비용 (cost)
    static class Node implements Comparable<Node> {
        int x, y, cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        // 비용 기준으로 오름차순 정렬
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}


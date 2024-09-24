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

        // �Է� �ޱ�
        N = sc.nextInt();
        maze = new int[N][N];
        dist = new int[N][N];

        // �̷� ���� �Է¹ޱ�
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < N; j++) {
                maze[i][j] = line.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;  // �ʱ�ȭ: �ſ� ū ������ ����
            }
        }

        // ���ͽ�Ʈ�� �˰��� ����
        Dijkstra();

        // ���: �� ����� ���� �ּ� ���
        System.out.println(dist[N - 1][N - 1]);
    }

    // ���ͽ�Ʈ�� �˰����� �̿��� �ִ� ��� Ž��
    private static void Dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // ������ ����
        pq.add(new Node(0, 0, 0));
        dist[0][0] = 0;

        // �켱���� ť�� �̿��� �ִ� ��� Ž��
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // ���� ��ġ���� �����¿�� �̵� ������ ��ǥ Ž��
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                // ��ǥ�� ��ȿ���� Ȯ��
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    int newCost = dist[current.x][current.y];

                    // ���� ���� ��� ��� 1 ����
                    if (maze[nx][ny] == 0) {
                        newCost++;
                    }

                    // ���� ��ΰ� ���� ��κ��� �� ���� ����� ��� ����
                    if (newCost < dist[nx][ny]) {
                        dist[nx][ny] = newCost;
                        pq.add(new Node(nx, ny, newCost));
                    }
                }
            }
        }
    }

    // ��� ����: ��ġ (x, y)�� �ش� ��ġ������ ��� (cost)
    static class Node implements Comparable<Node> {
        int x, y, cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        // ��� �������� �������� ����
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}


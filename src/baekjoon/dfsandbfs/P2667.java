package baekjoon.dfsandbfs;

import java.io.*;
import java.util.*;

/**
 * 백준 2667 (DFS, BFS 활용)
 * 단지 번호 붙이기
 */
public class P2667 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int[][] A;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        A = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            for (int j = 1; j < N + 1; j++) {
                A[i][j] = Integer.parseInt(s.substring(j - 1, j));
            }
        }

        int count = 0;
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (!visited[i][j] && A[i][j] != 0) {
                    count++;
                    list.add(DFS(i, j));
//                    list.add(DFS(i, j));
                }
            }
        }

        int[] result = new int[list.size()];

        System.out.println(count);

        for(int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }

        Arrays.sort(result);

        for (int j : result) {
            System.out.println(j);
        }

    }

    static int DFS(int i, int j) {
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[] {i, j});
        visited[i][j] = true;
        int count = 1;

        while (!stack.isEmpty()) {
            int[] now = stack.pop();
            for (int k = 0; k < 4; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                if (x > 0 && y > 0 && x < N + 1 && y < N + 1) {
                    if (A[x][y] != 0 && !visited[x][y]) {
                        count++;
                        visited[x][y] = true;
                        stack.push(new int[] {x, y});
                    }
                }
            }
        }

        return count;
    }

    static int BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        visited[i][j] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int k = 0; k < 4; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                if (x > 0 && y > 0 && x < N + 1 && y < N + 1) {
                    if (A[x][y] != 0 && !visited[x][y]) {
                        count++;
                        visited[x][y] = true;
                        queue.add(new int[] {x, y});
                    }
                }
            }
        }

        return count;
    }
}

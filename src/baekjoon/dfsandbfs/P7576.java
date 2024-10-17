package baekjoon.dfsandbfs;

import java.io.*;
import java.util.*;

public class P7576 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int N, M;
    static int[][] box;
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        box = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        System.out.println(BFS());
    }

    public static int BFS() {
        int days = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean ripened = false;

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if (nx >= 0 && ny >= 0 && nx < N && ny < M && box[nx][ny] == 0) {
                        box[nx][ny] = 1;
                        queue.add(new int[]{nx, ny});
                        ripened = true;
                    }
                }
            }

            if (ripened) {
                days++;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) {
                    return -1;
                }
            }
        }

        return days;
    }
}

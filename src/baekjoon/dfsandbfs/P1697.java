package baekjoon.dfsandbfs;

import java.util.*;
import java.io.*;

public class P1697 {
    static int[] dx = {-1, 1};
    static boolean[] visited = new boolean[100001];
    static int[] A = new int[100001];
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        System.out.println(BFS(N));
    }

    static int BFS(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visited[i] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            int time = A[now];
            if (now == M) break;

            for (int k = 0; k < 3; k++) {
                int x = 0;
                if (k == 2) {
                    x = 2 * now;
                } else {
                    x = now + dx[k];
                }

                if (x >= 0 && x < A.length) {
                    if (!visited[x]) {
                        visited[x] = true;
                        A[x] = time + 1;
                        queue.add(x);
                    }
                }
            }
        }

        return A[M];
    }
}

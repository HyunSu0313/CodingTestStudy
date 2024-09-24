package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 2637 위상정렬
 * 장난감 조립
 */
public class P2637 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Data>> A = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            A.add(new ArrayList<>());
        }

        int[] indegree = new int[N + 1];
        int[][] dp = new int[N + 1][N + 1];
        boolean[] basic = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            A.get(Y).add(new Data(X, K));
            indegree[X]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                basic[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (Data next : A.get(now)) {
                int nextPart = next.index;
                int needCount = next.count;

                if (basic[now]) {
                    dp[nextPart][now] += needCount;
                } else {
                    for (int i = 1; i <= N; i++) {
                        dp[nextPart][i] += dp[now][i] * needCount;
                    }
                }

                indegree[nextPart]--;
                if (indegree[nextPart] == 0) {
                    queue.offer(nextPart);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (basic[i]) {
                System.out.println(i + " " + dp[N][i]);
            }
        }
    }

    static class Data {
        int index;
        int count;

        public Data(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }
}

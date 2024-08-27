package baekjoon.dfsandbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16953 {
    static long N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());

        System.out.println(BFS(N));
    }

    static int BFS(long i) {
        Queue<Long> queue = new LinkedList<>();
        queue.add(i);

        int count = 1;  // 초기 연산 횟수는 1 (N을 포함)

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                long now = queue.poll();

                if (now == M) return count;

                long next1 = 2 * now;
                long next2 = 10 * now + 1;

                if (next1 <= M) {
                    queue.add(next1);
                }
                if (next2 <= M) {
                    queue.add(next2);
                }
            }
            count++;
        }

        return -1;
    }
}

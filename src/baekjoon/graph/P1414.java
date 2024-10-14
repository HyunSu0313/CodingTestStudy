package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1414 최소신장트리(MST)
 * 불우이웃돕기
 */
public class P1414 {

    static int[] A;
    static int N, sum;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] c = st.nextToken().toCharArray();

            for (int j = 0; j < N; j++) {
                int temp = 0;
                if (c[j] >= 'a' && c[j] <= 'z')
                    temp = c[j] - 'a' + 1;
                else if (c[j] >= 'A' && c[j] <= 'Z')
                    temp = c[j] - 'A' + 27;
                sum = sum + temp;
                if (i != j && temp != 0) pq.add(new Edge(i, j, temp));
            }
        }

        A = new int[N];
        for (int i = 0; i < A.length; i++) A[i] = i;
        int useEdge = 0;
        int result = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (Find(now.start) != Find(now.end)){
                Union(now.start, now.end);
                result = result + now.value;
                useEdge++;
            }
        }

        if (useEdge == N - 1) System.out.println(sum - result);
        else System.out.println(-1);
    }

    public static void Union(int i, int j) {
        i = Find(i);
        j = Find(j);

        if (i != j) A[j] = i;
    }

    public static int Find(int i) {
        if (i == A[i]) return i;
        else return A[i] = Find(A[i]);
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int value;

        Edge(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
        @Override
        public int compareTo(Edge o) {
            return this.value - o.value;
        }
    }
}

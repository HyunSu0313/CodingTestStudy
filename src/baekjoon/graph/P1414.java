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

    static int[] parent;
    static int N, sum;
    static PriorityQueue<Edge> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] c = st.nextToken().toCharArray();

            for (int j = 0; j < N; j++) {
                int temp = 0;
                if (c[j] >= 'a' && c[j] <= 'z')
                    temp = c[j] - 'a' + 1;
                else if (c[j] >= 'A' && c[j] <= 'Z')
                    temp = c[j] - 'A' + 27;
                sum = sum + temp; // 총 랜선의 길의 저장
                if (i != j && temp != 0)queue.add(new Edge(i, j, temp));
            }
        }

        parent = new int[N];
        for (int i = 0; i < parent.length; i++) parent[i] = i;
        int useEdge = 0;
        int result = 0;

        while (!queue.isEmpty()) {
            Edge now = queue.poll();
            if (find(now.s) != find(now.e)){
                union(now.s, now.e);
                result = result + now.v;
                useEdge++;
            }
        }

        if (useEdge == N - 1) System.out.println(sum - result);
        else System.out.println(-1);
    }

    public static void union(int a, int b) {
        a = find(a); b = find(b);
        if (a != b) parent[b] = a;
    }

    public static int find(int a) {
        if (a == parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }

    static class Edge implements Comparable<Edge> {
        int s, e, v;
        Edge(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
        @Override
        public int compareTo(Edge o) {
            return this.v - o.v;
        }
    }
}

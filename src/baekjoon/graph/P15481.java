package baekjoon.graph;

import java.io.*;
import java.util.*;

public class P15481 {
    static int[] A, depth;
    static long mstWeight;
    static long[] result;
    static ArrayList<pNode>[] tree;
    static ArrayList<pNode> nodes;
    static int[][] parent, maxEdge;
    static int LOG = 18;
    static boolean[] inMST;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList<>();
        result = new long[M];
        tree = new ArrayList[N + 1];
        inMST = new boolean[M];
        A = new int[N + 1];
        depth = new int[N + 1];
        parent = new int[N + 1][LOG];
        maxEdge = new int[N + 1][LOG];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            nodes.add(new pNode(u, v, w, i));
        }

        Collections.sort(nodes);

        Kruskal(N);

        DFS(1, 0, 0);
        preprocessLCA(N);

        for (pNode edge : nodes) {
            if (inMST[edge.index]) {
                result[edge.index] = mstWeight;
            } else {
                result[edge.index] = mstWeight - findMaxEdge(edge.start, edge.end) + edge.value;
            }
        }

        for (long res : result) {
            System.out.println(res);
        }
    }

    private static void Kruskal(int N) {
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = i;
        }

        mstWeight = 0;

        for (pNode pNode : nodes) {
            if (Find(pNode.start) != Find(pNode.end)) {
                Union(pNode.start, pNode.end);
                mstWeight += pNode.value;
                inMST[pNode.index] = true;
                tree[pNode.start].add(new pNode(pNode.start, pNode.end, pNode.value, pNode.index));
                tree[pNode.end].add(new pNode(pNode.end, pNode.start, pNode.value, pNode.index));
            }
        }
    }

    private static void DFS(int current, int parentNode, long weight) {
        depth[current] = depth[parentNode] + 1;
        parent[current][0] = parentNode;
        maxEdge[current][0] = (int) weight;

        for (pNode next : tree[current]) {
            if (next.end != parentNode) {
                DFS(next.end, current, next.value);
            }
        }
    }

    private static void preprocessLCA(int N) {
        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= N; i++) {
                if (parent[i][j - 1] != 0) {
                    parent[i][j] = parent[parent[i][j - 1]][j - 1];
                    maxEdge[i][j] = Math.max(maxEdge[i][j - 1], maxEdge[parent[i][j - 1]][j - 1]);
                }
            }
        }
    }

    private static long findMaxEdge(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        long maxWeight = 0;

        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                maxWeight = Math.max(maxWeight, maxEdge[u][i]);
                u = parent[u][i];
            }
        }

        if (u == v) {
            return maxWeight;
        }

        for (int i = LOG - 1; i >= 0; i--) {
            if (parent[u][i] != parent[v][i]) {
                maxWeight = Math.max(maxWeight, Math.max(maxEdge[u][i], maxEdge[v][i]));
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        return Math.max(maxWeight, Math.max(maxEdge[u][0], maxEdge[v][0]));
    }

    private static int Find(int i) {
        if (A[i] == i) return i;
        else return A[i] = Find(A[i]);
    }

    private static void Union(int i, int j) {
        i = Find(i);
        j = Find(j);

        if (i != j) {
            A[j] = i;
        }
    }

    static class pNode implements Comparable<pNode> {
        int start;
        int end;
        long value;
        int index;

        public pNode(int start, int end, long value, int index) {
            this.start = start;
            this.end = end;
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(pNode o) {
            return Long.compare(this.value, o.value);
        }
    }
}

package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P4792 {
    static int[] parent;
    static ArrayList<Edge> blueEdges, redEdges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0 && k == 0) break;

            // √ ±‚»≠
            blueEdges = new ArrayList<>();
            redEdges = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                String color = st.nextToken();
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                if (color.equals("B")) {
                    blueEdges.add(new Edge(from, to, 1));
                } else {
                    redEdges.add(new Edge(from, to, 0));
                }
            }

            if (canConstructTree(n, k)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    private static boolean canConstructTree(int n, int k) {
        int maxBlueEdges = kruskal(n, true);
        int minBlueEdges = kruskal(n, false);

        return minBlueEdges <= k && k <= maxBlueEdges;
    }

    private static int kruskal(int n, boolean useBlueFirst) {
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        ArrayList<Edge> edges = new ArrayList<>();

        if (useBlueFirst) {
            edges.addAll(blueEdges);
            edges.addAll(redEdges);
        } else {
            edges.addAll(redEdges);
            edges.addAll(blueEdges);
        }

        int edgeCount = 0;
        int blueEdgeCount = 0;

        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                edgeCount++;
                if (edge.color == 1) {
                    blueEdgeCount++;
                }
            }

            if (edgeCount == n - 1) break;
        }

        return blueEdgeCount;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
            return true;
        }

        return false;
    }

    static class Edge {
        int from;
        int to;
        int color;

        public Edge(int from, int to, int color) {
            this.from = from;
            this.to = to;
            this.color = color;
        }
    }
}

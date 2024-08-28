package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P14621 {
    static int[] A;
    static String[] S;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
    }

    static void Kruskal(int i) {

    }

    static void Union(int i, int j) {
        i = Find(i);
        j = Find(j);

        if (i != j) {
            A[j] = i;
        }
    }

    static int Find(int i) {
        if (A[i] == i) return i;
        else return A[i] = Find(A[i]);
    }

    static class Node implements Comparable<Node> {
        int start;
        int end;
        int value;
        int s;

        public Node(int start, int end, int value, int s) {
            this.start = start;
            this.end = end;
            this.value = value;
            this.s = s;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
}

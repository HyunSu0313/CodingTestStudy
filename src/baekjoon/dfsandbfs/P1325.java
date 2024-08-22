package baekjoon.dfsandbfs;

import java.util.*;
import java.io.*;

public class P1325 {

    static ArrayList<Integer>[] A;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            A[b].add(a);
        }

        List<Node> countList = new ArrayList<>();

        for (int i = 1; i < N + 1; i++) {
            BFS(i);

        }

        Collections.sort(countList);

        int value = countList.get(0).value;

        for (Node node : countList) {
            if (node.value == value) {
                System.out.print(node.index + " ");
            } else break;
        }
    }

    static void DFS(int v) {
        if (visited[v]) {
            return;
        }

        visited[v] = true;

        for (int i : A[v]) {
            if (!visited[i]) {
                DFS(i);
            }
        }
    }

    static int BFS(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i : A[now]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

        int count = 0;
        for (int i = 1; i < visited.length; i++) {
            if (visited[i]) {count++;}
        }

        return count;
    }

    static class Node implements Comparable<Node> {
        int index;
        int value;

        public Node(int index, int value) {
            super();
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            if (this.value != o.value) return o.value - this.value;
            else return this.index - o.index;
        }
    }
}

package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P17472 {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] A;
    static int[][] map;
    static int N, M, sNum;
    static boolean[][] visited;
    static ArrayList<ArrayList<int[]>> sumlist;
    static ArrayList<int[]> mlist;
    static PriorityQueue<bEdge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sNum = 1;
        sumlist = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    BFS(i, j);
                    sNum++;
                    sumlist.add(mlist);
                }
            }
        }

        pq = new PriorityQueue<>();

        for (int i = 0; i < sumlist.size(); i++) {
            ArrayList<int[]> now = sumlist.get(i);

            for (int j = 0; j < now.size(); j++) {
                int r = now.get(j)[0];
                int c = now.get(j)[1];
                int now_S = map[r][c];

                for (int k = 0; k < 4; k++) {
                    int tempR = dr[k];
                    int tempC = dc[k];
                    int blength = 0;

                    while (r + tempR >= 0 && r + tempR < N && c + tempC >= 0 && c + tempC < M) {
                        if (map[r + tempR][c + tempC] == now_S) break;
                        else if (map[r + tempR][c + tempC] != 0) {
                            if (blength > 1) pq.add(new bEdge(now_S, map[r + tempR][c + tempC], blength));
                            break;
                        } else {
                            blength++;
                        }

                        if (tempR < 0) tempR--;
                        else if (tempR > 0) tempR++;
                        else if (tempC < 0) tempC--;
                        else if (tempC > 0) tempC++;
                    }
                }
            }
        }

        A = new int[sNum];
        for (int i = 0; i < A.length; i++) {
            A[i] = i;
        }

        int useEdge = 0;
        int result = 0;
        while (!pq.isEmpty()) {
            bEdge now = pq.poll();
            if (Find(now.start) != Find(now.end)) {
                Union(now.start, now.end);
                result += now.value;
                useEdge++;
            }
        }

        if (useEdge == sNum - 2) System.out.println(result);
        else System.out.println("-1");
    }

    private static void Union(int i, int j) {
        i = A[i];
        j = A[j];

        if (i != j) A[j] = i;
    }

    private static int Find(int i) {
        if (A[i] == i) return i;
        else return A[i] = Find(A[i]);
    }

    private static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        mlist = new ArrayList<>();
        int[] start = {i, j};
        queue.add(start);
        mlist.add(start);
        visited[i][j] = true;
        map[i][j] = sNum;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];

            for (int k = 0; k < 4; k++) {
                int tempR = dr[k];
                int tempC = dc[k];

                while (r + tempR >= 0 && r + tempR < N && c + tempC >= 0 && c + tempC < M) {
                    if (!visited[r + tempR][c + tempC] && map[r + tempR][c + tempC] != 0) {
                        addNode(r + tempR, c + tempC, queue);
                    } else break;

                    if (tempR < 0) tempR--;
                    else if (tempR > 0) tempR++;
                    else if (tempC < 0) tempC--;
                    else if (tempC > 0) tempC++;
                }
            }
        }
    }

    private static void addNode(int i, int j, Queue<int[]> queue) {
        map[i][j] = sNum;
        visited[i][j] = true;
        int[] temp = {i, j};
        mlist.add(temp);
        queue.add(temp);
    }

    static class bEdge implements Comparable<bEdge> {
        int start;
        int end;
        int value;

        public bEdge(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(bEdge o) {
            return this.value - o.value;
        }
    }
}

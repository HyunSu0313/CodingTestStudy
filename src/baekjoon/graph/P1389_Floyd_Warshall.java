package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1389 (Floyd-Warshall 활용)
 */
public class P1389_Floyd_Warshall {
    static int[][] distance;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        distance = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i != j) distance[i][j] = 999999999;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            distance[u][v] = 1;
            distance[v][u] = 1;
        }

        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        int min = 999999999;
        int minIndex = 1;
        for (int i = 1; i < N + 1; i++) {
            int count = 0;
            for (int j = 1; j < N + 1; j++) {
                count += distance[i][j];
            }
            if (min > count) {
                min = count;
                minIndex = i;
            }
        }

        System.out.println(minIndex);
    }
}

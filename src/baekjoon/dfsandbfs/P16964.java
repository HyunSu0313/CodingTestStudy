package baekjoon.dfsandbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 백준 16964 (DFS 활용)
 * DFS 검증
 */
public class P16964 {
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int[] result;
    static int N;
    static int index = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        result = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            A[u].add(v);
            A[v].add(u);
        }

        int[] answer = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i < N + 1; i++) {
            answer[i] = Integer.parseInt(st.nextToken());
        }

        DFS(answer[1]);

        boolean check1 = true;
        for (int i = 1; i < N + 1; i++) {
            if (answer[i] != result[i]) {
                check1 = false;
                break;
            }
        }

        for (int i = 1; i < N + 1; i++) {
            Collections.sort(A[i]);
            Collections.reverse(A[i]);
        }

        result = new int[N + 1];
        visited = new boolean[N + 1];
        index = 1;
        DFS(answer[1]);

        boolean check2 = true;
        for (int i = 1; i < N + 1; i++) {
            if (answer[i] != result[i]) {
                check2 = false;
                break;
            }
        }

        if (check1 || check2) System.out.println("1");
        else System.out.println("0");
    }

    static void DFS(int i) {
        if (visited[i]) {
            return;
        }

        result[index] = i;
        visited[i] = true;

        for (int x : A[i]) {
            if (!visited[x]) {
                index++;
                DFS(x);
            }
        }
    }
}

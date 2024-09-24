package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 17352 Union-Find
 * 다리 연결
 */
public class P17352 {
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = i;
        }
        for (int i = 0; i < N - 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            Union(S, E);
        }

        int firstIsland = -1, secondIsland = -1;

        for (int i = 1; i <= N; i++) {
            if (Find(i) != Find(1)) {
                if (firstIsland == -1) {
                    firstIsland = i;
                } else {
                    secondIsland = i;
                    break;
                }
            }
        }

        if (secondIsland == -1) {
            secondIsland = firstIsland == 1 ? 2 : 1;
        }

        System.out.println(firstIsland + " " + secondIsland);
    }

    private static void Union(int i, int j) {
        i = Find(i);
        j = Find(j);

        if (i != j) {
            A[j] = i;
        }
    }

    private static int Find(int i) {
        if (A[i] == i) return i;
        return A[i] = Find(A[i]);
    }
}

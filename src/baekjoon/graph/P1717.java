package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1717 (Union-Find 기초)
 * Union 연산
 * 각 노드가 속한 집합을 1개로 합치는 연산
 * Find 연산
 * 특정 노드 a에 대하여 a가 속한 집합의 대표 노드 반환
 */
public class P1717 {
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        A = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            A[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int operator = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (operator == 0) {
                Union(a, b);
            } else {
                if (checkSame(a, b)) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }

    static void Union(int i, int j) {
        i = Find(i);
        j = Find(j);

        if (i != j) {
            A[j] = i;
        }
    }

    static int Find(int i) {
        if (i == A[i]) {
            return i;
        } else {
            return A[i] = Find(A[i]);
        }
    }

    static boolean checkSame(int i, int j) {
        i = Find(i);
        j = Find(j);
        return i == j;
    }
}

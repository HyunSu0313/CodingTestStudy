package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P20040 {
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        A = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            A[i] = i;
        }

        boolean check = true;
        int count = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            count++;
            if (Find(a) == Find(b)) {
                check = false;
                break;
            } else {
                Union(a, b);
            }
        }

        if (check) System.out.println(0);
        else System.out.println(count);
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
        else return A[i] = Find(A[i]);
    }
}

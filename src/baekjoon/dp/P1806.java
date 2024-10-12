package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1806 {
    static boolean[] visited = new boolean[100001];
    static long[] D = new long[100001];
    static int[] element;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int count = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (!visited[n]) {
                visited[n] = true;
                count++;
            }
        }

        element = new int[count];
        int index = 0;
        for (int i = 1; i < 100001; i++) {
            if (visited[i]) {
                element[index] = i;
                index++;
            }
        }

        Arrays.fill(D, 100000001);
        D[0] = 0;
        for (int i = element[0]; i < 100001; i++) {
            for (int j = 0; j < element.length; j++) {
                
            }
        }
    }
}

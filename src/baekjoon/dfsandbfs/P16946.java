package baekjoon.dfsandbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P16946 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] A;
    static int[][] group;
    static int[] groupSize;
    static int N, M, groupCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N + 1][M + 1];
        group = new int[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                A[i][j + 1] = s.charAt(j) - '0';
            }
        }

        groupCount = 0;
        groupSize = new int[N * M + 1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                if (A[i][j] == 0 && group[i][j] == 0) {
                    groupCount++;
                    groupSize[groupCount] = BFS(i, j, groupCount);
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                if (A[i][j] == 1) {
                    System.out.print(getAdjacentGroupSize(i, j));
                } else {
                    System.out.print(0);
                }
            }
            System.out.println();
        }
    }

    private static int BFS(int i, int j, int groupNumber) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        group[i][j] = groupNumber;
        int count = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int k = 0; k < 4; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];

                if (x > 0 && x <= N && y > 0 && y <= M) {
                    if (A[x][y] == 0 && group[x][y] == 0) {
                        group[x][y] = groupNumber;
                        queue.add(new int[]{x, y});
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private static int getAdjacentGroupSize(int i, int j) {
        Set<Integer> adjacentGroups = new HashSet<>();
        int sum = 1;

        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];

            if (x > 0 && x <= N && y > 0 && y <= M) {
                int groupNumber = group[x][y];
                if (groupNumber > 0 && !adjacentGroups.contains(groupNumber)) {
                    sum += groupSize[groupNumber];
                    adjacentGroups.add(groupNumber);
                }
            }
        }

        return sum % 10;
    }
}

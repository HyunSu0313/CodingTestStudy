package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 백준 4195 Union-Find
 * 친구 네트워크
 */
public class P4195 {

    static Map<String, Integer> map;
    static int[] parent;
    static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 개수

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int F = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            parent = new int[F * 2 + 1];
            size = new int[F * 2 + 1];

            int idx = 1;

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String id1 = st.nextToken();
                String id2 = st.nextToken();

                if (!map.containsKey(id1)) {
                    map.put(id1, idx);
                    parent[idx] = idx;
                    size[idx] = 1;
                    idx++;
                }

                if (!map.containsKey(id2)) {
                    map.put(id2, idx);
                    parent[idx] = idx;
                    size[idx] = 1;
                    idx++;
                }

                int root1 = find(map.get(id1));
                int root2 = find(map.get(id2));

                if (root1 != root2) {
                    union(root1, root2);
                }

                // 두 사람의 루트 노드를 찾아서 네트워크 크기를 출력
                sb.append(size[find(root1)]).append("\n");
            }
        }

        System.out.print(sb);
    }


    private static void union(int root1, int root2) {
        root1 = find(root1);
        root2 = find(root2);

        if (root1 != root2) {
            parent[root2] = root1;
            size[root1] += size[root2];
        }
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }
}

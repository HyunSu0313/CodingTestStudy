package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 1043 Union-Find
 * 거짓말쟁이
 */
public class P1043 {

    static int[] parent;
    static boolean[] truth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 사람의 수
        int M = Integer.parseInt(st.nextToken()); // 파티의 수

        parent = new int[N + 1];
        truth = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 진실을 아는 사람들 처리
        st = new StringTokenizer(br.readLine());
        int numTruth = Integer.parseInt(st.nextToken());
        if (numTruth > 0) {
            for (int i = 0; i < numTruth; i++) {
                int person = Integer.parseInt(st.nextToken());
                truth[person] = true; // 진실을 아는 사람 표시
            }
        }

        // 파티 정보를 저장할 리스트
        List<int[]> partyList = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int numPeople = Integer.parseInt(st.nextToken());
            int[] party = new int[numPeople];

            for (int j = 0; j < numPeople; j++) {
                party[j] = Integer.parseInt(st.nextToken());
            }

            partyList.add(party);

            // 파티에 참여한 사람들끼리 연결 (Union)
            for (int j = 1; j < numPeople; j++) {
                union(party[0], party[j]);
            }
        }

        // 진실을 아는 사람과 연결된 모든 사람들을 처리
        for (int i = 1; i <= N; i++) {
            if (truth[i]) {
                truth[find(i)] = true; // 진실을 아는 사람과 연결된 사람은 모두 진실을 알게 됨
            }
        }

        int count = 0;

        // 각 파티가 과장된 이야기를 할 수 있는지 확인
        for (int[] party : partyList) {
            boolean canLie = true;

            // 파티에 참여한 사람 중에 진실을 아는 사람이 있는지 확인
            for (int person : party) {
                if (truth[find(person)]) {
                    canLie = false; // 진실을 아는 사람이 있으면 과장할 수 없음
                    break;
                }
            }

            if (canLie) {
                count++; // 과장된 이야기를 할 수 있는 파티 카운트
            }
        }

        System.out.println(count);
    }

    // Union-Find에서 Union 연산 (i와 j를 같은 그룹으로 연결)
    private static void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        if (rootI != rootJ) {
            parent[rootJ] = rootI;
        }
    }

    // Union-Find에서 Find 연산 (i의 루트 찾기)
    private static int find(int i) {
        if (parent[i] == i) {
            return i;
        } else {
            return parent[i] = find(parent[i]); // 경로 압축
        }
    }
}

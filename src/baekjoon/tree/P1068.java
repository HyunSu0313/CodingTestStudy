package baekjoon.tree;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 백준 1068 트리 기초
 * 리프 노드 갯수 구하기
 */
public class P1068 {
    static ArrayList<Integer>[] tree;
    static boolean[] visit;
    static int answer = 0;
    static int deleteNode=0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        tree = new ArrayList[N];
        visit = new boolean[N];
        int root = 0;
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            int p = sc.nextInt();
            if (p != -1) {
                tree[i].add(p);
                tree[p].add(i);
            } else
                root = i;
        }

        deleteNode = sc.nextInt();
        if (deleteNode == root)
            System.out.println(0);
        else {
            DFS(root);
            System.out.println(answer);
        }
    }

    static void DFS(int number) {
        visit[number] = true;
        int cNode = 0;
        for (int i : tree[number]) {
            if (!visit[i] && i != deleteNode) {
                cNode++;
                DFS(i);
            }
        }
        if (cNode == 0) {
            answer++;
        }
    }
}

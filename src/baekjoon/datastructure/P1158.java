package baekjoon.datastructure;

import java.util.ArrayList;
import java.util.Scanner;

public class P1158 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            A.add(i + 1);
        }

        int currentIndex = 0;
        System.out.print("<");
        for (int i = 0; i < N; i++) {
            int currentSize = A.size();
            currentIndex = (currentIndex + (K - 1)) % currentSize;
            if (i != N - 1) {
                System.out.print(A.remove(currentIndex) + ", ");
            } else {
                System.out.print(A.remove(currentIndex));
            }
        }
        System.out.println(">");
    }
}

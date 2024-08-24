package baekjoon.numbertheory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/**
 * 백준 1929 (에라토스테네스의 체)
 */
public class P1929 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] numbers = new int [end + 1];

        for (int i = 2; i < numbers.length; i++) {
            numbers[i] = i;
        }

        for (int i = 2; i <= Math.sqrt(end); i++) {
            if (numbers[i] == 0) continue;
            for (int j = i + i; j < numbers.length; j += i) {
                numbers[j] = 0;
            }
        }

        for (int i = start; i < numbers.length; i++) {
            if (numbers[i] != 0) System.out.println(numbers[i]);
        }
    }
}

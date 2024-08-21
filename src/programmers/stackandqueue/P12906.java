package programmers.stackandqueue;

import java.util.*;

/**
 * Programmers (스택 활용)
 */
public class P12906 {

    public int[] solution(int []arr) {
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < arr.length; i++) {
            if (stack.isEmpty()) stack.push(arr[i]);
            else {
                if (stack.peek() != arr[i]) stack.push(arr[i]);
            }
        }

        int[] result = new int[stack.size()];

        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop().intValue();
        }

        return result;
    }
}

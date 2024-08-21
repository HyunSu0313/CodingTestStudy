package programmers.stackandqueue;

import java.util.*;

/**
 * Programmers (큐 활용)
 */
public class P42586 {

    public int[] solution(int[] progresses, int[] speeds) {
        int[] days = new int[progresses.length];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < progresses.length; i++) {
            if ((100 - progresses[i]) % speeds[i] == 0) days[i] = (100 - progresses[i]) / speeds[i];
            else days[i] = (100 - progresses[i]) / speeds[i] + 1;
            queue.offer(days[i]);
        }

        int previousMax = 0;

        for (int i = 0; i < days.length; i++) {
            if (i == 0) {
                int n = queue.poll();
                previousMax = n;
                list.add(n);
            }
            else {
                if (previousMax > queue.peek()) {
                    queue.poll();
                    list.add(previousMax);
                } else {
                    int n = queue.poll();
                    previousMax = n;
                    list.add(n);
                }
            }
        }

        int n = 1;

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).intValue() != list.get(i).intValue()) n++;
        }

        int[] result = new int[n];
        int countIndex = 0;
        result[countIndex] = 1;

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).intValue() != list.get(i).intValue()) {
                countIndex++;
            }
            result[countIndex]++;
        }

        return result;
    }
}

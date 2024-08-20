package programmers.dfsandbfs;

/**
 * Programmers (DFS 활용)
 * 타겟 넘버
 */
class P43165 {
    int answer = 0;

    public int solution(int[] numbers, int target) {
        DFS(0, numbers, target, 0);
        return answer;
    }

    private void DFS(int depth, int[] numbers, int target, int sum) {
        if (depth == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }
        DFS(depth + 1, numbers, target, sum + numbers[depth]);
        DFS(depth + 1, numbers, target, sum - numbers[depth]);
    }
}

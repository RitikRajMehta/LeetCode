class Solution {
    public int minScore(int n, int[][] roads) {

        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            graph[road[0]].add(new int[]{road[1], road[2]});
            graph[road[1]].add(new int[]{road[0], road[2]});
        }

        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);
        visited[1] = true;

        int answer = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int city = queue.poll();

            for (int[] next : graph[city]) {
                answer = Math.min(answer, next[1]);

                if (!visited[next[0]]) {
                    visited[next[0]] = true;
                    queue.offer(next[0]);
                }
            }
        }

        return answer;
    }
}
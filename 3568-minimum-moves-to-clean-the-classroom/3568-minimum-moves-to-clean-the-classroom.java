class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int[][] id = new int[m][n];
        int sr = 0, sc = 0;
        int litter = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    sr = i;
                    sc = j;
                } else if (c == 'L') {
                    id[i][j] = litter++;
                }
            }
        }

        if (litter == 0)
            return 0;

        int fullMask = (1 << litter) - 1;

        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << litter];

        java.util.Queue<int[]> queue = new java.util.LinkedList<>();

        queue.offer(new int[]{sr, sc, energy, fullMask});
        visited[sr][sc][energy][fullMask] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] cur = queue.poll();

                int r = cur[0];
                int c = cur[1];
                int e = cur[2];
                int mask = cur[3];

                if (mask == 0)
                    return moves;
                if (e == 0)
                    continue;

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                        continue;

                    if (classroom[nr].charAt(nc) == 'X')
                        continue;

                    int newEnergy;
                    int newMask = mask;

                    if (classroom[nr].charAt(nc) == 'R')
                        newEnergy = energy;
                    else
                        newEnergy = e - 1;

                    if (classroom[nr].charAt(nc) == 'L') {
                        newMask &= ~(1 << id[nr][nc]);
                    }

                    if (!visited[nr][nc][newEnergy][newMask]) {
                        visited[nr][nc][newEnergy][newMask] = true;

                        queue.offer(
                            new int[]{nr, nc, newEnergy, newMask}
                        );
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}
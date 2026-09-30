class Solution {
    int[][] dp;
    int[] suffix;

    public int stoneGameII(int[] piles) {
        int n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return solve(0, 1);
    }

    private int solve(int i, int M) {
        if (i >= suffix.length - 1) {
            return 0;
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }
        if (i + 2 * M >= suffix.length - 1) {
            return dp[i][M] = suffix[i];
        }

        int best = 0;

        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);

            int alice = suffix[i]
                      - solve(i + X, nextM);

            best = Math.max(best, alice);
        }

        return dp[i][M] = best;
    }
}
class Solution {
    int[][] dp;
    int[] prefix;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        dp = new int[n][n];
        prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(0, n - 1);
    }

    private int solve(int l, int r) {
        if (l == r) {
            return 0;
        }

        if (dp[l][r] != 0) {
            return dp[l][r];
        }

        int ans = 0;

        for (int k = l; k < r; k++) {

            int leftSum = prefix[k + 1] - prefix[l];
            int rightSum = prefix[r + 1] - prefix[k + 1];

            if (leftSum < rightSum) {

                ans = Math.max(
                    ans,
                    leftSum + solve(l, k)
                );

            } else if (leftSum > rightSum) {

                ans = Math.max(
                    ans,
                    rightSum + solve(k + 1, r)
                );

            } else {

                ans = Math.max(
                    ans,
                    leftSum + solve(l, k)
                );

                ans = Math.max(
                    ans,
                    rightSum + solve(k + 1, r)
                );
            }
        }

        return dp[l][r] = ans;
    }
}
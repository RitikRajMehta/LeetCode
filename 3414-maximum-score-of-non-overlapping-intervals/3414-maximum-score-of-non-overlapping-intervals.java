import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1); 
            a[i][2] = intervals.get(i).get(2); 
            a[i][3] = i;                       
        }

        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0])
                return Integer.compare(x[0], y[0]);
            return Integer.compare(x[1], y[1]);
        });

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int lo = i + 1;
            int hi = n;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (a[mid][0] > a[i][1])
                    hi = mid;
                else
                    lo = mid + 1;
            }

            next[i] = lo;
        }

        long[][] dp = new long[n + 1][5];
        int[][] count = new int[n + 1][5];

        int[][][] ids = new int[n + 1][5][4];

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                dp[i][k] = dp[i + 1][k];
                count[i][k] = count[i + 1][k];

                for (int x = 0; x < count[i][k]; x++) {
                    ids[i][k][x] = ids[i + 1][k][x];
                }

                int j = next[i];

                long takeScore = a[i][2] + dp[j][k - 1];

                int[] candidate = new int[4];
                int c = count[j][k - 1];

                for (int x = 0; x < c; x++) {
                    candidate[x] = ids[j][k - 1][x];
                }

                candidate[c] = a[i][3];
                c++;

                Arrays.sort(candidate, 0, c);

                boolean take = false;

                if (takeScore > dp[i][k]) {
                    take = true;
                } else if (takeScore == dp[i][k]) {
                    
                    for (int x = 0; x < c; x++) {
                        if (candidate[x] < ids[i][k][x]) {
                            take = true;
                            break;
                        }

                        if (candidate[x] > ids[i][k][x]) {
                            break;
                        }
                    }
                }

                if (take) {
                    dp[i][k] = takeScore;
                    count[i][k] = c;

                    for (int x = 0; x < c; x++) {
                        ids[i][k][x] = candidate[x];
                    }
                }
            }
        }

        int[] answer = new int[count[0][4]];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = ids[0][4][i];
        }

        return answer;
    }
}
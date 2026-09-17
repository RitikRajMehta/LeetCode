import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = 1000000;

        int[] best = new int[n];
        Arrays.fill(best, INF);

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int prefix = 0;
        int minLen = INF;
        int ans = INF;

        for (int i = 0; i < n; i++) {
            prefix += arr[i];

            int need = prefix - target;

            if (map.containsKey(need)) {
                int start = map.get(need);
                int len = i - start;

                if (start >= 0 && best[start] != INF) {
                    ans = Math.min(ans, len + best[start]);
                }

                minLen = Math.min(minLen, len);
            }

            best[i] = minLen;
            map.put(prefix, i);
        }

        return ans == INF ? -1 : ans;
    }
}
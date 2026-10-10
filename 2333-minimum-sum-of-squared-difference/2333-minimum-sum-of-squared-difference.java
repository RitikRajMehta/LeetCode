class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        int max = 0;
        long sum = 0;
        long k = (long) k1 + k2;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
            max = Math.max(max, diff[i]);
        }

        if (sum <= k) return 0;

        int left = 0, right = max;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long needed = 0;

            for (int d : diff) {
                needed += Math.max(0, d - mid);
            }

            if (needed <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        long remaining = k;
        long ans = 0;

        for (int i = 0; i < n; i++) {
            remaining -= Math.max(0, diff[i] - left);
            diff[i] = Math.min(diff[i], left);
        }

        for (int i = 0; i < n && remaining > 0; i++) {
            if (diff[i] == left && diff[i] > 0) {
                diff[i]--;
                remaining--;
            }
        }

        for (int d : diff) {
            ans += (long) d * d;
        }

        return ans;
    }
}
class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        Integer[] index = new Integer[n];

        for (int i = 0; i < n; i++) {
            index[i] = i;
        }

        java.util.Arrays.sort(index, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] ans = new int[n];

        int i = 0;

        while (i < n) {
            int j = i + 1;

            while (j < n &&
                   nums[index[j]] - nums[index[j - 1]] <= limit) {
                j++;
            }

            Integer[] positions =
                java.util.Arrays.copyOfRange(index, i, j);

            java.util.Arrays.sort(positions);

            for (int k = i; k < j; k++) {
                ans[positions[k - i]] = nums[index[k]];
            }

            i = j;
        }

        return ans;
    }
}
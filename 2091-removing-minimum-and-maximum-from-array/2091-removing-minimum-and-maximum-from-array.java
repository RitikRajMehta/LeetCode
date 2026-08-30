class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int min = 0, max = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[min]) min = i;
            if (nums[i] > nums[max]) max = i;
        }

        int left = Math.min(min, max);
        int right = Math.max(min, max);

        int option1 = right + 1;

        int option2 = n - left;

        int option3 = (left + 1) + (n - right);

        return Math.min(option1, Math.min(option2, option3));
    }
}
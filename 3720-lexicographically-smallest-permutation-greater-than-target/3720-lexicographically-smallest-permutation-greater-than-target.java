class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();
        int[] cnt = new int[26];

        // Count characters in s
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        // temp = remaining characters while matching target
        int[] temp = cnt.clone();

        // Store the latest position where we can make target larger
        int best = -1;

        for (int i = 0; i < n; i++) {

            int x = target.charAt(i) - 'a';

            // Find a character greater than target[i]
            for (int c = x + 1; c < 26; c++) {
                if (temp[c] > 0) {
                    best = i;
                    break;
                }
            }

            // Cannot use target[i]
            if (temp[x] == 0) {
                break;
            }

            // Use target[i]
            temp[x]--;
        }

        // No possible position
        if (best == -1) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        // Use target's prefix
        for (int i = 0; i < best; i++) {
            ans.append(target.charAt(i));
            cnt[target.charAt(i) - 'a']--;
        }

        // Find smallest character > target[best]
        int x = target.charAt(best) - 'a';

        int bigger = x + 1;

        while (bigger < 26 && cnt[bigger] == 0) {
            bigger++;
        }

        // Put the smallest bigger character
        ans.append((char) ('a' + bigger));
        cnt[bigger]--;

        // Fill remaining positions in sorted order
        for (int c = 0; c < 26; c++) {
            while (cnt[c] > 0) {
                ans.append((char) ('a' + c));
                cnt[c]--;
            }
        }

        return ans.toString();
    }
}
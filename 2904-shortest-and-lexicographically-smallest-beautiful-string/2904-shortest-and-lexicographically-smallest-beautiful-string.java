class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int l = 0, ones = 0, best = -1, len = Integer.MAX_VALUE;

        for (int r = 0; r < s.length(); r++) {
            ones += s.charAt(r) - '0';

            while (ones > k)
                ones -= s.charAt(l++) - '0';

            while (ones == k && s.charAt(l) == '0')
                l++;

            if (ones == k) {
                int cur = r - l + 1;

                if (cur < len ||
                    (cur == len &&
                     s.substring(l, r + 1).compareTo(
                         s.substring(best, best + len)) < 0)) {
                    len = cur;
                    best = l;
                }
            }
        }

        return best == -1 ? "" : s.substring(best, best + len);
    }
}
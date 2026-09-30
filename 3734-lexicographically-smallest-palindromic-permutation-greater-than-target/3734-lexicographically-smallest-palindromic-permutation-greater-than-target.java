class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        String calendrix = s;

        int n = s.length();
        int half = n / 2;

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int odd = 0;
        int mid = -1;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                odd++;
                mid = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        for (int i = 0; i < 26; i++) {
            freq[i] /= 2;
        }

        char[] ans = new char[n];
        char[] t = target.toCharArray();

        int pos = 0;

        while (pos < half) {
            int c = t[pos] - 'a';

            if (freq[c] == 0) {
                break;
            }

            ans[pos] = t[pos];
            freq[c]--;
            pos++;
        }

        if (pos == half) {

            build(ans, freq, mid, half);

            if (new String(ans).compareTo(target) > 0) {
                return new String(ans);
            }
        }

        while (true) {

            if (pos < half) {

                int start = (t[pos] - 'a') + 1;

                for (int c = start; c < 26; c++) {

                    if (freq[c] == 0) {
                        continue;
                    }

                    ans[pos] = (char) ('a' + c);
                    freq[c]--;

                    int index = pos + 1;

                    for (int x = 0; x < 26; x++) {
                        while (freq[x] > 0) {
                            ans[index++] = (char) ('a' + x);
                            freq[x]--;
                        }
                    }

                    build(ans, freq, mid, half);

                    return new String(ans);
                }
            }

            if (pos == 0) {
                return "";
            }

            pos--;
            freq[t[pos] - 'a']++;
        }
    }

    private void build(char[] ans, int[] freq, int mid, int half) {

        int n = ans.length;

        if (n % 2 == 1) {
            ans[half] = (char) ('a' + mid);
        }

        for (int i = 0; i < half; i++) {
            ans[n - 1 - i] = ans[i];
        }
    }
}
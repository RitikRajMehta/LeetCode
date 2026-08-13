class Solution {

    int[] leftChar, rightChar, prefix, suffix, best;
    int n;

    public int[] longestRepeating(String s, String queryCharacters,
                                  int[] queryIndices) {

        n = s.length();
        int size = 4 * n;

        leftChar = new int[size];
        rightChar = new int[size];
        prefix = new int[size];
        suffix = new int[size];
        best = new int[size];

        build(1, 0, n - 1, s);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {
            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            update(1, 0, n - 1, index, ch);

            ans[i] = best[1];
        }

        return ans;
    }

    void build(int node, int l, int r, String s) {
        if (l == r) {
            int c = s.charAt(l);

            leftChar[node] = c;
            rightChar[node] = c;
            prefix[node] = 1;
            suffix[node] = 1;
            best[node] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, s);
        build(node * 2 + 1, mid + 1, r, s);

        merge(node, l, r);
    }

    void update(int node, int l, int r, int index, char ch) {
        if (l == r) {
            int c = ch;

            leftChar[node] = c;
            rightChar[node] = c;
            prefix[node] = 1;
            suffix[node] = 1;
            best[node] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, r, index, ch);
        }

        merge(node, l, r);
    }

    void merge(int node, int l, int r) {

        int left = node * 2;
        int right = node * 2 + 1;

        leftChar[node] = leftChar[left];
        rightChar[node] = rightChar[right];

        prefix[node] = prefix[left];
        suffix[node] = suffix[right];

        best[node] = Math.max(best[left], best[right]);

        int mid = (l + r) / 2;

        if (rightChar[left] == leftChar[right]) {

            best[node] = Math.max(
                best[node],
                suffix[left] + prefix[right]
            );

            int leftLength = mid - l + 1;
            int rightLength = r - mid;

            if (prefix[left] == leftLength) {
                prefix[node] = leftLength + prefix[right];
            }

            if (suffix[right] == rightLength) {
                suffix[node] = rightLength + suffix[left];
            }
        }
    }
}
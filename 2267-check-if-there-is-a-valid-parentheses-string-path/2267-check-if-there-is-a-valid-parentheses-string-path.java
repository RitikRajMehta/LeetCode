class Solution {
    int m, n;
    char[][] grid;
    boolean[][][] visited;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Path length must be even
        if ((m + n - 1) % 2 == 1) {
            return false;
        }

        // First must be '(' and last must be ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        this.grid = grid;
        visited = new boolean[m][n][m + n];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int r, int c, int balance) {

        if (visited[r][c][balance]) {
            return false;
        }

        visited[r][c][balance] = true;

        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        if (balance < 0) {
            return false;
        }

        int remaining = (m - 1 - r) + (n - 1 - c);

        if (balance > remaining) {
            return false;
        }

        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        if (r + 1 < m && dfs(r + 1, c, balance)) {
            return true;
        }

        if (c + 1 < n && dfs(r, c + 1, balance)) {
            return true;
        }

        return false;
    }
}
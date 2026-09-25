import java.util.*;

class Solution {
    TreeSet<String> set = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(set);
    }

    private void dfs(String exp) {
        int close = exp.indexOf('}');

        if (close == -1) {
            set.add(exp);
            return;
        }

        int open = exp.lastIndexOf('{', close);

        String left = exp.substring(0, open);
        String right = exp.substring(close + 1);

        String[] parts = exp.substring(open + 1, close).split(",");

        for (String part : parts) {
            dfs(left + part + right);
        }
    }
}
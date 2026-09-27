import java.util.*;

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } 
            else if (s.charAt(i) == ')') {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        StringBuilder ans = new StringBuilder();

        int i = 0;
        int direction = 1;

        while (i < n) {
            char c = s.charAt(i);

            if (c == '(' || c == ')') {
                i = pair[i];
                direction = -direction;
            } 
            else {
                ans.append(c);
            }

            i += direction;
        }

        return ans.toString();
    }
}
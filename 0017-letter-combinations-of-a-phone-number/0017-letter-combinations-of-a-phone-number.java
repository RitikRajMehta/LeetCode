import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits.length() == 0) {
            return result;
        }

        String[] phone = {
            "", "", "abc", "def", "ghi",
            "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        backtrack(digits, 0, "", result, phone);

        return result;
    }

    public void backtrack(String digits, int index, String current,
                           List<String> result, String[] phone) {

        if (index == digits.length()) {
            result.add(current);
            return;
        }

        String letters = phone[digits.charAt(index) - '0'];

        for (int i = 0; i < letters.length(); i++) {
            backtrack(
                digits,
                index + 1,
                current + letters.charAt(i),
                result,
                phone
            );
        }
    }
}
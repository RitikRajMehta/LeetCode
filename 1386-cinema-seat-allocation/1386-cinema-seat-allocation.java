import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.put(row,
                    map.getOrDefault(row, 0) | (1 << (col - 1)));
        }

        int answer = (n - map.size()) * 2;

        int left = 0b0000011110;

        int middle = 0b0001111000;

        int right = 0b0111100000;

        for (int seats : map.values()) {

            boolean canLeft = (seats & left) == 0;
            boolean canMiddle = (seats & middle) == 0;
            boolean canRight = (seats & right) == 0;

            if (canLeft && canRight) {
                answer += 2;
            } 
            else if (canLeft || canMiddle || canRight) {
                answer += 1;
            }
        }

        return answer;
    }
}
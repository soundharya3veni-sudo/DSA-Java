class Solution {
    public String findRollOut(String s, int[] roll) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int m = roll.length;

        int[] diff = new int[n + 1];

        for (int i = 0; i < m; i++) {
            diff[0]++;
            diff[roll[i]]--;
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            count += diff[i];
            int shift = count % 26;

            arr[i] = (char) ('a' + (arr[i] - 'a' + shift) % 26);
        }

        return new String(arr);
    }
}

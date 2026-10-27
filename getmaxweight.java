
class Solution {
    public int getMaxWeight(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];

        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            // Current character as a single character
            dp[i] = dp[i - 1] + 1;

            // Use the previous two characters as a pair
            if (i >= 2) {
                int cost = (s.charAt(i - 2) == s.charAt(i - 1)) ? 1 : 0;
                dp[i] = Math.max(dp[i], dp[i - 2] + 4 - cost);
            }
        }

        return dp[n];
    }
}

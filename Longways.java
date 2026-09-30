class Solution {
    public int ways(int x, int y) {
        final long MOD = 1000000007L;

        long[] dp = new long[y + 1];

        // Base case: ways(x, 0) = 1
        for (int j = 0; j <= y; j++) {
            dp[j] = 1;
        }

        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }

        return (int) dp[y];
    }
}

class Solution {
    public int minimumCost(int x, int s, int m, int l,
                           int cs, int cm, int cl) {

        int INF = 1000000000;

        int[] dp = new int[x + 1];

        for (int i = 1; i <= x; i++) {
            dp[i] = INF;
        }

        dp[0] = 0;

        for (int area = 0; area <= x; area++) {

            if (dp[area] == INF)
                continue;

            // Small pizza
            int next = Math.min(x, area + s);
            dp[next] = Math.min(dp[next], dp[area] + cs);

            // Medium pizza
            next = Math.min(x, area + m);
            dp[next] = Math.min(dp[next], dp[area] + cm);

            // Large pizza
            next = Math.min(x, area + l);
            dp[next] = Math.min(dp[next], dp[area] + cl);
        }

        return dp[x];
    }
}

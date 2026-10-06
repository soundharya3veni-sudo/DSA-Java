class Solution {
    int[][] dp;
    int n, m;

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int longIncPath(int[][] arr, int n, int m) {
        this.n = n;
        this.m = m;

        dp = new int[n][m];

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, dfs(i, j, arr));
            }
        }

        return ans;
    }

    int dfs(int r, int c, int[][] arr) {
        if (dp[r][c] != 0) {
            return dp[r][c];
        }

        int best = 1;

        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];

            if (nr >= 0 && nr < n &&
                nc >= 0 && nc < m &&
                arr[nr][nc] > arr[r][c]) {

                best = Math.max(best, 1 + dfs(nr, nc, arr));
            }
        }

        dp[r][c] = best;

        return best;
    }
}

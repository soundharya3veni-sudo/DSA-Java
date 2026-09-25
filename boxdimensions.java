import java.util.*;

class Solution {

    static class Box {
        long h, w, l;

        Box(long h, long w, long l) {
            this.h = h;
            this.w = w;
            this.l = l;

            // Keep base dimensions in a fixed order
            if (this.w < this.l) {
                long temp = this.w;
                this.w = this.l;
                this.l = temp;
            }
        }
    }

    public long maxHeight(int[] height, int[] width, int[] length) {

        int n = height.length;

        ArrayList<Box> boxes = new ArrayList<>();

        // Generate all 3 rotations
        for (int i = 0; i < n; i++) {

            // height = height[i]
            boxes.add(new Box(
                    height[i],
                    width[i],
                    length[i]
            ));

            // height = width[i]
            boxes.add(new Box(
                    width[i],
                    height[i],
                    length[i]
            ));

            // height = length[i]
            boxes.add(new Box(
                    length[i],
                    height[i],
                    width[i]
            ));
        }

        /*
         * Since the same box can be used multiple times,
         * we cannot simply use DP over the number of boxes.
         *
         * Instead, dp[i] = maximum height when the current
         * bottom box is boxes[i].
         *
         * We can choose another box whose base is strictly
         * smaller.
         *
         * Because dimensions strictly decrease, cycles are
         * impossible.
         */

        int m = boxes.size();

        long[] dp = new long[m];

        long answer = 0;

        for (int i = 0; i < m; i++) {
            answer = Math.max(answer, dfs(i, boxes, dp));
        }

        return answer;
    }

    static long dfs(int i, ArrayList<Box> boxes, long[] dp) {

        if (dp[i] != 0) {
            return dp[i];
        }

        Box current = boxes.get(i);

        long best = current.h;

        for (int j = 0; j < boxes.size(); j++) {

            Box next = boxes.get(j);

            // next box can be placed on current box
            if (next.w < current.w &&
                next.l < current.l) {

                best = Math.max(
                    best,
                    current.h + dfs(j, boxes, dp)
                );
            }
        }

        dp[i] = best;

        return best;
    }
}

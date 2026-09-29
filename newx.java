import java.util.*;

class Solution {
    public int minStepToReachTarget(int[] KnightPos, int[] TargetPos, int N) {
        
        // If already at target
        if (KnightPos[0] == TargetPos[0] &&
            KnightPos[1] == TargetPos[1]) {
            return 0;
        }

        // 8 possible knight moves
        int[] dx = {2, 2, -2, -2, 1, 1, -1, -1};
        int[] dy = {1, -1, 1, -1, 2, -2, 2, -2};

        boolean[][] visited = new boolean[N + 1][N + 1];

        // Queue: {row, col, moves}
        Queue<int[]> q = new LinkedList<>();

        int startX = KnightPos[0];
        int startY = KnightPos[1];

        q.offer(new int[]{startX, startY, 0});
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();

            int x = current[0];
            int y = current[1];
            int moves = current[2];

            for (int i = 0; i < 8; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                // Check board boundary
                if (newX >= 1 && newX <= N &&
                    newY >= 1 && newY <= N &&
                    !visited[newX][newY]) {

                    // Target reached
                    if (newX == TargetPos[0] &&
                        newY == TargetPos[1]) {
                        return moves + 1;
                    }

                    visited[newX][newY] = true;
                    q.offer(new int[]{newX, newY, moves + 1});
                }
            }
        }

        return -1;
    }
}

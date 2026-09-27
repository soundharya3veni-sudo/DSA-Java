import java.util.*;

class Solution {

    public int longestPath(String s, int[][] edges) {

        int n = s.length();

        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0] - 1;
            int v = e[1] - 1;

            graph[u].add(v);
            graph[v].add(u);
        }

        char[] color = s.toCharArray();

        // ------------------------------------------------
        // Build parent + traversal order
        // ------------------------------------------------

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int[] order = new int[n];
        int count = 0;

        order[count++] = 0;

        for (int i = 0; i < count; i++) {

            int u = order[i];

            for (int v : graph[u]) {

                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                order[count++] = v;
            }
        }

        // ------------------------------------------------
        // down[u]:
        // longest same-color path starting at u and
        // going into u's subtree.
        // ------------------------------------------------

        int[] down = new int[n];

        Arrays.fill(down, 1);

        for (int i = n - 1; i >= 0; i--) {

            int u = order[i];

            for (int v : graph[u]) {

                if (parent[v] != u) {
                    continue;
                }

                if (color[u] == color[v]) {

                    down[u] = Math.max(
                        down[u],
                        1 + down[v]
                    );
                }
            }
        }

        // ------------------------------------------------
        // up[u]:
        // longest same-color path starting at u and
        // going through u's parent side.
        // ------------------------------------------------

        int[] up = new int[n];

        Arrays.fill(up, 1);

        for (int i = 0; i < n; i++) {

            int u = order[i];

            int best1 = 0;
            int best2 = 0;

            int bestChild = -1;

            // Find two best same-color child branches
            for (int v : graph[u]) {

                if (parent[v] != u) {
                    continue;
                }

                if (color[v] != color[u]) {
                    continue;
                }

                int val = down[v];

                if (val > best1) {

                    best2 = best1;
                    best1 = val;
                    bestChild = v;

                } else if (val > best2) {

                    best2 = val;
                }
            }

            // Calculate up[] for children
            for (int v : graph[u]) {

                if (parent[v] != u) {
                    continue;
                }

                if (color[v] != color[u]) {

                    // Different color, so same-color
                    // path cannot cross u -> v.
                    up[v] = 1;

                } else {

                    int bestOther;

                    if (bestChild == v) {
                        bestOther = best2;
                    } else {
                        bestOther = best1;
                    }

                    up[v] = 1 + Math.max(
                        up[u],
                        bestOther
                    );
                }
            }
        }

        // ------------------------------------------------
        // Evaluate every node as the middle of the path.
        // ------------------------------------------------

        int answer = 1;

        for (int u = 0; u < n; u++) {

            /*
             * bestSame1 and bestSame2:
             * two longest same-color branches.
             */
            int bestSame1 = 0;
            int bestSame2 = 0;

            /*
             * bestOpposite:
             * longest opposite-color branch.
             */
            int bestOpposite = 0;

            for (int v : graph[u]) {

                int branch;

                // v is a child
                if (parent[v] == u) {
                    branch = down[v];
                }
                // v is the parent
                else {
                    branch = up[u];
                }

                if (color[v] == color[u]) {

                    // Same-color branch
                    if (branch > bestSame1) {

                        bestSame2 = bestSame1;
                        bestSame1 = branch;

                    } else if (branch > bestSame2) {

                        bestSame2 = branch;
                    }

                } else {

                    // Opposite-color branch
                    bestOpposite = Math.max(
                        bestOpposite,
                        branch
                    );
                }
            }

            // --------------------------------------------
            // Case 1:
            // Only one color
            // --------------------------------------------

            answer = Math.max(
                answer,
                1 + bestSame1
            );

            // --------------------------------------------
            // Case 2:
            // Two same-color branches
            // --------------------------------------------

            answer = Math.max(
                answer,
                1 + bestSame1 + bestSame2
            );

            // --------------------------------------------
            // Case 3:
            // One same-color branch + one opposite branch
            //
            // Example:
            //
            // R R R
            //     \
            //      B B
            // --------------------------------------------

            answer = Math.max(
                answer,
                1 + bestSame1 + bestOpposite
            );
        }

        return answer;
    }
}

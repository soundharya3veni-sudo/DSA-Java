class Solution {
    public int minimumSum(String s) {
        int n = s.length();
        int sum = 0;
        char prev = '?';

        for (int i = 0; i < n; i++) {
            int j = n - 1 - i;
            char left = s.charAt(i);
            char right = s.charAt(j);

            // Check if forming a palindrome is impossible
            if (i < j && left != '?' && right != '?' && left != right) {
                return -1;
            }

            // Resolve the character using its mirrored position
            char current = (left != '?') ? left : right;

            // Both characters are unknown, so skip this position
            if (current == '?') {
                continue;
            }

            // Add the minimum cost between consecutive fixed characters
            if (prev != '?') {
                sum += Math.abs(current - prev);
            }

            prev = current;
        }

        return sum;
    }
}

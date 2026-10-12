
class Solution {
    public int minSquares(int n, int m) {
        int count = 0;

        while (n > 0 && m > 0) {
            if (n > m) {
                count += n / m;
                n = n % m;
            } else {
                count += m / n;
                m = m % n;
            }
        }

        return count;
    }
}

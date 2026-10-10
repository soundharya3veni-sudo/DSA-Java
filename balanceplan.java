
class Solution {
    public boolean balancePan(int a, int b) {
        long n = b;

        while (n > 0) {
            long rem = n % a;

            if (rem == 0 || rem == 1) {
                n /= a;
            } else if (rem == a - 1) {
                n = n / a + 1;
            } else {
                return false;
            }
        }

        return true;
    }
}

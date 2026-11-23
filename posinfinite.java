
class Solution {
    public char posInfinite(String s, int pos) {

        long p = pos;
        long len = s.length();

        while (p > len) {

            long block = len;
            long step = 1;

            while (p > 2 * block + step) {
                block = 2 * block + step;
                step++;
            }

            if (p > block && p <= block + step) {
                return '$';
            }

            if (p > block + step) {
                p = 2 * block + step - p + 1;
            }

            len = block;
        }

        return s.charAt((int) p - 1);
    }
}

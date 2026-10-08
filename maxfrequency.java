import java.util.*;

class Solution {
    public int maxFrequency(int[] arr, int k) {
        Arrays.sort(arr);

        int left = 0;
        long sum = 0;
        int ans = 1;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            long cost = (long) arr[right] * (right - left + 1) - sum;

            while (cost > k) {
                sum -= arr[left];
                left++;

                cost = (long) arr[right] * (right - left + 1) - sum;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}

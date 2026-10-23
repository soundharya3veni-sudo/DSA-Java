
class Solution {
    static long pairs = 0;

    static long pairsViolatingBST(Node root) {
        java.util.ArrayList<Integer> arr = new java.util.ArrayList<>();
        inorder(root, arr);

        int n = arr.size();
        long count = 0;

        // Count inversions in inorder traversal
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = arr.get(i);
        }

        int[] temp = new int[n];
        return mergeSort(a, temp, 0, n - 1);
    }

    static void inorder(Node root, java.util.ArrayList<Integer> arr) {
        if (root == null) return;

        inorder(root.left, arr);
        arr.add(root.data);
        inorder(root.right, arr);
    }

    static long mergeSort(int[] a, int[] temp, int left, int right) {
        if (left >= right) return 0;

        int mid = left + (right - left) / 2;

        long count = mergeSort(a, temp, left, mid);
        count += mergeSort(a, temp, mid + 1, right);
        count += merge(a, temp, left, mid, right);

        return count;
    }

    static long merge(int[] a, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        long count = 0;

        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
                count += mid - i + 1;
            }
        }

        while (i <= mid) temp[k++] = a[i++];
        while (j <= right) temp[k++] = a[j++];

        for (i = left; i <= right; i++) {
            a[i] = temp[i];
        }

        return count;
    }
}

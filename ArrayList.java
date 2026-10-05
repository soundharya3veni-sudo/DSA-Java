import java.util.*;

class Solution {
    
    public ArrayList<ArrayList<Integer>> socialNetwork(int[] arr) {
        
        int n = arr.length + 1;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        // Start from every user except user 1
        for (int user = 2; user <= n; user++) {
            findFriends(user, user, 1, arr, ans);
        }
        
        return ans;
    }
    
    private void findFriends(int original, int current, int distance,
                             int[] arr,
                             ArrayList<ArrayList<Integer>> ans) {
        
        // User 1 has no friend
        if (current == 1) {
            return;
        }
        
        // arr[0] -> friend of user 2
        // arr[1] -> friend of user 3
        int friend = arr[current - 2];
        
        // Go to the next friend first
        findFriends(original, friend, distance + 1, arr, ans);
        
        // Add after recursion so farthest friend comes first
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(original);
        temp.add(friend);
        temp.add(distance);
        
        ans.add(temp);
    }
}

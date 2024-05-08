import java.util.*;
public class Assignment05 {
    // problem 1
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() < 3) return s.length();
    
        // HashMap to store the last occurrence of each character
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 2; // Start with 2 because we're looking for at most two distinct characters
        int left = 0;   // Left pointer for the sliding window
    
        for (int right = 0; right < s.length(); right++) {
            // If the map contains less than 3 characters, add the current character
            map.put(s.charAt(right), right);
    
            // If the map's size exceeds 2, it means we have more than two distinct characters
            if (map.size() == 3) {
                // Remove the character with the leftmost position in the window
                int delIdx = Collections.min(map.values());
                map.remove(s.charAt(delIdx));
                // Move left pointer of the window
                left = delIdx + 1;
            }
    
            // Calculate the maximum length
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    //problem 2
    public static List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    private static List<String> helper(int n, int m) {
        // Base case for recursion: if no length is left, return a list with an empty string
        if (n == 0) return Arrays.asList("");
        // If only one digit can be placed, return the symmetric single-digit strobogrammatic numbers
        if (n == 1) return Arrays.asList("0", "1", "8");

        // Recurse with length decreased by 2, to build from the inner part to the outer part
        List<String> list = helper(n - 2, m);
        List<String> res = new ArrayList<>();

        // For each string from the recursive call, enclose it with strobogrammatic pairs
        for (String s : list) {
            if (n != m) res.add("0" + s + "0"); // Add "0" to both sides, only if it is not the outermost level
            res.add("1" + s + "1"); // Add "1" to both sides
            res.add("8" + s + "8"); // Add "8" to both sides
            res.add("6" + s + "9"); // Add "6" on the left and "9" on the right
            res.add("9" + s + "6"); // Add "9" on the left and "6" on the right
        }
        return res;
    }

    public static void main(String[] args) {
        //test for problem 1
        // String s1 = "eceba";
        // String s2 = "ccaabbb";
        System.out.println(lengthOfLongestSubstringTwoDistinct("eceba"));// output: shoule be 3
        System.out.println(lengthOfLongestSubstringTwoDistinct("ccaabbb"));// output: shoule be 5


        //test for problem 2
        System.out.println(findStrobogrammatic(2));// output: should be [“11”, “69”, ”88”, “96”]
        System.out.println(findStrobogrammatic(1));// output: should be [“0”,”1”,”8”]
        
    }

}

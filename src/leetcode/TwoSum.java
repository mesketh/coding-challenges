package leetcode;

import java.util.HashMap;

class TwoSum {

  public static int[] twoSum(int[] nums, int target) {

    int[] factorIdx = {-1, -1};
    int start = 0;
    boolean found = false;
    while (!found && start < nums.length - 1) {
      int i = start + 1;
      do {
        int factorSum = nums[start] + nums[i];
        if (factorSum == target) {
          factorIdx = new int[]{start, i};
          found = true;
          break;
        }
        i++;
      } while (!found && i < nums.length);
      start++;
    }

    return factorIdx;
  }


  // Other solution which was O(n)/O(n) time/space. I had started to think about the differences
  // but, got caught up coding rather than thinking. I have not modified this code.
  public static int[] twoSumOther(int[] nums, int target) {
    HashMap<Integer, Integer> prevMap = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      int diff = target - num;

      if (prevMap.containsKey(nums[i])) {
        return new int[]{prevMap.get(num), i};
      }

      prevMap.put(diff, i);
    }

    return new int[]{};
  }

  public static void main(String[] args) {

    int[] factors;
    if (args.length > 0) {
      factors = twoSum(new int[]{2, 11, 15, 7}, 9);
    } else {
      factors = twoSumOther(new int[]{2, 11, 15, 7}, 9);
    }
    System.out.printf("Factor idx = {%d,%d}", factors[0], factors[1]);
//    var factors = twoSum(new int[]{2,3,4}, 6);
  }
}
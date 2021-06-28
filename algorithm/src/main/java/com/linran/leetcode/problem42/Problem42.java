package com.linran.leetcode.problem42;

import org.junit.Assert;

public class Problem42 {

  public static void main(String[] args) {
    Solution solution = new Solution();
    Assert.assertEquals(
        6,
        solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})

    );

    Assert.assertEquals(
        6,
        solution.maxSubArray0(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})
    );
  }


  /**
   * 1. dp
   * dp[i] = max(dp[i-1] + arr[i], arr[i])
   */
  static class Solution {

    public int maxSubArray(int[] nums) {
      if (nums.length <= 0) {
        return 0;
      }
      int[] dp = new int[nums.length + 1];
      dp[0] = nums[0];
      int max = dp[0];
      for (int i = 1; i < nums.length; i++) {
        dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        if (max < dp[i]) {
          max = dp[i];
        }
      }
      return max;
    }

    // 简化版
    public int maxSubArray0(int[] nums) {
      if (nums.length <= 0) {
        return 0;
      }
      int max = Integer.MIN_VALUE, tmp = 0;
      for (int val : nums) {
        tmp = Math.max(tmp, 0) + val;
        max = Math.max(max, tmp);
      }
      return max;
    }
  }
}

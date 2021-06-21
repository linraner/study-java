package com.linran.leetcode.problem62;

import java.util.Arrays;
import org.junit.Assert;

public class Problem62 {

  public static void main(String[] args) {
    Solution solution = new Solution();
    Assert.assertTrue(solution.uniquePaths(3, 7) == 28);
    Assert.assertTrue(solution.uniquePaths(3, 3) == 6);
  }


  static class Solution {

    /**
     * 00 -> m,n c( (m+n-2), n)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
      int[] dp = new int[n];
      // 0,0 0 1,1-2
      Arrays.fill(dp, 1);
      for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
          dp[j] += dp[j - 1];
        }
      }
      return dp[n - 1];
    }
  }
}


package com.linran.leetcode.problem322;

import org.junit.Assert;

public class Problem322 {

  public static void main(String[] args) {
    Solution solution = new Solution();
    Assert.assertEquals(3,
        solution.coinChange(new int[]{1, 2, 5}, 11)
    );

    Assert.assertEquals(-1,
        solution.coinChange(new int[]{2}, 3)
    );

    Assert.assertEquals(0,
        solution.coinChange(new int[]{1}, 0)
    );
  }

  /**
   * f(n) = min(f(n-k), for k in [1,k] )
   */
  static class Solution {

    public int coinChange(int[] coins, int amount) {




      return 0;
    }
  }

}

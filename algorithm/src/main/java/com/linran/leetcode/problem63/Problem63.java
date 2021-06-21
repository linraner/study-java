package com.linran.leetcode.problem63;

import org.junit.Assert;

public class Problem63 {

  public static void main(String[] args) {
    Solution solution = new Solution();
    Assert.assertTrue(
        solution.uniquePathsWithObstacles(
            new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
            }
        ) == 2
    );

    Assert.assertTrue(
        solution.uniquePathsWithObstacles(
            new int[][]{
                {0, 1},
                {0, 0}
            }
        ) == 1
    );

    Assert.assertTrue(
        solution.uniquePathsWithObstacles(
            new int[][]{
                {1, 0},
            }
        ) == 0
    );


  }

  static class Solution {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
      if (obstacleGrid == null || obstacleGrid.length == 0) {
        return 0;
      }
      int m = obstacleGrid.length;
      int n = obstacleGrid[0].length;
      int[][] dp = new int[m][n];
      for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
          dp[i][0] = 1;
      }
      for (int i = 0; i < n && obstacleGrid[0][i] == 0; i++) {
          dp[0][i] = 1;
      }

      for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
          if (obstacleGrid[i][j] == 0) {
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
          }
        }
      }
      return obstacleGrid[m-1][n-1] == 1
          || obstacleGrid[0][0] == 1
          ? 0 :  dp[m - 1][n - 1];
    }
  }
}



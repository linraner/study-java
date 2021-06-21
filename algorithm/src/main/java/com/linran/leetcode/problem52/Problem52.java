package com.linran.leetcode.problem52;

import java.util.Arrays;
import org.junit.Assert;

/**
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 4 输出：2 解释：如上图所示，4 皇后问题存在两个不同的解法。 示例 2：
 * <p>
 * 输入：n = 1 输出：1  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 9 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * <p>
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/n-queens-ii 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Problem52 {

  public static void main(String[] args) {
    Solution solution = new Solution();

    Assert.assertEquals(1, solution.totalNQueens(1));
    Assert.assertEquals(0, solution.totalNQueens(2));
    Assert.assertEquals(0, solution.totalNQueens(3));
    Assert.assertEquals(2, solution.totalNQueens(4));

  }

  static class Solution {

    private static int count = 0;

    public int totalNQueens(int n) {
      // 初始化掉
      count = 0;
      if (n < 1) {
        return 0;
      }
      int[][] arr = new int[n][n];
      for (int[] ints : arr) {
        Arrays.fill(ints, 0);
      }
      slave(n, arr, 0);
      return count;
    }

    private void slave(int n, int[][] arr, int row) {
      if (n == row) {
        count++;
        return;
      }
      for (int col = 0; col < n; col++) {
        if (!valid(arr, n, row, col)) {
          continue;
        }
        arr[row][col] = 1;
        slave(n, arr, row + 1);
        arr[row][col] = 0;
      }
    }

    private boolean valid(int[][] arr, int n, int row, int col) {
      for (int i = 0; i < row; i++) {
        if (isMatch(arr[i][col])) {
          return false;
        }
      }
      for (int i = 0; i < col; i++) {
        if (isMatch(arr[row][i])) {
          return false;
        }
      }

      for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
        if (isMatch(arr[i][j])) {
          return false;
        }
      }

      for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
        if (isMatch(arr[i][j])) {
          return false;
        }
      }
      return true;
    }

    private boolean isMatch(int val) {
      return val == 1;
    }
  }
}


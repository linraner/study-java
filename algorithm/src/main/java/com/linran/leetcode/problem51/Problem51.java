package com.linran.leetcode.problem51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;

/**
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * <p>
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 4 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]] 解释：如上图所示，4 皇后问题存在两个不同的解法。 示例 2：
 * <p>
 * 输入：n = 1 输出：[["Q"]]
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/n-queens 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Problem51 {

  public static void main(String[] args) {
    Solution solution = new Solution();

    Assert.assertTrue(
        "[[\".Q..\",\"...Q\",\"Q...\",\"..Q.\"],[\"..Q.\",\"Q...\",\"...Q\",\".Q..\"]]".equals(solution.solveNQueens(4).toArray())
    );

    Assert.assertTrue(
        "[[\"Q\"]]".equals(
            solution.solveNQueens(1)
        )
    );

  }

}

class Solution {

  List<List<String>> result = new ArrayList<List<String>>();

  public List<List<String>> solveNQueens(int n) {
    char[][] area = new char[n][n];
    for (char[] chars : area) {
      Arrays.fill(chars, '.');
    }
    salve(n, area, 0);
    return result;
  }

  private void saveResult(char[][] area) {
    List<String> list = new ArrayList<String>();
    for (char[] chars : area) {
      list.add(String.copyValueOf(chars));
    }
    result.add(list);
  }


  private void salve(int n, char[][] area, int row) {
    // 终止条件
    if (row == n) {
      saveResult(area);
      return;
    }
    // 逐列遍历
    for (int col = 0; col < n; col++) {
      if (!valid(area, row, col, n)) {
        continue;
      }

      process(area, row, col);
      salve(n, area, row + 1);
      // 恢复 area 进入下列选择
      area[row][col] = '.';
    }
  }

  private boolean valid(char[][] area, int row, int col, int n) {
    // 同一行 同一列 对边
    for (int i = 0; i < row; i++) {
      if (isMatch(area[i][col])) {
        return false;
      }
    }

    for (int i = 0; i < col; i++) {
      if (isMatch(area[row][i])) {
        return false;
      }
    }

    // 左上方
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if (isMatch(area[i][j])) {
        return false;
      }
    }

    for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
      if (isMatch(area[i][j])) {
        return false;
      }
    }
    return true;
  }

  private boolean isMatch(char ch) {
    return ch == 'Q';
  }

  private void process(char[][] area, int row, int col) {
    area[row][col] = 'Q';
  }


}
package com.linran.leetcode.problem120;

import com.google.common.collect.Lists;
import java.util.List;
import org.junit.Assert;

/**
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 *
 * 输入：triangle = [[-10]]
 * 输出：-10
 *  
 *
 * 提示：
 *
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *  
 *
 * 进阶：
 *
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Problem120 {

  public static void main(String[] args) {
    Solution solution = new Solution();
    Assert.assertEquals(11,
        solution.minimumTotal(
            Lists.newArrayList(
                Lists.newArrayList(2),
                Lists.newArrayList(3, 4),
                Lists.newArrayList(6, 5, 7),
                Lists.newArrayList(4, 1, 8, 3)
            )
        ));

    Assert.assertEquals(11,
        solution.minimumTotal0(
            Lists.newArrayList(
                Lists.newArrayList(2),
                Lists.newArrayList(3, 4),
                Lists.newArrayList(6, 5, 7),
                Lists.newArrayList(4, 1, 8, 3)
            )
        ));
  }


  /**
   * i 层数 j f[i][j] = min(f[i+1][j], f[i+1][j+1]) + t[i][j]
   */
  static class Solution {

    // bottom - up
    public int minimumTotal(List<List<Integer>> triangle) {
      int n = triangle.size();
      int[][] dp = new int[n + 1][n + 1];
      for (int i = n - 1; i >= 0; i--) {
        for (int j = 0; j <= i; j++) {
          dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
        }
      }
      return dp[0][0];
    }

    public int minimumTotal0(List<List<Integer>> triangle) {
      int n = triangle.size();
      int[] dp = new int[n + 1];
      for (int i = n - 1; i >= 0; i--) {
        for (int j = 0; j <= i; j++) {
          dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
        }
      }
      return dp[0];
    }
  }
}


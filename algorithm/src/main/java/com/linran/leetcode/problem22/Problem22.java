package com.linran.leetcode.problem22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：["()"]
//
//
// 示例 3
// n = 2
// >>>> ()() | (()) |
//
//
//
// 提示：
//
//
// 1 <= n <= 8
//
// Related Topics 字符串 动态规划 回溯
// 👍 1851 👎 0
public class Problem22 {

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.generateParenthesis(0));
    System.out.println(solution.generateParenthesis(1));
    System.out.println(solution.generateParenthesis(2));
    System.out.println(solution.generateParenthesis(3));
  }

  static class Solution {

    private final List<String> results = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
      if (n <= 0) {
        return Collections.emptyList();
      }
      dfs("", n, n);
      return results;
    }

    private void dfs(String s, int l, int r) {
      if (l == 0 && r == 0) {
        results.add(s);
        return;
      }
      if (l > r) {
        return;
      }

      if (l > 0) {
        dfs(s + "(", l - 1, r);
      }
      if (r > 0) {
        dfs(s + ")", l, r - 1);
      }
    }


  }

}



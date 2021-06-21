package com.linran.notes.template.dp;

import java.util.Arrays;
import org.junit.Assert;

/**
 * 最长公共子序列问题
 */
public class LCS {

  public static void main(String[] args) {
    LCS lcs = new LCS();

    Assert.assertEquals(
        2,
        lcs.solution0("asdasd", "as")
    );
  }

  // 0. 二维数组 取最后枚举状态
  protected int solution0(String s1, String s2) {
    if (isEmpty(s1) || isEmpty(s2)) {
      return 0;
    }
    int len1 = s1.length();
    int len2 = s2.length();
    int[][] dp = new int[len1+1][len2+1];
    for (int[] i : dp) {
      Arrays.fill(i, 0);
    }
    for (int i = 1; i < len1+1; i++) {
      for (int j = 1; j < len2+1; j++) {
        if (s1.charAt(i-1) == s2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[len1][len2];
  }

  protected boolean isEmpty(String s) {
    return s == null || s.length() == 0;
  }


}

package com.linran.notes.template.slidingwindow;

import org.junit.Assert;

public class MinSubSeqTest {

  public static void main(String[] args) {
    String s = "";
    String t = "";
    String expect = "";
    MinSubSeq minSubSeq = new MinSubSeq();
    String result = minSubSeq.minSeq(s, t);
    Assert.assertEquals(result, expect);
  }
}

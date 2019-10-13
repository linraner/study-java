package com.lin;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-10-12 18:37
 * @Description: 数字转中文读法
 **/

public class QAnXinMain {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
//        String s = cin.next();
        String s = "11119";
//        String s = "10081";
//        String s = "10080";
        String map = "零一二三四五六七八九";
//        String index = "万千百十";
        Map<Integer, String> indexMap = new HashMap();
        indexMap.put(5, "万");
        indexMap.put(4, "千");
        indexMap.put(3, "百");
        indexMap.put(2, "十");

        StringBuilder sb = new StringBuilder();
        try {
            Integer num = Integer.parseInt(s);
            int cnt = s.length();
            if (cnt == 2 && s.charAt(0) == '1') {
                char tCh = s.charAt(1);
                int tNum = Integer.parseInt(String.valueOf(tCh));
                if (s.charAt(1) == '0') {
                    System.out.println("十");
                } else {
                    System.out.println("十"+map.charAt(tNum));
                }
            } else {
                for (int i = 0; i < s.length(); i++) {
                    char tCh = s.charAt(i);
                    int tNum = Integer.parseInt(String.valueOf(tCh));
//                System.out.print(tNum);

                    if (i == s.length() - 1 && tNum == 0) {
                        break;
                    }
                    sb.append(map.charAt(tNum));
                    if (cnt > 1 && tNum != 0) {

                        sb.append(indexMap.get(cnt));
                    }
                    cnt--;
                }
                String ans = sb.toString();
                ans = ans.replaceAll("零零零", "零");
                ans = ans.replaceAll("零零", "零");
                if (ans.substring(ans.length()-1, ans.length()).equals("零")) {
                    ans = ans.substring(0, ans.length() - 1);
                }
                System.out.println(ans);
            }
        } catch (Exception e) {
            System.out.println("错误输入");
        }
    }
}




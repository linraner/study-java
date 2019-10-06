package com.lin.algorithm.linklist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-29 16:22
 * @Description:
 **/
public class Main {
    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        int[] nums3 = intersection(nums1, nums2);
        for (int i = 0; i < nums3.length; i++) {
            System.out.print(nums3[i] + " ");
        }
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap(nums1.length + 3);
        for (int i = 0; i < nums1.length - 1; i++) {
            map.put(i, nums1[i]);
        }
        for (int i = 0; i < nums2.length - 1; i++) {
            if (map.containsValue(nums2[i])) {
                list.add(nums2[i]);
            }
        }
        int[] ans = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}

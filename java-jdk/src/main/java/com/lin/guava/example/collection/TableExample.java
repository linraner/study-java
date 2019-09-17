package com.lin.guava.example.collection;

import com.google.common.collect.HashBasedTable;

import java.util.Map;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-17 13:21
 * @Description: 矩阵
 **/
public class TableExample {
    public static void main(String[] args) {
        HashBasedTable<Integer, Integer, String> table = HashBasedTable.create();
        table.put(1, 1, "11");
        table.put(1, 2, "12");
        table.put(1, 3, "13");
        table.put(2, 1, "21");
        table.put(2, 2, "22");
        table.put(2, 3, "23");

        boolean contains11 = table.contains(1, 1);
        System.out.println("contains11 = " + contains11);
        boolean containsColumn2 = table.containsColumn(2);
        System.out.println("containsColumn2 = " + containsColumn2);
        boolean containsRow1 = table.containsRow(1);
        System.out.println("containsRow1 = " + containsRow1);
        boolean containsValue11 = table.containsValue("11");
        System.out.println("containsValue11 = " + containsValue11);
        table.remove(1, 3);
        String s = table.get(3, 4);
        System.out.println("s = " + s);

        Map<Integer, String> columnMap = table.column(1);
        System.out.println("columnMap = " + columnMap);
        Map<Integer, String> rowMap = table.row(2);
        System.out.println("rowMap = " + rowMap);


    }
}
